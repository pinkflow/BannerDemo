package com.jarsoftdemo.bannerbackend.service;

import com.jarsoftdemo.bannerbackend.entity.LogRecord;

import java.util.List;

public interface LogRecordService {

    LogRecord save(LogRecord logRecord);

    List<LogRecord> getTodayRecords(String ip, String userAgent);

}
