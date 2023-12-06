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

    static double warpFactor = 5;
    static double warpFactorSquared = 5 * 5;

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
            case LRSCAN:
                execLRSCAN(con);
                break;
            // case PHASERS:
            // execPHASERS();
            // break;
            case PHOTONS:
                execPHOTONS(con);
                break;
            case MOVE:
                execMOVE(con, ship, map);
                break;
            // case SHIELDS:
            // execSHIELDS();
            // break;
            // case DOCK:
            // execDOCK();
            // break;
            case DAMAGES:
                execDAMAGES(con);
                break;
            case CHART:
                execCHART(con, ship, map);
                break;
            case IMPULSE:
                execIMPULSE();
                break;
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
        con.printf("   SRSCAN    MOVE\n");
        con.printf("   IMPULSE   PHOTONS\n");
        con.printf("   LRSCAN    WARP\n");
        con.printf("   COMPUTER  COMMANDS\n");
        con.printf("   QUIT\n");
        con.printf("\n");
    }

    static void execSRSCAN(Console con) {
        srscan.srReport(con);
    }

    static void execLRSCAN(Console con) {
        lrscan.lrReport(con);
    }

    static void execMOVE(Console con, Ship ship, Map map) {
        ship.print();

        /*
         * Testing move command with new command line parsing
         * It should work how it's supposed to, you just need to be careful
         * with how it's implemented.
         * Use this as an example of how to implement it.
         */
        int[] coords = new int[4];
        int next = 0;
        Token tkn = CmdProc.getToken();

        // end of line character
        if (tkn.getType() == TokenType.EOL) {
            // wait for user input
            con.printf("Move to which quadrant and which sector? \n");
            tkn = CmdProc.getToken();
        }

        // alpha character
        if (tkn.getType() == TokenType.ALPHA) {
            huh(con);
            return;
        }

        // reading number input
        while (tkn.getType() != TokenType.EOL) {
            coords[next++] = (int) tkn.getDouble();
            tkn = CmdProc.getToken();
        }

        // invalid destination entered
        if (coords[0] < 1 || coords[0] > 8 || coords[1] < 1 || coords[1] > 8 ||
                coords[2] < 1 || coords[2] > 10 || coords[3] < 1 || coords[3] > 10) {
            con.printf("Invalid destination \n");
            execMOVE(con, ship, map);
            return;
        }
        map.removeShip(ship, con);
        ship.move(coords[0], coords[1], coords[2], coords[3]);
        map.updateShip(ship, con);
        ship.print();
    }

    static void execPHOTONS(Console con) {
        Photons.fire(con);
    }

    static void execDAMAGES(Console con) {
        Damages.damageReport(con);
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

        double oldWarpFactor = warpFactor;
        warpFactor = w;
        warpFactorSquared = w * w;

        if ((warpFactor <= oldWarpFactor) || (warpFactor <= 6.0)) {
            con.printf("Helmsman Sulu- \"Warp factor %.1f, Captain.\"\n\n", warpFactor);
            return;
        }

        if (warpFactor < 8.0) {
            con.printf("Engineer Scott- \"Aye, but our maximum safe speed is warp 6.\"\n\n");
            return;
        }

        if (warpFactor < 10.0) {
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
        con.printf("\nBeg your pardon, Captain?\n\n");
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

    static void execIMPULSE() {
        Impulse.impulse();
    }
}