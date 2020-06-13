package model;


public class Operand implements Sourcable {
    private double value;
    private Token token;

    public Operand(String source) throws NumberFormatException {
        token = new Token(source);

        value = Double.parseDouble(source);
    }

    public Operand(double value) {
        this.value = value;
        token = new Token(String.valueOf(this.value));
    }

    public double value() {
        return value;
    }

    public Token token() {
        return token;
    }

    @Override
    public String getSource() {
        return token.source();
    }
}
