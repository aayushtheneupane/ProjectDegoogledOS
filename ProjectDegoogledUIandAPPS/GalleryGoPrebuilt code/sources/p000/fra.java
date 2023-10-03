package p000;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: fra */
/* compiled from: PG */
public final class fra {

    /* renamed from: a */
    public static /* synthetic */ int f10302a;

    /* renamed from: b */
    private static final String[] f10303b = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};

    /* renamed from: c */
    private static final String[] f10304c;

    static {
        String[] strArr = new String[4];
        strArr[0] = "media";
        int i = Build.VERSION.SDK_INT;
        String str = "";
        strArr[1] = str;
        int i2 = Build.VERSION.SDK_INT;
        strArr[2] = str;
        if (Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("ranchu")) {
            str = "com.google.android.apps.common.testing.services.storage.runfiles";
        }
        strArr[3] = str;
        f10304c = strArr;
    }

    /* renamed from: a */
    private static void m9446a(Exception exc, Exception exc2) {
        int i = Build.VERSION.SDK_INT;
        ifn.m12935a((Throwable) exc, (Throwable) exc2);
    }

    /* renamed from: a */
    private static Object m9442a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new FileNotFoundException("Content resolver returned null value.");
    }

    /* renamed from: a */
    public static void m9444a(AssetFileDescriptor assetFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            assetFileDescriptor.close();
        } catch (IOException e) {
            m9446a((Exception) fileNotFoundException, (Exception) e);
        }
    }

    /* renamed from: a */
    private static void m9445a(ParcelFileDescriptor parcelFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            m9446a((Exception) fileNotFoundException, (Exception) e);
        }
    }

    /* renamed from: a */
    private static File[] m9448a(Callable callable) {
        try {
            return (File[]) callable.call();
        } catch (NullPointerException e) {
            int i = Build.VERSION.SDK_INT;
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    /* renamed from: a */
    private static Uri m9439a(Uri uri) {
        return Uri.parse(uri.toString());
    }

    /* renamed from: a */
    private static boolean m9447a(Context context, Uri uri, int i, fqz fqz) {
        String authority = uri.getAuthority();
        int indexOf = authority.indexOf(64);
        if (indexOf >= 0) {
            authority = authority.substring(indexOf + 1);
        }
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
        if (resolveContentProvider == null) {
            List<ProviderInfo> queryContentProviders = context.getPackageManager().queryContentProviders(context.getPackageName(), Process.myUid(), 0);
            if (queryContentProviders != null) {
                for (ProviderInfo providerInfo : queryContentProviders) {
                    if (authority.equals(providerInfo.authority)) {
                        return fqz.f10298b;
                    }
                }
            }
            return true;
        } else if (context.getPackageName().equals(resolveContentProvider.packageName)) {
            return fqz.f10298b;
        } else {
            if (fqz.f10298b) {
                return false;
            }
            if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), i) != 0) {
                for (String equals : f10304c) {
                    if (equals.equals(authority)) {
                        return true;
                    }
                }
                if (resolveContentProvider.exported) {
                    for (String str : f10303b) {
                        if (str.charAt(str.length() - 1) == '.') {
                            if (resolveContentProvider.packageName.startsWith(str)) {
                                return false;
                            }
                        } else if (resolveContentProvider.packageName.equals(str)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0040 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m9436a(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 114(0x72, float:1.6E-43)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x0027
            r1 = 119(0x77, float:1.67E-43)
            if (r0 == r1) goto L_0x001d
            r1 = 3653(0xe45, float:5.119E-42)
            if (r0 == r1) goto L_0x0013
            goto L_0x0031
        L_0x0013:
            java.lang.String r0 = "rw"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0031
            r4 = 2
            goto L_0x0032
        L_0x001d:
            java.lang.String r0 = "w"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0031
            r4 = 1
            goto L_0x0032
        L_0x0027:
            java.lang.String r0 = "r"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0031
            r4 = 0
            goto L_0x0032
        L_0x0031:
            r4 = -1
        L_0x0032:
            if (r4 == 0) goto L_0x0040
            if (r4 == r3) goto L_0x003f
            if (r4 != r2) goto L_0x0039
            goto L_0x003f
        L_0x0039:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>()
            throw r4
        L_0x003f:
            return r2
        L_0x0040:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fra.m9436a(java.lang.String):int");
    }

    /* renamed from: a */
    public static AssetFileDescriptor m9437a(Context context, Uri uri, String str) {
        return m9438a(context, uri, str, fqz.f10297a);
    }

    /* renamed from: a */
    public static AssetFileDescriptor m9438a(Context context, Uri uri, String str, fqz fqz) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri a = m9439a(uri);
        String scheme = a.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openAssetFileDescriptor(a, str);
        }
        if ("content".equals(scheme)) {
            if (m9447a(context, a, m9436a(str), fqz)) {
                return (AssetFileDescriptor) m9442a((Object) contentResolver.openAssetFileDescriptor(a, str));
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            AssetFileDescriptor assetFileDescriptor = (AssetFileDescriptor) m9442a((Object) contentResolver.openAssetFileDescriptor(a, str));
            try {
                m9443a(context, assetFileDescriptor.getParcelFileDescriptor(), a, fqz);
                return assetFileDescriptor;
            } catch (FileNotFoundException e) {
                m9444a(assetFileDescriptor, e);
                throw e;
            } catch (IOException e2) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e2);
                m9444a(assetFileDescriptor, fileNotFoundException);
                throw fileNotFoundException;
            }
        } else {
            throw new FileNotFoundException("Not implemented. Contact tiktok-users@");
        }
    }

    @Deprecated
    /* renamed from: b */
    public static ParcelFileDescriptor m9449b(Context context, Uri uri, String str) {
        fqz fqz = fqz.f10297a;
        ContentResolver contentResolver = context.getContentResolver();
        Uri a = m9439a(uri);
        String scheme = a.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openFileDescriptor(a, str);
        }
        if ("content".equals(scheme)) {
            if (m9447a(context, a, m9436a(str), fqz)) {
                return (ParcelFileDescriptor) m9442a((Object) contentResolver.openFileDescriptor(a, str));
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(a, str);
            try {
                m9443a(context, openFileDescriptor, a, fqz);
                return openFileDescriptor;
            } catch (FileNotFoundException e) {
                m9445a(openFileDescriptor, e);
                throw e;
            } catch (IOException e2) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e2);
                m9445a(openFileDescriptor, fileNotFoundException);
                throw fileNotFoundException;
            }
        } else {
            throw new FileNotFoundException("Not implemented. Contact tiktok-users@");
        }
    }

    /* renamed from: a */
    public static InputStream m9440a(Context context, Uri uri) {
        return m9441a(context, uri, fqz.f10297a);
    }

    /* renamed from: a */
    public static InputStream m9441a(Context context, Uri uri, fqz fqz) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri a = m9439a(uri);
        String scheme = a.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openInputStream(a);
        }
        if ("content".equals(scheme)) {
            if (m9447a(context, a, 1, fqz)) {
                return (InputStream) m9442a((Object) contentResolver.openInputStream(a));
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(Uri.fromFile(new File(a.getPath()).getCanonicalFile()), "r");
                try {
                    m9443a(context, openFileDescriptor, a, fqz);
                    return new ParcelFileDescriptor.AutoCloseInputStream(openFileDescriptor);
                } catch (FileNotFoundException e) {
                    m9445a(openFileDescriptor, e);
                    throw e;
                } catch (IOException e2) {
                    FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                    fileNotFoundException.initCause(e2);
                    m9445a(openFileDescriptor, fileNotFoundException);
                    throw fileNotFoundException;
                }
            } catch (IOException e3) {
                FileNotFoundException fileNotFoundException2 = new FileNotFoundException("Canonicalization failed.");
                fileNotFoundException2.initCause(e3);
                throw fileNotFoundException2;
            }
        } else {
            throw new FileNotFoundException("Not implemented. Contact tiktok-users@");
        }
    }

    /* renamed from: a */
    private static void m9443a(Context context, ParcelFileDescriptor parcelFileDescriptor, Uri uri, fqz fqz) {
        File a;
        String canonicalPath = new File(uri.getPath()).getCanonicalPath();
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        int i = Build.VERSION.SDK_INT;
        StructStat structStat = (StructStat) frf.m9451a(new frd(fileDescriptor));
        frf frf = new frf(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
        int i2 = Build.VERSION.SDK_INT;
        StructStat structStat2 = (StructStat) frf.m9451a(new fre(canonicalPath));
        frf frf2 = new frf(structStat2.st_dev, structStat2.st_ino, OsConstants.S_ISLNK(structStat2.st_mode));
        if (frf2.f10311c) {
            String valueOf = String.valueOf(canonicalPath);
            throw new FileNotFoundException(valueOf.length() == 0 ? new String("Can't open file: ") : "Can't open file: ".concat(valueOf));
        } else if (frf.f10309a == frf2.f10309a && frf.f10310b == frf2.f10310b) {
            if (!canonicalPath.startsWith("/proc/") && !canonicalPath.startsWith("/data/misc/")) {
                File a2 = C0071co.m4662a(context);
                boolean z = false;
                if (a2 != null ? !canonicalPath.startsWith(a2.getCanonicalPath()) : !canonicalPath.startsWith(Environment.getDataDirectory().getCanonicalPath())) {
                    Context c = C0071co.m4672c(context);
                    if (c == null || (a = C0071co.m4662a(c)) == null || !canonicalPath.startsWith(a.getCanonicalPath())) {
                        int i3 = Build.VERSION.SDK_INT;
                        File[] a3 = m9448a((Callable) new fqx(context));
                        int length = a3.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 < length) {
                                File file = a3[i4];
                                if (file != null && canonicalPath.startsWith(file.getCanonicalPath())) {
                                    break;
                                }
                                i4++;
                            } else {
                                File[] a4 = m9448a((Callable) new fqy(context));
                                int length2 = a4.length;
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= length2) {
                                        break;
                                    }
                                    File file2 = a4[i5];
                                    if (file2 != null && canonicalPath.startsWith(file2.getCanonicalPath())) {
                                        break;
                                    }
                                    i5++;
                                }
                            }
                        }
                    }
                }
                z = true;
                if (z == fqz.f10298b) {
                    return;
                }
            }
            String valueOf2 = String.valueOf(canonicalPath);
            throw new FileNotFoundException(valueOf2.length() == 0 ? new String("Can't open file: ") : "Can't open file: ".concat(valueOf2));
        } else {
            String valueOf3 = String.valueOf(canonicalPath);
            throw new FileNotFoundException(valueOf3.length() == 0 ? new String("Can't open file: ") : "Can't open file: ".concat(valueOf3));
        }
    }
}
