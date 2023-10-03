package p009io.grpc;

import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.Deadline */
public final class Deadline implements Comparable<Deadline> {
    private static final long MAX_OFFSET = TimeUnit.DAYS.toNanos(36500);
}
