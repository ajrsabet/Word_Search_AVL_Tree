package data;
import java.util.Stack;

public class WordsAVL {
    private Word root;

    public WordsAVL() {
        this.root = null;
    }

    // Method to insert a word into the AVL tree
    public void insert(String word) {
        root = insertRec(root, word);
    }

    private Word insertRec(Word node, String word) {
        if (node == null) {
            return new Word(word);
        }

        if (word.compareTo(node.word) < 0) {
            node.left = insertRec(node.left, word);
        } else if (word.compareTo(node.word) > 0) {
            node.right = insertRec(node.right, word);
        } else {
            node.count++;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balance(node);
    }

    // Method to balance the AVL tree
    private Word balance(Word node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1) {
            if (getBalance(node.left) >= 0) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (getBalance(node.right) <= 0) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private Word rightRotate(Word y) {
        Word x = y.left;
        Word T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Word leftRotate(Word x) {
        Word y = x.right;
        Word T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int height(Word node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Word node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Method to search for a word in the AVL tree
    public boolean search(String word) {
        return searchRec(root, word);
    }

    private boolean searchRec(Word node, String word) {
        if (node == null) {
            return false;
        }

        if (word.equals(node.word)) {
            return true;
        }

        if (word.compareTo(node.word) < 0) {
            return searchRec(node.left, word);
        } else {
            return searchRec(node.right, word);
        }
    }

    // Method to get the count of a word in the AVL tree
    public int getCount(String word) {
        return getCountRec(root, word);
    }

    private int getCountRec(Word node, String word) {
        if (node == null) {
            return 0;
        }

        if (word.equals(node.word)) {
            return node.count;
        }

        if (word.compareTo(node.word) < 0) {
            return getCountRec(node.left, word);
        } else {
            return getCountRec(node.right, word);
        }
    }

    // get Word node 
    public Word getWord(String word) {
        return getWordRec(root, word);
    }

    private Word getWordRec(Word node, String word) {
        if (node == null) {
            return null;
        }

        if (word.equals(node.word)) {
            return node;
        }

        if (word.compareTo(node.word) < 0) {
            return getWordRec(node.left, word);
        } else {
            return getWordRec(node.right, word);
        }
    }

    // Method to print the AVL tree in order
    public void printTree() {
        inOrderRec(root);
    }

    private int wordCount = 0;
    private void inOrderRec(Word node) {
        if (node == null) {
            return;
        }

        Stack<Word> stack = new Stack<>();
        Word current = node;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(String.format("%-15s", current.word + " " + current.count));
            wordCount++;
            if (wordCount % 10 == 0) {
                System.out.println();
            }

            current = current.right;
        }
    }

    // Method to return the number of words in the AVL tree
    public int countWords() {
        return countWordsRec(root);
    }

    private int countWordsRec(Word node) {
        if (node == null) {
            return 0;
        }

        Stack<Word> stack = new Stack<>();
        stack.push(node);
        int count = 0;

        while (!stack.isEmpty()) {
            Word current = stack.pop();
            count++;

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return count;
    }

    // get the height of the tree
    public int getHeight() {
        return height(root);
    }

    // get the width of the tree
    public int getWidth() {
        return getWidthRec(root);
    }

    private int getWidthRec(Word node) {
        if (node == null) {
            return 0;
        }

        Stack<Word> stack = new Stack<>();
        stack.push(node);
        int maxWidth = 0;

        while (!stack.isEmpty()) {
            int count = stack.size();
            maxWidth = Math.max(maxWidth, count);

            while (count-- > 0) {
                Word current = stack.pop();

                if (current.left != null) {
                    stack.push(current.left);
                }
                if (current.right != null) {
                    stack.push(current.right);
                }
            }
        }

        return maxWidth;
    }

    // balance the tree
    public void balance() {
        // get all words in the tree
        String[] words = new String[countWords()];
        getWords(root, words, 0);
        // sort the words
        java.util.Arrays.sort(words);
        // create a new balanced tree
        root = null;
        for (String word : words) {
            insert(word);
        }
    }

    private int getWords(Word node, String[] words, int index) {
        if (node == null) {
            return index;
        }

        Stack<Word> stack = new Stack<>();
        Word current = node;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            words[index++] = current.word;
            current = current.right;
        }

        return index;
    }

    // Word class representing each node in the AVL tree
    private class Word {
        String word;
        int count;
        int height;
        Word left, right;

        Word(String word) {
            this.word = word;
            this.count = 1;
            this.height = 1;
            this.left = this.right = null;
        }
    }
}