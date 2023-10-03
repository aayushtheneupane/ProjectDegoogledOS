package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.MimeException;

public class ParseException extends MimeException {
    private static final long serialVersionUID = 1;

    protected ParseException(String str) {
        super(str);
    }
}
