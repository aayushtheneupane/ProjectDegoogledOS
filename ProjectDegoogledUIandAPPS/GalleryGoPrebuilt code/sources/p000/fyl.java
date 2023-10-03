package p000;

import android.net.Uri;
import android.text.TextUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: fyl */
/* compiled from: PG */
public final class fyl {

    /* renamed from: a */
    private static final Pattern f10703a = Pattern.compile("(\\w+).*");

    /* renamed from: a */
    public static String m9877a(List list) {
        if (list.isEmpty()) {
            return null;
        }
        String valueOf = String.valueOf(hpv.m11887a("+").mo7669a((Iterable) list));
        return valueOf.length() == 0 ? new String("transform=") : "transform=".concat(valueOf);
    }

    /* renamed from: a */
    public static hso m9876a(Uri uri) {
        hso hso;
        hsj j = hso.m12048j();
        String encodedFragment = uri.getEncodedFragment();
        if (TextUtils.isEmpty(encodedFragment) || !encodedFragment.startsWith("transform=")) {
            hso = hso.m12047f();
        } else {
            String substring = encodedFragment.substring(10);
            hqj a = hqj.m11915a("+");
            hso = hso.m12032a(new hqj(a.f13259c, true, a.f13257a, a.f13260d).mo7679a((CharSequence) substring));
        }
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            String str = (String) i.next();
            Matcher matcher = f10703a.matcher(str);
            if (!matcher.matches()) {
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Invalid fragment spec: ") : "Invalid fragment spec: ".concat(valueOf));
            }
            j.mo7908c(matcher.group(1));
        }
        return j.mo7905a();
    }
}
