package com.android.messaging.datamodel;

import android.content.res.Resources;
import androidx.core.app.NotificationCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.google.common.collect.C1664ia;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.G */
public class C0766G extends C0769J {
    public final List mChildren = new ArrayList();

    public C0766G(C0763D d, C0769J j) {
        super(d);
        this.f1004ey = null;
        this.f1005fy = null;
        this.f1006sy = j.mTitle;
        Resources resources = C0967f.get().getApplicationContext().getResources();
        int i = d.f985Ux;
        this.mTitle = resources.getQuantityString(R.plurals.notification_new_messages, i, new Object[]{Integer.valueOf(i)});
        this.f1007ty = j.mContent;
        for (int i2 = 0; i2 < d.f986Vx.size(); i2++) {
            C0764E e = (C0764E) d.f986Vx.get(i2);
            if (e.f993ay.get(0) instanceof C0765F) {
                String str = e.f987Ka;
                this.mChildren.add(new C0762C(new C0763D(e.f994by, C1664ia.newArrayList(e)), i2));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public NotificationCompat.Style mo5878a(NotificationCompat.Builder builder) {
        String str;
        builder.setContentTitle(this.mTitle);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
        String string = C0967f.get().getApplicationContext().getString(R.string.enumeration_comma);
        StringBuilder sb = new StringBuilder();
        long j = 0;
        for (int i = 0; i < this.f1008uy.f986Vx.size(); i++) {
            C0764E e = (C0764E) this.f1008uy.f986Vx.get(i);
            long j2 = e.f991Zx;
            if (j2 > j) {
                j = j2;
            }
            C0765F f = (C0765F) e.f993ay.get(0);
            if (!e.f988Wx) {
                str = f.f999gy;
            } else if (e.f989Xx.length() > 30) {
                String str2 = e.f989Xx;
                int i2 = 30;
                while (true) {
                    if (i2 < 0) {
                        i2 = 30;
                        break;
                    } else if (str2.charAt(i2) == ',') {
                        break;
                    } else {
                        i2--;
                    }
                }
                str = str2.substring(0, i2) + 8230;
            } else {
                str = e.f989Xx;
            }
            CharSequence charSequence = f.mText;
            this.f1004ey = f.f997ey;
            this.f1005fy = f.f998fy;
            inboxStyle.addLine(C0944e.m2093c(str, charSequence, this.f1004ey, this.f1005fy));
            if (str != null) {
                if (sb.length() > 0) {
                    sb.append(string);
                }
                sb.append(str);
            }
        }
        this.mContent = sb;
        builder.setContentText(sb).setTicker(mo5882ne()).setWhen(j);
        return inboxStyle;
    }

    public int getIcon() {
        return R.drawable.ic_sms_multi_light;
    }
}
