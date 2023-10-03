package org.apache.james.mime4j.p012io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* renamed from: org.apache.james.mime4j.io.EOLConvertingInputStream */
public class EOLConvertingInputStream extends InputStream {
    private int flags = 3;

    /* renamed from: in */
    private PushbackInputStream f87in = null;
    private int previous = 0;

    public EOLConvertingInputStream(InputStream inputStream) {
        this.f87in = new PushbackInputStream(inputStream, 2);
        this.flags = 3;
    }

    public void close() throws IOException {
        this.f87in.close();
    }

    public int read() throws IOException {
        int read = this.f87in.read();
        if (read == -1) {
            return -1;
        }
        if ((this.flags & 1) != 0 && read == 13) {
            int read2 = this.f87in.read();
            if (read2 != -1) {
                this.f87in.unread(read2);
            }
            if (read2 != 10) {
                this.f87in.unread(10);
            }
        } else if (!((this.flags & 2) == 0 || read != 10 || this.previous == 13)) {
            this.f87in.unread(10);
            read = 13;
        }
        this.previous = read;
        return read;
    }
}
