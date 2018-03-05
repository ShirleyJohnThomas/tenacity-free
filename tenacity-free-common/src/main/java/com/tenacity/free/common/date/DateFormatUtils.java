package com.tenacity.free.common.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: DateFormatUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午5:54:13
 * @desc: 日期工具类
 */
public class DateFormatUtils {

    // 私有化构造函数
    private DateFormatUtils() {
    }

	// 日期类型格式
    public static final String CHINA_FORMAT_YMD_PATTERN = "yyyy年MM月dd日";
    public static final String PUBLIC_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String PUBLIC_DATE_FORMAT_HMS = "HH:mm:ss";
    public static final String PUBLIC_DATE_FORMAT_YM = "yyyy/MM/dd HH:mm:ss";
    public static final String PUBLIC_DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String PUBLIC_DATE_FORMAT_Y = "yyyy/MM/dd";
    public static final String PUBLIC_DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String PUBLIC_DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> dateFormatLocal = new ThreadLocal<>();
    private static final SimpleDateFormat DateTimeFormater = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
    private static final String DateTimeFormaterArray[] = new String[]{PUBLIC_DATE_FORMAT_YMDHMS, PUBLIC_DATE_FORMAT_YMD, PUBLIC_DATE_FORMAT_YMDHM,
            PUBLIC_DATE_FORMAT_YM, PUBLIC_DATE_FORMAT_Y};

