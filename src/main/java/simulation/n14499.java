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
    static int startX, startY;
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

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == 0){
                    startX = i;
                    startY = j;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        while(k-- > 0){
            int d = Integer.parseInt(st.nextToken());

        }

    }

    //동쪽 - 윗 -> 왼쪽면 -> 바닥면 -> 오른쪽 면 -> 윗면
    //다이스 배열 [윗, 바닥, 앞, 뒤, 왼, 오]
    static int moveRight(){
        int tmp = board[startX][startY];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
    }
}
