package com.android.contacts.list;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.group.GroupUtil;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.BaseAccountType;

public class ContactsIntentResolver {
    private final Activity mContext;

    public ContactsIntentResolver(Activity activity) {
        this.mContext = activity;
    }

    public ContactsRequest resolveIntent(Intent intent) {
        ContactsRequest contactsRequest = new ContactsRequest();
        String action = intent.getAction();
        Log.i("ContactsIntentResolver", "Called with action: " + action);
        if ("com.android.contacts.action.LIST_DEFAULT".equals(action)) {
            contactsRequest.setActionCode(10);
        } else if ("com.android.contacts.action.LIST_ALL_CONTACTS".equals(action)) {
            contactsRequest.setActionCode(15);
        } else if ("com.android.contacts.action.LIST_CONTACTS_WITH_PHONES".equals(action)) {
            contactsRequest.setActionCode(17);
        } else if ("com.android.contacts.action.LIST_STARRED".equals(action)) {
            contactsRequest.setActionCode(30);
        } else if ("com.android.contacts.action.LIST_FREQUENT".equals(action)) {
            contactsRequest.setActionCode(40);
        } else if ("com.android.contacts.action.LIST_STREQUENT".equals(action)) {
            contactsRequest.setActionCode(50);
        } else if ("com.android.contacts.action.LIST_GROUP".equals(action)) {
            contactsRequest.setActionCode(20);
        } else if ("com.android.contacts.action.ACTION_SELECT_ITEMS".equals(action)) {
            String resolveType = intent.resolveType(this.mContext);
            if ("vnd.android.cursor.dir/phone_v2".equals(resolveType)) {
                contactsRequest.setActionCode(107);
            } else if ("vnd.android.cursor.dir/email_v2".equals(resolveType)) {
                contactsRequest.setActionCode(106);
            }
        } else if ("android.intent.action.PICK".equals(action)) {
            String resolveType2 = intent.resolveType(this.mContext);
            if ("vnd.android.cursor.dir/contact".equals(resolveType2)) {
                contactsRequest.setActionCode(60);
            } else if ("vnd.android.cursor.dir/person".equals(resolveType2)) {
                contactsRequest.setActionCode(60);
                contactsRequest.setLegacyCompatibilityMode(true);
            } else if ("vnd.android.cursor.dir/phone_v2".equals(resolveType2)) {
                contactsRequest.setActionCode(90);
            } else if ("vnd.android.cursor.dir/phone".equals(resolveType2)) {
                contactsRequest.setActionCode(90);
                contactsRequest.setLegacyCompatibilityMode(true);
            } else if ("vnd.android.cursor.dir/postal-address_v2".equals(resolveType2)) {
                contactsRequest.setActionCode(100);
            } else if ("vnd.android.cursor.dir/postal-address".equals(resolveType2)) {
                contactsRequest.setActionCode(100);
                contactsRequest.setLegacyCompatibilityMode(true);
            } else if ("vnd.android.cursor.dir/email_v2".equals(resolveType2)) {
                contactsRequest.setActionCode(105);
            } else if ("vnd.android.cursor.dir/group".equals(resolveType2)) {
                contactsRequest.setActionCode(21);
                contactsRequest.setAccountWithDataSet(new AccountWithDataSet(intent.getStringExtra("com.android.contacts.extra.GROUP_ACCOUNT_NAME"), intent.getStringExtra("com.android.contacts.extra.GROUP_ACCOUNT_TYPE"), intent.getStringExtra("com.android.contacts.extra.GROUP_ACCOUNT_DATA_SET")));
                contactsRequest.setRawContactIds(intent.getStringArrayListExtra("com.android.contacts.extra.GROUP_CONTACT_IDS"));
            }
        } else if ("android.intent.action.CREATE_SHORTCUT".equals(action)) {
            String className = intent.getComponent().getClassName();
            if (className.equals("alias.DialShortcut")) {
                contactsRequest.setActionCode(120);
            } else if (className.equals("alias.MessageShortcut")) {
                contactsRequest.setActionCode(BaseAccountType.Weight.NOTE);
            } else {
                contactsRequest.setActionCode(110);
            }
        } else if ("android.intent.action.GET_CONTENT".equals(action)) {
            String type = intent.getType();
            if ("vnd.android.cursor.item/contact".equals(type)) {
                contactsRequest.setActionCode(70);
            } else if ("vnd.android.cursor.item/phone_v2".equals(type)) {
                contactsRequest.setActionCode(90);
            } else if ("vnd.android.cursor.item/phone".equals(type)) {
                contactsRequest.setActionCode(90);
                contactsRequest.setLegacyCompatibilityMode(true);
            } else if ("vnd.android.cursor.item/postal-address_v2".equals(type)) {
                contactsRequest.setActionCode(100);
            } else if ("vnd.android.cursor.item/postal-address".equals(type)) {
                contactsRequest.setActionCode(100);
                contactsRequest.setLegacyCompatibilityMode(true);
            } else if ("vnd.android.cursor.item/person".equals(type)) {
                contactsRequest.setActionCode(70);
                contactsRequest.setLegacyCompatibilityMode(true);
            }
        } else if ("android.intent.action.INSERT_OR_EDIT".equals(action)) {
            contactsRequest.setActionCode(80);
        } else if ("android.intent.action.INSERT".equals(action) && "vnd.android.cursor.dir/group".equals(intent.getType())) {
            contactsRequest.setActionCode(22);
        } else if ("android.intent.action.SEARCH".equals(action)) {
            String stringExtra = intent.getStringExtra("query");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = intent.getStringExtra("phone");
            }
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = intent.getStringExtra("email");
            }
            contactsRequest.setQueryString(stringExtra);
            contactsRequest.setSearchMode(true);
        } else if ("android.intent.action.VIEW".equals(action)) {
            String resolveType3 = intent.resolveType(this.mContext);
            if ("vnd.android.cursor.dir/contact".equals(resolveType3) || "vnd.android.cursor.dir/person".equals(resolveType3)) {
                contactsRequest.setActionCode(15);
            } else if (!GroupUtil.isGroupUri(intent.getData())) {
                contactsRequest.setActionCode(BaseAccountType.Weight.f12IM);
                contactsRequest.setContactUri(intent.getData());
                intent.setAction("android.intent.action.VIEW");
                intent.setData((Uri) null);
            } else {
                contactsRequest.setActionCode(23);
                contactsRequest.setContactUri(intent.getData());
            }
        } else if ("android.intent.action.EDIT".equals(action)) {
            if (GroupUtil.isGroupUri(intent.getData())) {
                contactsRequest.setActionCode(24);
                contactsRequest.setContactUri(intent.getData());
            }
        } else if ("android.provider.Contacts.SEARCH_SUGGESTION_CLICKED".equals(action)) {
            Uri data = intent.getData();
            contactsRequest.setActionCode(BaseAccountType.Weight.f12IM);
            contactsRequest.setContactUri(data);
            intent.setAction("android.intent.action.VIEW");
            intent.setData((Uri) null);
        } else if ("com.android.contacts.action.JOIN_CONTACT".equals(action)) {
            contactsRequest.setActionCode(BaseAccountType.Weight.GROUP_MEMBERSHIP);
        }
        String stringExtra2 = intent.getStringExtra("com.android.contacts.extra.TITLE_EXTRA");
        if (stringExtra2 != null) {
            contactsRequest.setActivityTitle(stringExtra2);
        }
        return contactsRequest;
    }
}
