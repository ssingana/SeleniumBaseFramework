package com.web.ui.automation.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.web.ui.automation.configurations.WebdriverConfiguration;

public class CommonUtil {

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Date getDateInstanceForDay(int dayNumber) {
		return DateUtils.addDays(new Date(), -dayNumber);
	}

	public static Long getStartOfDay(Date date) {
		return DateUtils.truncate(date, Calendar.DATE).getTime();
	}

	public static Long getEndOfDay(Date date) {
		return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1).getTime();
	}

	public static String percentageCalculator(int total, int whatis) {
		float percent = (whatis * 100.0f) / total;
		return String.format("%.1f", percent) + "%";
	}

	public static String getSystemIpAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public static String calculateExecutionTime() {
		NumberFormat numberFormat = new DecimalFormat("#,##0.0");
		Long totalExecutionTimeInMilliSeconds = getTimeinMilliSeconds();
		String timeTaken = "";
		if (totalExecutionTimeInMilliSeconds < 1000) {
			timeTaken = totalExecutionTimeInMilliSeconds + " ms";
		} else if (totalExecutionTimeInMilliSeconds < 60000) {
			timeTaken = numberFormat.format((double) totalExecutionTimeInMilliSeconds / 1000) + " sec";
		} else if (totalExecutionTimeInMilliSeconds < 3600000) {
			timeTaken = totalExecutionTimeInMilliSeconds / 60000 + " mins "
					+ numberFormat.format((double) (totalExecutionTimeInMilliSeconds % 60000) / 1000) + " sec";
		} else {
			timeTaken = (totalExecutionTimeInMilliSeconds / 3600000) + " hrs "
					+ ((totalExecutionTimeInMilliSeconds % 3600000) / 60000) + " mins "
					+ numberFormat.format((double) (totalExecutionTimeInMilliSeconds % 60000) / 1000) + " sec";
		}
		return timeTaken;
	}

	private static Long getTimeinMilliSeconds() {
		DateFormat dateFormat = new SimpleDateFormat(WebdriverConfiguration.getDateFormat());
		String startTime = WebdriverConfiguration.startTime;
		String endTime = WebdriverConfiguration.endTime;
		Date startDate;
		try {
			startDate = dateFormat.parse(startTime);
			Date endDate = dateFormat.parse(endTime);
			return endDate.getTime() - startDate.getTime();
		} catch (ParseException e) {
			System.out.println("Error while parsing the date format");
			e.printStackTrace();
		}
		return 0L;

	}

}
