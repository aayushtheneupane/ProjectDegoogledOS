package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.foldermanagement.creation.FolderCreationFragmentPeer$2;
import com.google.android.apps.photosgo.foldermanagement.creation.FolderCreationFragmentPeer$3;
import com.google.android.apps.photosgo.foldermanagement.creation.FolderCreationFragmentPeer$4;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* renamed from: clr */
/* compiled from: PG */
public final class clr implements C0438q {

    /* renamed from: y */
    private static final Pattern f4623y = Pattern.compile("\\w[\\w .,_\\-()]*");

    /* renamed from: A */
    private final iek f4624A;

    /* renamed from: a */
    public final Context f4625a;

    /* renamed from: b */
    public final cla f4626b;

    /* renamed from: c */
    public final cnh f4627c;

    /* renamed from: d */
    public final cml f4628d;

    /* renamed from: e */
    public final blu f4629e;

    /* renamed from: f */
    public final cmg f4630f;

    /* renamed from: g */
    public final ckk f4631g;

    /* renamed from: h */
    public final gus f4632h;

    /* renamed from: i */
    public final egp f4633i;

    /* renamed from: j */
    public final gvi f4634j;

    /* renamed from: k */
    public final grx f4635k;

    /* renamed from: l */
    public final hlz f4636l;

    /* renamed from: m */
    public final iij f4637m;

    /* renamed from: n */
    public final gry f4638n;

    /* renamed from: o */
    public final gry f4639o;

    /* renamed from: p */
    public final gry f4640p;

    /* renamed from: q */
    public View f4641q;

    /* renamed from: r */
    public TextInputEditText f4642r;

    /* renamed from: s */
    public TextInputLayout f4643s;

    /* renamed from: t */
    public gwd f4644t;

    /* renamed from: u */
    public hso f4645u = hso.m12047f();

    /* renamed from: v */
    public boolean f4646v;

    /* renamed from: w */
    public boolean f4647w;

    /* renamed from: x */
    public final gvc f4648x = new clp(this);

