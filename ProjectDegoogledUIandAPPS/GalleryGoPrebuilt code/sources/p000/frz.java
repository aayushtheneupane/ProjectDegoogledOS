package p000;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.apps.photosgo.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

/* renamed from: frz */
/* compiled from: PG */
public final class frz {
    @Deprecated
    public frz() {
    }

    public /* synthetic */ frz(byte[] bArr) {
    }

    /* renamed from: a */
    public static boolean m9476a(File file) {
        int i = Build.VERSION.SDK_INT;
        return Files.isSymbolicLink(file.toPath());
    }

    /* renamed from: a */
    public static long m9473a(File[] fileArr) {
        long j = 0;
        try {
            int length = fileArr.length;
            int i = 0;
            while (i < length) {
                try {
                    File file = fileArr[i];
                    if (!m9476a(file)) {
                        if (file.isFile()) {
                            j += file.length();
                        } else if (!file.isDirectory()) {
                            flw.m9202d("DirStatsCapture", "not a link / dir / regular file: %s", file);
                        } else {
                            j += m9473a(file.listFiles());
                        }
                    }
                    i++;
                } catch (IOException | SecurityException e) {
                    e = e;
                    flw.m9198b("DirStatsCapture", "failure computing subtree size", e, new Object[0]);
                    return j;
                }
            }
        } catch (IOException | SecurityException e2) {
            e = e2;
            flw.m9198b("DirStatsCapture", "failure computing subtree size", e, new Object[0]);
            return j;
        }
        return j;
    }

    /* renamed from: a */
    public static String m9475a(InputStream inputStream, long j, int i) {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream.skip(j);
            if (i <= 0) {
                i = Integer.MAX_VALUE;
            }
            while (i > 0) {
                int read = inputStream.read(bArr, 0, Math.min(i, 1024));
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i -= read;
            }
            inputStream.close();
            try {
                return byteArrayOutputStream.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unsupported encoding UTF8. This should always be supported.", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Failed to read license or metadata text.", e2);
        }
    }

    /* renamed from: a */
    public static String m9474a(Context context, String str, long j, int i) {
        Resources resources = context.getApplicationContext().getResources();
        return m9475a(resources.openRawResource(resources.getIdentifier(str, "raw", resources.getResourcePackageName(R.id.dummy_placeholder))), j, i);
    }
}
