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

            Commands.getCommand(con, cmdstr);

            CmdProc.flushTok();
        }
    }
}
