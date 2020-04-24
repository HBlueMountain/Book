package com.book.test;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * 测试 ThreadLocal jdk中的工具类
 * Created by YongXin Xue on 2020/04/24 16:59
 */
public class ThreadLocalTest {

    public static Map<String, Integer> map =  new Hashtable<>();
    //ThreadLocal 的key就是当前线程
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    //获取随机数对象
    public static Random random = new Random();

    /**
     * 自定义线程工作类
     */
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            //获取当前线程,然后关联数据,保存到map
            String name = Thread.currentThread().getName();
            //获取1~100内的随机数
            int i = random.nextInt(100);
            System.out.println("当前线程名:" + name + " 随机生成的数:" +i);
            //以线程名作为key,关联的数据作为value保存到map中
            ////map.put(name, i);

            //将 i copy 到当前线程中
            threadLocal.set(i);

            //让线程休眠一会
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //////在当前线程工作的时候就调用saveOrder()这个线程/////////////////
            new OrderService().saveOrder();
            ////////////////////////////////////////////////////////////////

            //线程快结束就取出数据
            ///Integer integer = map.get(name);

            //可以 (不需要线程名) 直接取出来
            Integer integer = threadLocal.get();

            //在线程结束的时候以当前线程作为名取出关联的数据,查看数据和当前数据是否一致
            System.out.println(" 当前线程名为: " + name + " , 随机生成的数据是: " + i);
        }

    }

    /**
     * [注意]: 线程一旦关联后在任何位置都可以取出来
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyTask()).start();
        }

    }
}
class OrderService{
    public void saveOrder(){
        //获取当前线程
        String name = Thread.currentThread().getName();
        ///Integer integer = ThreadLocalTest.map.get(name);
        Integer integer = ThreadLocalTest.threadLocal.get();
        System.out.println("OrderService 当前线程名:" + name + " 随机生成的数:" + integer);

        //调用一下两个线程关联数据
        new OrderDao().saveDao();
        new OrderItem().saveItem();

    }
}
class OrderDao{
    public void saveDao(){
        //获取当前线程
        String name = Thread.currentThread().getName();
        Integer integer = ThreadLocalTest.threadLocal.get();
        System.out.println("OrderService 当前线程名:" + name + " 随机生成的数:" + integer);
    }
}
class OrderItem{
    public void saveItem(){
        //获取当前线程
        String name = Thread.currentThread().getName();
        Integer integer = ThreadLocalTest.threadLocal.get();
        System.out.println("OrderService 当前线程名:" + name + " 随机生成的数:" + integer);
    }
}