import java.util.Stack;

/**
 * evaluate-reverse-polish-notation
 *
 * Evaluate the value of an arithmetic
 * expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may
 * be an integer or another expression.
 *
 * 逆波兰记法，求出逆波兰表示法的值，不清楚逆波兰表示法的请百度
 */

/**
 * 逻辑概述：
 * 通过栈来存储运算数字，遇到运算符就抛两个值出来运算。
 */
public class No2 {
    public int evalRPN(String[] tokens) {
        int result = 0;
        if (tokens == null || tokens.length == 0) return result;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            char a = tokens[i].charAt(0);
            if (tokens[i].length() == 1 && (tokens[i].charAt(0) - '*') < 6) {
                int temp2 = stack.pop();
                int temp1 = stack.pop();
                int count = evaluate(temp1,temp2,tokens[i].toCharArray()[0]);
                stack.push(count);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 传递运算符进行运算
     */
    private int evaluate(int temp1, int temp2, char operator) {
        switch (operator) {
            case '+':
                return temp1 + temp2;
            case '-':
                return temp1 - temp2;
            case '*':
                return temp1 * temp2;
            case '/':
                return temp1 / temp2;
            default:
                return 0;
        }
    }
}
