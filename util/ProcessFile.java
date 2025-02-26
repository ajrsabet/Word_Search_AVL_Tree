package util;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

// import files.*;
import data.WordsAVL;

public class ProcessFile {
    
    // private File file = new File("filename.txt");
    
    public ProcessFile() {
    }
    
    public boolean SortWords(WordsAVL words, String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner scanner = new Scanner(myObj);
            scanner.useDelimiter("[\\s!,.?\"\\[\\]()\\-;:'`~@#$%^&*_+=|<>/\\\\]+");
            while (scanner.hasNext()) {
                String data = scanner.next();
                // Remove non-alphabetic characters from the beginning and end of the word, but keep apostrophes within words
                data = data.replaceAll("^[^a-zA-Z']+|[^a-zA-Z']+$", "").toLowerCase();
                if (!data.isEmpty()) {
                    words.insert(data);
                }
            }
            scanner.close();
            return true;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
        
    public void ReadText(WordsAVL words, String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ListFiles(File[] listOfFiles) {
        System.out.println("Available text files:");
        
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile()) {
                System.out.println(i + 1 + ". " + file.getName());
            }
        }  
    }

    public File[] GetFileNames(){
        File folder = new File("./files");
        System.out.println("Looking for files in: " + folder.getAbsolutePath()); // Debugging line
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        
        if (listOfFiles != null) {
            return listOfFiles;
        } else {
            System.out.println("No text files found.");
        }
        return null;
    }
}
