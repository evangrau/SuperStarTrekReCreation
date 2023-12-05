import java.io.Console;

public class Photons {
    static int photonTorpedoes = 10;
    static char[][][][] map = SST.map.getMap();

    public static void fire(Console con) {
        while (photonTorpedoes > 0) {
            con.printf("%d torpedoes left.\n", photonTorpedoes);
            Token tkn = CmdProc.getToken();
            while (tkn.getType() == TokenType.EOL) {
                con.printf("Number of torpedoes to fire-");
                tkn = CmdProc.getToken();
            }

            if (tkn.getType() == TokenType.ALPHA) {
                Commands.huh(con);
                return;
            }

            double t = tkn.getDouble();

            if (t < 0) {
                Commands.huh(con);
                return;
            } else if (t > 3) {
                con.printf("Maximum of 3 torpedoes per burst.\n");
                continue;
            } else {
                int count = 1;
                int[][] sectors = new int[3][2];

                for (int i = 0; i < 3; i++) {
                    sectors[i][0] = -1;
                    sectors[i][1] = -1;
                }

                while (count <= t) {
                    Token tkn1 = CmdProc.getToken();
                    Token tkn2 = CmdProc.getToken();
                    while (tkn1.getType() == TokenType.EOL) {
                        con.printf("Target sector for torpedo number %d-", count);
                        tkn1 = CmdProc.getToken();
                    }

                    if (tkn1.getType() == TokenType.ALPHA || tkn2.getType() == TokenType.ALPHA) {
                        Commands.huh(con);
                        return;
                    }

                    double x = tkn1.getDouble();
                    double y = tkn2.getDouble();

                    if (x < 0 || x > 9 || y < 0 || y > 9) {
                        Commands.huh(con);
                        return;
                    }

                    sectors[count - 1][0] = (int) x;
                    sectors[count - 1][1] = (int) y;

                    count++;
                }

                photonTorpedoes -= count;

                count = 1;
                while (count <= t) {
                    int x = sectors[count - 1][0];
                    int y = sectors[count - 1][1];

                    if (x != -1 && y != -1) {
                        if (map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == 'K' ||
                        map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == 'C' ||
                        map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == 'S') {
                            if (map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == 'K') {
                                con.printf("***Klingon at Sector %d - %d destroyed.\n\n", x, y);
                            }
                            else if (map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == 'C') {
                                con.printf("***Commander at Sector %d - %d destroyed.\n\n", x, y);
                            }
                            else {
                                con.printf("***Super Commander at Sector %d - %d destroyed.\n\n", x, y);
                            }
                            map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] = '.';
                        }
                        else if (map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] == '*') {
                            con.printf("***Star at Sector %d - %d novas.\n\n", x, y);
                            map[SST.ship.getXQuad()][SST.ship.getYQuad()][x][y] = '.';
                        }
                        else {
                            con.printf("Torpedo missed.\n\n");
                        }
                    }

                    count++;
                }
                    
            }
            SST.map.globalMap = map;
        }
        con.printf("No photon torpedoes left.\n");
    }
}
