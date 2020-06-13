package model;

public class Token {
    private String source;

    public Token(String source) {

        this.source = source;
    }


    public String source() {
        return source;
    }

    @Override
    public String toString() {
        return source;
    }
}
