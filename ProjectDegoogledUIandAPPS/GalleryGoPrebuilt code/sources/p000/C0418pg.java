package p000;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: pg */
/* compiled from: PG */
public class C0418pg extends Dialog implements C0396ol {

    /* renamed from: a */
    private C0397om f15507a;

    /* renamed from: b */
    private final C0322ls f15508b = new C0417pf(this);

    public C0418pg(Context context, int i) {
        super(context, m15031a(context, i));
        C0397om b = mo6471b();
        b.mo9547a(m15031a(context, i));
        b.mo9566l();
    }

    /* renamed from: a */
    public final void mo6273a(C0443qe qeVar) {
    }

    /* renamed from: j */
    public final void mo6276j() {
    }

    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo6471b().mo9554b(view, layoutParams);
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return C0323lt.m14637a(this.f15508b, getWindow().getDecorView(), this, keyEvent);
    }

    public final View findViewById(int i) {
        return mo6471b().mo9553b(i);
    }

    /* renamed from: b */
    private final C0397om mo6471b() {
        if (this.f15507a == null) {
            this.f15507a = C0397om.m14920a((Dialog) this, (C0396ol) this);
        }
        return this.f15507a;
    }

    /* renamed from: a */
    private static int m15031a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final void invalidateOptionsMenu() {
        mo6471b().mo9560f();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        mo6471b().mo9562h();
        super.onCreate(bundle);
        mo6471b().mo9566l();
    }

    /* access modifiers changed from: protected */
    public final void onStop() {
        super.onStop();
        mo6471b().mo9557d();
    }

    public void setContentView(int i) {
        mo6471b().mo9556c(i);
    }

    public void setContentView(View view) {
        mo6471b().mo9549a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo6471b().mo9550a(view, layoutParams);
    }

    public final void setTitle(int i) {
        super.setTitle(i);
        mo6471b().mo9551a((CharSequence) getContext().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        mo6471b().mo9551a(charSequence);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo9618a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: a */
    public final void mo9617a() {
        mo6471b().mo9559e(1);
    }
}
