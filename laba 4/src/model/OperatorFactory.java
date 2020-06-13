package model;

//Factory method
//AbstractFactory
//State
public class OperatorFactory {
    public static final String SQRT = "√";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String DIVIDE = "/";
    public static final String MULTIPLICATION = "*";
    public static final String MOD = "%";
    public static final String POWER = "^";


    public static Operator getOperator(String operator) throws Exception {
        switch (operator) {
            case SQRT: {
                return new UnaryOperator(SQRT);
            }
            case PLUS: {
                return new BinaryOperator(PLUS);
            }
            case MINUS: {
                return new BinaryOperator(MINUS);
            }
            case DIVIDE: {
                return new BinaryOperator(DIVIDE);
            }
            case MULTIPLICATION: {
                return new BinaryOperator(MULTIPLICATION);
            }
            case MOD: {
                return new BinaryOperator(MOD);
            }
            case POWER: {
                return new BinaryOperator(POWER);
            }
        }

        throw new Exception("The token given is not an operator");
    }
}
