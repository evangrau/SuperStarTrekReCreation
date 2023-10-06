import java.io.Console;

enum Command {
    SRSCAN,
    LRSCAN,
    PHASERS,
    PHOTONS,
    MOVE,
    SHIELDS,
    DOCK,
    DAMAGES,
    CHART,
    IMPULSE,
    REST,
    WARP,
    STATUS,
    SENSORS,
    ORBIT,
    TRANSPORT,
    MINE,
    CRYSTALS,
    SHUTTLE,
    PLANETS,
    REQUEST,
    REPORT,
    COMPUTER,
    COMMANDS,
    EMEXIT,
    PROBE,
    CLOAK,
    CAPTURE,
    SCORE,
    ABANDON(false),
    DESTRUCT(false),
    FREEZE(false),
    DEATHRAY(false),
    DEBUG(false),
    CALL(false),
    QUIT(false),
    HELP(false),
    undefined;

    private boolean canAbbrev;

    Command() {
        canAbbrev = true;
    }

    Command(boolean abrOk) {
        canAbbrev = abrOk;
    }

    public boolean canAbbrev() {
        return canAbbrev;
    }
}

class Commands {

    // ================================================
    // legacy status variables

    static double warp_factor = 5;
    static double warp_factor_squared = 5 * 5;

    // ================================================

    private static final int QUAD_SIZE = 8;
    private static final int SECT_SIZE = 10;

    static void getCommand(Console con, String cmdstr, Ship ship, Map map) {
        Command c = Command.undefined;

        for (Command cx : Command.values()) {
            boolean Matched;

            if (cx.canAbbrev())
                Matched = compareAbbrev(cmdstr, cx.toString()) == 0;
            else
                Matched = cmdstr.compareToIgnoreCase(cx.toString()) == 0;

            if (Matched) {
                c = cx;
                break;
            }
        }

        switch (c) {
            case SRSCAN:
                execSRSCAN(con);
                break;
            // case LRSCAN:
            // execLRSCAN();
            // break;
            // case PHASERS:
            // execPHASERS();
            // break;
            // case PHOTONS:
            // execPHOTONS();
            // break;
            // case MOVE:
            // execMOVE(ship);
            // break;
            // case SHIELDS:
            // execSHIELDS();
            // break;
            // case DOCK:
            // execDOCK();
            // break;
            // case DAMAGES:
            // execDAMAGES();
            // break;
            case CHART:
                execCHART(con, ship, map, con);
                break;
            // case IMPULSE:
            // execIMPULSE();
            // break;
            // case REST:
            // execREST();
            // break;
            case WARP:
                execWARP(con);
                break;
            // case STATUS:
            // execSTATUS();
            // break;
            // case SENSORS:
            // execSENSORS();
            // break;
            // case ORBIT:
            // execORBIT();
            // break;
            // case TRANSPORT:
            // execTRANSPORT();
            // break;
            // case MINE:
            // execMINE();
            // break;
            // case CRYSTALS:
            // execCRYSTALS();
            // break;
            // case SHUTTLE:
            // execSHUTTLE();
            // break;
            // case PLANETS:
            // execPLANETS();
            // break;
            // case REQUEST:
            // execREQUEST();
            // break;
            // case REPORT:
            // execREPORT();
            // break;
            case COMPUTER:
                execCOMPUTER(con);
                break;
            case COMMANDS:
                execCOMMANDS(con);
                break;
            // case EMEXIT:
            // execEMEXIT();
            // break;
            // case PROBE:
            // execPROBE();
            // break;
            // case CLOAK:
            // execCLOAK();
            // break;
            // case CAPTURE:
            // execCAPTURE();
            // break;
            // case SCORE:
            // execSCORE();
            // break;
            // case ABANDON:
            // execABANDON();
            // break;
            // case DESTRUCT:
            // execDESTRUCT();
            // break;
            // case FREEZE:
            // execFREEZE();
            // break;
            // case DEATHRAY:
            // execDEATHRAY();
            // break;
            // case DEBUG:
            // execDEBUG();
            // break;
            // case CALL:
            // execCALL();
            // break;
            case QUIT:
                execQUIT(con);
                break;
            // case HELP:
            // execHELP();
            // break;

            case undefined:
                con.printf("'%s' is not a command.\n\n", c);
                break;

            default:
                con.printf("Engineer Scott: \"Captain, '%s' is nae yet operational.\"\n\n", c.toString());
                break;
        }
    }

    static int compareAbbrev(String strAbbr, String strFull) {
        strAbbr = strAbbr.toUpperCase();

        if (strAbbr.length() >= strFull.length())
            return strAbbr.compareTo(strFull);

        strFull = strFull.substring(0, strAbbr.length());

        return strAbbr.compareTo(strFull);
    }

    static void execCOMMANDS(Console con) {
        con.printf("   SRSCAN    MOVE      PHASERS   CALL\n");
        con.printf("   STATUS    IMPULSE   PHOTONS   ABANDON\n");
        con.printf("   LRSCAN    WARP      SHIELDS   DESTRUCT\n");
        con.printf("   CHART     REST      DOCK      QUIT\n");
        con.printf("   DAMAGES   REPORT    SENSORS   ORBIT\n");
        con.printf("   TRANSPORT MINE      CRYSTALS  SHUTTLE\n");
        con.printf("   PLANETS   REQUEST   DEATHRAY  FREEZE\n");
        con.printf("   COMPUTER  EMEXIT    PROBE     COMMANDS\n");
        con.printf("   SCORE     CLOAK     CAPTURE   HELP\n");
        con.printf("\n");
    }

