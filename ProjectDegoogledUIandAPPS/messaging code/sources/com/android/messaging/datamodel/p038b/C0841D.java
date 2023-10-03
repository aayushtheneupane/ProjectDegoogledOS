package com.android.messaging.datamodel.p038b;

import android.net.Uri;
import com.android.messaging.datamodel.action.UpdateMessagePartSizeAction;
import com.android.messaging.datamodel.data.MessagePartData;

/* renamed from: com.android.messaging.datamodel.b.D */
public class C0841D extends C0849L {

    /* renamed from: IC */
    private final String f1094IC;

    public C0841D(MessagePartData messagePartData, int i, int i2, boolean z) {
        this(messagePartData.mo6298ch(), messagePartData.getContentUri(), i, i2, messagePartData.getWidth(), messagePartData.getHeight(), z);
    }

    /* renamed from: u */
    public void mo6083u(int i, int i2) {
        String str = this.f1094IC;
        if (str != null && i != -1 && i2 != -1 && i != this.f1129zC && i2 != this.f1124AC) {
            UpdateMessagePartSizeAction.m1462a(str, i, i2);
        }
    }

    protected C0841D(String str, Uri uri, int i, int i2, int i3, int i4, boolean z) {
        super(uri, i, i2, i3, i4, true, z, false, 0, 0);
        this.f1094IC = str;
    }
}
