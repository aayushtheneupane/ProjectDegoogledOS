package com.android.contacts.common.model.account;

import android.content.Context;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.dataitem.DataKind;
import com.android.dialer.common.LogUtil;
import java.util.ArrayList;

public class FallbackAccountType extends BaseAccountType {
    public FallbackAccountType(Context context) {
        this.accountType = null;
        this.dataSet = null;
        this.resourcePackageName = null;
        this.syncAdapterPackageName = null;
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
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            LogUtil.m7e("FallbackAccountType", "Problem building account type", (Throwable) e);
        }
    }

    public boolean areContactsWritable() {
        return true;
    }
}
