package 冒泡排序;

import org.omg.CORBA.ARG_OUT;
import utils.ArrayUtils;

import java.util.Arrays;

/**
 * @auther Summerday
 */
public class MySort {
    public static void main(String[] args) throws Exception {
        int[] arr = ArrayUtils.getRandomArray(10, 10, false);
        ArrayUtils.show(arr);

        //bubbleSort(arr);

        //bubbleSort2(arr);
        //insertSort(arr);
        //selectSort(arr);
        mergeSortion(arr);
        ArrayUtils.show(arr);


    }

    private static void bubbleSort(int[]arr){

        for(int i = 0;i<arr.length-1;i++){
            boolean flag = true;

            for(int j = 0;j<arr.length-1-i;j++){
                if(arr[j+1]<arr[j]){
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }
    private static void bubbleSort2(int[]arr){
        int bound = arr.length-1;
        int lastSwapIndex = 0;
        for(int i = 0;i<arr.length-1;i++){
            boolean flag = true;

            for(int j = 0;j<bound;j++){
                if(arr[j+1]<arr[j]){
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    lastSwapIndex = j;
                    flag = false;
                }
            }
            bound = lastSwapIndex;
            if(flag||lastSwapIndex == 0){
                break;
            }
        }
    }

    private static void insertSort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            int temp = arr[i];
            int j;
            for(j = i-1;j>=0&&arr[j]>temp;j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }

    private static void selectSort(int[]arr){
        for(int i = 0;i<arr.length-1;i++){
            int min = i;
            int j;
            for(j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            if(min!=i){
                //注意是arr[min]与arr[i]交换
                arr[min]^=arr[i];
                arr[i] ^= arr[min];
                arr[min] ^= arr[i];
            }
        }
    }

    private static void mergeSortion(int[] arr){
        int left = 0;
        int right = arr.length-1;
        mergeSort(arr,left,right);

    }
    private static void mergeSort(int[]arr ,int left,int right){
        if(left<right){
            int mid = (left+right)>>1;
            //归并左边
            mergeSort(arr,left,mid);
            //归并右边
            mergeSort(arr,mid+1,right);

            merge(arr,left,mid,right );
        }
    }
    private static void merge(int[]arr,int left,int mid,int right){
        //创建一个原数组长度的临时数组
        int[]temp = new int[arr.length];
        //定义三个指针
        int p1 = left;
        int p2 = mid+1;
        int p3 = left;
        //左右两边都不为空
        while(p1<=mid&&p2<=right){
            if(arr[p1]<=arr[p2]){
                temp[p3++] = arr[p1++];
            }else {
                temp[p3++] = arr[p2++];
            }
        }
        //左边为空
        while(p2<=right){
            temp[p3++] = arr[p2++];
        }

        //右边为空
        while(p1<=mid){
            temp[p3++] = arr[p1++];
        }

        //复制数组数据
        //注意是[left,right]
        for(int i = left;i<=right;i++){
            arr[i] = temp[i];
        }



    }




}
