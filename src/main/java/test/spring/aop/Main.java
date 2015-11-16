package test.spring.aop;

import test.spring.aop.expr.Expression;
import test.spring.aop.expr.Number;
import test.spring.aop.expr.Plus;

public class Main {
    public static void main(String[] args) {
        final Expression expr = new Plus(new Plus(new Number(5), new Number(7)), new Number(3));    // (5 + 7) + 3

        long time = 0;

        for(int idx = 0; idx < 100; idx++) {
            long tic = System.nanoTime();
            expr.eval();
            time += ((System.nanoTime() - tic) / 100);
        }

        System.out.println("Avg time: " + time);
    }
}
