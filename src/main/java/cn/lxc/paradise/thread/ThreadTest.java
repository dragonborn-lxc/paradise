package cn.lxc.paradise.thread;

public class ThreadTest {

    public static void main(String[] args) {
        MyThread mythread = new MyThread();
        mythread.run();
        new Thread(() -> {
            System.out.println("instant Runnable");
        }).start();
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("extends Thread");
    }

}
