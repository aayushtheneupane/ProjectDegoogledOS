package p009io.grpc.okhttp.internal.framed;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: io.grpc.okhttp.internal.framed.FrameReader */
public interface FrameReader extends Closeable {

    /* renamed from: io.grpc.okhttp.internal.framed.FrameReader$Handler */
    public interface Handler {
    }

    boolean nextFrame(Handler handler) throws IOException;
}
