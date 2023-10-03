package p000;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* renamed from: fqc */
/* compiled from: PG */
public final /* synthetic */ class fqc implements hqk {

    /* renamed from: a */
    public static final hqk f10243a = new fqc();

    private fqc() {
    }

    /* renamed from: a */
    public final Object mo2652a() {
        hpy hpy;
        BufferedReader bufferedReader;
        Context context = fqg.f10249b;
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        String str3 = Build.HARDWARE;
        if ((!str.equals("eng") && !str.equals("userdebug")) || ((!str3.equals("goldfish") && !str3.equals("ranchu") && !str3.equals("robolectric")) || (!str2.contains("dev-keys") && !str2.contains("test-keys")))) {
            return hph.f13219a;
        }
        int i = Build.VERSION.SDK_INT;
        if (!context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            if (file.exists()) {
                hpy = hpy.m11893b(file);
            } else {
                hpy = hph.f13219a;
            }
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            hpy = hph.f13219a;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        if (!hpy.mo7646a()) {
            return hph.f13219a;
        }
        File file2 = (File) hpy.mo7647b();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(" ", 3);
                    if (split.length == 3) {
                        String str4 = split[0];
                        String decode = Uri.decode(split[1]);
                        String decode2 = Uri.decode(split[2]);
                        if (!hashMap.containsKey(str4)) {
                            hashMap.put(str4, new HashMap());
                        }
                        ((Map) hashMap.get(str4)).put(decode, decode2);
                    } else {
                        Log.e("HermeticFileOverrides", readLine.length() == 0 ? new String("Invalid: ") : "Invalid: ".concat(readLine));
                    }
                } else {
                    String valueOf = String.valueOf(file2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
                    sb.append("Parsed ");
                    sb.append(valueOf);
                    sb.toString();
                    fpz fpz = new fpz(hashMap);
                    bufferedReader.close();
                    return hpy.m11893b(fpz);
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th2) {
            ifn.m12935a(th, th2);
        }
        throw th;
    }
}
