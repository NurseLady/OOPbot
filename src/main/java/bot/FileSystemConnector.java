package bot;

import interfaces.DataBaseConnector;

import java.io.*;

import static bot.StringConstants.errMessage;

public class FileSystemConnector implements DataBaseConnector {
    public static boolean writeQuestion(Question quest) {
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

    public static Question readQuestion(int questID) {
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

    public static void deleteQuestion(int questID) {
        File file = new File("questions/" + questID +"_question.ser");

        if(file.delete()) {
            System.out.println("questions/" + questID +"_question.ser файл удален");
        } else
            System.out.println("Файла questions/\" + questID +\"_question.ser не обнаружено");
    }
}
