package cc.zhanyun.util;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * 90000.0D) + 100000;

        return str + rannum;
    }

    public void Test() {
        String fileName = getRandomFileName();

        System.out.println(fileName);
    }

    public static String createRandomVcode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9.0D);
        }
        return vcode;
    }
}
