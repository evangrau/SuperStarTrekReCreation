import java.io.Console;
import java.util.Random;

// This class holds all the information regarding the physical playspace
// the player can navigate through

class Map {
    private int quadSize, sectSize;
    private int[][] lrMap;
    private char[][][][] globalMap;
    private double starbaseRate = 0.01, klingonRate = 0.02, commanderRate = 0.005, supercommanderRate = 0.001,
            planetRate = 0.005, romulonRate = 0.005, starRate = 0.1;
    private static Random rand = new Random();

    public Map(int qSize, int sSize) {
        // create map
        quadSize = qSize;
        sectSize = sSize;

        lrMap = new int[quadSize][quadSize];
        globalMap = new char[quadSize][quadSize][sectSize][sectSize];
    }

    public char[][][][] getMap() {
        return globalMap;
    }

    public void populate(int shipxq, int shipyq, int shipxs, int shipys) { // populating the map
        boolean ship;
        for (int i = 0; i < quadSize; i++) {
            for (int j = 0; j < quadSize; j++) {
                for (int k = 0; k < sectSize; k++) {
                    for (int l = 0; l < sectSize; l++) {
                        if (shipxq == i && shipyq == j && shipxs == k && shipys == l) {
                            globalMap[i][j][k][l] = 'E';
                        } else if (Math.random() < starbaseRate) {
                            globalMap[i][j][k][l] = 'B';
                        } else if (Math.random() < klingonRate) {
                            globalMap[i][j][k][l] = 'K';
                        } else if (Math.random() < commanderRate) {
                            globalMap[i][j][k][l] = 'C';
                        } else if (Math.random() < supercommanderRate) {
                            globalMap[i][j][k][l] = 'S';
                        } else if (Math.random() < planetRate) {
                            globalMap[i][j][k][l] = 'P';
                        } else if (Math.random() < romulonRate) {
                            globalMap[i][j][k][l] = 'R';
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
}