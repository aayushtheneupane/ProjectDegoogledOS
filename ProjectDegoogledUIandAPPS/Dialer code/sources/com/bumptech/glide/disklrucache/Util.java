package com.bumptech.glide.disklrucache;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

final class Util {
    static final Charset US_ASCII = Charset.forName("US-ASCII");

    static {
        Charset.forName("UTF-8");
    }

    static void deleteContents(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    String valueOf = String.valueOf(file2);
                    throw new IOException(GeneratedOutlineSupport.outline4(valueOf.length() + 23, "failed to delete file: ", valueOf));
                }
            }
            return;
        }
        String valueOf2 = String.valueOf(file);
        throw new IOException(GeneratedOutlineSupport.outline4(valueOf2.length() + 26, "not a readable directory: ", valueOf2));
    }
}
