package com.android.contacts.common.model.account;

import android.content.ContentValues;
import android.content.Context;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.account.BaseAccountType;
import com.android.contacts.common.model.dataitem.DataKind;
import com.android.contacts.common.util.CommonDateUtils;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExchangeAccountType extends BaseAccountType {
    public ExchangeAccountType(Context context, String str, String str2) {
        this.accountType = str2;
        this.resourcePackageName = null;
        this.syncAdapterPackageName = str;
        try {
            addDataKindStructuredName(context);
            addDataKindDisplayName(context);
            addDataKindPhoneticName(context);
            addDataKindNickname(context);
            addDataKindPhone(context);
            DataKind addDataKindEmail = super.addDataKindEmail(context);
            addDataKindEmail.typeOverallMax = 3;
            addDataKindEmail.fieldList = new ArrayList();
            addDataKindEmail.fieldList.add(new AccountType.EditField("data1", R.string.emailLabelsGroup, 33));
            addDataKindStructuredPostal(context);
            addDataKindIm(context);
            addDataKindOrganization(context);
            addDataKindPhoto(context);
            addDataKindNote(context);
            addDataKindEvent(context);
            addDataKindWebsite(context);
            addDataKindGroupMembership(context);
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            LogUtil.m7e("ExchangeAccountType", "Problem building account type", (Throwable) e);
        }
    }

    public static boolean isExchangeType(String str) {
        return "com.android.exchange".equals(str) || "com.google.android.exchange".equals(str) || "com.google.android.gm.exchange".equals(str);
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindDisplayName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("#displayName", R.string.nameLabelsGroup, -1, true, this);
        boolean z = context.getResources().getBoolean(R.bool.config_editor_field_order_primary);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        List<AccountType.EditField> list = outline2.fieldList;
        AccountType.EditField editField = new AccountType.EditField("data4", R.string.name_prefix, 8289);
        editField.optional = true;
        list.add(editField);
        if (!z) {
            GeneratedOutlineSupport.outline17("data3", R.string.name_family, 8289, outline2.fieldList);
            List<AccountType.EditField> list2 = outline2.fieldList;
            AccountType.EditField editField2 = new AccountType.EditField("data5", R.string.name_middle, 8289);
            editField2.optional = true;
            list2.add(editField2);
            GeneratedOutlineSupport.outline17("data2", R.string.name_given, 8289, outline2.fieldList);
        } else {
            GeneratedOutlineSupport.outline17("data2", R.string.name_given, 8289, outline2.fieldList);
            List<AccountType.EditField> list3 = outline2.fieldList;
            AccountType.EditField editField3 = new AccountType.EditField("data5", R.string.name_middle, 8289);
            editField3.optional = true;
            list3.add(editField3);
            GeneratedOutlineSupport.outline17("data3", R.string.name_family, 8289, outline2.fieldList);
        }
        List<AccountType.EditField> list4 = outline2.fieldList;
        AccountType.EditField editField4 = new AccountType.EditField("data6", R.string.name_suffix, 8289);
        editField4.optional = true;
        list4.add(editField4);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEmail(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindEmail = super.addDataKindEmail(context);
        addDataKindEmail.typeOverallMax = 3;
        addDataKindEmail.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.emailLabelsGroup, 33, addDataKindEmail.fieldList);
        return addDataKindEmail;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEvent(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/contact_event", R.string.eventLabelsGroup, 120, true, this);
        outline2.actionHeader = new BaseAccountType.EventActionInflater();
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildEventType = BaseAccountType.buildEventType(3, false);
        buildEventType.specificMax = 1;
        list.add(buildEventType);
        outline2.dateFormatWithYear = CommonDateUtils.DATE_AND_TIME_FORMAT;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.eventLabelsGroup, 1, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindIm(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/im", R.string.imLabelsGroup, 140, true, this);
        outline2.actionHeader = new BaseAccountType.ImActionInflater();
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 3);
        outline2.typeColumn = "data5";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(BaseAccountType.buildImType(0));
        outline2.typeList.add(BaseAccountType.buildImType(1));
        outline2.typeList.add(BaseAccountType.buildImType(2));
        outline2.typeList.add(BaseAccountType.buildImType(3));
        outline2.typeList.add(BaseAccountType.buildImType(4));
        outline2.typeList.add(BaseAccountType.buildImType(5));
        outline2.typeList.add(BaseAccountType.buildImType(6));
        outline2.typeList.add(BaseAccountType.buildImType(7));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildImType = BaseAccountType.buildImType(-1);
        buildImType.secondary = true;
        buildImType.customColumn = "data6";
        list.add(buildImType);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.imLabelsGroup, 33, outline2.fieldList);
        outline2.typeOverallMax = 3;
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 3);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.imLabelsGroup, 33, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNickname(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/nickname", R.string.nicknameLabelsGroup, 111, true, this);
        outline2.typeOverallMax = 1;
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.nicknameLabelsGroup, (String) null);
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 1);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.nicknameLabelsGroup, 8289, outline2.fieldList);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.nicknameLabelsGroup, 8289, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNote(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/note", R.string.label_notes, 130, true, this);
        outline2.typeOverallMax = 1;
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.label_notes, (String) null);
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.label_notes, 147457, outline2.fieldList);
        outline2.maxLinesForDisplay = 100;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.label_notes, 147457, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindOrganization(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/organization", R.string.organizationLabelsGroup, 125, true, this);
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.organizationLabelsGroup, (String) null);
        outline2.actionBody = BaseAccountType.ORGANIZATION_BODY_INFLATER;
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.ghostData_company, 8193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data4", R.string.ghostData_title, 8193, outline2.fieldList);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.ghostData_company, 8193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data4", R.string.ghostData_title, 8193, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhone(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindPhone = super.addDataKindPhone(context);
        addDataKindPhone.typeColumn = "data2";
        addDataKindPhone.typeList = new ArrayList();
        List<AccountType.EditType> list = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType = BaseAccountType.buildPhoneType(2);
        buildPhoneType.specificMax = 1;
        list.add(buildPhoneType);
        List<AccountType.EditType> list2 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType2 = BaseAccountType.buildPhoneType(1);
        buildPhoneType2.specificMax = 2;
        list2.add(buildPhoneType2);
        List<AccountType.EditType> list3 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType3 = BaseAccountType.buildPhoneType(3);
        buildPhoneType3.specificMax = 2;
        list3.add(buildPhoneType3);
        List<AccountType.EditType> list4 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType4 = BaseAccountType.buildPhoneType(4);
        buildPhoneType4.secondary = true;
        buildPhoneType4.specificMax = 1;
        list4.add(buildPhoneType4);
        List<AccountType.EditType> list5 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType5 = BaseAccountType.buildPhoneType(5);
        buildPhoneType5.secondary = true;
        buildPhoneType5.specificMax = 1;
        list5.add(buildPhoneType5);
        List<AccountType.EditType> list6 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType6 = BaseAccountType.buildPhoneType(6);
        buildPhoneType6.secondary = true;
        buildPhoneType6.specificMax = 1;
        list6.add(buildPhoneType6);
        List<AccountType.EditType> list7 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType7 = BaseAccountType.buildPhoneType(9);
        buildPhoneType7.secondary = true;
        buildPhoneType7.specificMax = 1;
        list7.add(buildPhoneType7);
        List<AccountType.EditType> list8 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType8 = BaseAccountType.buildPhoneType(10);
        buildPhoneType8.secondary = true;
        buildPhoneType8.specificMax = 1;
        list8.add(buildPhoneType8);
        List<AccountType.EditType> list9 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType9 = BaseAccountType.buildPhoneType(20);
        buildPhoneType9.secondary = true;
        buildPhoneType9.specificMax = 1;
        list9.add(buildPhoneType9);
        List<AccountType.EditType> list10 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType10 = BaseAccountType.buildPhoneType(14);
        buildPhoneType10.secondary = true;
        buildPhoneType10.specificMax = 1;
        list10.add(buildPhoneType10);
        List<AccountType.EditType> list11 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType11 = BaseAccountType.buildPhoneType(19);
        buildPhoneType11.secondary = true;
        buildPhoneType11.specificMax = 1;
        list11.add(buildPhoneType11);
        addDataKindPhone.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.phoneLabelsGroup, 3, addDataKindPhone.fieldList);
        return addDataKindPhone;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoneticName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("#phoneticName", R.string.name_phonetic, -1, true, this);
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.nameLabelsGroup, (String) null);
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data9", R.string.name_phonetic_family, 193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data7", R.string.name_phonetic_given, 193, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoto(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/photo", -1, -1, true, this);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data15", -1, -1, outline2.fieldList);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data15", -1, -1, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredName(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/name", R.string.nameLabelsGroup, -1, true, this);
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.nameLabelsGroup, (String) null);
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        List<AccountType.EditField> list = outline2.fieldList;
        AccountType.EditField editField = new AccountType.EditField("data4", R.string.name_prefix, 8289);
        editField.optional = true;
        list.add(editField);
        GeneratedOutlineSupport.outline17("data3", R.string.name_family, 8289, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data5", R.string.name_middle, 8289, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data2", R.string.name_given, 8289, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data6", R.string.name_suffix, 8289, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data9", R.string.name_phonetic_family, 193, outline2.fieldList);
        GeneratedOutlineSupport.outline17("data7", R.string.name_phonetic_given, 193, outline2.fieldList);
        return outline2;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredPostal(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindStructuredPostal = super.addDataKindStructuredPostal(context);
        boolean equals = Locale.JAPANESE.getLanguage().equals(Locale.getDefault().getLanguage());
        addDataKindStructuredPostal.typeColumn = "data2";
        addDataKindStructuredPostal.typeList = new ArrayList();
        List<AccountType.EditType> list = addDataKindStructuredPostal.typeList;
        AccountType.EditType buildPostalType = BaseAccountType.buildPostalType(2);
        buildPostalType.specificMax = 1;
        list.add(buildPostalType);
        List<AccountType.EditType> list2 = addDataKindStructuredPostal.typeList;
        AccountType.EditType buildPostalType2 = BaseAccountType.buildPostalType(1);
        buildPostalType2.specificMax = 1;
        list2.add(buildPostalType2);
        List<AccountType.EditType> list3 = addDataKindStructuredPostal.typeList;
        AccountType.EditType buildPostalType3 = BaseAccountType.buildPostalType(3);
        buildPostalType3.specificMax = 1;
        list3.add(buildPostalType3);
        addDataKindStructuredPostal.fieldList = new ArrayList();
        if (equals) {
            List<AccountType.EditField> list4 = addDataKindStructuredPostal.fieldList;
            AccountType.EditField editField = new AccountType.EditField("data10", R.string.postal_country, 139377);
            editField.optional = true;
            list4.add(editField);
            GeneratedOutlineSupport.outline17("data9", R.string.postal_postcode, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data8", R.string.postal_region, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data7", R.string.postal_city, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data4", R.string.postal_street, 139377, addDataKindStructuredPostal.fieldList);
        } else {
            GeneratedOutlineSupport.outline17("data4", R.string.postal_street, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data7", R.string.postal_city, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data8", R.string.postal_region, 139377, addDataKindStructuredPostal.fieldList);
            GeneratedOutlineSupport.outline17("data9", R.string.postal_postcode, 139377, addDataKindStructuredPostal.fieldList);
            List<AccountType.EditField> list5 = addDataKindStructuredPostal.fieldList;
            AccountType.EditField editField2 = new AccountType.EditField("data10", R.string.postal_country, 139377);
            editField2.optional = true;
            list5.add(editField2);
        }
        return addDataKindStructuredPostal;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindWebsite(Context context) throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/website", R.string.websiteLabelsGroup, 160, true, this);
        outline2.actionHeader = new BaseAccountType.SimpleInflater(R.string.websiteLabelsGroup, (String) null);
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 7);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.websiteLabelsGroup, 17, outline2.fieldList);
        outline2.typeOverallMax = 1;
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.websiteLabelsGroup, 17, outline2.fieldList);
        return outline2;
    }

    public boolean areContactsWritable() {
        return true;
    }

    public boolean isGroupMembershipEditable() {
        return true;
    }
}
