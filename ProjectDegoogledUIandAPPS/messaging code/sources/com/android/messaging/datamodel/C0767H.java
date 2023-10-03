package com.android.messaging.datamodel;

import android.content.Context;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import androidx.core.app.NotificationCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1452ha;
import com.android.messaging.util.C1481w;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.H */
public class C0767H extends C0769J {
    public C0767H(C0763D d) {
        super(d);
        C0764E e = (C0764E) d.f986Vx.get(0);
        String str = e.f987Ka;
        Context applicationContext = C0967f.get().getApplicationContext();
        C0765F f = (C0765F) e.f993ay.get(0);
        this.f1004ey = f.f997ey;
        this.f1005fy = f.f998fy;
        this.mContent = f.mText;
        if (this.f1004ey != null) {
            int i = R.string.notification_picture;
            if (C1481w.m3831za(this.f1005fy)) {
                i = R.string.notification_audio;
            } else if (C1481w.m3830Ea(this.f1005fy)) {
                i = R.string.notification_video;
            } else if (C1481w.m3829Da(this.f1005fy)) {
                i = R.string.notification_vcard;
            }
            String string = applicationContext.getString(i);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (!TextUtils.isEmpty(this.mContent)) {
                spannableStringBuilder.append(this.mContent).append(System.getProperty("line.separator"));
            }
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(string);
            spannableStringBuilder.setSpan(new StyleSpan(2), length, spannableStringBuilder.length(), 33);
            this.mContent = spannableStringBuilder;
        }
        if (e.f988Wx) {
            CharSequence charSequence = this.mContent;
            this.f1007ty = charSequence;
            String str2 = f.f999gy;
            this.f1006sy = str2;
            this.mContent = C0944e.m2091b(str2, charSequence, this.f1004ey, this.f1005fy);
            this.mTitle = e.f989Xx;
            return;
        }
        this.mTitle = ((C0765F) e.f993ay.get(0)).f999gy;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public NotificationCompat.Style mo5878a(NotificationCompat.Builder builder) {
        NotificationCompat.Style style;
        CharSequence charSequence;
        CharSequence charSequence2;
        builder.setContentTitle(this.mTitle).setTicker(mo5882ne());
        C0764E e = (C0764E) this.f1008uy.f986Vx.get(0);
        List list = e.f993ay;
        int size = list.size();
        builder.setContentText(this.mContent);
        boolean z = true;
        if (size == 1) {
            if (!C1481w.isImageType(this.f1005fy) && (!C1481w.m3830Ea(this.f1005fy) || !C1452ha.m3728Qj())) {
                z = false;
            }
            if (this.f1004ey == null || !z) {
                style = new NotificationCompat.BigTextStyle(builder).bigText(this.mContent);
            } else {
                String str = ((C0765F) list.get(0)).f1000hy;
                CharSequence c = C0944e.m2094c(str, this.f1005fy);
                if (!e.f988Wx) {
                    charSequence2 = C0944e.m2094c((String) null, this.f1005fy);
                    str = null;
                } else {
                    charSequence2 = c;
                }
                builder.setContentText(charSequence2);
                builder.setTicker(c);
                style = new NotificationCompat.BigPictureStyle(builder).setSummaryText(C0944e.m2093c(str, (CharSequence) null, (Uri) null, (String) null));
            }
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                C0765F f = (C0765F) list.get(size2);
                this.f1004ey = f.f997ey;
                this.f1005fy = f.f998fy;
                CharSequence charSequence3 = f.mText;
                if (!TextUtils.isEmpty(charSequence3) || this.f1004ey != null) {
                    if (e.f988Wx) {
                        charSequence = C0944e.m2091b(f.f999gy, charSequence3, this.f1004ey, this.f1005fy);
                    } else {
                        charSequence = C0944e.m2091b((String) null, charSequence3, this.f1004ey, this.f1005fy);
                    }
                    spannableStringBuilder.append(charSequence);
                    if (size2 > 0) {
                        spannableStringBuilder.append(10);
                    }
                }
            }
            style = new NotificationCompat.BigTextStyle(builder).bigText(spannableStringBuilder);
        }
        builder.setWhen(e.f991Zx);
        return style;
    }
}
