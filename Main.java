import java.util;
import java.io;

public class Main {



public static void main(String[] args) throws IOException {
    // ファイルから入力を読み込む
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = "";
    String line = "";
    while ((line = br.readLine()) != null) {
        input += line + "\n";
    }

    // 入力をパースする
    String[] data = input.split("\n");
    int num_state = Integer.parseInt(data[0].split(" ")[0]);
    String[][] delta = new String[num_state][num_state];
    for (int i = 0; i < num_state; i++) {
        delta[i] = data[2+i].split(" ");
    }
    char[] sigma = data[1].toCharArray();
    String current_state = data[data.length - 3];
    String[] final_states = data[data.length - 2].split(" ");
    List<String> not_accept_states = new ArrayList<String>();
    for (int i = 1; i <= num_state; i++) {
        if (Arrays.asList(final_states).contains(String.valueOf(i))) {
            continue;
        } else {
            not_accept_states.add(String.valueOf(i));
        }
    }

    // 現在の状態から到達可能な状態を取得する
    List<String> reachable_states = getReachableStates(num_state, delta, current_state);

    // 入力文字列がDFAに受理されるかどうかを判定する
    String word = "111";
    for (int i = 1; i <= num_state; i++) {
        String state = String.valueOf(i);
        if (!reachable_states.contains(state)) {
            continue;
        }
        for (char symbol : word.toCharArray()) {
            int j = String.valueOf(sigma).indexOf(symbol);
            state = delta[Integer.parseInt(state) - 1][j];
        }
        List<String> reachable_states_from_current_state = getReachableStates(num_state, delta, state);
        if (Arrays.asList(final_states).containsAll(reachable_states_from_current_state)) {
            System.out.println("Yes");
            System.exit(0);
        }
    }
    System.out.println("No");
}

// 現在の状態から到達可能な状態を取得する
public static List<String> getReachableStates(int num_state, String[][] delta, String current_state) {
    List<String> marked_states = new ArrayList<String>();
    marked_states.add(current_state);
    int index = 0;
    while (true) {
        for (String state : delta[Integer.parseInt(current_state) - 1]) {
            if (!marked_states.contains(state)) {
                marked_states.add(state);
            }
        }
        if (index + 1 < marked_states.size()) {
            index++;
            current_state = marked_states.get(index);
        } else {
            break;
        }
    }
    return marked_states;
}

}