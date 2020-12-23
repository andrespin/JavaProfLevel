package Task3;

/*
3. Написать класс МФУ, на котором возможно одновременно выполнять печать и сканирование документов,
но нельзя одновременно печатать или сканировать два документа.
При печати в консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы»,
при сканировании – аналогично «Отсканировано...». Вывод в консоль с периодом в 50 мс.
 */


public class Main {

    String doc = "Doc";

    public static void main(String[] args) {

        MultiFuncDev device = new MultiFuncDev();

        Doc doc1 = new Doc(10, "Отчёт");
        Doc doc2 = new Doc(10, "Приказ");

        new Thread(() -> {
            device.printDoc(doc1);
            device.scanDoc(doc1);
        }).start();

        new Thread(() -> {
            device.printDoc(doc2);
            device.scanDoc(doc2);
        }).start();

    }
}
