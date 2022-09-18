import java.sql.Array;
import java.util.Random;

public class Meson {

  private int  numberOfFloors;

    Floor[] house;




    public Meson() {
       setNumberOfFloors();
        Passenger.setFloorNumberMax(numberOfFloors);
        this.house=createAHouse();
          }

    private Floor[] createAHouse(){
      Floor[] house=new Floor[numberOfFloors];
     for (int i=0;i<house.length;i++) {
      house[i]=new Floor(i+1);
     }
      return house;
    }


    public void setNumberOfFloors() {
        int nOf= (int) ((Math.random()*10)+2);
        this.numberOfFloors = nOf>10?10:nOf;
    }
}

