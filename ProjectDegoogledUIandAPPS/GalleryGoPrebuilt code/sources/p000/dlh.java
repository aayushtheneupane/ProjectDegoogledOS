package p000;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.Menu;
import com.google.android.apps.photosgo.R;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: dlh */
/* compiled from: PG */
public final class dlh {

    /* renamed from: a */
    public final Context f6773a;

    /* renamed from: b */
    public final C0147fh f6774b;

    /* renamed from: c */
    private final cjo f6775c;

    public dlh(Context context, C0147fh fhVar, cjo cjo) {
        this.f6773a = context;
        this.f6774b = fhVar;
        this.f6775c = cjo;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Optional mo4206a(cxi cxi) {
        Uri a = this.f6775c.mo3173a(Uri.parse(cxi.f5910b));
        if (Uri.EMPTY.equals(a)) {
            return Optional.empty();
        }
        Intent intent = new Intent("android.intent.action.ATTACH_DATA");
        intent.setDataAndType(a, cxi.f5914f);
        intent.addFlags(1);
        return Optional.m16285of(intent);
    }

    /* renamed from: a */
    public final List mo4207a(Intent intent) {
        return this.f6773a.getPackageManager().queryIntentActivities(intent, 65536);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4208a(Menu menu, Optional optional) {
        menu.removeItem(R.id.oneup_menu_use_as);
        if (optional.isPresent() && (((cxi) optional.get()).f5909a & 1048576) == 0) {
            Optional a = mo4206a((cxi) optional.get());
            if (a.isPresent()) {
                List a2 = mo4207a((Intent) a.get());
                if (a2.isEmpty()) {
                    return;
                }
                if (a2.size() == 1) {
                    PackageManager packageManager = this.f6773a.getPackageManager();
                    menu.add(0, R.id.oneup_menu_use_as, 0, this.f6773a.getString(R.string.oneup_use_as_one_app, new Object[]{((ResolveInfo) a2.get(0)).loadLabel(packageManager)}));
                    return;
                }
                menu.add(0, R.id.oneup_menu_use_as, 0, this.f6773a.getString(R.string.oneup_use_as));
            }
        }
    }
}
