package androidx.loader.content;

import androidx.core.p024os.OperationCanceledException;

/* renamed from: androidx.loader.content.a */
final class C0470a extends C0479j implements Runnable {
    final /* synthetic */ C0471b this$0;
    boolean waiting;

    C0470a(C0471b bVar) {
        this.this$0 = bVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Kc */
    public Object mo4483Kc() {
        try {
            return this.this$0.loadInBackground();
        } catch (OperationCanceledException e) {
            if (this.mCancelled.get()) {
                return null;
            }
            throw e;
        }
    }

    public void run() {
        this.waiting = false;
        this.this$0.executePendingTask();
    }
}
