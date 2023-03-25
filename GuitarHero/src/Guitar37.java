import java.util.*;
public class Guitar37 implements Guitar {


   public static final String KEYBOARD =
           "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   public static GuitarString[] strings;
   private int time;

   /**

    Constructs a Guitar37 object with 37 guitar strings.
    Each string is assigned a frequency corresponding to the 37
    keys of a guitar, based on a musical formula.
    */

   public Guitar37() {

      // Initialize an array of GuitarString objects with length equal to the length of the KEYBOARD string
      strings = new GuitarString[KEYBOARD.length()];

      // Loop through each index of the strings array
      for (int i = 0; i < strings.length; i++) {

         // Calculate the frequency of the note corresponding to the current index
         double frequency = 440.0 * Math.pow(2, (i - 24.0) / 12.0);

         // Create a new GuitarString object with the calculated frequency and store it in the strings array at the current index
         strings[i] = new GuitarString(frequency);
         //GuitarHeroLayout.keyboard();
      }
   }




   public void playNote(int pitch) {
      // Calculate the index of the string corresponding to the pitch
      int index = pitch + 24;
      // Check if the index is out of bounds, and return if it is
      if (index < 0 || index >= strings.length) {
         return;
      }
      // Pluck the string corresponding to the pitch
      strings[index].pluck();
   }


   /**

    Determines if a given character key is mapped to a guitar string in this instance of the Guitar37 class.
    @param key the character key to check
    @return true if the character key is mapped to a guitar string, false otherwise
    */
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) >= 0;
   }

   /**

    Plucks the guitar string associated with the given key.
    @param key the key that corresponds to the guitar string to be plucked
    @throws IllegalArgumentException if the given key does not correspond to a valid guitar string
    */
   public void pluck(char key) throws IllegalArgumentException {
      // Find the index of the key in the KEYBOARD string
      int index = KEYBOARD.indexOf(key);
      // If the index is valid, pluck the corresponding guitar string
      if (index >= 0) {
         strings[index].pluck();
      } else {
         // Otherwise, throw an IllegalArgumentException
         throw new IllegalArgumentException("Invalid key for plucking guitar string");
      }
   }

   /**

    Returns the sum of the samples of all the guitar strings.
    @return the sum of the samples of all the guitar strings.
    */
   public double sample() {
   // Initialize a sum to accumulate the samples of all the guitar strings
      double sum = 0.0;
      // Iterate through all the guitar strings and add their samples to the sum
      for (GuitarString string : strings) {
         sum += string.sample();
      }
      // Return the sum of the samples
      return sum;
   }

   /**

    Advances the simulation one time step by performing a single "tic" on each string in the array of strings.
    It calls the tic() method on each string in the array to update their states and add the new samples to the
    end of the respective buffer. It also updates the global time variable by incrementing it by 1.
    */
   public void tic() {
      // Call the tic() method on each string in the array
      for (GuitarString string : strings) {
         string.tic();
      }
      // Increment the global time variable by 1
      time++;
   }

   /**

    Returns the number of times that the tic() method has been called on this Guitar37 object.
    @return an integer representing the number of times that tic() method has been called
    */
   public int time() {
      return time;
   }
}
