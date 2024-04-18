package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n13460 {
    static int n, m;
    static char[][] board;
    static Map<String,int[]> map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북
    static boolean[][] visited;
    static Queue<Marvel> queue;

    static class Marvel{
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public Marvel(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        map = new HashMap<>();
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            board[i] = br.readLine().toCharArray();
        }

        Marvel marvel = new Marvel(0,0,0,0,0);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'R'){
                    marvel.redX = i;
                    marvel.redY = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    marvel.blueX = i;
                    marvel.blueY = j;
                    board[i][j] = '.';
                }
            }
        }
        System.out.println(bfs(marvel));
    }

    static int bfs(Marvel marvel){
        queue = new LinkedList<>();
        queue.offer(marvel);

        while(!queue.isEmpty()){
            Marvel mv = queue.poll();

            if(mv.count == 10){
                continue;
            }
            for(int i = 0; i < 4; i++){
                int redX = mv.redX;
                int redY = mv.redY;
                int blueX = mv.blueX;
                int blueY = mv.blueY;
                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동 및 체크
                while(true){
                    int newRedX = redX + dx[i];
                    int newRedY = redY + dy[i];

                    if(board[newRedX][newRedY] == '#'){
                        break;
                    }

                    if(board[newRedX][newRedY] == 'O'){
                        isRedHole = true;
                        break;
                    }

                    redX = newRedX;
                    redY = newRedY;
                }

                // 파란 구슬 이동 및 체크
                while(true){
                    int newBlueX = blueX + dx[i];
                    int newBlueY = blueY + dy[i];

                    if(board[newBlueX][newBlueY] == '#'){
                        break;
                    }

                    if(board[newBlueX][newBlueY] == 'O'){
                        isBlueHole = true;
                        break;
                    }

                    blueX = newBlueX;
                    blueY = newBlueY;
                }

                // 파란 구슬이 구멍에 들어갔다면 다음 큐로 진행
                if(isBlueHole){
                    continue;
                } else if (isRedHole) { // 빨간 구슬이 들어갔다면 카운트 값 리턴
                    return mv.count + 1;
                }

                // 두 구슬이 이동이 없을 때 큐에 넣지 않고 패스
                if(redX == mv.redX && redY == mv.redY && blueX == mv.blueX && blueY == mv.blueY){
                    continue;
                }

                // 두 구슬이 한 공간으로 겹쳤을 경우
                if(redX == blueX && redY == blueY){
                    if(i == 0){
                        if(mv.redY > mv.blueY) blueY--;
                        else redY--;
                    } else if (i == 1) {
                        if(mv.redX > mv.blueX) blueX--;
                        else redX--;
                    } else if (i == 2) {
                        if(mv.redY > mv.blueY) redY++;
                        else blueY++;
                    } else if (i == 3){
                        if(mv.redX > mv.blueX) redX++;
                        else blueX++;
                    }
                }

                queue.offer(new Marvel(redX, redY, blueX, blueY, mv.count + 1));
            }
        }

        return -1;
    }
}

