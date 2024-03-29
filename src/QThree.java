import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QThree{
    public void processEx3(){
        ArbitraryVignere(4);
    }

    /**
     * Perform frequency analysis on the text for a vignere key that is l digits long
     * @param length
     */
    public void ArbitraryVignere(int length){
        // String tess26 = getTess26();
        String exercise = getExercise("cexercise4.txt");
        ArrayList<String> sixLetters = new ArrayList<>();

        // Split into l length strings
        int count = 0;
        while(exercise.length() > count){
            sixLetters.add(exercise.substring(count, count+length));
            // System.out.println(exercise.substring(count, count+length) +"\n");
            count+=length;
        }

        //Find most common letter in each column
        ArrayList<Character> chars = new ArrayList<>();
        
        for(int x = 0; x < length; x++){
            //hash for frequency
            HashMap<Character, Integer> hash1 = new HashMap<>();
            for(String item : sixLetters){    
                if(hash1.containsKey(item.charAt(x))){
                    hash1.put(item.charAt(x), hash1.get(item.charAt(x))+1);
                }
                else{
                    hash1.put(item.charAt(x), 1);
                }
            }

            System.out.println(hash1.toString());

            Character highestChar = 'a';
            int highestNum = -1;
            for(Character c : hash1.keySet()){
                if(hash1.get(c) > highestNum){
                    highestChar = c;
                    highestNum = hash1.get(c);
                }
            }

            chars.add(highestChar);
        }

        // System.out.println(chars.toString());

        // System.out.println(sixLetters.toString());

        //shift first 3 values
        String total = "";
        for(String temp : sixLetters) {
            String newTemp = temp;
            newTemp = shiftByNum(newTemp.charAt(0), 'H' - 'E') + newTemp.substring(1);
            newTemp = newTemp.substring(0, 1) + shiftByNum(newTemp.charAt(1), 'G' - 'E') + newTemp.substring(2);
            newTemp = newTemp.substring(0, 2) + shiftByNum(newTemp.charAt(2), 'H' - 'E') + newTemp.substring(3);
            newTemp = newTemp.substring(0, 3) + shiftByNum(newTemp.charAt(3), 'H' - 'D'); //+ newTemp.substring(4);
            // newTemp = newTemp.substring(0, 4) + shiftByNum(newTemp.charAt(4), 'U' - 'H') + newTemp.substring(5);
            // newTemp = newTemp.substring(0, 5) + shiftByNum(newTemp.charAt(5), 'G' - 'E');
            // System.out.println("Old value: " + temp);
            // System.out.println("New value: " + newTemp +"\n");
            
            
            total += newTemp;
        }
        //frequency for each column
        for(int x = 0; x < 4; x++){
            String col = "";
            for(String item : sixLetters){
                col += item.charAt(x);
            }
            // System.out.println(letterFreq(col).toString());
        }
        System.out.println(total);
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


    //----------------------------------------------------------//
    //                    Shift Methods                         //
    //----------------------------------------------------------//

    public Character shiftByNum(Character c, Integer num){

        int newNum = num;
        char newC = ' ';

        newC = (char) (c - newNum);

        if(newC < 'A'){
            newC = (char)(newC + 26);
        }

        if(newC > 'Z'){
            newC = (char)(newC - 26);
        }

        return newC;
    }

    public String stringShift(String encoding, String cipherText){
        int count = 0;
        String answer = "";

        // For each character
        for(Character c : cipherText.toCharArray()){
            answer += shiftLetter(c, encoding.charAt(count));
            count = (count+1) % encoding.length();
        }
        return answer;
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

    public static List<String> generatePermutations(char[] chars) {
        List<String> result = new ArrayList<>();
        generatePermutations(chars, 0, result);
        return result;
    }

    //----------------------------------------------------------//
    //              Permutation Methods                         \\
    //----------------------------------------------------------//

    private static void generatePermutations(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                generatePermutations(chars, index + 1, result);
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    //----------------------------------------------------------//
    //              File Methods                                \\
    //----------------------------------------------------------//

    private String getExercise(String fileName){
        String data;

        try {
            // File tess = new File("C:\\Users\\Andrew Meyer\\OneDrive - University of Kent\\Files\\Computer Science\\Year 2 (23-24)\\Intro to Cyber Security\\Assignment 2\\Excercises\\" + fileName);
            File tess = new File("..\\" + fileName);

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

    /**
     * Get the decrypted text document
     * @return string of that document
     */
    public String getTess26(){
        String data;

        try {
            // File tess = new File("C:\\Users\\andrr\\OneDrive - University of Kent\\Files\\Computer Science\\Year 2 (23-24)\\Intro to Cyber Security\\Assignment 2\\tess26.txt");
            File tess = new File("..\\..\\tess26.txt");
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
}