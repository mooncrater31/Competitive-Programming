/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

public class GFG {
    static void coun(int[] arr,int n){
        int[] brr=new int[n];
        for(int a=0;a<n;a++)
        {
            brr[a]=-1;
        }
        int j=0;
        for(int i=0;i<n;i++){
            if(arr[i]==i+1){
                brr[j]=i+1;
                j++;
            }
        }
        for(int k=0;k<n;k++)
            if(brr[k]>-1)
		        System.out.print(brr[k]+" ");
		System.out.println();
    }
	public static void main (String[] args) {
		//code
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		for(int i=0;i<t;i++){
		    int n=sc.nextInt();
		    int[] arr=new int[n];
		    for(int j=0;j<n;j++){
		        arr[j]=sc.nextInt();
		    }
		    coun(arr,n);
		}
	}
}