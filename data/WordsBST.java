package data;

public class WordsBST {
    private Word root;

    public WordsBST() {
        this.root = null;
    }

    // Method to insert a word into the BST
    public void insert(String word) {
        root = insertRec(root, word);
    }

    private Word insertRec(Word root, String word) {
        // remove punctuation
        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        if (root == null) {
            root = new Word(word);
            return root;
        }

        if (word.compareTo(root.word) < 0) {
            root.left = insertRec(root.left, word);
        } else if (word.compareTo(root.word) > 0) {
            root.right = insertRec(root.right, word);
        } else {
            // If the word already exists, increment the count
            root.count++;
        }

        return root;
    }

    // Method to search for a word in the BST
    public boolean search(String word) {
        return searchRec(root, word);
    }

    private boolean searchRec(Word root, String word) {
        if (root == null) {
            return false;
        }

        if (word.equals(root.word)) {
            return true;
        }

        if (word.compareTo(root.word) < 0) {
            return searchRec(root.left, word);
        } else {
            return searchRec(root.right, word);
        }
    }

    // Method to get the count of a word in the BST
    public int getCount(String word) {
        return getCountRec(root, word);
    }

    private int getCountRec(Word root, String word) {
        if (root == null) {
            return 0;
        }

        if (word.equals(root.word)) {
            return root.count;
        }

        if (word.compareTo(root.word) < 0) {
            return getCountRec(root.left, word);
        } else {
            return getCountRec(root.right, word);
        }
    }

    // get Word node 
    public Word getWord(String word) {
        return getWordRec(root, word);
    }

    private Word getWordRec(Word root, String word) {
        if (root == null) {
            return null;
        }

        if (word.equals(root.word)) {
            return root;
        }

        if (word.compareTo(root.word) < 0) {
            return getWordRec(root.left, word);
        } else {
            return getWordRec(root.right, word);
        }
    }

    // Method to print the BST in order
    public void printTree() {
        inOrderRec(root);
    }

    private void inOrderRec(Word root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.word + " " + root.count);
            inOrderRec(root.right);
        }
    }
}