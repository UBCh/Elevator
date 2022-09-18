import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;

public class Floor {


  private int floorNumber;
  int queueSize= (int) ((Math.random()*5)+2);
    Deque<Passenger> queueForElevator;
   Deque <Passenger>  queueCameOutOfTheElevator=new ArrayDeque<>();



    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.queueForElevator=createQueue();
           }

 private Deque <Passenger> createQueue(){
     Deque <Passenger>  queue=new ArrayDeque<>();
     for (int i = 1; i < queueSize; i++) {
             queue.add(new Passenger());
     }
        return queue ;
 }

    public Deque <Passenger> getQueueForElevator() {
        return queueForElevator;
    }

    public void setQueueForElevator(Deque<Passenger> queueForElevator) {
        this.queueForElevator = queueForElevator;
    }
}
