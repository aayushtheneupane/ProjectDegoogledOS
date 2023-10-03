package p000;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import java.util.Iterator;

/* renamed from: fmz */
/* compiled from: PG */
final class fmz {

    /* renamed from: a */
    public static final hqk f10072a = ife.m12811a((hqk) new fmy());

    /* renamed from: a */
    static final hpy m9252a() {
        int i = Build.VERSION.SDK_INT;
        return hpy.m11893b(Long.valueOf(Os.sysconf(OsConstants._SC_CLK_TCK)));
    }

    /* renamed from: a */
    public static hpy m9253a(String str) {
        try {
            Iterable a = hqj.m11914a(' ').mo7679a((CharSequence) str);
            ife.m12898e((Object) a);
            Iterator it = a.iterator();
            ife.m12898e((Object) it);
            ife.m12845a(true, (Object) "numberToAdvance must be nonnegative");
            int i = 0;
            while (i < 21 && it.hasNext()) {
                it.next();
                i++;
            }
            if (it.hasNext()) {
                return hpy.m11893b(Long.decode((String) it.next()));
            }
            StringBuilder sb = new StringBuilder(91);
            sb.append("position (");
            sb.append(21);
            sb.append(") must be less than the number of elements that remained (");
            sb.append(i);
            sb.append(")");
            throw new IndexOutOfBoundsException(sb.toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return hph.f13219a;
        }
    }
}
