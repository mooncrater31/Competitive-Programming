    import java.util.* ;
    import java.io.BufferedReader ;
    import java.io.InputStreamReader ;

    public class Rebuild
    {
        public static void main(String args[]) throws Exception
        {
            BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
            int T = Integer.parseInt(bro.readLine()) ;
            for(int t=0;t<T;t++)
            {
                String[] S = bro.readLine().split(" ") ;
                int n = Integer.parseInt(S[0]) ;
                int m = Integer.parseInt(S[1]) ;
                List<List<Triplet>> L = new ArrayList<List<Triplet>>() ;
                for(int i=0;i<n;i++) L.add(new ArrayList<Triplet>()) ;
                for(int i=0;i<m;i++)
                {
                    S = bro.readLine().split(" ") ;
                    int a = Integer.parseInt(S[0])-1 ;
                    int b = Integer.parseInt(S[1])-1 ;
                    long c = Long.parseLong(S[2]) ;
                    L.get(a).add(new Triplet(c,b,a)) ;
                    L.get(b).add(new Triplet(c,a,b)) ;
                    
                }
                Triplet tr = prim(L) ;
                System.out.println(tr.a+" ") ;
                for(int i=0;i<n;i++) System.out.print(tr.deg[i]+" ") ;
                System.out.println() ;
            }
        
        }
        static Triplet prim(List<List<Triplet>> L)
        {
            int n = L.size() ;
            boolean[] visited = new boolean[n] ;
            PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
            pq.add(new Triplet(0,0,0)) ;//dist, vertex b, vertex a
            long mst=0 ;
            int[] deg = new int[n] ;
            deg[0]=-2 ;
            while(!pq.isEmpty())
            {
                Triplet temp = pq.poll() ;
                if(!visited[temp.b])
                {
                    visited[temp.b]=true ;
                    mst+=temp.a ;
                    deg[temp.b]++ ;
                    deg[temp.c]++ ;
                    for(int i=0;i<L.get(temp.b).size();i++)
                    {
                        Triplet val = L.get(temp.b).get(i) ;
                        if(!visited[val.b])
                            pq.add(val) ;
                    }
                }
            }
            Triplet t = new Triplet(mst,0,0) ;
            t.deg = deg ;
            return t ;
            
        }
        
    }
    class Triplet implements Comparable<Triplet>
    {
        long a ;
        int b,c ;
        int[] deg ;
        Triplet(long a,int b,int c)
        {
            this.a =a ;
            this.b=b ;
            this.c=c ;
        }
        @Override
        public int compareTo(Triplet p)
        {
            if(this.a>p.a)
                return 1 ;
            else if(this.a<p.a)
                return -1 ;
            else
            {
                int alpha = Math.min(this.b,this.c) ;
                int beta = Math.min(p.c,p.b) ;
                if(alpha<beta)
                    return 1 ;
                else if(alpha>beta) return -1 ;
                else 
                {
                    int gamma = Math.max(this.c,this.b) ;
                    int delta = Math.max(p.c,p.b) ;
                    if(gamma>delta) return -1 ;
                    else if(gamma<delta) return 1 ;
                    else return 0 ;
                }
            }
        }
    }