package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1764_김지희 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			map.put(br.readLine(), 1); //중복 없응게
		}
		for(int i=0;i<M; i++) {
			String name = br.readLine();
			map.put(name, map.getOrDefault(name, 0)+1);
		}
		ArrayList<String> names = new ArrayList<>();
		
		for(String key : map.keySet()) {
			if(map.get(key)>1) names.add(key);
		}
		
		Collections.sort(names);
		int size = names.size();
		System.out.println(size);
		
		for(int i=0; i<size; i++) {
			System.out.println(names.get(i));
		}
	}
}
