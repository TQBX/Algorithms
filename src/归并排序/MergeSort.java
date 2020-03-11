package 归并排序;

import java.util.Arrays;

/**
 * @auther Summerday
 * 方式一
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2,3,1,4,5,6,3,2,52,3,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr){
        int len = arr.length;
        if(len >1){
            //处理前一半
            int[] firstArr = new int[len/2];
            //System.arraycopy(arr,0,firstArr,0,len/2);

            //拷贝前一半进辅助数组firstArr
            for(int i = 0;i<len/2;i++){
                firstArr[i] = arr[i];
            }
            mergeSort(firstArr);
            //处理另一半
            int secondArrLen = len-len/2;
            int[] secondArr = new int[secondArrLen];
            //拷贝后一半进辅助数组secondArr
            for(int i = 0;i<secondArrLen;i++){
                secondArr[i] = arr[i+len/2];
            }
            mergeSort(secondArr);
            //合并两个结果
            merge(firstArr,secondArr,arr);
        }
    }
    private static void merge(int[] firstArr, int[] secondArr, int[] arr) {
        int curr1 = 0;
        int curr2 = 0;
        int curr3 = 0;
        //两个数组都还有元素的时候，把更小的插入到arr中
        while(curr1<firstArr.length&& curr2<secondArr.length){
            if(firstArr[curr1]<secondArr[curr2]){
                arr[curr3++] = firstArr[curr1++];
            }else{
                arr[curr3++] = secondArr[curr2++];
            }
        }
        //此时secondArr已经空了，直接把firstArr的后面的全部填充到arr中
        while(curr1<firstArr.length){
            arr[curr3++] = firstArr[curr1++];
        }
        while(curr2<secondArr.length){
            arr[curr3++] = secondArr[curr2++];
        }
    }
}
