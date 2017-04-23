import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by benma on 4/23/2017.
 */
public class DataLoader {

    public static void fillDataMap(int[][][] map){

//        ArrayList<Record> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data\\rplace.csv"))) {
            String line;
            int numlines = 0;
            System.out.print("processing");

            while ((line = br.readLine()) != null) {
                numlines++;
                String [] vals = line.split(",");
                if(numlines == 1) continue;
                if (numlines % 1000000 == 0) System.out.print(".");
                long timeStamp = Long.parseLong(vals[0]);
                int x = Integer.parseInt(vals[2]);
                int y = Integer.parseInt(vals[3]);
                int color = Integer.parseInt(vals[4]);
                if (x < 0 || x > 999 || y < 0 || y > 999 || color < 0 || color > 15) continue;

//                records.add(new Record(x, y, timeStamp, color));
                map[x][y][color]++;
//                if (color != map[x][y][16]) map[x][y][color]++;
//                else map[x][y][color]--;
                map[x][y][16] = color;
            }

//            Collections.sort(records);


            System.out.println("\nFinished! " + numlines + " lines");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
