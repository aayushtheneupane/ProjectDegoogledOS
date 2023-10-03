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

public class SamsungAccountType extends BaseAccountType {
    public SamsungAccountType(Context context, String str, String str2) {
        this.accountType = str2;
        this.resourcePackageName = null;
        this.syncAdapterPackageName = str;
        try {
            addDataKindStructuredName(context);
            addDataKindDisplayName(context);
            addDataKindPhoneticName(context);
            addDataKindNickname(context);
            addDataKindPhone(context);
            addDataKindEmail(context);
            addDataKindStructuredPostal(context);
            addDataKindIm(context);
            addDataKindOrganization(context);
            DataKind dataKind = new DataKind("vnd.android.cursor.item/photo", -1, -1, true);
            addKind(dataKind);
            dataKind.typeOverallMax = 1;
            dataKind.fieldList = new ArrayList();
            dataKind.fieldList.add(new AccountType.EditField("data15", -1, -1));
            addDataKindNote(context);
            addDataKindWebsite(context);
            addDataKindGroupMembership(context);
            addDataKindRelation();
            addDataKindEvent();
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            LogUtil.m7e("KnownExternalAccountType", "Problem building account type", (Throwable) e);
        }
    }

    private DataKind addDataKindEvent() throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/contact_event", R.string.eventLabelsGroup, 150, true, this);
        outline2.actionHeader = new BaseAccountType.EventActionInflater();
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        outline2.dateFormatWithoutYear = CommonDateUtils.NO_YEAR_DATE_FORMAT;
        outline2.dateFormatWithYear = CommonDateUtils.FULL_DATE_FORMAT;
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildEventType = BaseAccountType.buildEventType(3, true);
        buildEventType.specificMax = 1;
        list.add(buildEventType);
        outline2.typeList.add(BaseAccountType.buildEventType(1, false));
        outline2.typeList.add(BaseAccountType.buildEventType(2, false));
        List<AccountType.EditType> list2 = outline2.typeList;
        AccountType.EditType buildEventType2 = BaseAccountType.buildEventType(0, false);
        buildEventType2.secondary = true;
        buildEventType2.customColumn = "data3";
        list2.add(buildEventType2);
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 3);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.eventLabelsGroup, 1, outline2.fieldList);
        return outline2;
    }

    private DataKind addDataKindRelation() throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/relation", R.string.relationLabelsGroup, 160, true, this);
        outline2.actionHeader = new BaseAccountType.RelationActionInflater();
        outline2.actionBody = new BaseAccountType.SimpleInflater(-1, "data1");
        outline2.typeColumn = "data2";
        outline2.typeList = new ArrayList();
        outline2.typeList.add(BaseAccountType.buildRelationType(1));
        outline2.typeList.add(BaseAccountType.buildRelationType(2));
        outline2.typeList.add(BaseAccountType.buildRelationType(3));
        outline2.typeList.add(BaseAccountType.buildRelationType(4));
        outline2.typeList.add(BaseAccountType.buildRelationType(5));
        outline2.typeList.add(BaseAccountType.buildRelationType(6));
        outline2.typeList.add(BaseAccountType.buildRelationType(7));
        outline2.typeList.add(BaseAccountType.buildRelationType(8));
        outline2.typeList.add(BaseAccountType.buildRelationType(9));
        outline2.typeList.add(BaseAccountType.buildRelationType(10));
        outline2.typeList.add(BaseAccountType.buildRelationType(11));
        outline2.typeList.add(BaseAccountType.buildRelationType(12));
        outline2.typeList.add(BaseAccountType.buildRelationType(13));
        outline2.typeList.add(BaseAccountType.buildRelationType(14));
        List<AccountType.EditType> list = outline2.typeList;
        AccountType.EditType buildRelationType = BaseAccountType.buildRelationType(0);
        buildRelationType.secondary = true;
        buildRelationType.customColumn = "data3";
        list.add(buildRelationType);
        outline2.defaultValues = new ContentValues();
        outline2.defaultValues.put("data2", 14);
        outline2.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.relationLabelsGroup, 8289, outline2.fieldList);
        return outline2;
    }

    public static boolean isSamsungAccountType(Context context, String str, String str2) {
        if ("com.osp.app.signin".equals(str)) {
            if (!(ExternalAccountType.loadContactsXml(context, str2) != null)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEmail(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindEmail = super.addDataKindEmail(context);
        addDataKindEmail.typeColumn = "data2";
        addDataKindEmail.typeList = new ArrayList();
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(1));
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(2));
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(3));
        List<AccountType.EditType> list = addDataKindEmail.typeList;
        AccountType.EditType buildEmailType = BaseAccountType.buildEmailType(0);
        buildEmailType.secondary = true;
        buildEmailType.customColumn = "data3";
        list.add(buildEmailType);
        addDataKindEmail.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.emailLabelsGroup, 33, addDataKindEmail.fieldList);
        return addDataKindEmail;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhone(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindPhone = super.addDataKindPhone(context);
        addDataKindPhone.typeColumn = "data2";
        addDataKindPhone.typeList = new ArrayList();
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(2));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(3));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(12));
        List<AccountType.EditType> list = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType = BaseAccountType.buildPhoneType(4);
        buildPhoneType.secondary = true;
        list.add(buildPhoneType);
        List<AccountType.EditType> list2 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType2 = BaseAccountType.buildPhoneType(5);
        buildPhoneType2.secondary = true;
        list2.add(buildPhoneType2);
        List<AccountType.EditType> list3 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType3 = BaseAccountType.buildPhoneType(6);
        buildPhoneType3.secondary = true;
        list3.add(buildPhoneType3);
        List<AccountType.EditType> list4 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType4 = BaseAccountType.buildPhoneType(14);
        buildPhoneType4.secondary = true;
        list4.add(buildPhoneType4);
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(7));
        List<AccountType.EditType> list5 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType5 = BaseAccountType.buildPhoneType(0);
        buildPhoneType5.secondary = true;
        buildPhoneType5.customColumn = "data3";
        list5.add(buildPhoneType5);
        addDataKindPhone.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.phoneLabelsGroup, 3, addDataKindPhone.fieldList);
        return addDataKindPhone;
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

    public boolean areContactsWritable() {
        return true;
    }

    public boolean isGroupMembershipEditable() {
        return true;
    }
}
