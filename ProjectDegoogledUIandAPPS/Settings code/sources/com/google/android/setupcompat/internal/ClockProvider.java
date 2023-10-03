package com.google.android.setupcompat.internal;

import com.google.android.setupcompat.internal.ClockProvider;
import java.util.concurrent.TimeUnit;

public class ClockProvider {
    private static final Ticker SYSTEM_TICKER = $$Lambda$ClockProvider$Xhv6ez4NTBFUn6cNE_oxYP2IXvc.INSTANCE;
    private static Ticker ticker = SYSTEM_TICKER;

    public interface Supplier<T> {
        T get();
    }

    public static long timeInNanos() {
        return ticker.read();
    }

    public static long timeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(timeInNanos());
    }

    public static void resetInstance() {
        ticker = SYSTEM_TICKER;
    }

    public static void setInstance(Supplier<Long> supplier) {
        ticker = new Ticker() {
            public final long read() {
                return ((Long) ClockProvider.Supplier.this.get()).longValue();
            }
        };
    }
}
