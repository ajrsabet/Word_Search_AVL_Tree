package data;

public class WordsAVL {
    private Word root;

    // Get the height of the word
    public int height(Word N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right rotate subtree rooted with y
    private Word rightRotate(Word y) {
        Word x = y.left;
        Word T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }


    // get root 
    public Word getRoot() {
        return root;
    }

    // Left rotate subtree rooted with x
    private Word leftRotate(Word x) {
        Word y = x.right;
        Word T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of word N
    private int getBalance(Word N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a key into the subtree rooted with word
    // and returns the new root of the subtree
    public void insert(String key) {
        root = insertRec(root, key);
    }

    private Word insertRec(Word word, String key) {
        // Perform the normal BST insertion
        if (word == null)
            return (new Word(key));

        if (key.compareTo(word.key) < 0)
            word.left = insertRec(word.left, key);
        else if (key.compareTo(word.key) > 0)
            word.right = insertRec(word.right, key);
        else { // Duplicate keys not allowed
            word.count++;
            return word;
        }

        // Update height of this ancestor word
        word.height = 1 + max(height(word.left), height(word.right));

        // Get the balance factor of this ancestor word
        // to check whether this word became unbalanced
        int balance = getBalance(word);

        // If this word becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key.compareTo(word.left.key) < 0)
            return rightRotate(word);

        // Right Right Case
        if (balance < -1 && key.compareTo(word.right.key) > 0)
            return leftRotate(word);

        // Left Right Case
        if (balance > 1 && key.compareTo(word.left.key) > 0) {
            word.left = leftRotate(word.left);
            return rightRotate(word);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(word.right.key) < 0) {
            word.right = rightRotate(word.right);
            return leftRotate(word);
        }

        // Return the (unchanged) word pointer
        return word;
    }

    // Utility function to print inorder traversal of the tree
    public void inOrder(Word word) {
        if (word != null) {
            inOrder(word.left);
            System.out.print(word.key + " ");
            inOrder(word.right);
        }
    }

    // Method to search for a word in the AVL tree
    public Word search(String key) {
        return searchRec(root, key);
    }

    private Word searchRec(Word word, String key) {
        if (word == null) {
            return null;
        }

        if (key.equals(word.key)) {
            return word;
        }

        if (key.compareTo(word.key) < 0) {
            return searchRec(word.left, key);
        } else {
            return searchRec(word.right, key);
        }
    }

    // Method to get the count of a word in the AVL tree
    public int getCount(Word word, String key) {
        if (word == null) {
            return 0;
        }

        if (key.equals(word.key)) {
            return word.count;
        }

        if (key.compareTo(word.key) < 0) {
            return getCount(word.left, key);
        } else {
            return getCount(word.right, key);
        }
    }

    // Method to count the total number of nodes in the AVL tree
    public int countWords(Word word) {
        int count = 0;
        // count with itteration
        if (word != null) {
            count = 1 + countWords(word.left) + countWords(word.right);
        }
        return count;
    }
}