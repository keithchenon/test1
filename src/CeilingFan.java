public class CeilingFan {
    private Speed speed;

   public CeilingFan() {
       speed = Speed.OFF;
   }

    public Speed increase() {
       switch (speed) {
           case OFF: speed= Speed.ONE; break;
           case ONE: speed= Speed.TWO;break;
           case TWO: speed =Speed.THREE; break;
           case THREE: speed=Speed.OFF; break;
       }
       return speed;
    }

    public Speed reverse() {
        switch (speed) {
            case OFF: speed= Speed.THREE; break;
            case ONE: speed= Speed.OFF; break;
            case TWO: speed=Speed.ONE; break;
            case THREE: speed= Speed.TWO;break;
        }
        return speed;
    }

 public static void main(String[] args) {
       CeilingFan ceilingFan = new CeilingFan();
       System.out.println(ceilingFan.increase());
       System.out.println(ceilingFan.increase());
       System.out.println(ceilingFan.increase());
       System.out.println(ceilingFan.increase());
     System.out.println(ceilingFan.reverse());
     System.out.println(ceilingFan.reverse());
     System.out.println(ceilingFan.reverse());
     System.out.println(ceilingFan.reverse());
 }

}
