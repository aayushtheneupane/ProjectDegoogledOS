package org.apache.james.mime4j.p012io;

import org.apache.james.mime4j.MimeException;

/* renamed from: org.apache.james.mime4j.io.MaxHeaderLengthLimitException */
public class MaxHeaderLengthLimitException extends MimeException {
    private static final long serialVersionUID = 8924290744274769913L;

    public MaxHeaderLengthLimitException(String str) {
        super(str);
    }
}
