package p000;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.google.android.gms.common.api.Status;
import java.io.InputStream;
import java.util.List;

/* renamed from: xy */
/* compiled from: PG */
public class C0652xy {
    /* renamed from: a */
    public static /* synthetic */ String m16064a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "null" : "MEMORY_CACHE" : "RESOURCE_DISK_CACHE" : "DATA_DISK_CACHE" : "REMOTE" : "LOCAL";
    }

    /* renamed from: a */
    public void mo4639a(RecyclerView recyclerView, int i) {
    }

    /* renamed from: a */
    public void mo4654a(RecyclerView recyclerView, int i, int i2) {
    }

    /* renamed from: a */
    public static void m16067a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: a */
    public static String m16065a(Context context, Uri uri, String str) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{str}, (String) null, (String[]) null, (String) null);
            try {
                if (cursor.moveToFirst() && !cursor.isNull(0)) {
                    String string = cursor.getString(0);
                    m16067a((AutoCloseable) cursor);
                    return string;
                }
            } catch (Exception e) {
                e = e;
                try {
                    Log.w("DocumentFile", "Failed query: " + e);
                    m16067a((AutoCloseable) cursor);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                }
            }
        } catch (Exception e2) {
            e = e2;
            cursor = null;
            Log.w("DocumentFile", "Failed query: " + e);
            m16067a((AutoCloseable) cursor);
            return null;
        } catch (Throwable th2) {
            th = th2;
            m16067a((AutoCloseable) cursor2);
            throw th;
        }
        m16067a((AutoCloseable) cursor);
        return null;
    }

    /* renamed from: b */
    public static int m16069b(List list, InputStream inputStream, aui aui) {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new bap(inputStream, aui);
        }
        inputStream.mark(5242880);
        return m16059a(list, (aqs) new aqq(inputStream, aui));
    }

    /* renamed from: a */
    public static int m16059a(List list, aqs aqs) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int a = aqs.mo1493a((aqm) list.get(i));
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static ImageHeaderParser$ImageType m16061a(List list, InputStream inputStream, aui aui) {
        if (inputStream == null) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new bap(inputStream, aui);
        }
        inputStream.mark(5242880);
        return m16060a(list, (aqt) new aqn(inputStream));
    }

    /* renamed from: a */
    public static ImageHeaderParser$ImageType m16060a(List list, aqt aqt) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser$ImageType a = aqt.mo1492a((aqm) list.get(i));
            if (a != ImageHeaderParser$ImageType.UNKNOWN) {
                return a;
            }
        }
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    /* renamed from: a */
    public static eko m16062a(Status status) {
        if (status.f4979h != null) {
            return new ekz(status);
        }
        return new eko(status);
    }

    /* renamed from: a */
    public static boolean m16068a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static eqo m16063a(Object obj) {
        return new eqo(obj);
    }

    /* renamed from: a */
    public static void m16066a(ekx ekx) {
        ekx.mo3506a((ekw) new eqp(ekx, new exe()));
    }
}
