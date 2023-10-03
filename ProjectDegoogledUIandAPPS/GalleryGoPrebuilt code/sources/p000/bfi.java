package p000;

import java.util.concurrent.Executor;

/* renamed from: bfi */
/* compiled from: PG */
final class bfi implements Executor {
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
