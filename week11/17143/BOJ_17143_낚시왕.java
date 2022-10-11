package last;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {

	static int R, C, M;
	static Shark[][] start, end; // class로 된 2차원 배열
	static int direct[][] = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 1 up 2 down 3 right 4 left
	static int fisher, ans;

	static class Shark {
		int speed, dir, size;

		public Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

	}

	static void fishing() {
		for (int i = 1; i <= R; i++) {
			if (start[i][fisher] != null) { // 객체배열은 null
				ans += start[i][fisher].size;
				start[i][fisher] = null;
				break;
			}
		}
	}

	static void move() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (start[i][j] != null) { // 상어 발견
					Shark now = start[i][j];
					int nexti = i + (direct[now.dir][0] * now.speed);
					int nextj = j + (direct[now.dir][1] * now.speed);

					while (nexti > R || nexti < 1) { // 위아래로 뛰쳐나갔다면? 여기 반복
						now.dir = reverse(now.dir);
						if (nexti > R) { // 크게 뛰쳐나간부분 음수로 계산돼서 돌어오게
							nexti = R + (R - nexti); // i가 R 보다 커거 R- nexti는 음수;
						} else if (nexti < 1) {
							nexti = 1 + (1 - nexti);
						}
					}

					while (nextj > C || nextj < 1) { // 왼오로 뛰쳐나갔다면? 여기 반복
						now.dir = reverse(now.dir);
						if (nextj > C) { // 크게 뛰쳐나간부분 음수로 계산돼서 돌어오게
							nextj = C + (C - nextj); // i가 R 보다 커거 R- nexti는 음수;
						} else if (nextj < 1) {
							nextj = 1 + (1 - nextj);
						}
					}
					start[i][j] = null; // 이동전 좌표는 떠남
					if (end[nexti][nextj] == null || end[nexti][nextj].size < now.size) {
						end[nexti][nextj] = now;
					}
				}
			}
		} // end all shark

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				start[i][j] = end[i][j];
				end[i][j] = null;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = new Shark[R + 1][C + 1];
		end = new Shark[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			start[x][y] = new Shark(speed, dir, size);
		}

		fisher = 0;
		while (true) {
			fisher++;
			if (fisher > C)
				break;
			fishing(); // 상어 낚시
			move();
		}

		System.out.println(ans);

	}

	static int reverse(int dir) {
		switch (dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}

}
