package util;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

// import files.*;
import data.WordsBST;

public class ProcessFile {
    
    // private File file = new File("filename.txt");
    
    public ProcessFile() {
    }
    
    public void SortWords(WordsBST words, String filePath) {
      try {
          File myObj = new File(filePath);
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
        
    public void ReadText(WordsBST words, String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
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
