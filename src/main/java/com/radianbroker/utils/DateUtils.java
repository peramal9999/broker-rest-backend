package com.radianbroker.utils;


import com.radianbroker.exceptions.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

	private static final SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String verifyDateFormat(String date) {
		try {
			return mysqlDateFormat.format(mysqlDateFormat.parse(date)).toString();
		} catch (ParseException e) {
		   throw new BadRequestException("Invalid date format!");
		}
	}
	
	public static String getTodaysDate() {
		Date date = new Date();
		return mysqlDateFormat.format(date);
	}
	
	public static ZoneOffset getZoneOffset(String timeZone) {
        if(timeZone != null && !timeZone.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            ZoneId zone = ZoneId.of(timeZone);
            return zone.getRules().getOffset(now);
        } else {
            return ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
        }
    }
	
}
