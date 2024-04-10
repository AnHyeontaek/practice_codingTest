package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class n2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String[] strArr = str.split(" ");
        int[] numArr = new int[n];

        for(int i = 0; i < strArr.length; i++){
            numArr[i] = Integer.parseInt(strArr[i]);
        }

        int low = 0;
        int high = numArr.length - 1;
        int sum;
        Arrays.sort(numArr);

        while(low != high){
            sum = numArr[low] + numArr[high];
            map.put(Math.abs(sum), numArr[low] + " " + numArr[high]);

            if(sum == 0){
                break;
            } else if(sum > 0){
                high--;
            } else{
                low++;
            }
        }
        System.out.println(map.get(Collections.min(map.keySet())));
    }
}
