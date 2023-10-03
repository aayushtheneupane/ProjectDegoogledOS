package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: iel */
/* compiled from: PG */
public interface iel extends ScheduledExecutorService, iek {
    /* renamed from: a */
    iej mo5934a(Runnable runnable, long j, long j2, TimeUnit timeUnit);

    /* renamed from: a */
    iej mo5935a(Runnable runnable, long j, TimeUnit timeUnit);

    /* renamed from: a */
    iej mo5936a(Callable callable, long j, TimeUnit timeUnit);

    /* renamed from: b */
    iej mo5938b(Runnable runnable, long j, long j2, TimeUnit timeUnit);
}
