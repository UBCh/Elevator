import java.util.*;

public class Elevator {

  private final int maximumload=5;

   public TreeSet<Passenger> elevatorPassengers=new TreeSet<>(new Comparator<Passenger>() {
      @Override
      public int compare(Passenger o1, Passenger o2) {
         int floorNumberPassenger1 = o1.getFloorNumber();
         int floorNumberPassenger2=o2.getFloorNumber();
         if(floorNumberPassenger1 < floorNumberPassenger2)
            return -1;

         if(floorNumberPassenger1 > floorNumberPassenger2)
            return 1;

         return 0;
      }
   });

   public Elevator() {
   }

    public int getMaximumload() {
        return maximumload;
    }
}
