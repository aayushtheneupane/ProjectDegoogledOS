package p000;

import android.util.ArraySet;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import p003j$.util.Optional;

/* renamed from: dwm */
/* compiled from: PG */
final class dwm implements gvc {

    /* renamed from: a */
    private Optional f7504a = Optional.empty();

    /* renamed from: b */
    private Optional f7505b = Optional.empty();

    /* renamed from: c */
    private Optional f7506c = Optional.empty();

    /* renamed from: d */
    private Optional f7507d = Optional.empty();

    /* renamed from: e */
    private final /* synthetic */ dwn f7508e;

    public /* synthetic */ dwm(dwn dwn) {
        this.f7508e = dwn;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    private final void m6812a(int i) {
        int i2 = i - 1;
        if (i2 == 1) {
            ihg.m13041a((hoi) new dvr(2), (C0147fh) this.f7508e.f7516h);
        } else if (i2 == 2) {
            ihg.m13041a((hoi) new dvr(1), (C0147fh) this.f7508e.f7516h);
        }
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        m6812a(4);
        cwn.m5515b(th, "PhotoGridFragment: Failed to get data. Doing nothing :(.", new Object[0]);
        m6813b();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        boolean z;
        String str;
        dyu dyu = (dyu) obj;
        int i = 3;
        if (dyu.mo4576e().isPresent()) {
            dwn dwn = this.f7508e;
            if (dwn.f7529u) {
                cqh cqh = dwn.f7512d;
                ArraySet arraySet = new ArraySet();
                for (cxi a : (Collection) dyu.mo4576e().get()) {
                    arraySet.add(ede.m7258a(a));
                }
                ife.m12876b(cqh.f5422c.isPresent(), (Object) "MultiselectState must be set");
                int a2 = cun.m5438a(((cqe) cqh.f5422c.get()).f5417b);
                if (a2 == 0 || a2 != 3) {
                    if (!cqh.f5423d) {
                        if (arraySet.isEmpty()) {
                            cqh.mo3765e();
                        } else {
                            cqh.f5420a.addAll(arraySet);
                            cqh.mo3759b();
                        }
                        cqh.f5423d = true;
                    } else {
                        cwn.m5514b("SelectionManger: setAndInitializeOnce was called more than once", new Object[0]);
                    }
                }
                this.f7508e.f7523o.mo4618a(hvf.f13465a);
                this.f7508e.f7529u = false;
            }
        }
        if (dyu.mo4572a().isPresent()) {
            this.f7504a = dyu.mo4572a();
        }
        if (dyu.mo4573b().isPresent()) {
            this.f7505b = dyu.mo4573b();
        }
        if (dyu.mo4574c().isPresent()) {
            this.f7507d = dyu.mo4574c();
        }
        if (dyu.mo4575d().isPresent()) {
            this.f7506c = dyu.mo4575d();
        }
        ArrayList arrayList = new ArrayList();
        if (this.f7504a.isPresent()) {
            List list = (List) this.f7504a.get();
            if (list.size() >= 2) {
                if (list != null) {
                    arrayList.add(new dvh(list));
                } else {
                    throw null;
                }
            }
            if (this.f7505b.isPresent()) {
                arrayList.addAll((List) this.f7505b.get());
            }
            if (this.f7505b.isPresent() && !arrayList.isEmpty() && this.f7507d.isPresent()) {
                dzf dzf = (dzf) this.f7507d.get();
                if (dzf != null) {
                    arrayList.add(new dvm(dzf));
                } else {
                    throw null;
                }
            }
            if (this.f7504a.isPresent()) {
                bnt bnt = this.f7508e.f7521m;
                List list2 = (List) this.f7504a.get();
                bnu bnu = bnt.f3224a;
                if (bnu == null) {
                    bnt.f3225b = list2;
                } else {
                    bnu.mo2622a(list2);
                    bnt.f3225b = null;
                }
            }
            dwn dwn2 = this.f7508e;
            if (!dyu.mo4578f().booleanValue() || !dyu.mo4572a().isPresent() || !dyu.mo4573b().isPresent() || !dyu.mo4575d().isPresent()) {
                z = false;
            } else {
                z = true;
            }
            int i2 = this.f7508e.f7530v;
            int i3 = i2 - 1;
            if (i2 != 0) {
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i2 != 1) {
                                str = i2 != 2 ? "LOADED" : "EMPTY";
                            } else {
                                str = "LOADING";
                            }
                            StringBuilder sb = new StringBuilder(str.length() + 26);
                            sb.append("Unexpected loading state: ");
                            sb.append(str);
                            throw new IllegalStateException(sb.toString());
                        } else if (arrayList.isEmpty()) {
                            m6812a(2);
                        }
                    } else if (!arrayList.isEmpty()) {
                        m6812a(3);
                    }
                    i = 2;
                } else if (!arrayList.isEmpty()) {
                    m6812a(3);
                } else if (z) {
                    m6812a(2);
                    i = 2;
                } else {
                    i = 1;
                }
                dwn2.f7530v = i;
                dwn dwn3 = this.f7508e;
                int i4 = dwn3.f7530v;
                if (i4 != 0) {
                    if (i4 != 1) {
                        dwn3.f7512d.f5421b = arrayList;
                        dwn3.f7511c.mo3730a();
                        this.f7508e.f7528t.mo4540a(arrayList, (Map) this.f7506c.orElse(hvb.f13454a), this.f7508e.f7510a);
                        dwn dwn4 = this.f7508e;
                        if (dwn4.f7525q) {
                            dxg dxg = dwn4.f7528t;
                            C0598vy vyVar = dxg.f7558c;
                            int i5 = ((ViewGroup.MarginLayoutParams) dxg.f7557b.getLayoutParams()).topMargin;
                            vyVar.f16226f = 0;
                            vyVar.f16227g = i5;
                            C0606wf wfVar = vyVar.f16228h;
                            if (wfVar != null) {
                                wfVar.mo10459b();
                            }
                            vyVar.mo10583p();
                            this.f7508e.f7525q = false;
                        }
                        dwn dwn5 = this.f7508e;
                        cqc cqc = dwn5.f7515g;
                        boolean z2 = dwn5.f7526r;
                        cqa cqa = cqc.f5409a;
                        if (z2 != cqa.f5408a) {
                            cqa.f5408a = z2;
                            for (cqb a3 : cqc.f5410b) {
                                a3.mo3751a(cqc.f5409a);
                            }
                        }
                    }
                    if (this.f7504a.isPresent() && this.f7505b.isPresent()) {
                        m6813b();
                        return;
                    }
                    return;
                }
                throw null;
            }
            throw null;
        }
    }

    /* renamed from: b */
    private final void m6813b() {
        ((bkz) this.f7508e.f7513e.mo9034a()).mo2540a(this.f7508e.f7516h.mo5653m());
        this.f7508e.f7514f.mo2551b();
    }
}
