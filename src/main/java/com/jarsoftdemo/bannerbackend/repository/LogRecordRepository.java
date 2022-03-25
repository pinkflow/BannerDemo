package com.jarsoftdemo.bannerbackend.repository;

import com.jarsoftdemo.bannerbackend.entity.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {

    @Query(value = "select * from request_log l where datediff(l.date, :date) = 0 and ip_address = :ipAddress and user_agent = :userAgent",
            nativeQuery = true)
    List<LogRecord> getRecordsByDateAndIpAddressAndUserAgent(Date date, String ipAddress, String userAgent);
}
