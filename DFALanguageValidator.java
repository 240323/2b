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
            acceptStates[i] = dfaScanner.nextInt();
        }
        dfaScanner.close();



// 与えられたDFAが少なくとも1つの文字列を受理するかどうかを判定
    boolean[] reachable = new boolean[numStates];
    dfs(startState, reachable, transitions);
    for (int i = 0; i < numFinalStates; i++) {
        if (reachable[acceptStates[i]]) {
            System.out.println("Yes");
            System.exit(0);
        }
    }
    System.out.println("No");
}

private static void dfs(int state, boolean[] reachable, int[][] transitions) {
    if (state < 0 || state >= transitions.length || reachable[state]) {
        return;
    }
    reachable[state] = true;
    for (int i = 0; i < transitions[state].length; i++) {
        int nextState = transitions[state][i];
        dfs(nextState, reachable, transitions);
    }
 }
}

