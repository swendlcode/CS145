public class GuitarHero {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        Guitar g = new Piano();
        Keyboard keyboard = new Keyboard();


        while (true) {
            // Check if the user has typed a key and process it
            if (keyboard.hasNextKeyPlayed()) {
                char key = Character.toLowerCase(keyboard.nextKeyPlayed());
                if (g.hasString(key)) {
                    g.pluck(key);
                } else {
                    System.out.println("bad key: " + key);
                }
            }

            // Send the result to the sound card
            StdAudio.play(g.sample());
            g.tic();
        }
    }
}
