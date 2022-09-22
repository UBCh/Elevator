import java.io.*;

public class Writer {
     private String path = "src/main/resources/output";


    public void writingToFile(String[] toStringForWrite) {

        StringBuilder stringBuilder = new StringBuilder();
        String[] tmp = toStringForWrite;
        for (int i = tmp.length-1; i >=0; i--) {
            stringBuilder.append(tmp[i]).append("\n");
           }
            try {
                FileWriter writer = new FileWriter("output.txt", true);
                stringBuilder.append("++++++++++++++++++++++step++++++++++++++++++++++++++++++++").append("\n");
                writer.append(stringBuilder);
                writer.close();
                } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
