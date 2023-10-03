package org.apache.james.mime4j.p012io;

import org.apache.james.mime4j.MimeException;

/* renamed from: org.apache.james.mime4j.io.MaxHeaderLimitException */
public class MaxHeaderLimitException extends MimeException {
    private static final long serialVersionUID = 2154269045186186769L;

    public MaxHeaderLimitException(String str) {
        super(str);
    }
}
