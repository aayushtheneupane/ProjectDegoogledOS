package com.android.voicemail.impl.mail;

import android.content.Context;
import java.io.File;

public class TempDirectory {
    private static File tempDirectory;

    public static File getTempDirectory() {
        File file = tempDirectory;
        if (file != null) {
            return file;
        }
        throw new RuntimeException("TempDirectory not set.  If in a unit test, call Email.setTempDirectory(context) in setUp().");
    }

    public static void setTempDirectory(Context context) {
        tempDirectory = context.getCacheDir();
    }
}
