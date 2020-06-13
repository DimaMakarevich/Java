package model;

import java.util.ArrayList;
import java.util.List;


public class Expression {
    private List<Token> tokens;


    public Expression() {
        tokens = new ArrayList<>();
    }

    public List<Token> tokens() {
        return tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    @Override
    public String toString() {
        String toString = "";
        for (Token token : tokens) {
            toString = toString.concat(token.toString());
        }

        return toString;
    }
}
