import java.io.Console;

public class lrscan {
    private static char[][] lrScan = new char[3][3];
    private static char[][][][] fullMap = SST.map.getMap();
    private static Ship lr_ship = SST.ship;

    public static void lrReport(Console con) {
        int xQuad = lr_ship.getXQuad();
        int yQuad = lr_ship.getYQuad();
        con.printf("\nLong-range scan for Quadrant %d - %d\n\n", xQuad, yQuad);
    }

}
