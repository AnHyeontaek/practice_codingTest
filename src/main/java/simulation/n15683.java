package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n15683 {
    static int n, m;
    static int result = Integer.MAX_VALUE;
    static int[][] board;
    static int[][] copyBoard;
    static int[] output;
    static List<Cctv> cctvList;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        cctvList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] != 0 && board[i][j] != 6){
                    cctvList.add(new Cctv(i, j, board[i][j]));
                }
            }
        }
        output = new int[cctvList.size()];
        backTracking(0, cctvList.size());

        System.out.println(result);
    }

    static void backTracking(int depth, int r){
        if(depth == r){
            copyBoard = new int[n][m];
            for(int i = 0; i < board.length; i++){
                System.arraycopy(board[i], 0, copyBoard[i], 0, board[i].length);
            }

            for(int i = 0; i < cctvList.size(); i++){
                direction(cctvList.get(i), output[i]);
            }
            getBoardSize();

            return;
        }

        for(int i = 0; i < 4; i++){
            output[depth] = i;
            backTracking(depth + 1, r);
        }
    }

    static void direction(Cctv cctv, int d){
        int cctvNum = cctv.type;

        switch (cctvNum){
            case 1:
                if(d == 0) dfs(cctv, 0); // 우
                else if (d == 1) dfs(cctv, 1); // 하
                else if (d == 2) dfs(cctv, 2); // 좌
                else dfs(cctv, 3); // 하
                break;

            case 2:
                if (d == 0 || d ==2){
                    dfs(cctv,0);dfs(cctv, 2);
                }
                else{
                    dfs(cctv, 1);
                    dfs(cctv, 3);
                }
                break;

            case 3:
                if (d == 0){
                    dfs(cctv, 0);
                    dfs(cctv, 1);
                } else if (d == 1) {
                    dfs(cctv, 1);
                    dfs(cctv, 2);
                } else if (d == 2) {
                    dfs(cctv, 2);
                    dfs(cctv, 3);
                } else{
                    dfs(cctv, 3);
                    dfs(cctv, 0);
                }
                break;

            case 4:
                if(d == 0){
                    dfs(cctv, 0);
                    dfs(cctv, 1);
                    dfs(cctv, 2);
                } else if (d == 1) {
                    dfs(cctv, 1);
                    dfs(cctv, 2);
                    dfs(cctv, 3);
                } else if (d == 2) {
                    dfs(cctv, 2);
                    dfs(cctv, 3);
                    dfs(cctv, 0);
                } else{
                    dfs(cctv, 3);
                    dfs(cctv, 0);
                    dfs(cctv, 1);
                }
                break;

            case 5:
                dfs(cctv, 0);
                dfs(cctv, 1);
                dfs(cctv, 2);
                dfs(cctv, 3);
                break;
        }
    }

    static void dfs(Cctv cctv, int d){
        Queue<Cctv> cctvQueue = new LinkedList<>();
//        boolean[][] isVisited = new boolean[n][m];

        cctvQueue.offer(cctv);
//        isVisited[cctv.x][cctv.y] = true;

        while(!cctvQueue.isEmpty()){
            Cctv cctv1 = cctvQueue.poll();
            int nx = cctv1.x + dx[d];
            int ny = cctv1.y + dy[d];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m || copyBoard[nx][ny] == 6){
                break;
            }

            if(copyBoard[nx][ny] == 0){
                copyBoard[nx][ny] = -1; // 체크 지역
                cctvQueue.add(new Cctv(nx, ny, cctv.type));
            } else{
                cctvQueue.add(new Cctv(nx, ny, cctv.type));
            }
        }
    }

    static void getBoardSize(){
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(copyBoard[i][j] == 0){
                    count++;
                }
            }
        }
        result = Math.min(result, count);
    }

    static class Cctv{
        int x;
        int y;
        int type;

        public Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
