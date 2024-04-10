package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class n1251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        List<String> result = new ArrayList<>();

        for(int i = 1; i < word.length(); i++){
            for(int j = i+1; j < word.length(); j++){
                StringBuilder sb1 = new StringBuilder(word.substring(0,i)); // substring(0,1) --> 0번 index만 출력
                StringBuilder sb2 = new StringBuilder(word.substring(i,j)); // substring(1,3) --> 1,2번 index 출력
                StringBuilder sb3 = new StringBuilder(word.substring(j));

                String str1 = sb1.reverse().toString();
                String str2 = sb2.reverse().toString();
                String str3 = sb3.reverse().toString();

                result.add(str1 + str2 + str3);
            }
        }
        System.out.println(Collections.min(result));
    }
}
