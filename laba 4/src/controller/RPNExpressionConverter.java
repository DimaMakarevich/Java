package controller;

import model.*;
import view.OperButtonPanel;

import java.util.*;

public class RPNExpressionConverter {
    public static final String DOT = ".";
    private static final String EXPRESSION_BORDER = "$";

    private String source;
    private Expression rpn;

    private Deque<String> operatorStack;


    public RPNExpressionConverter(String source) {
        this.source = source;
        rpn = new Expression();

        operatorStack = new ArrayDeque<>();
    }

    public Expression convert() throws Exception {
        operatorStack.push(EXPRESSION_BORDER);
        source = source.concat(EXPRESSION_BORDER);

        String currentOperand = "";
        String currentOperationSymbol;


        for (int expIndex = 0; expIndex < source.length(); expIndex++) {
            if (isOperand(source.substring(expIndex, expIndex + 1))) {
                currentOperand = currentOperand.concat(source.substring(expIndex, expIndex + 1));
            } else {
                if (!currentOperand.equals("")) {
                    rpn.addToken(new Operand(currentOperand).token());
                    currentOperand = "";
                }

                currentOperationSymbol = source.substring(expIndex, expIndex + 1);

                // if open bracket found
                if (source.substring(expIndex, expIndex + OperButtonPanel.OPEN.length())
                        .equals(OperButtonPanel.OPEN)) {

                    expIndex = addOpenBracket(expIndex);

                    continue;
                }

                // if minus or plus found
                if (currentOperationSymbol.equals(OperatorFactory.MINUS)
                        || currentOperationSymbol.equals(OperatorFactory.PLUS)) {

                    expIndex = addPlusMinusOperator(expIndex);

                    continue;
                }

                // if divide, multiplicate or mod found
                if (currentOperationSymbol
                        .equals(OperatorFactory.DIVIDE)
                        || currentOperationSymbol
                        .equals(OperatorFactory.MULTIPLICATION)
                        || currentOperationSymbol
                        .equals(OperatorFactory.MOD)) {

                    addMultDivModOperator(expIndex);

                    continue;
                }

                // if close bracket found
                if (source.substring(expIndex, expIndex + OperButtonPanel.CLOSE.length())
                        .equals(OperButtonPanel.CLOSE)) {

                    addCloseBracket();

                    continue;
                }

                // if expression border found
                if (source.substring(expIndex, expIndex + EXPRESSION_BORDER.length())
                        .equals(EXPRESSION_BORDER)) {

                    endParse();

                    continue;
                }

                // if sqrt() found
                if (source.substring(expIndex, expIndex + OperatorFactory.SQRT.length())
                        .equals(OperatorFactory.SQRT)) {

                    operatorStack.push(OperatorFactory.SQRT);
                    expIndex += OperatorFactory.SQRT.length() - 1;
                    continue;
                }

                // if ^ found

                if(currentOperationSymbol.equals(OperatorFactory.POWER)){
                    addPowOperator(expIndex);
                    continue;
                }

            }
        }

        return rpn;
    }

    private boolean isOperand(String operand) {
        for(char symbol : operand.toCharArray()) {
            if(!Character.isDigit(symbol) && (symbol != DOT.charAt(0))) {
                return false;
            }
        }

        return true;
    }

    private void addOperatorToRPN(String source) throws Exception {
        Operator operator = OperatorFactory.getOperator(source);

        if (operator instanceof BinaryOperator) {
            rpn.addToken(((BinaryOperator) operator).getToken());
        } else {
            rpn.addToken(((UnaryOperator) operator).getToken());
        }
    }

    private void addCloseBracket() throws Exception {
        while (true) {
            if (operatorStack.peek().equals(EXPRESSION_BORDER)) {
                throw new Exception("No matches to closing bracket");
            } else if (operatorStack.peek().equals(OperButtonPanel.OPEN)) {
                operatorStack.pop();

                break;
            }

            if (!operatorStack.isEmpty()) {
                try {
                    addOperatorToRPN(operatorStack.pop());
                } catch (Exception ex) {
                    throw new Exception(ex.getMessage());
                }
            }
        }
    }

    private int addOpenBracket(int beginIndex) {
        operatorStack.push(OperButtonPanel.OPEN);
        return beginIndex + OperButtonPanel.OPEN.length() - 1;
    }

    private int addPlusMinusOperator(int beginIndex) throws Exception {
        if (beginIndex == 0 || source.substring(beginIndex - 1, beginIndex).equals(OperButtonPanel.OPEN)) {
            operatorStack.push(source.substring(beginIndex, beginIndex + 1));
        } else {
            while (true) {
                if (operatorStack.peek().equals(EXPRESSION_BORDER)
                        || operatorStack.peek().equals(OperButtonPanel.OPEN)) {

                    operatorStack.push(source.substring(beginIndex, beginIndex + 1));

                    break;
                } else {
                    if (!operatorStack.isEmpty()) {
                        try {
                            addOperatorToRPN(operatorStack.pop());
                        } catch (Exception ex) {
                            throw new Exception(ex.getMessage());
                        }
                    }
                }
            };
        }

        return beginIndex;
    }

    private void endParse() throws Exception {
        while (true) {
            if (operatorStack.peek().equals(EXPRESSION_BORDER)) {
                break;
            } else if (operatorStack.peek().equals(OperButtonPanel.OPEN)) {
                throw new Exception("Open bracket in the end of the expression");
            }

            if (!operatorStack.isEmpty()) {
                try {
                    addOperatorToRPN(operatorStack.pop());
                } catch (Exception ex) {
                    throw new Exception(ex.getMessage());
                }
            }
        }
    }

    private void addMultDivModOperator(int beginIndex) throws Exception {
        while (true) {
            if (operatorStack.peek().equals(OperatorFactory.MULTIPLICATION) ||
                    operatorStack.peek().equals(OperatorFactory.DIVIDE) ||
                    operatorStack.peek().equals(OperatorFactory.SQRT) ||
                    operatorStack.peek().equals(OperatorFactory.MOD)) {

                if (!operatorStack.isEmpty()) {
                    try {
                        addOperatorToRPN(operatorStack.pop());
                    } catch (Exception ex) {
                        throw new Exception(ex.getMessage());
                    }
                }
            } else {
                operatorStack.push(source.substring(beginIndex, beginIndex + 1));
                break;
            }
        }
    }

    private void addPowOperator(int beginIndex) throws Exception {
        while (true) {
            if (operatorStack.peek().equals(OperatorFactory.POWER)) {
                if (!operatorStack.isEmpty()) {
                    try {
                        addOperatorToRPN(operatorStack.pop());
                    } catch (Exception ex) {
                        throw new Exception(ex.getMessage());
                    }
                }
            } else {
                operatorStack.push(source.substring(beginIndex, beginIndex + 1));
                break;
            }
        }
    }



}
