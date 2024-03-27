import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QOne {
    /*
     * Method for exercise 1
     * @calls - bruteForce
     */
    public void processEx1(){
        bruteForce("cexercise1.txt");
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

    /**
     * Gets the excercise file of an input filename
     * @param fileName
     * @return String of text contained in that file
     */
        public String getExercise(String fileName){
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
