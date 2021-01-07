package Task3;

public class MultiFuncDev {

    private static int timeToSleep = 600;

    public synchronized void printDoc(Doc doc){

        System.out.print("\nРаспечатано : ");
        for (int i = 0; i < doc.getPages().size() ; i++) {
            if(doc.getDocName() != ""){
                System.out.print(doc.getPages().get(i) +
                        " , название документа " + doc.getDocName());
            } else{
                System.out.print(doc.getPages().get(i));
            }
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void scanDoc(Doc doc)  {
        System.out.print("\nОтсканировано : ");
        for (int i = 0; i < doc.getPages().size() ; i++) {
            if(doc.getDocName() != ""){
                System.out.print(doc.getPages().get(i) +
                        " , название документа " + doc.getDocName());
            } else{
                System.out.print( doc.getPages().get(i));
            }
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
