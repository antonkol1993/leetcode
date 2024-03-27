package n0020Cwithpromt;

/**
 * 7. <a href="https://https://leetcode.com/problems/valid-parentheses">Valid Parentheses</a>
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        do {
            if (s.contains("()")) {
                s = s.replace("()", "");

            } else if (s.contains("{}")) {
                s = s.replace("{}", "");

            } else if (s.contains("[]")) {
                s = s.replace("[]", "");

            } else {
                return s.isEmpty();
            }

        } while (true);

    }


}
