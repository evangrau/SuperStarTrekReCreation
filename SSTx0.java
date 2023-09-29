import java.io.Console;

// Note to CS 374 F23 students:
//   This is a skeleton to use to get started.  
//   The parsing loop is partially implemented, but assumes you'll be entering commands in novice mode, letting the
//      computer prompt you for data rather than putting it all on the command line.
//   I have implemented one command (COMMANDS) and a mockup for another (SRSCAN).
//   The only liberty I have take wrt the original game is to use the middle dot ('·', unicode \u00B7) for empty space instead
//      of the original period ('.') to make it easier to spot black holes.
//
//   Do not assume this code is error free.  Even the original probably has bugs (I think there's a bug in file ai.c at line 469).
//
//   Best wishes,
//  Dr. Spivey

class SSTx0 {
    private static String cmdstr;
    private static Console con;

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

    // ================================================
    // legacy status variables

    static double warpfac = 5;
    static double wfacsq = 5 * 5;

    // ================================================

    public static void main(String[] args) {
        // main polling loop

        con = System.console();
        if (con == null)
            return;

        con.printf("\n            SUPER STAR TREK (Java Edition)\n");
        con.printf("\n *** Welcome aboard the USS Enterprise (NCC 1701) *** \n\n");

        while (true) {
            con.printf("COMMAND> ");
            Token tkn = CmdProc.getToken();
            if (tkn.getType() == TokenType.EOL)
                continue;

            cmdstr = tkn.getString();

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
                    execSRSCAN();
                    break;
                // case LRSCAN: execLRSCAN(); break;
                // case PHASERS: execPHASERS(); break;
                // case PHOTONS: execPHOTONS(); break;
                // case MOVE: execMOVE(); break;
                // case SHIELDS: execSHIELDS(); break;
                // case DOCK: execDOCK(); break;
                // case DAMAGES: execDAMAGES(); break;
                // case CHART: execCHART(); break;
                // case IMPULSE: execIMPULSE(); break;
                // case REST: execREST(); break;
                case WARP:
                    execWARP();
                    break;
                // case STATUS: execSTATUS(); break;
                // case SENSORS: execSENSORS(); break;
                // case ORBIT: execORBIT(); break;
                // case TRANSPORT: execTRANSPORT(); break;
                // case MINE: execMINE(); break;
                // case CRYSTALS: execCRYSTALS(); break;
                // case SHUTTLE: execSHUTTLE(); break;
                // case PLANETS: execPLANETS(); break;
                // case REQUEST: execREQUEST(); break;
                // case REPORT: execREPORT(); break;
                case COMPUTER:
                    execCOMPUTER();
                    break;
                case COMMANDS:
                    execCOMMANDS();
                    break;
                // case EMEXIT: execEMEXIT(); break;
                // case PROBE: execPROBE(); break;
                // case CLOAK: execCLOAK(); break;
                // case CAPTURE: execCAPTURE(); break;
                // case SCORE: execSCORE(); break;
                // case ABANDON: execABANDON(); break;
                // case DESTRUCT: execDESTRUCT(); break;
                // case FREEZE: execFREEZE(); break;
                // case DEATHRAY: execDEATHRAY(); break;
                // case DEBUG: execDEBUG(); break;
                // case CALL: execCALL(); break;
                case QUIT:
                    execQUIT();
                    break;
                // case HELP: execHELP(); break;

                case undefined:
                    con.printf("'%s' is not a command.\n\n", c);
                    break;

                default:
                    con.printf("Engineer Scott: \"Captain, '%s' is nae yet operational.\"\n\n", c.toString());
                    break;
            }

            CmdProc.flushTok();
        }
    }

    static int compareAbbrev(String strAbbr, String strFull) {
        strAbbr = strAbbr.toUpperCase();

        if (strAbbr.length() >= strFull.length())
            return strAbbr.compareTo(strFull);

        strFull = strFull.substring(0, strAbbr.length());

        return strAbbr.compareTo(strFull);
    }

    static void execCOMMANDS() {
        con.printf("   SRSCAN    MOVE      PHASERS   CALL\n");
        con.printf("   STATUS    IMPULSE   PHOTONS   ABANDON\n");
        con.printf("   LRSCAN    WARP      SHIELDS   DESTRUCT\n");
        con.printf("   CHART     REST      DOCK      QUIT\n");
        con.printf("   DAMAGES   REPORT    SENSORS   ORBIT\n");
        con.printf("   TRANSPORT MIHE      CRYSTALS  SHUTTLE\n");
        con.printf("   PLANETS   REQUEST   DEATHRAY  FREEZE\n");
        con.printf("   COMPUTER  EMEXIT    PROBE     COMMANDS\n");
        con.printf("   SCORE     CLOAK     CAPTURE   HELP\n");
        con.printf("\n");
    }

    static void execSRSCAN() {
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
                        con.printf("Warp Factor   %.1f", warpfac);
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

    static void execCOMPUTER() {

        con.printf("Destination quadrant and/or sector? \n");
        /*
         * Use Evan's command line parsing to get the destination
         */
        // Using given Stardate 2516.3 and Position 5 - 1 2 - 4 and Warp Factor of 5.0
        Computer.computer(4, 3, 1);
    }

    static void execWARP() {
        Token tkn = CmdProc.getToken();
        while (tkn.getType() == TokenType.EOL) {
            con.printf("Warp factor-");
            tkn = CmdProc.getToken();
        }

        if (tkn.getType() == TokenType.ALPHA) {
            huh();
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

        double oldfac = warpfac;
        warpfac = w;
        wfacsq = w * w;

        if ((warpfac <= oldfac) || (warpfac <= 6.0)) {
            con.printf("Helmsman Sulu- \"Warp factor %.1f, Captain.\"\n\n", warpfac);
            return;
        }

        if (warpfac < 8.0) {
            con.printf("Engineer Scott- \"Aye, but our maximum safe speed is warp 6.\"\n\n");
            return;
        }

        if (warpfac < 10.0) {
            con.printf("Engineer Scott- \"Aye, Captain, but our engines may not take it.\"\n\n");
            return;
        }

        con.printf("Engineer Scott- \"Aye, Captain, we'll try it.\"\n\n");
    }

    static void execQUIT() {
        con.printf("\n\n******************************************************\n");
        System.exit(0);
    }

    static void huh() {
        CmdProc.flushTok();
        con.printf("Beg your pardon, Captain?\n");
    }
}
