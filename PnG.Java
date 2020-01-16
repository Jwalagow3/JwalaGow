package com.basic;
import java.util.*;
public class Gussing
{
	static
	{
		System.out.println("     Gussing game just for fun");
		System.out.println("     *************************");
	}
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter 1st Player Name");
		String s1=s.next();
		System.out.println("Enter 2nd Player Name");
		String s2=s.next();
		System.out.println("Enter 3rd Player Name");
		String s3=s.next();
		System.out.println("Enter 4th Player Name");
		String s4=s.next();
		System.out.println("Now Guess Any Number Ex:0-9");
		
		System.out.println(s1+":");
		int p1=s.nextInt();
		System.out.println(s2+":");
		int p2=s.nextInt();
		System.out.println(s3+"");
		int p3=s.nextInt();
		System.out.println(s4+"");
		int p4=s.nextInt();
		
		int r=(int)(Math.random()*9);
		
		System.out.println("Computer Gussing  "  +r);
		if(r==p1)
		{
			System.out.println(s1+  "  Win");
		}
			else if(r==p2)
			{
				System.out.println(s2+  " Win");
			}
			else if(r==p3)
			{
				System.out.println(s3+ " win");
			}
			else
			{
				System.out.println("Nobody Win Try again..!");
				
		}
	}

}

