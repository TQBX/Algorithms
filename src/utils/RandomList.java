package utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * @auther Summerday
 */
public class RandomList<T> {
    private ArrayList<T> storage = new ArrayList<>();
    private Random rand = new Random(47);

    public void add(T item) {//新增对象
        storage.add(item);
    }

    public T select() {//获取对象
        return storage.get(rand.nextInt(storage.size()));
    }

    public static void main(String[] args) {//测试
        RandomList<String> rs = new RandomList<>();
        for (String s : ("The quick brown fox jumped over "
                + "the lazy brown dog").split(" "))
            rs.add(s);
        for (int i = 0; i < 11; i++)
            System.out.print(rs.select() + " ");
    }

}
