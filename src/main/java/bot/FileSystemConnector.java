package bot;

import bot.dataClasses.CustomGameData;
import bot.dataClasses.UserInfo;
import bot.interfaces.DataBaseConnector;

import java.io.*;

import static bot.StringConstants.errMessage;

public class FileSystemConnector implements DataBaseConnector {
    public boolean writeQuestion(Question quest) {
        try {
            FileOutputStream outputStream = new FileOutputStream("questions" + StringConstants.separator + quest.ID +"_question.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(quest);

            objectOutputStream.close();
            System.out.println("Файл questions" + StringConstants.separator + quest +"_question.ser записан");
            return true;
        } catch (Exception e){
            System.out.println(errMessage + e.toString());
            return false;
        }

    }

    public Question readQuestion(int questID) {
        Question question = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("questions" + StringConstants.separator + questID +"_question.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            question = (Question) objectInputStream.readObject();

            objectInputStream.close();
        } catch (Throwable e){
            System.out.println(errMessage + e.toString());
        }

        return question;
    }

    public boolean deleteQuestion(int questID) {
        File file = new File("questions" + StringConstants.separator + questID +"_question.ser");

        if(file.delete()) {
            System.out.println("questions" + StringConstants.separator + questID +"_question.ser файл удален");
            return true;
        }

        System.out.println("Файла questions" + StringConstants.separator + questID + "_question.ser не обнаружено");
        return false;
    }

    public boolean writeUser(UserInfo userInfo) {
        try {
            FileOutputStream outputStream = new FileOutputStream("questions" + StringConstants.separator + userInfo.ID +"_user.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(userInfo);

            objectOutputStream.close();
            System.out.println("Файл questions" + StringConstants.separator + userInfo.ID +"_user.ser записан");
            return true;
        } catch (Exception e){
            System.out.println(errMessage + e.toString());
            return false;
        }

    }

    public UserInfo readUser(long userID) {
        UserInfo userInfo = new UserInfo(userID);
        try {
            FileInputStream fileInputStream = new FileInputStream("questions" + StringConstants.separator + userID +"_user.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            userInfo = (UserInfo) objectInputStream.readObject();
            System.out.println("Файл questions" + StringConstants.separator + userID +"_user.ser прочитан");

            objectInputStream.close();
        } catch (Throwable e){
            System.out.println(errMessage + e.toString());
        }

        return userInfo;
    }
}
