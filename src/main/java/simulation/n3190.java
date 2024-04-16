package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n3190 {
    static int[][] appleArr;
    static int n, k, l;
    static List<int[]> list = new ArrayList<>();
    static Map<Integer,String> moveMap = new HashMap<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        appleArr = new int[n][n];
        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            appleArr[x][y] = 1;
        }

        l = Integer.parseInt(br.readLine());

        while(l-- > 0){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String turn = st.nextToken();
            moveMap.put(time,turn);
        }

        snake();
    }

    public static void snake(){
        int time = 0;
        int cx = 0, cy = 0;
        int d = 0;
        list.add(new int[]{0, 0});


        while(true){
            // 1. 시간재기
            time++;

            // 2. 뱀 이동하기
            cx = cx + dx[d];
            cy = cy + dy[d];

            // 3. 범위를 벗어나거나, 뱀 몸통을 만날 때 종료
            if(isFinished(cx, cy)){
                break;
            }

            // 4. 사과가 있을 때 없을 때 처리
            list.add(new int[] {cx , cy});

            if(appleArr[cx][cy] == 1){
                appleArr[cx][cy] = 0;

            }
            else{
                list.remove(0);
            }

            // 5. 방향 바꿔주는 시간 시 방향 변경
            if(moveMap.containsKey(time)){
                if(moveMap.get(time).equals("D")){
                    d = d + 1;
                    if(d > 3){
                        d = 0;
                    }
                }
                else{
                    d = d -1;
                    if(d < 0){
                        d = 3;
                    }
                }
            }

        }

        System.out.println(time);
    }

    public static boolean isFinished(int cx, int cy){
        if(cx < 0 || cy < 0 || cx >= n || cy >= n){
            return true;
        }

        for(int[] i : list){
            if(cx == i[0] && cy == i[1]){
                return true;
            }
        }
        return false;
    }
}
