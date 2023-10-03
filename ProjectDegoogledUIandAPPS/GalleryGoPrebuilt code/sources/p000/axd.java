package p000;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: axd */
/* compiled from: PG */
public final class axd {

    /* renamed from: b */
    private static final String f1815b;

    /* renamed from: c */
    private static final Map f1816c;

    /* renamed from: a */
    public final Map f1817a = f1816c;

    static {
        String property = System.getProperty("http.agent");
        if (!TextUtils.isEmpty(property)) {
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if ((charAt > 31 || charAt == 9) && charAt < 127) {
                    sb.append(charAt);
                } else {
                    sb.append('?');
                }
            }
            property = sb.toString();
        }
        f1815b = property;
        HashMap hashMap = new HashMap(2);
        if (!TextUtils.isEmpty(f1815b)) {
            hashMap.put("User-Agent", Collections.singletonList(new axe(f1815b)));
        }
        f1816c = Collections.unmodifiableMap(hashMap);
    }
}
