package entitys;

import java.util.Arrays;

public class Elevator {

    static int maximumLoad;
    public int[] elevatorPassengers = new int[maximumLoad];


    public Elevator() {
    }

    public static void setMaximumLoad(int maxLoad) {
        maximumLoad = maxLoad;
    }

    public int[] sortPassengers() {
        if (elevatorPassengers.length >= 2) {
            Arrays.sort(elevatorPassengers);
            elevatorPassengers = Arrays.stream(elevatorPassengers).filter(x -> x > 0).toArray();
        }
        return elevatorPassengers;
    }


    public void deletePassenger(int passenger) {
        int[] tmp = new int[elevatorPassengers.length - 1];
        int j = 0;
        for (int i = 0; i < tmp.length; i++) {
            var g = elevatorPassengers[j];
            if (elevatorPassengers[j] == passenger) {
                j = i + 1;
                tmp[i] = elevatorPassengers[j];
            } else {
                tmp[i] = elevatorPassengers[j];
            }
            j++;
        }
        elevatorPassengers = tmp;
    }

    public void addPassenger(int passenger) {
        int[] tmp = new int[elevatorPassengers.length + 1];
        System.arraycopy(elevatorPassengers, 0, tmp, 0, elevatorPassengers.length);
        elevatorPassengers = tmp;
        elevatorPassengers[tmp.length - 1] = passenger;
    }

    public boolean emptyElevator() {
        boolean empty = Arrays.stream(elevatorPassengers).allMatch(x -> x == 0);
        if (empty) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        int count = 5 - elevatorPassengers.length;
        if (count > 0) {
            int[] tmp = Arrays.copyOf(elevatorPassengers, elevatorPassengers.length + count);
            return Arrays.toString(tmp);
        }
        return Arrays.toString(elevatorPassengers);
    }
}
