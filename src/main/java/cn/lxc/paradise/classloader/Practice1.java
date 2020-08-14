package cn.lxc.paradise.classloader;

/**
 * File Name: Practice1
 * General Description: 
 *      1.jvm中类加载是懒加载模型，所以用不到就不加载
 *      2.static String str = "super str";其实它是存储在Super的InstanceMirrorKlass中的，而Test_1_B中是没有str的，所以main中调用时调用的其实是A的str，B用不到自然就不用加载了。
 * Revision History:
 * Modification
 * Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 * ---------------------   ------------    ----------     -----------------------------
 * Le xing chen            2020/8/14
 */
public class Practice1 {

	public static void main(String[] args) {
		System.out.println(Sub.str);
	}
	
}

class Super {
	public static String str = "super str";
	
	static {
		System.out.println("super static block");
	}
}

class Sub extends Super {
	static {
		System.out.println("sub static block");
	}
}
