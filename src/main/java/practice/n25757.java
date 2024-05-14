package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class n25757 {
    static int n;
    static char game;
    static Set<String> nameSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        game = st.nextToken().charAt(0);

        nameSet = new HashSet<>();
        while(n-- > 0){
            nameSet.add(br.readLine());
        }
        if(game == 'Y'){
            System.out.println(nameSet.size());
        } else if (game == 'F') {
            System.out.println(nameSet.size() / 2);
        } else if (game == 'O') {
            System.out.println(nameSet.size() / 3);
        }
    }
}
