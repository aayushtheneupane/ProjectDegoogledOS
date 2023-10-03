package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.editor.presets.PresetItemView;

/* renamed from: cbt */
/* compiled from: PG */
public class cbt extends LinearLayout implements ioe {

    /* renamed from: a */
    private ftv f4022a;

    public cbt(Context context) {
        super(context);
        mo3356a();
    }

    public cbt(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3356a();
    }

    public cbt(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3356a();
    }

    public cbt(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3356a();
    }

    /* renamed from: a */
    private final void mo3356a() {
        PresetItemView presetItemView = (PresetItemView) this;
        ((cbl) mo2453b()).mo2491aa();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4022a == null) {
            this.f4022a = new ftv(this);
        }
        return this.f4022a.mo2453b();
    }
}
