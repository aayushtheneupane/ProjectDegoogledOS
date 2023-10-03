package com.android.systemui.havoc.header;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.havoc.header.StatusBarHeaderMachine;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class FileHeaderProvider implements StatusBarHeaderMachine.IStatusBarHeaderProvider {
    private Context mContext;
    private Drawable mImage;

    public void disableProvider() {
    }

    public String getName() {
        return "file";
    }

    public FileHeaderProvider(Context context) {
        this.mContext = context;
    }

    public void settingsChanged(Uri uri) {
        String stringForUser;
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) == 1) {
            z = true;
        }
        if (!(uri == null || !uri.equals(Settings.System.getUriFor("status_bar_file_header_image")) || (stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "status_bar_file_header_image", -2)) == null)) {
            saveHeaderImage(Uri.parse(stringForUser));
        }
        if (z) {
            loadHeaderImage();
        }
    }

    public void enableProvider() {
        settingsChanged((Uri) null);
    }

    private void saveHeaderImage(Uri uri) {
        try {
            InputStream openInputStream = this.mContext.getContentResolver().openInputStream(uri);
            File file = new File(this.mContext.getFilesDir(), "custom_file_header_image");
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    return;
                }
            }
        } catch (IOException unused) {
            Log.e("FileHeaderProvider", "Save header image failed  " + uri);
        }
    }

    private void loadHeaderImage() {
        this.mImage = null;
        File file = new File(this.mContext.getFilesDir(), "custom_file_header_image");
        if (file.exists()) {
            this.mImage = new BitmapDrawable(this.mContext.getResources(), BitmapFactory.decodeFile(file.getAbsolutePath()));
        }
    }

    public Drawable getCurrent(Calendar calendar) {
        return this.mImage;
    }
}
