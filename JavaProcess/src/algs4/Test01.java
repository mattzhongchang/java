package algs4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import algs4.string.Alphabet;

public class Test01 {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Alphabet alpha = Alphabet.LOWERCASE;
		int R = alpha.radix();
		int[] count = new int[R];
		
		while (true)
		{
			String line = reader.readLine();
			int N = line.length();
			for (int i=0; i<N; i++)
			{
				if (alpha.contains(line.charAt(i)))
				{
					count[alpha.toIndex(line.charAt(i))]++;
				}
			}
			
			for (int c=0; c<R; c++)
			{
				System.out.println(alpha.toChar(c) + " " + count[c]);
			}
		}
		
	}
}
