package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.TypedValue;

/* renamed from: ggi */
/* compiled from: PG */
public final class ggi {

    /* renamed from: a */
    public final float f11239a;

    /* renamed from: b */
    public final ColorStateList f11240b;

    /* renamed from: c */
    public final int f11241c;

    /* renamed from: d */
    public final ColorStateList f11242d;

    /* renamed from: e */
    public final float f11243e;

    /* renamed from: f */
    public final float f11244f;

    /* renamed from: g */
    public final float f11245g;

    /* renamed from: h */
    public Typeface f11246h;

    /* renamed from: i */
    private final int f11247i;

    /* renamed from: j */
    private final String f11248j;

    /* renamed from: k */
    private final int f11249k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public boolean f11250l = false;

    public ggi(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, ggg.f11236a);
        this.f11239a = obtainStyledAttributes.getDimension(0, 0.0f);
        this.f11240b = gqb.m10615a(context, obtainStyledAttributes, 3);
        gqb.m10615a(context, obtainStyledAttributes, 4);
        gqb.m10615a(context, obtainStyledAttributes, 5);
        this.f11241c = obtainStyledAttributes.getInt(2, 0);
        this.f11247i = obtainStyledAttributes.getInt(1, 1);
        int i2 = !obtainStyledAttributes.hasValue(12) ? 10 : 12;
        this.f11249k = obtainStyledAttributes.getResourceId(i2, 0);
        this.f11248j = obtainStyledAttributes.getString(i2);
        obtainStyledAttributes.getBoolean(14, false);
        this.f11242d = gqb.m10615a(context, obtainStyledAttributes, 6);
        this.f11243e = obtainStyledAttributes.getFloat(7, 0.0f);
        this.f11244f = obtainStyledAttributes.getFloat(8, 0.0f);
        this.f11245g = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void mo6615a() {
        String str;
        if (this.f11246h == null && (str = this.f11248j) != null) {
            this.f11246h = Typeface.create(str, this.f11241c);
        }
        if (this.f11246h == null) {
            int i = this.f11247i;
            if (i == 1) {
                this.f11246h = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.f11246h = Typeface.SERIF;
            } else if (i != 3) {
                this.f11246h = Typeface.DEFAULT;
            } else {
                this.f11246h = Typeface.MONOSPACE;
            }
            this.f11246h = Typeface.create(this.f11246h, this.f11241c);
        }
    }

    /* renamed from: a */
    public final void mo6616a(Context context, ggj ggj) {
        mo6615a();
        int i = this.f11249k;
        if (i == 0) {
            this.f11250l = true;
        }
        if (!this.f11250l) {
            try {
                ggh ggh = new ggh(this, ggj);
                C0321lr.m14624a((Object) ggh);
                if (!context.isRestricted()) {
                    C0071co.m4658a(context, i, new TypedValue(), 0, ggh, false);
                    return;
                }
                ggh.mo9035a(-4);
            } catch (Resources.NotFoundException e) {
                this.f11250l = true;
                ggj.mo6619b();
            } catch (Exception e2) {
                String valueOf = String.valueOf(this.f11248j);
                if (valueOf.length() != 0) {
                    "Error loading font ".concat(valueOf);
                } else {
                    new String("Error loading font ");
                }
                this.f11250l = true;
                ggj.mo6619b();
            }
        } else {
            ggj.mo6618a(this.f11246h);
        }
    }
}
