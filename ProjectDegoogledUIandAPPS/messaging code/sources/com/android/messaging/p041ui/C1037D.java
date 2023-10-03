package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1416U;

/* renamed from: com.android.messaging.ui.D */
public class C1037D {
    private static C1037D sInstance;

    /* renamed from: GF */
    private Drawable f1619GF;

    /* renamed from: HF */
    private Drawable f1620HF;

    /* renamed from: IF */
    private Drawable f1621IF;

    /* renamed from: JF */
    private Drawable f1622JF;

    /* renamed from: KF */
    private Drawable f1623KF;

    /* renamed from: MF */
    private Drawable f1624MF;

    /* renamed from: NF */
    private Drawable f1625NF;

    /* renamed from: OF */
    private Drawable f1626OF;

    /* renamed from: PF */
    private Drawable f1627PF;

    /* renamed from: QF */
    private Drawable f1628QF;

    /* renamed from: RF */
    private Drawable f1629RF;

    /* renamed from: SF */
    private Drawable f1630SF;

    /* renamed from: TF */
    private Drawable f1631TF;

    /* renamed from: UF */
    private Drawable f1632UF;

    /* renamed from: VF */
    private int f1633VF;

    /* renamed from: WF */
    private int f1634WF;

    /* renamed from: XF */
    private int f1635XF;

    /* renamed from: YF */
    private int f1636YF;

    /* renamed from: ZF */
    private int f1637ZF;
    private TypedArray mColors;
    private final Context mContext;

    /* renamed from: ob */
    private int f1638ob;

    private C1037D(Context context) {
        this.mContext = context;
        mo6941Wi();
    }

    public static C1037D get() {
        if (sInstance == null) {
            sInstance = new C1037D(C0967f.get().getApplicationContext());
        }
        return sInstance;
    }

    /* renamed from: Ea */
    public int mo6936Ea() {
        return this.f1638ob;
    }

    /* renamed from: T */
    public Drawable mo6937T(boolean z) {
        if (z) {
            return this.f1626OF;
        }
        return this.f1627PF;
    }

    /* renamed from: U */
    public Drawable mo6938U(boolean z) {
        return C1416U.m3563a(this.mContext, this.f1628QF, z ? this.f1635XF : this.f1638ob);
    }

    /* renamed from: V */
    public Drawable mo6939V(boolean z) {
        Drawable drawable;
        if (z) {
            drawable = this.f1632UF;
        } else {
            drawable = this.f1631TF;
        }
        return C1416U.m3563a(this.mContext, drawable, this.f1638ob);
    }

    /* renamed from: W */
    public Drawable mo6940W(boolean z) {
        if (z) {
            return C1416U.m3563a(this.mContext, this.f1630SF, this.f1638ob);
        }
        return this.f1629RF;
    }

    /* renamed from: Wi */
    public void mo6941Wi() {
        Resources resources = this.mContext.getResources();
        this.f1619GF = resources.getDrawable(R.drawable.msg_bubble_incoming_new);
        this.f1622JF = resources.getDrawable(R.drawable.message_bubble_incoming_no_arrow);
        this.f1621IF = resources.getDrawable(R.drawable.msg_bubble_error);
        this.f1620HF = resources.getDrawable(R.drawable.msg_bubble_outgoing_new);
        this.f1623KF = resources.getDrawable(R.drawable.message_bubble_outgoing_no_arrow);
        this.f1624MF = resources.getDrawable(R.drawable.ic_audio_play);
        this.f1625NF = resources.getDrawable(R.drawable.ic_audio_pause);
        this.f1626OF = resources.getDrawable(R.drawable.audio_progress_bar_background_incoming);
        this.f1627PF = resources.getDrawable(R.drawable.audio_progress_bar_background_outgoing);
        this.f1628QF = resources.getDrawable(R.drawable.audio_progress_bar_progress);
        this.f1629RF = resources.getDrawable(R.drawable.fastscroll_thumb);
        this.f1630SF = resources.getDrawable(R.drawable.fastscroll_thumb_pressed);
        this.f1631TF = resources.getDrawable(R.drawable.fastscroll_preview_left);
        this.f1632UF = resources.getDrawable(R.drawable.fastscroll_preview_right);
        this.f1633VF = resources.getColor(R.color.message_bubble_color_outgoing);
        this.f1634WF = resources.getColor(R.color.message_error_bubble_color_incoming);
        this.f1635XF = resources.getColor(R.color.message_audio_button_color_incoming);
        this.f1636YF = resources.getColor(R.color.message_bubble_color_selected);
        this.f1638ob = resources.getColor(R.color.primary_color);
        this.f1637ZF = resources.getColor(R.color.google_gray);
        this.mColors = resources.obtainTypedArray(R.array.letter_tile_colors);
    }

    /* renamed from: X */
    public Drawable mo6942X(boolean z) {
        return C1416U.m3563a(this.mContext, this.f1625NF, z ? this.f1635XF : this.f1638ob);
    }

    /* renamed from: Y */
    public Drawable mo6943Y(boolean z) {
        return C1416U.m3563a(this.mContext, this.f1624MF, z ? this.f1635XF : this.f1638ob);
    }

    /* renamed from: a */
    public Drawable mo6944a(boolean z, boolean z2, boolean z3, boolean z4, String str) {
        Drawable drawable;
        int i;
        if (z3) {
            if (z2) {
                drawable = (!z4 || z) ? this.f1619GF : this.f1621IF;
            } else {
                drawable = this.f1620HF;
            }
        } else if (z2) {
            drawable = this.f1622JF;
        } else {
            drawable = this.f1623KF;
        }
        if (z) {
            i = this.f1636YF;
        } else if (!z2) {
            i = this.f1633VF;
        } else if (z4) {
            i = this.f1634WF;
        } else if (str == null || !this.mContext.getResources().getBoolean(R.bool.contact_colors)) {
            i = this.f1637ZF;
        } else {
            i = this.mColors.getColor(Math.abs(str.hashCode()) % this.mColors.length(), this.f1638ob);
        }
        return C1416U.m3563a(this.mContext, drawable, i);
    }
}