    /**
     * @param pattern
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:16:35
     * @desc: 获得日期格式化对象
     */
    private static DateFormat getDateFormat(String pattern) {
        SimpleDateFormat dateFormat = dateFormatLocal.get();

        if (dateFormat == null || !dateFormat.toPattern().equals(pattern)) {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatLocal.set(dateFormat);
        }

        return dateFormat;
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:16:26
     * @desc: 使用默认格式格式化日期 yyyy-MM-dd HH:mm:ss
     */
    public static String formatWithDefaultPattern(Date date) {
        return format(date, PUBLIC_DATE_FORMAT_YMDHMS);
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:16:18
     * @desc: 使用中文格式格式化日期 yyyy年MM月dd日
     */
    public static String formatWithChinaPattern(Date date) {
        return format(date, CHINA_FORMAT_YMD_PATTERN);
    }

    /**
     * @param date
     * @param pattern
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:16:11
     * @desc: 格式化日期
     */
    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:16:04
     * @desc: 使用时间格式格式化日期 HH:mm:ss
     */
    public static String formatWithTimePattern(Date date) {
        return format(date, PUBLIC_DATE_FORMAT_HMS);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:50
     * @desc: 获取当前时间 yyyy-MM-dd HH:mm:ss
     */
    public static String formatNowDate() {
        return format(new Date(), PUBLIC_DATE_FORMAT_YMDHMS);
    }

    /**
     * @param time
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:42
     * @desc: 根据时间戳获取日期 yyyy-MM-dd HH:mm:ss
     */
    public static String formLongDate(long time) {
        return format(new Date(time), PUBLIC_DATE_FORMAT_YMDHMS);
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:34
     * @desc: 将日期格式化成yyyyMMddHHmmss的格式
     */
    public static String formatDate1(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        return format.format(date);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:26
     * @desc: 将当前日期格式化成yyyyMMddHHmmss的格式
     */
    public static String formatDate1() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        return format.format(new Date());
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:18
     * @desc: 将日期格式化成yyyy-MM-dd HH:mm:ss的格式
     */
    public static String formatDate2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        return format.format(date);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:10
     * @desc: 将当前日期格式化成yyyy-MM-dd HH:mm:ss的格式
     */
    public static String formatDate2() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        return format.format(new Date());
    }

    /**
     * @param startTime
     * @param timeNum
     * @return
     * @throws Exception
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:15:02
     * @desc:与系统时间相差几个小时
     */
    public static boolean subtractionEndTime(String startTime, int timeNum) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(sdf.format(new Date()));
        long cha = end.getTime() - start.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        if (result <= timeNum) {
            // System.out.println("可用");
            return true;
        } else {
            // System.out.println("已过期");
            return false;
        }
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:52
     * @desc: 将日期格式化成yyyy-MM-dd
     */
    public static String formatDate3(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMD);
        return format.format(date);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:44
     * @desc: 将当前日期格式化成yyyy-MM-dd
     */
    public static String formatDate3() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMD);
        return format.format(new Date());
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:35
     * @desc: 获取当前8位日期格式yyyyMMdd
     */
    public static String format8Date() {
        return formatDate1().substring(0, 8);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:28
     * @desc: 获得当前月的第一天，格式为yyyyMMdd
     */
    public static String getFirstDayOfMonth() {
        String today = format8Date();
        String month = today.substring(0, 6);
        return month + "01";

    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:20
     * @desc: 获取当前6位日期格式HHmmss
     */
    public static String format6Time() {
        return formatDate1().substring(8);
    }

    /**
     * @param 改变时间字符串的格式，便于写入数据库 传入格式为yyyyMMddHHssmm 返回格式为yyyy-MM-dd HH:ss:mm
     * @return
     * @throws ParseException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:14:12
     * @desc: TODO
     */
    public static String formatDateStr(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        SimpleDateFormat format2 = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        return formatDate(date, format1, format2);
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:11:25
     * @desc: 改变时间字符串的格式，便于写入数据库 传入格式为 yyyy-MM-dd HH:ss:mm 返回格式为
     * yyyyMMddHHssmm
     */
    public static String formatDateStr2(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        SimpleDateFormat format2 = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        return formatDate(date, format, format2);
    }

    private static String formatDate(String date, SimpleDateFormat format, SimpleDateFormat formatStr)
            throws ParseException {
        String res = "";
        Date tempDate = (Date) format.parseObject(date);
        res = formatStr.format(tempDate);

        return res;
    }

    public static String formatDateStr3(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        Date tempDate = format.parse(date);
        String ret = format.format(tempDate);
        return ret;
    }

    public static String formatDateStr4(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMD);
        Date tempDate = format.parse(date);
        String ret = format.format(tempDate);
        return ret;
    }

    /**
     * @param ymd
     * @param datetime
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:11:43
     * @desc: 返回字符串日期
     */
    public static String formatDateTimeToString(String ymd, Date datetime) {
        // 格式化当前时间
        SimpleDateFormat isNow = new SimpleDateFormat(ymd);
        String now = "";
        try {
            now = isNow.format(datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:11:52
     * @desc: 将当前日期格式化成yyyy-MM-dd的格式用于生成日报
     */
    public static String formatReportDate() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMD);
        String ret = format.format(new Date());
        return ret;
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:03
     * @desc: 将当前日期格式化成yyyy-MM-dd的格式用于生成日报
     */
    public static String formatReportMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String ret = format.format(new Date());
        return ret;
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:13
     * @desc: 获取当前时间的Timestamp，用于操作数据库
     */
    public static Timestamp getTimestap() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:25
     * @desc: 计算传入的时间戳距今天多少天 例如，今天是2012-4-20 传入时间为2012-4-19 00:00:00，返回值为-1
     * 传入时间为2012-4-21 00:00:00 返回为1
     */
    public static int daysAgoToday(String date) {
        int day = (int) (getTimestap(date + " 00:00:00").getTime() / (1000 * 60 * 60 * 24));
        int today = (int) (getTimestap(formatDate3() + " 00:00:00").getTime() / (1000 * 60 * 60 * 24));
        return day - today;
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:34
     * @desc: 得到当前时间的前一个月的年月日时分秒
     */
    public static String getLastMonthTime() {
        long oneDayValue = 24 * 60 * 60 * 1000;
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(start.getTimeInMillis() - oneDayValue * 30);
        // start.add(Calendar.MONTH, -1);
        int year = start.get(Calendar.YEAR);
        int month = start.get(Calendar.MONTH) + 1;
        int date = start.get(Calendar.DATE);
        int hour = start.get(Calendar.HOUR_OF_DAY);
        int minute = start.get(Calendar.MINUTE);
        int second = start.get(Calendar.SECOND);
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " "
                + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
                + (second < 10 ? "0" + second : second);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:44
     * @desc: 得到当前时间的前一个月
     */
    public static String ulTimoToString() {
        long oneDayValue = 24 * 60 * 60 * 1000;
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(start.getTimeInMillis() - oneDayValue * 30);
        String lastMonth = start.get(Calendar.YEAR) + "-"
                + ((start.get(Calendar.MONTH) + 1) < 10 ? "0" + (start.get(Calendar.MONTH) + 1)
                : "" + (start.get(Calendar.MONTH) + 1))
                + "-" + (start.get(Calendar.DATE) < 10 ? "0" + start.get(Calendar.DATE) : start.get(Calendar.DATE));
        return lastMonth;
    }

    /**
     * @param ts
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:12:52
     * @desc: 将日期时间格式化成yyyyMMddHHmmss的格式
     */
    public static String formatDateTime(Timestamp ts) {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT);
        return format.format(ts);
    }

    /**
     * @param ts
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:00
     * @desc: 将日期时间格式化成yyyy-MM-dd的格式
     */
    public static String formatDateTime2(Timestamp ts) {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMD);
        return format.format(ts);
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:07
     * @desc: 转换成时间字符串
     */
    public static String formatDateTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        return format.format(getTimestap());
    }

    /**
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:16
     * @desc: 转换成时间字符串
     */
    public static String formatDateTimeStr2() {
        SimpleDateFormat format = new SimpleDateFormat(PUBLIC_DATE_FORMAT_YMDHMS);
        return format.format(getTimestap());
    }

    /**
     * @param datetime
     * @return
     * @throws ParseException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:24
     * @desc: 字符串转timestamp
     */
    public static final Timestamp parserTimestamp(String datetime) throws ParseException {
        Timestamp ts = null;
        @SuppressWarnings("unused")
        SimpleDateFormat sf = null;
        for (int i = 0; i < DateTimeFormaterArray.length; i++) {
            ts = convertDate(DateTimeFormaterArray[i], datetime);
            if (ts != null)
                break;
        }
        return ts;
    }

    public static final Timestamp convertDate(String format, String source) {
        try {
            Timestamp t = new Timestamp(new SimpleDateFormat(format).parse(source).getTime());
            return t;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param date
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:33
     * @desc: 日期字符串转timestamp
     */
    public static final Timestamp dateStrCovTimestamp(String date) {
        date += " 00:00:00";
        Timestamp ts = null;
        try {
            ts = new Timestamp(DateTimeFormater.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ts;
    }

    public static Timestamp getTimestap(String date) {
        return Timestamp.valueOf(date);
    }

    public static String getMyTimeStamp(String tempTimes) {
        long tempTimeLong = Long.valueOf(tempTimes).intValue(); // 将数字转化成long型
        long ss = (tempTimeLong - 70 * 365 - 17 - 2) * 24 * 3600 * 1000;
        Date dss = new Date(ss);
        SimpleDateFormat dateFormat = new SimpleDateFormat(CHINA_FORMAT_YMD_PATTERN);
        String str = dateFormat.format(dss);
        return str;
    }

    /**
     * @param type
     * @param count
     * @param timeType
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:13:52
     * @desc: 获取系统当前时间的上几天/周/月/年或者以后几天/周/月/年的时间
     */
    public static String getSomeTimes(int type, int count, String timeType) {
        String resultTimeStr = "";
        Date date = null;
        Calendar curr = Calendar.getInstance();
        switch (type) {
            case 1:
                curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + count);
                date = curr.getTime();
                break;
            case 2:
                curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + 7 * count);
                date = curr.getTime();
                break;
            case 3:
                curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + count);
                date = curr.getTime();
                break;
            case 4:
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + count);
                date = curr.getTime();
                break;
            default:
                break;
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat(timeType);
        resultTimeStr = timeFormat.format(date);
        return resultTimeStr;
    }

}
