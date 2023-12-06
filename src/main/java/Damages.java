import java.io.Console;
import java.util.Random;

public class Damages {
    static boolean hasDamage = false;
    static String[] devices = { "Computer", "D. S. Probe", "S. R. Sensors", "L. R. Sensors", "Subspace Radio",
            "Engine" };
    static int[] deviceHealth = { 100, 100, 100, 100, 100, 100 };

    static void damageReport(Console con) {
        if (!hasDamage) {
            con.printf("All devices functional.\n\n");
            return;
        } else {
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

    static int hasDamage(String device) {
        boolean inArray = false;
        for (String element : devices) {
            if (element == device) {
                inArray = true;
                break;
            }
        }
        if (inArray) {
            int ind = 0;
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] == device)
                    ind = i;
            }

            if (deviceHealth[ind] == 100)
                return 0;
            else
                return 1;

        }

        else
            return 2;
    }
}
