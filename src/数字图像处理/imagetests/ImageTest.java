package 数字图像处理.imagetests;

import org.junit.Test;

import static 数字图像处理.imageutils.ImageUtils.*;

/**
 * @auther Summerday
 */
public class ImageTest {
    double[][] arr = {
            {1,  5, 255, 255, 100, 200, 255, 200},
            {1,  7, 254, 255, 100,  10,  10,   9},
            {3,  7,  10, 100, 100,   2,   9,   6},
            {3,  6,  10,  10,   9,   2,   8,   2},
            {2,  1,   8,   8,   9,   3,   4,   2},
            {1,  0,   7,   8,   8,   3,   2,   1},
            {1,  1,   8,   8,   7,   2,   2,   1},
            {2,  3,   9,   8,   7,   2,   2,   0}
    };
    @Test
    public void test1(){

        System.out.println("---------- 原图 ----------");
        showHistogramArr(arr);
        System.out.println("原图对比度："+computeContrast(arr));
        changeWithLiner(arr,10,100,20,0.5);
        System.out.println("---------- 新图 ----------");
        showHistogramArr(arr);
        System.out.println("新图线性对比度展宽后的对比度："+computeContrast(arr));
    }
    @Test
    public void test2(){

        System.out.println("---------- 原图 ----------");
        showHistogramArr(arr);
        System.out.println("原图对比度："+computeContrast(arr));
        changeWithNoneDynamic(arr);
        System.out.println("---------- 新图 ----------");
        showHistogramArr(arr);
        System.out.println("非线性动态范围调整后的对比度："+computeContrast(arr));
    }
    @Test
    public void test3(){

        System.out.println("---------- 原图 ----------");
        showHistogramArr(arr);
        System.out.println("原图对比度："+computeContrast(arr));
        changeWithDynamic(arr,10,200);
        System.out.println("---------- 新图 ----------");
        showHistogramArr(arr);
        System.out.println("线性动态范围调整后的对比度："+computeContrast(arr));
    }
    @Test
    public void test4(){

        System.out.println("---------- 原图 ----------");
        showHistogramArr(arr);
        System.out.println("原图对比度："+computeContrast(arr));
        equalization(arr);
        System.out.println("---------- 新图 ----------");
        showHistogramArr(arr);
        System.out.println("直方图均衡化后的对比度："+computeContrast(arr));
    }
}
