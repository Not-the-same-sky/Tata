package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 不一样的天空 on 2016/9/6.
 */
public class TimeUtil {
    private static final String FORMAT="yy-MM-dd HH:mm";
    private static final String HOURANDMINFORMAT="HH:mm";
    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT);
        return format.format(new Date(time));
    }

    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat(HOURANDMINFORMAT);
        return format.format(new Date(time));
    }

    public static String getChatTime(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));
        switch (temp) {
            case 0:
                result = "今天 " + getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
                result = "前天 " + getHourAndMin(timesamp);
                break;

            default:
                result = getTime(timesamp);
                break;
        }

        return result;
    }
}
