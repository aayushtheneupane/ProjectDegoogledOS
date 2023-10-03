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
import java.util.Locale;

public class ExchangeAccountType extends BaseAccountType {
    private static final String ACCOUNT_TYPE_AOSP = "com.android.exchange";
    private static final String ACCOUNT_TYPE_GOOGLE_1 = "com.google.android.exchange";
    private static final String ACCOUNT_TYPE_GOOGLE_2 = "com.google.android.gm.exchange";
    private static final String TAG = "ExchangeAccountType";

    public boolean areContactsWritable() {
        return true;
    }

    public boolean isGroupMembershipEditable() {
        return true;
    }

    public ExchangeAccountType(Context context, String str, String str2) {
        this.accountType = str2;
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
            addDataKindEvent(context);
            addDataKindWebsite(context);
            addDataKindGroupMembership(context);
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            FeedbackHelper.sendFeedback(context, TAG, "Failed to build exchange account type", e);
        }
    }

    public static boolean isExchangeType(String str) {
        return ACCOUNT_TYPE_AOSP.equals(str) || ACCOUNT_TYPE_GOOGLE_1.equals(str) || ACCOUNT_TYPE_GOOGLE_2.equals(str);
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredName(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind("vnd.android.cursor.item/name", R.string.nameLabelsGroup, -1, true));
        addKind.actionHeader = new BaseAccountType.SimpleInflater((int) R.string.nameLabelsGroup);
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeOverallMax = 1;
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data4", R.string.name_prefix, 8289).setOptional(true));
        addKind.fieldList.add(new AccountType.EditField("data3", R.string.name_family, 8289));
        addKind.fieldList.add(new AccountType.EditField("data5", R.string.name_middle, 8289));
        addKind.fieldList.add(new AccountType.EditField("data2", R.string.name_given, 8289));
        addKind.fieldList.add(new AccountType.EditField("data6", R.string.name_suffix, 8289));
        addKind.fieldList.add(new AccountType.EditField("data9", R.string.name_phonetic_family, 193));
        addKind.fieldList.add(new AccountType.EditField("data7", R.string.name_phonetic_given, 193));
        return addKind;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoneticName(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind(DataKind.PSEUDO_MIME_TYPE_PHONETIC_NAME, R.string.name_phonetic, -1, true));
        addKind.actionHeader = new BaseAccountType.SimpleInflater((int) R.string.nameLabelsGroup);
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeOverallMax = 1;
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data9", R.string.name_phonetic_family, 193));
        addKind.fieldList.add(new AccountType.EditField("data7", R.string.name_phonetic_given, 193));
        return addKind;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNickname(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindNickname = super.addDataKindNickname(context);
        addDataKindNickname.typeOverallMax = 1;
        addDataKindNickname.fieldList = Lists.newArrayList();
        addDataKindNickname.fieldList.add(new AccountType.EditField("data1", R.string.nicknameLabelsGroup, 8289));
        return addDataKindNickname;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhone(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindPhone = super.addDataKindPhone(context);
        addDataKindPhone.typeColumn = "data2";
        addDataKindPhone.typeList = Lists.newArrayList();
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(2).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(1).setSpecificMax(2));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(3).setSpecificMax(2));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(4).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(5).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(6).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(9).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(10).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(20).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(14).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.typeList.add(BaseAccountType.buildPhoneType(19).setSecondary(true).setSpecificMax(1));
        addDataKindPhone.fieldList = Lists.newArrayList();
        addDataKindPhone.fieldList.add(new AccountType.EditField("data1", R.string.phoneLabelsGroup, 3));
        return addDataKindPhone;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEmail(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindEmail = super.addDataKindEmail(context);
        addDataKindEmail.typeOverallMax = 3;
        addDataKindEmail.fieldList = Lists.newArrayList();
        addDataKindEmail.fieldList.add(new AccountType.EditField("data1", R.string.emailLabelsGroup, 33));
        return addDataKindEmail;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredPostal(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindStructuredPostal = super.addDataKindStructuredPostal(context);
        boolean equals = Locale.JAPANESE.getLanguage().equals(Locale.getDefault().getLanguage());
        addDataKindStructuredPostal.typeColumn = "data2";
        addDataKindStructuredPostal.typeList = Lists.newArrayList();
        addDataKindStructuredPostal.typeList.add(BaseAccountType.buildPostalType(2).setSpecificMax(1));
        addDataKindStructuredPostal.typeList.add(BaseAccountType.buildPostalType(1).setSpecificMax(1));
        addDataKindStructuredPostal.typeList.add(BaseAccountType.buildPostalType(3).setSpecificMax(1));
        addDataKindStructuredPostal.fieldList = Lists.newArrayList();
        if (equals) {
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data10", R.string.postal_country, 139377).setOptional(true));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data9", R.string.postal_postcode, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data8", R.string.postal_region, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data7", R.string.postal_city, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data4", R.string.postal_street, 139377));
        } else {
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data4", R.string.postal_street, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data7", R.string.postal_city, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data8", R.string.postal_region, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data9", R.string.postal_postcode, 139377));
            addDataKindStructuredPostal.fieldList.add(new AccountType.EditField("data10", R.string.postal_country, 139377).setOptional(true));
        }
        return addDataKindStructuredPostal;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindIm(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindIm = super.addDataKindIm(context);
        addDataKindIm.typeOverallMax = 3;
        addDataKindIm.defaultValues = new ContentValues();
        addDataKindIm.defaultValues.put("data2", 3);
        addDataKindIm.fieldList = Lists.newArrayList();
        addDataKindIm.fieldList.add(new AccountType.EditField("data1", R.string.imLabelsGroup, 33));
        return addDataKindIm;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindOrganization(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindOrganization = super.addDataKindOrganization(context);
        addDataKindOrganization.typeOverallMax = 1;
        addDataKindOrganization.fieldList = Lists.newArrayList();
        addDataKindOrganization.fieldList.add(new AccountType.EditField("data1", R.string.ghostData_company, 8193));
        addDataKindOrganization.fieldList.add(new AccountType.EditField("data4", R.string.ghostData_title, 8193));
        return addDataKindOrganization;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindPhoto(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindPhoto = super.addDataKindPhoto(context);
        addDataKindPhoto.typeOverallMax = 1;
        addDataKindPhoto.fieldList = Lists.newArrayList();
        addDataKindPhoto.fieldList.add(new AccountType.EditField("data15", -1, -1));
        return addDataKindPhoto;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindNote(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindNote = super.addDataKindNote(context);
        addDataKindNote.fieldList = Lists.newArrayList();
        addDataKindNote.fieldList.add(new AccountType.EditField("data1", R.string.label_notes, 147457));
        return addDataKindNote;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindEvent(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind("vnd.android.cursor.item/contact_event", R.string.eventLabelsGroup, 120, true));
        addKind.actionHeader = new BaseAccountType.EventActionInflater();
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeOverallMax = 1;
        addKind.typeColumn = "data2";
        addKind.typeList = Lists.newArrayList();
        addKind.typeList.add(BaseAccountType.buildEventType(3, false).setSpecificMax(1));
        addKind.dateFormatWithYear = CommonDateUtils.DATE_AND_TIME_FORMAT;
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data1", R.string.eventLabelsGroup, 1));
        return addKind;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindWebsite(Context context) throws AccountType.DefinitionException {
        DataKind addDataKindWebsite = super.addDataKindWebsite(context);
        addDataKindWebsite.typeOverallMax = 1;
        addDataKindWebsite.fieldList = Lists.newArrayList();
        addDataKindWebsite.fieldList.add(new AccountType.EditField("data1", R.string.websiteLabelsGroup, 17));
        return addDataKindWebsite;
    }
}
