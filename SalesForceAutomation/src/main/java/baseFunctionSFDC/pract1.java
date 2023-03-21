package baseFunctionSFDC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.testng.annotations.Test;

public class pract1 {

	@Test
	public static void test() throws IOException {

		
	// Approach 1 --> Using fileReader bufferReader	
	FileReader fr = new FileReader("C:\\Users\\DECOMM\\Desktop\\pract2.txt");
	BufferedReader br = new BufferedReader(fr);
	String str;
	while((str=br.readLine())!=null) {
		
		System.out.println(str);
	}
	
		
	//Approach 2-->Using Scanner and file
//	
//		File file= new File("C:\\Users\\DECOMM\\Desktop\\pract2.txt");
//		
//		Scanner sc= new Scanner(file);
//		
//		while(sc.hasNext()) {
//			
//			System.out.println(sc.nextLine());
//		}
		
//Approach 3-->instead of while we r using delimeter	
		
//		File file1 = new File("C:\\Users\\DECOMM\\Desktop\\pract2.txt");
//		
//		Scanner sc= new Scanner(file1);
//		
//		sc.useDelimiter("\\z");
//		System.out.println(sc.next());
//		
		
		
		
}
	
		
}

