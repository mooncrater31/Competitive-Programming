import java.util.*;
class loopinlinkedlist
{
    Node head;
    
   static class Node
    {
        Node next;
        int data;
        Node(int d)
        {
            data=d;
        }
    }
    void print(Node n)
    {
     
        if(n==null)
        {
            System.out.println("null");
        }
       else{
        while(n!=null)
        {
             System.out.print(n.data+" ");
            n=n.next;
        }
        System.out.println(" ");
       }
    }
    void insertnode(int data, int pos)
    { 
        Node curr=head;
        Node n=new Node(data);
        if(pos==1)
        {
            n.next=head;
            head=n;
        }
        else{
            
        
        for(int i=1;i<pos-1;i++)
        {
            curr=curr.next;
            
        }
        
        n.next=curr.next;
        curr.next=n;
        }
    }
    void arrangement()
    {
        Node slowptr=head;
       while(slowptr.next!=null&&slowptr.next.next!=null)
        {
            System.out.println("slowptr :"+slowptr.data) ;
              Node next=slowptr.next;
              Node fastptr=slowptr;
              Node prev=null;
              while(fastptr.next!=null)
            {
                 prev=fastptr;
                 fastptr=fastptr.next;
            }
              slowptr.next=fastptr;
              fastptr.next=next;
              prev.next=null;
             slowptr=slowptr.next.next;
         }
    }

    public static void main(String args[])
    {
        
        loopinlinkedlist obj=new loopinlinkedlist();
        obj.head=new Node(1);
        Node second1=new Node(2);
        Node third1=new Node(3);
       
        obj.head.next=second1;
        second1.next=third1;
         obj.insertnode(4,2);
         obj.insertnode(5,2);
         //obj.insertnode(6,2);
       
         obj.print(obj.head);
         obj.arrangement();
         System.out.println("NOOOOOOO") ;
         obj.print(obj.head);
    }
}