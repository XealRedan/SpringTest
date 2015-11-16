package test.spring.aop.expr;

public class Number extends Expression {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
