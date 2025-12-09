package com.itheima.Day3_code;

public class San_Yuan_yunsuanfu {
    public static void main(String[] args) {
        // 三元运算符
        // 格式：
        // 比较表达式？表达式1：表达式2；
        // 执行流程：
        // 首先计算比较表达式的值，看是true还是false
        // 如果是true，表达式1的值就是结果
        // 如果是false，表达式2的值就是结果
        int a = 10;
        int b = 20;
        int c = a + b;
        System.out.println(c);
        int d = (a > b) ? a : b;
        System.out.println(d);
        // 练习：
        // 定义两个变量，判断两个变量是否相等
        int e = 10;
        int f = (a > b) ? a : b;
        System.out.println(f);

    }
}
