package org.apache.james.mime4j.p012io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

/* renamed from: org.apache.james.mime4j.io.LineReaderInputStream */
public abstract class LineReaderInputStream extends FilterInputStream {
    protected LineReaderInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public abstract int readLine(ByteArrayBuffer byteArrayBuffer) throws MaxLineLimitException, IOException;

    public abstract boolean unread(ByteArrayBuffer byteArrayBuffer);
}
