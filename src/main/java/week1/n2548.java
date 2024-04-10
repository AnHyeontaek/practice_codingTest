package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n2548 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String numbers = br.readLine();
        String[] tmp = numbers.split(" ");

        int[] arr = new int[n];
        Map<Integer,Integer> map = new HashMap<>();

        int min = 200000000;
        int sum = 0;

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(tmp[i]);
        }

        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++){
            for(int j=0; j<arr.length; j++){
                sum += Math.abs(arr[i] - arr[j]);
            }

            if(map.containsKey(sum))
                map.put(sum,Math.min(map.get(sum),arr[i]));
            else
                map.put(sum,arr[i]);

            min = Math.min(sum, min);
            sum = 0;
        }

        System.out.println(map.get(min));
    }
}
