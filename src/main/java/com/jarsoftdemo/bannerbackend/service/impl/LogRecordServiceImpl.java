package com.jarsoftdemo.bannerbackend.service.impl;

import com.jarsoftdemo.bannerbackend.entity.LogRecord;
import com.jarsoftdemo.bannerbackend.repository.LogRecordRepository;
import com.jarsoftdemo.bannerbackend.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogRecordServiceImpl implements LogRecordService {


    private final LogRecordRepository logRecordRepository;

    @Autowired
    LogRecordServiceImpl(LogRecordRepository logRecordRepository) {
        this.logRecordRepository = logRecordRepository;
    }


    @Override
    public LogRecord save(LogRecord logRecord) {
        return logRecordRepository.save(logRecord);
    }

    @Override
    public List<LogRecord> getTodayRecords(String ipAddress, String userAgent) {
        return logRecordRepository.getRecordsByDateAndIpAddressAndUserAgent(new Date(), ipAddress, userAgent);
    }
}
