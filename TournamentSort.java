class TournamentSort//Only Even Number of nembers is allowed, building a class named Tournament Sort
{
 int B[] ;// Array, to save the tournamant tree of the original tree A, initialisation is out of TSort to make it available to the Show method
 int[] TSort(int A[])// A method TSort that returns an array, and it's input too is an array 'A'
 {
  int l = A.length -1,height=0 ;//Since the 0th element of A is redundant, therefore the length of A is one extra, therefore I have decreased l by 1
  int j = 2*l-1 ;// If there are l leaves, then there are 2*l-1 total vertices in a binary tree
  for(int alpha = l;alpha>0;alpha/=2,height++) ;//Found "height" here, which serves as the height of the Tree
  
  B = new int[2*l] ;//Now, the size of B is declared, which is again one more than needed.
  
  for (int x:B)//Initially all elements are made 0
  {
   B[x]=0 ;
  }
  
  for (int i = l ;i>0 ;i--)//the elements of 'A' are copied to 'B',as B's leaves
  {
   B[j--]=A[i] ;
  }
  
  for(int k=1;k<=height;k++) //We would need to carry it out "height" times, as we can already see.
  {
   for(int i= B.length-1 ;i>1 ;i--)//From B's length -1 (As B.length is not accessible) to 2(if it were to 1, a comparison with B[0] would be made)
   {
   if (i%2!=0)	//So that one element is compared only one time, we compare odd and even elements at a time(considering even number of elements)
   {
    if(B[i]>B[i-1]) //checking  if  ith element is bigger or not
	{
	 if (B[(i-1)/2]==0)//checking if the parent is already occupied or not
	 B[(i-1)/2]=B[i] ;// Entering the bigger vertex as the parent
	} 
	 else
	 {
	 if (B[(i-1)/2]==0)
	 B[(i-1)/2]=B[i-1] ; 
	}
   }
  }
 }
 return B ; //Return the tree(implemented as an array) to the caller
}

void Show()
{
 for (int i=1;i<=B.length-1;i++) //Printing the tournament tree
 {
  System.out.println("Value of element #"+i+" is "+B[i]) ;
 }
 System.out.println() ;
}
}
class TestTSort
{
 public static void main(String args[])
 {
  int  A[]={0,9,4,1,2,1,1,2,8,8,8},B[],i,j=0; //input array 'A', Tournament tree B
  int C[] = new int[A.length] ; // Array C gets the sorted elements
  TournamentSort TS = new TournamentSort() ; //creating an object of the class "TournamentSort"
  for(int gamma = 0;gamma<A.length;gamma++)//Since we'll be needing as much tournament trees as the elements 
  {
  B=TS.TSort(A) ;//the returned tree is saved into B, the array
  TS.Show() ;// Prints each tournament tree
  int x=B[1] ;//the largest element in A becomes the B[1]
  for ( i=1;i<A.length-1;i++)//Finding the position of the largest element in A
  {
   if (A[i]==x)
   break ;
  }// here, after the loop, we find that i represents the position of the largest element in A
  C[j++]=B[1];//C, is saving the largest element, in each iteration, forming a non-increasing series
  A[i]=-1 ;   // the largest element in A is deleted and is replaced by a"-1"(assuming all the numbers in A, initially are positive)
 }
 for(int delta=0;delta<C.length-1;delta++)// printing the C array(or the sorted array of A
 {
 System.out.println("Value of Element #"+(delta+1)+" in Sorted order is "+C[delta]);
 }
	}
}	
  