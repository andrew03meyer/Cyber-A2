import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QTwo {
    public void processEx2(){
        String encoder = "TESSOFTHEDURBERVILLES";
        String cipherText = getExercise("cexercise2.txt");

        stringShift(encoder, cipherText);
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

    /**
     * Shifts all contents of a string by a specific string
     * @param encoding
     * @param cipherText
     * @return decrypted string
     */
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
}
