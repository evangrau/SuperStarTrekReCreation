import java.io.Console;
import java.util.Random;

// This class holds all the information regarding the physical playspace
// the player can navigate through

class Map {
    private int quadSize, sectSize;
    private int[][] lrMap;
    public char[][][][] globalMap;
    public static int klingonCount = 0;
    public static int starbaseCount = 0;

    private double starbaseRate = 0.001, klingonRate = 0.008, planetRate = 0.005, starRate = 0.06;
    private static Random rand = new Random();

    public Map(int qSize, int sSize) {
        // create map
        quadSize = qSize;
        sectSize = sSize;

        lrMap = new int[quadSize + 1][quadSize + 1];
        globalMap = new char[quadSize + 1][quadSize + 1][sectSize + 1][sectSize + 1];
    }

    public char[][][][] getMap() {
        return globalMap;
    }

    public void populate(int shipxq, int shipyq, int shipxs, int shipys, Console con) { // populating the map
        boolean ship;
        for (int i = 1; i <= quadSize; i++) {
            for (int j = 1; j <= quadSize; j++) {
                for (int k = 1; k <= sectSize; k++) {
                    for (int l = 1; l <= sectSize; l++) {
                        if (shipxq == i && shipyq == j && shipxs == k && shipys == l) {
                            con.printf("The ship is being created at" + i + j + k + l);
                            globalMap[i][j][k][l] = 'E';
                        } else if (Math.random() < starbaseRate) {
                            globalMap[i][j][k][l] = 'B';
                            starbaseCount++;
                        } else if (Math.random() < klingonRate) {
                            globalMap[i][j][k][l] = 'K';
                            klingonCount++;
                        } else if (Math.random() < planetRate) {
                            globalMap[i][j][k][l] = 'P';
                        } else if (Math.random() < starRate) {
                            globalMap[i][j][k][l] = '*';
                        } else {
                            globalMap[i][j][k][l] = '.';
                        }
                    }
                }
            }
        }
    }

    public void removeShip(Ship ship, Console con) {
        globalMap[ship.getXQuad()][ship.getYQuad()][ship.getXSect()][ship.getYSect()] = '.';
    }

    public void updateShip(Ship ship, Console con) {
        globalMap[ship.getXQuad()][ship.getYQuad()][ship.getXSect()][ship.getYSect()] = 'E';
    }

    public int checkKlingonCount(Console con) {
        return klingonCount;
    }
}