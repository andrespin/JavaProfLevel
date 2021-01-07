package Task1;
/*
1. Создать три потока, каждый из которых выводит определенную букву
(A, B и C) 5 раз (порядок – ABСABСABС).
 Используйте wait/notify/notifyAll.
 */


public class Main {

    private final Object obj = new Object();
    private volatile char currentLetter = 'A';
    private int n = 5;

    public static void main(String[] args) {

        Main m = new Main();
        new Thread(m::printA).start();
        new Thread(m::printB).start();
        new Thread(m::printC).start();

    }

    private void printA() {
        synchronized (obj) {
            try {
                for (int i = 0; i < n; i++) {
                    while (currentLetter != 'A') {
                        obj.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized (obj) {
            try {
                for (int i = 0; i < n; i++) {
                    while (currentLetter != 'B') {
                        obj.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC() {
        synchronized (obj) {
            try {
                for (int i = 0; i < n; i++) {
                    while (currentLetter != 'C') {
                        obj.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
