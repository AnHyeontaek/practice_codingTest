package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> timeMap = new HashMap<>();
        String time = "";
        int count = 0;
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            time = br.readLine();
            String[] tmpArr = time.split(" ");
            if(Integer.parseInt(tmpArr[0]) == Integer.parseInt(tmpArr[1])){
                count++;
                continue;
            }
            timeMap.computeIfPresent(Integer.parseInt(tmpArr[0]),(k,v) ->  Math.min(v,Integer.parseInt(tmpArr[1])));
            timeMap.computeIfAbsent(Integer.parseInt(tmpArr[0]), v -> Integer.parseInt(tmpArr[1]));
        }

        while(!timeMap.isEmpty()){
            int min = Collections.min(timeMap.values());
            timeMap.entrySet().removeIf(entry -> entry.getKey() < min );
            count++;
        }
        System.out.println(count);
    }
}
