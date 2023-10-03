package com.android.contacts;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.contacts.group.GroupUtil;

public final class GroupListLoader extends CursorLoader {
    private static final String[] COLUMNS = {"account_name", "account_type", "data_set", "_id", "title", "summ_count", "group_is_read_only", "system_id"};
    private static final Uri GROUP_LIST_URI = ContactsContract.Groups.CONTENT_SUMMARY_URI;

    public GroupListLoader(Context context) {
        super(context, GROUP_LIST_URI, COLUMNS, GroupUtil.DEFAULT_SELECTION, (String[]) null, GroupUtil.getGroupsSortOrder());
    }
}
