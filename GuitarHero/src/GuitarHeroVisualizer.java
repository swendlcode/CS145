

public class GuitarHeroVisualizer {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final double BOX_WIDTH = 400;
    private static final double BOX_HEIGHT = 100;
    private static final int NUM_PLOTS = 500;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < strings.length; i++) {
            int N = (int) (440.0 * Math.pow(2.0, (double) (i - 24) / 12));
            strings[i] = new GuitarString(N);
        }

        double[] y = new double[NUM_PLOTS];
        double[] time = new double[NUM_PLOTS];
        for (int i = 0; i < NUM_PLOTS; i++) {
            time[i] = i;
        }

        int timestep = 0;

        while (true) {
            StdDraw.clear();

            char key = '.';
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0) {
                    GuitarString string = strings[index];
                    string.pluck();
                    timestep = 0; // Reset the timestep when a key is pressed
                }
            }

            double sample = 0.0;
            for (GuitarString gs : strings) {
                sample += gs.sample();
                gs.tic();
            }
            StdAudio.play(sample);

            y[timestep] = sample;

            if (timestep < NUM_PLOTS - 1) {
                timestep++;
            } else {
                drawWave(time, y);
            }

            StdDraw.show();
        }
    }

    private static void drawWave(double[] time, double[] samples) {
        double xStart = WIDTH / 2 - BOX_WIDTH / 2;
        double yStart = HEIGHT / 2 - BOX_HEIGHT / 2;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(WIDTH / 2, HEIGHT / 2, BOX_WIDTH / 2, BOX_HEIGHT / 2);

        int numSamples = samples.length;
        double xScale = BOX_WIDTH / numSamples;
        double yScale = BOX_HEIGHT / 2;

        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < numSamples - 1; i++) {
            double x1 = xStart + i * xScale;
            double y1 = yStart + samples[i] * yScale + BOX_HEIGHT / 2;
            double x2 = xStart + (i + 1) * xScale;
            double y2 = yStart + samples[i + 1] * yScale + BOX_HEIGHT / 2;
            StdDraw.line(x1, y1, x2, y2);
        }
    }
}
