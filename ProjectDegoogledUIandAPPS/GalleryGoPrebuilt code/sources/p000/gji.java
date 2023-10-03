package p000;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

/* renamed from: gji */
/* compiled from: PG */
final class gji implements View.OnTouchListener {

    /* renamed from: a */
    private final /* synthetic */ AutoCompleteTextView f11477a;

    /* renamed from: b */
    private final /* synthetic */ gjn f11478b;

    public gji(gjn gjn, AutoCompleteTextView autoCompleteTextView) {
        this.f11478b = gjn;
        this.f11477a = autoCompleteTextView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.f11478b.mo6763c()) {
                this.f11478b.f11486c = false;
            }
            this.f11478b.mo6759a(this.f11477a);
            view.performClick();
        }
        return false;
    }
}
