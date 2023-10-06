import java.io.Console;
import java.util.Random;

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

class SST {
    private static String cmdstr;
    private static Console con;
    private static final int QUAD_SIZE = 8;
    private static final int SECT_SIZE = 10;
    private static Random rand = new Random();

    // initializing ship and map

    // Need to make sure that is within bounds of the sector (this goes 0-11 not
    // 1-10)
    public static Ship ship = new Ship(rand.nextInt(QUAD_SIZE + 1), rand.nextInt(QUAD_SIZE + 1),
            rand.nextInt(SECT_SIZE + 1), rand.nextInt(SECT_SIZE + 1));

    public static Map map = new Map(QUAD_SIZE, SECT_SIZE);

    public static void main(String[] args) {
        // main polling loop

        map.populate(ship.getXQuad(), ship.getYQuad(), ship.getXSect(), ship.getYSect());

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

            Commands.getCommand(con, cmdstr, ship, map);

            CmdProc.flushTok();
        }
    }
}
