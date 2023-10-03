package com.android.voicemail.impl.mail.internet;

import android.util.Base64OutputStream;
import com.android.voicemail.impl.mail.Body;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.TempDirectory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.p011io.IOUtils;

public class BinaryTempFileBody implements Body {
    /* access modifiers changed from: private */
    public File file;

    class BinaryTempFileBodyInputStream extends FilterInputStream {
        public BinaryTempFileBodyInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public void close() throws IOException {
            super.close();
            BinaryTempFileBody.this.file.delete();
        }
    }

    public OutputStream getOutputStream() throws IOException {
        this.file = File.createTempFile("body", (String) null, TempDirectory.getTempDirectory());
        this.file.deleteOnExit();
        return new FileOutputStream(this.file);
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        try {
            BinaryTempFileBodyInputStream binaryTempFileBodyInputStream = new BinaryTempFileBodyInputStream(new FileInputStream(this.file));
            Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, 20);
            IOUtils.copy(binaryTempFileBodyInputStream, base64OutputStream);
            base64OutputStream.close();
            this.file.delete();
            binaryTempFileBodyInputStream.close();
        } catch (IOException e) {
            throw new MessagingException(0, "Unable to open body", e);
        }
    }
}
