import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;


public class TextAnalysis {
    public String getTess26(){
        String data;

        try {
            File tess = new File("C:\\Users\\andrr\\OneDrive - University of Kent\\Files\\Computer Science\\Year 2 (23-24)\\Intro to Cyber Security\\Assignment 2\\tess26.txt");
            Scanner myReader = new Scanner(tess);
            data = myReader.nextLine();
            myReader.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            data = "";
        }

        return data;
    }

    public String getEx1(){
        String data;

        try {
            File tess = new File("C:\\Users\\andrr\\OneDrive - University of Kent\\Files\\Computer Science\\Year 2 (23-24)\\Intro to Cyber Security\\Assignment 2\\Excercises\\cexercise1.txt");
            Scanner myReader = new Scanner(tess);
            data = myReader.nextLine();
            myReader.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            data = "";
        }

        return data;
    }

    public HashMap<Character, Integer> letterFreq(String data){
        HashMap<Character, Integer> letterFreq = new HashMap<>();
        
        int count = 0;
        while(count < data.length()){
            if(letterFreq.containsKey(data.charAt(count))){
                letterFreq.put(data.charAt(count), letterFreq.get(data.charAt(count))+1);
            }
            else{
                letterFreq.put(data.charAt(count), 1);
            }
            count++;
        }
        return letterFreq;
    }

    public void processEx1(){
        String text = getTess26();
        String cipherText = getEx1();

        HashMap<Character, Integer> textLetterFreq = letterFreq(text);
        HashMap<Character, Integer> cipherTextLetterFreq = letterFreq(cipherText);

        Character textCommon = findHighestFreqChar(textLetterFreq);
        Character cipherCommon = findHighestFreqChar(cipherTextLetterFreq);

        bruteForce(cipherText);
        // shift(cipherText, cipherCommon, textCommon);
    }

    private Character findHighestFreqChar(HashMap<Character, Integer> hashset){

        int highestInt = 0;
        Character highestChar = ' ';

        for(Character key : hashset.keySet()){
            if(hashset.get(key) > highestInt){
                highestInt = hashset.get(key);
                highestChar = key;
            }
        }

        return highestChar;
    }

    private void shift(String ciphertext, Character cipherChar, Character textChar){
        // List<Character> albet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        // int shiftVal = 0;

        int cipherCharNum = (int) cipherChar;
        int textCharNum = (int) textChar;

        String decode = "";

        for(Character c : ciphertext.toCharArray()){
            char newC = ((char)((int) c + ((cipherCharNum - textCharNum) % 26)));
            decode += newC;
        }

        System.out.println(decode);
    }

    public void bruteForce(String cipherText){
        //shift is +21 from ciphertext -> plain (therefore +5 to go from plain to cipher)
        for(int x = 1; x < 26; x++){
            String decode = "";
            for(Character c : cipherText.toCharArray()){
                char newC = (char)((int) c + x);
    
                if(newC > 'Z'){
                    newC = (char)(newC - 26);
                }
    
                decode += newC;
            }
            System.out.println(x + ": " + decode);
        }
    }
    
}
