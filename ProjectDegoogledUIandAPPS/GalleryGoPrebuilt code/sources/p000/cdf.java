package p000;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.VideoView;
import com.google.android.apps.photosgo.R;
import java.util.List;

/* renamed from: cdf */
/* compiled from: PG */
final class cdf implements gvc {

    /* renamed from: a */
    private final /* synthetic */ cdh f4100a;

    public cdf(cdh cdh) {
        this.f4100a = cdh;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        this.f4100a.mo3048c();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        int i;
        String str;
        ceq ceq;
        cco cco = (cco) obj;
        if (cco.mo3024a().isPresent() && !((dtl) ife.m12898e((Object) this.f4100a.f4115n)).mo4409d()) {
            iae c = ((iag) cco.mo3024a().get()).mo8329c();
            if (c == null) {
                cwn.m5515b(((iag) cco.mo3024a().get()).mo8320b(), "VideoEditorFragmentPeer: Failed to fetch media.", new Object[0]);
                this.f4100a.mo3048c();
                return;
            }
            cdh cdh = this.f4100a;
            cdh.f4116o = (cxi) c.f13714a;
            ((dtl) ife.m12898e((Object) cdh.f4115n)).mo4404a(this.f4100a.f4116o);
            ((dtl) ife.m12898e((Object) this.f4100a.f4115n)).mo4410e();
            View view = this.f4100a.f4103b.f9573L;
            if (view != null) {
                VideoView videoView = (VideoView) view.findViewById(R.id.video_view);
                cdh cdh2 = this.f4100a;
                int b = cdu.m4147b(cdh2.f4104c.f4033b);
                int i2 = b - 1;
                if (b != 0) {
                    if (i2 == 0) {
                        cbx cbx = cdh2.f4104c;
                        str = (cbx.f4033b == 1 ? (cxi) cbx.f4034c : cxi.f5908x).f5910b;
                    } else if (i2 == 1) {
                        cbx cbx2 = cdh2.f4104c;
                        if (cbx2.f4033b == 4) {
                            ceq = (ceq) cbx2.f4034c;
                        } else {
                            ceq = ceq.f4197g;
                        }
                        str = ceq.f4200b;
                    } else if (i2 != 2) {
                        String a = cdu.m4144a(cdu.m4147b(cdh2.f4104c.f4033b));
                        StringBuilder sb = new StringBuilder(a.length() + 21);
                        sb.append("Unsupported TypeCase ");
                        sb.append(a);
                        throw new IllegalArgumentException(sb.toString());
                    } else {
                        throw new IllegalArgumentException("TypeCase not set");
                    }
                    videoView.setVideoPath(str);
                    this.f4100a.mo3049d().f4177l = (cxi) ife.m12898e((Object) this.f4100a.f4116o);
                    ((dtl) ife.m12898e((Object) this.f4100a.f4115n)).mo4403a(1);
                } else {
                    throw null;
                }
            } else {
                return;
            }
        }
        if (!((Boolean) cco.mo3026c().orElse(true)).booleanValue()) {
            this.f4100a.mo3048c();
        } else if (cco.mo3025b().isPresent()) {
            cef d = this.f4100a.mo3049d();
            List<Bitmap> list = (List) cco.mo3025b().get();
            cdn a2 = d.f4169d.mo2635n();
            if (a2.f4127g.size() != list.size()) {
                a2.f4127g.clear();
                for (Bitmap add : list) {
                    a2.f4127g.add(add);
                }
                int a3 = a2.mo3050a();
                if (list.isEmpty() || ((i = a2.f4128h) > 0 && i == a3)) {
                    a2.mo3051b();
                } else {
                    a2.f4122b.requestLayout();
                }
            }
        }
    }
}
