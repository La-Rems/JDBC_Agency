package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleManager {

    private Scanner scan;

    private static ConsoleManager INSTANCE = null;

    private ConsoleManager() {
        scan = new Scanner(System.in);
    }

    public static ConsoleManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConsoleManager();
        }

        return INSTANCE;
    }

    public String readUserInput() {
        return scan.nextLine();
    }

    public Integer readUserInputInteger() {
        Boolean answerRight = false;
        Integer answer = -1;

        do {
            try {
                answer = scan.nextInt();
                answerRight = true;
            } catch (InputMismatchException e) {
                ConsoleManager.getInstance().printToConsole("Merci de réessayer : ", true);
            }

            scan.nextLine();
        } while (!answerRight);

        return answer;
    }

    public Long readUserInputLong() {
        Boolean answerRight = false;
        Long answer = -1L;

        do {
            try {
                answer = scan.nextLong();
                answerRight = true;
            } catch (InputMismatchException e) {
                ConsoleManager.getInstance().printToConsole("Merci de réessayer : ", false);
            }

            scan.nextLine();
        } while (!answerRight);

        return answer;
    }

    public void printToConsole(String text, boolean breakLine) {
        if (breakLine) {
            System.out.println(text);
        } else {
            System.out.print(text);
        }
    }

    public void consoleLineBreak() {
        System.out.println();
    }

    public void closeScanner() {
        scan.close();
    }

    public void printLine() {
        printToConsole("#####################################################", true);
    }

    public void printLineNoLineBreak() {
        printToConsole("#####################################################", false);
    }

    public void printTwoLines() {
        printToConsole("#####################################################", true);
        printToConsole("#####################################################", true);
    }
}

