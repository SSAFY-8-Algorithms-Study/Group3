package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15997_김지희 {
	
	static class Probability{
		String a;
		String b;
		double win, draw, lose;
		
		public Probability(String a, String b, double win, double draw, double lose) {
			this.a = a;
			this.b = b;
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
	
	static String[] nations;
	static HashMap<String, Integer> scores;
	static Probability[] prob;
	static double[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		scores = new HashMap<>(); //나라 별 승점 저장할 맵
		nations = new String[4]; // 인덱스 확인용으로 쓸 나라 배열
		answer = new double[4]; // 나중에 확률 리턴할 배열
		
		for(int i=0; i<4; i++) {
			nations[i] = st.nextToken();
			scores.put(nations[i], 0); //나라, 승점0점 put
		}
		
		prob = new Probability[6]; //전문가들의 예측
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			double w = Double.parseDouble(st.nextToken());
			double d = Double.parseDouble(st.nextToken());
			double l = Double.parseDouble(st.nextToken());
			
			prob[i] = new Probability(a, b, w, d, l);
		}
		
		dfs(0, 1);
		
		for(int i=0; i<4; i++) {
			System.out.println(answer[i]);
		}
		
	}
		
	private static void dfs(int idx, double percent) {
		if(percent == 0) return;
		if(idx == 6) {
			//계산
			Collection<Integer> values = scores.values();
			ArrayList<Integer> list = new ArrayList<>(values);
			//승점 리스트 가져와서
			Collections.sort(list); //4등 3등 2등 1등 순서로 정렬

			int first=0, second=0;
			for(int i=0; i<4; i++) {
				if(list.get(i) == list.get(3)) { 
					//1등이랑 같으면 1등 몇명인지 세기
					first++;
				}if(list.get(i)==list.get(2)) {
					//2등 수 세기 (공동 1등이든 아니든 일단 셀거야 )
					second++;
				}
			} //공동순위 찾기
			
			double firstRate = 1;
			double secondRate = 1;
			if(first>=2) {
				//1등이 2명 이상이면
				//만약에 공동1등이 4명이면 4중에 2팀 진출이니까 2/4 곱해주고
				//3팀이면 3팀 중 2팀 -> 2/3
				//2팀 공동이면 2/2
				firstRate = firstRate*2.0/first;
				//아 헷갈려
			}
			else if(second>=2) {
				//1등은 하난데 공동 2등이 있다면.....와 너무 헷갈려
				secondRate = secondRate*1.0/second; //공동 2등들 중 한팀 그냥 secondRate/second해도 되네
			}
			
			for(int i=0; i<4; i++) {
				//1등 점수 
				if(list.get(3) == scores.get(nations[i])) {
					answer[i] += (percent*firstRate);
				}
				if(first==1) {
					if(list.get(2) == scores.get(nations[i])) {
						//1등 하나면 2등 확률 구해주기
						answer[i] += (percent*secondRate);	
					}
				}
			}
			return;
		}
		
		Probability cur = prob[idx];
		
		scores.put(cur.a, scores.get(cur.a)+3); //이겼을 때...
		dfs(idx+1, percent*cur.win);
		scores.put(cur.a, scores.get(cur.a)-3);
		
		scores.put(cur.a, scores.get(cur.a)+1); //비겼을 때
		scores.put(cur.b, scores.get(cur.b)+1); //비겼을 때
		dfs(idx+1, percent*cur.draw);
		scores.put(cur.a, scores.get(cur.a)-1);
		scores.put(cur.b, scores.get(cur.b)-1);
		
		scores.put(cur.b, scores.get(cur.b)+3); //졌을 떄
		dfs(idx+1, percent*cur.lose);
		scores.put(cur.b, scores.get(cur.b)-3);
		
		
	}
	
}
