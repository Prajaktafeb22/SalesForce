package Hackathon;

import java.util.Arrays;
import java.util.HashMap;

public class Booleansaretrue {

	public static void main(String[] args) {
 
	
		//test1();
		//test2();
		//test3();
		//test4();
		//test5();
		//test6();
		test7();
	}
	//Q1. Consider there is a 3 Boolean variable called a, b, c. Check if at least two out of three 
	//	Booleans are true *
	private static void test1() {
		boolean a=true,b=false,c=false;
		
		if((a&&b)||(b&&c)||((a&&c))) {
			
			System.out.println("2 out of 3 are same");
			
			
		}
		else {
			
			System.out.println("2 out of 3 are not same");
		}
	}
	
	
	public static void test2() {
	//Q2. write a program to find factorial (Non Recursive) *
	
		int answer=1;
		int fact=5;
		for(int i=1;i<=fact;i++) {
			
			answer=answer*i;
		}
		System.out.println(answer);
		
		
		
	}
	
	public static void test3() {
		
		int[]input= {5,6,1,9,0,4,2,8,7};
		
//		Arrays.sort(input);
//		System.out.println(Arrays.toString(input));
		
		for(int i=0;i<input.length;i++) {
			
			for(int j=0;j<input.length;j++) {
				
				if(input[j]>input[i]) {
					int temp=input[j];
					input[j]=input[i];
					input[i]=temp;
					
				}	
			}
				
		}
	System.out.println(Arrays.toString(input));		
		
	}

//Given an array prints the unique numbers and also prints the number 
//of occurrences of duplicate numbers.
//	Enter your Value :
//		123445566
//		1  is unique
//		2  is unique
//		3  is unique
//		4  is present more than once
//		5  is present more than once
//		6  is present more than once

	
	
public static void test4() {
	
	int[]input= {1,2,3,4,4,5,5,6,6};
	HashMap<Integer,Integer>hs= new HashMap<>();
	
	for(int i=0;i<input.length;i++) {
		
		int Curentchar=input[i];
		if(!hs.containsKey(Curentchar)) {
			
			hs.put(Curentchar, 1);
		}
		else {
			
			hs.put(Curentchar,(hs.get(Curentchar)+1));
		}	
	}
	hs.forEach((key , value)->{
  		if(value > 1)
  			System.out.println(key + "  is present more than once");
  		else
  			System.out.println(key + "  is unique");		
	
});	
	
	
}

 public static void test5() {
	
//Q6. WJP to perform ascending order Selection sort *	
	
 	int[]input= {5,9,2,7,3,1,0,6,4};
 	
 	for(int i=0;i<input.length;i++) {
 		
 		for(int j=i+1;j<input.length;j++) {
 			
 			if(input[j]<input[i]) {
 				
 				int temp=input[j];
 				input[j]=input[i];
 				input[i]=temp;
 				
 			}
 		}	
 	}
 	System.out.println(Arrays.toString(input));	 
	 
}

 //Q12. Write a program to check palindrome (MalayalaM) for both numbers and string?

 public static void test6() {
	
	 //for string
	 String str = "Radar";
	 String  reverseStr = "";
	 
	 for(int i=str.length()-1;i>=0;i--) {
		 
		 reverseStr=reverseStr+str.charAt(i);
		 
	 }
 
//	 for(str.compareToIgnoreCase(reverseStr)) {
//		 
//		 
//	 }
// 
  
}
 //left rotate 
 public static void test7() {
	 
	String s="Prajakta"; 
	
	char[]ch=s.toCharArray();
	
	for(int i=0;i<3;i++) {
	
		char first=ch[0];
		
		for(int j=0;j<ch.length-1;j++) {
			
		  ch[j]=ch[j+1];
		  
		}
		
		ch[ch.length-1]=first;
		
	}
	StringBuffer sb= new StringBuffer();
	for(char c:ch) {
		
		sb.append(c);
	}
	
System.out.println(sb);	 
	 
 }
 

}
