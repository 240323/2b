import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFAValidator {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: java DFAValidator <DFA file> <input string file>");
            System.exit(1);
        }

        // テキストファイルからDFAを読み込む
        Scanner dfaScanner = new Scanner(new File(args[0]));
        int numStates = dfaScanner.nextInt(); //DFAの状態数
        int numSymbols = dfaScanner.nextInt();//DFAの入力の数　
        int numFinalStates = dfaScanner.nextInt();//DFAの受理状態の数
        dfaScanner.nextLine();//DFAのアルファベットの記号を表す文字列
        String alphabet = dfaScanner.nextLine();
        int[][] transitions = new int[numStates][numSymbols];
        for (int i = 0; i < numStates; i++) {//DFAの遷移関数のテーブルを読み込んで、二次元配列transitionsに格納
            String[] line = dfaScanner.nextLine().split(" ");
            for (int j = 0; j < numSymbols; j++) {
                transitions[i][j] = Integer.parseInt(line[j]);
            }
        }
        int startState = dfaScanner.nextInt();//DFAの初期状態
        int[] acceptStates = new int[numFinalStates];
        for (int i = 0; i < numFinalStates; i++) {
            acceptStates[i] = dfaScanner.nextInt();
        }
        dfaScanner.close();

        // テキストファイルから文字列wを読み込む
        Scanner wScanner = new Scanner(new File(args[1]));
        int length = Integer.parseInt(wScanner.nextLine());
        String w = "";
        if (length > 0) {
            w = wScanner.nextLine();
        }
        wScanner.close();

        // DFAが文字列wを受理するかどうかを判断する
        int currentState = startState;
        for (int i = 0; i < w.length(); i++) {
            int symbolIndex = alphabet.indexOf(w.charAt(i));
            if (symbolIndex == -1) {
                System.err.println("Invalid symbol in w: " + w.charAt(i));
                System.exit(1);
            }
            currentState = transitions[currentState -1][symbolIndex];
        }
        for (int acceptState : acceptStates) {
            if (currentState == acceptState) {
                System.out.println("w is accepted by the DFA.");
                return;
            }
        }
        System.out.println("w is not accepted by the DFA.");
    }
}
