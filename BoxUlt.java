class Box
{
 double height,width, depth ;
 Box(Box ob)// clone constructor
 {
  width = ob.width ;
  depth = ob.depth ;
  height = ob.height ;
 }

 Box(double w, double h, double d)
 {
  width = w ;
  height = h ;
  depth = d ;
 }
 Box()
 {
  width = -1 ;
  height = -1 ;
  depth = -1 ;
 }
 Box (double len)
 {
  width = height = depth = len ;
  }
 
 double volume()
 {
  return width*height*depth ;
 }
}
class BoxWeight extends Box
{
 double weight ;
 BoxWeight(double w, double h, double d, double m)
 {
  width = w ;
  height = h ;
  depth = d ;
  weight = m ;
 }
}

class DemoBoxWeight
{
 public static void main(String args[])
 {
  BoxWeight mybox1 = new BoxWeight(10,20,15,34.3) ;
  BoxWeight mybox2 = new BoxWeight(2,3,4,0.076) ;
  double vol1 = mybox1.volume() ;
  double vol2 = mybox2.volume() ;
  System.out.println("Volume of the first box is :"+vol1) ;
  System.out.println("Volume of the second box is :"+vol2) ;
  System.out.println("Weight of box1 is :"+mybox1.weight+"\nWeight of box2 is :"+mybox2.weight) ;
 }
} 