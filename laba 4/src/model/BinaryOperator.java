package model;


public class BinaryOperator implements Operator, Sourcable {
    private Operand leftOperand;
    private Operand rightOperand;
    private Token token;


    public BinaryOperator(String source) {
        token = new Token(source);
    }

    @Override
    public Operand result() {
        switch (token.source()) {
            case OperatorFactory.PLUS: {
                return new Operand(rightOperand.value() + leftOperand.value());
            }
            case OperatorFactory.MINUS: {
                return new Operand(rightOperand.value() - leftOperand.value());
            }
            case OperatorFactory.DIVIDE: {
                return new Operand(rightOperand.value() / leftOperand.value());
            }
            case OperatorFactory.MULTIPLICATION: {
                return new Operand(rightOperand.value() * leftOperand.value());
            }
            case OperatorFactory.MOD: {
                return new Operand(rightOperand.value() % leftOperand.value());
            }
            case OperatorFactory.POWER: {
                return new Operand(Math.pow(rightOperand.value(),leftOperand.value()));
            }

        }

        return new Operand(0);
    }

    public void setLeftOperand(Operand operand) {
        leftOperand = operand;
    }

    public void setRightOperand(Operand operand) {
        rightOperand = operand;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String getSource() {
        return token.source();
    }
}
