package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
----> 공백 없는 문자열 int 배열로 변경
 */
public class n14891 {
//    static List<Integer>[] chainList;
    static List<List<Integer>> chainList;
    static int k, chainNum, direction, score;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        chainList = new ArrayList<>(4);
//        chainList[0] = new ArrayList<>();

        for(int i = 1; i < 5; i++){
//            chainList[i] = new ArrayList<>();
            List<Integer> tmpList = new ArrayList<>();

            String str = br.readLine();
            String[] splitStr = str.split("");
            int[] numArr = Arrays.stream(splitStr).mapToInt(Integer::parseInt).toArray();

            for(int j = 0; j < 8; j++){
//                chainList[i].add(numArr[j]);
                tmpList.add(numArr[j]);
            }
            chainList.add(tmpList);
        }

        k = Integer.parseInt(br.readLine());

        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            chainNum = Integer.parseInt(st.nextToken()) - 1;
            direction = Integer.parseInt(st.nextToken());
            checkRotate(chainNum,direction);
        }

        score = 0;

        if(chainList.get(0).get(0) == 1){
            score += 1;
        }
        if(chainList.get(1).get(0) == 1){
            score += 2;
        }
        if(chainList.get(2).get(0) == 1){
            score += 4;
        }
        if(chainList.get(3).get(0) == 1){
            score += 8;
        }
        System.out.println(score);
    }

    static void checkRotate(int num, int dir){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(num, dir);

        // 왼쪽으로 검사
        for(int i = num - 1; i >= 0; i--){
//            if(chainList[i].get(2) != chainList[i+1].get(6)){
            if(chainList.get(i).get(2) != chainList.get(i+1).get(6)){
                map.put(i, map.get(i+1) * -1);
            } else{
                break;
            }
        }
        //오른쪽으로 검사
        for(int i = num + 1; i <= 3; i++){
//            if(chainList[i].get(6) != chainList[i-1].get(2)){
            if(chainList.get(i).get(6) != chainList.get(i-1).get(2)){
                map.put(i, map.get(i-1) * -1);
            } else{
                break;
            }
        }
        doRotate(map);
    }

    static void doRotate(Map<Integer,Integer> map){
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int key = entry.getKey();

            if(map.get(key) == 1){
                Collections.rotate(chainList.get(key),1);
            } else if (map.get(key) == -1) {
                Collections.rotate(chainList.get(key), 7);
            }
        }
    }
}
