package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: hos */
/* compiled from: PG */
public final class hos {

    /* renamed from: a */
    public final hlz f13168a;

    /* renamed from: b */
    public final iqk f13169b;

    /* renamed from: c */
    public final iqk f13170c;

    /* renamed from: d */
    public View f13171d;

    public hos(hlz hlz, iqk iqk, iqk iqk2) {
        this.f13168a = hlz;
        this.f13169b = iqk;
        this.f13170c = iqk2;
    }

    /* renamed from: a */
    public static final String m11845a(String str, View view) {
        String str2 = (String) view.getTag(R.id.tiktok_event_internal_trace);
        if (str2 == null) {
            str2 = view.getClass().getSimpleName();
        }
        StringBuilder sb = new StringBuilder(str.length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m11846a(Context context) {
        if (context != null) {
            if (context instanceof C0149fj) {
                return !((C0149fj) context).mo5851d().mo6443c();
            }
            if (context instanceof ContextWrapper) {
                return m11846a(((ContextWrapper) context).getBaseContext());
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void mo7632a(View view, View.OnClickListener onClickListener) {
        view.setOnClickListener(new hop(this, hoo.f13159a, onClickListener, view));
    }

    /* renamed from: a */
    public final void mo7633a(View view, hoi hoi) {
        mo7632a(view, (View.OnClickListener) new hon(hoi));
    }
}
