package 快速排序;

import utils.ArrayUtils;

/**
 * @auther Summerday
 */
public class QuickSort {


    public static void main(String[] args){
        int[] arr = ArrayUtils.getRandomArray(10, 100, false);
        ArrayUtils.show(arr);
        quickSort(arr);
        ArrayUtils.show(arr);
    }

    //用户接口
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    //快速排序，分而治之
    private static void quickSort(int[] arr, int start, int end) {
        if(end>start){
            //通过partition方法获取pivot的位置
            int pivotIndex = partition(arr,start,end);
            quickSort(arr,start, pivotIndex);
            quickSort(arr,pivotIndex+1,end);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 基准
        int pivot = arr[low];
        while (low < high) {
            // 从后往前找到比pivot小（相等的情况忽略）的元素位置
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            // 将这个位置移到左边low阵营
            arr[low] = arr[high];
            // 从前向后找到比pivot大（相等的情况忽略）的元素
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            // 将这个位置移到右边high阵营
            arr[high] = arr[low];

        }
        // low==high相等，跳出循环 此时将pivot插入属于他的位置
        arr[low] = pivot;
        // 返回pivot的位置
        return low;
    }



    public static int[] qsort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i<j) {
            while ((i<j)&&(arr[j]>pivot)) {
                j--;
            }
            while ((i<j)&&(arr[i]<pivot)) {
                i++;
            }
            if ((arr[i]==arr[j])&&(i<j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }
        }
        if (i-1>start) arr=qsort(arr,start,i-1);
        if (j+1<end) arr=qsort(arr,j+1,end);
        return (arr);
    }
    //对特定的区间进行操作
    private static int getIndex(int[] arr, int start, int end) {
        int pivot = arr[start];
        int low = start+1;
        int high = end;
        while(high>low){
            //保证左边的数都<=基准
            while(low<=high&& arr[low]<=pivot){
                low++;
            }
            //保证右边的数都>基准
            while(low<=high&& arr[high]>pivot){
                high--;
            }
            //交换
            if(high>low){
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }
        }

        while(high>start && arr[high]>=pivot){
            high--;
        }
        //如果基准元素被移动，将返回基准元素的新下标
        if(pivot>arr[high]){
            arr[start] = arr[high];
            arr[high] = pivot;
            return high;
        }else{
            //基准元素未被移动，返回初始下标
            return start;
        }
    }

}
