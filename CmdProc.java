import java.util.NoSuchElementException;
//import java.util.StringTokenizer;

class CmdProc {
    // private static StringTokenizer tokenizer = null;
    private static StringSplitter splitter = null;

    public static Token getToken() {
        // if (tokenizer == null)
        if (splitter == null) {
            String cmdln = System.console().readLine();
            // tokenizer = new StringTokenizer(cmdln, " ");
            splitter = new StringSplitter(cmdln, " ");
        }

        // if (tokenizer.hasMoreTokens() == false)
        if (splitter.hasMoreTokens() == false) {
            // tokenizer = null;
            splitter = null;

            return new Token(TokenType.EOL, null, 0.0);
        }

        // String str = tokenizer.nextToken();
        String str = splitter.nextToken();

        try {
            double dbl = Double.parseDouble(str);

            return new Token(TokenType.DOUBLE, str, dbl);
        } catch (NumberFormatException e) {
            return new Token(TokenType.ALPHA, str, 0.0);
        }
    }

    public static void flushTok() {
        // tokenizer = null;
        splitter = null;
    }
}

class StringSplitter {
    int index = -1;
    private String[] split;

    StringSplitter(String str, String regex) {
        split = str.split(regex);
        for (int i = 0; i < split.length; i++)
        index = (split.length > 0) ? 0 : -1;
    }

    boolean hasMoreTokens() {
        return (index > -1) && (index < split.length);
    }

    String nextToken() throws NoSuchElementException {
        if (hasMoreTokens())
            return split[index++];
        else
            throw new NoSuchElementException();
    }
}
