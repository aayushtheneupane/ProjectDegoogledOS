package com.android.messaging.datamodel;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import com.android.messaging.datamodel.data.C0890B;
import com.android.messaging.datamodel.data.MessagePartData;
import com.google.common.base.C1504A;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.z */
public class C0965z extends C0837b {
    private static final String SELECTION;

    /* renamed from: ic */
    private static final Uri f1379ic = MediaStore.Files.getContentUri("external");

    static {
        String[] strArr = MessagePartData.f1178UB;
        StringBuilder Pa = C0632a.m1011Pa("mime_type IN ('");
        Pa.append(C1504A.m3943Ua("','").mo8516c(strArr));
        Pa.append("') AND ");
        Pa.append("media_type");
        Pa.append(" IN (");
        Pa.append(C1504A.m3945e(',').mo8516c(new Integer[]{1, 3, 2}));
        Pa.append(")");
        SELECTION = Pa.toString();
    }

    public C0965z(String str, Context context) {
        super(str, context, f1379ic, C0890B.f1149LB, SELECTION, (String[]) null, "date_modified DESC");
    }
}
