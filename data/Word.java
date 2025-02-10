package data;

public class Word {
    String word;
    Word left;
    Word right;
    int count;

    public Word(String word) {
        this.word = word;
        this.left = null;
        this.right = null;
        this.count = 1;
    }

    public String getWord() {
        return word;
    }

    // get count
    public int getCount() {
        return count;
    }
}