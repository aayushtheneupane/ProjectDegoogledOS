package com.android.messaging.datamodel.action;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.concurrent.TimeUnit;

public class ProcessDeliveryReportAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0833w();

    private ProcessDeliveryReportAction(Uri uri, int i) {
        this.f1057Oy.putParcelable("uri", uri);
        this.f1057Oy.putInt(NotificationCompat.CATEGORY_STATUS, i);
    }

    /* renamed from: a */
    public static void m1405a(Uri uri, int i) {
        new ProcessDeliveryReportAction(uri, i).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        Uri uri = (Uri) this.f1057Oy.getParcelable("uri");
        int i = this.f1057Oy.getInt(NotificationCompat.CATEGORY_STATUS);
        C0955p database = C0947h.get().getDatabase();
        if (ContentUris.parseId(uri) < 0) {
            C1430e.m3622e("MessagingAppDataModel", "ProcessDeliveryReportAction: can't find message");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (uri != null) {
            C1029y.m2433a(uri, i, currentTimeMillis);
        }
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_status", Integer.valueOf(C0802P.m1402c(true, 2, i)));
            contentValues.put("sent_timestamp", Long.valueOf(TimeUnit.MILLISECONDS.toMicros(currentTimeMillis)));
            MessageData a = C0887c.m1632a(database, uri);
            if (a != null) {
                C1424b.m3592ia(uri.equals(a.mo6253Wg()));
                C0887c.m1663c(database, a.getMessageId(), contentValues);
                MessagingContentProvider.m1273r(a.mo6250Ue());
            }
            database.setTransactionSuccessful();
            return null;
        } finally {
            database.endTransaction();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ProcessDeliveryReportAction(Parcel parcel, C0833w wVar) {
        super(parcel);
    }
}
