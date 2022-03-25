package com.jarsoftdemo.bannerbackend.logging;

import com.jarsoftdemo.bannerbackend.entity.Banner;
import com.jarsoftdemo.bannerbackend.entity.LogRecord;
import com.jarsoftdemo.bannerbackend.exception.NoContentException;
import com.jarsoftdemo.bannerbackend.service.LogRecordService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogRecorder {

    private final LogRecordService logRecordService;

    @Autowired
    public LogRecorder(LogRecordService logRecordService) {
        this.logRecordService = logRecordService;
    }

    @AfterThrowing(
            pointcut = "execution(* com.jarsoftdemo.bannerbackend.controller.BannerController.getBanner(..))",
            throwing = "ex")
    public void logException(JoinPoint joinPoint, NoContentException ex) {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        LogRecord logRecord = new LogRecord();
        logRecord.setIpAddress(request.getRemoteAddr());
        logRecord.setUserAgent(request.getHeader("User-Agent"));
        logRecord.setDate(new Date());
        logRecord.setNoContent(ex.getMessage());
        logRecordService.save(logRecord);
    }


    @AfterReturning(pointcut = "execution(* com.jarsoftdemo.bannerbackend.controller.BannerController.getBanner(..))", returning = "banner")
    public void logAfterGetEmployee(JoinPoint joinPoint, Banner banner) {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        LogRecord logRecord = new LogRecord();
        logRecord.setIpAddress(request.getRemoteAddr());
        logRecord.setUserAgent(request.getHeader("User-Agent"));
        logRecord.setDate(new Date());
        logRecord.setBanner(banner);
        logRecordService.save(logRecord);
    }

}
