package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestInput {
    Scanner input = new Scanner(System.in);

    // Test the input of the user
    public int TestInt() {
        while (true) {
            try {
                int choice = input.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number. \n" + e);
                input.nextLine();
            }
        }
    }

    public String TestLine() {
        while (true) {
            try {
                String choice = input.nextLine();
                if (choice.isEmpty()) {
                    System.out.println("Please enter a string.");
                    continue;               
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a string. \n" + e);
                input.nextLine();
            }
        }
    }
}
