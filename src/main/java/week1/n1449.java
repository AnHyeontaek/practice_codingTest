package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String[] strArr = str.split(" ");
        int[] numArr = new int[n];

        for(int i = 0; i < strArr.length; i++){
            numArr[i] = Integer.parseInt(strArr[i]);
        }
    }
}
