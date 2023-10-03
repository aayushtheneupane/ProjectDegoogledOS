package p000;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: crc */
/* compiled from: PG */
final class crc implements gvc {

    /* renamed from: a */
    private final /* synthetic */ crd f5454a;

    public crc(crd crd) {
        this.f5454a = crd;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "HomeFragment: Failed to fetch home fragment data.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        crq crq = (crq) obj;
        int d = crq.mo3784d();
        int i = d - 1;
        if (d == 0) {
            throw null;
        } else if (i != 0) {
            boolean z = true;
            if (i != 1) {
                if (i == 2) {
                    View view = this.f5454a.f5476q;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                    this.f5454a.f5475p.setVisibility(0);
                } else if (i == 3) {
                    crd crd = this.f5454a;
                    View view2 = crd.f5476q;
                    if (view2 == null) {
                        crd.f5476q = crd.f5477r.inflate();
                        cqt cqt = crd.f5460a;
                        ((TextView) crd.f5476q.findViewById(R.id.permission_rationale)).setText(cqt.mo5657p().getString(R.string.home_permission_request, new Object[]{cqt.mo5635a((int) R.string.app_name)}));
                        ((Button) crd.f5476q.findViewById(R.id.allow)).setOnClickListener(crd.f5465f.mo7575a((View.OnClickListener) new crb(crd), "allow permission"));
                    } else {
                        view2.setVisibility(0);
                    }
                    this.f5454a.f5475p.setVisibility(8);
                    return;
                }
                if (crq.mo3781a().isPresent()) {
                    dkj dkj = (dkj) crq.mo3781a().get();
                    int a = C0637xj.m15889a(dkj.f6726b);
                    if (a == 0) {
                        a = 2;
                    }
                    int i2 = a - 1;
                    if (i2 == 2 || i2 == 3) {
                        crd crd2 = this.f5454a;
                        if (crd2.f5460a.mo5659r().mo6418a("OnboardingDialogFragment") == null) {
                            dkb dkb = new dkb();
                            ftr.m9611b(dkb);
                            ftr.m9610a((C0147fh) dkb);
                            hcl.m11210a(dkb, dkj);
                            dkb.mo5429b(crd2.f5460a.mo5659r(), "OnboardingDialogFragment");
                        }
                    }
                }
                int e = crq.mo3785e();
                int i3 = e - 1;
                if (e != 0) {
                    if (i3 == 0 || i3 == 1) {
                        this.f5454a.f5482w.setVisibility(0);
                        this.f5454a.f5482w.mo3383a();
                        crd crd3 = this.f5454a;
                        crd3.f5478s.setVisibility(0);
                        crd3.f5479t.setVisible(true);
                        crd3.f5481v.setVisible(false);
                        if (crd3.f5457C != 2) {
                            iir g = dwi.f7492h.mo8793g();
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = false;
                            }
                            dwi dwi = (dwi) g.f14318b;
                            int i4 = dwi.f7494a | 2;
                            dwi.f7494a = i4;
                            dwi.f7496c = true;
                            dwi.f7494a = i4 | 32;
                            dwi.f7500g = R.layout.empty_state_view;
                            crd3.mo3776a(dwn.m6817a((dwi) g.mo8770g()), "PHOTOS");
                        }
                        crd3.f5457C = 2;
                    } else if (i3 == 2) {
                        this.f5454a.f5482w.setVisibility(0);
                        this.f5454a.f5482w.mo3384b();
                        crd crd4 = this.f5454a;
                        crd4.f5478s.setVisibility(0);
                        crd4.f5479t.setVisible(false);
                        crd4.f5481v.setVisible(true);
                        if (crd4.f5457C != 3) {
                            crd4.mo3776a(brp.m3494a(brn.f3429h), "FOLDERS");
                        }
                        crd4.f5457C = 3;
                    } else if (i3 == 3) {
                        this.f5454a.f5482w.setVisibility(8);
                    }
                    crd crd5 = this.f5454a;
                    int e2 = crq.mo3785e();
                    int i5 = e2 - 1;
                    if (e2 != 0) {
                        if (i5 == 0 || i5 == 1) {
                            z = true ^ crq.mo3782b();
                        } else if (i5 == 2) {
                            z = true ^ crq.mo3783c();
                        } else if (i5 != 3) {
                            throw new IllegalArgumentException("Unknown current tab.");
                        }
                        crd5.mo3777a(z);
                        return;
                    }
                    throw null;
                }
                throw null;
            }
        } else {
            crd crd6 = this.f5454a;
            crd6.f5462c.mo3792a((C0147fh) crd6.f5460a);
        }
    }
}
