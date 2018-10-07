import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class WhatGoesUp
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        List<Integer> L = new ArrayList<Integer>() ;
        for(String S = bro.readLine().trim();!S.equals("");S = bro.readLine().trim())
        {
            L.add(Integer.parseInt(S)) ;
        }
        List<Integer> res = LIS(L) ;
        System.out.println(res.size()+"\n-") ;
        for(int el:res)
            System.out.println(el) ;
        
        
    }
    static List<Integer> reconstruct(int end,List<Integer> A,int[] p)
    {
        List<Integer> L = new ArrayList<Integer>() ;
        int x = end ;
        for(;p[x]>=0;x=p[x]) L.add(A.get(x)) ;
        L.add(A.get(x)) ;
        Collections.reverse(L) ;
        return L ;
    }
    static List<Integer> LIS(List<Integer> A)
    {
        // List<Integer> L_id = new ArrayList<Integer>() ;
        // List<Integer> P = new ArrayList<Integer>() ;
        int[] L_id = new int[A.size()] ;
        int[] P = new int[A.size()] ;
        List<Integer> L = new ArrayList<Integer>() ;
        int lis = 0,lis_end = 0 ;
        for(int i=0;i<A.size();i++)
        {
            int pos = Collections.binarySearch(L,A.get(i)) ;
            if(debug) System.out.println("pos :"+pos) ;
            if (pos<0)
                pos = -(pos+1) ;
            if(pos>=L.size())
                L.add(A.get(i)) ;
            else L.set(pos,A.get(i)) ;
            L_id[pos] = i ;
            P[i] = pos>0?L_id[pos-1]:-1 ;
            if(pos+1>=lis)
            {
                lis = pos+1 ;
                lis_end = i ;
            }
        }
        if(debug) System.out.println(L) ;
        return reconstruct(lis_end,A,P) ;
    }
}
//-8061-