package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n2578 {
    static int[][] checkBoard = new int[5][5];
    static Map<Integer,int[]> board = new HashMap<>();
    static List<Integer> callNum;
    static int cnt;
    static int totalCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                board.put(Integer.parseInt(st.nextToken()), new int[] {i, j});
            }
        }

        callNum = new ArrayList<>(25);

        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                callNum.add(Integer.parseInt(st.nextToken()));
            }
        }
        bingo();
    }

    static void bingo(){
        for(int i = 0; i < callNum.size(); i++){
            int num = callNum.get(i);

            int[] tmp = board.get(num);
            checkBoard[tmp[0]][tmp[1]] = -1;

            checkBingo();
            if(totalCnt > 2){
                System.out.println(i + 1);
                return;
            }

        }
    }

    static void checkBingo() {
        totalCnt = 0;
        cnt = 0;
        for(int i = 0; i < 5; i++){
            // 가로 체크
            for(int j = 0; j < 5; j++){
                if(checkBoard[i][j] == -1){
                    cnt++;
                }
            }

            if(cnt == 5){
                totalCnt++;
            }
            cnt = 0;
            //세로 체크
            for(int j = 0; j < 5; j++){
                if(checkBoard[j][i] == -1){
                    cnt++;
                }
            }
            if(cnt == 5){
                totalCnt++;
            }
            cnt = 0;
        }
        if(checkBoard[0][0] == -1 && checkBoard[1][1] == -1 && checkBoard[2][2] == -1 && checkBoard[3][3] == -1 && checkBoard[4][4] == -1){
            totalCnt++;
        }
        if(checkBoard[0][4] == -1 && checkBoard[1][3] == -1 && checkBoard[2][2] == -1 && checkBoard[3][1] == -1 && checkBoard[4][0] == -1){
            totalCnt++;
        }
    }
}
