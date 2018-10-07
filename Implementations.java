import java.io.* ;
class Node//Adjacency list
{
    Node next=null ;
    int val ;
    Node(int val)
    {
        this.val = val ;
    }
}
class Implementations
{
    static void addToList(int idx,int x,Node[] AL)// To add into an adjacency list
    {
        Node n = new Node(x) ;
        n.next = AL[idx] ;
        AL[idx]  = n ;
    }
    static void mergeSort(Node[] NL,int p,int r)
    {
        if(p<r)
        {
            int q = (p+r)/2 ;
            mergeSort(NL,p,q) ;
            mergeSort(NL,q+1,r) ;
            merge(NL,p,q,r) ;
        }
    }
    static void merge(Node[] NL,int p,int q,int r)
    {
        int n1 = q-p+1 ;
        int n2 = r-q ;
        Node[] L = new Node[n1+1] ;
        Node[] R = new Node[n2+1] ;
        for(int i=0;i<n1;i++) L[i]=NL[p+i];
        for(int i=0;i<n2;i++) R[i]=NL[q+i+1];
        L[n1] = new Node(Integer.MAX_VALUE) ;
        R[n2] = new Node(Integer.MAX_VALUE) ;
        int i=0,j=0 ;
        for(int k=p;k<=r;k++) 
            if (compareNode(L[i],R[j])<=0)
                NL[k]=L[i++]; 
            else 
                NL[k]=R[j++];
    }
    static int compareNode(Node a,Node b)
    {
        return a.val<b.val?-1:(a.val>b.val?1:0) ;
    }
    public static void main(String args[]) throws Exception
    {
        
        //////////////////Use of Adjacency list
        /*
        Input : 
        
5 5
1 2
2 3
3 4
4 5
1 3

        */
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        Node[] AL = new Node[n] ;
        for(int i=0;i<m;i++)
        {
            // int a = in.nextInt()-1 ;
            // int b = in.nextInt()-1 ;
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            addToList(a,b,AL) ;
            addToList(b,a,AL) ;
        }
        for(int i=0;i<n;i++)
        {
            System.out.print("Node :"+i+" : ") ;
            Node temp = AL[i] ;
            while(temp!=null)
            {
                System.out.print(temp.val+" ") ;
                temp=temp.next ;
            }
            System.out.println() ;
        }
        //////////////////Sorting of objects example
        int[] A =  {4,6,1,10,22,4,12,2} ;
        int len = A.length ;
        Node[] NA = new Node[len] ;
        for(int i=0;i<len;i++) NA[i] = new Node(A[i]) ;
        mergeSort(NA,0,len-1) ;
        for(int i=0;i<len;i++)
        {
            System.out.print(NA[i].val+" ") ;
        }
    }
    
}
