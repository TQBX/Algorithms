package 堆排序;

import utils.RandomList;

import java.util.List;

/**
 * @auther Summerday
 */
public class HeapSort {

    public static <E extends Comparable<E>> void heapSort(E[] arr) {
        Heap<E> heap = new Heap<>();

        for (E e : arr) {
            heap.add(e);
        }
        for(int i = arr.length-1;i>=0;i--){
            arr[i] = heap.remove();
        }

    }


    public static void main(String[] args) {
        Integer[] arr = {-44,-5,-3,2,3,21,43,21,3,1,0,5,-6,-0};
        heapSort(arr);
        for(Integer i:arr){
            System.out.println(i);
        }

    }
}
