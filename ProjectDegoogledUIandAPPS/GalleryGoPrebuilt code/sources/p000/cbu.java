package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.editor.presets.PresetSelectionView;

/* renamed from: cbu */
/* compiled from: PG */
public class cbu extends RecyclerView implements ioe {

    /* renamed from: a */
    private ftv f4023a;

    public cbu(Context context) {
        super(context);
        mo3359a();
    }

    public cbu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3359a();
    }

    public cbu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3359a();
    }

    /* renamed from: a */
    private final void mo3359a() {
        PresetSelectionView presetSelectionView = (PresetSelectionView) this;
        ((cbs) mo2453b()).mo2492ab();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4023a == null) {
            this.f4023a = new ftv(this);
        }
        return this.f4023a.mo2453b();
    }
}
