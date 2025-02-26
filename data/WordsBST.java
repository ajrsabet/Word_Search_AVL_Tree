package data;
import java.util.Stack;

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
        // remove punctuation but keep apostrophe for contractions and possessives
        // word = word.replaceAll("[^a-zA-Z'-]", "").toLowerCase();

        
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

    private int wordCount = 0;
    // private void inOrderRec(Word root) {
    //     if (root != null) {
    //         inOrderRec(root.left);
    //         System.out.print(String.format("%-15s", root.word + " " + root.count));
    //         wordCount++;
    //         if (wordCount % 10 == 0) {
    //             System.out.println();
    //         }
    //         inOrderRec(root.right);
    //     }
    // }
    private void inOrderRec(Word root) {
        if (root == null) {
            return;
        }
    
        Stack<Word> stack = new Stack<>();
        Word current = root;
    
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

    // Method to return the number of words in the BST
    public int countWords() {
        return countWordsRec(root);
    }

    // private int countWordsRec(Word root) {
    //     if (root == null) {
    //         return 0;
    //     }

    //     return countWordsRec(root.left) + countWordsRec(root.right) + 1;
    // }
    private int countWordsRec(Word root) {
        if (root == null) {
            return 0;
        }
    
        Stack<Word> stack = new Stack<>();
        stack.push(root);
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
        return getHeightRec(root);
    }

    // private int getHeightRec(Word root) {
    //     if (root == null) {
    //         return 0;
    //     }

    //     int leftHeight = getHeightRec(root.left);
    //     int rightHeight = getHeightRec(root.right);

    //     return Math.max(leftHeight, rightHeight) + 1;
    // }

    private int getHeightRec(Word root) {
        if (root == null) {
            return 0;
        }
    
        Stack<Word> stack = new Stack<>();
        Stack<Integer> heights = new Stack<>();
        stack.push(root);
        heights.push(1);
        int maxHeight = 0;
    
        while (!stack.isEmpty()) {
            Word current = stack.pop();
            int currentHeight = heights.pop();
            maxHeight = Math.max(maxHeight, currentHeight);
    
            if (current.left != null) {
                stack.push(current.left);
                heights.push(currentHeight + 1);
            }
            if (current.right != null) {
                stack.push(current.right);
                heights.push(currentHeight + 1);
            }
        }
    
        return maxHeight;
    }

    // get the width of the tree
    public int getWidth() {
        return getWidthRec(root);
    }

    // private int getWidthRec(Word root) {
    //     if (root == null) {
    //         return 0;
    //     }

    //     int leftWidth = getWidthRec(root.left);
    //     int rightWidth = getWidthRec(root.right);

    //     return Math.max(leftWidth, rightWidth) + 1;
    // }

    private int getWidthRec(Word root) {
        if (root == null) {
            return 0;
        }
    
        Stack<Word> stack = new Stack<>();
        stack.push(root);
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

    // private int getWords(Word root, String[] words, int index) {
    //     if (root != null) {
    //         index = getWords(root.left, words, index);
    //         words[index++] = root.word;
    //         index = getWords(root.right, words, index);
    //     }
    //     return index;
    // }

    private int getWords(Word root, String[] words, int index) {
        if (root == null) {
            return index;
        }
    
        Stack<Word> stack = new Stack<>();
        Word current = root;
    
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
}