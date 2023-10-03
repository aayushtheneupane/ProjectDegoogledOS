package p000;

import android.view.View;
import android.widget.Switch;
import com.google.android.apps.photosgo.sharing.SharingAppGridView;
import p003j$.util.Optional;

/* renamed from: edc */
/* compiled from: PG */
public final class edc {

    /* renamed from: a */
    public ect f8015a;

    /* renamed from: b */
    public SharingAppGridView f8016b;

    /* renamed from: c */
    public View f8017c;

    /* renamed from: d */
    public Switch f8018d;

    /* renamed from: e */
    public final String f8019e;

    /* renamed from: f */
    public final ecw f8020f;

    /* renamed from: g */
    public final ecx f8021g;

    /* renamed from: h */
    public final efk f8022h;

    /* renamed from: i */
    public final een f8023i;

    /* renamed from: j */
    public final edj f8024j;

    /* renamed from: k */
    public final gvi f8025k;

    /* renamed from: l */
    public final cjo f8026l;

    /* renamed from: m */
    public final fee f8027m;

    /* renamed from: n */
    public final ble f8028n;

    /* renamed from: o */
    public final iel f8029o;

    /* renamed from: p */
    public final Optional f8030p;

    /* renamed from: q */
    public final boolean f8031q;

    /* renamed from: r */
    public final gvc f8032r = new edb(this);

    /* renamed from: s */
    private final cjr f8033s;

    public edc(ecw ecw, ecx ecx, efk efk, edj edj, een een, gvi gvi, cjr cjr, cjo cjo, fee fee, String str, ble ble, iel iel) {
        this.f8020f = ecw;
        this.f8021g = ecx;
        this.f8023i = een;
        this.f8022h = efk;
        this.f8024j = edj;
        this.f8025k = gvi;
        this.f8033s = cjr;
        this.f8026l = cjo;
        this.f8027m = fee;
        this.f8019e = str;
        this.f8028n = ble;
        this.f8029o = iel;
        this.f8031q = ecx.f7950b.size() <= 1 ? false : true;
        ije ije = ecx.f7950b;
        Optional empty = Optional.empty();
        int size = ije.size();
        for (int i = 0; i < size; i++) {
            cyd cyd = (cyd) ije.get(i);
            if (empty.isPresent()) {
                cxh cxh = (cxh) empty.get();
                cxh a = cxh.m5592a(cyd.f5992f);
                if (!cxh.equals(a == null ? cxh.UNKNOWN_MEDIA_TYPE : a)) {
                    empty = Optional.m16285of(cxh.UNKNOWN_MEDIA_TYPE);
                }
            } else {
                cxh a2 = cxh.m5592a(cyd.f5992f);
                empty = Optional.m16285of(a2 == null ? cxh.UNKNOWN_MEDIA_TYPE : a2);
            }
        }
        this.f8030p = empty;
    }

    /* renamed from: a */
    public final boolean mo4718a() {
        ecx ecx = this.f8021g;
        if (ecx.f7951c && ecx.f7950b.size() == 1) {
            cxh a = cxh.m5592a(((cyd) this.f8021g.f7950b.get(0)).f5992f);
            if (a == null) {
                a = cxh.UNKNOWN_MEDIA_TYPE;
            }
            return a.equals(cxh.IMAGE) && this.f8033s.mo3175a();
        }
    }
}