    /* renamed from: z */
    private final clb f4649z;

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
    }

    /* renamed from: b */
    public final void mo2558b() {
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
    }

    /* renamed from: c */
    public final void mo2560c() {
    }

    public clr(Context context, clb clb, cla cla, cnh cnh, cml cml, blu blu, cmg cmg, ckk ckk, gus gus, egp egp, gvi gvi, grx grx, hlz hlz, iij iij, iek iek) {
        cla cla2 = cla;
        egp egp2 = egp;
        this.f4625a = context;
        this.f4649z = clb;
        this.f4626b = cla2;
        this.f4627c = cnh;
        this.f4628d = cml;
        this.f4629e = blu;
        this.f4630f = cmg;
        this.f4631g = ckk;
        this.f4632h = gus;
        this.f4633i = egp2;
        this.f4634j = gvi;
        this.f4635k = grx;
        this.f4636l = hlz;
        this.f4637m = iij;
        this.f4624A = iek;
        this.f4638n = new FolderCreationFragmentPeer$2(new clc(this), new clg(this));
        this.f4639o = new FolderCreationFragmentPeer$3(new clh(this), new cli(this));
        this.f4640p = new FolderCreationFragmentPeer$4(new clj(this), new clk(this));
        cla2.f4595a.mo64a(this);
        cla2.f4595a.mo64a(egp2);
    }

    /* renamed from: b */
    public final void mo3246b(int i, hto hto) {
        int i2;
        cmg cmg = this.f4630f;
        String str = (String) cmg.f4672c.getOrDefault(cmg.mo3255b(), "");
        if (str.isEmpty()) {
            mo3245a(true);
            cwn.m5514b("FolderCreationFragmentPeer: Unable to get volume path.", new Object[0]);
            return;
        }
        String a = m4510a(((TextInputEditText) ife.m12885c((Object) this.f4642r)).getText());
        ckk ckk = this.f4631g;
        ieh a2 = gte.m10773a(ckk.f4566g.mo5933a(hmq.m11749a((Callable) new cjx(new File(str), a))), SecurityException.class, (icf) new ckb(ckk, str, a), (Executor) ckk.f4567h);
        iir g = eai.f7772e.mo8793g();
        if (i == 1) {
            i2 = R.string.folder_creation_move_in_progress;
        } else {
            i2 = R.string.folder_creation_copy_in_progress;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eai eai = (eai) g.f14318b;
        eai.f7774a = 1 | eai.f7774a;
        eai.f7775b = i2;
        int size = hto.size();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eai eai2 = (eai) g.f14318b;
        eai2.f7774a |= 2;
        eai2.f7776c = size;
        eai.m7019a(eai2);
        eaj a3 = eaj.m7021a((eai) g.mo8770g());
        a3.mo5429b(this.f4626b.mo5659r(), "progress_dialog_tag");
        this.f4635k.mo6986a(grw.m10689d(gte.m10771a(a2, (icf) new cld(this, hto, i, a3), (Executor) this.f4624A)), grt.m10681a(), this.f4640p);
    }

    /* renamed from: a */
    public final void mo3245a(boolean z) {
        this.f4646v = z;
        mo3247e();
    }

    /* renamed from: d */
    public static cla m4512d() {
        return cla.m4489a(clb.f4598d);
    }

    /* renamed from: a */
    public static cla m4509a(clb clb) {
        return cla.m4489a(clb);
    }

    /* renamed from: a */
    public final void mo3244a(int i, hto hto) {
        mo3245a(false);
        if (i == 1) {
            Bundle bundle = new Bundle();
            imi.m14108a(bundle, "media_key", (List) hto.mo7884g());
            this.f4635k.mo6986a(grw.m10690e(this.f4633i.mo4795a((List) hto.mo7884g())), grt.m10682a((Parcelable) bundle), this.f4639o);
            return;
        }
        mo3246b(i, hto);
    }

    /* renamed from: a */
    public static boolean m4511a(String str) {
        if (str.trim().isEmpty() || str.trim().length() > 127) {
            return false;
        }
        return f4623y.matcher(str.trim()).matches();
    }

    /* renamed from: a */
    public static String m4510a(Editable editable) {
        return editable != null ? editable.toString().trim() : "";
    }

    /* renamed from: f */
    public final void mo3248f() {
        int i;
        if (!m4511a(m4510a(((TextInputEditText) ife.m12898e((Object) this.f4642r)).getText()))) {
            ((TextInputLayout) ife.m12898e((Object) this.f4643s)).mo3687a((CharSequence) this.f4625a.getString(R.string.folder_creation_invalid_folder_name));
        } else if (!clb.f4598d.equals(this.f4649z)) {
            clb clb = this.f4649z;
            if (!clb.f4601b) {
                i = 2;
            } else {
                i = 1;
            }
            mo3244a(i, hto.m12125a((Collection) clb.f4602c));
        } else {
            this.f4627c.mo3271a(cle.f4608a, "add items to folder");
        }
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
        cmg cmg = this.f4630f;
        cmg.f4670a.clear();
        cmg.f4672c = hvb.f13454a;
        cmg.f4671b = cml.f4678a;
    }

    /* renamed from: e */
    public final void mo3247e() {
        TextInputEditText textInputEditText;
        hvs i = this.f4645u.listIterator();
        while (true) {
            boolean z = false;
            if (!i.hasNext()) {
                break;
            }
            ckz ckz = (ckz) i.next();
            if (this.f4646v && this.f4647w) {
                z = true;
            }
            ckz.mo3227a(z);
        }
        if (this.f4641q != null && (textInputEditText = this.f4642r) != null && !m4511a(m4510a(textInputEditText.getText()))) {
            this.f4641q.findViewById(R.id.folder_creation_create_button).setEnabled(false);
        }
    }
}
