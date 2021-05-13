package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");

        // length of code to guess
        int len = 0;
        try {
            len = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
        }
        if (len > 36 || len < 1) {
            System.out.println("Error");
            System.exit(1);
        }
        System.out.println("Input the number of possible symbols in the code:");

        // amount of symbols to choose from 0-9 + a-z
        int symbols = 0;
        try {
            symbols = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
        }
        if (len > symbols || symbols > 36) {
            System.out.println("Error");
            System.exit(1);
        }

        Code code = new Code(len, symbols);

        System.out.println("Okay, let's start a game!");
        do {
            code.step();
        } while (!code.win());

        System.out.println("Congratulations! You guessed the secret code.");
    }
}
