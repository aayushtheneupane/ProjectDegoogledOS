package com.bumptech.glide.disklrucache;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    private final int appVersion;
    private final Callable<Void> cleanupCallable = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r3 = this;
                com.bumptech.glide.disklrucache.DiskLruCache r0 = com.bumptech.glide.disklrucache.DiskLruCache.this
                monitor-enter(r0)
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.trimToSize()     // Catch:{ all -> 0x0028 }
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                com.bumptech.glide.disklrucache.DiskLruCache r1 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1.rebuildJournal()     // Catch:{ all -> 0x0028 }
                com.bumptech.glide.disklrucache.DiskLruCache r3 = com.bumptech.glide.disklrucache.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                r1 = 0
                int unused = r3.redundantOpCount = r1     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.C07981.call():java.lang.Void");
        }
    };
    /* access modifiers changed from: private */
    public final File directory;
    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DiskLruCacheThreadFactory((C07981) null));
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    private static final class DiskLruCacheThreadFactory implements ThreadFactory {
        /* synthetic */ DiskLruCacheThreadFactory(C07981 r1) {
        }

        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    public final class Editor {
        private boolean committed;
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public final boolean[] written;

        /* synthetic */ Editor(Entry entry2, C07981 r3) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (IOException unused) {
                }
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.completeEdit(this, true);
            this.committed = true;
        }

        public File getFile(int i) throws IOException {
            File file;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor == this) {
                    if (!this.entry.readable) {
                        this.written[i] = true;
                    }
                    file = this.entry.dirtyFiles[i];
                    if (!DiskLruCache.this.directory.exists()) {
                        DiskLruCache.this.directory.mkdirs();
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return file;
        }
    }

    private final class Entry {
        File[] cleanFiles;
        /* access modifiers changed from: private */
        public Editor currentEditor;
        File[] dirtyFiles;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        /* synthetic */ Entry(String str, C07981 r8) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                sb.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            String valueOf = String.valueOf(Arrays.toString(strArr));
            throw new IOException(valueOf.length() != 0 ? "unexpected journal line: ".concat(valueOf) : new String("unexpected journal line: "));
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.valueCount) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.lengths[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        invalidLengths(strArr);
                        throw null;
                    }
                }
                return;
            }
            invalidLengths(strArr);
            throw null;
        }

        public String getLengths() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.lengths) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }
    }

    public final class Value {
        private final File[] files;

        /* synthetic */ Value(DiskLruCache diskLruCache, String str, long j, File[] fileArr, long[] jArr, C07981 r7) {
            this.files = fileArr;
        }

        public File getFile(int i) {
            return this.files[i];
        }
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        File file2 = file;
        this.directory = file2;
        this.appVersion = i;
        this.journalFile = new File(file2, "journal");
        this.journalFileTmp = new File(file2, "journal.tmp");
        this.journalFileBackup = new File(file2, "journal.bkp");
        this.valueCount = i2;
        this.maxSize = j;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0109, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(com.bumptech.glide.disklrucache.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = r10.entry     // Catch:{ all -> 0x0110 }
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x0110 }
            if (r1 != r10) goto L_0x010a
            r1 = 0
            if (r11 == 0) goto L_0x004f
            boolean r2 = r0.readable     // Catch:{ all -> 0x0110 }
            if (r2 != 0) goto L_0x004f
            r2 = r1
        L_0x0015:
            int r3 = r9.valueCount     // Catch:{ all -> 0x0110 }
            if (r2 >= r3) goto L_0x004f
            boolean[] r3 = r10.written     // Catch:{ all -> 0x0110 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0110 }
            if (r3 == 0) goto L_0x0033
            java.io.File[] r3 = r0.dirtyFiles     // Catch:{ all -> 0x0110 }
            r3 = r3[r2]     // Catch:{ all -> 0x0110 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0110 }
            if (r3 != 0) goto L_0x0030
            r10.abort()     // Catch:{ all -> 0x0110 }
            monitor-exit(r9)
            return
        L_0x0030:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0033:
            r10.abort()     // Catch:{ all -> 0x0110 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0110 }
            r11 = 61
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0110 }
            r0.<init>(r11)     // Catch:{ all -> 0x0110 }
            java.lang.String r11 = "Newly created entry didn't create value for index "
            r0.append(r11)     // Catch:{ all -> 0x0110 }
            r0.append(r2)     // Catch:{ all -> 0x0110 }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x0110 }
            r10.<init>(r11)     // Catch:{ all -> 0x0110 }
            throw r10     // Catch:{ all -> 0x0110 }
        L_0x004f:
            int r10 = r9.valueCount     // Catch:{ all -> 0x0110 }
            if (r1 >= r10) goto L_0x0083
            java.io.File[] r10 = r0.dirtyFiles     // Catch:{ all -> 0x0110 }
            r10 = r10[r1]     // Catch:{ all -> 0x0110 }
            if (r11 == 0) goto L_0x007d
            boolean r2 = r10.exists()     // Catch:{ all -> 0x0110 }
            if (r2 == 0) goto L_0x0080
            java.io.File[] r2 = r0.cleanFiles     // Catch:{ all -> 0x0110 }
            r2 = r2[r1]     // Catch:{ all -> 0x0110 }
            r10.renameTo(r2)     // Catch:{ all -> 0x0110 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0110 }
            r3 = r10[r1]     // Catch:{ all -> 0x0110 }
            long r5 = r2.length()     // Catch:{ all -> 0x0110 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0110 }
            r10[r1] = r5     // Catch:{ all -> 0x0110 }
            long r7 = r9.size     // Catch:{ all -> 0x0110 }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.size = r7     // Catch:{ all -> 0x0110 }
            goto L_0x0080
        L_0x007d:
            deleteIfExists(r10)     // Catch:{ all -> 0x0110 }
        L_0x0080:
            int r1 = r1 + 1
            goto L_0x004f
        L_0x0083:
            int r10 = r9.redundantOpCount     // Catch:{ all -> 0x0110 }
            r1 = 1
            int r10 = r10 + r1
            r9.redundantOpCount = r10     // Catch:{ all -> 0x0110 }
            r10 = 0
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.currentEditor = r10     // Catch:{ all -> 0x0110 }
            boolean r10 = r0.readable     // Catch:{ all -> 0x0110 }
            r10 = r10 | r11
            r2 = 10
            r3 = 32
            if (r10 == 0) goto L_0x00cb
            boolean unused = r0.readable = r1     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r1 = "CLEAN"
            r10.append(r1)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            r10.append(r3)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r1 = r0.key     // Catch:{ all -> 0x0110 }
            r10.append(r1)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r1 = r0.getLengths()     // Catch:{ all -> 0x0110 }
            r10.append(r1)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            r10.append(r2)     // Catch:{ all -> 0x0110 }
            if (r11 == 0) goto L_0x00ee
            long r10 = r9.nextSequenceNumber     // Catch:{ all -> 0x0110 }
            r1 = 1
            long r1 = r1 + r10
            r9.nextSequenceNumber = r1     // Catch:{ all -> 0x0110 }
            long unused = r0.sequenceNumber = r10     // Catch:{ all -> 0x0110 }
            goto L_0x00ee
        L_0x00cb:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r10 = r9.lruEntries     // Catch:{ all -> 0x0110 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0110 }
            r10.remove(r11)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r11 = "REMOVE"
            r10.append(r11)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            r10.append(r3)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0110 }
            r10.append(r11)     // Catch:{ all -> 0x0110 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            r10.append(r2)     // Catch:{ all -> 0x0110 }
        L_0x00ee:
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0110 }
            r10.flush()     // Catch:{ all -> 0x0110 }
            long r10 = r9.size     // Catch:{ all -> 0x0110 }
            long r0 = r9.maxSize     // Catch:{ all -> 0x0110 }
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 > 0) goto L_0x0101
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x0110 }
            if (r10 == 0) goto L_0x0108
        L_0x0101:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.executorService     // Catch:{ all -> 0x0110 }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.cleanupCallable     // Catch:{ all -> 0x0110 }
            r10.submit(r11)     // Catch:{ all -> 0x0110 }
        L_0x0108:
            monitor-exit(r9)
            return
        L_0x010a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0110 }
            r10.<init>()     // Catch:{ all -> 0x0110 }
            throw r10     // Catch:{ all -> 0x0110 }
        L_0x0110:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.completeEdit(com.bumptech.glide.disklrucache.DiskLruCache$Editor, boolean):void");
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    renameTo(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    return diskLruCache;
                } catch (IOException e) {
                    PrintStream printStream = System.out;
                    String valueOf = String.valueOf(file);
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(message, valueOf.length() + 36));
                    sb.append("DiskLruCache ");
                    sb.append(valueOf);
                    sb.append(" is corrupt: ");
                    sb.append(message);
                    sb.append(", removing");
                    printStream.println(sb.toString());
                    diskLruCache.delete();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
            diskLruCache2.rebuildJournal();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                Editor unused = next.currentEditor = null;
                while (i < this.valueCount) {
                    deleteIfExists(next.cleanFiles[i]);
                    deleteIfExists(next.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|(1:19)(1:20)|(3:21|22|36)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.redundantOpCount = r0 - r8.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        if (r1.hasUnterminatedLine() != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        rebuildJournal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        r8.journalWriter = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r8.journalFile, true), com.bumptech.glide.disklrucache.Util.US_ASCII));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008d, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005f */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x008e=Splitter:B:26:0x008e, B:16:0x005f=Splitter:B:16:0x005f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournal() throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r0 = ", "
            com.bumptech.glide.disklrucache.StrictLineReader r1 = new com.bumptech.glide.disklrucache.StrictLineReader
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.File r3 = r8.journalFile
            r2.<init>(r3)
            java.nio.charset.Charset r3 = com.bumptech.glide.disklrucache.Util.US_ASCII
            r1.<init>(r2, r3)
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x00e1 }
            java.lang.String r3 = r1.readLine()     // Catch:{ all -> 0x00e1 }
            java.lang.String r4 = r1.readLine()     // Catch:{ all -> 0x00e1 }
            java.lang.String r5 = r1.readLine()     // Catch:{ all -> 0x00e1 }
            java.lang.String r6 = r1.readLine()     // Catch:{ all -> 0x00e1 }
            java.lang.String r7 = "libcore.io.DiskLruCache"
            boolean r7 = r7.equals(r2)     // Catch:{ all -> 0x00e1 }
            if (r7 == 0) goto L_0x008e
            java.lang.String r7 = "1"
            boolean r7 = r7.equals(r3)     // Catch:{ all -> 0x00e1 }
            if (r7 == 0) goto L_0x008e
            int r7 = r8.appVersion     // Catch:{ all -> 0x00e1 }
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x00e1 }
            boolean r4 = r7.equals(r4)     // Catch:{ all -> 0x00e1 }
            if (r4 == 0) goto L_0x008e
            int r4 = r8.valueCount     // Catch:{ all -> 0x00e1 }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x00e1 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00e1 }
            if (r4 == 0) goto L_0x008e
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x00e1 }
            if (r4 == 0) goto L_0x008e
            r0 = 0
        L_0x0055:
            java.lang.String r2 = r1.readLine()     // Catch:{ EOFException -> 0x005f }
            r8.readJournalLine(r2)     // Catch:{ EOFException -> 0x005f }
            int r0 = r0 + 1
            goto L_0x0055
        L_0x005f:
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r2 = r8.lruEntries     // Catch:{ all -> 0x00e1 }
            int r2 = r2.size()     // Catch:{ all -> 0x00e1 }
            int r0 = r0 - r2
            r8.redundantOpCount = r0     // Catch:{ all -> 0x00e1 }
            boolean r0 = r1.hasUnterminatedLine()     // Catch:{ all -> 0x00e1 }
            if (r0 == 0) goto L_0x0072
            r8.rebuildJournal()     // Catch:{ all -> 0x00e1 }
            goto L_0x0088
        L_0x0072:
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x00e1 }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00e1 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x00e1 }
            java.io.File r4 = r8.journalFile     // Catch:{ all -> 0x00e1 }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x00e1 }
            java.nio.charset.Charset r4 = com.bumptech.glide.disklrucache.Util.US_ASCII     // Catch:{ all -> 0x00e1 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00e1 }
            r0.<init>(r2)     // Catch:{ all -> 0x00e1 }
            r8.journalWriter = r0     // Catch:{ all -> 0x00e1 }
        L_0x0088:
            r1.close()     // Catch:{ RuntimeException -> 0x008c, Exception -> 0x008b }
        L_0x008b:
            return
        L_0x008c:
            r8 = move-exception
            throw r8
        L_0x008e:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x00e1 }
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00e1 }
            int r4 = r4.length()     // Catch:{ all -> 0x00e1 }
            int r4 = r4 + 35
            java.lang.String r7 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00e1 }
            int r7 = r7.length()     // Catch:{ all -> 0x00e1 }
            int r4 = r4 + r7
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00e1 }
            int r7 = r7.length()     // Catch:{ all -> 0x00e1 }
            int r4 = r4 + r7
            java.lang.String r7 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00e1 }
            int r7 = r7.length()     // Catch:{ all -> 0x00e1 }
            int r4 = r4 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e1 }
            r7.<init>(r4)     // Catch:{ all -> 0x00e1 }
            java.lang.String r4 = "unexpected journal header: ["
            r7.append(r4)     // Catch:{ all -> 0x00e1 }
            r7.append(r2)     // Catch:{ all -> 0x00e1 }
            r7.append(r0)     // Catch:{ all -> 0x00e1 }
            r7.append(r3)     // Catch:{ all -> 0x00e1 }
            r7.append(r0)     // Catch:{ all -> 0x00e1 }
            r7.append(r5)     // Catch:{ all -> 0x00e1 }
            r7.append(r0)     // Catch:{ all -> 0x00e1 }
            r7.append(r6)     // Catch:{ all -> 0x00e1 }
            java.lang.String r0 = "]"
            r7.append(r0)     // Catch:{ all -> 0x00e1 }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x00e1 }
            r8.<init>(r0)     // Catch:{ all -> 0x00e1 }
            throw r8     // Catch:{ all -> 0x00e1 }
        L_0x00e1:
            r8 = move-exception
            r1.close()     // Catch:{ RuntimeException -> 0x00e6, Exception -> 0x00e5 }
        L_0x00e5:
            throw r8
        L_0x00e6:
            r8 = move-exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.readJournal():void");
    }

    private void readJournalLine(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException(str.length() != 0 ? "unexpected journal line: ".concat(str) : new String("unexpected journal line: "));
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            str2 = str.substring(i);
            if (indexOf == 6 && str.startsWith("REMOVE")) {
                this.lruEntries.remove(str2);
                return;
            }
        } else {
            str2 = str.substring(i, indexOf2);
        }
        Entry entry = this.lruEntries.get(str2);
        if (entry == null) {
            entry = new Entry(str2, (C07981) null);
            this.lruEntries.put(str2, entry);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            boolean unused = entry.readable = true;
            Editor unused2 = entry.currentEditor = null;
            entry.setLengths(split);
        } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
            Editor unused3 = entry.currentEditor = new Editor(entry, (C07981) null);
        } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith("READ")) {
            throw new IOException(str.length() != 0 ? "unexpected journal line: ".concat(str) : new String("unexpected journal line: "));
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.appVersion));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.valueCount));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry next : this.lruEntries.values()) {
                if (next.currentEditor != null) {
                    String access$1200 = next.key;
                    StringBuilder sb = new StringBuilder(String.valueOf(access$1200).length() + 7);
                    sb.append("DIRTY ");
                    sb.append(access$1200);
                    sb.append(10);
                    bufferedWriter.write(sb.toString());
                } else {
                    String access$12002 = next.key;
                    String lengths = next.getLengths();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(access$12002).length() + 7 + String.valueOf(lengths).length());
                    sb2.append("CLEAN ");
                    sb2.append(access$12002);
                    sb2.append(lengths);
                    sb2.append(10);
                    bufferedWriter.write(sb2.toString());
                }
            }
            bufferedWriter.close();
            if (this.journalFile.exists()) {
                renameTo(this.journalFile, this.journalFileBackup, true);
            }
            renameTo(this.journalFileTmp, this.journalFile, false);
            this.journalFileBackup.delete();
            this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void renameTo(File file, File file2, boolean z) throws IOException {
        if (z) {
            deleteIfExists(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    public synchronized Value get(String str) throws IOException {
        checkNotClosed();
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return null;
        }
        if (!entry.readable) {
            return null;
        }
        for (File exists : entry.cleanFiles) {
            if (!exists.exists()) {
                return null;
            }
        }
        this.redundantOpCount++;
        this.journalWriter.append("READ");
        this.journalWriter.append(' ');
        this.journalWriter.append(str);
        this.journalWriter.append(10);
        if (journalRebuildRequired()) {
            this.executorService.submit(this.cleanupCallable);
        }
        return new Value(this, str, entry.sequenceNumber, entry.cleanFiles, entry.lengths, (C07981) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0096, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0098, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.checkNotClosed()     // Catch:{ all -> 0x0099 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0099 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ all -> 0x0099 }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0099 }
            r1 = 0
            if (r0 == 0) goto L_0x0097
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x0017
            goto L_0x0097
        L_0x0017:
            int r2 = r6.valueCount     // Catch:{ all -> 0x0099 }
            if (r1 >= r2) goto L_0x0063
            java.io.File[] r2 = r0.cleanFiles     // Catch:{ all -> 0x0099 }
            r2 = r2[r1]     // Catch:{ all -> 0x0099 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x004d
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x002c
            goto L_0x004d
        L_0x002c:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0099 }
            int r1 = r0.length()     // Catch:{ all -> 0x0099 }
            int r1 = r1 + 17
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r2.<init>(r1)     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = "failed to delete "
            r2.append(r1)     // Catch:{ all -> 0x0099 }
            r2.append(r0)     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0099 }
            r7.<init>(r0)     // Catch:{ all -> 0x0099 }
            throw r7     // Catch:{ all -> 0x0099 }
        L_0x004d:
            long r2 = r6.size     // Catch:{ all -> 0x0099 }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x0099 }
            r4 = r4[r1]     // Catch:{ all -> 0x0099 }
            long r2 = r2 - r4
            r6.size = r2     // Catch:{ all -> 0x0099 }
            long[] r2 = r0.lengths     // Catch:{ all -> 0x0099 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0099 }
            int r1 = r1 + 1
            goto L_0x0017
        L_0x0063:
            int r0 = r6.redundantOpCount     // Catch:{ all -> 0x0099 }
            r1 = 1
            int r0 = r0 + r1
            r6.redundantOpCount = r0     // Catch:{ all -> 0x0099 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "REMOVE"
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0099 }
            r2 = 32
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0099 }
            r0.append(r7)     // Catch:{ all -> 0x0099 }
            java.io.Writer r0 = r6.journalWriter     // Catch:{ all -> 0x0099 }
            r2 = 10
            r0.append(r2)     // Catch:{ all -> 0x0099 }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0099 }
            r0.remove(r7)     // Catch:{ all -> 0x0099 }
            boolean r7 = r6.journalRebuildRequired()     // Catch:{ all -> 0x0099 }
            if (r7 == 0) goto L_0x0095
            java.util.concurrent.ThreadPoolExecutor r7 = r6.executorService     // Catch:{ all -> 0x0099 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r6.cleanupCallable     // Catch:{ all -> 0x0099 }
            r7.submit(r0)     // Catch:{ all -> 0x0099 }
        L_0x0095:
            monitor-exit(r6)
            return r1
        L_0x0097:
            monitor-exit(r6)
            return r1
        L_0x0099:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.remove(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.bumptech.glide.disklrucache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x005d }
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = (com.bumptech.glide.disklrucache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x005d }
            r1 = -1
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            long r3 = r0.sequenceNumber     // Catch:{ all -> 0x005d }
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r5)
            return r2
        L_0x001f:
            if (r0 != 0) goto L_0x002c
            com.bumptech.glide.disklrucache.DiskLruCache$Entry r0 = new com.bumptech.glide.disklrucache.DiskLruCache$Entry     // Catch:{ all -> 0x005d }
            r0.<init>(r6, r2)     // Catch:{ all -> 0x005d }
            java.util.LinkedHashMap<java.lang.String, com.bumptech.glide.disklrucache.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x005d }
            r7.put(r6, r0)     // Catch:{ all -> 0x005d }
            goto L_0x0034
        L_0x002c:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x0034
            monitor-exit(r5)
            return r2
        L_0x0034:
            com.bumptech.glide.disklrucache.DiskLruCache$Editor r7 = new com.bumptech.glide.disklrucache.DiskLruCache$Editor     // Catch:{ all -> 0x005d }
            r7.<init>(r0, r2)     // Catch:{ all -> 0x005d }
            com.bumptech.glide.disklrucache.DiskLruCache.Editor unused = r0.currentEditor = r7     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "DIRTY"
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x005d }
            r0 = 32
            r8.append(r0)     // Catch:{ all -> 0x005d }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x005d }
            r8.append(r6)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x005d }
            r8 = 10
            r6.append(r8)     // Catch:{ all -> 0x005d }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x005d }
            r6.flush()     // Catch:{ all -> 0x005d }
            monitor-exit(r5)
            return r7
        L_0x005d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.edit(java.lang.String, long):com.bumptech.glide.disklrucache.DiskLruCache$Editor");
    }
}
