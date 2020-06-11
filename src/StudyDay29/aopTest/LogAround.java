package StudyDay29.aopTest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object proceed = null;
        try {
            //前置通知
            System.out.println("前置");
            proceed = invocation.proceed();
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
