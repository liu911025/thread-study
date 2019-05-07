package com.example.demotest.date;

import com.example.demotest.util.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test_01() throws ParseException {
        String str = "2019-05-06 14:25:03";
        Date date = sdf.parse(str);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 10);

        System.out.println(sdf.format(calendar.getTime()));

        int i = DateUtils.dateCompare(calendar.getTime(), new Date());
        System.out.println(i);
    }

    @Test
    public void test_02() {
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
    }

}
