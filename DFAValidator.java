import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFAValidator {
    public static void main(String[] args) throws FileNotFoundException {
        // テキストファイルからDFAを読み込む
        Scanner dfaScanner = new Scanner(new File("/Users/beeks/OneDrive/ドキュメント/講義/計算理論/2b/dfa.txt"));
        int numStates = dfaScanner.nextInt();
        int numSymbols = dfaScanner.nextInt();
        int startState = dfaScanner.nextInt();
        dfaScanner.nextLine();
        String alphabet = dfaScanner.nextLine();
        int[][] transitions = new int[numStates][numSymbols];
        for (int i = 0; i < numStates; i++) {
            String[] line = dfaScanner.nextLine().split(" ");
            for (int j = 0; j < numSymbols; j++) {
                transitions[i][j] = Integer.parseInt(line[j]);
            }
        }
        int[] acceptStates = new int[dfaScanner.nextInt()];
        for (int i = 0; i < acceptStates.length; i++) {
            acceptStates[i] = dfaScanner.nextInt();
        }
        dfaScanner.close();

        // テキストファイルから文字列wを読み込む
        Scanner wScanner = new Scanner(new File("/Users/beeks/OneDrive/ドキュメント/講義/計算理論/2b/w.txt"));
        String w = wScanner.nextLine();
        wScanner.close();

        // DFAが文字列wを受理するかどうかを判断する
        int currentState = startState;
        for (int i = 0; i < w.length(); i++) {
            int symbolIndex = alphabet.indexOf(w.charAt(i));
            if (symbolIndex == -1) {
                System.err.println("Invalid symbol in w: " + w.charAt(i));
                System.exit(1);
            }
            currentState = transitions[currentState][symbolIndex];
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
