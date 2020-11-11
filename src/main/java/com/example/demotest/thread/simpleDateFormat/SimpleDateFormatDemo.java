package com.example.demotest.thread.simpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;


/**
 * SimpleDateFormat线程不安全
 * <p>
 * 解决方法:
 * 1.使用局部变量
 * 2.加同步锁
 * 3.使用ThreadLocal
 * SimpleDateFormat的创建过程可以改为延迟加载
 * private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
 *
 * @Override protected SimpleDateFormat initialValue() {
 * return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * }
 * };
 * 4.JAVA 8 使用DateTimeFormatter
 * <p>
 * //解析日期String
 * dateStr= "2016年10月25日";DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
 * LocalDate date= LocalDate.parse(dateStr, formatter);
 * //日期转换为字符串
 * LocalDateTime now = LocalDateTime.now();
 * DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
 * String nowStr = now .format(format);
 * System.out.println(nowStr);
 */

public class SimpleDateFormatDemo {

    /**
     * 定义一个全局的SimpleDateFormat
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 使用ThreadFactoryBuilder定义一个线程池
     */
    /*private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();*/
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());
    /**
     * 定义一个CountDownLatch，保证所有子线程执行完之后主线程再执行
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) { //定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) { //获取当前时间
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> { //时间增加
                calendar.add(Calendar.DATE, finalI);    //通过simpleDateFormat把时间转换成字符串
                String dateString = simpleDateFormat.format(calendar.getTime());//把字符串放入Set中
                dates.add(dateString); //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出去重后的时间个数
        System.out.println(dates.size());
    }


}
