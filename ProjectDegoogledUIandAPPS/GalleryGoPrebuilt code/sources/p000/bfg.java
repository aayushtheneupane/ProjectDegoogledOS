package p000;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* renamed from: bfg */
/* compiled from: PG */
public final class bfg extends InputStream {

    /* renamed from: a */
    public static final Queue f2204a = bfp.m2429a(0);

    /* renamed from: b */
    public InputStream f2205b;

    /* renamed from: c */
    public IOException f2206c;

    public final boolean markSupported() {
        return true;
    }

    public final int available() {
        return this.f2205b.available();
    }

    public final void close() {
        this.f2205b.close();
    }

    public final void mark(int i) {
        this.f2205b.mark(i);
    }

    public final int read() {
        try {
            return this.f2205b.read();
        } catch (IOException e) {
            this.f2206c = e;
            return -1;
        }
    }

    public final int read(byte[] bArr) {
        try {
            return this.f2205b.read(bArr);
        } catch (IOException e) {
            this.f2206c = e;
            return -1;
        }
    }

    public final int read(byte[] bArr, int i, int i2) {
        try {
            return this.f2205b.read(bArr, i, i2);
        } catch (IOException e) {
            this.f2206c = e;
            return -1;
        }
    }

    /* renamed from: a */
    public final void mo1942a() {
        this.f2206c = null;
        this.f2205b = null;
        synchronized (f2204a) {
            f2204a.offer(this);
        }
    }

    public final synchronized void reset() {
        this.f2205b.reset();
    }

    public final long skip(long j) {
        try {
            return this.f2205b.skip(j);
        } catch (IOException e) {
            this.f2206c = e;
            return 0;
        }
    }
}
