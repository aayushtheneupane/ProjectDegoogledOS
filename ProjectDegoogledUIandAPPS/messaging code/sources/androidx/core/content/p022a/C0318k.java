package androidx.core.content.p022a;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* renamed from: androidx.core.content.a.k */
public abstract class C0318k {
    public final void callbackFailAsync(int i, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new C0317j(this, i));
    }

    public final void callbackSuccessAsync(Typeface typeface, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new C0316i(this, typeface));
    }

    public abstract void onFontRetrievalFailed(int i);

    public abstract void onFontRetrieved(Typeface typeface);
}
