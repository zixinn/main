package seedu.address.model.util;

import java.util.Stack;

/**
 * To store user input strings.
 * Can use this pattern to implement undo/redo.
 */
public class UserInputHistory {
    private static Stack<String> primary = new Stack<>();
    private static Stack<String> secondary = new Stack<>();

    /**
     * Saves user input to the history.
     */
    public static void saveInput(String input) {
        if (primary.empty() || !primary.peek().equals(input)) {
            primary.push(input);
        }
        secondary.clear();
    }

    /**
     * User to scroll up in the history.
     */
    public static String goUp() {
        if (primary.empty()) {
            return "";
        }
        String pTop = primary.pop();
        secondary.push(pTop);
        return pTop;
    }

    /**
     * User to scroll down in the history.
     */
    public static String goDown() {
        if (secondary.empty()) {
            return "";
        }
        String sTop = secondary.pop();
        primary.push(sTop);
        return sTop;
    }
}
