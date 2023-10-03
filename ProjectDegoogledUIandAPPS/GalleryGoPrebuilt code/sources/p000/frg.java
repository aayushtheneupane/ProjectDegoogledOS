package p000;

import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandomSpi;

/* renamed from: frg */
/* compiled from: PG */
public final class frg extends SecureRandomSpi {

    /* renamed from: a */
    private static final File f10312a = new File("/dev/urandom");

    /* renamed from: b */
    private static final Object f10313b = new Object();

    /* renamed from: c */
    private static DataInputStream f10314c;

    /* renamed from: d */
    private static OutputStream f10315d;

    /* renamed from: e */
    private boolean f10316e;

    /* access modifiers changed from: protected */
    public final byte[] engineGenerateSeed(int i) {
        byte[] bArr = new byte[i];
        engineNextBytes(bArr);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final void engineNextBytes(byte[] bArr) {
        DataInputStream dataInputStream;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            if (!this.f10316e) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeLong(System.currentTimeMillis());
                    dataOutputStream.writeLong(System.nanoTime());
                    dataOutputStream.writeInt(Process.myPid());
                    dataOutputStream.writeInt(Process.myUid());
                    StringBuilder sb = new StringBuilder();
                    String str = Build.FINGERPRINT;
                    if (str != null) {
                        sb.append(str);
                    }
                    String str2 = Build.SERIAL;
                    if (str2 != null) {
                        sb.append(str2);
                    }
                    dataOutputStream.write(sb.toString().getBytes("UTF-8"));
                    dataOutputStream.close();
                    engineSetSeed(byteArrayOutputStream.toByteArray());
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("UTF-8 encoding not supported");
                } catch (IOException e2) {
                    throw new SecurityException("Failed to generate seed", e2);
                }
            }
            synchronized (f10313b) {
                synchronized (f10313b) {
                    if (f10314c == null) {
                        try {
                            f10314c = new DataInputStream(new FileInputStream(f10312a));
                        } catch (IOException e3) {
                            String valueOf = String.valueOf(f10312a);
                            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 27);
                            sb2.append("Failed to open ");
                            sb2.append(valueOf);
                            sb2.append(" for reading");
                            throw new SecurityException(sb2.toString(), e3);
                        }
                    }
                    dataInputStream = f10314c;
                }
            }
            synchronized (dataInputStream) {
                dataInputStream.readFully(bArr);
            }
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        } catch (IOException e4) {
            try {
                String valueOf2 = String.valueOf(f10312a);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 20);
                sb3.append("Failed to read from ");
                sb3.append(valueOf2);
                throw new SecurityException(sb3.toString(), e4);
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void engineSetSeed(byte[] bArr) {
        OutputStream outputStream;
        try {
            synchronized (f10313b) {
                synchronized (f10313b) {
                    if (f10315d == null) {
                        f10315d = new FileOutputStream(f10312a);
                    }
                    outputStream = f10315d;
                }
            }
            outputStream.write(bArr);
            outputStream.flush();
        } catch (IOException e) {
            try {
                String valueOf = String.valueOf(f10312a);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Failed to mix seed into ");
                sb.append(valueOf);
                Log.w("PrngFixes", sb.toString());
            } catch (Throwable th) {
                this.f10316e = true;
                throw th;
            }
        }
        this.f10316e = true;
    }
}
