package org.apache.james.mime4j.p012io;

import java.io.IOException;

/* renamed from: org.apache.james.mime4j.io.MaxLineLimitException */
public class MaxLineLimitException extends IOException {
    private static final long serialVersionUID = 1855987166990764426L;

    public MaxLineLimitException(String str) {
        super(str);
    }
}
