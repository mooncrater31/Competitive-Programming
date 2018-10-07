import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class Node 
{
    int a,b ;
    Node next ;
    Node(int a,int b){this.a = a;this.b=b;}
}

public class MonkAndTree2
{
    static int heapSize = 0 ;
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception,java.io.IOException,InterruptedException
    {
        Thread t = new Thread(null,null,"TT",(int)1e9)
        {
            @Override
            public void run()
            {
                try
                {
                    BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
                    int T = Integer.parseInt(bro.readLine()) ;
                    for(int t=0;t<T;t++)
                    {
                        String[] S = bro.readLine().split(" ") ;
                        int n = Integer.parseInt(S[0]) ;
                        int m = Integer.parseInt(S[1]) ;
                        Node[] AL = new Node[n];
                        long total = 0 ;
                        for(int i=0;i<m;i++)
                        {
                            S = bro.readLine().split(" ") ;
                            int a = Integer.parseInt(S[0])-1 ;
                            int b = Integer.parseInt(S[1])-1 ;
                            total+=abs(a-b) ;
                            addToList(a,b,AL) ;
                            addToList(b,a,AL) ;
                            //AL completed
                        }
                        if(debug)
                        {
                            System.out.println("AL :") ;
                            for(int i=0;i<n;i++)
                            {
                                System.out.print("Node :"+i+" : ") ;
                                Node temp = AL[i] ;
                                while(temp!=null)
                                {
                                    System.out.print(temp.b+" ") ;
                                    temp = temp.next ;
                                }
                                System.out.println() ;
                            }
                        }
                        boolean[] visited = new boolean[n] ;
                        long mst = 0 ;
                        int comp =0;
                        for(int i=0;i<n;i++)
                        {
                            if(!visited[i])
                            {
                                comp++ ;
                                mst+=prim(AL,i,visited); 
                            }
                        }
                        if(debug)
                        {
                            System.out.println("total :"+total+" mst :"+mst+" comp :"+comp) ;
                        }
                        System.out.println(total-mst+comp-1) ;
                    }
                }
                catch(java.io.IOException e)
                {
                    
                }
            }
        };
        t.start() ;
        t.join() ;
    }
    static void addToList(int idx,int val,Node[] AL)//O(1)
    {
        Node n = new Node(abs(idx-val),val) ;
        n.next = AL[idx] ;
        AL[idx]=n ;
    }
    static int abs(int val)
    {
        if(val<0) return -1*val ;
        return val ;
    }
    static long prim(Node[] AL,int s,boolean[] visited)
    {
        Node[] heap = new Node[(int)1e6] ;
        long mst=0 ;
        addToHeap(new Node(0,s),heap) ;// O(1):since only one element
        while(heapSize!=0)
        {
            Node n = extractMax(heap) ;//O(log n)
            if(!visited[n.b])
            {
                visited[n.b] = true ;
                mst+=n.a ;
                if(debug) System.out.println("node :"+abs(n.b-n.a)+","+n.b) ;
                for(Node temp = AL[n.b] ;temp!=null ;temp = temp.next )
                {
                    if(!visited[temp.b])
                    {
                        addToHeap(new Node(abs(n.b-temp.b),temp.b),heap) ;//O(logn)
                        if(debug) System.out.println("  Added "+n.b+" "+temp.b) ;                       
                    }
                }
            }
        }
        return mst ;
        
    }
    static int compareNode(Node a,Node b)
    {
        return a.a>b.a?1:(a.a<b.a?-1:0) ;
    }
    
    static void maxHeapify(int i,Node[] A)//O(log n)
    {
        int l = 2*i,r=2*i+1 ;
        int max ;
        if(l<=heapSize && compareNode(A[l],A[i])>0) max=l;
        else max=i ;
        if(r<=heapSize && compareNode(A[r],A[max])>0) max=r ;
        if(max!=i)
        {
            Node temp = A[max] ;
            A[max]=A[i] ;
            A[i]=temp ;
            maxHeapify(max,A) ;
        }
    }
    static Node extractMax(Node[] A)//O(log n)
    {
        Node max = A[1] ;
        A[1] = A[heapSize] ;
        heapSize-- ;
        maxHeapify(1,A) ;
        return max ;
    }
    static void addToHeap(Node val,Node[] A)//O(log n) 
    {
        A[++heapSize]=val;
        int idx = heapSize ;
        while(idx!=0)
        {
            maxHeapify(idx,A) ;
            idx=idx/2 ;
        }
    }
    
}