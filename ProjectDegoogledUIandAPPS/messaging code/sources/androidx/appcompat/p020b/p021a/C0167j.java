package androidx.appcompat.p020b.p021a;

import android.graphics.drawable.Drawable;

/* renamed from: androidx.appcompat.b.a.j */
class C0167j implements Drawable.Callback {
    private Drawable.Callback mCallback;

    C0167j() {
    }

    public void invalidateDrawable(Drawable drawable) {
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = this.mCallback;
        if (callback != null) {
            callback.scheduleDrawable(drawable, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = this.mCallback;
        if (callback != null) {
            callback.unscheduleDrawable(drawable, runnable);
        }
    }

    public Drawable.Callback unwrap() {
        Drawable.Callback callback = this.mCallback;
        this.mCallback = null;
        return callback;
    }

    public C0167j wrap(Drawable.Callback callback) {
        this.mCallback = callback;
        return this;
    }
}
