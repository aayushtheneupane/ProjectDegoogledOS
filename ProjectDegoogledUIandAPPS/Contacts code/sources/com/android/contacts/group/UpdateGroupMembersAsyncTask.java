package com.android.contacts.group;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.provider.ContactsContract;
import android.widget.Toast;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.activities.PeopleActivity;

public class UpdateGroupMembersAsyncTask extends AsyncTask<Void, Void, Intent> {
    private final String mAccountName;
    private final String mAccountType;
    private final long[] mContactIds;
    private final Context mContext;
    private final String mDataSet;
    private final long mGroupId;
    private final int mType;

    public UpdateGroupMembersAsyncTask(int i, Context context, long[] jArr, long j, String str, String str2, String str3) {
        this.mContext = context;
        this.mType = i;
        this.mContactIds = jArr;
        this.mGroupId = j;
        this.mAccountName = str;
        this.mAccountType = str2;
        this.mDataSet = str3;
    }

    /* access modifiers changed from: protected */
    public Intent doInBackground(Void... voidArr) {
        String str;
        long[] jArr;
        long[] jArr2;
        long[] rawContactIds = getRawContactIds();
        if (rawContactIds.length == 0) {
            return null;
        }
        int i = this.mType;
        if (i == 0) {
            jArr2 = rawContactIds;
            str = GroupUtil.ACTION_ADD_TO_GROUP;
            jArr = null;
        } else if (i == 1) {
            jArr = rawContactIds;
            str = GroupUtil.ACTION_REMOVE_FROM_GROUP;
            jArr2 = null;
        } else {
            throw new IllegalStateException("Unrecognized type " + this.mType);
        }
        return ContactSaveService.createGroupUpdateIntent(this.mContext, this.mGroupId, (String) null, jArr2, jArr, PeopleActivity.class, str);
    }

    private long[] getRawContactIds() {
        Uri.Builder buildUpon = ContactsContract.RawContacts.CONTENT_URI.buildUpon();
        String str = this.mAccountName;
        if (str != null) {
            buildUpon.appendQueryParameter("account_name", str);
            buildUpon.appendQueryParameter("account_type", this.mAccountType);
        }
        String str2 = this.mDataSet;
        if (str2 != null) {
            buildUpon.appendQueryParameter("data_set", str2);
        }
        Uri build = buildUpon.build();
        String[] strArr = {"_id"};
        StringBuilder sb = new StringBuilder();
        String[] strArr2 = new String[this.mContactIds.length];
        for (int i = 0; i < this.mContactIds.length; i++) {
            if (i > 0) {
                sb.append(" OR ");
            }
            sb.append("contact_id");
            sb.append("=?");
            strArr2[i] = Long.toString(this.mContactIds[i]);
        }
        Cursor query = this.mContext.getContentResolver().query(build, strArr, sb.toString(), strArr2, (String) null, (CancellationSignal) null);
        long[] jArr = new long[query.getCount()];
        int i2 = 0;
        while (query.moveToNext()) {
            try {
                jArr[i2] = query.getLong(0);
                i2++;
            } finally {
                query.close();
            }
        }
        return jArr;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Intent intent) {
        if (intent == null) {
            Toast.makeText(this.mContext, R.string.groupSavedErrorToast, 0).show();
        } else {
            this.mContext.startService(intent);
        }
    }
}
