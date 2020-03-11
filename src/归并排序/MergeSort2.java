package 归并排序;

import java.util.Arrays;

/**
 * @auther Summerday
 * 方式2
 */
public class MergeSort2 {

    public static void main(String[] args) {

        int[] arr = {2,3,1,4,5,6,3,2,52,3,1};
        mergeSort(arr);
        show(arr);
    }
    public static void mergeSort(int[]arr){
        int low = 0;
        int high = arr.length-1;
        mergeSort1(arr,low,high);
    }

    private static void mergeSort1(int[] arr, int low, int high) {
        if(low<high){
            int mid = (low+high)>>1;
            mergeSort1(arr,low,mid);
            mergeSort1(arr,mid+1,high);
            merge(arr,low,mid,high);
        }

    }
    // [low,mid] + [mid+1,high]
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[arr.length];

        int p1 = low;
        int p2 = mid+1;
        int p3 = low;

        while(p1<=mid&& p2<=high){
            if(arr[p1]<=arr[p2]){
                temp[p3++] = arr[p1++];
            }else{
                temp[p3++] = arr[p2++];
            }
        }
        //p2空了
        while(p1<= mid) {
            temp[p3++] = arr[p1++];
        }
        while(p2<=high){
            temp[p3++] = arr[p2++];
        }
        for(int i = low;i<=high;i++){
            arr[i] = temp[i];
        }
    }

    private static void show(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}
