package com.android.contacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.ContactsActivity;
import com.android.contacts.ContactsUtils;
import com.android.contacts.R;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.NotifyingAsyncQueryHandler;

public final class ShowOrCreateActivity extends ContactsActivity implements NotifyingAsyncQueryHandler.AsyncQueryListener {
    static final String[] CONTACTS_PROJECTION = {"contact_id", "lookup"};
    static final String[] PHONES_PROJECTION = {"_id", "lookup"};
    private String mCreateDescrip;
    private Bundle mCreateExtras;
    private boolean mCreateForce;
    private NotifyingAsyncQueryHandler mQueryHandler;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            NotifyingAsyncQueryHandler notifyingAsyncQueryHandler = this.mQueryHandler;
            if (notifyingAsyncQueryHandler == null) {
                this.mQueryHandler = new NotifyingAsyncQueryHandler(this, this);
            } else {
                notifyingAsyncQueryHandler.cancelOperation(42);
            }
            Intent intent = getIntent();
            Uri data = intent.getData();
            String str2 = null;
            if (data != null) {
                str2 = data.getScheme();
                str = data.getSchemeSpecificPart();
            } else {
                str = null;
            }
            this.mCreateExtras = new Bundle();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.mCreateExtras.putAll(extras);
            }
            this.mCreateDescrip = intent.getStringExtra("com.android.contacts.action.CREATE_DESCRIPTION");
            if (this.mCreateDescrip == null) {
                this.mCreateDescrip = str;
            }
            this.mCreateForce = intent.getBooleanExtra("com.android.contacts.action.FORCE_CREATE", false);
            if (ContactsUtils.SCHEME_MAILTO.equals(str2)) {
                this.mCreateExtras.putString("email", str);
                this.mQueryHandler.startQuery(42, (Object) null, Uri.withAppendedPath(ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI, Uri.encode(str)), CONTACTS_PROJECTION, (String) null, (String[]) null, (String) null);
            } else if ("tel".equals(str2)) {
                this.mCreateExtras.putString("phone", str);
                this.mQueryHandler.startQuery(42, (Object) null, Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, str), PHONES_PROJECTION, (String) null, (String[]) null, (String) null);
            } else {
                Log.w("ShowOrCreateActivity", "Invalid intent:" + getIntent());
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NotifyingAsyncQueryHandler notifyingAsyncQueryHandler = this.mQueryHandler;
        if (notifyingAsyncQueryHandler != null) {
            notifyingAsyncQueryHandler.cancelOperation(42);
        }
    }

    public void onQueryComplete(int i, Object obj, Cursor cursor) {
        long j;
        if (cursor == null) {
            finish();
            return;
        }
        String str = null;
        try {
            int count = cursor.getCount();
            if (count != 1 || !cursor.moveToFirst()) {
                j = -1;
            } else {
                j = cursor.getLong(0);
                str = cursor.getString(1);
            }
            if (count == 1 && j != -1 && !TextUtils.isEmpty(str)) {
                ImplicitIntentsUtil.startActivityInApp(this, new Intent("android.intent.action.VIEW", ContactsContract.Contacts.getLookupUri(j, str)));
                finish();
            } else if (count > 1) {
                Intent intent = new Intent("android.intent.action.SEARCH");
                intent.setComponent(new ComponentName(this, PeopleActivity.class));
                intent.putExtras(this.mCreateExtras);
                startActivity(intent);
                finish();
            } else if (this.mCreateForce) {
                Intent intent2 = new Intent("android.intent.action.INSERT", ContactsContract.RawContacts.CONTENT_URI);
                intent2.putExtras(this.mCreateExtras);
                intent2.setType("vnd.android.cursor.dir/raw_contact");
                ImplicitIntentsUtil.startActivityInApp(this, intent2);
                finish();
            } else {
                showDialog(1);
            }
        } finally {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 1) {
            return super.onCreateDialog(i);
        }
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
        intent.putExtras(this.mCreateExtras);
        intent.setType("vnd.android.cursor.item/raw_contact");
        return new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.add_contact_dlg_message_fmt, new Object[]{this.mCreateDescrip})).setPositiveButton(17039370, new IntentClickListener(this, intent)).setNegativeButton(17039360, new IntentClickListener(this, (Intent) null)).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                ShowOrCreateActivity.this.finish();
            }
        }).create();
    }

    private static class IntentClickListener implements DialogInterface.OnClickListener {
        private Intent mIntent;
        private Activity mParent;

        public IntentClickListener(Activity activity, Intent intent) {
            this.mParent = activity;
            this.mIntent = intent;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = this.mIntent;
            if (intent != null) {
                ImplicitIntentsUtil.startActivityInApp(this.mParent, intent);
            }
            this.mParent.finish();
        }
    }
}
