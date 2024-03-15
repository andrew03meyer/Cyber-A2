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

    public String getExercise(String fileName){
        String data;

        try {
            File tess = new File("C:\\Users\\andrr\\OneDrive - University of Kent\\Files\\Computer Science\\Year 2 (23-24)\\Intro to Cyber Security\\Assignment 2\\Excercises\\" + fileName);
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

    /*
     * Shifts by the difference between the two most common characters in two texts
     * @param - the file to do analysis
     * Prints decoded value
     */
    private void shiftByFrequency(String exercise){
        String text = getTess26();
        String cipherText = getExercise(exercise);

        HashMap<Character, Integer> textLetterFreq = letterFreq(text);
        HashMap<Character, Integer> cipherTextLetterFreq = letterFreq(cipherText);

        Character textChar = findHighestFreqChar(textLetterFreq);
        Character cipherChar = findHighestFreqChar(cipherTextLetterFreq);

        System.out.println(textChar + " : " + cipherChar);

        // int cipherCharNum = (int) cipherChar;
        // int textCharNum = (int) textChar;

        String decode = "";

        for(Character c : cipherText.toCharArray()){
            char newC = shiftByNum(c, Math.abs(textChar - cipherChar));
            decode += newC;
        }

        System.out.println(decode);
    }

    public Character shiftByNum(Character c, Integer num){

        char newC = ' ';

        newC = (char) (c - num);

        if(newC < 'A'){
            newC = (char)(newC + 26);
        }

        return newC;
    }

    /*
     * Try each shift (+1 to +25)
     * Uses ascii values for letters, and looping at letter Z
     * Prints decryption for each shift
     */
    public void bruteForce(String fileName){
        String cipherText = getExercise(fileName);
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
            System.out.println(x + ": " + "\n" + decode + "\n");
        }
    }

    public void stringShift(String encoding, String cipherText){
        int count = 0;
        String answer = "";

        // For each character
        for(Character c : cipherText.toCharArray()){
            answer += shiftLetter(c, encoding.charAt(count));
            count = (count+1) % encoding.length();
        }
        System.out.println(answer);
    }

    private Character shiftLetter(Character shiftee, Character shifter){
        char newC = ' ';

        int num = shiftee - Math.abs((shifter - 'A'));
        newC = (char) num;

        if(newC < 'A'){
            newC = (char)(newC + 26);
        }

        return newC;
    }

    /*
     * Method for exercise 1
     * @calls - bruteForce
     */
    public void processEx1(){
        bruteForce("cexercise1.txt");
    }
    
    public void processEx2(){
        String encoder = "TESSOFTHEDURBERVILLES";
        String cipherText = getExercise("cexercise2.txt");

        stringShift(encoder, cipherText);
    }

    public void processEx3(String fileName){

        shiftByFrequency(fileName);
    }
}
