import java.util.* ;


class TestClass {
    public static void main(String args[] ) throws Exception {
        double ratSum = 0 ;
        int n = 1000 ;
        for(int j=0;j<n;j++)
        {
        int[] A = new int[(int)1e5] ;
        List<Integer> L = new ArrayList<Integer>() ;
        for(int i=0;i<A.length;i++)
        {
            int no = (int)(Math.random()*(int)1e5) ;
            A[i] = no ;
            L.add(A[i]) ;
        }
        
        long startTime = System.nanoTime() ;
        Arrays.sort(A) ;
        long endTime = System.nanoTime() ;
        Collections.sort(L) ;
        long endTime2 = System.nanoTime() ;
        long t1 = (endTime-startTime), t2 = (endTime2-endTime) ;
        ratSum+=(double)t2/t1 ;
        System.out.println("Arrays.sort took :"+t1+" Collections.sort took :"+t2+" ratio :"+((double)t2/t1)) ;
    }
    System.out.println("Average ratio :"+(ratSum/n)) ;
    }
}
