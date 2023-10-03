package org.apache.james.mime4j.field.datetime.parser;

import java.io.Serializable;

public class Token implements Serializable {
    private static final long serialVersionUID = 1;
    public int beginColumn;
    public int beginLine;
    public int endColumn;
    public int endLine;
    public String image;
    public int kind;
    public Token next;
    public Token specialToken;

    public Token() {
    }

    public String toString() {
        return this.image;
    }

    public Token(int i, String str) {
        this.kind = i;
        this.image = str;
    }
}
