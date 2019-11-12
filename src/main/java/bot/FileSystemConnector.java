package bot;

import interfaces.DataBaseConnector;

import java.io.*;

public class FileSystemConnector implements DataBaseConnector {
    public static void WriteQuestion(Question quest, int groupID) {
        try {
            FileOutputStream outputStream = new FileOutputStream(groupID + "_" + quest.ID +"_question.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // сохраняем игру в файл
            objectOutputStream.writeObject(quest);

            //закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
        } catch (Throwable e){
            System.out.println("Упс, при записи ошибочка вышла... " + e.toString());
        }

    }

    public static Question ReadQuestion(int groupID, int questID) {
        Question question = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(groupID + "_" + questID +"_question.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            question = (Question) objectInputStream.readObject();

            objectInputStream.close();
        } catch (Throwable e){
            System.out.println("Упс, при чтении ошибочка вышла... " + e.toString());
        }

        return question;
    }
}
