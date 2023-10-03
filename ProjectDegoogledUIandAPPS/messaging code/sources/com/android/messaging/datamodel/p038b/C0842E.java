package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.datamodel.data.MessagePartData;

/* renamed from: com.android.messaging.datamodel.b.E */
public class C0842E extends C0841D {
    public C0842E(MessagePartData messagePartData) {
        super(messagePartData, -1, -1, false);
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        return new C0859W(context, this);
    }

    public C0842E(Uri uri) {
        super((String) null, uri, -1, -1, -1, -1, false);
    }
}
