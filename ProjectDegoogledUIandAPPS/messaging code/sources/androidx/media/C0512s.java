package androidx.media;

import android.content.Context;
import android.service.media.MediaBrowserService;

/* renamed from: androidx.media.s */
class C0512s extends C0509p {
    final /* synthetic */ C0514t this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0512s(C0514t tVar, Context context) {
        super(tVar, context);
        this.this$1 = tVar;
    }

    public void onLoadItem(String str, MediaBrowserService.Result result) {
        this.this$1.mo4602b(str, new C0520z(result));
    }
}
