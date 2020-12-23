package Task3;

import java.util.ArrayList;

public class Doc {

    String docName = "";

    private static ArrayList<String> pages = new ArrayList<>();

    Doc(int pagesNumber){
        for (int i = 0; i < pagesNumber; i++) {
            pages.add(String.format("Страница %d", i));
        }
    }

    Doc(int pagesNumber, String docName){
        for (int i = 0; i < pagesNumber; i++) {
            pages.add(String.format(" Страница %d ", i));
        }
        this.docName = docName;
    }

    public static ArrayList<String> getPages() {
        return pages;
    }

    public String getDocName() {
        return docName;
    }

}
