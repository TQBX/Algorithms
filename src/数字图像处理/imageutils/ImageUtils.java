package 数字图像处理.imageutils;

import java.util.*;

/**
 * @auther Summerday
 */
public class ImageUtils {

    /* 灰度图为[0-255规格] */
    private static final int RANGE = 255;

    /**
     * 根据直方图计算图像对比度,默认大于等于3*3matrix
     * @param matrix 直方图二维数组
     * @return 对比度
     */
    public static double computeContrast(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //对比度
        double contrast = 0;
        //相邻像素间灰度差的像素个数
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //当前灰度
                double current = matrix[i][j];
                //首尾行
                if (i == 0 || i == row - 1) {
                    //首尾列
                    if (j == 0 || j == col - 1) {
                        double row_temp = j == 0 ? current - matrix[i][j + 1] : current - matrix[i][j - 1];
                        double col_temp = i == 0 ? current - matrix[i + 1][j] : current - matrix[i - 1][j];
                        //计算平方和
                        contrast += computePowNSum(2, row_temp, col_temp);
                        //个数加2
                        count += 2;
                        //中间列
                    } else {
                        double row_front_temp = current - matrix[i][j - 1];
                        double row_behind_temp = current - matrix[i][j + 1];
                        double col_temp = i == 0 ? current - matrix[i + 1][j] : current - matrix[i - 1][j];

                        contrast += computePowNSum(2, row_front_temp, row_behind_temp, col_temp);
                        count += 3;
                    }
                    //中间行
                } else {
                    //首尾列
                    if (j == 0 || j == col - 1) {
                        double row_temp = j == 0 ? current - matrix[i][j + 1] : current - matrix[i][j - 1];
                        double col_down_temp = current - matrix[i + 1][j];
                        double col_up_temp = current - matrix[i - 1][j];
                        contrast += computePowNSum(2, row_temp, col_down_temp, col_up_temp);
                        count += 3;
                        //中间列
                    } else {
                        double row_front_temp = current - matrix[i][j - 1];
                        double row_behind_temp = current - matrix[i][j + 1];
                        double col_down_temp = current - matrix[i + 1][j];
                        double col_up_temp = current - matrix[i - 1][j];
                        contrast += computePowNSum(2, row_behind_temp, row_front_temp, col_down_temp, col_up_temp);
                        count += 4;
                    }
                }
            }
        }
        contrast /= count;
        return contrast;
    }

    /**
     * 计算任意阶次方和
     *
     * @param exponent 阶次
     * @param params   要计算的因数
     * @return exp次方和
     */
    private static double computePowNSum(double exponent, double... params) {
        double sum = 0;
        for (double i : params) {
            sum += Math.pow(i, exponent);
        }
        return sum;
    }

    /**
     * 线性动态展宽
     * @param matrix 输入图像灰度图
     * @param fa  边界fa
     * @param fb  边界fb
     * @param a   [0,fa]的系数
     * @param b   [fa,fb]的系数
     */
    public static void changeWithLiner(double[][] matrix, double fa, double fb, double a, double b) {
        //映射系数
        int row = matrix.length;
        int col = matrix[0].length;
        double c = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                double current = matrix[i][j];

                if (current >= 0 && current <= fa) {
                    matrix[i][j] *= a;
                } else if (current > fa && current <= fb) {
                    matrix[i][j] = fa * a + (current - fa) * b;
                } else {
                    c = (255 - fa * a + (fb - fa) * b) / (255 - fb);
                    matrix[i][j] = (current - fb) * c;
                }
            }
        }
        System.out.println("线性对比度展宽：[0," + fa + "] -- " + a + ", [" + fa + "," + fb + "] -- " + b + ", [" + fb + ",255] -- " + String.format("%.2f", c));
    }

    /**
     * 线性动态范围调整，小于fa赋值0，大于fb赋值RANGE
     *
     * @param matrix 输入图像灰度图
     * @param fa     边界fa
     * @param fb     边界fb
     */
    public static void changeWithDynamic(double[][] matrix, double fa, double fb) {
        double beld = RANGE / (fb - fa);//对图像素
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                double current = matrix[i][j];
                if (current >= fa && current < fb) {
                    matrix[i][j] = beld * (current - fa);
                } else if (current < fa) {
                    matrix[i][i] = 0;
                } else {
                    matrix[i][j] = RANGE;
                }
            }
        }
        System.out.println("线性动态范围调整：[fa,fb] -- [" + fa + "," + fb + "]");

    }

    /**
     * 非线性动态范围调整，log函数平滑处理
     * @param matrix  输入图像灰度图
     */
    public static void changeWithNoneDynamic(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        double c = RANGE/Math.log10(RANGE+1);//对图像素处理

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = (Math.log10(matrix[i][j] + 1)) *c;
            }
        }
        System.out.println("非线性动态范围调整：g = 105(log(i+1))");
    }
    /**
     * 直方图均衡化方法实现
     * @param matrix 输入图像灰度图
     */
    public static void equalization(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        //灰度级和像素个数的映射
        Map<Integer, Integer> map = getHistogramArr(matrix);

        //从高到底获取灰度级
        //从高到底获取各个像素的个数的数组[2, 8, 11, 5, 1, 1, 2, 5, 8, 5, 5, 4, 2, 1, 4]
        List<Integer> count_list = new ArrayList<>(map.values());
        List<Integer> num_list = new ArrayList<>(map.keySet());

        System.out.println("原图灰度级取值:" + num_list);
        //求除图像f的总体像素个数
        int Nf = row * col;
        List<Double> Hs = new ArrayList<>();
        //计算原图的灰度分布概率
        for (double count : count_list) {
            count = count / Nf;
            Hs.add(count);
        }
        System.out.println("HS原图的灰度分布概率" + Hs);
        //[0.03125, 0.125, 0.171875, 0.078125, 0.015625, 0.015625, 0.03125, 0.078125, 0.125, 0.078125, 0.078125, 0.0625, 0.03125, 0.015625, 0.0625]

        //求各灰度级累计分布
        List<Double> Hp = new ArrayList<>();
        for (int i = 0; i < Hs.size(); i++) {
            double sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += Hs.get(j);
            }
            Hp.add(sum);
        }
        System.out.println("HP各级灰度累计分布：" + Hp);
        //[0.03125, 0.15625, 0.328125, 0.40625, 0.421875, 0.4375, 0.46875, 0.546875, 0.671875, 0.75, 0.828125, 0.890625, 0.921875, 0.9375, 1.0]
        //计算原、新图灰度值的隐射关系

        //新图的灰度值，取整  = 累计的概率分布范围hp*灰度最大范围255  [8, 40, 84, 104, 108, 112, 120, 139, 171, 191, 211, 227, 235, 239, 255]
        List<Integer> list = new ArrayList<>();
        for (Double d : Hp) {
            double max = num_list.get(num_list.size() - 1);
            d *= max;
            int r = (int) Math.round(d);
            list.add(r);
        }
        System.out.println("新图灰度级取值：" + list);

        //用新图的灰度级取值代替旧的
        insteadOf(matrix, list, num_list);

    }
    /**
     * 用新灰度值代替旧灰度值
     * @param matrix 输入图像灰度图
     * @param new_list 新灰度值
     * @param old_list 旧灰度值
     */
    private static void insteadOf(double[][] matrix, List<Integer> new_list, List<Integer> old_list) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int current = (int) Math.round(matrix[i][j]);
                //因为new_list与old_list一一对应
                int index = old_list.indexOf(current);
                matrix[i][j] = new_list.get(index);
            }
        }
    }

    /**
     * 四舍五入显示灰度图
     * @param matrix  输入图像灰度图
     */
    public static void showHistogramArr(double[][] matrix) {
        for (double[] ar : matrix) {
            for (double a : ar) {
                System.out.print(Math.round(a) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 从高到底获取 灰度级：个数的map
     * @param matrix  输入图像灰度图
     * @return  map：键值对形式：{灰度级：灰度级所占个数}
     */
    private static Map<Integer, Integer> getHistogramArr(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        Map<Integer, Integer> map = new TreeMap<>(Integer::compareTo);
        for (double[] doubles : matrix) {
            for (int j = 0; j < col; j++) {
                //存在第一个数
                int current = (int) Math.round(doubles[j]);

                if (!map.containsKey(current)) {
                    map.put(current, 1);
                } else {
                    Integer count = map.get(current);
                    count += 1;
                    map.put(current, count);
                }
            }
        }
        return map;

    }


}
