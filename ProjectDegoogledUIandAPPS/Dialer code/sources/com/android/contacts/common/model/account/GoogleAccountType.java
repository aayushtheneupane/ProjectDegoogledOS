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
import java.util.Collections;
import java.util.List;

public class GoogleAccountType extends BaseAccountType {
    private static final List<String> mExtensionPackages = new ArrayList(Collections.singletonList("com.google.android.gms"));

    public GoogleAccountType(Context context, String str) {
        this.accountType = "com.google";
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
            addDataKindSipAddress(context);
            addDataKindGroupMembership(context);
            addDataKindRelation();
            addDataKindEvent();
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            LogUtil.m7e("GoogleAccountType", "Problem building account type", (Throwable) e);
        }
    }

    private DataKind addDataKindEvent() throws AccountType.DefinitionException {
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/contact_event", R.string.eventLabelsGroup, 120, true, this);
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
        DataKind outline2 = GeneratedOutlineSupport.outline2("vnd.android.cursor.item/relation", R.string.relationLabelsGroup, 999, true, this);
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
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(3));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(1));
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
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(7));
        List<AccountType.EditType> list4 = addDataKindPhone.typeList;
        AccountType.EditType buildPhoneType4 = BaseAccountType.buildPhoneType(0);
        buildPhoneType4.secondary = true;
        buildPhoneType4.customColumn = "data3";
        list4.add(buildPhoneType4);
        addDataKindPhone.fieldList = new ArrayList();
        GeneratedOutlineSupport.outline17("data1", R.string.phoneLabelsGroup, 3, addDataKindPhone.fieldList);
        return addDataKindPhone;
    }

    public boolean areContactsWritable() {
        return true;
    }

    public List<String> getExtensionPackageNames() {
        return mExtensionPackages;
    }

    public String getViewContactNotifyServiceClassName() {
        return "com.google.android.syncadapters.contacts.SyncHighResPhotoIntentService";
    }

    public String getViewContactNotifyServicePackageName() {
        return "com.google.android.syncadapters.contacts";
    }

    public boolean isGroupMembershipEditable() {
        return true;
    }
}
