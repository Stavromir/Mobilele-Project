package bg.softuni.mobilele.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("execution(* bg.softuni.mobilele.service.OfferService.getAllOffers(..))")
    public void trackOfferSearch(){}

    @Pointcut("@annotation(WarnIfExecutionExceeds)")
    public void warnIfTimeExceeds(){}
}
