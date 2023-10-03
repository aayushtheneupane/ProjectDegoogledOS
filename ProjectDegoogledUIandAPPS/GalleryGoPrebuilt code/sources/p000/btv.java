package p000;

import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;

/* renamed from: btv */
/* compiled from: PG */
final class btv implements gvc {

    /* renamed from: a */
    private final /* synthetic */ TextView f3577a;

    /* renamed from: b */
    private final /* synthetic */ btw f3578b;

    public btv(btw btw, TextView textView) {
        this.f3578b = btw;
        this.f3577a = textView;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "SingleDeviceFolderFragment: Failed to get fragment data.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        buf buf = (buf) obj;
        btw btw = this.f3578b;
        TextView textView = this.f3577a;
        ArrayList arrayList = new ArrayList();
        if (buf.mo2762a() == 0) {
            arrayList.add(btw.f3583e.getString(R.string.singlefolder_empty_summary));
        } else {
            arrayList.add(C0643xp.m15940a(btw.f3583e, R.string.folder_summary_item_count_icu, "PHOTO_COUNT", Long.valueOf(buf.mo2763b())));
            arrayList.add(Formatter.formatShortFileSize(btw.f3583e, buf.mo2762a()));
        }
        int c = buf.mo2764c();
        if (c != 0) {
            if (c == 2) {
                arrayList.add(btw.f3583e.getString(R.string.sd_card_summary));
            }
            textView.setText(TextUtils.join(" · ", arrayList));
            return;
        }
        throw null;
    }
}
