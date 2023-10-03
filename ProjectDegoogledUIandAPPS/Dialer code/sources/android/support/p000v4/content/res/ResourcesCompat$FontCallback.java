package android.support.p000v4.content.res;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* renamed from: android.support.v4.content.res.ResourcesCompat$FontCallback */
public abstract class ResourcesCompat$FontCallback {
    public final void callbackFailAsync(final int i, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new Runnable() {
            public void run() {
                ResourcesCompat$FontCallback.this.onFontRetrievalFailed(i);
            }
        });
    }

    public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new Runnable() {
            public void run() {
                ResourcesCompat$FontCallback.this.onFontRetrieved(typeface);
            }
        });
    }

    public abstract void onFontRetrievalFailed(int i);

    public abstract void onFontRetrieved(Typeface typeface);
}
