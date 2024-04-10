package week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class n1065_not_resolved {
    public static void main(String[] args) {
        List<Integer> tmp = new ArrayList<>();
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n>0){
            tmp.add(n%10);
            n /= 10;
        }

        for(int i = 0; i < tmp.size() - 1; i++){
            result = tmp.get(i+1) - tmp.get(i);
        }
    }
}
