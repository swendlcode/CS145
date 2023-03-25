import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class GuitarHeroLayout {
    private static final String[] KEY_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final Color[] KEY_COLORS = {Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE};

    public static final int NUM_SAMPLES = 400;
    private static final double KEY_WIDTH = 0.05; // fraction of screen width
    private static final double KEY_HEIGHT = 0.2; // fraction of screen height

    public GuitarHeroLayout(Guitar guitar) {
    }



    public static void keyboard() {
        StdDraw.setCanvasSize(1200, 800);
        int numKeys = KEY_NAMES.length * 2; // 2 keys per octave (white and black)
        double keySpacing = (1.0 - numKeys * KEY_WIDTH) / (numKeys - 1); // space between keys

        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        boolean[] keyPlayed = new boolean[37]; // to keep track of played keys
        Color[] keyColors = new Color[numKeys]; // to keep track of the colors of the keys

        for (int i = 0; i < numKeys; i++) {
            double x = i * (KEY_WIDTH + keySpacing);
            double y = 0;
            double width = KEY_WIDTH;
            double height = KEY_HEIGHT;
            Color color = KEY_COLORS[i % 12];

            if (color == Color.BLACK) {
                y = (KEY_HEIGHT - 0.2);
            }

            keyColors[i] = color; // save the original color of the key
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(x + width / 2.0, y + height / 2.0, width / 2.0, height / 2.0);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.rectangle(x + width / 2.0, y + height / 2.0, width / 2.0, height / 2.0);

            // Draw key label
            String keyName = KEY_NAMES[i % 12];
            double labelX = x + width / 2.0;
            double labelY = y + height / 2.0 + 0.05;
            if (color == Color.WHITE) {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(labelX, labelY - 0.1, keyName);
            } else {
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.text(labelX, labelY, keyName);
            }
        }
    }


    public static void waveSound(Guitar guitar) {
        int NUM_SAMPLES = 400;
        // Initialize StdDraw canvas and set scale
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, NUM_SAMPLES);
        StdDraw.setYscale(-1, 1);


        while (true) {
            // Read user input and pluck the corresponding strings
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (guitar.hasString(key)) {
                    guitar.pluck(key);
                }
            }

            // Update the sound wave and draw it on the screen
            double[] y = new double[NUM_SAMPLES];
            for (int i = 0; i < NUM_SAMPLES; i++) {
                double sample = guitar.sample();
                StdAudio.play(sample);
                y[i] = sample;
                guitar.tic();
            }
            StdDraw.clear(Color.BLACK);
            double[] x = new double[NUM_SAMPLES];
            for (int i = 0; i < NUM_SAMPLES; i++) {
                x[i] = i;
            }
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.polygon(x, y);
            StdDraw.show();
        }
    }
}
