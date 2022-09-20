package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_1764듣보잡 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> notlisten = new HashSet<>();
		ArrayList<String> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<N;i++) {
			notlisten.add(br.readLine());
		} //먼저 듣도 못한 입력
		for(int j=0;j<M;j++) {
			String notsee = br.readLine(); // 보도못한애 입력받으면서 있는지 확인
			if(notlisten.contains(notsee)) {
				result.add(notsee);//있으면 추가
			}
		}
		
		Collections.sort(result); // 정렬후 출력
		System.out.println(result.size());
		for(int i=0;i<result.size();i++) {
			sb.append(result.get(i)+"\n");
		}
		System.out.println(sb);
	}

}
