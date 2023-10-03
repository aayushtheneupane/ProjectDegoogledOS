package com.android.contacts.model.account;

import android.content.ContentValues;
import android.content.Context;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.BaseAccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.CommonDateUtils;
import com.android.contactsbind.FeedbackHelper;
import com.google.common.collect.Lists;
import java.util.List;

public class GoogleAccountType extends BaseAccountType {
    public static final String ACCOUNT_TYPE = "com.google";
    public static final String PLUS_EXTENSION_PACKAGE_NAME = "com.google.android.gms";
    private static final String TAG = "GoogleAccountType";
    private static final List<String> mExtensionPackages = Lists.newArrayList((E[]) new String[]{PLUS_EXTENSION_PACKAGE_NAME});

    public boolean areContactsWritable() {
        return true;
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

    public GoogleAccountType(Context context, String str) {
        this.accountType = ACCOUNT_TYPE;
        this.resourcePackageName = null;
        this.syncAdapterPackageName = str;
        try {
            addDataKindStructuredName(context);
            addDataKindName(context);
            addDataKindPhoneticName(context);
            addDataKindNickname(context);
            addDataKindPhone(context);
            addDataKindEmail(context);
            addDataKindStructuredPostal(context);
            addDataKindIm(context);
            addDataKindOrganization(context);
            addDataKindPhoto(context);
            addDataKindNote(context);
            addDataKindWebsite(context);
            addDataKindSipAddress(context);
            addDataKindGroupMembership(context);
            addDataKindRelation(context);
            addDataKindEvent(context);
            addDataKindCustomField(context);
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            FeedbackHelper.sendFeedback(context, TAG, "Failed to build google account type", e);
        }
    }

    public List<String> getExtensionPackageNames() {
        return mExtensionPackages;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhone(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindPhone = super.addDataKindPhone(context);
        addDataKindPhone.typeColumn = "data2";
        addDataKindPhone.typeList = Lists.newArrayList();
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(2));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(3));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(12));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(4).setSecondary(true));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(5).setSecondary(true));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(6).setSecondary(true));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(7));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(0).setSecondary(true).setCustomColumn("data3"));
        addDataKindPhone.fieldList = Lists.newArrayList();
        addDataKindPhone.fieldList.add(new AccountType.EditField("data1", R.string.phoneLabelsGroup, 3));
        return addDataKindPhone;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEmail(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindEmail = super.addDataKindEmail(context);
        addDataKindEmail.typeColumn = "data2";
        addDataKindEmail.typeList = Lists.newArrayList();
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(1));
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(2));
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(3));
        addDataKindEmail.typeList.add(BaseAccountType.buildEmailType(0).setSecondary(true).setCustomColumn("data3"));
        addDataKindEmail.fieldList = Lists.newArrayList();
        addDataKindEmail.fieldList.add(new AccountType.EditField("data1", R.string.emailLabelsGroup, 33));
        return addDataKindEmail;
    }

    private DataKind addDataKindRelation(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind("vnd.android.cursor.item/relation", R.string.relationLabelsGroup, BaseAccountType.Weight.RELATIONSHIP, true));
        addKind.actionHeader = new BaseAccountType.RelationActionInflater();
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeColumn = "data2";
        addKind.typeList = Lists.newArrayList();
        addKind.typeList.add(BaseAccountType.buildRelationType(1));
        addKind.typeList.add(BaseAccountType.buildRelationType(2));
        addKind.typeList.add(BaseAccountType.buildRelationType(3));
        addKind.typeList.add(BaseAccountType.buildRelationType(4));
        addKind.typeList.add(BaseAccountType.buildRelationType(5));
        addKind.typeList.add(BaseAccountType.buildRelationType(6));
        addKind.typeList.add(BaseAccountType.buildRelationType(7));
        addKind.typeList.add(BaseAccountType.buildRelationType(8));
        addKind.typeList.add(BaseAccountType.buildRelationType(9));
        addKind.typeList.add(BaseAccountType.buildRelationType(10));
        addKind.typeList.add(BaseAccountType.buildRelationType(11));
        addKind.typeList.add(BaseAccountType.buildRelationType(12));
        addKind.typeList.add(BaseAccountType.buildRelationType(13));
        addKind.typeList.add(BaseAccountType.buildRelationType(14));
        addKind.typeList.add(BaseAccountType.buildRelationType(0).setSecondary(true).setCustomColumn("data3"));
        addKind.defaultValues = new ContentValues();
        addKind.defaultValues.put("data2", 14);
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data1", R.string.relationLabelsGroup, 8289));
        return addKind;
    }

    private DataKind addDataKindEvent(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind("vnd.android.cursor.item/contact_event", R.string.eventLabelsGroup, 120, true));
        addKind.actionHeader = new BaseAccountType.EventActionInflater();
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeColumn = "data2";
        addKind.typeList = Lists.newArrayList();
        addKind.dateFormatWithoutYear = CommonDateUtils.NO_YEAR_DATE_FORMAT;
        addKind.dateFormatWithYear = CommonDateUtils.FULL_DATE_FORMAT;
        addKind.typeList.add(BaseAccountType.buildEventType(3, true).setSpecificMax(1));
        addKind.typeList.add(BaseAccountType.buildEventType(1, false));
        addKind.typeList.add(BaseAccountType.buildEventType(2, false));
        addKind.typeList.add(BaseAccountType.buildEventType(0, false).setSecondary(true).setCustomColumn("data3"));
        addKind.defaultValues = new ContentValues();
        addKind.defaultValues.put("data2", 3);
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data1", R.string.eventLabelsGroup, 1));
        return addKind;
    }
}
