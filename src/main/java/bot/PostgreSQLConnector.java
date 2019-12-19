package bot;

import bot.dataClasses.UserInfo;
import bot.interfaces.DataBaseConnector;

import java.io.*;
import java.net.URI;
import java.sql.*;
import java.util.Base64;

import static bot.StringConstants.errMessage;

public class PostgreSQLConnector implements DataBaseConnector {
    private static Connection conn;
    private static Statement statement;
    private static ResultSet resSet;

    static {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            System.out.println(dbUrl);

            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("База Подключена!");
            statement = conn.createStatement();
            statement.execute("CREATE TABLE if not exists users (id INTEGER PRIMARY KEY, userInfo TEXT);");
            System.out.println("Таблица 'users' создана или уже существует.");

        } catch (Exception e) {
            System.out.println("Что-то не так, база не подключена((");
            e.printStackTrace();
        }
    }

    @Override
    public boolean writeUser(UserInfo userInfo) {
        try {
            byte[] byteArray;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
                out.writeObject(userInfo);
                byteArray = bos.toByteArray();
            }

            String bof = Base64.getEncoder().encodeToString(byteArray);


            String sql = "UPDATE users SET userInfo = ?  WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, bof);
            preparedStatement.setLong(2, userInfo.ID);
            if (preparedStatement.executeUpdate() <= 0){
                sql = "INSERT INTO users (id, userInfo) VALUES (?, ?);";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1, userInfo.ID);
                preparedStatement.setString(2, bof);
                System.out.println(preparedStatement.executeUpdate());
            };
            System.out.println("записан " + userInfo.ID);
            return true;
        } catch (Exception e){
            System.out.println(errMessage);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserInfo readUser(long userID) {
        UserInfo userInfo = null;
        try {
            String sql = "SELECT userInfo FROM users WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, userID);

            resSet = preparedStatement.executeQuery();
            String bof = resSet.getString("userInfo");
            byte[] bytes = Base64.getDecoder().decode(bof);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
                userInfo = (UserInfo) in.readObject();
            }
        } catch (Exception e){
            userInfo = new UserInfo(userID);
            System.out.println(errMessage);
            e.printStackTrace();
        }

        if (userInfo == null) {
            userInfo = new UserInfo(userID);
            userInfo.state = "error";
        }

        return userInfo;
    }
}
