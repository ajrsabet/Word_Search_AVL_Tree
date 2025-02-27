package data;

public class Word {
    String key;
    int height;
    int count;
    Word left;
    Word right;
    
    public Word(String key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1; // new node is initially added at leaf
        this.count = 1; // new node is initially added with frequency 1
    }
}