package p000;

import android.view.Menu;
import android.view.View;
import android.view.Window;

/* renamed from: pn */
/* compiled from: PG */
final class C0425pn extends C0454qp {

    /* renamed from: b */
    private final /* synthetic */ C0426po f15518b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0425pn(C0426po poVar, Window.Callback callback) {
        super(callback);
        this.f15518b = poVar;
    }

    public final View onCreatePanelView(int i) {
        if (i == 0) {
            return new View(this.f15518b.f15519a.mo10329b());
        }
        return super.onCreatePanelView(i);
    }

    public final boolean onPreparePanel(int i, View view, Menu menu) {
        boolean onPreparePanel = super.onPreparePanel(i, view, menu);
        if (onPreparePanel) {
            C0426po poVar = this.f15518b;
            if (!poVar.f15520b) {
                poVar.f15519a.mo10341l();
                this.f15518b.f15520b = true;
                return true;
            }
        }
        return onPreparePanel;
    }
}
