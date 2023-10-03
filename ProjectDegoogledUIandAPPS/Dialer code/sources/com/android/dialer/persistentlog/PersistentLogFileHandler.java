package com.android.dialer.persistentlog;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class PersistentLogFileHandler {
    private static final byte[] ENTRY_POSTFIX = {76};
    private static final byte[] ENTRY_PREFIX = {80};
    private Context context;
    private final int fileCountLimit;
    private final int fileSizeLimit;
    private File logDirectory;
    private File outputFile;
    private SharedPreferences sharedPreferences;
    private final String subfolder;

    private static class LogCorruptionException extends Exception {
        public LogCorruptionException(String str) {
            super(str);
        }
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    PersistentLogFileHandler(String str, int i, int i2) {
        this.subfolder = str;
        this.fileSizeLimit = i;
        this.fileCountLimit = i2;
    }

    private File[] getLogFiles() {
        this.logDirectory.mkdirs();
        File[] listFiles = this.logDirectory.listFiles();
        if (listFiles == null) {
            listFiles = new File[0];
        }
        Arrays.sort(listFiles, $$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclcLh8HqD3Dw8.INSTANCE);
        return listFiles;
    }

    private String getNextFileKey() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("persistent_long_next_file_index_");
        outline13.append(this.subfolder);
        return outline13.toString();
    }

    private boolean initializeSharedPreference(Context context2) {
        if (this.sharedPreferences == null && R$dimen.isUserUnlocked(context2)) {
            this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context2);
            return true;
        } else if (this.sharedPreferences != null) {
            return true;
        } else {
            return false;
        }
    }

    private byte[] readLog(DataInputStream dataInputStream) throws IOException, LogCorruptionException {
        try {
            byte[] bArr = new byte[ENTRY_PREFIX.length];
            if (dataInputStream.read(bArr) == -1) {
                return null;
            }
            if (Arrays.equals(bArr, ENTRY_PREFIX)) {
                int readInt = dataInputStream.readInt();
                if (readInt <= this.fileSizeLimit) {
                    byte[] bArr2 = new byte[readInt];
                    dataInputStream.read(bArr2);
                    byte[] bArr3 = new byte[ENTRY_POSTFIX.length];
                    dataInputStream.read(bArr3);
                    if (Arrays.equals(bArr3, ENTRY_POSTFIX)) {
                        return bArr2;
                    }
                    throw new LogCorruptionException("entry postfix mismatch");
                }
                throw new LogCorruptionException("data length over max size");
            }
            throw new LogCorruptionException("entry prefix mismatch");
        } catch (EOFException unused) {
            return null;
        }
    }

    private void selectNextFileToWrite() throws IOException {
        File[] logFiles = getLogFiles();
        if (logFiles.length == 0 || logFiles[logFiles.length - 1].length() > ((long) this.fileSizeLimit)) {
            if (logFiles.length >= this.fileCountLimit) {
                for (int i = 0; i <= logFiles.length - this.fileCountLimit; i++) {
                    logFiles[i].delete();
                }
            }
            File file = this.logDirectory;
            if (initializeSharedPreference(this.context)) {
                int i2 = this.sharedPreferences.getInt(getNextFileKey(), 0);
                this.sharedPreferences.edit().putInt(getNextFileKey(), i2 + 1).commit();
                this.outputFile = new File(file, String.valueOf(i2));
                return;
            }
            throw new IOException("Shared preference is not available");
        }
        this.outputFile = logFiles[logFiles.length - 1];
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        $closeResource(r10, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        $closeResource(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<byte[]> getLogs() throws java.io.IOException {
        /*
            r10 = this;
            java.io.File[] r0 = r10.getLogFiles()
            int r1 = r0.length
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0008:
            if (r3 >= r1) goto L_0x0015
            r5 = r0[r3]
            long r5 = r5.length()
            int r5 = (int) r5
            int r4 = r4 + r5
            int r3 = r3 + 1
            goto L_0x0008
        L_0x0015:
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r4)
            int r3 = r0.length
            r4 = r2
        L_0x001b:
            r5 = 0
            if (r4 >= r3) goto L_0x0041
            r6 = r0[r4]
            long r7 = r6.length()
            int r7 = (int) r7
            byte[] r7 = new byte[r7]
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile
            java.lang.String r9 = "r"
            r8.<init>(r6, r9)
            r8.readFully(r7)     // Catch:{ all -> 0x003a }
            $closeResource(r5, r8)
            r1.put(r7)
            int r4 = r4 + 1
            goto L_0x001b
        L_0x003a:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x003c }
        L_0x003c:
            r0 = move-exception
            $closeResource(r10, r8)
            throw r0
        L_0x0041:
            byte[] r0 = r1.array()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ LogCorruptionException -> 0x006d }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ LogCorruptionException -> 0x006d }
            r4.<init>(r0)     // Catch:{ LogCorruptionException -> 0x006d }
            r3.<init>(r4)     // Catch:{ LogCorruptionException -> 0x006d }
            byte[] r0 = r10.readLog(r3)     // Catch:{ all -> 0x0066 }
        L_0x0058:
            if (r0 == 0) goto L_0x0062
            r1.add(r0)     // Catch:{ all -> 0x0066 }
            byte[] r0 = r10.readLog(r3)     // Catch:{ all -> 0x0066 }
            goto L_0x0058
        L_0x0062:
            $closeResource(r5, r3)     // Catch:{ LogCorruptionException -> 0x006d }
            return r1
        L_0x0066:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
            $closeResource(r0, r3)     // Catch:{ LogCorruptionException -> 0x006d }
            throw r1     // Catch:{ LogCorruptionException -> 0x006d }
        L_0x006d:
            r0 = move-exception
            java.lang.String r1 = "PersistentLogFileHandler.getLogs"
            java.lang.String r3 = "logs corrupted, deleting"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r1, (java.lang.String) r3, (java.lang.Throwable) r0)
            java.io.File[] r0 = r10.getLogFiles()
            int r1 = r0.length
        L_0x007a:
            if (r2 >= r1) goto L_0x0084
            r3 = r0[r2]
            r3.delete()
            int r2 = r2 + 1
            goto L_0x007a
        L_0x0084:
            r10.selectNextFileToWrite()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.persistentlog.PersistentLogFileHandler.getLogs():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public void initialize(Context context2) {
        this.context = context2;
        this.logDirectory = new File(new File(context2.getCacheDir(), "persistent_log"), this.subfolder);
        initializeSharedPreference(context2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        $closeResource(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeLogs(java.util.List<byte[]> r7) throws java.io.IOException {
        /*
            r6 = this;
            java.io.File r0 = r6.outputFile
            if (r0 != 0) goto L_0x0007
            r6.selectNextFileToWrite()
        L_0x0007:
            java.io.File r0 = r6.outputFile
            r0.createNewFile()
            java.io.DataOutputStream r0 = new java.io.DataOutputStream
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            java.io.File r2 = r6.outputFile
            r3 = 1
            r1.<init>(r2, r3)
            r0.<init>(r1)
            r1 = 0
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0053 }
        L_0x001e:
            boolean r2 = r7.hasNext()     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x003c
            java.lang.Object r2 = r7.next()     // Catch:{ all -> 0x0053 }
            byte[] r2 = (byte[]) r2     // Catch:{ all -> 0x0053 }
            byte[] r3 = ENTRY_PREFIX     // Catch:{ all -> 0x0053 }
            r0.write(r3)     // Catch:{ all -> 0x0053 }
            int r3 = r2.length     // Catch:{ all -> 0x0053 }
            r0.writeInt(r3)     // Catch:{ all -> 0x0053 }
            r0.write(r2)     // Catch:{ all -> 0x0053 }
            byte[] r2 = ENTRY_POSTFIX     // Catch:{ all -> 0x0053 }
            r0.write(r2)     // Catch:{ all -> 0x0053 }
            goto L_0x001e
        L_0x003c:
            r0.close()     // Catch:{ all -> 0x0053 }
            java.io.File r7 = r6.outputFile     // Catch:{ all -> 0x0053 }
            long r2 = r7.length()     // Catch:{ all -> 0x0053 }
            int r7 = r6.fileSizeLimit     // Catch:{ all -> 0x0053 }
            long r4 = (long) r7     // Catch:{ all -> 0x0053 }
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x004f
            r6.selectNextFileToWrite()     // Catch:{ all -> 0x0053 }
        L_0x004f:
            $closeResource(r1, r0)
            return
        L_0x0053:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r7 = move-exception
            $closeResource(r6, r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.persistentlog.PersistentLogFileHandler.writeLogs(java.util.List):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        $closeResource(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeRawLogsForTest(byte[] r7) throws java.io.IOException {
        /*
            r6 = this;
            java.io.File r0 = r6.outputFile
            if (r0 != 0) goto L_0x0007
            r6.selectNextFileToWrite()
        L_0x0007:
            java.io.File r0 = r6.outputFile
            r0.createNewFile()
            java.io.DataOutputStream r0 = new java.io.DataOutputStream
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            java.io.File r2 = r6.outputFile
            r3 = 1
            r1.<init>(r2, r3)
            r0.<init>(r1)
            r1 = 0
            r0.write(r7)     // Catch:{ all -> 0x0034 }
            r0.close()     // Catch:{ all -> 0x0034 }
            java.io.File r7 = r6.outputFile     // Catch:{ all -> 0x0034 }
            long r2 = r7.length()     // Catch:{ all -> 0x0034 }
            int r7 = r6.fileSizeLimit     // Catch:{ all -> 0x0034 }
            long r4 = (long) r7     // Catch:{ all -> 0x0034 }
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0030
            r6.selectNextFileToWrite()     // Catch:{ all -> 0x0034 }
        L_0x0030:
            $closeResource(r1, r0)
            return
        L_0x0034:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r7 = move-exception
            $closeResource(r6, r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.persistentlog.PersistentLogFileHandler.writeRawLogsForTest(byte[]):void");
    }
}
