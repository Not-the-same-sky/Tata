package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 不一样的天空 on 2016/9/6.
 */
public class TimeUtil {
    private static final String DATE_FORMAT = "yy-MM-dd HH:mm";
    private static final String TODAY_FORMAT = "yy年MM月dd日";
    private static final String HOURANDMIN_FORMAT = "HH:mm";

    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(new Date(time));
    }

    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat(HOURANDMIN_FORMAT);
        return format.format(new Date(time));
    }

    public static String getChatTime(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otherDay);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));
        switch (temp) {
            case 0:
                result = getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                result = "星期" + getWeek(calendar.get(Calendar.DAY_OF_WEEK)) + " " + getHourAndMin(timesamp);
                break;
            default:
                result = getTime(timesamp);
                break;
        }
        return result;
    }

    private static String getWeek(int day) {
        String dayOfWeek = "";
        switch (day) {
            case 1:
                dayOfWeek = "天";
                break;
            case 2:
                dayOfWeek = "一";
                break;
            case 3:
                dayOfWeek = "二";
                break;
            case 4:
                dayOfWeek = "三";
                break;
            case 5:
                dayOfWeek = "四";
                break;
            case 6:
                dayOfWeek = "五";
                break;
            case 7:
                dayOfWeek = "六";
                break;
            default:
                break;
        }
        return dayOfWeek;
    }
    public static String getToday(){
        SimpleDateFormat format = new SimpleDateFormat(TODAY_FORMAT);
        return format.format(new Date(System.currentTimeMillis()));
    }
}
