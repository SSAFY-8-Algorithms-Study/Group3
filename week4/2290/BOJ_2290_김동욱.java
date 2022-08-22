package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2290_김동욱 {
	static int s;
	static StringBuilder sb;

	public static void space() {
		for (int i = 0; i < s + 2; i++) {
			sb.append(" ");
		}
		sb.append(" ");
	}

	public static void garoLine() {
		sb.append(" ");
		for (int i = 0; i < s; i++) {
			sb.append("-");
		}
		sb.append("  ");
	}

	public static void seroLineRight() {
		for (int i = 0; i < s + 1; i++) {
			sb.append(" ");
		}
		sb.append("| ");
	}

	public static void seroLineLeft() {
		sb.append("|");
		for (int i = 0; i < s + 2; i++) {
			sb.append(" ");
		}
	}

	public static void seroLineBoth() {
		sb.append("|");
		for (int i = 0; i < s; i++) {
			sb.append(" ");
		}
		sb.append("| ");
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		String n = st.nextToken();
		//long beforeTime = System.currentTimeMillis();
		sb = new StringBuilder();
		// head
		for (int i = 0; i < n.length(); i++) {
			char temp = n.charAt(i);
			if (temp == '1' || temp == '4')
				space();
			else
				garoLine();
		}
		sb.append("\n");
		// body
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < n.length(); j++) {
				char temp = n.charAt(j);
				if (temp == '1' || temp == '2' || temp=='3'|| temp=='7')
					seroLineRight();
				else if(temp=='5'|| temp=='6')
					seroLineLeft();
				else
					seroLineBoth();
			}
			sb.append("\n");
		}
		// center
		for (int i = 0; i < n.length(); i++) {
			char temp = n.charAt(i);
			if (temp == '1' || temp == '7'|| temp=='0')
				space();
			else
				garoLine();
		}
		sb.append("\n");
		// bodytwo
				for (int i = 0; i < s; i++) {
					for (int j = 0; j < n.length(); j++) {
						char temp = n.charAt(j);
						if(temp=='2')
							seroLineLeft();
						else if(temp=='6'||temp=='8'||temp=='0')
							seroLineBoth();
						else
							seroLineRight();
					}
					sb.append("\n");
				}
		
		// foot
		for (int i = 0; i < n.length(); i++) {
			char temp = n.charAt(i);
			if (temp == '1' || temp == '4'|| temp=='7')
				space();
			else
				garoLine();
		}
		sb.append("\n");
		System.out.println(sb);
//		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//		long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
//		System.out.println("시간차이(m) : "+secDiffTime);
	}

}
