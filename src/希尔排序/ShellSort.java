package 希尔排序;

import utils.ArrayUtils;

/**
 * @auther Summerday
 */
public class ShellSort {

    public static void main(String[] args){
        int[] arr = ArrayUtils.getRandomArray(10, 10, false);

        ArrayUtils.show(arr);

        shellSort(arr);

        ArrayUtils.show(arr);

    }

    private static void shellSort(int[] arr) {
        int len = arr.length;
        //最开始增量gap为数组长度的一半
        for (int gap = len / 2; gap > 0; gap /= 2) {
            //对各个分组进行插入排序
            for (int i = gap; i < len; i++) {

                //将arr[i]插入到所在分组的正确位置上
                int temp = arr[i];
                int j;
                for(j = i;j>=gap&&arr[j-gap]>temp;j-=gap){
                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;

            }
        }


    }

}
