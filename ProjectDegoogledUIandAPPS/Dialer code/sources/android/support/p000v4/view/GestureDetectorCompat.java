package android.support.p000v4.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: android.support.v4.view.GestureDetectorCompat */
public final class GestureDetectorCompat {
    private final GestureDetectorCompatImpl mImpl;

    /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImpl */
    interface GestureDetectorCompatImpl {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    /* renamed from: android.support.v4.view.GestureDetectorCompat$GestureDetectorCompatImplJellybeanMr2 */
    static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
        private final GestureDetector mDetector;

        GestureDetectorCompatImplJellybeanMr2(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.mDetector = new GestureDetector(context, onGestureListener, handler);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.mDetector.onTouchEvent(motionEvent);
        }
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener) {
        int i = Build.VERSION.SDK_INT;
        this.mImpl = new GestureDetectorCompatImplJellybeanMr2(context, onGestureListener, (Handler) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mImpl.onTouchEvent(motionEvent);
    }
}
