package org.apache.james.mime4j;

public class MimeException extends Exception {
    private static final long serialVersionUID = 8352821278714188542L;

    public MimeException(String str) {
        super(str);
    }

    public MimeException(Throwable th) {
        super(th);
    }

    public MimeException(String str, Throwable th) {
        super(str, th);
    }
}
