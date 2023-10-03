package p000;

import androidx.preference.PreferenceGroup;

/* renamed from: adl */
/* compiled from: PG */
public final class adl implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ PreferenceGroup f221a;

    public adl(PreferenceGroup preferenceGroup) {
        this.f221a = preferenceGroup;
    }

    public final void run() {
        synchronized (this) {
            this.f221a.f1128a.clear();
        }
    }
}
