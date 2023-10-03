package com.android.contacts.quickcontact;

import android.content.Context;
import android.os.Bundle;
import com.android.contacts.ContactSaveService;
import com.android.contacts.group.GroupMetaData;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.Contact;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.RawContactDeltaList;
import com.android.contacts.model.RawContactModifier;
import com.android.contacts.model.ValuesDelta;
import java.util.List;

public class InvisibleContactUtil {
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r7 = r7.getRawContacts().get(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isInvisibleAndAddable(com.android.contacts.model.Contact r7, android.content.Context r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0072
            boolean r1 = r7.isDirectoryEntry()
            if (r1 == 0) goto L_0x000a
            goto L_0x0072
        L_0x000a:
            boolean r1 = r7.isUserProfile()
            if (r1 == 0) goto L_0x0011
            return r0
        L_0x0011:
            com.google.common.collect.ImmutableList r1 = r7.getRawContacts()
            int r1 = r1.size()
            r2 = 1
            if (r1 == r2) goto L_0x001d
            return r0
        L_0x001d:
            com.google.common.collect.ImmutableList r1 = r7.getGroupMetaData()
            if (r1 != 0) goto L_0x0024
            return r0
        L_0x0024:
            long r3 = getDefaultGroupId(r1)
            r5 = -1
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x002f
            return r0
        L_0x002f:
            com.google.common.collect.ImmutableList r7 = r7.getRawContacts()
            java.lang.Object r7 = r7.get(r0)
            com.android.contacts.model.RawContact r7 = (com.android.contacts.model.RawContact) r7
            com.android.contacts.model.account.AccountType r8 = r7.getAccountType(r8)
            if (r8 == 0) goto L_0x0072
            boolean r8 = r8.areContactsWritable()
            if (r8 != 0) goto L_0x0046
            goto L_0x0072
        L_0x0046:
            java.util.List r7 = r7.getDataItems()
            java.lang.Class<com.android.contacts.model.dataitem.GroupMembershipDataItem> r8 = com.android.contacts.model.dataitem.GroupMembershipDataItem.class
            java.lang.Iterable r7 = com.google.common.collect.Iterables.filter((java.lang.Iterable<?>) r7, r8)
            java.util.Iterator r7 = r7.iterator()
        L_0x0054:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x006f
            java.lang.Object r8 = r7.next()
            com.android.contacts.model.dataitem.GroupMembershipDataItem r8 = (com.android.contacts.model.dataitem.GroupMembershipDataItem) r8
            java.lang.Long r8 = r8.getGroupRowId()
            if (r8 == 0) goto L_0x0054
            long r5 = r8.longValue()
            int r8 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0054
            r0 = 1
        L_0x006f:
            r7 = r0 ^ 1
            return r7
        L_0x0072:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.quickcontact.InvisibleContactUtil.isInvisibleAndAddable(com.android.contacts.model.Contact, android.content.Context):boolean");
    }

    public static void addToDefaultGroup(Contact contact, Context context) {
        RawContactDeltaList createRawContactDeltaList = contact.createRawContactDeltaList();
        if (markAddToDefaultGroup(contact, createRawContactDeltaList, context)) {
            ContactSaveService.startService(context, ContactSaveService.createSaveContactIntent(context, createRawContactDeltaList, "", 0, false, QuickContactActivity.class, "android.intent.action.VIEW", (Bundle) null, (String) null, (Long) null));
        }
    }

    public static boolean markAddToDefaultGroup(Contact contact, RawContactDeltaList rawContactDeltaList, Context context) {
        long defaultGroupId = getDefaultGroupId(contact.getGroupMetaData());
        if (defaultGroupId == -1) {
            return false;
        }
        RawContactDelta rawContactDelta = (RawContactDelta) rawContactDeltaList.get(0);
        ValuesDelta insertChild = RawContactModifier.insertChild(rawContactDelta, rawContactDelta.getAccountType(AccountTypeManager.getInstance(context)).getKindForMimetype("vnd.android.cursor.item/group_membership"));
        if (insertChild == null) {
            return false;
        }
        insertChild.setGroupRowId(defaultGroupId);
        return true;
    }

    private static long getDefaultGroupId(List<GroupMetaData> list) {
        long j = -1;
        for (GroupMetaData next : list) {
            if (next.defaultGroup) {
                if (j != -1) {
                    return -1;
                }
                j = next.groupId;
            }
        }
        return j;
    }
}
