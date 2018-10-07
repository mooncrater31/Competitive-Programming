import java.util.* ;
import java.io.* ;

public class PrimeGift
{
	static long nextUp = -1 ;
	static int possibleResult ; 
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String s1 = br.readLine() ;
		String s2 = br.readLine() ;
		String s3 = br.readLine() ;
		int n = Integer.parseInt(s1) ;
		int[] p = new int[n] ;
		String[] s2s = s2.split(" ") ;
		// arrayStringPrinter(s2s) ;
		for(int i=0;i<n;i++)
		{
			p[i] = Integer.parseInt(s2s[i]) ;
		}
		int K = Integer.parseInt(s3) ;
		primes = new int[n] ;
		for(int i = 0; i < n / 2; i++) {
            primes[i] = p[2 * i];
            primes[n / 2 + i] = p[2 * i + 1];
        }
        if((n & 1) == 1)
            primes[n - 1] = p[n - 1] ;
		long large[] = new long[(int) 2e6];//how the size was decided?
        Arrays.fill(large, Long.MAX_VALUE);//why?
        ptr = 0;
        brute(0, n / 2, 1, large);
        int szL = ptr;
        long small[] = new long[(int) 2e6];
        Arrays.fill(small, Long.MAX_VALUE);
        ptr = 0;
        brute(n / 2, n, 1, small);
        int szS = ptr;
        // int K = in.nextInt() ;
        small = radixSort(small);
        large = radixSort(large);
		long low = 0, high = 1000000000000000005L;//why does the answer is wrong for test case 6, for 10^{18}+7
		while(high - low > 1){//how does this exactly work
			
			long h = high+low>>1;
			int co = count(h,small,large,low,high) ;
			System.out.println("high :"+high+"low :"+low+"("+h+") and count = "+co) ;
			if(co >= K){
				high = h;
			}else{
				low = h;
			}
			
		}
		System.out.println(high) ;
	}
	static int[] primes ;
	static int ptr ;
	static long MAX = (long)1e18 ;
	//brute function from bhishma's submission: http://codeforces.com/contest/912/submission/34101881
	static void brute(int idx,int len,long curr ,long[] collect)
	{
		if(idx==len)
		{
			collect[ptr++] = curr ;
			return ;
		}
		brute(idx+1,len,curr,collect) ;
		while(curr<=(MAX/primes[idx]))//this '=' is very important for test 6, since it wasn't generating 10^{18}
		{
			curr*=primes[idx] ;
			brute(idx+1,len,curr,collect) ;
		}
		
	}
	//count and Radix Sort from uwi's submission: http://codeforces.com/contest/912/submission/33951400
	static int count(long h,long[] left,long[] right,long low,long high)
	{
		if(h == nextUp)
		{
			return possibleResult ;
		}
		int ret = 0,ret2 = 0 ;
		int p = left.length-1,p2 = left.length-1  ;
		// int count = 0 ;
		nextUp = (h+high)/2 ;
		for(long v:right)
		{
			// count++ ;
			while(p>=0 && (v*left[p] > h || (double)v*left[p] > 1e18))
			{
				if(v*left[p]>nextUp)
					p2-- ;
				p-- ;
				
			}
			ret2+=p2+1 ;
			ret +=p+1 ;
		}
		possibleResult = ret2 ;
		System.out.println("possibleResult :"+possibleResult) ;
		
		// System.out.println("Count :"+count) ;
		
		return ret ;
	}
	
	public static long[] radixSort(long[] f){ return radixSort(f, f.length); }
	public static long[] radixSort(long[] f, int n)
	{
		long[] to = new long[n];
		{
			int[] b = new int[65537];//2^16 = 65536 
			for(int i = 0;i < n;i++)b[1+(int)(f[i]&0xffff)]++;//ffff = 65535 
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>16&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>16&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>32&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>32&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>48&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>48&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		return f;
	}
	
}