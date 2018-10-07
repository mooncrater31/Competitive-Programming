import java.io.*;
import java.util.*;
import java.lang.*;
class FastReader
    { BufferedReader br;StringTokenizer st;
        public FastReader(){ br = new BufferedReader(new InputStreamReader(System.in));}
        String next(){while (st == null || !st.hasMoreElements()){try { st = new StringTokenizer(br.readLine());}
         catch (IOException  e){e.printStackTrace();}}return st.nextToken();}
        int nextInt(){return Integer.parseInt(next());}
        long nextLong(){ return Long.parseLong(next());}
        double nextDouble(){return Double.parseDouble(next()); }
        String nextLine(){ String str = "";try{str = br.readLine();}catch (IOException e){e.printStackTrace();}return str;}
    }
class cmp implements Comparator<Long>
 {
	 
	 public int compare(Long a1,Long b1)
       {
          
          return 10;
       }
    }
    
    
    class Demo
    {
     static ArrayList<ArrayList<Integer> > v=new ArrayList<ArrayList<Integer> >();
    static ArrayList<Long> arr1=new ArrayList<Long> ();
    static ArrayList<Long> arr2=new ArrayList<Long>();
    static int arr[]=new int[200005];
    static int vis[]=new int[200005];
        static int n,m;
        public static long dfs(int s)
        {
            vis[s]=1;
            long ans=0;
            for(int it:v.get(s))
            {
                if(vis[it]==0)
                ans+=dfs(it);
            }
            ans++;
            arr1.add(ans*((long)n-ans+1));
            return ans;
            
        }
        public static void main(String []args)
        {
            FastReader sc=new FastReader();
            
            n=sc.nextInt();
            m=sc.nextInt();
            Arrays.fill(vis,0);
            for(int i=0;i<=n+1;i++)
            v.add(new ArrayList<Integer> ());
            
            for(int i=1;i<=n;i++)
            {
                int x,y;
                x=sc.nextInt();
                y=sc.nextInt();
                v.get(x).add(y);
                v.get(y).add(x);
            }
            dfs(1);
            
            for(int i=1;i<=n;i++)
              arr2.add(sc.nextLong());
            
            Collections.sort(arr2,new cmp());
            
           /* Collections.sort(arr2,new Comparator<Long> (){
                public int compare(long a,long b)
                {
                    return b.compare(a);
                }
            })
            */
            long ans=0;
            for(int i=0;i<n;i++)
            {
               // System.out.println(arr1.get(i)+" "+arr2.get(i));
                ans+=arr1.get(i)*arr2.get(i);
            }
            PrintWriter out=new PrintWriter(System.out);
            System.out.println(ans);
            
        }
    }
// how to take char "char ch=sc.next().charAt(0);
// fast output PrintWriter object=new PrintWriter(System.in); object.println();