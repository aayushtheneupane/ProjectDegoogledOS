package androidx.loader.content;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: androidx.loader.content.d */
public final class C0473d extends ContentObserver {
    final /* synthetic */ C0475f this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0473d(C0475f fVar) {
        super(new Handler());
        this.this$0 = fVar;
    }

    public boolean deliverSelfNotifications() {
        return true;
    }

    public void onChange(boolean z) {
        this.this$0.onContentChanged();
    }
}
