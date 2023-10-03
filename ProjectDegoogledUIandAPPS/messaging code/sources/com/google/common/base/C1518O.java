package com.google.common.base;

import java.util.concurrent.TimeUnit;

/* renamed from: com.google.common.base.O */
public final class C1518O {

    /* renamed from: cN */
    private long f2396cN;

    /* renamed from: dN */
    private long f2397dN;
    private boolean isRunning;
    private final C1525S ticker;

    @Deprecated
    C1518O() {
        C1525S Yk = C1525S.m3984Yk();
        C1508E.checkNotNull(Yk, "ticker");
        this.ticker = Yk;
    }

    /* renamed from: So */
    private long m3980So() {
        return this.isRunning ? (this.ticker.read() - this.f2397dN) + this.f2396cN : this.f2396cN;
    }

    /* renamed from: Xk */
    public static C1518O m3981Xk() {
        C1518O o = new C1518O();
        C1508E.m3961a(!o.isRunning, "This stopwatch is already running.");
        o.isRunning = true;
        o.f2397dN = o.ticker.read();
        return o;
    }

    /* renamed from: a */
    public long mo8533a(TimeUnit timeUnit) {
        return timeUnit.convert(m3980So(), TimeUnit.NANOSECONDS);
    }

    public C1518O stop() {
        long read = this.ticker.read();
        C1508E.m3961a(this.isRunning, "This stopwatch is already stopped.");
        this.isRunning = false;
        this.f2396cN = (read - this.f2397dN) + this.f2396cN;
        return this;
    }

    public String toString() {
        TimeUnit timeUnit;
        String str;
        long So = m3980So();
        if (TimeUnit.DAYS.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.DAYS;
        } else if (TimeUnit.HOURS.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.MINUTES.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.SECONDS.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MILLISECONDS.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (TimeUnit.MICROSECONDS.convert(So, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MICROSECONDS;
        } else {
            timeUnit = TimeUnit.NANOSECONDS;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(((double) So) / ((double) TimeUnit.NANOSECONDS.convert(1, timeUnit)));
        switch (C1517N.f2395bN[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = "s";
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = "h";
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        objArr[1] = str;
        return String.format("%.4g %s", objArr);
    }
}
