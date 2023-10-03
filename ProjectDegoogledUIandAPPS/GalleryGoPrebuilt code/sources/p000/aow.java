package p000;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.GeneratedAppGlideModule;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: aow */
/* compiled from: PG */
public final class aow implements ComponentCallbacks2 {

    /* renamed from: h */
    private static volatile aow f1286h;

    /* renamed from: i */
    private static volatile boolean f1287i;

    /* renamed from: a */
    public final atj f1288a;

    /* renamed from: b */
    public final auk f1289b;

    /* renamed from: c */
    public final apa f1290c;

    /* renamed from: d */
    public final aph f1291d;

    /* renamed from: e */
    public final aui f1292e;

    /* renamed from: f */
    public final bdc f1293f;

    /* renamed from: g */
    public final List f1294g = new ArrayList();

    /* renamed from: j */
    private final avo f1295j;

    private aow(Context context, atj atj, avo avo, auk auk, aui aui, bdc bdc, aov aov, Map map, List list) {
        Context context2 = context;
        auk auk2 = auk;
        aui aui2 = aui;
        Class<byte[]> cls = byte[].class;
        this.f1288a = atj;
        this.f1289b = auk2;
        this.f1292e = aui2;
        this.f1295j = avo;
        this.f1293f = bdc;
        Resources resources = context.getResources();
        aph aph = new aph();
        this.f1291d = aph;
        aph.mo1405a((aqm) new azu());
        if (Build.VERSION.SDK_INT >= 27) {
            this.f1291d.mo1405a((aqm) new bag());
        }
        List a = this.f1291d.mo1403a();
        bbq bbq = new bbq(context2, a, auk2, aui2);
        bbf bbf = new bbf(auk2, new bbe());
        bac bac = new bac(this.f1291d.mo1403a(), resources.getDisplayMetrics(), auk2, aui2);
        azm azm = new azm(bac);
        bau bau = new bau(bac, aui2);
        bbm bbm = new bbm(context2);
        aya aya = new aya(resources);
        ayb ayb = new ayb(resources);
        axz axz = new axz(resources);
        Class<byte[]> cls2 = cls;
        axy axy = new axy(resources);
        azj azj = new azj(aui2);
        axy axy2 = axy;
        bce bce = new bce();
        bch bch = new bch();
        ContentResolver contentResolver = context.getContentResolver();
        aph aph2 = this.f1291d;
        ayb ayb2 = ayb;
        axz axz2 = axz;
        aph2.mo1407a(ByteBuffer.class, (aqk) new awl());
        aph2.mo1407a(InputStream.class, (aqk) new ayd(aui2));
        aph2.mo1412a("Bitmap", ByteBuffer.class, Bitmap.class, azm);
        aph2.mo1412a("Bitmap", InputStream.class, Bitmap.class, bau);
        int i = Build.VERSION.SDK_INT;
        this.f1291d.mo1412a("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new ban(bac));
        aph aph3 = this.f1291d;
        aph3.mo1412a("Bitmap", ParcelFileDescriptor.class, Bitmap.class, bbf);
        aph3.mo1412a("Bitmap", AssetFileDescriptor.class, Bitmap.class, new bbf(auk2, new bbc((byte[]) null)));
        aph3.mo1410a(Bitmap.class, Bitmap.class, (axp) ayi.f1861a);
        aph3.mo1412a("Bitmap", Bitmap.class, Bitmap.class, new baz());
        aph3.mo1408a(Bitmap.class, (arc) azj);
        aph3.mo1412a("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new azh(resources, azm));
        aph3.mo1412a("BitmapDrawable", InputStream.class, BitmapDrawable.class, new azh(resources, bau));
        aph3.mo1412a("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new azh(resources, bbf));
        aph3.mo1408a(BitmapDrawable.class, (arc) new azi(auk2, azj));
        aph3.mo1412a("Gif", InputStream.class, bbt.class, new bcd(a, bbq, aui2));
        aph3.mo1412a("Gif", ByteBuffer.class, bbt.class, bbq);
        aph3.mo1408a(bbt.class, (arc) new bbu());
        aph3.mo1410a(apz.class, apz.class, (axp) ayi.f1861a);
        aph3.mo1412a("Bitmap", apz.class, Bitmap.class, new bcb(auk2));
        aph3.mo1409a(Uri.class, Drawable.class, (arb) bbm);
        aph3.mo1409a(Uri.class, Bitmap.class, (arb) new baq(bbm, auk2));
        aph3.mo1406a((arj) new bbg());
        aph3.mo1410a(File.class, ByteBuffer.class, (axp) new awn());
        aph3.mo1410a(File.class, InputStream.class, (axp) new awu((byte[]) null));
        aph3.mo1409a(File.class, File.class, (arb) new bbo());
        aph3.mo1410a(File.class, ParcelFileDescriptor.class, (axp) new awu());
        aph3.mo1410a(File.class, File.class, (axp) ayi.f1861a);
        aph3.mo1406a((arj) new ars(aui2));
        int i2 = Build.VERSION.SDK_INT;
        this.f1291d.mo1406a((arj) new arv());
        aph aph4 = this.f1291d;
        aya aya2 = aya;
        aph4.mo1410a(Integer.TYPE, InputStream.class, (axp) aya2);
        axz axz3 = axz2;
        aph4.mo1410a(Integer.TYPE, ParcelFileDescriptor.class, (axp) axz3);
        aph4.mo1410a(Integer.class, InputStream.class, (axp) aya2);
        aph4.mo1410a(Integer.class, ParcelFileDescriptor.class, (axp) axz3);
        ayb ayb3 = ayb2;
        aph4.mo1410a(Integer.class, Uri.class, (axp) ayb3);
        axy axy3 = axy2;
        aph4.mo1410a(Integer.TYPE, AssetFileDescriptor.class, (axp) axy3);
        aph4.mo1410a(Integer.class, AssetFileDescriptor.class, (axp) axy3);
        aph4.mo1410a(Integer.TYPE, Uri.class, (axp) ayb3);
        aph4.mo1410a(String.class, InputStream.class, (axp) new aws());
        aph4.mo1410a(Uri.class, InputStream.class, (axp) new aws());
        aph4.mo1410a(String.class, InputStream.class, (axp) new ayg());
        aph4.mo1410a(String.class, ParcelFileDescriptor.class, (axp) new ayf());
        aph4.mo1410a(String.class, AssetFileDescriptor.class, (axp) new aye());
        aph4.mo1410a(Uri.class, InputStream.class, (axp) new ayu());
        aph4.mo1410a(Uri.class, InputStream.class, (axp) new awc(context.getAssets()));
        aph4.mo1410a(Uri.class, ParcelFileDescriptor.class, (axp) new awb(context.getAssets()));
        Context context3 = context;
        aph4.mo1410a(Uri.class, InputStream.class, (axp) new ayw(context3));
        aph4.mo1410a(Uri.class, InputStream.class, (axp) new ayy(context3));
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1291d.mo1410a(Uri.class, InputStream.class, (axp) new aza(context3, (byte[]) null));
            this.f1291d.mo1410a(Uri.class, ParcelFileDescriptor.class, (axp) new aza(context3));
        }
        aph aph5 = this.f1291d;
        ContentResolver contentResolver2 = contentResolver;
        aph5.mo1410a(Uri.class, InputStream.class, (axp) new ayo(contentResolver2));
        aph5.mo1410a(Uri.class, ParcelFileDescriptor.class, (axp) new aym(contentResolver2));
        aph5.mo1410a(Uri.class, AssetFileDescriptor.class, (axp) new ayl(contentResolver2));
        aph5.mo1410a(Uri.class, InputStream.class, (axp) new ayq());
        aph5.mo1410a(URL.class, InputStream.class, (axp) new azd());
        aph5.mo1410a(Uri.class, File.class, (axp) new axg(context3));
        aph5.mo1410a(axa.class, InputStream.class, (axp) new ays());
        Class<byte[]> cls3 = cls2;
        aph5.mo1410a((Class) cls3, ByteBuffer.class, (axp) new awf());
        aph5.mo1410a((Class) cls3, InputStream.class, (axp) new awj());
        aph5.mo1410a(Uri.class, Uri.class, (axp) ayi.f1861a);
        aph5.mo1410a(Drawable.class, Drawable.class, (axp) ayi.f1861a);
        aph5.mo1409a(Drawable.class, Drawable.class, (arb) new bbn());
        aph5.mo1411a(Bitmap.class, BitmapDrawable.class, (bci) new bcf(resources));
        bce bce2 = bce;
        aph5.mo1411a(Bitmap.class, (Class) cls3, (bci) bce2);
        bch bch2 = bch;
        aph5.mo1411a(Drawable.class, (Class) cls3, (bci) new bcg(auk2, bce2, bch2));
        aph5.mo1411a(bbt.class, (Class) cls3, (bci) bch2);
        this.f1290c = new apa(context, aui, this.f1291d, aov, map, list, atj);
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    /* renamed from: b */
    public final void mo1397b() {
        bfp.m2430a();
        this.f1295j.mo1674a();
        this.f1289b.mo1643a();
        this.f1292e.mo1636a();
    }

    /* renamed from: a */
    public static aow m1346a(Context context) {
        if (f1286h == null) {
            GeneratedAppGlideModule d = m1351d(context.getApplicationContext());
            synchronized (aow.class) {
                if (f1286h == null) {
                    if (!f1287i) {
                        f1287i = true;
                        m1347a(context, new aoz(), d);
                        f1287i = false;
                    } else {
                        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
                    }
                }
            }
        }
        return f1286h;
    }

    /* renamed from: d */
    private static GeneratedAppGlideModule m1351d(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context.getApplicationContext()});
        } catch (ClassNotFoundException e) {
            if (!Log.isLoggable("Glide", 5)) {
                return null;
            }
            Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            return null;
        } catch (InstantiationException e2) {
            m1348a((Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            m1348a((Exception) e3);
            return null;
        } catch (NoSuchMethodException e4) {
            m1348a((Exception) e4);
            return null;
        } catch (InvocationTargetException e5) {
            m1348a((Exception) e5);
            return null;
        }
    }

    /* renamed from: a */
    public final Context mo1395a() {
        return this.f1290c.getBaseContext();
    }

    /* renamed from: b */
    public static bdc m1349b(Context context) {
        cns.m4633a((Object) context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return m1346a(context).f1293f;
    }

    /* renamed from: a */
    private static void m1347a(Context context, aoz aoz, GeneratedAppGlideModule generatedAppGlideModule) {
        bdb bdb;
        int i;
        aoz aoz2 = aoz;
        GeneratedAppGlideModule generatedAppGlideModule2 = generatedAppGlideModule;
        Context applicationContext = context.getApplicationContext();
        Collections.emptyList();
        bdm bdm = new bdm(applicationContext);
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = bdm.f2089a.getPackageManager().getApplicationInfo(bdm.f2089a.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(bdm.m2201a(str));
                    }
                }
            }
            if (generatedAppGlideModule2 != null && !generatedAppGlideModule.mo3305a().isEmpty()) {
                Set a = generatedAppGlideModule.mo3305a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (a.contains(((bdk) it.next()).getClass())) {
                        it.remove();
                    }
                }
            }
            if (generatedAppGlideModule2 != null) {
                bdb = generatedAppGlideModule.mo3306b();
            } else {
                bdb = null;
            }
            aoz2.f1308l = bdb;
            int size = arrayList.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                ((bdk) arrayList.get(i3)).mo1837a(applicationContext, aoz2);
            }
            if (generatedAppGlideModule2 != null) {
                generatedAppGlideModule2.mo1837a(applicationContext, aoz2);
            }
            if (aoz2.f1302f == null) {
                avw avw = new avw(false);
                avw.mo1677a(avz.m1778a());
                avw.f1779a = "source";
                aoz2.f1302f = avw.mo1676a();
            }
            if (aoz2.f1303g == null) {
                avw avw2 = new avw(true);
                avw2.mo1677a(1);
                avw2.f1779a = "disk-cache";
                aoz2.f1303g = avw2.mo1676a();
            }
            if (aoz2.f1309m == null) {
                if (avz.m1778a() >= 4) {
                    i = 2;
                } else {
                    i = 1;
                }
                avw avw3 = new avw(true);
                avw3.mo1677a(i);
                avw3.f1779a = "animation";
                aoz2.f1309m = avw3.mo1676a();
            }
            if (aoz2.f1305i == null) {
                aoz2.f1305i = new avs(new avp(applicationContext));
            }
            if (aoz2.f1306j == null) {
                aoz2.f1306j = new bct();
            }
            if (aoz2.f1299c == null) {
                int i4 = aoz2.f1305i.f1772a;
                if (i4 > 0) {
                    aoz2.f1299c = new aut((long) i4);
                } else {
                    aoz2.f1299c = new aul();
                }
            }
            if (aoz2.f1300d == null) {
                aoz2.f1300d = new aus(aoz2.f1305i.f1774c);
            }
            if (aoz2.f1301e == null) {
                aoz2.f1301e = new avm((long) aoz2.f1305i.f1773b);
            }
            if (aoz2.f1304h == null) {
                aoz2.f1304h = new avl(applicationContext);
            }
            if (aoz2.f1298b == null) {
                aoz2.f1298b = new atj(aoz2.f1301e, aoz2.f1304h, aoz2.f1303g, aoz2.f1302f, new avz(new ThreadPoolExecutor(0, Integer.MAX_VALUE, avz.f1787a, TimeUnit.MILLISECONDS, new SynchronousQueue(), new avy("source-unlimited", false))), aoz2.f1309m);
            }
            List list = aoz2.f1310n;
            if (list != null) {
                aoz2.f1310n = Collections.unmodifiableList(list);
            } else {
                aoz2.f1310n = Collections.emptyList();
            }
            aow aow = new aow(applicationContext, aoz2.f1298b, aoz2.f1301e, aoz2.f1299c, aoz2.f1300d, new bdc(aoz2.f1308l), aoz2.f1307k, aoz2.f1297a, aoz2.f1310n);
            int size2 = arrayList.size();
            while (i2 < size2) {
                bdk bdk = (bdk) arrayList.get(i2);
                try {
                    bdk.mo1838a(applicationContext, aow, aow.f1291d);
                    i2++;
                } catch (AbstractMethodError e) {
                    String valueOf = String.valueOf(bdk.getClass().getName());
                    throw new IllegalStateException(valueOf.length() == 0 ? new String("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: ") : "Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: ".concat(valueOf), e);
                }
            }
            if (generatedAppGlideModule2 != null) {
                generatedAppGlideModule2.mo1838a(applicationContext, aow, aow.f1291d);
            }
            applicationContext.registerComponentCallbacks(aow);
            f1286h = aow;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e2);
        }
    }

    public final void onLowMemory() {
        mo1397b();
    }

    public final void onTrimMemory(int i) {
        mo1396a(i);
    }

    /* renamed from: a */
    private static void m1348a(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    /* renamed from: a */
    public final void mo1396a(int i) {
        bfp.m2430a();
        List list = this.f1294g;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            apn apn = (apn) list.get(i2);
        }
        avo avo = this.f1295j;
        if (i >= 40) {
            ((bfl) avo).mo1954a();
        } else if (i >= 20 || i == 15) {
            bfl bfl = (bfl) avo;
            bfl.mo1955a(bfl.mo1956b() / 2);
        }
        this.f1289b.mo1644a(i);
        this.f1292e.mo1637a(i);
    }

    /* renamed from: c */
    public static apn m1350c(Context context) {
        return m1349b(context).mo1824a(context);
    }
}
