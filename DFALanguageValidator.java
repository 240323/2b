//問題2を解く
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFALanguageValidator {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Usage: java DFAValidator <DFA file> <input string file>");
            System.exit(1);
        }

        

        // テキストファイルからDFAを読み込む
        Scanner dfaScanner = new Scanner(new File(args[0]));
        int numStates = dfaScanner.nextInt(); //DFAの状態数
        int numSymbols = dfaScanner.nextInt();//DFAの入力の数　
        int numFinalStates = dfaScanner.nextInt();//DFAの受理状態の数
        dfaScanner.nextLine();
        String alphabet = dfaScanner.nextLine();//DFAのアルファベットの記号を表す文字列
        int[][] transitions = new int[numStates][numSymbols];
        for (int i = 0; i < numStates; i++) {//DFAの遷移関数のテーブルを読み込んで、二次元配列transitionsに格納
            String[] line = dfaScanner.nextLine().split(" ");
            if (line.length != numSymbols) {
                System.err.println("Invalid number of symbols in transition function for state " + i);
                System.exit(1);
            }
            for (int j = 0; j < numSymbols; j++) {
                transitions[i][j] = Integer.parseInt(line[j]);
            }
        }
        int startState = dfaScanner.nextInt();//DFAの初期状態
        int[] acceptStates = new int[numFinalStates];
        for (int i = 0; i < numFinalStates; i++) {
            String line = dfaScanner.nextLine();
            String[] numbers = line.split(" ");
            if (numbers.length != 1) {
                System.err.println("Invalid accept state format: " + line);
                System.exit(1);
            }
            acceptStates[i] = dfaScanner.nextInt();
        }
        dfaScanner.close();



        /// 与えられたDFAが少なくとも1つの文字列を受理するかどうかを判定
    boolean[] reachable = new boolean[numStates];
    dfs(startState, reachable, transitions, alphabet);
    for (int i = 0; i < numStates; i++) {
        if (reachable[i]) {
            for (int j = 0; j < acceptStates.length; j++) {
                if (i == acceptStates[j]) {
                    System.out.println("Yes");
                    System.exit(0);
                }
            }
        }
    }
    System.out.println("No");
}

    private static void dfs(int state, boolean[] reachable, int[][] transitions, String alphabet) {
        reachable[state] = true;
        for (int i = 0; i < alphabet.length(); i++) {
            char symbol = alphabet.charAt(i);
            int symbolIndex = alphabet.indexOf(alphabet.charAt(i));
            int nextState = transitions[state][symbolIndex];
            if (!reachable[nextState]) {
                dfs(nextState, reachable, transitions, alphabet);
            }
        }
    }
}

