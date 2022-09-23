package dispatcher;

import entitys.Elevator;
import entitys.Floor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Loader {

    String pathFile="src/main/resources/config.properties";
    FileInputStream fis;
    Properties property = new Properties();


    public void setConfigurations(){
            int limitLastFloor = 0;
            int limitQueue = 0;
            int maximumLoad = 0;

        try {
            fis = new FileInputStream(pathFile);
            property.load(fis);
          limitLastFloor = Integer.parseInt(property.getProperty("maximumFloor"));
            limitQueue = Integer.parseInt(property.getProperty("queueSize"));
           maximumLoad = Integer.parseInt(property.getProperty("elevatorMaximumLoad"));
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        Dispatcher.setLimitLastFloor(limitLastFloor);
        Floor.setLimitQueue(limitQueue);
        Elevator.setMaximumLoad(maximumLoad);
    }


    }





