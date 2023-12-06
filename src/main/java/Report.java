import java.io.Console;

public class Report {
    private static Console con;

    public static void report() {
        con = System.console();
        con.printf("You are now playing a short novice\n");
        con.printf(0 + " out of " + Map.klingonCount + " Klingon ships have been destroyed\n");
        con.printf(Map.starbaseCount + " out of " + Map.starbaseCount + " starbases remain\n");
    }
}
