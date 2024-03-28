import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QFour{
    public void processEx4(){
        ArbitraryVignere();
    }

    /**
     * Perform frequency analysis on the text for a vignere key that is l digits long
     * @param length
     */
    public void ArbitraryVignere(){
        String exercise = getExercise("cexercise4.txt");
        
        // Split into l length strings
        ArrayList<String> fourLetters = split(exercise, 4);
        ArrayList<String> repeats = new ArrayList<>();

        //Find repeats
        int index1 = 0;
        for(String item : fourLetters){
            int index2 = 0;
            for(String comp : fourLetters){
                if(comp.equals(item) && !repeats.contains(item) && index1 != index2){
                    repeats.add(item);
                }
                index2++;
            }
            index1++;
        }

        System.out.println(fourLetters.toString());
        System.out.println(repeats.toString());
        //Find difference for each repeat
        for(int x = 0; x < repeats.size(); x++){
            
            int count = 0;
            ArrayList<Integer> index = new ArrayList<>();
            for(String item : fourLetters){
                if(item.equals(repeats.get(x))){
                    index.add(count);
                }
                count+=4;
            }
            int difference = index.get(1) - index.get(0);
            Boolean divsix = false;
            Boolean divfive = false;
            Boolean divfour = false;

            if(difference % 6 == 0){
                divsix = true;
            }
            if(difference % 5 == 0){
                divfive = true;
            }
            if(difference % 4 == 0){
                divfour = true;
            }
            System.out.println(index.toString() + " difference: " + difference + " divisible by:\n6: " + divsix + "\n4: " + divfour + "\n5: " + divfive);
        }
    }

    private ArrayList<String> split(String exercise, int length){
        ArrayList<String> threeLetters = new ArrayList<>();
        int count = 0;
        while(exercise.length() > count){
            String sub = exercise.substring(count, count+length);

            threeLetters.add(sub);
            count+=length;
        }
        return threeLetters;
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