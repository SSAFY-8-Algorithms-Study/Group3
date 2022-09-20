package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//님 무기가 좀 나쁘시네여
// 모 르 겠 어 요

public class BOJ_11387_김지희 {
	static class CombatPower{
		int attack, power, prob, damage, speed;

		public CombatPower(int attack, int power, int prob, int damage, int speed) {
			this.attack = attack;
			this.power = power;
			this.prob = prob;
			this.damage = damage;
			this.speed = speed;
		}
	}
	
	static CombatPower[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new CombatPower[4];
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int pw = Integer.parseInt(st.nextToken());
			int pr = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			if(i==1 || i==0) {
				arr[i] = new CombatPower(a, pw, pr, d, s);	
			}
			
			else if(i==2) {
				
				arr[2] = new CombatPower(arr[0].attack-a, arr[0].power-pw, arr[0].prob-pr, arr[0].damage-d, arr[0].speed-s);
				arr[3] = new CombatPower(arr[1].attack+a, arr[1].power+pw, arr[1].prob+pr, arr[1].damage+d, arr[1].speed+s);
			}
			else if(i==3) {
				arr[3] = new CombatPower(arr[3].attack-a, arr[3].power-pw, arr[3].prob-pr, arr[3].damage-d, arr[3].speed-s);
				arr[2] = new CombatPower(arr[2].attack+a, arr[2].power+pw, arr[2].prob+pr, arr[2].damage+d, arr[2].speed+s);
				
			}
		}
		double c = calc(0); //현재 전투력
		double p = calc(1);
		double nc = calc(2);
		double np = calc(3);
		
		System.out.println(check(c, nc));
		System.out.println(check(p, np));
		
	}
	
	private static double calc(int idx) {
		//전투력 = attack(1+pw/100) X [{1-min(prob, 1)} + min(prob, 1) X damage] x {1+ speed)
		CombatPower cur = arr[idx];
		int pb = Math.min(100, cur.prob);
		double combat = cur.attack * ((100+cur.power)*0.01) * ((1-pb*0.01) + ((pb*0.01) * cur.damage)) * (1+cur.speed);
//		System.out.println(combat);
		return combat;
	}
	
	private static String check(double a, double b) {
		if(a>b) return "-";
		else if(a<b) return "+";
		else return "0";
	}
}
