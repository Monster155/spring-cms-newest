package ru.itlab.cms.ascpects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    // Logger LogPoJ
    // User from spring security
    // Annotations (within())

//    @Before("execution(* ru.itlab.cms.services.ArticleService.getAll(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        System.out.println("logBefore() is running!");
//        System.out.println("execution : " + joinPoint.getSignature().getName());
//        System.out.println("******");
//    }

//    @After("execution(* ru.itlab.cms.services.ArticleService.getAll(..))")
//    public void logAfter(JoinPoint joinPoint) {
//        System.out.println("logAfter() is running!");
//        System.out.println("hijacked : " + joinPoint.getSignature().getName());
//        System.out.println("******");
//    }

    //    @AfterReturning(
//            pointcut = "execution(* ru.itlab.cms.services.ArticleService.getAll(..))",
//            returning = "result")
    @AfterReturning(pointcut = "@annotation(ru.itlab.cms.annotations.AfterReturningLog)",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        String userName = principal.getName();
//        System.out.println("logAfterReturning() is running by " + userName);
        System.out.println("logAfterReturning() is running!");
        System.out.println("Method " + joinPoint.getSignature().getName() + " returned value is : " + result);
        System.out.println("******");
    }

//    @AfterThrowing(
//            pointcut = "execution(* ru.itlab.cms.services.ArticleService.getAll(..))",
//            throwing = "error")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
//        System.out.println("logAfterThrowing() is running!");
//        System.out.println("hijacked : " + joinPoint.getSignature().getName());
//        System.out.println("Exception : " + error);
//        System.out.println("******");
//    }

//    @Around("execution(* ru.itlab.cms.services.ArticleService.getAll(..))")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("logAround() is running!");
//        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
//        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
//
//        System.out.println("Around before is running!");
//        Object result = joinPoint.proceed(); //continue on the intercepted method
//        System.out.println("Around after is running!");
//
//        System.out.println("******");
//        return result;
//    }
}