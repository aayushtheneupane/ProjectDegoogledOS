package com.android.messaging.util.exif;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: com.android.messaging.util.exif.n */
class C1445n extends FilterOutputStream {
    private final ByteBuffer mByteBuffer = ByteBuffer.allocate(4);

    public C1445n(OutputStream outputStream) {
        super(outputStream);
    }

    /* renamed from: a */
    public C1445n mo8156a(C1446o oVar) {
        writeInt((int) oVar.getNumerator());
        writeInt((int) oVar.getDenominator());
        return this;
    }

    public C1445n setByteOrder(ByteOrder byteOrder) {
        this.mByteBuffer.order(byteOrder);
        return this;
    }

    public C1445n writeInt(int i) {
        this.mByteBuffer.rewind();
        this.mByteBuffer.putInt(i);
        this.out.write(this.mByteBuffer.array());
        return this;
    }

    public C1445n writeShort(short s) {
        this.mByteBuffer.rewind();
        this.mByteBuffer.putShort(s);
        this.out.write(this.mByteBuffer.array(), 0, 2);
        return this;
    }
}
