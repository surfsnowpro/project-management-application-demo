package com.jrp.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// This does not get loaded as a Bean by default without adding @Component
@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // The WHERE: Defines where to apply the methods to run
    // in the controllers package - the classes, the methods and everything else
    @Pointcut("within(com.jrp.pma.controllers..*)"
            + "|| within(com.jrp.pma.dao..*)")
    public void definePackagePointCuts() {
        //empty method just to name the location specified in the pointcut
    }

    // The WHAT:
    // this method will run AFTER EVERYTHING (methods, etc) in what was specified in the WITHIN clause
    @After("definePackagePointCuts()")
    public void log() {
        log.debug(" ---- ");
    }
}
