package com.android.contacts.group;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.contacts.ContactsUtils;
import com.android.contacts.activities.ContactSelectionActivity;
import com.android.contacts.group.GroupMembersFragment;
import com.android.contacts.list.ContactsSectionIndexer;
import com.android.contacts.model.account.GoogleAccountType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GroupUtil {
    public static final String ACTION_ADD_TO_GROUP = "addToGroup";
    public static final String ACTION_CREATE_GROUP = "createGroup";
    public static final String ACTION_DELETE_GROUP = "deleteGroup";
    public static final String ACTION_REMOVE_FROM_GROUP = "removeFromGroup";
    public static final String ACTION_SWITCH_GROUP = "switchGroup";
    public static final String ACTION_UPDATE_GROUP = "updateGroup";
    public static final String ALL_GROUPS_SELECTION = "account_type NOT NULL AND account_name NOT NULL AND deleted=0";
    public static final String DEFAULT_SELECTION = "account_type NOT NULL AND account_name NOT NULL AND deleted=0 AND auto_add=0 AND favorites=0";
    private static final Set<String> FFC_GROUPS = new HashSet(Arrays.asList(new String[]{"Friends", "Family", "Coworkers"}));
    public static final int RESULT_SEND_TO_SELECTION = 100;

    public static String getGroupsSortOrder() {
        return "title COLLATE LOCALIZED ASC";
    }

    private GroupUtil() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0061, code lost:
        if (android.text.TextUtils.equals(r5, r13) != false) goto L_0x0065;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.contacts.group.GroupListItem getGroupListItem(android.database.Cursor r13, int r14) {
        /*
            if (r13 == 0) goto L_0x0070
            boolean r0 = r13.isClosed()
            if (r0 != 0) goto L_0x0070
            boolean r0 = r13.moveToPosition(r14)
            if (r0 != 0) goto L_0x000f
            goto L_0x0070
        L_0x000f:
            r0 = 0
            java.lang.String r2 = r13.getString(r0)
            r1 = 1
            java.lang.String r3 = r13.getString(r1)
            r4 = 2
            java.lang.String r5 = r13.getString(r4)
            r6 = 3
            long r6 = r13.getLong(r6)
            r8 = 4
            java.lang.String r8 = r13.getString(r8)
            r9 = 5
            int r9 = r13.getInt(r9)
            r10 = 6
            int r10 = r13.getInt(r10)
            if (r10 != r1) goto L_0x0036
            r10 = 1
            goto L_0x0037
        L_0x0036:
            r10 = 0
        L_0x0037:
            r11 = 7
            java.lang.String r11 = r13.getString(r11)
            int r14 = r14 - r1
            if (r14 < 0) goto L_0x0064
            boolean r14 = r13.moveToPosition(r14)
            if (r14 == 0) goto L_0x0064
            java.lang.String r14 = r13.getString(r0)
            java.lang.String r12 = r13.getString(r1)
            java.lang.String r13 = r13.getString(r4)
            boolean r14 = android.text.TextUtils.equals(r2, r14)
            if (r14 == 0) goto L_0x0064
            boolean r14 = android.text.TextUtils.equals(r3, r12)
            if (r14 == 0) goto L_0x0064
            boolean r13 = android.text.TextUtils.equals(r5, r13)
            if (r13 == 0) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            r0 = 1
        L_0x0065:
            com.android.contacts.group.GroupListItem r13 = new com.android.contacts.group.GroupListItem
            r1 = r13
            r4 = r5
            r5 = r6
            r7 = r8
            r8 = r0
            r1.<init>(r2, r3, r4, r5, r7, r8, r9, r10, r11)
            return r13
        L_0x0070:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.group.GroupUtil.getGroupListItem(android.database.Cursor, int):com.android.contacts.group.GroupListItem");
    }

    public static List<String> getSendToDataForIds(Context context, long[] jArr, String str) {
        String str2;
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        String convertArrayToString = convertArrayToString(jArr);
        if (ContactsUtils.SCHEME_MAILTO.equals(str)) {
            str2 = "mimetype='vnd.android.cursor.item/email_v2' AND _id IN (" + convertArrayToString + ")";
        } else {
            str2 = "mimetype='vnd.android.cursor.item/phone_v2' AND _id IN (" + convertArrayToString + ")";
        }
        String str3 = str2;
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContactsContract.Data.CONTENT_URI;
        if (ContactsUtils.SCHEME_MAILTO.equals(str)) {
            strArr = GroupMembersFragment.Query.EMAIL_PROJECTION;
        } else {
            strArr = GroupMembersFragment.Query.PHONE_PROJECTION;
        }
        Cursor query = contentResolver.query(uri, strArr, str3, (String[]) null, (String) null);
        if (query == null) {
            return arrayList;
        }
        try {
            query.moveToPosition(-1);
            while (query.moveToNext()) {
                String string = query.getString(3);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            return arrayList;
        } finally {
            query.close();
        }
    }

    public static void startSendToSelectionActivity(Fragment fragment, String str, String str2, String str3) {
        fragment.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.SENDTO", Uri.fromParts(str2, str, (String) null)), str3), 100);
    }

    public static Intent createSendToSelectionPickerIntent(Context context, long[] jArr, long[] jArr2, String str, String str2) {
        Intent intent = new Intent(context, ContactSelectionActivity.class);
        intent.setAction("com.android.contacts.action.ACTION_SELECT_ITEMS");
        intent.setType(ContactsUtils.SCHEME_MAILTO.equals(str) ? "vnd.android.cursor.dir/email_v2" : "vnd.android.cursor.dir/phone_v2");
        intent.putExtra("com.android.contacts.extra.SELECTION_ITEM_LIST", jArr);
        intent.putExtra("com.android.contacts.extra.SELECTION_DEFAULT_SELECTION", jArr2);
        intent.putExtra("com.android.contacts.extra.SELECTION_SEND_SCHEME", str);
        intent.putExtra("com.android.contacts.extra.SELECTION_SEND_TITLE", str2);
        return intent;
    }

    public static Intent createPickMemberIntent(Context context, GroupMetaData groupMetaData, ArrayList<String> arrayList) {
        Intent intent = new Intent(context, ContactSelectionActivity.class);
        intent.setAction("android.intent.action.PICK");
        intent.setType("vnd.android.cursor.dir/group");
        intent.putExtra("com.android.contacts.extra.GROUP_ACCOUNT_NAME", groupMetaData.accountName);
        intent.putExtra("com.android.contacts.extra.GROUP_ACCOUNT_TYPE", groupMetaData.accountType);
        intent.putExtra("com.android.contacts.extra.GROUP_ACCOUNT_DATA_SET", groupMetaData.dataSet);
        intent.putExtra("com.android.contacts.extra.GROUP_CONTACT_IDS", arrayList);
        return intent;
    }

    public static String convertArrayToString(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return "";
        }
        return Arrays.toString(jArr).replace("[", "").replace("]", "");
    }

    public static long[] convertLongSetToLongArray(Set<Long> set) {
        Long[] lArr = (Long[]) set.toArray(new Long[set.size()]);
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }

    public static long[] convertStringSetToLongArray(Set<String> set) {
        String[] strArr = (String[]) set.toArray(new String[set.size()]);
        long[] jArr = new long[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                jArr[i] = Long.parseLong(strArr[i]);
            } catch (NumberFormatException unused) {
                jArr[i] = -1;
            }
        }
        return jArr;
    }

    public static boolean isEmptyFFCGroup(GroupListItem groupListItem) {
        return groupListItem.isReadOnly() && isSystemIdFFC(groupListItem.getSystemId()) && groupListItem.getMemberCount() <= 0;
    }

    /* access modifiers changed from: private */
    public static boolean isSystemIdFFC(String str) {
        return !TextUtils.isEmpty(str) && FFC_GROUPS.contains(str);
    }

    public static boolean isGroupUri(Uri uri) {
        return uri != null && uri.toString().startsWith(ContactsContract.Groups.CONTENT_URI.toString());
    }

    public static boolean needTrimming(int i, int[] iArr, int[] iArr2) {
        return iArr2.length > 0 && iArr.length > 0 && i <= iArr[iArr.length - 1] + iArr2[iArr2.length - 1];
    }

    public static void updateBundle(Bundle bundle, ContactsSectionIndexer contactsSectionIndexer, List<Integer> list, String[] strArr, int[] iArr) {
        for (Integer intValue : list) {
            int sectionForPosition = contactsSectionIndexer.getSectionForPosition(intValue.intValue());
            if (sectionForPosition < iArr.length && sectionForPosition >= 0) {
                iArr[sectionForPosition] = iArr[sectionForPosition] - 1;
                if (iArr[sectionForPosition] == 0) {
                    strArr[sectionForPosition] = "";
                }
            }
        }
        bundle.putStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES", clearEmptyString(strArr));
        bundle.putIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS", clearZeros(iArr));
    }

    private static String[] clearEmptyString(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static int[] clearZeros(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        int[] iArr2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public static final class GroupsProjection {
        public final int accountName;
        public final int accountType;
        public final int autoAdd;
        public final int dataSet;
        public final int deleted;
        public final int favorites;
        public final int groupId;
        public final int isReadOnly;
        public final int summaryCount;
        public final int systemId;
        public final int title;

        public GroupsProjection(Cursor cursor) {
            this.groupId = cursor.getColumnIndex("_id");
            this.title = cursor.getColumnIndex("title");
            this.summaryCount = cursor.getColumnIndex("summ_count");
            this.systemId = cursor.getColumnIndex("system_id");
            this.accountName = cursor.getColumnIndex("account_name");
            this.accountType = cursor.getColumnIndex("account_type");
            this.dataSet = cursor.getColumnIndex("data_set");
            this.autoAdd = cursor.getColumnIndex("auto_add");
            this.favorites = cursor.getColumnIndex("favorites");
            this.isReadOnly = cursor.getColumnIndex("group_is_read_only");
            this.deleted = cursor.getColumnIndex("deleted");
        }

        public String getTitle(Cursor cursor) {
            return cursor.getString(this.title);
        }

        public boolean isEmptyFFCGroup(Cursor cursor) {
            int i = this.accountType;
            if (i != -1 && this.isReadOnly != -1 && this.systemId != -1 && this.summaryCount != -1) {
                return GoogleAccountType.ACCOUNT_TYPE.equals(cursor.getString(i)) && cursor.getInt(this.isReadOnly) != 0 && GroupUtil.isSystemIdFFC(cursor.getString(this.systemId)) && cursor.getInt(this.summaryCount) <= 0;
            }
            throw new IllegalArgumentException("Projection is missing required columns");
        }
    }
}
