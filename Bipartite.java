class GfG
{
    static int[] q = new int[(int)1e6] ;
    static int top=-1,tail=-1 ;
    private static final boolean debug = true ;
    static void enq(int val)
    {
        q[++top]=val ;
        if(tail==-1) tail=0 ;
    }
    static int deq()
    {
        int ans = q[tail] ;
        if(tail==top)
        {
            tail=-1 ;
            top=-1 ;
            
        }
        else tail++ ;
        return ans ;
    }
    static boolean isEmp()
    {
        return top==-1 ;
    }
	  boolean isBipartite(int G[][],int V)
       {
          
              int[] color = new int[V] ;
              boolean[] visited = new boolean[V] ;
              boolean ans=true ;
              top=-1 ;
              tail=-1 ;
              for(int i=0;i<V;i++)
              {
                  if(!visited[i])
                  {
                      ans&=bfsColor(i,G,color,visited) ;
                  }
              }
              return ans ;
        }
          
        
        static boolean bfsColor(int s,int[][] G,int[] color,boolean[] visited)
        {
            
            visited[s]=true ;
            color[s]=-1 ;
            enq(s) ;
            while(!isEmp())
            {
                int v = deq() ;
                
                
                    for(int i=0;i<G[v].length;i++)
                    {
                        if(G[v][i]==1 && !visited[i])
                        {
                            visited[i]=true ;
                            color[i]=color[v]*-1 ;
                            enq(i) ;
                        }
                        else if(G[v][i]==1 && color[v]==color[i]) 
                        {
                            return false ;
                        }
                    }
                
                /*catch(Exception e)
                {
                    System.err.println("v :"+v+" G[0].length :"+G[0].length) ;
                    System.err.println("n :"+G.length+" m :"+G[0].length) ;
                }*/
            }
            return true ;
        }
        
        
}