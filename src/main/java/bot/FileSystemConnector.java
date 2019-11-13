package bot;

import interfaces.DataBaseConnector;

import java.io.*;

import static bot.StringConstants.errMessage;

public class FileSystemConnector implements DataBaseConnector {
    public static boolean WriteQuestion(Question quest) {
        try {
            FileOutputStream outputStream = new FileOutputStream("questions/" + quest.ID +"_question.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // сохраняем игру в файл
            objectOutputStream.writeObject(quest);

            //закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
            return true;
        } catch (Throwable e){
            System.out.println(errMessage + e.toString());
            return false;
        }

    }

    public static Question ReadQuestion(int questID) {
        Question question = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("questions/" + questID +"_question.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            question = (Question) objectInputStream.readObject();

            objectInputStream.close();
        } catch (Throwable e){
            System.out.println(errMessage + e.toString());
        }

        return question;
    }
}
