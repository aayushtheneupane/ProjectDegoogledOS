package p009io.grpc.internal;

import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import p009io.grpc.Status;

/* renamed from: io.grpc.internal.MessageDeframer */
public class MessageDeframer implements Closeable {

    /* renamed from: io.grpc.internal.MessageDeframer$Listener */
    public interface Listener {
    }

    public void close() {
        throw null;
    }

    public boolean isStalled() {
        throw null;
    }

    /* renamed from: io.grpc.internal.MessageDeframer$SizeEnforcingInputStream */
    static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark;
        private final int maxMessageSize;

        private void verifySize() {
            long j = this.count;
            int i = this.maxMessageSize;
            if (j > ((long) i)) {
                throw Status.INTERNAL.withDescription(String.format("Compressed frame exceeds maximum frame size: %d. Bytes read: %d. If this is normal, increase the maxMessageSize in the channel/server builder", new Object[]{Integer.valueOf(i), Long.valueOf(this.count)})).asRuntimeException();
            }
        }

        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        public int read() throws IOException {
            int read = this.in.read();
            if (read != -1) {
                this.count++;
            }
            verifySize();
            return read;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.count = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        public long skip(long j) throws IOException {
            long skip = this.in.skip(j);
            this.count += skip;
            verifySize();
            return skip;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.in.read(bArr, i, i2);
            if (read != -1) {
                this.count += (long) read;
            }
            verifySize();
            return read;
        }
    }
}
