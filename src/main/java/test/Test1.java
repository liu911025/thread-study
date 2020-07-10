package test;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1() {
        int count = 0;
        int x=91, y=100;
        while(y>0) {
            count++;
            if(x>100) {
                x=x-10;
                y--;
            }else {
                x++;
            }
            System.out.println("x:" + x + "y:" + y);
        }
        System.out.println("执行次数:" + count);
    }

    @Test
    public void test2() {
        //System.out.println(cal(2));
        System.out.println(cal2(2));
    }

    private int cal(int n) {
        int sum = 0;
        int i = 1;
        for (; i <= n; ++i) {
            sum = sum + i;
        }
        return sum;
    }

    private int cal2(int n) {
        int sum = 0;                        // 1
        for (int i = 1; i <= n; ++i) {      // n
            for (int j = 1; j <= n; ++j) {  // n
                sum = sum + i * j;          // n
            }
        }
        return sum;
    }
}
