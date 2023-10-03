package p000;

import p003j$.util.Optional;

/* renamed from: dpz */
/* compiled from: PG */
final class dpz extends dqa {

    /* renamed from: a */
    public Optional f7043a;

    /* renamed from: b */
    public Optional f7044b;

    /* renamed from: c */
    public Optional f7045c;

    /* renamed from: d */
    public Boolean f7046d;

    public dpz(byte[] bArr) {
        this.f7043a = Optional.empty();
        this.f7044b = Optional.empty();
        this.f7045c = Optional.empty();
    }

    /* renamed from: a */
    public final Optional mo4330a() {
        return this.f7043a;
    }

    /* renamed from: b */
    public final Optional mo4331b() {
        return this.f7044b;
    }

    /* renamed from: c */
    public final Optional mo4332c() {
        return this.f7045c;
    }

    /* renamed from: d */
    public final boolean mo4333d() {
        Boolean bool = this.f7046d;
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IllegalStateException("Property \"initialMediaFetchFailed\" has not been set");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4337a(boolean z) {
        this.f7046d = Boolean.valueOf(z);
    }

    public dpz() {
    }
}
