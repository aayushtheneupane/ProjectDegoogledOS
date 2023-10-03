package p000;

import android.database.Observable;

/* renamed from: xh */
/* compiled from: PG */
public final class C0635xh extends Observable {
    /* renamed from: a */
    public final boolean mo10545a() {
        return !this.mObservers.isEmpty();
    }

    /* renamed from: b */
    public final void mo10546b() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((dcm) this.mObservers.get(size)).mo4054a();
        }
    }

    /* renamed from: d */
    public final void mo10549d(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((dcm) this.mObservers.get(size)).mo4058c(i, i2);
        }
    }

    /* renamed from: a */
    public final void mo10543a(int i, int i2) {
        mo10544a(i, i2, (Object) null);
    }

    /* renamed from: a */
    public final void mo10544a(int i, int i2, Object obj) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((dcm) this.mObservers.get(size)).mo4056a(i, i2, obj);
        }
    }

    /* renamed from: b */
    public final void mo10547b(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((dcm) this.mObservers.get(size)).mo4055a(i, i2);
        }
    }

    /* renamed from: c */
    public final void mo10548c(int i, int i2) {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((dcm) this.mObservers.get(size)).mo4057b(i, i2);
        }
    }
}
