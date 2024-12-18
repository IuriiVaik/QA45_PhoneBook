package utils;

import java.util.Random;

public class RandomUtils {


    static Random random = new Random();

   // public static String generate

    public static String generateString(int length){
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        char [] randomStr = new char[length];
        int index = 0;
        for (int i = 0; i < length; i++){
            index = random.nextInt(characters.length());
            randomStr[i] = characters.charAt(index);
        }
        return new String(randomStr);
    }

    public static String generateEmail(int lenght){
        String[] domains = {"gmail.com", "mail.com", "yahoo.com", "yandex.ru"};
        String domain = domains[random.nextInt(domains.length)];
        return (generateString(lenght)+"@"+domain);
    }

    public static String generatePhone(int length){
        String characters = "0123456789";
        char [] randomStr = new char[length];
        int index = 0;
        for (int i = 0; i < length; i++){
            index = random.nextInt(characters.length());
            randomStr[i] = characters.charAt(index);
        }
        return new String(randomStr);
    }

}
