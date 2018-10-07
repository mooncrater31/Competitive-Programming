import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class theEnds
{
    int minIndex = 0;
    int maxIndex = 0;
}
public class Solution {

    theEnds findMinMax(long[] Arr)
	{
		theEnds TE  = new theEnds() ;
    
    
		for(int i=0;i<Arr.length;i++)
		{
		if(Arr[TE.minIndex]>Arr[i])
			TE.minIndex = i ;
		else if(Arr[TE.maxIndex]<Arr[i])
			TE.maxIndex = i ;
		}
		return TE ;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		
        long[] arr = new long[5];
        for(int arr_i=0; arr_i < 5; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        theEnds TE = findMinMax(arr) ;
        long MSum = 0 , LSum = 0 ;
        for(int i=0;i<arr.length;i++)
        {
            if(i!=TE.minIndex)
            {
                MSum = MSum+arr[i] ;
            }
            if(i!=TE.maxIndex)
            {
                LSum = LSum+arr[i] ;
            }
        }
        System.out.print(LSum+" "+MSum);
    }
}
