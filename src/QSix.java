import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QSix {

    public void processEx6(int keySize){
        String exercise = getExercise("cexercise6.txt");
        ArrayList<ArrayList<Character>> split = new ArrayList<>();

        //permutations of keySize numbers
        int[] nums = new int[]{0,1,2,3};
        if(keySize == 5){
            nums = new int[]{0,1,2,3,4};
        }
        if(keySize == 6){
            nums = new int[]{0,1,2,3,4,5};
        }

        List<List<Integer>> perms = generatePermutations(nums);

        //Take chars of key size length from exercise
        for(int col = 0; col < exercise.length(); col+=(exercise.length()/keySize)){
            ArrayList<Character> colVals = new ArrayList<>();
            for(int row = 0; row < (exercise.length()/keySize); row++){
                colVals.add(exercise.charAt(col + row));
            }
            split.add(colVals);
        }

        System.out.println(split.size());
        System.out.println(split.get(0).size());

        for(List<Integer> permute : perms){
            ArrayList<ArrayList<Character>> newSplit = new ArrayList<>();
            for(Integer x : permute){
                newSplit.add(split.get(x));
            }
            
            checkContains(newSplit, keySize);
        }
    }

    public void checkContains(ArrayList<ArrayList<Character>> perms, int keySize){
        String temp = "";
        String tess26 = getTess26();

        //Add each character of each column to string
        for(int x = 0; x < perms.get(0).size(); x++){
            for(int y = 0; y < keySize; y++){
                temp += perms.get(y).get(x);
            }
        }
        
        if(tess26.contains(temp)){
            System.out.println("found: " + temp);
        }
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
    
    private static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutations(nums, 0, result);
        return result;
    }

    private static void generatePermutations(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                generatePermutations(nums, index + 1, result);
                swap(nums, index, i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
