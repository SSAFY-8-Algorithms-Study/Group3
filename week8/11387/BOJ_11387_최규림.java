package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 님 무기가 좀 나쁘시네여
public class BOJ_11387_최규림 {

	static class Weapon {
		int attack;
		int power;
		int criPercentage;
		int criAttackPercentage;
		int speed;

		Weapon() {
		};

		Weapon(int a, int p, int cP, int cA, int s) {
			this.attack = a;
			this.power = p;
			this.criPercentage = cP;
			this.criAttackPercentage = cA;
			this.speed = s;
		}

	}

	static Weapon[] weapons = new Weapon[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 무기를 장착한 크리의 수치
		// 무기를 장착한 파부의 수치
		// 크리가 현재 장착하고 있는 무기가 올려주는 수치
		// 파부가 현재 장착하고 있는 무기가 올려주는 수치
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			weapons[i] = new Weapon();
			weapons[i].attack = Integer.parseInt(st.nextToken());
			weapons[i].power = Integer.parseInt(st.nextToken());
			weapons[i].criPercentage = Integer.parseInt(st.nextToken());
			weapons[i].criAttackPercentage = Integer.parseInt(st.nextToken());
			weapons[i].speed = Integer.parseInt(st.nextToken());
		}

		// 크리 + 무기
		double cri = calc(weapons[0]);
		// 파부 + 무기
		double pabu = calc(weapons[1]);
		// 크리 + 파부무기
		double c = calc_new(weapons[0], weapons[2], weapons[3]);
		// 파부 + 크리무기
		double p = calc_new(weapons[1], weapons[3], weapons[2]);

		if (cri - c > 0) {
			System.out.println("-");
		} else if (cri - c < 0) {
			System.out.println("+");
		} else {
			System.out.println(0);
		}

		if (pabu - p > 0) {
			System.out.println("-");
		} else if (pabu - p < 0) {
			System.out.println("+");
		} else {
			System.out.println(0);
		}
	}

	public static double calc(Weapon weapon) {
		int a = weapon.attack;
		int p = weapon.power;
		int cP = weapon.criPercentage;
		int cA = weapon.criAttackPercentage;
		int s = weapon.speed;
//		double result = a * (100 + p) * (100 * (100 - Math.min(cP, 100)) + Math.min(cP, 100) * cA) * (100 + s);
		double result = 1;
		result *= a;
		result *= (100 + p);
		result *= (100 * (100 - Math.min(cP, 100)) + Math.min(cP, 100) * cA);
		result *= (100 + s);
		return result;
	}

	public static double calc_re(double result, Weapon weapon1, Weapon weapon2) {
		int a = weapon1.attack - weapon2.attack;
		int p = weapon1.power - weapon2.power;
		int cP = weapon1.criPercentage - weapon2.criPercentage;
		int cA = weapon1.criAttackPercentage - weapon2.criAttackPercentage;
		int s = weapon1.speed - weapon2.speed;
		return calc(new Weapon(a, p, cP, cA, s));
	}

	public static double calc_new(Weapon weapon1, Weapon weapon2, Weapon weapon3) {
		int a = weapon1.attack - weapon2.attack + weapon3.attack;
		int p = weapon1.power - weapon2.power + weapon3.power;
		int cP = weapon1.criPercentage - weapon2.criPercentage + weapon3.criPercentage;
		int cA = weapon1.criAttackPercentage - weapon2.criAttackPercentage + weapon3.criAttackPercentage;
		int s = weapon1.speed - weapon2.speed + weapon3.speed;
		return calc(new Weapon(a, p, cP, cA, s));
	}
}
