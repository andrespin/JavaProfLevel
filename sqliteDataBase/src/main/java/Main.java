
/*
1. Подключиться к БД
2. Создать таблицу товаров (good_id, good_name, good_price) запрсом из Java приложения.
3. При запуске приложения очистить таблицу и заполнить 10000 товаров
4. Написать консольное приложение, которое позволяет узнать цену товара по его имени,
 либо вывести сообщение «Такого товара нет», если товар не обнаружен в базе.
5. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
6. Вывести товары в заданном ценовом диапазоне.
Вам понадобятся: SELECT, DELETE, WHERE, UPDATE, LIKE, BETWEEN
 */

// https://www.sqlitetutorial.net/sqlite-java/

import java.sql.*;

public class Main {

    private static DataBaseManager dataBaseManager = new DataBaseManager();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        goodsDataBaseProcessing();
    }

    // Подскажите как исправить данные баги
    private static void goodsDataBaseProcessing() throws SQLException, ClassNotFoundException {
        dataBaseManager.connection();
        // Вот тут при создании таблицы создаётся колонка "good_idINTEGER" вместо "good_id" (аналогично и с другими),
        // в результате в SQL-запросе далее приходится прописывать good_namevarchar, иначе ошибка - no such colomn
        dataBaseManager.createTable("goods","good_id", "good_name", "good_price");
        dataBaseManager.clearTable("goods");
        // Тут при заполнении таблицы не получилось заполнить good_id, постоянно вылетает ошибка при попытке это сделать
        dataBaseManager.insertTable("goods", 10000);
        dataBaseManager.findProductPrice("goods", "productName2");
        dataBaseManager.replaceProductPrice("goods", "productName2", "1000");
        // а тут как бы ни писал название таблицы (то ли good_namevarchar, то ли good_name) всегда вылетает ошибка:
        // [SQLITE_ERROR] SQL error or missing database (no such column: good_namevarchar)
        dataBaseManager.findGoodsInPriceRange("goods" ,1200, 1800);
    }

}
