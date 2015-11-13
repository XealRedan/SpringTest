package test.spring.aop.expr;

import com.sun.istack.internal.NotNull;

/**
 * Created by alombard on 13/11/2015.
 */
public class Plus extends Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public Plus(@NotNull Expression leftExpression, @NotNull Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setLeftExpression(@NotNull Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setRightExpression(@NotNull Expression rightExpression) {
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