    static void execSRSCAN(Console con) {
        int r, c;

        char[][] chScan = new char[10][10]; // minor inefficiency here

        for (r = 1; r <= 10; r++)
            for (c = 1; c <= 10; c++) {
                // chScan[r-1][c-1] = '.';
                chScan[r - 1][c - 1] = '\u00B7';
            }

        // MOCK
        chScan[0][0] = '*';
        chScan[0][5] = 'R';
        chScan[1][3] = 'E';
        chScan[2][5] = '*';
        chScan[2][7] = 'B';
        chScan[3][3] = 'S';
        chScan[4][7] = 'K';
        chScan[5][1] = 'K';
        chScan[5][3] = ' ';
        chScan[5][8] = '*';
        chScan[6][5] = 'P';
        chScan[7][4] = '*';
        chScan[8][1] = '*';
        chScan[8][4] = '*';
        chScan[8][8] = 'C';

        con.printf("\nShort-range scan (MOCK):\n\n");
        boolean leftside = true;
        boolean rightside = true;

        // print column header
        con.printf("        ");
        for (c = 1; c <= 10; c++)
            con.printf("%1d ", c);
        con.printf("\n");

        for (r = 1; r <= 10; r++) {
            if (leftside) {
                con.printf("    %2d  ", r);

                for (c = 1; c <= 10; c++)
                    con.printf("%c ", chScan[r - 1][c - 1]);
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
                        con.printf("Position      %d - %d, %d - %d", 5, 1, 2, 4);
                        break;
                    case 4:
                        con.printf("Life Support  %s", "DAMAGED, Reserves = 2.30");
                        break;
                    case 5:
                        con.printf("Warp Factor   %.1f", warp_factor);
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
                        con.printf("Klingons Left %d", 12);
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

    static void execMOVE(Console con, Ship ship) {
        ship.print();

        // For testing for now, ship will just move 1, 1, 1, 1
        // Will implement with user giving movement commands later

        /*
         * Testing move command with new command line parsing
         * It should work how it's supposed to, you just need to be careful
         * with how it's implemented.
         * Use this as an example of how to implement it.
         */
        Token tkn = CmdProc.getToken();
        int[] coords = new int[4];
        int next = 0;

        if (tkn.getType() == TokenType.EOL) {
            // wait for user input
            con.printf("Move to which quadrant and which sector? \n");
            tkn = CmdProc.getToken();
        }

        if (tkn.getType() == TokenType.ALPHA) {
            huh(con);
            return;
        }

        while (tkn.getType() != TokenType.EOL) {
            coords[next++] = (int) tkn.getDouble();
            tkn = CmdProc.getToken();
        }

        // ship.move(1, 1, 1, 1);
        ship.move(coords[0], coords[1], coords[2], coords[3]);
        ship.print();
    }

    static void execCOMPUTER(Console con) {

        con.printf("Destination quadrant and/or sector? \n");
        /*
         * Use Evan's command line parsing to get the destination
         */
        // Using given Stardate 2516.3 and Position 5 - 1 2 - 4 and Warp Factor of 5.0
        Computer.computer(4, 3, 2, 1);
    }

    static void execWARP(Console con) {
        Token tkn = CmdProc.getToken();
        while (tkn.getType() == TokenType.EOL) {
            con.printf("Warp factor-");
            tkn = CmdProc.getToken();
        }

        if (tkn.getType() == TokenType.ALPHA) {
            huh(con);
            return;
        }

        // MOCK - no damage yet

        double w = tkn.getDouble();

        if (w > 10) {
            con.printf("Helmsman Sulu- \"Our top speed is warp 10, Captain.\"\n\n");
            return;
        }

        if (w < 1.0) {
            con.printf("Helmsman Sulu- \"We can't go below warp 1, Captain.\"\n\n");
            return;
        }

        double old_warp_factor = warp_factor;
        warp_factor = w;
        warp_factor_squared = w * w;

        if ((warp_factor <= old_warp_factor) || (warp_factor <= 6.0)) {
            con.printf("Helmsman Sulu- \"Warp factor %.1f, Captain.\"\n\n", warp_factor);
            return;
        }

        if (warp_factor < 8.0) {
            con.printf("Engineer Scott- \"Aye, but our maximum safe speed is warp 6.\"\n\n");
            return;
        }

        if (warp_factor < 10.0) {
            con.printf("Engineer Scott- \"Aye, Captain, but our engines may not take it.\"\n\n");
            return;
        }

        con.printf("Engineer Scott- \"Aye, Captain, we'll try it.\"\n\n");
    }

    static void execQUIT(Console con) {
        con.printf("\n\n******************************************************\n");
        System.exit(0);
    }

    static void huh(Console con) {
        CmdProc.flushTok();
        con.printf("Beg your pardon, Captain?\n");
    }

    static void execLRSCAN(Console con) { // Not yet implemented

    }

    static void execCHART(Console con, Ship ship, Map map) {
        // TEMPORARY TESTING OF MAP
        con.printf(" ");
        for (int i = 0; i < SECT_SIZE; i++) {
            con.printf(String.valueOf(i));
        }
        con.printf("\n");

        for (int i = 0; i < SECT_SIZE; i++) {
            con.printf(String.valueOf(i) + " ");
            for (int j = 0; j < SECT_SIZE; j++) {
                con.printf(String.valueOf((map.getMap()[ship.getXQuad()][ship.getYQuad()][i][j])));
            }
            con.printf("\n");
        }
    }
}