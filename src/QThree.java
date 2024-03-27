import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QThree{
    public void processEx3(String fileName){
        
    }

    /**
     * Perform frequency analysis on the text for a vignere key that is l digits long
     * @param length
     */
    public void ArbitraryVignere(int length){
        // String tess26 = getTess26();
        String exercise = getExercise("cexercise3.txt");
        ArrayList<String> sixLetters = new ArrayList<>();

        String tess26 = getTess26();

        // Split into l length strings
        int count = 0;
        while(exercise.length() > count){
            sixLetters.add(exercise.substring(count, count+length));
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

        //Shifts
        char[] mostCommon = {'e', 't', 's', 'i', 'a', 'n'};
        List<String> permutations = generatePermutations(mostCommon);
        for (String perm : permutations) {
            
            //Translate sample given these values
            ArrayList<String> decoded = new ArrayList<>();

            Boolean foundMatch = false;

            for(String item : sixLetters){ 
                String newStr = item.toLowerCase();
                for(int x = 0; x < length; x++){
                    //Old string but with letter at x shifted 
                    // System.out.println(newStr.charAt(x) - perm.toLowerCase().charAt(x));
                    newStr = newStr.replace(newStr.charAt(x), shiftByNum(newStr.charAt(x), newStr.charAt(x) - perm.toLowerCase().charAt(x)));
                    decoded.add(newStr);

                    // //if decode contained in tess26
                    // if(tess26.contains(newStr)){
                    //     foundMatch = true;
                    // }
                }
            }
            // if(foundMatch){
                System.out.println("found a match: \n" + decoded.toString() + "\n\n");
            // }
        }
    }


    //----------------------------------------------------------//
    //                    Shift Methods                         //
    //----------------------------------------------------------//

    public Character shiftByNum(Character c, Integer num){

        char newC = ' ';

        newC = (char) (c - num);

        if(newC < 'A'){
            newC = (char)(newC + 26);
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