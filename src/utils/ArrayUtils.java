package utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @auther Summerday
 */
public class ArrayUtils {

    public static void show(int[] arr){
        System.out.println(Arrays.toString(arr));
    }


    public static int[] getRandomArray(int len,int bound,boolean isSorted){

        if(len<=0) return null;
        int[] arr = new int[len];

        if(!isSorted) {

            Random random = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(bound);
            }
            return arr;
        }else{
            Random random = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(bound);
            }
            Arrays.sort(arr);
            return arr;
        }
    }







}
