package p000;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: isi */
/* compiled from: PG */
final class isi extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    private final /* synthetic */ isn f15003a;

    public isi(isn isn) {
        this.f15003a = isn;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        isn isn = this.f15003a;
        View.OnLongClickListener onLongClickListener = isn.f15025k;
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(isn.mo9095c());
        }
    }
}
