package 插入排序;

import java.util.Arrays;

/**
 * @auther Summerday
 */
public class InsertSort {


    public static void main(String[] args) {

        int[] arr = {1,9,4,6,5,-4};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //插入排序：先前的子线性表都保证是有序的 如果将[1,3,2]进行按递增排序，如何将2插入呢

    //假设子线性表有序，先将2存到变量current中，向前比较，如果比之前小，则arr[2] = arr[1],此时变成[1,3,3]

    //直到没有前面元素大为止，此时将当前位置赋值为current，arr[1] = current = 2,结果便是[1,2,3]
    private static void insertSort(int[] arr){

        for(int i = 1;i<arr.length;i++){
            int current = arr[i];

            int k;

            for(k = i-1;k>=0&& arr[k]>current;k--){

                arr[k+1] = arr[k];
            }

            arr[k+1] = current;
        }
    }
}
