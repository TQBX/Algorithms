package 冒泡排序;

import java.util.Arrays;

/**
 * @auther Summerday
 */
public class BubbleSort1 {

    public static void main(String[] args) {
        System.out.println("排序前");
        int[] arr1 = new int[]{6, 9, 8, 3, 2, 11, 15, 16, 18, 19};
        int[] arr2 = new int[]{6, 9, 8, 3, 2, 11, 15, 16, 18, 19};
        int[] arr3 = new int[]{6, 9, 8, 3, 2, 11, 15, 16, 18, 19};
        sort(arr1);
        System.out.println("------");
        sort1(arr2);
        System.out.println("------");
        sort2(arr3);
    }

    private static void sort(int[] arr) {
        //最后一轮不需要排序 所以到arr.length-2位置的索引即可
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("第" + (i + 1) + "趟");
            //每轮过后都可以确定一个数 所以arr.length-(i+1）
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                }
                System.out.println("第" + (j + 1) + "次：" + Arrays.toString(arr));
            }
        }
    }

    private static void sort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("第" + (i + 1) + "趟");
            //优化排序，增加判断位，有序标记
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //异或运算交换元素
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    //有元素交换，所以还需要进行下一轮排序
                    flag = false;

                }
                System.out.println("第" + (j + 1) + "次：" + Arrays.toString(arr));
            }
            //该轮结束后，如果顺序没改动说明已经排序结束
            if (flag) {
                System.out.println("!!!!!后面已经有序！！！");
                break;
            }

        }
    }

    private static void sort2(int[] arr) {
        // 最后一次交换的下标
        int lastSwapIndex = 0;
        // 无序数组的边界，每次比较比到这里为止
        int arrBoundary = arr.length - 1;

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("第" + (i + 1) + "趟");
            // 优化冒泡排序，增加判断位，有序标记，每一轮的初始是true
            boolean flag = true;
            for (int j = 0; j < arrBoundary; j++) {
                // 找最小数，如果前一位比后一位大，则交换位置
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    // 有元素交换，所以不是有序，标记变为false
                    flag = false;
                    // 最后一次交换元素的位置
                    lastSwapIndex = j;
                }
                System.out.println("  第" + (j + 1) + "次：" + Arrays.toString(arr));
            }

            // 把最后一次交换元素的位置赋值给无序数组的边界
            arrBoundary = lastSwapIndex;
            // 说明上面内层for循环中，没有交换任何元素，直接跳出外层循环
            if (flag||arrBoundary == 0) {
                break;
            }
        }
    }

    private static void mysort(int[] arr){
        //控制趟数
        for(int i = 0;i<arr.length-1;i++){

            int lastSwapIndex = 0;
            int arrBoundary = arr.length-1;

            boolean flag = true;
            //控制次数
            for(int j = 0;j<arrBoundary;j++){
                if(arr[j]>arr[j+1]){
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    flag = false;
                    lastSwapIndex = j;

                }
            }
            arrBoundary = lastSwapIndex;
            if(flag|| arrBoundary == 0){
                break;
            }

        }
    }



}
