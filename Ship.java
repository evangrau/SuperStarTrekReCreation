import java.io.Console;
import java.util.Random;
/*
 * This file is for handling all variables and commands that have anything to do
 * with the player controlled ship, mainly focused on tracking the ships status,
 * coordinates, systems, and damages.
 */

class Ship {
    // all variables that belong to the ship
    private int quadx, quady, sectx, secty;
    Random rand = new Random();

    public Ship() { // default constructor, will only be used for testing
        quadx = 0;
        quady = 0;
        sectx = 0;
        secty = 0;
    }

    public Ship(int qx, int qy, int sx, int sy) { // constructor
        quadx = qx;
        quady = qy;
        sectx = sx;
        secty = sy;
    }

    public void print() {
        System.out.println(String.format("X Quadrant: %d; Y Quadrant: %d; X Sector: %d; Y Sector: %d", quadx, quady,
                sectx, secty));
    }

    public void move(int qx, int qy, int sx, int sy) {
        quadx = qx;
        quady = qy;
        sectx = sx;
        secty = sy;
    }
}