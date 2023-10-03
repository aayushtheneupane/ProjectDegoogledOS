package p000;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.infosheet.InfoSheetListView;
import com.google.android.apps.photosgo.infosheet.InfoView;
import java.util.Locale;

/* renamed from: csy */
/* compiled from: PG */
public final class csy {

    /* renamed from: a */
    public final Context f5609a;

    /* renamed from: b */
    public final ctk f5610b;

    /* renamed from: c */
    public final ViewGroup f5611c;

    /* renamed from: d */
    private final InfoSheetListView f5612d;

    /* renamed from: e */
    private final LayoutInflater f5613e;

    /* renamed from: f */
    private final ViewGroup f5614f;

    /* renamed from: g */
    private gin f5615g = null;

    public csy(hbl hbl, InfoSheetListView infoSheetListView, ctk ctk) {
        this.f5609a = hbl;
        this.f5612d = infoSheetListView;
        this.f5610b = ctk;
        LayoutInflater from = LayoutInflater.from(hbl);
        this.f5613e = from;
        from.inflate(R.layout.info_sheet_listview, infoSheetListView, true);
        this.f5611c = (ViewGroup) infoSheetListView.findViewById(R.id.loading_view);
        this.f5614f = (ViewGroup) infoSheetListView.findViewById(R.id.list_view);
    }

    /* renamed from: a */
    public final ctb mo3808a() {
        View inflate = this.f5613e.inflate(R.layout.infosheet_info_view, this.f5614f, false);
        this.f5614f.addView(inflate);
        return ((InfoView) inflate).mo2635n();
    }

    /* renamed from: a */
    public final void mo3809a(cxq cxq) {
        if (cxq.mo3890n().isPresent() && cxq.mo3891o().isPresent()) {
            try {
                this.f5609a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.US, "geo:0,0?q=%f,%f", new Object[]{cxq.mo3890n().get(), cxq.mo3891o().get()}))));
            } catch (ActivityNotFoundException e) {
                cwn.m5511a((Throwable) e, "InfoSheetListViewPeer: No activity handles intent geo:{lat},{lng}", new Object[0]);
                try {
                    this.f5609a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.US, "https://www.google.com/maps/search/?api=1&query=%f,%f", new Object[]{cxq.mo3890n().get(), cxq.mo3891o().get()}))));
                } catch (ActivityNotFoundException e2) {
                    cwn.m5511a((Throwable) e2, "InfoSheetListViewPeer: No activity handles ACTION_VIEW for maps URL.", new Object[0]);
                    if (this.f5615g == null) {
                        this.f5615g = gin.m10373a((View) this.f5612d, (int) R.string.info_sheet_no_app_support_map, -1);
                    }
                    gin gin = this.f5615g;
                    gir a = gir.m10379a();
                    gip gip = gin.f11427n;
                    synchronized (a.f11439a) {
                        if (!a.mo6728c(gip)) {
                            this.f5615g.mo6715c();
                        }
                    }
                }
            }
        }
    }
}
