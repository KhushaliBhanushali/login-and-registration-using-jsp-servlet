package model;

import java.util.Random;

public class Helper {

		public static  int randomNumber(int min, int max){
			Random random = new Random();
			int n = 0;
			while(n < min) {
			   n = random.nextInt(max);
			}
			return n;
		}
}


