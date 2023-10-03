package p000;

import android.os.Build;
import androidx.work.OverwritingInputMerger;

/* renamed from: ahk */
/* compiled from: PG */
public final class ahk extends ahr {
    public ahk(Class cls) {
        super(cls);
        this.f497b.f715d = OverwritingInputMerger.class.getName();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ahr mo484a() {
        return this;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ahs mo485b() {
        if (this.f497b.f727p) {
            int i = Build.VERSION.SDK_INT;
            if (this.f497b.f721j.f476c) {
                throw new IllegalArgumentException("Cannot run in foreground with an idle mode constraint");
            }
        }
        return new ahs(this);
    }
}
