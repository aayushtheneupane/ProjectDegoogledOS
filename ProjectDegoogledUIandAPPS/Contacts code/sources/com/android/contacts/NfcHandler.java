package com.android.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.provider.ContactsContract;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NfcHandler implements NfcAdapter.CreateNdefMessageCallback {
    private final Uri mContactUri;
    private final Context mContext;

    public static void register(Activity activity, Uri uri) {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(activity.getApplicationContext());
        if (defaultAdapter != null) {
            defaultAdapter.setNdefPushMessageCallback(new NfcHandler(activity, uri), activity, new Activity[0]);
        }
    }

    public NfcHandler(Context context, Uri uri) {
        this.mContext = context;
        this.mContactUri = uri;
    }

    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        Uri uri;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Uri uri2 = this.mContactUri;
        if (uri2 != null) {
            String encode = Uri.encode(uri2.getPathSegments().get(2));
            if (encode.equals("profile")) {
                uri = ContactsContract.Profile.CONTENT_VCARD_URI.buildUpon().appendQueryParameter("no_photo", "true").build();
            } else {
                uri = ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath(encode).appendQueryParameter("no_photo", "true").build();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            try {
                InputStream openInputStream = contentResolver.openInputStream(uri);
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read <= 0) {
                        return new NdefMessage(NdefRecord.createMime("text/x-vcard", byteArrayOutputStream.toByteArray()), new NdefRecord[0]);
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (IOException unused) {
                Log.e("ContactNfcHandler", "IOException creating vcard.");
                return null;
            }
        } else {
            Log.w("ContactNfcHandler", "No contact URI to share.");
            return null;
        }
    }
}
