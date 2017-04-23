import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by benma on 4/23/2017.
 */
public class Main extends Application {
    public static boolean SaveImage = false;
    public static boolean Average = true;
    public static int NMostCommon = 0;

    public static final Color[] colors = {
            Color.web("#FFFFFF"),
            Color.web("E4E4E4"),
            Color.web("#888888"),
            Color.web("#222222"),
            Color.web("#FFA7D1"),
            Color.web("E50000"),
            Color.web("E59500"),
            Color.web("A06A42"),
            Color.web("E5D900"),
            Color.web("94E044"),
            Color.web("02BE01"),
            Color.web("00E5F0"),
            Color.web("0083C7"),
            Color.web("0000EA"),
            Color.web("E04AFF"),
            Color.web("820080")
    };

    public static final int[][] colorVals = {
            {255, 255, 255},
            {228, 228, 228},
            {136, 136, 136},
            {34, 34, 34},
            {255, 167, 209},
            {229, 0, 0},
            {229, 149, 0},
            {160, 106, 66},
            {229, 217, 0},
            {148, 224, 68},
            {2, 190, 1},
            {0, 129, 240},
            {0, 131, 199},
            {0, 0, 234},
            {224, 74, 255},
            {130, 0, 128}
    };

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Canvas canvas = new Canvas(1000, 1000);
        pane.getChildren().add(canvas);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        GraphicsContext gfx = canvas.getGraphicsContext2D();
        int[][][] map = new int[1000][1000][17];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 17; k++) {
                    map[i][j][k] = 0;
                }
            }
        }
        PixelWriter pr = gfx.getPixelWriter();
        DataLoader.fillDataMap(map);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (Average) pr.setColor(i, j, getAverageColor(map[i][j]));
                else pr.setColor(i, j, getNMostCommon(map[i][j], NMostCommon));
            }
        }

        if (SaveImage){
            WritableImage image = pane.snapshot(new SnapshotParameters(), null);

            // TODO: probably use a file chooser here
            File file = new File("rplace." + System.currentTimeMillis() +  ".png");

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Color getAverageColor(int[] values){
        double total = 0;
        double red = 0;
        double green = 0;
        double blue = 0;

        for (int i = 0; i < 16; i++) {
            if (values[i] < 0) values[i] = 0;
            total += values[i];
        }

        for (int i = 0; i < 16; i++) {
            double perc = values[i]/total;
            red += colorVals[i][0]*perc;
            green += colorVals[i][1]*perc;
            blue += colorVals[i][2]*perc;
        }

        if (red < 0) red = 0;
        if (green < 0) green = 0;
        if (blue < 0) blue = 0;
        return Color.rgb((int)red, (int)green, (int)blue);
    }

    public static Color getNMostCommon(int[] vals, int n){
        int[] temp = new int[16];
        for (int k = 0; k < 16; k++) {
            temp[k] = vals[k];
        }
        Arrays.sort(temp);
        int index = 15 - n;
        for (int k = 0; k < 16; k++) {
            if (vals[k] == temp[index]){
                return colors[k];
            }
        }
        return colors[0];
    }

    public static void main(String[] args){
        launch();
    }
}
