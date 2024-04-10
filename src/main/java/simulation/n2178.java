package simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class n2178 {

    static LinkedList<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int docsNum = Integer.parseInt(st.nextToken());
            int findNum = Integer.parseInt(st.nextToken());

            queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < docsNum; i++){
                queue.offer(new int[] {i, Integer.parseInt(st.nextToken())});
            }

            int count = 0;

            while(!queue.isEmpty()){
                int[] first = queue.poll();
                boolean isMax = true;

                for(int i = 0; i < queue.size(); i++){
                    if(first[1] < queue.get(i)[1]){
                        queue.offer(first);

                        for(int j = 0; j < i; j++){
                            queue.offer(queue.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                if(!isMax){
                    continue;
                }

                count++;
                if(first[0] == findNum){
                    sb.append(count).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
