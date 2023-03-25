import java.util.*;

public class AssassinManager {
    /**
     * A reference to the first node in the kill ring, which represents the current state of the game.
     * This node is the head of the linked list that contains the remaining players who have not been killed.
     */
    private AssassinNode killRing;

    /**
     * A reference to the first node in the graveyard, which represents the list of players who have been killed.
     * This node is the head of the linked list that contains the players who have been removed from the kill ring.
     */
    private AssassinNode graveyard;


    /**
     * Creates a new instance of the AssassinManager class with the specified list of player names.
     *
     * @param names A list of player names that will be used to initialize the game. The list cannot be null or empty.
     * @throws IllegalArgumentException If the list of names is null or empty.
     */
    public AssassinManager(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("List of names cannot be null or empty.");
        }

        // Create the first node in the kill ring using the first name in the list
        killRing = new AssassinNode(names.get(0));
        AssassinNode current = killRing;

        // Create the rest of the nodes in the kill ring using the remaining names in the list
        for (int i = 1; i < names.size(); i++) {
            AssassinNode next = new AssassinNode(names.get(i));
            current.next = next;
            current = next;
        }
    }


    /**
     * Prints the current state of the kill ring to the console, showing which players are still alive and who they are stalking.
     * If the kill ring is empty, no output is printed to the console.
     */
    public void printKillRing() {
        if (killRing == null) {
            return;
        }

        // Traverse the kill ring linked list and print out each node's name and who they are stalking
        AssassinNode current = killRing;
        while (current.next != null) {
            System.out.printf("    %s is stalking %s%n", current.name, current.next.name);
            current = current.next;
        }

        // Print the last node's name and who they are stalking (which is the first node in the list)
        System.out.printf("    %s is stalking %s%n", current.name, killRing.name);
    }


    /**
     * Prints the list of killed players to the console, showing who killed them.
     * If the graveyard is empty, no output is printed to the console.
     */
    public void printGraveyard() {
        if (graveyard == null) {
            return;
        }

        // Traverse the graveyard linked list and print out each node's name and who killed them
        AssassinNode current = graveyard;
        while (current != null) {
            System.out.printf("    %s was killed by %s%n", current.name, current.killer);
            current = current.next;
        }
    }


    /**
     * Checks if the kill ring contains a player with the specified name.
     *
     * @param name The name of the player to check for in the kill ring.
     * @return true if the kill ring contains a player with the specified name, false otherwise.
     */
    public boolean killRingContains(String name) {
        AssassinNode current = killRing;
        while (current != null) {
            if (name.equalsIgnoreCase(current.name)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }


    /**
     * Checks if the graveyard contains a player with the specified name.
     *
     * @param name The name of the player to check for in the graveyard.
     * @return true if the graveyard contains a player with the specified name, false otherwise.
     */
    public boolean graveyardContains(String name) {
        // Traverse the graveyard linked list and check if any node's name matches the specified name
        AssassinNode current = graveyard;
        while (current != null) {
            if (name.equalsIgnoreCase(current.name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    /**
     * Checks if the game is over, i.e., if there is only one player left in the kill ring.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean gameOver() {
        // Check if the kill ring is not null and if it contains only one node
        return killRing != null && killRing.next == null;

    }



    /**
     * Returns the name of the winner of the game, i.e., the name of the last player remaining in the kill ring.
     *
     * @return The name of the winner, or null if the game is not yet over.
     */
    public String winner() {
        // If the game is not over, return null; otherwise, return the name of the player in the kill ring
        if (!gameOver()) {
            return null;
        }
        return killRing.name;
    }


    /**

     Records the killing of the player with the given name, transferring the player from

     the kill ring to the graveyard. If the given name is null or empty, or the game is already over

     @param name The name of the player to kill.

     @throws IllegalArgumentException If the given name is null or empty, or the game is already gameOver.
     */
    public void kill(String name) {
        if (gameOver()) {
            throw new IllegalArgumentException("The game is already over");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // Search for the previous to kill
        AssassinNode current = killRing;
        AssassinNode previous = killRing;
        if (current.name.equalsIgnoreCase(name)) {
            while (current.next != null) {
                current = current.next;
            }
            previous.killer = current.name;
            killRing = killRing.next;
        } else {
            while (!current.next.name.equalsIgnoreCase(name)) {
                current = current.next;
                if (current.next == null) {
                    throw new IllegalArgumentException(name + " is not in the kill ring");
                }
            }
            current.next.killer = current.name;
            previous = current.next;
            current.next = current.next.next;
        }

        // Add the killed player to the graveyard
        if (graveyard == null) {
            graveyard = previous;
            graveyard.next = null;
        } else {
            previous.next = graveyard;
            graveyard = previous;
        }
    }





    /*========================***=============================*\

    Optional practice: just get the size of each list, which
    can be useful for tracking the progress of the game in the
    future if needed. :)

    \*========================***=============================*/

    /**

     Returns the number of players in the kill ring.
     @return the size of the kill ring
     */
    public int getKillRingSize() {
        int size = 0;
        AssassinNode current = killRing;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    /**

     Returns the number of players in the graveyard.
     @return the size of the graveyard
     */
    public int getGraveyardSize() {
        int size = 0;
        AssassinNode current = graveyard;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
