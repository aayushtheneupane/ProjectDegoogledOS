package p000;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.google.android.apps.photosgo.R;
import com.google.android.material.datepicker.MaterialCalendarGridView;

/* renamed from: gey */
/* compiled from: PG */
final class gey extends C0634xg {

    /* renamed from: c */
    public final gep f11144c;

    /* renamed from: d */
    private final gea f11145d;

    /* renamed from: e */
    private final ged f11146e;

    /* renamed from: f */
    private final int f11147f;

    public gey(Context context, ged ged, gea gea, gep gep) {
        geu geu = gea.f11081a;
        geu geu2 = gea.f11082b;
        geu geu3 = gea.f11083c;
        if (geu.compareTo(geu3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (geu3.compareTo(geu2) <= 0) {
            this.f11147f = (gev.f11135a * geq.m10161b(context)) + (ges.m10170b(context) ? geq.m10161b(context) : 0);
            this.f11145d = gea;
            this.f11146e = ged;
            this.f11144c = gep;
            mo10536a(true);
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    /* renamed from: a */
    public final int mo220a() {
        return this.f11145d.f11086f;
    }

    /* renamed from: b */
    public final long mo224b(int i) {
        return this.f11145d.f11081a.mo6527b(i).f11129a.getTimeInMillis();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final geu mo6543f(int i) {
        return this.f11145d.f11081a.mo6527b(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo6542a(geu geu) {
        return this.f11145d.f11081a.mo6526b(geu);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo223a(C0667ym ymVar, int i) {
        gex gex = (gex) ymVar;
        geu b = this.f11145d.f11081a.mo6527b(i);
        gex.f11142p.setText(b.f11130b);
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) gex.f11143q.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !b.equals(materialCalendarGridView.getAdapter().f11136b)) {
            gev gev = new gev(b, this.f11146e, this.f11145d);
            materialCalendarGridView.setNumColumns(b.f11133e);
            materialCalendarGridView.setAdapter((ListAdapter) gev);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new gew(this, materialCalendarGridView));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0667ym mo222a(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!ges.m10170b(viewGroup.getContext())) {
            return new gex(linearLayout, false);
        }
        linearLayout.setLayoutParams(new C0648xu(-1, this.f11147f));
        return new gex(linearLayout, true);
    }
}
