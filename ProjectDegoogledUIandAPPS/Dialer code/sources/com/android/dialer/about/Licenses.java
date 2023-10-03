package com.android.dialer.about;

import android.content.Context;
import android.content.res.Resources;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public final class Licenses {
    public static String getLicenseText(Context context, License license) {
        return getTextFromResource(context, "third_party_licenses", license.getLicenseOffset(), license.getLicenseLength());
    }

    public static ArrayList<License> getLicenses(Context context) {
        String[] split = getTextFromResource(context.getApplicationContext(), "third_party_license_metadata", 0, -1).split("\n");
        ArrayList<License> arrayList = new ArrayList<>(split.length);
        for (String str : split) {
            int indexOf = str.indexOf(32);
            String[] split2 = str.substring(0, indexOf).split(":");
            Assert.checkState(indexOf > 0 && split2.length == 2, GeneratedOutlineSupport.outline8("Invalid license meta-data line:\n", str), new Object[0]);
            arrayList.add(License.create(str.substring(indexOf + 1), Long.parseLong(split2[0]), Integer.parseInt(split2[1])));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static String getTextFromResource(Context context, String str, long j, int i) {
        Resources resources = context.getApplicationContext().getResources();
        InputStream openRawResource = resources.openRawResource(resources.getIdentifier(str, "raw", resources.getResourcePackageName(R.id.license)));
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            openRawResource.skip(j);
            if (i <= 0) {
                i = Integer.MAX_VALUE;
            }
            while (i > 0) {
                int read = openRawResource.read(bArr, 0, Math.min(i, bArr.length));
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i -= read;
            }
            openRawResource.close();
            try {
                return byteArrayOutputStream.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unsupported encoding UTF8. This should always be supported.", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Failed to read license or metadata text.", e2);
        }
    }
}
