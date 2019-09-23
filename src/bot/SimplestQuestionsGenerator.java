package bot;

import interfaces.IQuestionsGenerator;
import java.util.*;
import java.io.*;

public class SimplestQuestionsGenerator implements IQuestionsGenerator {
    public int question;
    public int GenerateQuestion(){
        return  question = new Random().nextInt(100);
    }
    public int GenerateAnswer(){return question;}
    public int GenerateBait(){return new Random().nextInt(100);}
