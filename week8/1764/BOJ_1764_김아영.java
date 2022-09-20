package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1764_김아영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String,Integer> map = new HashMap<>();
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			map.put(str,1);
		}
		
		//System.out.println(map);
		for(int i = 0; i<M;i++) {
			String str = br.readLine();
			if (!map.containsKey(str)) {
				map.put(str, 1);
			}else {
				map.put(str, 2);
			}
		}
		//System.out.println(map);
		
		
		ArrayList<String> crr = new ArrayList<>(); 
		for( String key : map.keySet()) {
			if (map.get(key) > 1) {
				crr.add(key);
			}
		}	
		Collections.sort(crr);
		System.out.println(crr.size());
		for(int i=0;i<crr.size();i++) {
			System.out.println(crr.get(i));
		}

	}
}
