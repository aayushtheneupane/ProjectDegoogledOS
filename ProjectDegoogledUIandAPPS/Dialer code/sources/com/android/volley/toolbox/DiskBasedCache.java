package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiskBasedCache implements Cache {
    static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75f, true);
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize = 0;

    public DiskBasedCache(File file) {
        this.mRootDirectory = file;
        this.mMaxCacheSizeInBytes = 5242880;
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(String.valueOf(str.substring(0, length).hashCode()));
        outline13.append(String.valueOf(str.substring(length).hashCode()));
        return outline13.toString();
    }

    private void pruneIfNeeded() {
        if (this.mTotalSize >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.m61v("Pruning old cache entries.", new Object[0]);
            }
            long j = this.mTotalSize;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> it = this.mEntries.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                CacheHeader cacheHeader = (CacheHeader) it.next().getValue();
                if (getFileForKey(cacheHeader.key).delete()) {
                    this.mTotalSize -= cacheHeader.size;
                } else {
                    String str = cacheHeader.key;
                    VolleyLog.m58d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
                }
                it.remove();
                i++;
                if (((float) this.mTotalSize) < ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                    break;
                }
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.m61v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.mTotalSize - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.size;
        } else {
            this.mTotalSize = (cacheHeader.size - this.mEntries.get(str).size) + this.mTotalSize;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static int readInt(InputStream inputStream) throws IOException {
        return (read(inputStream) << 24) | (read(inputStream) << 0) | 0 | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    static long readLong(InputStream inputStream) throws IOException {
        return ((((long) read(inputStream)) & 255) << 0) | 0 | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((255 & ((long) read(inputStream))) << 56);
    }

    static String readString(CountingInputStream countingInputStream) throws IOException {
        return new String(streamToBytes(countingInputStream, readLong(countingInputStream)), "UTF-8");
    }

    static byte[] streamToBytes(CountingInputStream countingInputStream, long j) throws IOException {
        long bytesRemaining = countingInputStream.bytesRemaining();
        if (j >= 0 && j <= bytesRemaining) {
            int i = (int) j;
            if (((long) i) == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(countingInputStream).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + bytesRemaining);
    }

    static void writeInt(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    /* access modifiers changed from: package-private */
    public InputStream createInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    /* access modifiers changed from: package-private */
    public OutputStream createOutputStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    public synchronized Cache.Entry get(String str) {
        CountingInputStream countingInputStream;
        CacheHeader cacheHeader = this.mEntries.get(str);
        if (cacheHeader == null) {
            return null;
        }
        File fileForKey = getFileForKey(str);
        try {
            countingInputStream = new CountingInputStream(new BufferedInputStream(createInputStream(fileForKey)), fileForKey.length());
            CacheHeader readHeader = CacheHeader.readHeader(countingInputStream);
            if (!TextUtils.equals(str, readHeader.key)) {
                VolleyLog.m58d("%s: key=%s, found=%s", fileForKey.getAbsolutePath(), str, readHeader.key);
                CacheHeader remove = this.mEntries.remove(str);
                if (remove != null) {
                    this.mTotalSize -= remove.size;
                }
                countingInputStream.close();
                return null;
            }
            Cache.Entry cacheEntry = cacheHeader.toCacheEntry(streamToBytes(countingInputStream, countingInputStream.bytesRemaining()));
            countingInputStream.close();
            return cacheEntry;
        } catch (IOException e) {
            VolleyLog.m58d("%s: %s", fileForKey.getAbsolutePath(), e.toString());
            remove(str);
            return null;
        } catch (Throwable th) {
            countingInputStream.close();
            throw th;
        }
    }

    public File getFileForKey(String str) {
        return new File(this.mRootDirectory, getFilenameForKey(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0059 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r9 = this;
            monitor-enter(r9)
            java.io.File r0 = r9.mRootDirectory     // Catch:{ all -> 0x0061 }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0061 }
            r1 = 0
            if (r0 != 0) goto L_0x0024
            java.io.File r0 = r9.mRootDirectory     // Catch:{ all -> 0x0061 }
            boolean r0 = r0.mkdirs()     // Catch:{ all -> 0x0061 }
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Unable to create cache dir %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0061 }
            java.io.File r3 = r9.mRootDirectory     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x0061 }
            r2[r1] = r3     // Catch:{ all -> 0x0061 }
            com.android.volley.VolleyLog.m59e(r0, r2)     // Catch:{ all -> 0x0061 }
        L_0x0022:
            monitor-exit(r9)
            return
        L_0x0024:
            java.io.File r0 = r9.mRootDirectory     // Catch:{ all -> 0x0061 }
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x0061 }
            if (r0 != 0) goto L_0x002e
            monitor-exit(r9)
            return
        L_0x002e:
            int r2 = r0.length     // Catch:{ all -> 0x0061 }
        L_0x002f:
            if (r1 >= r2) goto L_0x005f
            r3 = r0[r1]     // Catch:{ all -> 0x0061 }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0059 }
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r6 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x0059 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0059 }
            java.io.InputStream r8 = r9.createInputStream(r3)     // Catch:{ IOException -> 0x0059 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0059 }
            r6.<init>(r7, r4)     // Catch:{ IOException -> 0x0059 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r7 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r6)     // Catch:{ all -> 0x0054 }
            r7.size = r4     // Catch:{ all -> 0x0054 }
            java.lang.String r4 = r7.key     // Catch:{ all -> 0x0054 }
            r9.putEntry(r4, r7)     // Catch:{ all -> 0x0054 }
            r6.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005c
        L_0x0054:
            r4 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0059 }
            throw r4     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            r3.delete()     // Catch:{ all -> 0x0061 }
        L_0x005c:
            int r1 = r1 + 1
            goto L_0x002f
        L_0x005f:
            monitor-exit(r9)
            return
        L_0x0061:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        if (r0.delete() == false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006c, code lost:
        com.android.volley.VolleyLog.m58d("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r8, com.android.volley.Cache.Entry r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.mTotalSize     // Catch:{ all -> 0x007b }
            byte[] r2 = r9.data     // Catch:{ all -> 0x007b }
            int r2 = r2.length     // Catch:{ all -> 0x007b }
            long r2 = (long) r2     // Catch:{ all -> 0x007b }
            long r0 = r0 + r2
            int r2 = r7.mMaxCacheSizeInBytes     // Catch:{ all -> 0x007b }
            long r2 = (long) r2     // Catch:{ all -> 0x007b }
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0020
            byte[] r0 = r9.data     // Catch:{ all -> 0x007b }
            int r0 = r0.length     // Catch:{ all -> 0x007b }
            float r0 = (float) r0     // Catch:{ all -> 0x007b }
            int r1 = r7.mMaxCacheSizeInBytes     // Catch:{ all -> 0x007b }
            float r1 = (float) r1
            r2 = 1063675494(0x3f666666, float:0.9)
            float r1 = r1 * r2
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0020
            monitor-exit(r7)
            return
        L_0x0020:
            java.io.File r0 = r7.getFileForKey(r8)     // Catch:{ all -> 0x007b }
            r1 = 0
            r2 = 1
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0066 }
            java.io.OutputStream r4 = r7.createOutputStream(r0)     // Catch:{ IOException -> 0x0066 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0066 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = new com.android.volley.toolbox.DiskBasedCache$CacheHeader     // Catch:{ IOException -> 0x0066 }
            r4.<init>(r8, r9)     // Catch:{ IOException -> 0x0066 }
            boolean r5 = r4.writeHeader(r3)     // Catch:{ IOException -> 0x0066 }
            if (r5 == 0) goto L_0x0050
            byte[] r9 = r9.data     // Catch:{ IOException -> 0x0066 }
            r3.write(r9)     // Catch:{ IOException -> 0x0066 }
            r3.close()     // Catch:{ IOException -> 0x0066 }
            long r5 = r0.length()     // Catch:{ IOException -> 0x0066 }
            r4.size = r5     // Catch:{ IOException -> 0x0066 }
            r7.putEntry(r8, r4)     // Catch:{ IOException -> 0x0066 }
            r7.pruneIfNeeded()     // Catch:{ IOException -> 0x0066 }
            monitor-exit(r7)
            return
        L_0x0050:
            r3.close()     // Catch:{ IOException -> 0x0066 }
            java.lang.String r8 = "Failed to write header for %s"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0066 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0066 }
            r9[r1] = r3     // Catch:{ IOException -> 0x0066 }
            com.android.volley.VolleyLog.m58d(r8, r9)     // Catch:{ IOException -> 0x0066 }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ IOException -> 0x0066 }
            r8.<init>()     // Catch:{ IOException -> 0x0066 }
            throw r8     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            boolean r8 = r0.delete()     // Catch:{ all -> 0x007b }
            if (r8 != 0) goto L_0x0079
            java.lang.String r8 = "Could not clean up file %s"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x007b }
            r9[r1] = r0     // Catch:{ all -> 0x007b }
            com.android.volley.VolleyLog.m58d(r8, r9)     // Catch:{ all -> 0x007b }
        L_0x0079:
            monitor-exit(r7)
            return
        L_0x007b:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.put(java.lang.String, com.android.volley.Cache$Entry):void");
    }

    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        CacheHeader remove = this.mEntries.remove(str);
        if (remove != null) {
            this.mTotalSize -= remove.size;
        }
        if (!delete) {
            VolleyLog.m58d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
        }
    }

    static class CountingInputStream extends FilterInputStream {
        private long bytesRead;
        private final long length;

        CountingInputStream(InputStream inputStream, long j) {
            super(inputStream);
            this.length = j;
        }

        /* access modifiers changed from: package-private */
        public long bytesRead() {
            return this.bytesRead;
        }

        /* access modifiers changed from: package-private */
        public long bytesRemaining() {
            return this.length - this.bytesRead;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.bytesRead += (long) read;
            }
            return read;
        }
    }

    static class CacheHeader {
        final List<Header> allResponseHeaders;
        final String etag;
        final String key;
        final long lastModified;
        final long serverDate;
        long size;
        final long softTtl;
        final long ttl;

        private CacheHeader(String str, String str2, long j, long j2, long j3, long j4, List<Header> list) {
            this.key = str;
            this.etag = "".equals(str2) ? null : str2;
            this.serverDate = j;
            this.lastModified = j2;
            this.ttl = j3;
            this.softTtl = j4;
            this.allResponseHeaders = list;
        }

        static CacheHeader readHeader(CountingInputStream countingInputStream) throws IOException {
            if (DiskBasedCache.readInt(countingInputStream) == 538247942) {
                String readString = DiskBasedCache.readString(countingInputStream);
                String readString2 = DiskBasedCache.readString(countingInputStream);
                long readLong = DiskBasedCache.readLong(countingInputStream);
                long readLong2 = DiskBasedCache.readLong(countingInputStream);
                long readLong3 = DiskBasedCache.readLong(countingInputStream);
                long readLong4 = DiskBasedCache.readLong(countingInputStream);
                int readInt = DiskBasedCache.readInt(countingInputStream);
                if (readInt >= 0) {
                    List emptyList = readInt == 0 ? Collections.emptyList() : new ArrayList();
                    for (int i = 0; i < readInt; i++) {
                        emptyList.add(new Header(DiskBasedCache.readString(countingInputStream).intern(), DiskBasedCache.readString(countingInputStream).intern()));
                    }
                    return new CacheHeader(readString, readString2, readLong, readLong2, readLong3, readLong4, emptyList);
                }
                throw new IOException(GeneratedOutlineSupport.outline5("readHeaderList size=", readInt));
            }
            throw new IOException();
        }

        /* access modifiers changed from: package-private */
        public Cache.Entry toCacheEntry(byte[] bArr) {
            Cache.Entry entry = new Cache.Entry();
            entry.data = bArr;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.lastModified = this.lastModified;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            List<Header> list = this.allResponseHeaders;
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (Header next : list) {
                treeMap.put(next.getName(), next.getValue());
            }
            entry.responseHeaders = treeMap;
            entry.allResponseHeaders = Collections.unmodifiableList(this.allResponseHeaders);
            return entry;
        }

        /* access modifiers changed from: package-private */
        public boolean writeHeader(OutputStream outputStream) {
            try {
                DiskBasedCache.writeInt(outputStream, 538247942);
                DiskBasedCache.writeString(outputStream, this.key);
                DiskBasedCache.writeString(outputStream, this.etag == null ? "" : this.etag);
                DiskBasedCache.writeLong(outputStream, this.serverDate);
                DiskBasedCache.writeLong(outputStream, this.lastModified);
                DiskBasedCache.writeLong(outputStream, this.ttl);
                DiskBasedCache.writeLong(outputStream, this.softTtl);
                List<Header> list = this.allResponseHeaders;
                if (list != null) {
                    DiskBasedCache.writeInt(outputStream, list.size());
                    for (Header next : list) {
                        DiskBasedCache.writeString(outputStream, next.getName());
                        DiskBasedCache.writeString(outputStream, next.getValue());
                    }
                } else {
                    DiskBasedCache.writeInt(outputStream, 0);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.m58d("%s", e.toString());
                return false;
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        CacheHeader(java.lang.String r14, com.android.volley.Cache.Entry r15) {
            /*
                r13 = this;
                java.lang.String r2 = r15.etag
                long r3 = r15.serverDate
                long r5 = r15.lastModified
                long r7 = r15.ttl
                long r9 = r15.softTtl
                java.util.List<com.android.volley.Header> r0 = r15.allResponseHeaders
                if (r0 == 0) goto L_0x0010
            L_0x000e:
                r11 = r0
                goto L_0x0044
            L_0x0010:
                java.util.Map<java.lang.String, java.lang.String> r15 = r15.responseHeaders
                java.util.ArrayList r0 = new java.util.ArrayList
                int r1 = r15.size()
                r0.<init>(r1)
                java.util.Set r15 = r15.entrySet()
                java.util.Iterator r15 = r15.iterator()
            L_0x0023:
                boolean r1 = r15.hasNext()
                if (r1 == 0) goto L_0x000e
                java.lang.Object r1 = r15.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                com.android.volley.Header r11 = new com.android.volley.Header
                java.lang.Object r12 = r1.getKey()
                java.lang.String r12 = (java.lang.String) r12
                java.lang.Object r1 = r1.getValue()
                java.lang.String r1 = (java.lang.String) r1
                r11.<init>(r12, r1)
                r0.add(r11)
                goto L_0x0023
            L_0x0044:
                r0 = r13
                r1 = r14
                r0.<init>(r1, r2, r3, r5, r7, r9, r11)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.CacheHeader.<init>(java.lang.String, com.android.volley.Cache$Entry):void");
        }
    }
}
