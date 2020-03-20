package 堆排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @auther Summerday
 */
public class Heap<E extends Comparable<E>> {
    //堆排序使用二叉堆，将所有元素添加到一个堆上，不断移除最大的元素以获得一个排好序的线性表

    /*

        二叉堆：- 完全二叉树+每个节点>=任意一个孩子

            插入键和删除根节点，执行效率很高。

           对于任意位置i，其父节点为(i-1)/2 左节点为2*i+1 右节点为2*i+2
     */


    /*
      数组实现二叉堆
     */

    private ArrayList<E> list = new ArrayList<>();

    //创建一个空堆
    public Heap(){

    }
    //传入数组，构造堆
    public Heap(E[] objects){
        list.addAll(Arrays.asList(objects));
    }

    /*
     * 添加新元素
     */
    void add(E newObject){
        //先将节点添加到末尾
        list.add(newObject);
        int currIndex = list.size()-1;
        //如果不是第一个，再进行比较
        while(currIndex>=1){
            //其父节点为(i-1)/2
            int parentIndex = (currIndex-1)/2;
            //只要当前比父节点大，就交换
            if(list.get(currIndex).compareTo(list.get(parentIndex))>0){

                E temp = list.get(currIndex);
                list.set(currIndex,list.get(parentIndex));
                list.set(parentIndex,temp);
            }
            else {
                break;
            }
            currIndex = parentIndex;
        }
    }

    /*
    移除根节点
     */
    E remove(){

        if(list.size() == 0) return null;

        E removaObject = list.get(0);
        //将尾节点作为根
        list.set(0,list.get(list.size()-1));
        //移除尾节点
        list.remove(list.size()-1);
        int currIndex = 0;
        while(currIndex<list.size()){

            int leftChildIndex = 2*currIndex+1;
            int rightChildIndex = 2*currIndex+2;

            //如果只有一个元素
            if(leftChildIndex>=list.size() ) break;

            //找到相对比较大的子节点
            int maxIndex = leftChildIndex;
            if(rightChildIndex<list.size()){
                if(list.get(maxIndex).compareTo(list.get(rightChildIndex))<0){
                    maxIndex = rightChildIndex;
                }

            }
            if(list.get(currIndex).compareTo(list.get(maxIndex))<0){
                E temp = list.get(currIndex);
                list.set(currIndex,list.get(maxIndex));
                list.set(maxIndex,temp);
                currIndex = maxIndex;
            }
            else break;
        }
        return removaObject;
    }
    public int getSize(){
        return list.size();
    }
}




