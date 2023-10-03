package com.android.messaging.p041ui.conversation;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import com.android.messaging.R;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1486ya;
import com.android.messaging.util.C1488za;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.N */
public class C1145N extends C1478ua {

    /* renamed from: Od */
    private final List f1827Od = new ArrayList();
    private final Context mContext;

    public C1145N(Context context, Uri uri, String str) {
        this.mContext = context;
        mo7375b(uri, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), this.mContext.getResources().getString(R.string.app_name));
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        for (C1143L l : this.f1827Od) {
            l.f1824WG = C1488za.m3870a(l.uri, C1481w.isImageType(l.contentType) || C1481w.m3830Ea(l.contentType) ? file : externalStoragePublicDirectory, l.contentType);
        }
        return null;
    }

    /* renamed from: b */
    public void mo7375b(Uri uri, String str) {
        this.f1827Od.add(new C1143L(uri, str));
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        String str;
        Void voidR = (Void) obj;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (C1143L l : this.f1827Od) {
            if (l.f1824WG == null) {
                i++;
            } else {
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(l.f1824WG);
                this.mContext.sendBroadcast(intent);
                if (C1481w.isImageType(l.contentType)) {
                    i4++;
                } else if (C1481w.m3830Ea(l.contentType)) {
                    i3++;
                } else {
                    i2++;
                    DownloadManager downloadManager = (DownloadManager) this.mContext.getSystemService("download");
                    File file = new File(l.f1824WG.getPath());
                    if (file.exists()) {
                        downloadManager.addCompletedDownload(file.getName(), this.mContext.getString(R.string.attachment_file_description), true, l.contentType, file.getAbsolutePath(), file.length(), false);
                    }
                }
            }
        }
        if (i > 0) {
            str = this.mContext.getResources().getQuantityString(R.plurals.attachment_save_error, i, new Object[]{Integer.valueOf(i)});
        } else {
            int i5 = R.plurals.attachments_saved;
            if (i2 <= 0) {
                i5 = i3 == 0 ? R.plurals.photos_saved_to_album : i4 == 0 ? R.plurals.videos_saved_to_album : R.plurals.attachments_saved_to_album;
            } else if (i4 + i3 == 0) {
                i5 = R.plurals.attachments_saved_to_downloads;
            }
            int i6 = i4 + i3 + i2;
            str = this.mContext.getResources().getQuantityString(i5, i6, new Object[]{Integer.valueOf(i6), this.mContext.getResources().getString(R.string.app_name)});
        }
        C1486ya.m3846Ma(str);
    }

    /* renamed from: vb */
    public int mo7377vb() {
        return this.f1827Od.size();
    }

    public C1145N(Context context) {
        this.mContext = context;
    }
}
