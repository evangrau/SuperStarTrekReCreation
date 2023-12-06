import java.io.Console;
import java.util.Random;

public class Damages {
    static boolean hasDamage = false;
    static String[] devices = {"Computer", "D. S. Probe", "S. R. Sensors", "L. R. Sensors", "Subspace Radio"};

    static void damageReport(Console con) {
        if (!hasDamage) {
            con.printf("All devices functional.\n\n");
            return;
        }
        else {
            con.printf("\nDEVICE        -REPAIR TIMES-\n");
            con.printf("            IN FLIGHT   DOCKED\n"); 
            int rand = new Random().nextInt(devices.length);
            float flight = new Random().nextFloat() * 5;
            float docked = new Random().nextFloat() * 5;
            if (docked > flight) {
                float temp = flight;
                flight = docked;
                docked = temp;
            }
            con.printf("  %s     %.2f     %.2f\n\n", devices[rand], flight, docked);
        }
    }
}
