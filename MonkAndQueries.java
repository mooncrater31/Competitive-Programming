import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class MonkAndQueries
{
    static int minHeapSize,maxHeapSize ;
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int Q = Integer.parseInt(bro.readLine()) ;
        int[] minHeap = new int[(int)1e6] ;
        int[] maxHeap = new int[(int)1e6] ;
        minHeapSize = 0 ;
        maxHeapSize = 0 ;
        for(int q=0;q<Q;q++)
        {
            String[] S = bro.readLine().split(" ") ;
            int op = Integer.parseInt(S[0]) ;
            if(op==1)
            {
                int val = Integer.parseInt(S[1]) ;
                addMax(val,maxHeap) ;
                addMin(val,minHeap) ;
            }
            else if(op==2)
            {
                int val = Integer.parseInt(S[1]) ;
                boolean found = false ;
                for(int i=0;i<minHeapSize;i++)
                if(minHeap[i]==val)
                {
                        extractMinHeap(i,minHeap) ;
                        found = true ;
                        break ;
                }
                for(int i=0;i<maxHeapSize;i++)
                {
                    if(maxHeap[i]==val)
                    {
                        extractMaxHeap(i,maxHeap) ;
                        break ;
                    }
                }
                if(!found) System.out.println("-1") ;
            }
            else if(op==3)
            {
                System.out.println(getExtreme(maxHeap)) ;
            }
            else if(op==4)
            {
                System.out.println(getExtreme(minHeap)) ;
            }
            if(debug)
            {
                System.out.println("max :") ;
                for(int i=0;i<maxHeapSize;i++)
                    System.out.print(maxHeap[i]+" ") ;
                System.out.println() ;
                System.out.println("min :") ;
                for(int i=0;i<minHeapSize;i++)
                    System.out.print(minHeap[i]+" ") ;
                System.out.println() ;
            }
        }
    }
    static  void minHeapify(int idx,int[] heap)
    {
        int l = 2*idx+1 ;
        int r = 2*idx+2 ;
        int min = idx ;
        if(l<minHeapSize && heap[l]<heap[min]) min=l ;
        if(r<minHeapSize && heap[r]<heap[min]) min=r ;
        if(min!=idx)
        {
            int temp = heap[min] ;
            heap[min]=heap[idx] ;
            heap[idx]=temp ;
            minHeapify(min,heap) ;
        }
    }
    static  void maxHeapify(int idx,int[] heap)
    {
        int l = 2*idx+1 ;
        int r = 2*idx+2 ;
        int max = idx ;
        if(debug) System.out.println("Checking :"+heap[idx]+" with children :"+heap[l]+" and "+heap[r]) ;
        if(l<maxHeapSize && heap[l]>heap[max]) max=l ;
        if(r<maxHeapSize && heap[r]>heap[max]) max=r ;
        if(max!=idx)
        {
            int temp = heap[max] ;
            heap[max]=heap[idx] ;
            heap[idx]=temp ;
            maxHeapify(max,heap) ;
        }
    }
    static void extractMinHeap(int idx,int[] heap)
    {
        heap[idx]=heap[minHeapSize-1] ;
        minHeapSize-- ;
        minHeapify(idx,heap) ;
    }
    static void extractMaxHeap(int idx,int[] heap)
    {
        heap[idx]=heap[maxHeapSize-1] ;
        maxHeapSize-- ;
        maxHeapify(idx,heap) ;
    }
    static void addMin(int val,int[] heap)
    {
        heap[minHeapSize++] = val ; 
        int temp = minHeapSize-1 ;
        while(temp!=0)
        {
            minHeapify(temp,heap) ;
            temp/=2 ;
        }
        minHeapify(temp,heap) ;
    }
    static void addMax(int val,int[] heap)
    {
        heap[maxHeapSize++] = val ; 
        int temp = maxHeapSize-1 ;
        while(temp!=0)
        {
            maxHeapify(temp,heap) ;
            temp/=2 ;
        }
        maxHeapify(temp,heap) ;
    }
    static int getExtreme(int[] heap) 
    {
        return heap[0] ;
    }   
}