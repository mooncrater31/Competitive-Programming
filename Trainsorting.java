import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
// [5,6,4,7,3,8,2,9,1,10,0 ]
//  0 1 2 3 4 5 6 7 8 9  10
/*
Input 1:
1
10
5
6
4
7
3
8
9
1
10
0
PASSED
Input 2:
1
3
3
3
3
PASSED
Input 3: [1,10,22,9,2,8,3,7,4,5,6] :{1,2,3,4,5,6}+{22,9,8,7}
1
11
1
10
22
9
2
8
3
7
4
5
6
Input 4: PASSED
1
5
5
4
3
2
1
Input 5: 
50
1164
24170
19823
32112
23917
30694
6618
15826
23199
14219
24191
26985
24208
15835
8810
29020
17112
14044
30484
8340
26829
30819
11666
19489
14417
21906
27660
25306
239
19360
14193
8260
28967
27608
17769
26294
6149
9957
10891
1210
9465
23884
14545
27061
23010
22842
8776
3531
25452
5504
Input 6:[12553,6956,9115,23610,2379,22335,8220,22211,17096,17342,
21241,2915,6789,17393,870,4427,17533,3890,20836]
:{2379,8220,17096,17342,17393,17553,20836}+{23610,22335,22211,21241,6789,4427,3890}
1
19
12553
6956
9115
23610
2379
22335
8220
22211
17096
17342
21241
2915
6789
17393
870
4427
17533
3890
20836
*/
public class Trainsorting
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            int[] A = new int[N] ;
            for(int i=0;i<N;i++)
            {
                A[i] = Integer.parseInt(bro.readLine()) ;
            }
            System.out.println(LIS(reverse(A))) ;
        }
    }
    static int[] reverse(int[] A)
    {
        int[] rev = new int[A.length] ;
        for(int i=0;i<A.length;i++)
        {
            rev[A.length-1-i] = A[i] ;
        }
        return rev ;
    }
    static int LIS(int[] A)
    {
        // int[] L_id = new int[A.length] ;
        // int[] P = new int[A.length] ;
        List<Integer> L = new ArrayList<Integer>() ;
        List<Integer> minL = new ArrayList<Integer>() ;
        int max = 0 ;
        int lis = 0,minLis=0;
        for(int i=0;i<A.length;i++)
        {
            int pos = Collections.binarySearch(L,A[i]) ;
            List<Integer> clone = new ArrayList<Integer>(minL) ;
            Collections.reverse(clone) ;
            int minpos = Collections.binarySearch(clone,A[i]) ;
            
            if(pos<0)
                pos = -(pos+1) ;
            if(pos>=L.size())
                L.add(A[i]) ;
            else L.set(pos,A[i]) ;
            // L_id[pos] = i ;
            // P[i] = pos>0?L_id[pos-1];-1 ;
            if(minpos==-1)//less than every element
            {
                minL.add(A[i]) ;
                minpos = minL.size()-1 ;
            }
            else 
            {
                
                if(minpos<0)
                    minpos = (-minpos-2)%minL.size() ;
                minpos = minL.size()-1-minpos ;
                minL.set(minpos,A[i]) ;
            }
// [5,6,4,7,3,8,2,9,1,10,0 ]
//  0 1 2 3 4 5 6 7 8 9  10            
            if(pos+1>=lis)
            {
                lis = pos+1 ;
                // lis_end = i;
            }
            if(minpos+1>=minLis)
            {
                minLis = minpos+1 ;
            }
            // max = minLis+lis-1>max?minLis+lis-1:max ;
            max = pos+minpos+1>max?pos+minpos+1:max ;
            if(debug)
            {
                System.out.println("A["+i+"] :"+A[i]+" max :"+max+" minpos : "+minpos+" pos :"+pos) ;
                System.out.println("L: "+L) ;
                System.out.println("minL: "+minL) ;
            }
        }
        
        return max ;
    }
    
}