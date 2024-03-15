public class Main{
    public static void main(String args[]){
        TextAnalysis ta1 = new TextAnalysis();
        String data = ta1.getTess();
        ta1.letterFreq(data);
    }
}