package bg.softuni.mobilele.service.aop;

import bg.softuni.mobilele.service.MonitoringService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    private final MonitoringService monitoringService;

    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Before("PointCuts.trackOfferSearch()")
    public void logOfferSearch() {
        monitoringService.logOfferSearch();
    }

    @Around("PointCuts.warnIfTimeExceeds()")
    public Object logExecutionTime(ProceedingJoinPoint  pjp) throws Throwable {
        WarnIfExecutionExceeds annotation = getAnnotation(pjp);

        long timeOut = annotation.timeInMillis();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //TODO timeout logic
        var proceed = pjp.proceed();

        stopWatch.stop();

        if (stopWatch.lastTaskInfo().getTimeMillis() > timeOut) {
            LOGGER.warn("The method {} ran for {} millis which is more than the expected {} millis",
                    pjp.getSignature(),
                    stopWatch.lastTaskInfo().getTimeMillis(),
                    timeOut);
        }

        return proceed;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint  pjp) {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        try {
            return pjp
                    .getTarget()
                    .getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(WarnIfExecutionExceeds.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
