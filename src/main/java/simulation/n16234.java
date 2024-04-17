package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 순회를 하며 방문하지 않은 노드를 방문한다. 이 과정은 모든 노드를 방문할 때까지 반복된다.

2. 노드를 방문할 때에는 BFS 탐색 알고리즘을 사용하여 구현한다. 이때 다음으로 이동 할 노드는 현재 노드의 값과의 차이가 L이상 R이하여야 한다.

3. 방문한 노드들을 차례대로 list에 넣어주고 노드 값의 합을 따로 저장해 둔다.

4. 모든 노드의 방문이 끝났다면 list에 넣어준 노드들의 인구이동을 시작한다. 이때 list의 크기가 1보다 커야 이동을 시작한다.

5. 이동 시에는 문제의 조건에 맞게 노드 값의 합을 노드의 사이즈로 나눈 값을 모든 노드에 변경시켜준다.

6. 1 ~ 6과정 동안 인구 이동이 일어난 적이 없다면 더 이상 이동할 수 있는 인구가 없으므로 순회를 멈추고 이때의 result값을 반환한다.
*/

public class n16234 {
    static int n, l, r;
    static int[][] arr; // 각 나라 인구 수
    static boolean[][] visited; // 방문 여부
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; //동 남 서 북
    static Queue<int[]> queue; // bfs에서 사용할 큐
    static List<int[]> list; // 연합이 가능한 노드를 저장할 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move());
    }

    static int move() { // 인구 이동에 걸리는 시간을 체크할 메소드
        int day = 0;
        while (true) {
            boolean isMove = false; // 인구 이동이 더 가능한지 체크하는 변수
            visited = new boolean[n][n]; // 인구이동이 계속 가능하면 방문 여부 초기화

            for (int i = 0; i < n; i++) { // 방문여부를 Full Scan
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) { // 방문하지 않은 노드라면 해당 노드부터 bfs탐색으로 연합이 가능한 노드 탐색
                        int sum = bfs(i, j);

                        if (list.size() > 1) { // 연합이 가능한 노드가 있다면 2 이상의 값, 없다면 1
                            calculate(sum); // 인구 이동 계산
                            isMove = true; // 인구 이동이 완료되었으므로 true로 변경하여 while 문을 한 번 더 순회
                        }
                    }
                }
            }

            if (!isMove) { // 더 이상 이동할 수 없다면 결과 리턴
                return day;
            }
            day++;
        }
    }

    static int bfs(int x, int y) {
        int sum = arr[x][y]; // 첫 방문 노드의 값을 sum 값으로 초기화
        queue = new LinkedList<>();
        list = new ArrayList<>();

        // 현재 값을 리스트와 큐에 적재
        list.add(new int[]{x, y});
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curArr = queue.poll();
            int curX = curArr[0];
            int curY = curArr[1];

            for (int i = 0; i < 4; i++) { // 네 방향 Scan
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (isArea(nx, ny) && !visited[nx][ny]) { // 범위 안인지 방문한 노드인지 체크
                    if (isAbleMove(nx, ny, curX, curY)) { // 연합이 가능하다면
                        // 리스트와 큐에 연합이 가능한 좌표를 삽입
                        list.add(new int[]{nx, ny});
                        queue.offer(new int[]{nx, ny});
                        sum += arr[nx][ny]; // 연합이 가능한 노드의 값을 합연산
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return sum;
    }

    static void calculate(int sum) {
        for (int[] i : list) {
            arr[i[0]][i[1]] = sum / list.size();
        }
    }

    static boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static boolean isAbleMove(int nx, int ny, int curX, int curY) {
        return Math.abs(arr[curX][curY] - arr[nx][ny]) >= l && Math.abs(arr[curX][curY] - arr[nx][ny]) <= r;
    }

}
