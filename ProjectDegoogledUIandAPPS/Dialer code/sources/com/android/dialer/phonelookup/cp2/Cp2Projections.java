package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.phonelookup.PhoneLookupInfo;

final class Cp2Projections {
    private static final String[] PHONE_LOOKUP_PROJECTION = {"display_name", "photo_thumb_uri", "photo_uri", "photo_id", "type", "label", "normalized_number", "contact_id", "lookup"};
    private static final String[] PHONE_PROJECTION = {"display_name", "photo_thumb_uri", "photo_uri", "photo_id", "data2", "data3", "data4", "contact_id", "lookup", "carrier_presence"};

    static PhoneLookupInfo.Cp2Info.Cp2ContactInfo buildCp2ContactInfoFromCursor(Context context, Cursor cursor, long j) {
        boolean z = false;
        String string = cursor.getString(0);
        String string2 = cursor.getString(1);
        String string3 = cursor.getString(2);
        int i = cursor.getInt(3);
        int i2 = cursor.getInt(4);
        String string4 = cursor.getString(5);
        int i3 = cursor.getInt(7);
        String string5 = cursor.getString(8);
        PhoneLookupInfo.Cp2Info.Cp2ContactInfo.Builder newBuilder = PhoneLookupInfo.Cp2Info.Cp2ContactInfo.newBuilder();
        if (!TextUtils.isEmpty(string)) {
            newBuilder.setName(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            newBuilder.setPhotoThumbnailUri(string2);
        }
        if (!TextUtils.isEmpty(string3)) {
            newBuilder.setPhotoUri(string3);
        }
        if (i > 0) {
            newBuilder.setPhotoId((long) i);
        }
        if (i2 != 0 || !TextUtils.isEmpty(string4)) {
            newBuilder.setLabel(ContactsContract.CommonDataKinds.Phone.getTypeLabel(context.getResources(), i2, string4).toString());
        }
        long j2 = (long) i3;
        newBuilder.setContactId(j2);
        if (!TextUtils.isEmpty(string5)) {
            newBuilder.setLookupUri(ContactsContract.Contacts.getLookupUri(j2, string5).buildUpon().appendQueryParameter("directory", String.valueOf(j)).build().toString());
        }
        int columnIndex = cursor.getColumnIndex("carrier_presence");
        if (columnIndex != -1) {
            if ((cursor.getInt(columnIndex) & 1) == 1) {
                z = true;
            }
            newBuilder.setCanSupportCarrierVideoCall(z);
        }
        return (PhoneLookupInfo.Cp2Info.Cp2ContactInfo) newBuilder.build();
    }

    static String[] getProjectionForPhoneLookupTable() {
        return PHONE_LOOKUP_PROJECTION;
    }

    static String[] getProjectionForPhoneTable() {
        return PHONE_PROJECTION;
    }
}
