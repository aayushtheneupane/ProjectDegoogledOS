package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.android.messaging.C0970i;
import com.android.messaging.R;
import com.android.messaging.datamodel.p038b.C0862b;
import com.android.messaging.datamodel.p038b.C0864d;
import com.android.messaging.datamodel.p038b.C0880t;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;

/* renamed from: com.android.messaging.ui.ContactIconView */
public class ContactIconView extends AsyncImageView {

    /* renamed from: Qj */
    private final int f1612Qj;
    /* access modifiers changed from: private */

    /* renamed from: Rj */
    public long f1613Rj;
    /* access modifiers changed from: private */

    /* renamed from: Sj */
    public String f1614Sj;

    /* renamed from: Tj */
    private boolean f1615Tj;
    /* access modifiers changed from: private */
    public Uri mAvatarUri;
    protected final int mIconSize;
    /* access modifiers changed from: private */
    public String mNormalizedDestination;

    public ContactIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.ContactIconView);
        int i = obtainStyledAttributes.getInt(0, 0);
        if (i == 0) {
            this.mIconSize = (int) resources.getDimension(R.dimen.contact_icon_view_normal_size);
        } else if (i == 1) {
            this.mIconSize = (int) resources.getDimension(R.dimen.contact_icon_view_large_size);
        } else if (i != 2) {
            this.mIconSize = 0;
            C1424b.fail("Unsupported ContactIconView icon size attribute");
        } else {
            this.mIconSize = (int) resources.getDimension(R.dimen.contact_icon_view_small_size);
        }
        this.f1612Qj = resources.getColor(R.color.contact_avatar_pressed_color);
        mo6859a((C0881u) null);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Zb */
    public void mo6928Zb() {
        if ((this.f1613Rj <= -1 || TextUtils.isEmpty(this.f1614Sj)) && TextUtils.isEmpty(this.mNormalizedDestination)) {
            setOnClickListener((View.OnClickListener) null);
        } else if (!this.f1615Tj) {
            setOnClickListener(new C1035C(this));
        }
    }

    /* renamed from: f */
    public void mo6930f(Uri uri) {
        mo6929a(uri, 0, (String) null, (String) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            setColorFilter(this.f1612Qj);
        } else {
            clearColorFilter();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setImageClickHandlerDisabled(boolean z) {
        this.f1615Tj = z;
        setOnClickListener((View.OnClickListener) null);
        setClickable(false);
    }

    /* renamed from: a */
    public void mo6929a(Uri uri, long j, String str, String str2) {
        if (uri == null) {
            mo6858a((C0880t) null);
        } else if ("g".equals(C1426c.m3603p(uri))) {
            int i = this.mIconSize;
            mo6858a((C0880t) new C0862b(uri, i, i));
        } else {
            int i2 = this.mIconSize;
            mo6858a((C0880t) new C0864d(uri, i2, i2, true));
        }
        this.f1613Rj = j;
        this.f1614Sj = str;
        this.mNormalizedDestination = str2;
        this.mAvatarUri = uri;
        mo6928Zb();
    }
}
