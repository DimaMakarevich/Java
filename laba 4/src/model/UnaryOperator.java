package model;


public class UnaryOperator implements Operator, Sourcable {
    private Operand operand;
    private Token token;


    public UnaryOperator(String source) {
        token = new Token(source);
    }

    @Override
    public Operand result() {
        switch (token.source()) {
            case OperatorFactory.SQRT: {
                return new Operand(Math.sqrt(operand.value()));
            }
        }

        return null;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String getSource() {
        return token.source();
    }
}
