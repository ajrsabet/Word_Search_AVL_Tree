package main;

import java.util.Scanner;
import java.io.File;
// import java.io.FileNotFoundException;
// import java.lang.reflect.Type;

import util.*;
import data.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProcessFile file = new ProcessFile();
        WordsBST words = new WordsBST();
        TestInput input = new TestInput();
        TypeWriter print = new TypeWriter();
        boolean exit = false;
        File[] listOfFiles = file.GetFileNames();
        long searchTime = System.nanoTime();

        print.SlowType("\nWelcome to the Text Scanner!");
        print.SlowType("\nThis tool lets you read text from a file, create a word tree");
        print.SlowType("from the text, search for a word, and print the tree.");
        
        while (!exit) {
            print.SlowType("Please choose an option from the menu below.\n");
            print.SlowType("1. Read text from a file");
            print.SlowType("2. Create a word tree from text");
            print.SlowType("3. Search for a word and use frequency");
            print.SlowType("4. Print the tree");
            print.SlowType("5. Exit\n");

            int choice = input.TestInt();
            

            switch (choice) {
                case 1: // Read text from a file
                    print.SlowType("Choose a file to read:");
                    file.ListFiles(listOfFiles);

                    int fileNum = input.TestInt();
                    try {
                        file.ReadText(words, "./files/" + listOfFiles[fileNum - 1].getName());
                    } catch (Exception e) {
                        print.SlowType("An error occurred, try again \n" + e);
                    }
                    System.out.println();
                    break;

                case 2: // Process text into BST
                    print.SlowType("Add text to word tree");
                    print.SlowType("What text would you like to add?");
                    
                    // list files in the files folder
                    file.ListFiles(listOfFiles);
                    fileNum = input.TestInt();
                    System.out.println();
                    
                    try {
                        searchTime = System.nanoTime(); // measure the time required to sort the words
                        // sort the words
                        boolean success = file.SortWords(words, "./files/" + listOfFiles[fileNum - 1].getName());
                        searchTime = System.nanoTime() - searchTime;
                        double searchTimeInSeconds = searchTime / 1_000_000_000.0;

                        if (success) {
                            print.SlowType( listOfFiles[fileNum - 1] + " was added to the word tree.");
                            print.SlowType("Number of words: " + words.countWords() + "");
                            print.SlowType("Duration: " + searchTimeInSeconds + " seconds (" + searchTime + " nanoseconds)\n");
                        } else {
                            print.SlowType("An error occurred, try again\n");
                        }
                    } catch (Exception e) {
                        print.SlowType("An error occurred, try again\n" + e);
                    }
                    System.out.println();
                    break;

                case 3: // Search for word and use frequency
                    // get single word
                    print.SlowType("Enter a word to search for:\n");
                    String searchWord = input.TestLine(); // consume newline
                    
                    // measure the time required to search for the word
                    searchTime = System.nanoTime(); 
                    // search for word
                    Word thisWord = words.getWord(searchWord);
                    searchTime = System.nanoTime() - searchTime;
                     
                    if (thisWord != null) {
                        print.SlowType("\nWord: " + searchWord);
                        print.SlowType("Count: " + words.getCount(searchWord));
                        double searchTimeInSeconds = searchTime / 1_000_000_000.0;
                        print.SlowType("Time to search: " + searchTimeInSeconds + " seconds (" + searchTime + " nanoseconds)");
                    } else {
                        print.SlowType("The word " + searchWord + " was not found.");
                    }
                    System.out.println();
                    break;

                    case 4: // display the tree
                    print.SlowType("Printing the tree...");
                    words.printTree();
                    System.out.println();
                    
                    break;

                    case 5: // Exit
                    print.SlowType("Exiting...");
                    exit = true;
                    break;

                    default:
                    print.SlowType("Invalid choice. Please try again.");
                    System.out.println();
            }
        }

        
        scanner.close();
    }
}