package Task2;

import java.util.ArrayList;

public class ConvertClass<T> {

    public ArrayList<T> convertMassiveToArrayList(T [] mas){
        ArrayList<T> list = new ArrayList();
        for (T i: mas) {
            list.add(i);
        }
        return list;
    }

}
