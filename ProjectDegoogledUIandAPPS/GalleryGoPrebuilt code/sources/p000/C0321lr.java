package p000;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import p003j$.util.Objects;

/* renamed from: lr */
/* compiled from: PG */
public class C0321lr {
    /* renamed from: b */
    public static /* synthetic */ String m14633b(int i) {
        switch (i) {
            case RecyclerView.SCROLL_STATE_SETTLING:
                return "LEFT";
            case 3:
                return "TOP";
            case 4:
                return "RIGHT";
            case 5:
                return "BOTTOM";
            case 6:
                return "BASELINE";
            case 7:
                return "CENTER";
            case 8:
                return "CENTER_X";
            default:
                return "CENTER_Y";
        }
    }

    /* renamed from: a */
    public static void m14629a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("MultiDex", "Failed to close resource", e);
        }
    }

    /* renamed from: a */
    public static SharedPreferences m14623a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getSharedPreferences("multidex.version", 4);
    }

    /* renamed from: a */
    public static long m14622a(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    public static long m14632b(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            long length = randomAccessFile.length() - 22;
            if (length >= 0) {
                long j = -65536 + length;
                if (j < 0) {
                    j = 0;
                }
                int reverseBytes = Integer.reverseBytes(101010256);
                while (true) {
                    randomAccessFile.seek(length);
                    if (randomAccessFile.readInt() != reverseBytes) {
                        length--;
                        if (length < j) {
                            throw new ZipException("End Of Central Directory signature not found");
                        }
                    } else {
                        randomAccessFile.skipBytes(2);
                        randomAccessFile.skipBytes(2);
                        randomAccessFile.skipBytes(2);
                        randomAccessFile.skipBytes(2);
                        C0126en enVar = new C0126en();
                        enVar.f8600b = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                        enVar.f8599a = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                        CRC32 crc32 = new CRC32();
                        long j2 = enVar.f8600b;
                        randomAccessFile.seek(enVar.f8599a);
                        byte[] bArr = new byte[16384];
                        int read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j2));
                        while (read != -1) {
                            crc32.update(bArr, 0, read);
                            j2 -= (long) read;
                            if (j2 == 0) {
                                break;
                            }
                            read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j2));
                        }
                        long value = crc32.getValue();
                        randomAccessFile.close();
                        return value == -1 ? value - 1 : value;
                    }
                }
            } else {
                throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
            }
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.util.zip.ZipFile} */
    /* JADX WARNING: type inference failed for: r1v33 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List m14625a(java.io.File r19, java.io.File r20) {
        /*
            r1 = r20
            java.lang.String r2 = ".zip"
            java.lang.String r3 = ".dex"
            java.lang.String r4 = "Failed to close resource"
            java.lang.String r5 = "classes"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = r19.getName()
            r0.append(r6)
            java.lang.String r6 = ".classes"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            el r0 = new el
            r0.<init>(r6)
            java.io.File[] r0 = r1.listFiles(r0)
            java.lang.String r8 = "MultiDex"
            if (r0 != 0) goto L_0x004a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r9 = "Failed to list secondary dex dir content ("
            r0.append(r9)
            java.lang.String r9 = r20.getPath()
            r0.append(r9)
            java.lang.String r9 = ")."
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r8, r0)
            goto L_0x00a6
        L_0x004a:
            int r9 = r0.length
            r10 = 0
        L_0x004c:
            if (r10 >= r9) goto L_0x00a6
            r11 = r0[r10]
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Trying to delete old file "
            r12.append(r13)
            java.lang.String r13 = r11.getPath()
            r12.append(r13)
            java.lang.String r13 = " of size "
            r12.append(r13)
            long r13 = r11.length()
            r12.append(r13)
            r12.toString()
            boolean r12 = r11.delete()
            if (r12 != 0) goto L_0x008f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Failed to delete old file "
            r12.append(r13)
            java.lang.String r11 = r11.getPath()
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            android.util.Log.w(r8, r11)
            goto L_0x00a3
        L_0x008f:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Deleted old file "
            r12.append(r13)
            java.lang.String r11 = r11.getPath()
            r12.append(r11)
            r12.toString()
        L_0x00a3:
            int r10 = r10 + 1
            goto L_0x004c
        L_0x00a6:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.zip.ZipFile r10 = new java.util.zip.ZipFile
            r0 = r19
            r10.<init>(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0314 }
            r0.<init>()     // Catch:{ all -> 0x0314 }
            r0.append(r5)     // Catch:{ all -> 0x0314 }
            r11 = 2
            r0.append(r11)     // Catch:{ all -> 0x0314 }
            r0.append(r3)     // Catch:{ all -> 0x0314 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0314 }
            java.util.zip.ZipEntry r0 = r10.getEntry(r0)     // Catch:{ all -> 0x0314 }
            r11 = r0
            r12 = 2
        L_0x00cb:
            if (r11 == 0) goto L_0x0308
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0314 }
            r0.<init>()     // Catch:{ all -> 0x0314 }
            r0.append(r6)     // Catch:{ all -> 0x0314 }
            r0.append(r12)     // Catch:{ all -> 0x0314 }
            r0.append(r2)     // Catch:{ all -> 0x0314 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0314 }
            em r13 = new em     // Catch:{ all -> 0x0314 }
            r13.<init>(r1, r0)     // Catch:{ all -> 0x0314 }
            r9.add(r13)     // Catch:{ all -> 0x0314 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0314 }
            r0.<init>()     // Catch:{ all -> 0x0314 }
            java.lang.String r14 = "Extraction is needed for file "
            r0.append(r14)     // Catch:{ all -> 0x0314 }
            r0.append(r13)     // Catch:{ all -> 0x0314 }
            r0.toString()     // Catch:{ all -> 0x0314 }
            r0 = 0
            r14 = 0
        L_0x00f9:
            r15 = 3
            if (r0 >= r15) goto L_0x02af
            if (r14 != 0) goto L_0x02a9
            int r14 = r0 + 1
            java.io.InputStream r15 = r10.getInputStream(r11)     // Catch:{ all -> 0x02a4 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a4 }
            r0.<init>()     // Catch:{ all -> 0x02a4 }
            java.lang.String r7 = "tmp-"
            r0.append(r7)     // Catch:{ all -> 0x02a4 }
            r0.append(r6)     // Catch:{ all -> 0x02a4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02a4 }
            java.io.File r7 = r13.getParentFile()     // Catch:{ all -> 0x02a4 }
            java.io.File r7 = java.io.File.createTempFile(r0, r2, r7)     // Catch:{ all -> 0x02a4 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a4 }
            r0.<init>()     // Catch:{ all -> 0x02a4 }
            java.lang.String r1 = "Extracting "
            r0.append(r1)     // Catch:{ all -> 0x02a4 }
            java.lang.String r1 = r7.getPath()     // Catch:{ all -> 0x02a4 }
            r0.append(r1)     // Catch:{ all -> 0x02a4 }
            r0.toString()     // Catch:{ all -> 0x02a4 }
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x0294 }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0294 }
            r16 = r2
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0294 }
            r2.<init>(r7)     // Catch:{ all -> 0x0294 }
            r0.<init>(r2)     // Catch:{ all -> 0x0294 }
            r1.<init>(r0)     // Catch:{ all -> 0x0294 }
            java.util.zip.ZipEntry r0 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x028b }
            java.lang.String r2 = "classes.dex"
            r0.<init>(r2)     // Catch:{ all -> 0x028b }
            r2 = r9
            r17 = r10
            long r9 = r11.getTime()     // Catch:{ all -> 0x0289 }
            r0.setTime(r9)     // Catch:{ all -> 0x0289 }
            r1.putNextEntry(r0)     // Catch:{ all -> 0x0289 }
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0289 }
            int r9 = r15.read(r0)     // Catch:{ all -> 0x0289 }
        L_0x015e:
            r10 = -1
            if (r9 == r10) goto L_0x016a
            r10 = 0
            r1.write(r0, r10, r9)     // Catch:{ all -> 0x0289 }
            int r9 = r15.read(r0)     // Catch:{ all -> 0x0289 }
            goto L_0x015e
        L_0x016a:
            r10 = 0
            r1.closeEntry()     // Catch:{ all -> 0x0289 }
            r1.close()     // Catch:{ all -> 0x0292 }
            boolean r0 = r7.setReadOnly()     // Catch:{ all -> 0x0292 }
            if (r0 == 0) goto L_0x025d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r0.<init>()     // Catch:{ all -> 0x0292 }
            java.lang.String r1 = "Renaming to "
            r0.append(r1)     // Catch:{ all -> 0x0292 }
            java.lang.String r1 = r13.getPath()     // Catch:{ all -> 0x0292 }
            r0.append(r1)     // Catch:{ all -> 0x0292 }
            r0.toString()     // Catch:{ all -> 0x0292 }
            boolean r0 = r7.renameTo(r13)     // Catch:{ all -> 0x0292 }
            if (r0 == 0) goto L_0x0231
            m14629a((java.io.Closeable) r15)     // Catch:{ all -> 0x029e }
            r7.delete()     // Catch:{ all -> 0x029e }
            long r0 = m14632b((java.io.File) r13)     // Catch:{ IOException -> 0x019f }
            r13.f8534a = r0     // Catch:{ IOException -> 0x019f }
            r0 = 1
            goto L_0x01ba
        L_0x019f:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x029e }
            r1.<init>()     // Catch:{ all -> 0x029e }
            java.lang.String r7 = "Failed to read crc from "
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = r13.getAbsolutePath()     // Catch:{ all -> 0x029e }
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x029e }
            android.util.Log.w(r8, r1, r0)     // Catch:{ all -> 0x029e }
            r0 = 0
        L_0x01ba:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x029e }
            r1.<init>()     // Catch:{ all -> 0x029e }
            java.lang.String r7 = "Extraction "
            r1.append(r7)     // Catch:{ all -> 0x029e }
            if (r0 != 0) goto L_0x01c9
            java.lang.String r7 = "failed"
            goto L_0x01cc
        L_0x01c9:
            java.lang.String r7 = "succeeded"
        L_0x01cc:
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = " - length "
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = r13.getAbsolutePath()     // Catch:{ all -> 0x029e }
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = ": "
            r1.append(r7)     // Catch:{ all -> 0x029e }
            r19 = r11
            long r10 = r13.length()     // Catch:{ all -> 0x029e }
            r1.append(r10)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = " - crc: "
            r1.append(r7)     // Catch:{ all -> 0x029e }
            long r9 = r13.f8534a     // Catch:{ all -> 0x029e }
            r1.append(r9)     // Catch:{ all -> 0x029e }
            r1.toString()     // Catch:{ all -> 0x029e }
            if (r0 != 0) goto L_0x0220
            r13.delete()     // Catch:{ all -> 0x029e }
            boolean r1 = r13.exists()     // Catch:{ all -> 0x029e }
            if (r1 == 0) goto L_0x0220
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x029e }
            r1.<init>()     // Catch:{ all -> 0x029e }
            java.lang.String r7 = "Failed to delete corrupted secondary dex '"
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = r13.getPath()     // Catch:{ all -> 0x029e }
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r7 = "'"
            r1.append(r7)     // Catch:{ all -> 0x029e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x029e }
            android.util.Log.w(r8, r1)     // Catch:{ all -> 0x029e }
            goto L_0x0221
        L_0x0220:
        L_0x0221:
            r11 = r19
            r1 = r20
            r9 = r2
            r2 = r16
            r10 = r17
            r18 = r14
            r14 = r0
            r0 = r18
            goto L_0x00f9
        L_0x0231:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0292 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r1.<init>()     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "Failed to rename \""
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r7.getAbsolutePath()     // Catch:{ all -> 0x0292 }
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "\" to \""
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r13.getAbsolutePath()     // Catch:{ all -> 0x0292 }
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "\""
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r1)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x025d:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0292 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r1.<init>()     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "Failed to mark readonly \""
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r7.getAbsolutePath()     // Catch:{ all -> 0x0292 }
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "\" (tmp of \""
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r13.getAbsolutePath()     // Catch:{ all -> 0x0292 }
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "\")"
            r1.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r1)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0289:
            r0 = move-exception
            goto L_0x028e
        L_0x028b:
            r0 = move-exception
            r17 = r10
        L_0x028e:
            r1.close()     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0292:
            r0 = move-exception
            goto L_0x0297
        L_0x0294:
            r0 = move-exception
            r17 = r10
        L_0x0297:
            m14629a((java.io.Closeable) r15)     // Catch:{ all -> 0x029e }
            r7.delete()     // Catch:{ all -> 0x029e }
            throw r0     // Catch:{ all -> 0x029e }
        L_0x029e:
            r0 = move-exception
            r2 = r0
            r1 = r17
            goto L_0x0317
        L_0x02a4:
            r0 = move-exception
            r2 = r0
            r1 = r10
            goto L_0x0317
        L_0x02a9:
            r16 = r2
            r2 = r9
            r17 = r10
            goto L_0x02b6
        L_0x02af:
            r16 = r2
            r2 = r9
            r17 = r10
            if (r14 == 0) goto L_0x02dc
        L_0x02b6:
            int r12 = r12 + 1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x02d8 }
            r0.<init>()     // Catch:{ all -> 0x02d8 }
            r0.append(r5)     // Catch:{ all -> 0x02d8 }
            r0.append(r12)     // Catch:{ all -> 0x02d8 }
            r0.append(r3)     // Catch:{ all -> 0x02d8 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x02d8 }
            r1 = r17
            java.util.zip.ZipEntry r11 = r1.getEntry(r0)     // Catch:{ all -> 0x0306 }
            r10 = r1
            r9 = r2
            r2 = r16
            r1 = r20
            goto L_0x00cb
        L_0x02d8:
            r0 = move-exception
            r1 = r17
            goto L_0x0316
        L_0x02dc:
            r1 = r17
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0306 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0306 }
            r2.<init>()     // Catch:{ all -> 0x0306 }
            java.lang.String r3 = "Could not create zip file "
            r2.append(r3)     // Catch:{ all -> 0x0306 }
            java.lang.String r3 = r13.getAbsolutePath()     // Catch:{ all -> 0x0306 }
            r2.append(r3)     // Catch:{ all -> 0x0306 }
            java.lang.String r3 = " for secondary dex ("
            r2.append(r3)     // Catch:{ all -> 0x0306 }
            r2.append(r12)     // Catch:{ all -> 0x0306 }
            java.lang.String r3 = ")"
            r2.append(r3)     // Catch:{ all -> 0x0306 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0306 }
            r0.<init>(r2)     // Catch:{ all -> 0x0306 }
            throw r0     // Catch:{ all -> 0x0306 }
        L_0x0306:
            r0 = move-exception
            goto L_0x0316
        L_0x0308:
            r2 = r9
            r1 = r10
            r1.close()     // Catch:{ IOException -> 0x030e }
            goto L_0x0313
        L_0x030e:
            r0 = move-exception
            r1 = r0
            android.util.Log.w(r8, r4, r1)
        L_0x0313:
            return r2
        L_0x0314:
            r0 = move-exception
            r1 = r10
        L_0x0316:
            r2 = r0
        L_0x0317:
            r1.close()     // Catch:{ IOException -> 0x031b }
            goto L_0x0320
        L_0x031b:
            r0 = move-exception
            r1 = r0
            android.util.Log.w(r8, r4, r1)
        L_0x0320:
            goto L_0x0322
        L_0x0321:
            throw r2
        L_0x0322:
            goto L_0x0321
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0321lr.m14625a(java.io.File, java.io.File):java.util.List");
    }

    /* renamed from: a */
    public static void m14628a(Context context, long j, long j2, List list) {
        SharedPreferences.Editor edit = m14623a(context).edit();
        edit.putLong("timestamp", j);
        edit.putLong("crc", j2);
        edit.putInt("dex.number", list.size() + 1);
        int size = list.size();
        int i = 2;
        for (int i2 = 0; i2 < size; i2++) {
            C0125em emVar = (C0125em) list.get(i2);
            edit.putLong("dex.crc." + i, emVar.f8534a);
            edit.putLong("dex.time." + i, emVar.lastModified());
            i++;
        }
        edit.commit();
    }

    /* renamed from: a */
    public static boolean m14630a(ConnectivityManager connectivityManager) {
        int i = Build.VERSION.SDK_INT;
        return connectivityManager.isActiveNetworkMetered();
    }

    /* renamed from: a */
    public static boolean m14631a(Object obj, Object obj2) {
        int i = Build.VERSION.SDK_INT;
        return Objects.equals(obj, obj2);
    }

    /* renamed from: a */
    public static void m14626a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    public static Object m14624a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: a */
    public static void m14627a(int i, int i2, int i3, Rect rect, Rect rect2, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        Gravity.apply(i, i2, i3, rect, rect2, i4);
    }

    /* renamed from: a */
    public static int m14621a(int i, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        return Gravity.getAbsoluteGravity(i, i2);
    }
}
