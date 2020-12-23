package Task2;

/*
2. Написать небольшой метод, в котором 3 потока построчно пишут данные в файл
(по 10 записей с периодом в 20 мс).
*/


import java.io.*;

public class Main {

    private static final Object obj = new Object();
    private static volatile char currentLetter = 'A';
    private static final String path = "file.txt";
    private static final int period = 20;
    private static boolean On = true;
    private int n = 5;


    public static void main(String[] args) {

        Main m = new Main();
        new Thread(m::currentOne).start();
        new Thread(m::currentTwo).start();
        new Thread(m::currentThree).start();

    }

    private static void readFile() {
        try (BufferedReader reader = new BufferedReader( new FileReader( path ))) {
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void fileWrite(String str) {
        File file = new File(path);

        try(FileWriter writer = new FileWriter(file, true)) {

            writer.write( "\n" + str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileWrite(String str, int Quantity) {
        File file = new File(path);

        try(FileWriter writer = new FileWriter(file, true)) {
            for (int i = 0; i < Quantity; i++) {
                writer.write( "\n" + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void currentOne() {
        synchronized (obj) {
            try {
                    while (currentLetter != 'A') {
                        obj.wait();
                    }
                    fileWrite("Thread 1", 10);
                    currentLetter = 'B';
                    obj.notifyAll();
                    Thread.sleep(period);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void currentTwo() {
        synchronized (obj) {
            try {
                    while (currentLetter != 'B') {
                        obj.wait();
                    }
                    fileWrite("Thread 2", 10);
                    currentLetter = 'C';
                    obj.notifyAll();
                    Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void currentThree() {
        synchronized (obj) {
            try {
                    while (currentLetter != 'C') {
                        obj.wait();
                    }
                    fileWrite("Thread 3",10);
                    currentLetter = 'A';
                    obj.notifyAll();
                    Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
