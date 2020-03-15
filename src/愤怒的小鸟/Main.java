package 愤怒的小鸟;

import sun.misc.Cache;

/**
 * @auther Summerday
 */
public class Main {

    public static void main(String[] args) {
        float s = 1000;//两车的距离
        float v1 = 10;//两车的速度
        float v2 = 50;//鸟的速度
        float t;//时间
        int count = 0;//次数
        while (s > 1)//当两车距离大于1
        {
            t = s / (v2 + v1);//鸟到B车的时间
            s = s - t * 2 * v1;//此时两车的距离
            count++;//次数加1
            t = s / (v2 + v1);//鸟到A车的时间
            s = s - t * 2 * v1;//此时两车的距离
        }
        System.out.println(count);
    }

}




