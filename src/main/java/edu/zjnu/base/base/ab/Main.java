package edu.zjnu.base.base.ab;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-11-11
 **/

/**
 *   抽象类不能实例化为什么可以写构造方法？
 *   1）抽象类不能被实例化（也就是不能直接new抽象类）,如果被实例化，就会报错，编译无法通过。
 *   只有抽象类的非抽象子类可以创建对象；
 *   2）抽象类中不一定包含抽象方法，但是有抽象类额方法一定是抽象类；
 *   3）抽象类中的抽象方法只是声明，不包含方法体，就是不给出方法的具体实现也就是方法的具体功能；
 *   4）构造方法，类方法（用static修饰的方法）不能声明为抽象方法；
 *   5）抽象方法的子类必须给出抽象方法的具体实现，除非改子类也是抽象方法
 *
 * @author SetsunaYang
 */
public class Main {

    public static void main(String[] args) {
        new Son();
    }
}
