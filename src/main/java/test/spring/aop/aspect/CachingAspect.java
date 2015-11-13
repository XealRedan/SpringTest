package test.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import test.spring.aop.expr.Expression;
import test.spring.aop.expr.Plus;

/**
 * Created by alombard on 13/11/2015.
 */
@Aspect
public class CachingAspect {
    public interface Cachable {
        int getCache();
        void setCache(int cache);
        boolean isCacheValid();
        void validateCache();
        void invalidateCache();
        Expression getAncestor();
        void setAncestor(Expression expr);
    }

    public static class CachableImpl implements Cachable {
        private int cache;
        private boolean cacheValid;
        private Expression ancestor;

        public int getCache() {
            return this.cache;
        }

        public void setCache(int cache) {
            this.cache = cache;
        }

        public Expression getAncestor() {
            return this.ancestor;
        }

        public void setAncestor(Expression ancestor) {
            this.ancestor = ancestor;
        }

        public boolean isCacheValid() {
            return this.cacheValid;
        }

        public void validateCache() {
            this.cacheValid = true;
        }

        public void invalidateCache() {
            this.cacheValid = false;
            if(this.ancestor != null)
                ((Cachable)this.ancestor).invalidateCache();
        }
    }

    @DeclareMixin("test.spring.aop.expr.Expression")
    public static Cachable createCachableImplementation() {
        return new CachableImpl();
    }

    @Pointcut("target(test.spring.aop.expr.Expression) && args(expr) && (" +
            "execution(* test.spring.aop.expr.Number.setValue(int)) ||" +
            "execution(* test.spring.aop.expr.Plus.setLeftExpression(test.spring.aop.expr.Expression)) ||" +
            "execution(* test.spring.aop.expr.Plus.setRightExpression(test.spring.aop.expr.Expression)))")
    void changeValue(Expression expr) {}

    @After("changeValue(expr)")
    public void afterChangeValue(Expression expr) {
        ((Cachable)expr).invalidateCache();
    }

    @Pointcut(value = "target(test.spring.aop.expr.Expression) && args(expr) && " +
            "execution(* test.spring.aop.expr.Expression.eval())")
    public void evaluation(Expression expr) {}

    @Around("evaluation(expr)")
    public int aroundEvaluation(ProceedingJoinPoint joinPoint, Expression expr) throws Throwable {
        if(((Cachable)expr).isCacheValid()) {
            int result = (Integer) joinPoint.proceed();
            ((Cachable)expr).setCache(result);
            ((Cachable)expr).validateCache();
        }

        return ((Cachable)expr).getCache();
    }

    @Pointcut("execution(test.spring.aop.expr.Plus.new(test.spring.aop.expr.Expression, test.spring.aop.expr.Expression))")
    public void newPlus() {}

    @After("newPlus()")
    public void afterNewPlus(JoinPoint joinPoint) {
        ((Cachable)((Plus)joinPoint.getThis()).getLeftExpression()).setAncestor(((Plus)joinPoint.getThis()));
        ((Cachable)((Plus)joinPoint.getThis()).getRightExpression()).setAncestor(((Plus)joinPoint.getThis()));
    }
}
