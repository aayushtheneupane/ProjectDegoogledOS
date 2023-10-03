package com.android.p032ex.photo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* renamed from: com.android.ex.photo.d */
public class C0720d {

    /* renamed from: Bo */
    private String f846Bo;

    /* renamed from: Co */
    private String f847Co;

    /* renamed from: Mv */
    private Integer f848Mv;

    /* renamed from: Nv */
    private String f849Nv;

    /* renamed from: Ov */
    private Float f850Ov;

    /* renamed from: Pv */
    private boolean f851Pv = true;

    /* renamed from: Qv */
    private boolean f852Qv;

    /* renamed from: Rv */
    private boolean f853Rv = false;

    /* renamed from: Sv */
    private int f854Sv;

    /* renamed from: Tv */
    private int f855Tv;

    /* renamed from: Uv */
    private int f856Uv;

    /* renamed from: Vv */
    private int f857Vv;

    /* renamed from: Wv */
    private boolean f858Wv = false;

    /* renamed from: Xv */
    private boolean f859Xv = false;

    /* renamed from: jq */
    private String f860jq;
    private String mContentDescription;
    private final Intent mIntent;
    private String[] mProjection;

    /* synthetic */ C0720d(Context context, Class cls, C0718c cVar) {
        this.mIntent = new Intent(context, cls);
    }

    /* renamed from: C */
    public C0720d mo5728C(String str) {
        this.mContentDescription = str;
        return this;
    }

    /* renamed from: D */
    public C0720d mo5729D(String str) {
        this.f849Nv = str;
        return this;
    }

    /* renamed from: E */
    public C0720d mo5730E(String str) {
        this.f860jq = str;
        return this;
    }

    /* renamed from: F */
    public C0720d mo5731F(String str) {
        this.f846Bo = str;
        return this;
    }

    /* renamed from: G */
    public C0720d mo5732G(String str) {
        this.f847Co = str;
        return this;
    }

    /* renamed from: b */
    public C0720d mo5734b(float f) {
        this.f850Ov = Float.valueOf(f);
        return this;
    }

    public Intent build() {
        this.mIntent.setAction("android.intent.action.VIEW");
        this.mIntent.setFlags(67633152);
        Integer num = this.f848Mv;
        if (num != null) {
            this.mIntent.putExtra("photo_index", num.intValue());
        }
        String str = this.f849Nv;
        if (str != null) {
            this.mIntent.putExtra("initial_photo_uri", str);
        }
        if (this.f849Nv == null || this.f848Mv == null) {
            String str2 = this.f860jq;
            if (str2 != null) {
                this.mIntent.putExtra("photos_uri", str2);
                this.mIntent.setData(Uri.parse(this.f860jq));
            }
            String str3 = this.f846Bo;
            if (str3 != null) {
                this.mIntent.putExtra("resolved_photo_uri", str3);
            }
            String[] strArr = this.mProjection;
            if (strArr != null) {
                this.mIntent.putExtra("projection", strArr);
            }
            String str4 = this.f847Co;
            if (str4 != null) {
                this.mIntent.putExtra("thumbnail_uri", str4);
            }
            String str5 = this.mContentDescription;
            if (str5 != null) {
                this.mIntent.putExtra("content_description", str5);
            }
            Float f = this.f850Ov;
            if (f != null) {
                this.mIntent.putExtra("max_scale", f);
            }
            this.mIntent.putExtra("watch_network", this.f852Qv);
            this.mIntent.putExtra("scale_up_animation", this.f853Rv);
            if (this.f853Rv) {
                this.mIntent.putExtra("start_x_extra", this.f854Sv);
                this.mIntent.putExtra("start_y_extra", this.f855Tv);
                this.mIntent.putExtra("start_width_extra", this.f856Uv);
                this.mIntent.putExtra("start_height_extra", this.f857Vv);
            }
            this.mIntent.putExtra("action_bar_hidden_initially", this.f858Wv);
            this.mIntent.putExtra("display_thumbs_fullscreen", this.f859Xv);
            this.mIntent.putExtra("enable_timer_lights_out", this.f851Pv);
            return this.mIntent;
        }
        throw new IllegalStateException("specified both photo index and photo uri");
    }

    public C0720d setProjection(String[] strArr) {
        this.mProjection = strArr;
        return this;
    }

    /* renamed from: G */
    public C0720d mo5733G(boolean z) {
        this.f859Xv = z;
        return this;
    }

    /* renamed from: b */
    public C0720d mo5735b(int i, int i2, int i3, int i4) {
        this.f853Rv = true;
        this.f854Sv = i;
        this.f855Tv = i2;
        this.f856Uv = i3;
        this.f857Vv = i4;
        return this;
    }
}
