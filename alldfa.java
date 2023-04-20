//(3)を解くプログラム
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class alldfa {
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

        // DFAがすべての文字列を受理するかどうかを判断する
        boolean acceptsAllStrings = true;
        for (int i = 0; i < numStates; i++) {
            for (int j = 0; j < numSymbols; j++) {
                int nextState = transitions[i][j];
                if (!acceptsAllStrings && isAcceptState(nextState, acceptStates)) {
                    acceptsAllStrings = true;
                }
            }
        }

        // 結果を出力する
        if (acceptsAllStrings) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // 現在の状態が受理状態であるかどうかを判断する
    private static boolean isAcceptState(int state, int[] acceptStates) {
        for (int i = 0; i < acceptStates.length; i++) {
            if (state == acceptStates[i]) {
                return true;
            }
        }
        return false;
    }
}
