package 选择排序;

import java.util.Arrays;

/**
 * @auther Summerday
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,2,1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr){
        //第一层循环从起始元素选到倒数第二个元素
        for (int i = 0; i < arr.length - 1; i++) {
            //再进入第二层循环之前，将外层循环的下标赋值给临时最小下标
            int  minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找到最小值元素的下标
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
            }
            //如果下标改变，说明需要交换
            if (minPos != i) {
                int tmp = arr[minPos];
                arr[minPos] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
