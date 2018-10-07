class PriorityQueue
{
    static int max_size = (int)1e6 ;
    static int heapSize  ;
    static void minHeapify(int x,int[] A)
    {
        int l = 2*x,r = 2*x+1 ;
        int smallest ;
        if(l<=heapSize && A[x]>A[l]) smallest = l ;
        else smallest =x ;
        if(r<=heapSize && A[r]<A[smallest]) smallest = r ;
        if(x!=smallest) 
        {
            int temp = A[x] ;
            A[x] = A[smallest] ;
            A[smallest] = temp ;
            minHeapify(smallest,A) ;
        }
    }
    static int extractMin(int[] A)
    {
        int max = A[1] ;
        A[1] = A[heapSize] ;
        heapSize-- ;
        minHeapify(1,A) ;
        return max ;
    }
    static void buildHeap(int[] A)
    {
        for(int i=heapSize/2;i>0;i--)
            minHeapify(i,A) ;
    }
    static void addToHeap(int val,int[] A)
    {
        A[++heapSize] = val ;
        int idx = heapSize ;
        while(idx!=0) 
        {
            minHeapify(idx,A) ;
            idx=idx/2 ;
        }
    }
    
    public static void main(String args[]) throws Exception
    {
        int[] A = {1,5,3,6,7,9,10,1,4} ;
        heapSize = A.length ;
        int[] heap = new int[max_size] ;
        for(int i=1;i<=A.length;i++) heap[i]=A[i-1];
        
        buildHeap(heap) ;   
        addToHeap(96,heap) ;
        addToHeap(26,heap) ;
        addToHeap(33,heap) ;
        addToHeap(12,heap) ;
        
        
        while(heapSize!=0)
            System.out.println(extractMin(heap)+" ") ;
    }
}