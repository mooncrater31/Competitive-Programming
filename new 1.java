import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;


public class HelpAshiwn { 
    public static void main(String[] args) { 
    InputReader in = new InputReader();
    PrintWriter out = new PrintWriter(System.out);
    int t = in.ni();
    while(t-->0){
        int n = in.ni();
        int m = in.ni();
        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];
        for
        (
            int i=0;
            ipreviousMax){ previousSec = previousMax;
            previousMax = arr[0][i];
        } 
        else if(arr[0][i]>previousSec)
        { 
            previousSec = arr[0][i];
        }
        dp[0][i] = arr[0][i];
    }
    for(int i=1;icurrentMax)
    { 
        currentSec = currentMax;
        currentMax = dp[i][j];
    } 
    else if(dp[i][j]>currentSec) currentSec = dp[i][j];
  }
  previousMax = currentMax;
  previousSec = currentSec;
  currentMax = Integer.MIN_VALUE;
  currentSec = Integer.MIN_VALUE;
 }
 out.println(previousMax);
 }
 out.close();
