package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n16236 {
    static int[][] board; // 입력 받은 물고기 맵
    static int[][] dist; // bfs에 사용할 거리 거리 표시 맵
    static int n; // n*n 크기의 board
    static int sharkX, sharkY; // 상어의 초기 위치
    static Queue<int[]> que; // bfs에 사용할 Queue
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북
    static int size = 2; // 상어의 크기
    static int eat = 0; // 물고기 먹은 개수
    static int count = 0; // 최종 출력 값
    static int minX, minY, minDist; // while 문 탈출 조건

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int fish = Integer.parseInt(st.nextToken());

                // 상어의 초기 위치 좌표를 따로 저장 후 빈칸으로 초기화
                if (fish == 9) {
                    sharkX = i;
                    sharkY = j;
                    board[i][j] = 0;
                    continue;
                }

                board[i][j] = fish;
            }
        }

        while (true) {
            dist = new int[n][n]; //루프 한번마다 최단 거리 표시 배열 초기화
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            bfs(sharkX, sharkY); // 가장 가까운 물고기를 먹는 bfs

            // MAX값이 아닐 경우 물고기를 먹었다는 의미
            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                eat++;
                board[minX][minY] = 0; // 먹은 곳은 0으로 초기화
                sharkX = minX; // 물고기를 먹은 위치가 상어의 시작지점으로 초기화
                sharkY = minY; // 상동
                count += dist[minX][minY];

                if (eat == size) { // 상어의 크기만큼 먹었다면
                    size++;
                    eat = 0;
                }
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    static void bfs(int x, int y) {
        que = new LinkedList<>();
        que.add(new int[]{x, y});

        while (!que.isEmpty()) {
            int[] curArr = que.poll();

            int curX = curArr[0];
            int curY = curArr[1];

            for (int d = 0; d < 4; d++) { // 네 방향을 다 체크
                int nx = curX + dx[d];
                int ny = curY + dy[d];

                // 맵의 크기를 벗어나지 않는지, 이동 가능한 공간인지, 해당 공간을 방문한 적이 없다면
                if (isArea(nx, ny) && isAbleMove(nx, ny) && dist[nx][ny] == 0) {
                    dist[nx][ny] = dist[curX][curY] + 1; // 이동한 공간을 이전공간 + 1 (거리 표시)

                    if (isAbleEat(nx, ny)) { // 물고기를 먹을수 있다면
                        if (minDist > dist[nx][ny]) { // 이동한 공간이 가장 가까운 거리라면
                            minDist = dist[nx][ny];
                            minX = nx;
                            minY = ny;
                        } else if (minDist == dist[nx][ny]) { // 가장 가까운 거리가 겹친다면
                            if (minX > nx) { // 더 위쪽에 물고기가 있다면
                                minX = nx;
                                minY = ny;
                            } else if (minX == nx) { // 위 쪽에 동일하게 존재한다면
                                if (minY > ny) { // 왼쪽으로 이동
                                    minY = ny;
                                }
                            }
                        }
                    }

                    que.add(new int[]{nx, ny}); // 이동한 공간을 Queue에 추가
                }
            }
        }
    }

    // 맵 범위를 벗어나지 않는지 체크
    static boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    // 이동 가능한 지역인지 체크
    static boolean isAbleMove(int x, int y) {
        return board[x][y] <= size; // 상어 크기가 물고기 크기보다 크거나 같다면 이동 가능 true
    }

    // 물고기를 먹을 수 있는지 체크
    static boolean isAbleEat(int x, int y) {
        return board[x][y] < size && board[x][y] != 0; // 상어 크기가 물고기 크기보다 크고, 해당 공간에 물고기가 있다면 true
    }
}
