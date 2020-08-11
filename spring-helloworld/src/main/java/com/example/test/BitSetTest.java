package com.example.test;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Author cbjun
 * @create 2020/8/11 10:39
 */
//问题：有0-9十个数，创建在0-9范围内的5个随机数，如何得到0-9之间不在随机数中的数
//方法：bitset位图
public class BitSetTest {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        // 创建5个随机数，每个数字再0-10范围内(左闭右开)
        for (int i = 0; i < 5; i++) {
            list.add(random.nextInt(10));
        }
        System.out.println("产生的随机数为：" + list);
        // 使用bitset,无参默认是64bit，参数表示你想放置的数字范围
        BitSet bitSet = new BitSet(10);
        for (int i = 0; i < list.size(); i++) {
            //bitset中只有0、1两个数字，表示false、true
            // 使用set方法，向开辟的10个bit位放元素，如将4放入，则在位图中存放形式为：
            // 0000100000
            bitSet.set(list.get(i));
        }
        // 遍历10位bit，对bit位为0的进行输出（0表示无元素，1表示有元素）
        for (int i = 0; i < 10; i++) {
            // 用get函数，boolean get(int index)，false表示为0
            if (!bitSet.get(i)) {
                System.out.println(i);
            }
        }
        unRepeatNum();
    }

    //问题：创建出10个不重复的随机数
    public static void unRepeatNum() {
        //思路一：bitset（不属于集合类），省内存
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        BitSet bitSet = new BitSet(10);
        while (list.size() < 10) {
            int num = random.nextInt(10);
            if (!bitSet.get(num)) {
                bitSet.set(num);
                list.add(num);
            }
        }
        list.sort(Comparator.naturalOrder());
        System.out.println("随机数" + list);
        //思路二：set集合，代码简洁
        Set<Integer> set = Sets.newHashSet();
        while(set.size()<10){
            set.add(random.nextInt(10));
        }
        System.out.println("随机数"+set);
    }
}

