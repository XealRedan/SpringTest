package test.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by alombard on 13/11/2015.
 */
@Aspect
public class LoggingAspect {
    @AfterReturning(value = "execution(* test.spring.aop.expr.Expression.eval())", returning = "returnVal")
    public void logAfter(JoinPoint joinPoint, int returnVal) {
        System.out.println(joinPoint.getThis() + " evaluated to " + returnVal);
    }
}
