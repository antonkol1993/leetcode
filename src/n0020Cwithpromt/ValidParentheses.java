package n0020Cwithpromt;

import java.util.Stack;

/**
 * 7. <a href="https://https://leetcode.com/problems/valid-parentheses">Valid Parentheses</a>
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        for (; ; ) {
            if (s.contains("()")) {
                s = s.replace("()", "");

            } else if (s.contains("{}")) {
                s = s.replace("{}", "");

            } else if (s.contains("[]")) {
                s = s.replace("[]", "");

            } else {
                return s.isEmpty();
            }

        }

    }


    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(', '[', '{':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != ')') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '}') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }

}
