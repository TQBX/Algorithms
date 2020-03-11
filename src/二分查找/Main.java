package 二分查找;

import com.sun.tracing.dtrace.StabilityLevel;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;

/**
 * @auther Summerday
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 7, 12, 23, 43, 45, 54, 65, 87, 99, 100};
        int index = binarySearch2(arr, 3);
        System.out.println(index);
    }

    private static int[] initRandomArray(int len) {

        int[] arr = new int[len];

        Random random = new Random();

        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }


    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        if(target<arr[low]||target>arr[high]){
            return -1;
        }
        int mid;

        while (low <= high) {
            System.out.println("----");
            mid = (low + high) >> 1;

            if (target < arr[mid]) {
                high = mid - 1;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }

        }
        return -1;
    }

    private static int binarySearch2(int[]arr, int target){
        int low = 0;
        int high = arr.length-1;
        if(target< arr[low]||target>arr[high]){
            return -1;
        }else {
            return binarySearch22(arr,target,low,high);
        }
    }
    private static int binarySearch22(int[] arr, int target,int low,int high) {
        int mid = (low+high)>>1;
        if(arr[mid] == target){
            return mid;
        }else if(arr[mid]>target){
            return binarySearch22(arr,target,low,mid-1);
        }else{
            return binarySearch22(arr,target,mid+1,high);
        }

    }
}
