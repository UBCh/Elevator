import java.util.Random;

public class Passenger {


    static int floorNumberMax;
    private int floorNumber;




    public Passenger() {
        setFloorNumber();
          }


    public static void setFloorNumberMax(int go) {
        floorNumberMax = go;

    }

    public int getFloorNumber() {
        return floorNumber;
    }

    private void setFloorNumber() {
        int fNNumber= (int) ((Math.random()*5)+2);
     floorNumber= fNNumber>floorNumberMax?floorNumberMax:fNNumber;

    }

    @Override
    public String toString() {
       String result= String.valueOf(floorNumber);

        return result;
    }
}
