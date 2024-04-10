package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String numArr = br.readLine();
        String[] splitArr = numArr.split(" ");

        int[] arr = new int[n];
        int sum = 0;

        // 1. 선택 절차
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(splitArr[i]);
        }
        Arrays.sort(arr);

        // 2. 적절성 검사
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j <= i; j++){
                sum += arr[j];
            }
        }
        // 3. 해답 검사
        System.out.println(sum);
    }
}
