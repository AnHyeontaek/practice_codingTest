package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
----> 공백 없는 문자열 int 배열로 변경
 */
public class n14891 {
    static List<Integer> chain1;
    static List<Integer> chain2;
    static List<Integer> chain3;
    static List<Integer> chain4;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 공백 없는 문자열 List로 삽입 (Stream 사용)
        chain1 = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());
        chain2 = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());
        chain3 = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());
        chain4 = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());

        k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int chainNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()); // 1 : 시계 / -1 : 반시계

            rotateChain(chainNum,direction);
        }
    }

    // 큐에 num dir 삽입
    static void rotateChain(int num, int dir){
        switch (num){
            case 1:
                if(dir == 1){
                    Collections.rotate(chain1,6);
                    if(chain1.get(2).equals(chain2.get(6))){
                        rotateChain(2, -1);
                    }
                } else if(dir == -1){
                    Collections.rotate(chain1, 0);
                    if(chain1.get(2).equals(chain2.get(6))){
                        rotateChain(2, 1);
                    }
                }

        }
    }
}
