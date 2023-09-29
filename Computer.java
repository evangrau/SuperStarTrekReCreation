import java.io.Console;
import java.util.Scanner;

public class Computer {
    private static int deltaQx, deltaQy;
    public static int deltaSx, deltaSy = 0;
    private static Console con;
    static Scanner myObj = new Scanner(System.in);
    // temporary values
    private static double starDate = 2516.3;
    private static int[] position = { 5, 1, 2, 4 };
    private static int x = 0;
    public static double warpF = 5.0;
    public static double energy = 5000.0;
    public static double energy_used;

    public static void computer(int... optionalNumbers) {
        con = System.console();
        if (optionalNumbers.length == 2 || optionalNumbers.length >= 4) {
            deltaQx = (optionalNumbers[0] - position[0]) * 10;
            deltaQy = (optionalNumbers[1] - position[1]) * 10;

            if (optionalNumbers.length >= 4) {
                deltaSx = optionalNumbers[2] - position[2];
                deltaSy = optionalNumbers[2] - position[2];
            }
        }

        else {
            con.printf("\nBeg your pardon, Captain?\n\n");
            return;
        }

        // double dist = Math.sqrt( Math.abs((deltaQx ^ 2) - (deltaQy ^ 2))
        // Math.abs((deltaSx ^ 2) - (deltaSy ^ 2)) )

        double a = Math.abs((deltaQx ^ 2) - (deltaQy ^ 2));
        double b = Math.abs((deltaSx ^ 2) - (deltaSy ^ 2));
        double dist = Math.sqrt(a + b);

        con.printf("Answer \"no\" if you don't know the value:\n");
        while (true) {
            if (x == 0) {
                con.printf("Time or arrival date? ");
                if (!myObj.hasNextInt() || !myObj.hasNextDouble()) {
                    x = eventCaseNo(x);
                }

                else {
                    double destinationTime = myObj.nextDouble();
                    if (destinationTime < starDate) {
                        eventTimeBehind();
                        break;
                    } else {
                        double deltaT = destinationTime - starDate;
                        // Warp drive requires (distance)*(warp factor cubed) units of energy
                        // to travel at a speed of (warp factor squared)/10 quadrants per stardate.
                        double warp_speed = dist / deltaT;
                        warpF = warp_speed * 10;
                        warpF = Math.sqrt(warpF);

                        energy_used = dist * (Math.pow(warpF, 3));
                        results(0, deltaT);
                        break;
                    }

                }
            }

            else if (x == 1) {
                con.printf("Warp Factor? ");
                if (!myObj.hasNextInt() || !myObj.hasNextDouble()) {
                    x = eventCaseNo(x);
                }

                else {
                    warpF = myObj.nextDouble();
                    energy_used = dist * (Math.pow(warpF, 3));
                    double warp_speed = (Math.pow(warpF, 2) / 10);
                    double deltaT = dist / warp_speed;
                    results(1, deltaT);
                    break;
                }
            }
            String invalid_input = myObj.next();
        }

        con.printf("New warp factor to try?");
        while (myObj.hasNextDouble()) {
            warpF = myObj.nextDouble();
            energy_used = dist * (Math.pow(warpF, 3));
            double warp_speed = (Math.pow(warpF, 2) / 10);
            double deltaT = starDate + (dist / warp_speed);
            results(1, deltaT);
            con.printf("New warp factor to try?");
        }
    }

    private static int eventCaseNo(int y) {
        if (y == 0) {
            return y + 1;
        }

        else {
            con.printf("Captain, certainly you can give me one of these.\n");
            return 0;
        }
    }

    private static void eventTimeBehind() {
        con.printf("Remaining Energy will be %.1f.\n", 5000.0);
        con.printf("any warp speed is adequate.\n");
        con.printf("Unfortunately, the Federation will be destroyed by then.\n");
    }

    public static void results(int b, double dT) {
        if (b == 0) {
            con.printf("Remaining energy will be %.1f\n", (energy - energy_used));
            con.printf("Minimum warp needed is %.2f\n", warpF);
            con.printf("And we will arrive at stardate %.1f\n", dT);
        }

        if (b == 1) {
            con.printf("Remaining energy will be %.1f\n", (energy - energy_used));
            con.printf("and we will arrive at stardate %.1f\n", dT);
        }
    }

    // public static void main(String[] args) {

    // computer(4, 3, 2, 1);

    // }
}
