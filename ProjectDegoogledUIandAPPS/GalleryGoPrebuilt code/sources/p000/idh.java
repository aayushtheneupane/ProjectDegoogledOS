package p000;

import java.util.concurrent.Executor;

/* renamed from: idh */
/* compiled from: PG */
public enum idh implements Executor {
    ;

    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }

    private idh(String str) {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
