package main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;

import util.ProcessFile;
import util.TypeWriter;
import data.WordsBST;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProcessFile file = new ProcessFile();
        WordsBST words = new WordsBST();
        TypeWriter print = new TypeWriter();
        boolean exit = false;
        File[] listOfFiles = file.GetFileNames();

        while (!exit) {
            print.SlowType("Welcome to the Text Scanner!");
            print.SlowType("Would you like to use a built in dictionary or scan in your own?");
            print.SlowType("1. Read text from a file");
            print.SlowType("2. Scan in your own");
            print.SlowType("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: // Read text from a file
                    print.SlowType("Choose a file to read:");
                    file.ListFiles(listOfFiles);

                    int fileNum = scanner.nextInt();
                    file.ReadText(words, "./files/" + listOfFiles[fileNum - 1].getName());
                    break;
                case 2: // Process text into BST
                    print.SlowType("Add text to word tree");
                    print.SlowType("What text would you like to add?");
                    file.ListFiles(listOfFiles);
                    fileNum = scanner.nextInt();
                    file.ReadText(words, "./files/" + listOfFiles[fileNum - 1].getName());
                    
                    break;
                case 3: // Display words and use frequency
                    print.SlowType("Display words and use frequency");
                    break;
                case 4: // Exit
                    print.SlowType("Exiting...");
                    exit = true;
                    break;
                default:
                    print.SlowType("Invalid choice. Please try again.");
            }
        }

        
        scanner.close();
    }
}