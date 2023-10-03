package com.android.voicemail.impl.mail.store.imap;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.mail.FixedLengthInputStream;
import com.android.voicemail.impl.mail.TempDirectory;
import com.android.voicemail.impl.mail.utils.LogUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.p011io.IOUtils;

public class ImapTempFileLiteral extends ImapString {
    final File file = File.createTempFile("imap", ".tmp", TempDirectory.getTempDirectory());
    private final int size;

    ImapTempFileLiteral(FixedLengthInputStream fixedLengthInputStream) throws IOException {
        this.size = fixedLengthInputStream.getLength();
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        IOUtils.copy(fixedLengthInputStream, fileOutputStream);
        fileOutputStream.close();
    }

    public void destroy() {
        try {
            if (!isDestroyed() && this.file.exists()) {
                this.file.delete();
            }
        } catch (RuntimeException e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Failed to remove temp file: ");
            outline13.append(e.getMessage());
            LogUtils.m56w("ImapTempFileLiteral", outline13.toString(), new Object[0]);
        }
        super.destroy();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            destroy();
        } finally {
            super.finalize();
        }
    }

    public InputStream getAsStream() {
        checkNotDestroyed();
        try {
            return new FileInputStream(this.file);
        } catch (FileNotFoundException unused) {
            LogUtils.m56w("ImapTempFileLiteral", "ImapTempFileLiteral: Temp file not found", new Object[0]);
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|(2:9|10)(2:11|12)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getString() {
        /*
            r4 = this;
            java.lang.String r0 = "ImapTempFileLiteral"
            r4.checkNotDestroyed()
            r1 = 0
            r4.checkNotDestroyed()     // Catch:{ IOException -> 0x0046 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0011 }
            java.io.File r4 = r4.file     // Catch:{ FileNotFoundException -> 0x0011 }
            r2.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0011 }
            goto L_0x001f
        L_0x0011:
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x0046 }
            java.lang.String r2 = "ImapTempFileLiteral: Temp file not found"
            com.android.voicemail.impl.mail.utils.LogUtils.m56w(r0, r2, r4)     // Catch:{ IOException -> 0x0046 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0046 }
            byte[] r4 = new byte[r1]     // Catch:{ IOException -> 0x0046 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0046 }
        L_0x001f:
            byte[] r4 = org.apache.commons.p011io.IOUtils.toByteArray(r2)     // Catch:{ IOException -> 0x0046 }
            int r2 = r4.length     // Catch:{ IOException -> 0x0046 }
            r3 = 2097152(0x200000, float:2.938736E-39)
            if (r2 > r3) goto L_0x0040
            java.nio.charset.Charset r2 = com.android.voicemail.impl.mail.utils.Utility.ASCII     // Catch:{ IOException -> 0x0046 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r4)     // Catch:{ IOException -> 0x0046 }
            java.nio.CharBuffer r4 = r2.decode(r4)     // Catch:{ IOException -> 0x0046 }
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x0046 }
            char[] r3 = r4.array()     // Catch:{ IOException -> 0x0046 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x0046 }
            r2.<init>(r3, r1, r4)     // Catch:{ IOException -> 0x0046 }
            return r2
        L_0x0040:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ IOException -> 0x0046 }
            r4.<init>()     // Catch:{ IOException -> 0x0046 }
            throw r4     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            r4 = move-exception
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r4
            java.lang.String r4 = "ImapTempFileLiteral: Error while reading temp file"
            com.android.voicemail.impl.mail.utils.LogUtils.m56w(r0, r4, r2)
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.store.imap.ImapTempFileLiteral.getString():java.lang.String");
    }

    public String toString() {
        return String.format("{%d byte literal(file)}", new Object[]{Integer.valueOf(this.size)});
    }
}
