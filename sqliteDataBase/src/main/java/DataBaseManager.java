
import java.sql.*;
import java.util.Random;

public class DataBaseManager {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    protected void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/goodsDataBase");
        stmt = connection.createStatement();
    }

    protected void createTable(String tableName ,String colomn1, String colomn2, String colomn3) throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS \n" +
                tableName + "(\n" +
                colomn1 + "\n integer PRIMARY KEY,\n" +
                colomn2 + "\n VARCHAR NOT NULL,\n" +
                colomn3 + "\n int NOT NULL" +
                ");");
    }

    protected void clearTable(String tableName ) throws SQLException {
        if(findTable(tableName)){
            stmt.execute("DELETE FROM \n" +
                    tableName);
            System.out.println("Таблица " + tableName + " очищена");
        } else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    protected void insertTable(String tableName, int quantity ) throws SQLException {
        if (findTable(tableName)){
            connection.setAutoCommit(false);
            for (int i = 0; i < quantity; i++) {
                int randomPrice = 1000 + new Random().nextInt(1000);
                stmt.addBatch("INSERT INTO \n"
                        + tableName + "\n (good_namevarchar, good_priceint) VALUES ('productName" + (i+1) + "', " + randomPrice + ")");
            }
            stmt.executeBatch();
            connection.setAutoCommit(true);
            System.out.println("Таблица " + tableName + " заполнена");
        } else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    protected void findProductPrice(String tableName, String s) throws SQLException {
        if (findTable(tableName)){
            ResultSet rs = stmt.executeQuery("SELECT good_priceint FROM " +
                    tableName +
                    " WHERE good_namevarchar = '" + s + "';");
            if (rs.next()) {
                System.out.println("Цена продукта : " + rs.getInt(1));
            }
            else {
                System.out.println("Продукт не найден!");
            }
        }else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    public void replaceProductPrice(String tableName, String productName, String price) throws SQLException {
        if (findTable(tableName)){
            if (findProductName(tableName, productName )){
                stmt.executeUpdate("UPDATE " +
                        tableName +
                        " SET good_priceint = '" + price + "' WHERE good_namevarchar = '" + productName + "';");
                System.out.println("цена у продукта с именем " + productName + " изменена, " + " новая цена: " + returnProductPrice(tableName, productName));
            }else{
                System.out.println("Продукт с именем " + productName + " не найден");
            }
        }else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    protected void findGoodsInPriceRange(String tableName ,int priceFrom, int priceTo) throws SQLException {
       if (findTable(tableName)){
           ResultSet rs = stmt.executeQuery("SELECT good_namevarchar, good_priceint FROM" +
                   tableName +
                   " WHERE good_priceint >= '" + priceFrom + "' and good_priceint <= '" + priceTo + "';");
           while (rs.next()) {
               System.out.println(rs.getString("good_namevarchar") + " | " + rs.getInt(2));
           }
       }else{
           System.out.println("Таблица " + tableName + " не найдена");
       }
    }

    private boolean findTable(String tableName ) throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            if (tableName.equals(rs.getString(3))){
               return true;
            }
        }
     return false;
    }

    private boolean findProductName(String tableName, String productName ) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT good_priceint FROM " +
                tableName +
                " WHERE good_namevarchar = '" + productName + "';");
        return rs.next();
    }

    private int returnProductPrice(String tableName, String s) throws SQLException {
        if (findTable(tableName)){
            ResultSet rs = stmt.executeQuery("SELECT good_priceint FROM " +
                    tableName +
                    " WHERE good_namevarchar = '" + s + "';");
            if (rs.next()) {
                return rs.getInt(1);
            }
        }else{
            System.out.println("Таблица " + tableName + " не найдена");
            return -1;
        }
        return -1;
    }
}
