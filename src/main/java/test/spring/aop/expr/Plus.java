package test.spring.aop.expr;

public class Plus extends Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public Plus(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }

    @Override
    public int eval() {
        return leftExpression.eval() + rightExpression.eval();
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " + " + rightExpression.toString() + ")";
    }
}
