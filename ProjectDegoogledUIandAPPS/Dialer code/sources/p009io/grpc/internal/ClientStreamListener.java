package p009io.grpc.internal;

import p009io.grpc.Metadata;
import p009io.grpc.Status;

/* renamed from: io.grpc.internal.ClientStreamListener */
public interface ClientStreamListener extends StreamListener {
    void closed(Status status, Metadata metadata);
}
