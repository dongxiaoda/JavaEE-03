package edu.bjtu.sse.djd.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 董金达
 * @version 1.0
 * @name StudentHomeworkAspect
 * @date 2020/4/22 22:18
 **/

@Aspect
@EnableAspectJAutoProxy
public class StudentHomeworkAspect {

    @Pointcut("execution(* edu.bjtu.sse.djd.controller.StudentHomeworkController.*(..))")
    private void studentHomeworkPointcut(){}

}
