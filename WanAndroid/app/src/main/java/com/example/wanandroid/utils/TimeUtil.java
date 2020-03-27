package com.example.wanandroid.utils;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author hwq
 * @date 2020/1/5.
 * GitHub：
 * Email：
 * Description：
 */
public class TimeUtil {
    public static SimpleDateFormat getSimpleM() {
        return new SimpleDateFormat("MM");
    }

    public static SimpleDateFormat getSimpleD() {
        return new SimpleDateFormat("dd");
    }

    /**
     * 获得yy-MM-dd 格式
     *
     * @return
     */
    public static String getCaladener(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(new Date(time * 1000));
    }

    /**
     * 获得当前时间 格式为"yyyy-MM-dd  HH:mm:ss"
     *
     * @param
     * @return
     */
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获得当前时间 格式为"yyyy.MM.dd  HH.mm.ss"
     * @param time
     * @return
     */
    public static String getTimeDot(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date date = new Date(Long.parseLong(time));
        return format.format(date);
    }

    /**
     * 获得当前时间 格式为"yyyy-MM-dd  HH:mm:ss"
     *
     * @param time
     * @return
     */
    public static String getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date;
        if (!"".equals(time)&&!"0".equals(time)){
            if (time.length()==13){
                date = new Date(Long.parseLong(time) );
            }else {
                date = new Date(Long.parseLong(time)*1000);
            }
            return format.format(date);
        }else {
            return "";
        }
    }

    /**
     * 获得当前时间 格式为"yyyy-MM-dd  HH:mm:ss"
     *
     * @param time
     * @return
     */
    public static String getDateTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(time) * 1000);
        return format.format(date);
    }

    /**
     * 获得当前时间 格式为"yyyy-MM-dd"(13位)
     */
    public static String getThirteenTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(time));
        return format.format(date);
    }

    /**
     * 获得当前时间 格式为"yyyy年MM月dd日"
     *
     * @param time
     * @return
     */
    public static String getYearTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(Long.parseLong(time) * 1000);
        return format.format(date);
    }
    /**
     * 获得当前时间 格式为"yyyy年MM月dd日"
     *
     * @param
     * @return
     */
    public static String getNowDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        return format.format(date);
    }
    /**
     * 取得简单时间的 SimpleDateFormat，时间格式为 "MM-dd HH:mm"
     *
     * @return
     */
    public static SimpleDateFormat getSimpleTimeSDF() {
        return new SimpleDateFormat("MM-dd HH:mm");
    }

    /**
     * 取得简单时间的 SimpleDateFormat，时间格式为 "yyyy-MM-dd"
     *
     * @return
     */
    public static SimpleDateFormat getDateSDF0() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 取得简单时间的 SimpleDateFormat，时间格式为 "MM-dd"
     *
     * @return
     */
    public static SimpleDateFormat getDateSDF() {
        return new SimpleDateFormat("MM月dd日");
    }

    public static String getWeekSDF() {
        SimpleDateFormat 格式 = new SimpleDateFormat("M月d日", Locale.CHINA);
        Calendar 日历 = Calendar.getInstance(Locale.CHINA);
        // 当前时间，貌似多余，其实是为了所有可能的系统一致

        日历.setFirstDayOfWeek(Calendar.MONDAY);

        日历.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String weeks = 格式.format(日历.getTime());
        日历.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        weeks = weeks + "~" + 格式.format(日历.getTime());

        return weeks;
    }

    public static String getTomorrowSDF() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static String getDateAfterN(String day, int N) {
        Date date = null;
        try {
            date = getDateSDF0().parse(day);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, N);
            date = c.getTime();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getDateSDF0().format(date);
    }

    /**
     * 取得时间戳的 SimpleDateFormat
     *
     * @return
     */
    public static SimpleDateFormat getTimestampSDF() {
        return new SimpleDateFormat("yyyyMMddHHmmss");
    }

    /**
     * 生成时间戳
     *
     * @return
     */
    public static String generateTimestamp() {
        Date date = new Date(new Date().getTime());
        return getTimestampSDF().format(date);
    }

    public static String generateTimestamp(String time) {
        Date date = null;
        try {
            date = getTimestampSDF().parse(time);
            return getTimestampSDF().format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /**
     * 返回文字描述的日期
     *
     * @param
     * @return
     */
    public static String getTimeFormatText(String time) {
        Date date = toDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date2 = new Date(Long.parseLong(time) * 1000);
        String time2 = sdf.format(date2);
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            //r = (diff / year);
            return time2;
        }
        if (diff > month) {
            //r = (diff / month);
            //return r + "个月前";
            return time2;
        }
        if (diff > day) {
            r = (diff / day);//天数
            if (r<8) {
                return r + "天前";
            }else {
                return time2;
            }
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }
    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(Long.parseLong(sdate) * 1000);
        return date;
    }
    /**
     * 将字符串转位时间类型
     * @param sdate
     * @return
     */
    public static String toTime(String sdate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(Long.parseLong(sdate) * 1000);
        return dateFormat.format(date);
    }
    /**
     * 将字符串转位时间类型
     * @param
     * @return
     */
    public static String toNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     *
     * @Title: dip2px @Description: TODO(dp转px) @param @param
     *         context @param @param dpValue @param @return 设定文件 @return int
     *         返回类型 @throws
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
//    public static String GetTimeType(String mNet_Time){
//        String mTime_type;
//        String toTime = TimeUtil.toTime(mNet_Time);
//        String[] dds = new String[] {};
//        dds = toTime.split(":");
//        int mNet_Time_hour=Integer.parseInt(dds[0]);
//        if (mNet_Time_hour>=0&&mNet_Time_hour<12){
//            mTime_type = Constants.SAVE0_12;
//        }else if(mNet_Time_hour>=12&&mNet_Time_hour<=17){
//            mTime_type =Constants.SAVE12_18;
//        }else {
//            mTime_type =Constants.SAVE18_0;}
//        return mTime_type ;
//    }

    /***
     * 日期月份加减一个月
     *
     * @param datetime
     */
    public static String dateFormat(String option,String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        if("pre".equals(option)) {
            cl.add(Calendar.MONTH, -1);
        }else if("next".equals(option)){
            cl.add(Calendar.MONTH, 1);
        }
        date = cl.getTime();
        return sdf.format(date);
    }
    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / ( 60 * 60 * 24);
        long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % ( 60 * 60)) /60;
        long seconds = mss % 60;
        if(days>0){
            DateTimes= days + "天" + hours + "小时" + minutes + "分钟";
        }else if(hours>0){
            DateTimes=hours + "小时" + minutes + "分钟";
        }else if(minutes>0){
            DateTimes=minutes + "分钟";
        }else{
            DateTimes=seconds + "秒";
        }

        return DateTimes;
    }

    //判断闰年
    private static boolean isLeap(int year){
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0)) {
            return true;
        }else {
            return false;
        }
    }

    //返回当月天数
    public static int getDays(int year, int month)
    {
        int days;
        int FebDay;
        if (isLeap(year)) {
            FebDay = 29;
        }else{
            FebDay = 28;
        }
        switch (month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }
}
