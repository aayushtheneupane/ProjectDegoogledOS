package com.android.contacts.vcard;

import android.app.Notification;
import android.net.Uri;
import com.android.vcard.VCardEntry;

interface VCardImportExportListener {
    void onCancelRequest(CancelRequest cancelRequest, int i);

    void onExportFailed(ExportRequest exportRequest);

    Notification onExportProcessed(ExportRequest exportRequest, int i);

    void onImportCanceled(ImportRequest importRequest, int i);

    void onImportFailed(ImportRequest importRequest);

    void onImportFinished(ImportRequest importRequest, int i, Uri uri);

    Notification onImportParsed(ImportRequest importRequest, int i, VCardEntry vCardEntry, int i2, int i3);

    Notification onImportProcessed(ImportRequest importRequest, int i, int i2);
}
