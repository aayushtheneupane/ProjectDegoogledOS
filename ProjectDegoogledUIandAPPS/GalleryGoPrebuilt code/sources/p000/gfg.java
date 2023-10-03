package p000;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: gfg */
/* compiled from: PG */
final class gfg extends C0634xg {

    /* renamed from: c */
    public final geq f11159c;

    public gfg(geq geq) {
        this.f11159c = geq;
    }

    /* renamed from: a */
    public final int mo220a() {
        return this.f11159c.f11126b.f11085e;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo223a(C0667ym ymVar, int i) {
        gff gff = (gff) ymVar;
        int i2 = this.f11159c.f11126b.f11081a.f11132d + i;
        String string = gff.f11158p.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        TextView textView = gff.f11158p;
        Locale locale = Locale.getDefault();
        Integer valueOf = Integer.valueOf(i2);
        textView.setText(String.format(locale, "%d", new Object[]{valueOf}));
        gff.f11158p.setContentDescription(String.format(string, new Object[]{valueOf}));
        gec gec = this.f11159c.f11128d;
        Calendar d = ggf.m10259d();
        geb geb = d.get(1) == i2 ? gec.f11098f : gec.f11096d;
        for (Long longValue : this.f11159c.f11120a.mo6504a()) {
            d.setTimeInMillis(longValue.longValue());
            if (d.get(1) == i2) {
                geb = gec.f11097e;
            }
        }
        geb.mo6503a(gff.f11158p);
        gff.f11158p.setOnClickListener(new gfe(this, i2));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0667ym mo222a(ViewGroup viewGroup, int i) {
        return new gff((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }
}
