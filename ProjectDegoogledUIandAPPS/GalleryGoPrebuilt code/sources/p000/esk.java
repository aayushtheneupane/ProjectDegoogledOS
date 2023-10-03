package p000;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: esk */
/* compiled from: PG */
public final class esk extends eqv {
    public static final Parcelable.Creator CREATOR = new esl();

    /* renamed from: a */
    public File f8941a;

    /* renamed from: b */
    private ParcelFileDescriptor f8942b;

    /* renamed from: c */
    private final String f8943c;

    /* renamed from: d */
    private final String f8944d;

    /* renamed from: e */
    private byte[] f8945e;

    public esk(ParcelFileDescriptor parcelFileDescriptor, String str, String str2) {
        this.f8942b = parcelFileDescriptor;
        this.f8943c = str;
        this.f8944d = str2;
    }

    public esk(byte[] bArr, String str, String str2) {
        this((ParcelFileDescriptor) null, str, str2);
        this.f8945e = bArr;
    }

    /* renamed from: a */
    private static final void m8100a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("FileTeleporter", "Could not close stream", e);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (this.f8942b == null) {
            File file = this.f8941a;
            if (file != null) {
                try {
                    File createTempFile = File.createTempFile("teleporter", ".tmp", file);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                        this.f8942b = ParcelFileDescriptor.open(createTempFile, 268435456);
                        createTempFile.delete();
                        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                        try {
                            dataOutputStream.writeInt(this.f8945e.length);
                            dataOutputStream.writeUTF(this.f8943c);
                            dataOutputStream.writeUTF(this.f8944d);
                            dataOutputStream.write(this.f8945e);
                            m8100a(dataOutputStream);
                        } catch (IOException e) {
                            throw new IllegalStateException("Could not write into unlinked file", e);
                        } catch (Throwable th) {
                            m8100a(dataOutputStream);
                            throw th;
                        }
                    } catch (FileNotFoundException e2) {
                        throw new IllegalStateException("Temporary file is somehow already deleted.");
                    }
                } catch (IOException e3) {
                    throw new IllegalStateException("Could not create temporary file:", e3);
                }
            } else {
                throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel.");
            }
        }
        int a = abj.m82a(parcel);
        abj.m97a(parcel, 2, (Parcelable) this.f8942b, i);
        abj.m98a(parcel, 3, this.f8943c);
        abj.m98a(parcel, 4, this.f8944d);
        abj.m92a(parcel, a);
    }
}
