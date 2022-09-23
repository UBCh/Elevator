package entitys;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

     private String path = "output.txt";


    public void writingToFile(String[] toStringForWrite) {

        StringBuilder stringBuilder = new StringBuilder();
        String[] tmp = toStringForWrite;
        for (int i = tmp.length-1; i >=0; i--) {
            stringBuilder.append(tmp[i]).append("\n");
           }
            try {
                FileWriter writer = new FileWriter(path, true);
                stringBuilder.append("++++++++++++++++++++++step++++++++++++++++++++++++++++++++").append("\n");
                writer.append(stringBuilder);
                writer.close();
                } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
