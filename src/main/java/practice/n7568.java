package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n7568 {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++){
            int rank = 1;

            for(int j = 0; j < n; j++){
                if(i == j) continue;

                if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]){
                    rank++;
                }
            }
            System.out.print(rank + " ");
        }
    }
}
