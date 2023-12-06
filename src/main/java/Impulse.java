import java.io.Console;
import java.util.Random;

public class Impulse {
    public static Damages dmgChecker;
    private static Console con;
    private float energy = 3000;
    private float power;
    private static Ship ship = SST.ship;

    public void impulse() {
        con = System.console();
        int damaged = dmgChecker.hasDamage("Engine");
        if (damaged == 1) {
            con.printf("Engineer Scott- \"The impulse engines are damaged, Sir.\"");
        } else {
            if (energy > 30) {
                power = 120;
            } else
                power = 30;

            if (power >= energy) {
                con.printf("First Officer Spock- \"Captain, the impulse engines");
                con.printf("require 20.0 units to engage, plus 100.0 units per");
                if (energy > 30) {
                    con.printf("quadrant.  We can go, therefore, a maximum of ");
                    con.printf((energy - 20) / 10 + " quadrants.\"");
                } else {
                    con.printf("quadrant.  They are, therefore, useless.\"");
                }
                return;
            }
            int qx = ship.getXQuad();
            int qy = ship.getYQuad();
            int sx = ship.getXSect();
            int sy = ship.getYSect();
            String dir = "right";
            if (qx == 0) {
                dir = "right";
            } else if (qx == 7) {
                dir = "left";
            }
            if (dir == "right") {
                ship.move(qx + 1, qy, sx, sy);
            } else
                ship.move(qx - 1, qy, sx, sy);

            energy -= 120;
            return;
        }
    }
}
