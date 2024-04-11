package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine()); //1 1 0

            for(int j = 0; j < M; j++){
                int n = Integer.parseInt(st.nextToken());

                arr[i][j] = n;
            }
        }

        int count = 0;

        while(true){
            if(arr[r][c] == 0){
                arr[r][c] = 2;
                count++;
            }

            if(arr[r-1][c] == 0 || arr[r][c-1] == 0 || arr[r+1][c] == 0 || arr[r][c+1] == 0){
                switch (d){
                    case 0:
                        d = 3;
                        if(arr[r][c-1] == 1 || arr[r][c-1] == 2){
                            break;
                        }
                        else{
                            c -= 1;
                            break;
                        }

                    case 1:
                        d = 0;
                        if(arr[r-1][c] == 1 || arr[r-1][c] == 2){
                            break;
                        }
                        else{

                            r -= 1;
                            break;
                        }

                    case 2:
                        d = 1;
                        if(arr[r][c+1] == 1 || arr[r][c+1] == 2){
                            break;
                        }
                        else{
                            c += 1;
                            break;
                        }

                    case 3:
                        d =2;
                        if(arr[r+1][c] == 1 || arr[r+1][c] == 2){
                            break;
                        }
                        else{
                            r += 1;

                            break;
                        }
                }
            }
            else{
                switch (d){
                    case 0:
                        r += 1;
                        if(arr[r][c] == 1){
                            System.out.println(count);
                            return;
                        }
                        break;

                    case 1:
                        c -= 1;
                        if(arr[r][c] == 1){
                            System.out.println(count);
                            return;
                        }
                        break;

                    case 2:
                        r -= 1;

                        if(arr[r][c] == 1){
                            System.out.println(count);
                            return;
                        }

                        break;

                    case 3:
                        c += 1;

                        if(arr[r][c] == 1){
                            System.out.println(count);
                            return;
                        }
                        break;
                }
            }
        }

    }
}
