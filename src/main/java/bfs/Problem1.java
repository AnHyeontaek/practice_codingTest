package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Problem1 {
    static ArrayList<Integer>[] list;
    static Queue<Integer> queue;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        String[] flArr = firstLine.split(" ");

        int N = Integer.parseInt(flArr[0]);
        int M = Integer.parseInt(flArr[1]);
        int V = Integer.parseInt(flArr[2]);

        list = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];

        for(int i = 0 ; i < list.length; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            String tmp = br.readLine();
            String[] tmpArr = tmp.split(" ");
            list[Integer.parseInt(tmpArr[0])].add(Integer.parseInt(tmpArr[1]));
            list[Integer.parseInt(tmpArr[1])].add(Integer.parseInt(tmpArr[0]));
        }

        for(int i = 0; i < list.length; i++){
            Collections.sort(list[i]);
        }
        dfs(V);
        isVisited = new boolean[N + 1];
        System.out.println();
        bfs(V);

    }
    public static void dfs(int index){
        isVisited[index] = true;
        System.out.print(index + " ");
        for(int i : list[index]){
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }

    public static void bfs(int index){
        isVisited[index] = true;
        queue = new LinkedList<>();
        queue.add(index);

        while(!queue.isEmpty()){
            int num = queue.poll();
            System.out.print(num + " ");

            for(int i : list[num]){
                if(!isVisited[i]){
                    queue.add(i);
                    isVisited[i] = true;
                }
            }
        }
    }
}
