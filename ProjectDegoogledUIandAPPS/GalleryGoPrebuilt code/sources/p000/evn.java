package p000;

import com.google.android.gms.common.api.Status;

/* renamed from: evn */
/* compiled from: PG */
public final class evn extends evt {

    /* renamed from: h */
    private final /* synthetic */ String f9096h;

    /* renamed from: i */
    private final /* synthetic */ int f9097i;

    /* renamed from: j */
    private final /* synthetic */ String[] f9098j;

    /* renamed from: k */
    private final /* synthetic */ byte[] f9099k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public evn(ekv ekv, String str, int i, String[] strArr, byte[] bArr) {
        super(ekv);
        this.f9096h = str;
        this.f9097i = i;
        this.f9098j = strArr;
        this.f9099k = bArr;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ela mo3504a(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4881a(ekl ekl) {
        ((evl) ((evw) ekl).mo5126p()).mo5325a(new evm(this), this.f9096h, this.f9097i, this.f9098j, this.f9099k);
    }
}
