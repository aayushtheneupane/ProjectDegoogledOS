package p000a.p006c.p007a;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: a.c.a.a */
class C0029a extends ContentObserver {
    final /* synthetic */ C0031c this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0029a(C0031c cVar) {
        super(new Handler());
        this.this$0 = cVar;
    }

    public boolean deliverSelfNotifications() {
        return true;
    }

    public void onChange(boolean z) {
        this.this$0.onContentChanged();
    }
}
