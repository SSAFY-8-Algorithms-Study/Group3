import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] map;
	static boolean[] used;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		used = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		powerset(0);//부분 집합 구하기 -> set에 모든 합을 넣기
		int i = 1;
		while(true) {
			if(!set.contains(i)) {// 1부터 set에 있는지 없는지 확인
				break;
			}
			i++;
		}
		System.out.println(i);//없는 최소 값 출력
	}
	private static void powerset(int idx) { //부분 집합 구하기
		if(idx == N) { //부분집합 구해졌을 때 
			int sum = 0;
			for(int i=0;i<N;i++) {
				if(used[i]) sum += map[i];
			}
			set.add(sum); //해당 집합 합 HashSet에 넣어서 중복 없애기
			return;
		}
		
		used[idx] = true;
		powerset(idx+1);
		used[idx] = false;
		powerset(idx+1);
	}
}
