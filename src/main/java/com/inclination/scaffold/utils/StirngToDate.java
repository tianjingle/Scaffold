package com.inclination.scaffold.utils;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StirngToDate {

	public static Date String2Date(String dateString){
		DateTimeFormatter format=DateTimeFormat.forPattern("yyyyMMdd");
		if(Strings.isBlank(dateString)){
			DateTime dateTime=DateTime.parse("00000101",format);
			return dateTime.toDate();
		}else{
			DateTime dateTime=DateTime.parse(dateString,format);
			return dateTime.toDate();
		}
	}
	public static Date StringTodate1(String dateString){
		DateTimeFormatter format=DateTimeFormat.forPattern("yyyy-MM-dd");
		if(Strings.isBlank(dateString)){
			DateTime dateTime=DateTime.parse("0000-01-01",format);
			return dateTime.toDate();
		}else{
			DateTime dateTime=DateTime.parse(dateString,format);
			return dateTime.toDate();
		}
	}
}
