package androidx.core.content.p022a;

import android.graphics.Typeface;

/* renamed from: androidx.core.content.a.i */
class C0316i implements Runnable {
    final /* synthetic */ C0318k this$0;
    final /* synthetic */ Typeface val$typeface;

    C0316i(C0318k kVar, Typeface typeface) {
        this.this$0 = kVar;
        this.val$typeface = typeface;
    }

    public void run() {
        this.this$0.onFontRetrieved(this.val$typeface);
    }
}
