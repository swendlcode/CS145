import java.awt.*;


public class PianoWaveVisualizer {
    public static final int NUM_SAMPLES = 400;

    public static void main(String[] args) {
        // Initialize StdDraw canvas and set scale
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, NUM_SAMPLES);
        StdDraw.setYscale(-1, 1);

        // Create a Piano instance
        Piano piano = new Piano();

        while (true) {
            // Read user input and pluck the corresponding strings
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (piano.hasString(key)) {
                    piano.pluck(key);
                }
            }

            // Update the sound wave and draw it on the screen
            double[] y = new double[NUM_SAMPLES];
            for (int i = 0; i < NUM_SAMPLES; i++) {
                double sample = piano.sample();
                StdAudio.play(sample);
                y[i] = sample;
                piano.tic();
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
