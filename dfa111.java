//課題4を解くプログラム
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dfa111 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Usage: java DFAValidator <DFA file> ");
            System.exit(1);
        }
        
        
        // テキストファイルからDFAを読み込む
        Scanner dfaScanner = new Scanner(new File(args[0]));
        int numStates = dfaScanner.nextInt(); //DFAの状態数
        int numSymbols = dfaScanner.nextInt();//DFAの入力の数　
        int numFinalStates = dfaScanner.nextInt();//DFAの受理状態の数
        dfaScanner.nextLine();//改行を読み飛ばす
        String alphabet = dfaScanner.nextLine().trim();//DFAのアルファベットの記号を表す文字列
        int[][] transitions = new int[numStates][numSymbols];
        for (int i = 0; i < numStates; i++) {//DFAの遷移関数のテーブルを読み込んで、二次元配列transitionsに格納
            for (int j = 0; j < numSymbols; j++) {
                transitions[i][j] = dfaScanner.nextInt();
            }
        }
        int startState = dfaScanner.nextInt();
        int[] acceptStates = new int[numFinalStates];
        for (int i = 0; i < numFinalStates; i++) {
            acceptStates[i] = dfaScanner.nextInt();
        }
        dfaScanner.close();

       // 111 を含む文字列を生成し、DFAがそれを受理するかどうかを判定する
       String inputString = "111"; // 最初に 111 を含む文字列を生成
       int currentState = startState;
       for (char symbol : inputString.toCharArray()) {
           int symbolIndex = alphabet.indexOf(symbol);
           currentState = transitions[currentState][symbolIndex];
       }
       boolean isAccepted = false;
       for (int acceptState : acceptStates) {
           if (currentState == acceptState) {
               isAccepted = true;
               break;
           }
       }
       System.out.println(isAccepted ? "accepted" : "rejected");
   }
}

