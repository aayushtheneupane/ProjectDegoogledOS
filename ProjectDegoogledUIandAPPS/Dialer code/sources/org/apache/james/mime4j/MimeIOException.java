package org.apache.james.mime4j;

import java.io.IOException;

public class MimeIOException extends IOException {
    private static final long serialVersionUID = 5393613459533735409L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MimeIOException(MimeException mimeException) {
        super(mimeException == null ? null : mimeException.getMessage());
        initCause(mimeException);
    }

    public Throwable getCause() {
        return (MimeException) super.getCause();
    }
}
