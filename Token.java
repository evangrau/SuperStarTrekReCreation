enum TokenType {
    EOL,
    DOUBLE,
    ALPHA
}

class Token {
    private TokenType type;
    private String strVal;
    private double dblVal;

    Token(TokenType t, String s, double d) {
        type = t;
        strVal = s;
        dblVal = d;
    }

    public TokenType getType() {
        return type;
    }

    public String getString() {
        return strVal;
    }

    public double getDouble() {
        return dblVal;
    }
}
