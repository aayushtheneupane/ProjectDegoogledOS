package com.android.contacts.vcard;

import android.content.ComponentName;
import android.os.IBinder;
import androidx.core.content.FileProvider;
import com.android.contacts.R;
import com.android.contacts.vcard.VCardService;
import com.android.contactsbind.FeedbackHelper;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShareVCardActivity extends ExportVCardActivity {
    private final long A_DAY_IN_MILLIS = 86400000;
    private final String EXPORT_FILE_PREFIX = "vcards_";

    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mConnected = true;
        this.mService = ((VCardService.MyBinder) iBinder).getService();
        clearExportFiles();
        File localFile = getLocalFile();
        try {
            localFile.createNewFile();
            this.mService.handleExportRequest(new ExportRequest(FileProvider.getUriForFile(this, getString(R.string.contacts_file_provider_authority), localFile)), new NotificationImportExportListener(this));
            finish();
        } catch (IOException e) {
            FeedbackHelper.sendFeedback(this, "VCardShare", "Failed to create .vcf file", e);
            finish();
        }
    }

    private void clearExportFiles() {
        for (File file : getCacheDir().listFiles()) {
            long currentTimeMillis = System.currentTimeMillis() - file.lastModified();
            if (file.getName().startsWith("vcards_") && currentTimeMillis > 86400000) {
                file.delete();
            }
        }
    }

    private File getLocalFile() {
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()).toString();
        return new File(getCacheDir(), "vcards_" + str + ".vcf");
    }
}
