import java.io.Console;
import java.util.Arrays;

public class srscan {
    private static char[][] srScan = new char[10][10]; // minor inefficiency here
    private static char[][][][] fullMap = SST.map.getMap();
    private static Ship sr_ship = SST.ship;

    public static void srReport(Console con) {
        int XQuad = sr_ship.getXQuad();
        int YQuad = sr_ship.getYQuad();

        srScan = fullMap[XQuad][YQuad];

        // for (r = 1; r <= 10; r++)
        // for (c = 1; c <= 10; c++) {
        // // chScan[r-1][c-1] = '.';
        // chScan[r - 1][c - 1] = '\u00B7';
        // }
        int c = 0;
        int r = 0;
        con.printf("\nShort-range scan :\n\n");
        boolean leftside = true;
        boolean rightside = true;
        int kling = 0;

        for (c = 0; c < 10; c++) {
            for (r = 0; r < 10; r++) {
                if (srScan[c][r] == 'K')
                    kling++;
            }
        }

        // print column header
        con.printf("        ");
        for (c = 1; c <= 10; c++)
            con.printf("%1d ", c);
        con.printf("\n");

        for (r = 1; r <= 10; r++) {
            if (leftside) {
                con.printf("    %2d  ", r);

                for (c = 1; c <= 10; c++)
                    con.printf("%c ", srScan[r][c]);
            }

            if (rightside) {
                con.printf(" ");

                switch (r) {
                    case 1:
                        con.printf("Stardate      %.1f", 2516.3);
                        break;
                    case 2:
                        con.printf("Condition     %s", "RED");
                        break;
                    case 3:
                        con.printf("Position      %d - %d, %d - %d", sr_ship.getXQuad(), sr_ship.getYQuad(),
                                sr_ship.getXSect(), sr_ship.getYSect());
                        break;
                    case 4:
                        con.printf("Life Support  %s", "DAMAGED, Reserves = 2.30");
                        break;
                    case 5:
                        con.printf("Warp Factor   %.1f", 5.0);
                        break;
                    case 6:
                        con.printf("Energy        %.2f", 2176.24);
                        break;
                    case 7:
                        con.printf("Torpedoes     %d", 3);
                        break;
                    case 8:
                        con.printf("Shields       %s, %d%% %.1f units", "UP", 42, 1050.0);
                        break;
                    case 9:
                        con.printf("Klingons Left %d", kling);
                        break;
                    case 10:
                        con.printf("Time Left     %.2f", 3.72);
                        break;
                }
            }

            con.printf("\n");
        }

        con.printf("\n");
    }
}
