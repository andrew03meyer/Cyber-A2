import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class QSeven {
    public void processEx7(){
        String exercise = getExercise("cexercise7.txt");
        ArrayList<String> engletterFreq = new ArrayList<>();

        engletterFreq.add("E");
        engletterFreq.add("T");
        engletterFreq.add("N");
        engletterFreq.add("H");
        engletterFreq.add("O");
        engletterFreq.add("D");
        engletterFreq.add("R");
        
        
        engletterFreq.add("A");
        
        // engletterFreq.add("I");
        
        engletterFreq.add("S");
        
        // engletterFreq.add("L");
        // engletterFreq.add("U");
        // engletterFreq.add("W");
        // engletterFreq.add("M");
        // engletterFreq.add("C");
        // engletterFreq.add("F");
        // engletterFreq.add("G");
        // engletterFreq.add("Y");
        // engletterFreq.add("P");
        // engletterFreq.add("B");
        // engletterFreq.add("V");
        // engletterFreq.add("K");
        // engletterFreq.add("X");
        // engletterFreq.add("J");
        // engletterFreq.add("Q");
        // engletterFreq.add("Z");
        
        
        
        
        
        
        // engletterFreq.add("L");
        // engletterFreq.add("U");
        // engletterFreq.add("C");
        // engletterFreq.add("M");

        System.out.println(letterFreq(getTess26()));

        


        HashMap<Character, Integer> letterFreq = letterFreq(exercise);
        System.out.println(letterFreq);
        System.out.println(convert(engletterFreq, exercise, letterFreq));
    }
    
    private String convert(ArrayList<String> engLetterFreq, String exercise, HashMap<Character, Integer> letterFreq){
        String data = "";
        
        //Go through character by character and replace with the corresponding frequencies of the english language
        for(Character c : exercise.toCharArray()){
            int charRank = rank(c, letterFreq);
            // System.out.println(c + ": " + charRank);
            if(charRank < engLetterFreq.size()){
                data += engLetterFreq.get(charRank);
            }
            else{
                data += '_';
            }
        }

        return data;
    }

    private int rank(Character letter, HashMap<Character, Integer> letterFreq){
        int rank = letterFreq.size();
        for(Character key : letterFreq.keySet()){
            if(letterFreq.get(key) < letterFreq.get(letter)){
                rank--;
            }
        }
        return rank-1;
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

    public String getTess26(){
        String data;

        try {
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
    
    private String getExercise(String fileName){
        String data;

        try {
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
}
