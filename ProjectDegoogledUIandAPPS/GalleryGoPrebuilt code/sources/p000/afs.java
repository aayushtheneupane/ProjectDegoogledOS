package p000;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: afs */
/* compiled from: PG */
public final class afs extends afl {

    /* renamed from: n */
    public int f349n;

    /* renamed from: o */
    public boolean f350o;

    /* renamed from: p */
    private ArrayList f351p;

    /* renamed from: q */
    private final boolean f352q;

    /* renamed from: r */
    private int f353r;

    public afs(byte[] bArr) {
        this();
        this.f352q = false;
        mo331a((afl) new afb(2));
        mo331a((afl) new aey());
        mo331a((afl) new afb(1));
    }

    public afs() {
        this.f351p = new ArrayList();
        this.f352q = true;
        this.f350o = false;
        this.f353r = 0;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo302a(afk afk) {
        super.mo302a(afk);
    }

    /* renamed from: d */
    public final /* bridge */ /* synthetic */ void mo319d(View view) {
        for (int i = 0; i < this.f351p.size(); i++) {
            ((afl) this.f351p.get(i)).mo319d(view);
        }
        super.mo319d(view);
    }

    /* renamed from: a */
    public final void mo331a(afl afl) {
        m384b(afl);
        long j = this.f323b;
        if (j >= 0) {
            afl.mo301a(j);
        }
        if ((this.f353r & 1) != 0) {
            afl.mo324h();
        }
        if ((this.f353r & 2) != 0) {
            afl.mo325i();
        }
        if ((this.f353r & 4) != 0) {
            afl.mo305a(this.f334m);
        }
        if ((this.f353r & 8) != 0) {
            afl.mo306a(this.f333l);
        }
    }

    /* renamed from: b */
    private final void m384b(afl afl) {
        this.f351p.add(afl);
        afl.f328g = this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public final void mo322f() {
        super.mo322f();
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo322f();
        }
    }

    /* renamed from: b */
    public final void mo272b(afu afu) {
        if (mo309a(afu.f356b)) {
            ArrayList arrayList = this.f351p;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                afl afl = (afl) arrayList.get(i);
                if (afl.mo309a(afu.f356b)) {
                    afl.mo272b(afu);
                    afu.f357c.add(afl);
                }
            }
        }
    }

    /* renamed from: c */
    public final void mo315c(afu afu) {
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo315c(afu);
        }
    }

    /* renamed from: a */
    public final void mo270a(afu afu) {
        if (mo309a(afu.f356b)) {
            ArrayList arrayList = this.f351p;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                afl afl = (afl) arrayList.get(i);
                if (afl.mo309a(afu.f356b)) {
                    afl.mo270a(afu);
                    afu.f357c.add(afl);
                }
            }
        }
    }

    /* renamed from: g */
    public final afl mo323g() {
        afs afs = (afs) super.clone();
        afs.f351p = new ArrayList();
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            afs.m384b(((afl) this.f351p.get(i)).clone());
        }
        return afs;
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return clone();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo303a(ViewGroup viewGroup, afv afv, afv afv2, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.f322a;
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            afl afl = (afl) this.f351p.get(i);
            if (j > 0 && (this.f352q || i == 0)) {
                long j2 = afl.f322a;
                if (j2 > 0) {
                    afl.mo311b(j2 + j);
                } else {
                    afl.mo311b(j);
                }
            }
            afl.mo303a(viewGroup, afv, afv2, arrayList, arrayList2);
        }
    }

    /* renamed from: a */
    public final afl mo330a(int i) {
        if (i < 0 || i >= this.f351p.size()) {
            return null;
        }
        return (afl) this.f351p.get(i);
    }

    /* renamed from: j */
    public final int mo332j() {
        return this.f351p.size();
    }

    /* renamed from: b */
    public final void mo313b(View view) {
        super.mo313b(view);
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo313b(view);
        }
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ void mo312b(afk afk) {
        super.mo312b(afk);
    }

    /* renamed from: e */
    public final /* bridge */ /* synthetic */ void mo321e(View view) {
        for (int i = 0; i < this.f351p.size(); i++) {
            ((afl) this.f351p.get(i)).mo321e(view);
        }
        super.mo321e(view);
    }

    /* renamed from: c */
    public final void mo316c(View view) {
        super.mo316c(view);
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo316c(view);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo314c() {
        if (!this.f351p.isEmpty()) {
            afr afr = new afr(this);
            ArrayList arrayList = this.f351p;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((afl) arrayList.get(i)).mo302a((afk) afr);
            }
            this.f349n = this.f351p.size();
            if (this.f352q) {
                ArrayList arrayList2 = this.f351p;
                int size2 = arrayList2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ((afl) arrayList2.get(i2)).mo314c();
                }
                return;
            }
            for (int i3 = 1; i3 < this.f351p.size(); i3++) {
                ((afl) this.f351p.get(i3 - 1)).mo302a((afk) new afq((afl) this.f351p.get(i3)));
            }
            afl afl = (afl) this.f351p.get(0);
            if (afl != null) {
                afl.mo314c();
                return;
            }
            return;
        }
        mo318d();
        mo320e();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo301a(long j) {
        ArrayList arrayList;
        this.f323b = j;
        if (this.f323b >= 0 && (arrayList = this.f351p) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((afl) this.f351p.get(i)).mo301a(j);
            }
        }
    }

    /* renamed from: a */
    public final void mo306a(fxk fxk) {
        this.f333l = fxk;
        this.f353r |= 8;
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo306a(fxk);
        }
    }

    /* renamed from: h */
    public final /* bridge */ /* synthetic */ void mo324h() {
        this.f353r |= 1;
        ArrayList arrayList = this.f351p;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((afl) this.f351p.get(i)).mo324h();
            }
        }
    }

    /* renamed from: a */
    public final void mo305a(flw flw) {
        super.mo305a(flw);
        this.f353r |= 4;
        if (this.f351p != null) {
            for (int i = 0; i < this.f351p.size(); i++) {
                ((afl) this.f351p.get(i)).mo305a(flw);
            }
        }
    }

    /* renamed from: i */
    public final void mo325i() {
        this.f353r |= 2;
        int size = this.f351p.size();
        for (int i = 0; i < size; i++) {
            ((afl) this.f351p.get(i)).mo325i();
        }
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ void mo311b(long j) {
        this.f322a = j;
    }

    /* renamed from: a */
    public final String mo300a(String str) {
        String a = super.mo300a(str);
        for (int i = 0; i < this.f351p.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append("\n");
            sb.append(((afl) this.f351p.get(i)).mo300a(str + "  "));
            a = sb.toString();
        }
        return a;
    }
}
