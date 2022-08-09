package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1931 {

	static class study implements Comparable<study>{
		private int start;
		private int end;
		
		public study(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(study o) {
			if (this.end > o.end) return 1;
			else if (this.end == o.end) {
				if (this.start > o.start)return 1;
			}
			return -1;
		}	
	}
	
	static List<study> room;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		room = new ArrayList<>();
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			room.add(new study(s, b));
		}
		// 종료시간이 더 빠르면 더 많은 활동을 한다. 따라서 끝나는 시간의 오름차순으로 정렬을 하고 만약 같으면 시작시간 오름차순
		Collections.sort(room);
		
		int cnt = 0;
		int check = 0;
		for(int j = 0; j < N; j++) {
			// System.out.println(room.get(j).start + " " +room.get(j).end);
			if (check <= room.get(j).start) {
				check = room.get(j).end;
				cnt++;
			}
		}
		System.out.println(cnt);	
	}

}
