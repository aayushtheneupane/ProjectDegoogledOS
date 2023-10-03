package p000;

import java.util.concurrent.Executor;

/* renamed from: amy */
/* compiled from: PG */
enum amy implements Executor {
    ;

    public final String toString() {
        return "DirectExecutor";
    }

    private amy(String str) {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
