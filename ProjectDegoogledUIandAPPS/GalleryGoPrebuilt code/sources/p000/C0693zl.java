package p000;

import android.view.View;
import android.view.Window;

/* renamed from: zl */
/* compiled from: PG */
final class C0693zl implements View.OnClickListener {

    /* renamed from: a */
    private final C0455qq f16447a = new C0455qq(this.f16448b.f16452a.getContext(), this.f16448b.f16454c);

    /* renamed from: b */
    private final /* synthetic */ C0695zn f16448b;

    public C0693zl(C0695zn znVar) {
        this.f16448b = znVar;
    }

    public final void onClick(View view) {
        C0695zn znVar = this.f16448b;
        Window.Callback callback = znVar.f16455d;
        if (callback != null && znVar.f16456e) {
            callback.onMenuItemSelected(0, this.f16447a);
        }
    }
}
