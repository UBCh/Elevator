package entitys;

import java.util.ArrayDeque;
import java.util.Deque;

public class Floor {

    static int limitQueue;
    private int floorNumberMax;
    private int queueSize = (int) (Math.random() * limitQueue);
    private Deque<Integer> queueForElevator;
    private Deque<Integer> queueCameOutOfTheElevator = new ArrayDeque<>();

    public Floor(int floorNumberMax) {
        this.floorNumberMax = floorNumberMax;
        this.queueForElevator = createQueue();
    }

    public static void setLimitQueue(int limQueue) {
       limitQueue = limQueue;
    }

    public Deque<Integer> getQueueCameOutOfTheElevator() {
        return queueCameOutOfTheElevator;
    }

    public void setQueueCameOutOfTheElevator(Deque<Integer> queue) {
        this.queueCameOutOfTheElevator = queue;
    }

    public Deque <Integer> getQueueForElevator() {
        return queueForElevator;
    }

    public void setQueueForElevator(Deque<Integer> queueForElevator) {
        this.queueForElevator = queueForElevator;
    }



    private Deque <Integer> createQueue(){
        Deque <Integer>  queue=new ArrayDeque<>();
        for (int i = 1; i < queueSize; i++) {
             queue.add(setFloorNumberPassenger());
        }
        return queue ;
     }


    private int setFloorNumberPassenger() {
        int fNNumber= (int) ((Math.random()*floorNumberMax)+2);
       int result= fNNumber>floorNumberMax?floorNumberMax:fNNumber;
        return result;
    }

      public String toString() {
          String result = getQueueForElevator().toString();
          result = result.replace('[', ' ');
          result = result.replace(']', ' ');
          result = result.replace(',', ' ');
          result.trim();
          return result;


      }



}
