package com.android.messaging.datamodel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.vcard.VCardConfig;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public abstract class FileProvider extends ContentProvider {

    /* renamed from: xb */
    private static final Random f1003xb = new Random();

    /* renamed from: a */
    protected static boolean m1234a(File file) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                return file.createNewFile();
            }
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    /* renamed from: j */
    protected static boolean m1235j(String str) {
        for (int startsWith = str.startsWith("/"); startsWith < str.length(); startsWith++) {
            if (!Character.isDigit(Character.valueOf(str.charAt(startsWith)).charValue())) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract File mo5870b(String str, String str2);

    public int delete(Uri uri, String str, String[] strArr) {
        String path = uri.getPath();
        if (m1235j(path)) {
            return mo5870b(path, uri.getQueryParameter("ext")).delete() ? 1 : 0;
        }
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        String path = uri.getPath();
        if (!m1235j(path)) {
            return null;
        }
        return ParcelFileDescriptor.open(mo5870b(path, uri.getQueryParameter("ext")), TextUtils.equals(str, "r") ? VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES : 603979776);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* renamed from: a */
    protected static Uri m1233a(String str, String str2) {
        long abs = Math.abs(f1003xb.nextLong());
        Uri.Builder scheme = new Uri.Builder().authority(str).scheme("content");
        scheme.appendPath(String.valueOf(abs));
        if (!TextUtils.isEmpty(str2)) {
            scheme.appendQueryParameter("ext", str2);
        }
        return scheme.build();
    }
}
