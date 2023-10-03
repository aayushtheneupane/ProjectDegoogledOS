package com.google.android.apps.photosgo.sharing.compression;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/* compiled from: PG */
public final class ImageCompressionProvider extends ContentProvider {

    /* renamed from: a */
    public static final Bitmap.CompressFormat f4923a = Bitmap.CompressFormat.JPEG;

    /* renamed from: b */
    private static final C0292kp f4924b = new C0292kp((Collection) hso.m12038a("mime_type", "height", "width", "_size", "_display_name", "media_type"));

    public final String getType(Uri uri) {
        return "image/jpeg";
    }

    public final boolean onCreate() {
        return false;
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Delete not supported");
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert not supported");
    }

    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        InputStream a;
        edu edu = (edu) hgh.m11442a(getContext(), edu.class);
        iel cn = edu.mo2314cn();
        ebi co = edu.mo2315co();
        Uri b = cyc.m5647b(cxh.IMAGE, ContentUris.parseId(uri));
        try {
            ParcelFileDescriptor[] createReliablePipe = ParcelFileDescriptor.createReliablePipe();
            try {
                a = co.mo4664a(b);
                cn.execute(hmq.m11748a((Runnable) new edt(co, b, BitmapFactory.decodeStream(a), new ParcelFileDescriptor.AutoCloseOutputStream(createReliablePipe[1]))));
                if (a != null) {
                    a.close();
                }
                return createReliablePipe[0];
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new FileNotFoundException(valueOf.length() == 0 ? new String("Error opening input stream: ") : "Error opening input stream: ".concat(valueOf));
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
            throw th;
        } catch (IOException e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            throw new FileNotFoundException(valueOf2.length() == 0 ? new String("Error Creating Pipe: ") : "Error Creating Pipe: ".concat(valueOf2));
        }
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                if (f4924b.contains(strArr[i])) {
                    i++;
                } else {
                    throw new IllegalArgumentException("Query with projection given not permitted.");
                }
            }
            return getContext().getContentResolver().query(MediaStore.Files.getContentUri("external", ContentUris.parseId(uri)), strArr, str, strArr2, str2);
        }
        throw new IllegalArgumentException("Query of all columns not permitted.");
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Update not supported");
    }
}
