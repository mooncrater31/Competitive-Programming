#include<bits/stdc++.h>
using namespace std;


int main()
{
       int n;cin>>n;
        long long arr[2*n];for(int i=0;i<2*n;i++)cin>>arr[i];
        sort(arr,arr+2*n);
        long  long ans=1e18;
        for(int i=0;i+n-1<2*n-1;i++)
        {
            long long val=arr[i+n-1]-arr[i];
            if(2*n-i-n<n) val*=arr[2*n-1]-arr[0];
            else val*=(arr[2*n-1]-arr[i+n]);
            ans=min(ans,val);
                
                
        }
        cout<<ans<<endl;
    
    return 0;
}