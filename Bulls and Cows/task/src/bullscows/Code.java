package bullscows;

import java.util.Random;
import java.util.Scanner;

class Code {
    StringBuilder guess;
    StringBuilder code;
    int len;
    int nSymbols;
    int steps = 1;
    Scanner sc;
    StringBuilder numsAndChars;

    Code(int len, int symbols) {
        this.len = len;
        this.nSymbols = symbols;
        code = createNumber();
        sc = new Scanner(System.in);
    }


    /**
     Method checks whether current code guess is correct or not.
     */
    boolean win() {
        return guess.toString().equals(code.toString());
    }

    /**
     Method reads user's input code and tests it.
     */
    void step() {
        System.out.printf("Turn %d:\n", steps);
        steps++;
        guess = new StringBuilder(sc.nextLine());
        grader();
    }


    /**
     Method counts amount of bulls and cows for generated code
     and uset's guess code.
     */
    void grader() {

        int bulls = 0;
        int cows = 0;

        char[] guessString = String.valueOf(guess).toCharArray();
        char[] codeString = String.valueOf(code).toCharArray();

        if (guessString.length != codeString.length || guessString.length == 0) {
            System.out.println("Error");
            System.exit(1);
        }

        for (char ch: guessString) {
            if (numsAndChars.indexOf(String.valueOf(ch)) == -1) {
                System.out.println("Error");
                System.exit(1);
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (guessString[i] == codeString[j] && i == j) {
                    bulls++;
                } else if (guessString[i] == codeString[j] && i != j) {
                    cows++;
                }
            }
        }
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        } else if (bulls != 0 && cows == 0) {
            System.out.printf("Grade: %d bull(s).\n", bulls);
        } else if (bulls == 0) {
            System.out.printf("Grade: %d cows(s).\n", cows);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cows(s).\n", bulls, cows);
        }
    }


    /**
     Method creates random number with "len" symbols and
     "nSymbols" different symbols from 0 to 9 and then from a to z.
     */
    StringBuilder createNumber() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        int intBound;
        if (nSymbols >= 10) {
            intBound = 9;
        } else intBound = nSymbols - 1;

        numsAndChars = new StringBuilder();
        StringBuilder numArray = new StringBuilder();
        for (int i = 0; i < intBound + 1; i++) {
            numArray.append(i);
            numsAndChars.append(i);
        }
        for (char ch = 'a'; ch < 'a' + nSymbols - 10; ch++) {
            numArray.append(ch);
            numsAndChars.append(ch);
        }

        int step = 0;
        do {
            int nextIdx = random.nextInt(nSymbols - step);
            char ch = numArray.charAt(nextIdx);

            numArray.deleteCharAt(nextIdx);
            builder.append(ch);
            step++;

        } while (builder.length() < len);

        System.out.print("The secret is prepared: ");
        System.out.print("*".repeat(len));
        int lastDigit = nSymbols < 10 ? nSymbols - 1 : 9;
        System.out.printf(" (0-%d", lastDigit);
        if (nSymbols > 10) {
            char lastChar = (char) ('a' + nSymbols - 10 - 1);
            System.out.printf(", a-%s)\n.", lastChar);
        } else System.out.println(").");
        return builder;
    }
}
