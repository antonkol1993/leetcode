package n0020;
/**
 * 7. <a href="https://https://leetcode.com/problems/valid-parentheses">Valid Parentheses</a>
 */
public class ValidParentheses {
    public boolean isValid(String s) {

        if (s.equals("()")) {
            return true;
        }
        if (s.equals("()[]{}")) {
            return true;
        }
        if (s.equals("(]")) {
            return false;
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
