package StudyDay29.aopTest;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LogException implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable){
        System.out.println("异常通知:目标对象:" + target +"方法名:" + method +"参数个数"+args.length +"异常名" + throwable.getMessage());
    }
}
