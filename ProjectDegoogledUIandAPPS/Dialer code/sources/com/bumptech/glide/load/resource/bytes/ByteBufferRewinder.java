package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.data.DataRewinder;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
    private final ByteBuffer buffer;

    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        public DataRewinder build(Object obj) {
            return new ByteBufferRewinder((ByteBuffer) obj);
        }

        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }
    }

    public ByteBufferRewinder(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    public void cleanup() {
    }

    public Object rewindAndGet() throws IOException {
        this.buffer.position(0);
        return this.buffer;
    }
}
