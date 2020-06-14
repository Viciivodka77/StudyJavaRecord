package StudyDay31.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class LogSchema {


    public void afterReturning(JoinPoint jp,Object returnValue) throws Throwable {
        System.out.println("后置通知:目标对象:" + jp.getThis()  +" 方法名:" + jp.getSignature().getName() +" 参数个数"+ jp.getArgs().length +" 返回值"+returnValue);
    }

    public void before(JoinPoint jp) throws Throwable {
        System.out.println("执行前置通知方法:目标对象:" + jp.getThis()  +" 方法名:" + jp.getSignature().getName() +" 参数个数"+ jp.getArgs().length);
    }

    public void afterThrowing(JoinPoint jp, Throwable throwable){
        System.out.println("异常通知:目标对象:" + jp.getTarget()  +"方法名:" + jp.getSignature().getName() +"参数个数 "+ jp.getArgs().length +"---异常名:" + throwable.getMessage());
    }

    public Object invoke(ProceedingJoinPoint jp) throws Throwable {
        Object proceed = null;
        try {
            //前置通知
            System.out.println("前置");
            proceed = jp.proceed();
            //后置通知
            System.out.println("后置");

        }catch (Exception e){
            //异常通知
            System.out.println("异常");
            //            e.printStackTrace();
        }
        return proceed;
    }


}
