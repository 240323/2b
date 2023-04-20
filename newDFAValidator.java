import java.io.FileNotFoundException;
import java.util.Scanner;

public class newDFAValidator {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 0) {
            System.err.println("Usage: java DFAValidator < <input string file>");
            System.exit(1);
        }

        Scanner stdin = new Scanner(System.in);

        // テキストファイルからDFAを読み込む
        int numStates = stdin.nextInt(); //DFAの状態数
        int numSymbols = stdin.nextInt();//DFAの入力の数　
        int numFinalStates = stdin.nextInt();//DFAの受理状態の数
        stdin.nextLine();//DFAのアルファベットの記号を表す文字列
        String alphabet = stdin.nextLine();
        int[][] transitions = new int[numStates][numSymbols];
        for (int i = 0; i < numStates; i++) {//DFAの遷移関数のテーブルを読み込んで、二次元配列transitionsに格納
            String[] line = stdin.nextLine().split(" ");
            for (int j = 0; j < numSymbols; j++) {
                transitions[i][j] = Integer.parseInt(line[j]);
            }
        }
        int startState = stdin.nextInt();//DFAの初期状態
        int[] acceptStates = new int[numFinalStates];
        for (int i = 0; i < numFinalStates; i++) {
            acceptStates[i] = stdin.nextInt();
        }

        // 標準入力から文字列wを読み込む
        int length = stdin.nextInt();
        String w = "";
        if (length > 0) {
            w = stdin.next();
        }
        stdin.close();

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

