/**
 * @author Pavel Stepanov
 * @version 1.0
 */

import java.awt.*;
import java.util.Random;

public class Lion extends Critter {
    private Color ColorType;
    private final Color RED = Color.RED; private final Color GREEN = Color.GREEN; private final Color BLUE = Color.BLUE;

    private final Random RandomOfColor = new Random();
    private int xyzDirection = 0;

    public Lion() {
        int RandomWheel = RandomOfColor.nextInt(3);
        if (RandomWheel == 1) {ColorType = RED; }
        if (RandomWheel == 1) {ColorType = GREEN; }
        if (RandomWheel == 1) {ColorType = BLUE; }
    }

    /**
     *
     * @param info
     * @return color for the object
     */


    //================= GET MOVE METHOD =================\\
    public Action getMove(CritterInfo info) {
        xyzDirection++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if(info.getFront() == Neighbor.WALL && info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    /**
     *
     * @return action of the object. HOP or INFECT or object WALL or OTHER
     */


    //================= GET COLOR =================\\
    public Color getColor() {
        if(xyzDirection > 3) {
            int RandomWheel = RandomOfColor.nextInt(3);
            if (RandomWheel == 1) { ColorType = RED; }
            if (RandomWheel == 1) { ColorType = GREEN; }
            if (RandomWheel == 1) { ColorType = BLUE; }
            xyzDirection = 0;
        }
        return ColorType;
    }

    /**
     * @return final color for the object
     */


    //================= TO STRING METHOD =================\\
    public String toString() {return "L"; }
    /**
     * @return "L" for the object
     */
}
