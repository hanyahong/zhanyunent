package cc.zhanyun.util;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getCurrentTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static String getCurrentTimeAsNumber() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static String getCurrentTime(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static String getCurDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd E");
        String strDate = simpledateformat.format(calendar.getTime());
        return strDate;
    }

    public static String getCurDate(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        String strDate = simpledateformat.format(calendar.getTime());
        return strDate;
    }

    public static Date stringToDate(String strDate, String srcDateFormat,
                                    String dstDateFormat) {
        Date rtDate = null;
        Date tmpDate = new SimpleDateFormat(srcDateFormat).parse(strDate,
                new ParsePosition(0));

        String tmpString = null;
        if (tmpDate != null)
            tmpString = new SimpleDateFormat(dstDateFormat).format(tmpDate);
        if (tmpString != null) {
            rtDate = new SimpleDateFormat(dstDateFormat).parse(tmpString,
                    new ParsePosition(0));
        }
        return rtDate;
    }
}
