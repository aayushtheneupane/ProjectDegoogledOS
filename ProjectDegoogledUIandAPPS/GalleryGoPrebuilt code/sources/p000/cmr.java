package p000;

import com.google.android.apps.photosgo.R;
import java.util.Collection;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: cmr */
/* compiled from: PG */
final /* synthetic */ class cmr implements BiConsumer {

    /* renamed from: a */
    private final cmv f4696a;

    public cmr(cmv cmv) {
        this.f4696a = cmv;
    }

    public final void accept(Object obj, Object obj2) {
        int i;
        cmv cmv = this.f4696a;
        String str = (String) obj;
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        cmv.f4711l = false;
        if (booleanValue) {
            iir g = eai.f7772e.mo8793g();
            if (!cmv.f4700a.f4692b) {
                i = R.string.folder_creation_copy_in_progress;
            } else {
                i = R.string.folder_creation_move_in_progress;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eai eai = (eai) g.f14318b;
            eai.f7774a |= 1;
            eai.f7775b = i;
            int size = cmv.f4700a.f4693c.size();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eai eai2 = (eai) g.f14318b;
            eai2.f7774a |= 2;
            eai2.f7776c = size;
            eai.m7019a(eai2);
            eaj a = eaj.m7021a((eai) g.mo8770g());
            a.mo5429b(cmv.f4701b.mo5659r(), "progress_dialog_tag");
            grx grx = cmv.f4705f;
            ckk ckk = cmv.f4704e;
            hto a2 = hto.m12125a((Collection) cmv.f4700a.f4693c);
            boolean z = cmv.f4700a.f4692b;
            eah eah = a.mo2635n().f7789d;
            eah.getClass();
            grx.mo6986a(grw.m10689d(ckk.mo3201a(a2, str, z, new cmu(eah), a.mo2635n().f7791f)), grt.m10681a(), cmv.f4708i);
        } else if (!cmv.f4700a.f4692b) {
            cmv.f4703d.mo2572a((int) R.string.folder_creation_copy_no_permission);
        } else {
            cmv.f4703d.mo2572a((int) R.string.folder_creation_move_no_permission);
        }
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
