import java.io.Console;

public class lrscan {
    private static String[][] lrScan = new String[3][3];
    private static char[][] data = new char[3][3];
    private static char[][][][] fullMap = SST.map.getMap();
    private static Ship lr_ship = SST.ship;

    public static void lrReport(Console con) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lrScan[i][j] = "0";
            }
        }
        int xQuad = lr_ship.getXQuad();
        int yQuad = lr_ship.getYQuad();
        con.printf("\nLong-range scan for Quadrant %d - %d\n\n", xQuad, yQuad);

        if (xQuad == 1) {
            for (int i = 0; i < lrScan.length; i++) {
                lrScan[0][i] = "-1";
            }
        }

        if (yQuad == 1) {
            for (int i = 0; i < lrScan.length; i++) {
                lrScan[i][0] = "-1";
            }
        }
        if (xQuad == 8) {
            for (int i = 0; i < lrScan.length; i++) {
                lrScan[2][i] = "-1";
            }
        }

        if (yQuad == 8) {
            for (int i = 0; i < lrScan.length; i++) {
                lrScan[i][2] = "-1";
            }
        }

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {

                // check for edge of map
                if (lrScan[r + 1][c + 1] == "-1") {
                    continue;
                }

                // get data for each quadrant
                data = fullMap[xQuad + r][yQuad + c];

                int klingons = 0;
                int starbases = 0;
                int stars = 0;

                // count klingons, starbases, and stars
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data.length; j++) {
                        switch (data[i][j]) {
                            case 'K':
                                klingons++;
                                break;
                            case 'B':
                                starbases++;
                                break;
                            case '*':
                                stars++;
                                break;
                        }
                    }
                }

                // make 3 digit number for each quadrant
                String quadData = String.valueOf(klingons) + String.valueOf(starbases) + String.valueOf(stars);

                // if no data, there was a supernova
                if (quadData == "000") {
                    quadData = "1000";
                }

                lrScan[r + 1][c + 1] = quadData;
            }
        }

        // print lrscan matrix
        for (int i = 0; i < lrScan.length; i++) {
            con.printf("    ");
            for (int j = 0; j < lrScan.length; j++) {
                con.printf("%s ", lrScan[i][j]);
            }
            con.printf("\n");
        }
        con.printf("\n");
    }
}
