package test.spring.aop;

import test.spring.aop.expr.Expression;
import test.spring.aop.expr.Number;
import test.spring.aop.expr.Plus;

/**
 * Created by alombard on 13/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        final Expression expr = new Plus(new Plus(new Number(5), new Number(7)), new Number(3));    // (5 + 7) + 3

        long tic = System.nanoTime();
        expr.eval();
        System.out.println(System.nanoTime() - tic);

        tic = System.nanoTime();
        expr.eval();
        System.out.println(System.nanoTime() - tic);
    }
}
