package com.android.contacts.common.model.dataitem;

import android.content.ContentValues;
import android.content.Context;
import com.android.contacts.common.Collapser$Collapsible;
import com.android.contacts.common.MoreContactUtils;
import com.android.contacts.common.model.account.AccountType;

public class DataItem implements Collapser$Collapsible<DataItem> {
    private final ContentValues mContentValues;
    protected DataKind mKind;

    protected DataItem(ContentValues contentValues) {
        this.mContentValues = contentValues;
    }

    public static DataItem createFrom(ContentValues contentValues) {
        String asString = contentValues.getAsString("mimetype");
        if ("vnd.android.cursor.item/group_membership".equals(asString)) {
            return new GroupMembershipDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/name".equals(asString)) {
            return new StructuredNameDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/phone_v2".equals(asString)) {
            return new PhoneDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/email_v2".equals(asString)) {
            return new EmailDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/postal-address_v2".equals(asString)) {
            return new StructuredPostalDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/im".equals(asString)) {
            return new ImDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/organization".equals(asString)) {
            return new OrganizationDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/nickname".equals(asString)) {
            return new NicknameDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/note".equals(asString)) {
            return new NoteDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/website".equals(asString)) {
            return new WebsiteDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/sip_address".equals(asString)) {
            return new SipAddressDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/contact_event".equals(asString)) {
            return new EventDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/relation".equals(asString)) {
            return new RelationDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/identity".equals(asString)) {
            return new IdentityDataItem(contentValues);
        }
        if ("vnd.android.cursor.item/photo".equals(asString)) {
            return new PhotoDataItem(contentValues);
        }
        return new DataItem(contentValues);
    }

    private static int getTypePrecedence(DataKind dataKind, int i) {
        for (int i2 = 0; i2 < dataKind.typeList.size(); i2++) {
            if (dataKind.typeList.get(i2).rawValue == i) {
                return i2;
            }
        }
        return Integer.MAX_VALUE;
    }

    public String buildDataString(Context context, DataKind dataKind) {
        CharSequence inflateUsing;
        AccountType.StringInflater stringInflater = dataKind.actionBody;
        if (stringInflater == null || (inflateUsing = stringInflater.inflateUsing(context, this.mContentValues)) == null) {
            return null;
        }
        return inflateUsing.toString();
    }

    public void collapseWith(Object obj) {
        DataItem dataItem = (DataItem) obj;
        DataKind dataKind = this.mKind;
        DataKind dataKind2 = dataItem.mKind;
        if ((!hasKindTypeColumn(dataKind) && dataItem.hasKindTypeColumn(dataKind2)) || (dataItem.hasKindTypeColumn(dataKind2) && getTypePrecedence(dataKind, getKindTypeColumn(dataKind)) > getTypePrecedence(dataKind2, dataItem.getKindTypeColumn(dataKind2)))) {
            this.mContentValues.put(dataKind2.typeColumn, Integer.valueOf(dataItem.getKindTypeColumn(dataKind2)));
            this.mKind = dataKind2;
        }
        this.mKind.maxLinesForDisplay = Math.max(dataKind.maxLinesForDisplay, dataKind2.maxLinesForDisplay);
        if (isSuperPrimary() || dataItem.isSuperPrimary()) {
            this.mContentValues.put("is_super_primary", 1);
            this.mContentValues.put("is_primary", 1);
        }
        if (isPrimary() || dataItem.isPrimary()) {
            this.mContentValues.put("is_primary", 1);
        }
        ContentValues contentValues = this.mContentValues;
        int i = 0;
        int intValue = getTimesUsed() == null ? 0 : getTimesUsed().intValue();
        if (dataItem.getTimesUsed() != null) {
            i = dataItem.getTimesUsed().intValue();
        }
        contentValues.put("times_used", Integer.valueOf(intValue + i));
        ContentValues contentValues2 = this.mContentValues;
        long j = 0;
        long longValue = getLastTimeUsed() == null ? 0 : getLastTimeUsed().longValue();
        if (dataItem.getLastTimeUsed() != null) {
            j = dataItem.getLastTimeUsed().longValue();
        }
        contentValues2.put("last_time_used", Long.valueOf(Math.max(longValue, j)));
    }

    public ContentValues getContentValues() {
        return this.mContentValues;
    }

    public long getId() {
        return this.mContentValues.getAsLong("_id").longValue();
    }

    public int getKindTypeColumn(DataKind dataKind) {
        return this.mContentValues.getAsInteger(dataKind.typeColumn).intValue();
    }

    public Long getLastTimeUsed() {
        return this.mContentValues.getAsLong("last_time_used");
    }

    public Integer getTimesUsed() {
        return this.mContentValues.getAsInteger("times_used");
    }

    public boolean hasKindTypeColumn(DataKind dataKind) {
        String str = dataKind.typeColumn;
        return (str == null || !this.mContentValues.containsKey(str) || this.mContentValues.getAsInteger(str) == null) ? false : true;
    }

    public boolean isPrimary() {
        Integer asInteger = this.mContentValues.getAsInteger("is_primary");
        return (asInteger == null || asInteger.intValue() == 0) ? false : true;
    }

    public boolean isSuperPrimary() {
        Integer asInteger = this.mContentValues.getAsInteger("is_super_primary");
        return (asInteger == null || asInteger.intValue() == 0) ? false : true;
    }

    public boolean shouldCollapseWith(DataItem dataItem, Context context) {
        if (this.mKind == null || dataItem.mKind == null) {
            return false;
        }
        return MoreContactUtils.shouldCollapse(this.mContentValues.getAsString("mimetype"), buildDataString(context, this.mKind), dataItem.mContentValues.getAsString("mimetype"), dataItem.buildDataString(context, dataItem.mKind));
    }
}
