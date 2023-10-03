package p000;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: dhs */
/* compiled from: PG */
public final /* synthetic */ class dhs implements Callable {

    /* renamed from: a */
    private final dht f6561a;

    public dhs(dht dht) {
        this.f6561a = dht;
    }

    public final Object call() {
        dht dht = this.f6561a;
        hvr a = dht.f6562a.mo4133a().iterator();
        while (a.hasNext()) {
            String str = (String) a.next();
            Bundle bundle = null;
            try {
                bundle = dht.f6563b.call(new Uri.Builder().scheme("content").authority(str).build(), "editor_data", (String) null, (Bundle) null);
            } catch (Throwable th) {
                new Object[1][0] = str;
            }
            if (bundle != null) {
                String string = bundle.getString("editor_icon_uri");
                String string2 = bundle.getString("editor_package_name");
                String string3 = bundle.getString("editor_activity_name");
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                    dhp d = dhq.m6122d();
                    d.mo4144a(cof.m4690b(Uri.parse(string)));
                    d.mo4146b(string2);
                    d.mo4145a(string3);
                    return Optional.m16285of(d.mo4143a());
                }
            }
        }
        return Optional.empty();
    }
}
