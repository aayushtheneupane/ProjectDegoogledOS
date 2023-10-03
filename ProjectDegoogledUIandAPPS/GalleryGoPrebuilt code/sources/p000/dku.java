package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.Menu;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dku */
/* compiled from: PG */
public final class dku implements frj, C0438q {

    /* renamed from: a */
    public final Context f6744a;

    /* renamed from: b */
    public final frm f6745b;

    /* renamed from: c */
    public dkt f6746c = null;

    /* renamed from: d */
    private final dge f6747d;

    /* renamed from: e */
    private final grx f6748e;

    /* renamed from: f */
    private final cjo f6749f;

    /* renamed from: g */
    private final gry f6750g = new dks(this);

    public dku(Context context, C0147fh fhVar, frm frm, dge dge, grx grx, cjo cjo) {
        this.f6744a = context;
        this.f6745b = frm;
        this.f6747d = dge;
        this.f6748e = grx;
        this.f6749f = cjo;
        fhVar.mo5ad().mo64a(this);
        frm.mo6068a((int) R.id.oneup_request_code_edit_in_external, (frj) this);
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

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
    }

    /* renamed from: a */
    public final Optional mo4180a(Uri uri, String str, Optional optional) {
        Intent intent = new Intent("android.intent.action.EDIT");
        Uri a = this.f6749f.mo3173a(uri);
        if (Uri.EMPTY.equals(a)) {
            return Optional.empty();
        }
        intent.setDataAndType(a, str);
        intent.setFlags(1);
        if (optional.isPresent()) {
            intent.setComponent((ComponentName) optional.get());
        }
        return Optional.m16285of(intent);
    }

    /* renamed from: a */
    public final Optional mo4181a(cxi cxi, Optional optional) {
        return mo4180a(Uri.parse(cxi.f5910b), cxi.f5914f, optional);
    }

    /* renamed from: b */
    public static ComponentName m6262b(ResolveInfo resolveInfo) {
        return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
    }

    /* renamed from: a */
    public final boolean mo4185a(ResolveInfo resolveInfo) {
        return this.f6744a.getPackageName().equals(resolveInfo.activityInfo.packageName);
    }

    /* renamed from: a */
    public final void mo4183a(int i, Intent intent) {
        Uri data;
        Object[] objArr = {Integer.valueOf(i), intent};
        if (i == -1 && intent != null && (data = intent.getData()) != null && "content".equals(data.getScheme()) && "media".equals(data.getAuthority())) {
            ieh a = gte.m10770a(this.f6747d.mo4115a(intent.getData()), dkr.f6742a, (Executor) idh.f13918a);
            this.f6748e.mo6987a(grw.m10688c(a), this.f6750g);
        }
    }

    /* renamed from: a */
    public final void mo2556a() {
        this.f6748e.mo6988a(this.f6750g);
    }

    /* renamed from: a */
    public final List mo4182a(Intent intent) {
        return (List) ife.m12898e((Object) this.f6744a.getPackageManager().queryIntentActivities(intent, 65536));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4184a(Menu menu, Optional optional) {
        menu.removeItem(R.id.oneup_menu_edit_in);
        if (optional.isPresent() && (((cxi) optional.get()).f5909a & 1048576) == 0) {
            Optional a = mo4181a((cxi) optional.get(), Optional.empty());
            if (a.isPresent()) {
                List<ResolveInfo> a2 = mo4182a((Intent) a.get());
                if (!a2.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    for (ResolveInfo resolveInfo : a2) {
                        if (!mo4185a(resolveInfo)) {
                            arrayList.add(resolveInfo);
                        }
                    }
                    if (arrayList.size() > 1) {
                        menu.add(0, R.id.oneup_menu_edit_in, 0, R.string.oneup_edit_in);
                    } else if (arrayList.size() == 1) {
                        PackageManager packageManager = this.f6744a.getPackageManager();
                        menu.add(0, R.id.oneup_menu_edit_in, 0, this.f6744a.getResources().getString(R.string.oneup_edit_in_app, new Object[]{((ResolveInfo) arrayList.get(0)).loadLabel(packageManager)}));
                    }
                }
            }
        }
    }
}
