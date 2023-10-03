package p000;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.button.MaterialButton;

/* renamed from: geq */
/* compiled from: PG */
public final class geq extends gfa {

    /* renamed from: Z */
    public RecyclerView f11119Z;

    /* renamed from: a */
    public ged f11120a;

    /* renamed from: aa */
    public RecyclerView f11121aa;

    /* renamed from: ab */
    public View f11122ab;

    /* renamed from: ac */
    public int f11123ac;

    /* renamed from: ae */
    private int f11124ae;

    /* renamed from: af */
    private View f11125af;

    /* renamed from: b */
    public gea f11126b;

    /* renamed from: c */
    public geu f11127c;

    /* renamed from: d */
    public gec f11128d;

    /* renamed from: b */
    static int m10161b(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: P */
    public final C0607wg mo6518P() {
        return (C0607wg) this.f11121aa.getLayoutManager();
    }

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        if (bundle == null) {
            bundle = this.f9592k;
        }
        this.f11124ae = bundle.getInt("THEME_RES_ID_KEY");
        this.f11120a = (ged) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f11126b = (gea) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f11127c = (geu) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(mo2634k(), this.f11124ae);
        this.f11128d = new gec(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        geu geu = this.f11126b.f11081a;
        boolean b = ges.m10170b(contextThemeWrapper);
        View inflate = cloneInContext.inflate(!b ? R.layout.mtrl_calendar_horizontal : R.layout.mtrl_calendar_vertical, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        C0340mj.m14698a((View) gridView, (C0315ll) new geg());
        gridView.setAdapter(new gee());
        gridView.setNumColumns(geu.f11133e);
        gridView.setEnabled(false);
        this.f11121aa = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.f11121aa.setLayoutManager(new geh(this, b ? 1 : 0, b));
        this.f11121aa.setTag("MONTHS_VIEW_GROUP_TAG");
        gey gey = new gey(contextThemeWrapper, this.f11120a, this.f11126b, new gei(this));
        this.f11121aa.setAdapter(gey);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.f11119Z = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f11119Z.setLayoutManager(new C0598vy(integer));
            this.f11119Z.setAdapter(new gfg(this));
            this.f11119Z.addItemDecoration(new gej(this));
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            C0340mj.m14698a((View) materialButton, (C0315ll) new gek(this));
            MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(R.id.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) inflate.findViewById(R.id.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.f11125af = inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
            this.f11122ab = inflate.findViewById(R.id.mtrl_calendar_day_selector_frame);
            mo6520e(1);
            materialButton.setText(this.f11127c.f11130b);
            this.f11121aa.addOnScrollListener(new gel(this, gey, materialButton));
            materialButton.setOnClickListener(new gem(this));
            materialButton3.setOnClickListener(new gen(this, gey));
            materialButton2.setOnClickListener(new geo(this, gey));
        }
        if (!ges.m10170b(contextThemeWrapper)) {
            new C0608wh().mo4650a(this.f11121aa);
        }
        this.f11121aa.scrollToPosition(gey.mo6542a(this.f11127c));
        return inflate;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.f11124ae);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f11120a);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f11126b);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f11127c);
    }

    /* renamed from: f */
    private final void m10162f(int i) {
        this.f11121aa.post(new gef(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6519a(geu geu) {
        gey gey = (gey) this.f11121aa.getAdapter();
        int a = gey.mo6542a(geu);
        int a2 = a - gey.mo6542a(this.f11127c);
        int abs = Math.abs(a2);
        this.f11127c = geu;
        if (abs <= 3) {
            m10162f(a);
        } else if (a2 > 0) {
            this.f11121aa.scrollToPosition(a - 3);
            m10162f(a);
        } else {
            this.f11121aa.scrollToPosition(a + 3);
            m10162f(a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo6520e(int i) {
        this.f11123ac = i;
        if (i == 2) {
            this.f11119Z.getLayoutManager().mo10470d(this.f11127c.f11132d - ((gfg) this.f11119Z.getAdapter()).f11159c.f11126b.f11081a.f11132d);
            this.f11125af.setVisibility(0);
            this.f11122ab.setVisibility(8);
            return;
        }
        this.f11125af.setVisibility(8);
        this.f11122ab.setVisibility(0);
        mo6519a(this.f11127c);
    }
}
