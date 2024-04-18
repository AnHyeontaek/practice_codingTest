package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
동쪽 - 윗 -> 왼쪽면 -> 바닥면 -> 오른쪽 면 -> 윗면
서쪽 - 윗 -> 오 -> 바닥 -> 왼 -> 윗
남쪽 - 윗 -> 뒷 -> 바닥 -> 앞 -> 윗
북쪽 - 윗 -> 앞 -> 바닥 -> 뒷 -> 윗
*/

public class n14499 {
    static int n, m, x, y, k;
    static int[][] board;
    static int[] dice = {0, 0, 0, 0, 0, 0}; // 다이스 배열 [윗, 바닥, 앞, 뒤, 왼, 오]
    static int[] dx = {0, 0, -1, 1}; // 1 2 3 4
    static int[] dy = {1, -1, 0, 0}; // 동 서 북 남
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        while(k-- > 0){
            int d = Integer.parseInt(st.nextToken()) - 1;

            x = x + dx[d];
            y = y + dy[d];

            if(x >= 0 && y >= 0 && x < n && y < m){
                switch (d+1){
                    case 1:
                        moveRight();
                        break;
                    case 2:
                        moveLeft();
                        break;
                    case 3:
                        moveTop();
                        break;
                    case 4:
                        moveBot();
                        break;
                }
                if(board[x][y] == 0){
                    board[x][y] = dice[1];
                } else{
                    dice[1] = board[x][y];
                    board[x][y] = 0;
                }
            } else{
                x = x - dx[d];
                y = y - dy[d];
                continue;
            }
            System.out.println(dice[0]);
        }
    }

    //동쪽 - 윗 -> 왼쪽면 -> 바닥면 -> 오른쪽 면 -> 윗면
    //다이스 배열 [윗, 바닥, 앞, 뒤, 왼, 오]
    static void moveRight(){
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = tmp;
    }

    // 서쪽 - 윗 -> 오 -> 바닥 -> 왼 -> 윗
    static void moveLeft(){
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = tmp;
    }

    // 북쪽 - 윗 -> 앞 -> 바닥 -> 뒷 -> 윗
    static void moveTop(){
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = tmp;
    }

    // 남쪽 - 윗 -> 뒷 -> 바닥 -> 앞 -> 윗
    static void moveBot(){
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = tmp;
    }
}
