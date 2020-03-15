package 对比度计算;

import utils.ImageUtils;

/**
 * @auther Summerday
 */
public class Main {


    public static void main(String[] args) {
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
        System.out.println("/*图像的对比度为：*/");
        double i = ImageUtils.computeContrast(arr);
        System.out.println(i);

        System.out.println("/*（2）对其进行非线性动态范围调整，并计算调整后图像的对比度。*/");
        System.out.println("g = 105(log(i+1))");
        ImageUtils.changeWithNoneLiner(arr);

        double k = ImageUtils.computeContrast(arr);
        System.out.println(k);



        //System.out.println("/*（1）线性对比度展宽，要求展宽后的对比度大于原图像的对比度。*/");
        //System.out.println("[0,100] a=0.5  [100,150] a=2  [150,255] a=1");
        //ImageUtils.changeWithLiner(arr);
        //double j = ImageUtils.computeContrast(arr);
        //System.out.println(j);




    }

}
