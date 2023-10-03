package p000;

import android.content.Intent;
import java.util.Calendar;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: eeh */
/* compiled from: PG */
final /* synthetic */ class eeh implements icf {

    /* renamed from: a */
    private final een f8098a;

    /* renamed from: b */
    private final String f8099b;

    /* renamed from: c */
    private final String f8100c;

    public eeh(een een, String str, String str2) {
        this.f8098a = een;
        this.f8099b = str;
        this.f8100c = str2;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        een een = this.f8098a;
        String str = this.f8099b;
        String str2 = this.f8100c;
        Optional optional = (Optional) obj;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setClassName(str2, str);
        Optional map = Optional.ofNullable(een.f8109d.resolveActivity(intent, 0)).map(new eei(een)).map(eej.f8102a);
        if (!map.isPresent()) {
            cwn.m5510a("SharingDao: No package found for application[%s].", str);
            return ife.m12820a((Object) null);
        }
        edv j = edw.m7289j();
        j.mo4724a(str);
        j.mo4726b(str2);
        j.mo4728c((String) map.get());
        j.mo4723a(((Calendar) een.f8106a.mo2097a()).getTimeInMillis());
        j.mo4722a(((Integer) optional.map(eee.f8095a).orElse(0)).intValue() + 1);
        j.mo4725a(een.mo4752a(new Intent("android.intent.action.SEND_MULTIPLE").setClassName(str2, str).setType("image/*")));
        j.mo4727b(een.mo4752a(new Intent("android.intent.action.SEND").setClassName(str2, str).setType("video/*")));
        edw a = j.mo4721a();
        if (!optional.isPresent()) {
            return een.mo4751a((List) hso.m12033a((Object) a));
        }
        return een.mo4754c(hso.m12033a((Object) a));
    }
}
