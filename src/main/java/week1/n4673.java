package week1;

import java.util.ArrayList;
import java.util.List;

public class n4673 {
    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for(int i=1; i<10000; i++){
            num.add(i + cal(i));
            if(!num.contains(i)){
                result.add(i);
            }
        }
        for(int i=0; i<result.size(); i++){
            System.out.println(result.get(i));
        }
    }
    public static int cal(int n){
        int result = 0;
        while(n>0){
            result += n%10;
            int mok = n/10;
            n = mok;
        }
        return result;
    }
}