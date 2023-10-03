package p000;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/* renamed from: qj */
/* compiled from: PG */
public final class C0448qj extends ActionMode {

    /* renamed from: a */
    public final C0443qe f15619a;

    /* renamed from: b */
    private final Context f15620b;

    public C0448qj(Context context, C0443qe qeVar) {
        this.f15620b = context;
        this.f15619a = qeVar;
    }

    public final Object getTag() {
        return this.f15619a.f15603c;
    }

    public final boolean getTitleOptionalHint() {
        return this.f15619a.f15604d;
    }

    public final void finish() {
        this.f15619a.mo9650c();
    }

    public final View getCustomView() {
        return this.f15619a.mo9655h();
    }

    public final Menu getMenu() {
        return new C0489rx(this.f15620b, (C0254je) this.f15619a.mo9647b());
    }

    public final MenuInflater getMenuInflater() {
        return this.f15619a.mo9642a();
    }

    public final CharSequence getSubtitle() {
        return this.f15619a.mo9653f();
    }

    public final CharSequence getTitle() {
        return this.f15619a.mo9652e();
    }

    public final void invalidate() {
        this.f15619a.mo9651d();
    }

    public final boolean isTitleOptional() {
        return this.f15619a.mo9654g();
    }

    public final void setCustomView(View view) {
        this.f15619a.mo9644a(view);
    }

    public final void setSubtitle(int i) {
        this.f15619a.mo9648b(i);
    }

    public final void setSubtitle(CharSequence charSequence) {
        this.f15619a.mo9645a(charSequence);
    }

    public final void setTag(Object obj) {
        this.f15619a.f15603c = obj;
    }

    public final void setTitle(int i) {
        this.f15619a.mo9643a(i);
    }

    public final void setTitle(CharSequence charSequence) {
        this.f15619a.mo9649b(charSequence);
    }

    public final void setTitleOptionalHint(boolean z) {
        this.f15619a.mo9646a(z);
    }
}
