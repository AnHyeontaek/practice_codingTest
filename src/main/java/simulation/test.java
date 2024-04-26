package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(1);tmp.add(1);tmp.add(0);tmp.add(0);tmp.add(1);tmp.add(1);tmp.add(1);tmp.add(0);
        Collections.rotate(tmp, 7);

        for(int i : tmp){
            System.out.println(i);
        }
    }
}
