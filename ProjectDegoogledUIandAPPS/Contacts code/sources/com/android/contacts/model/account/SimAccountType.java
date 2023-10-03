package com.android.contacts.model.account;

import android.accounts.AuthenticatorDescription;
import android.content.Context;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.BaseAccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.google.common.collect.Lists;
import java.util.Collections;

public class SimAccountType extends BaseAccountType {
    public boolean areContactsWritable() {
        return true;
    }

    public void initializeFieldsFromAuthenticator(AuthenticatorDescription authenticatorDescription) {
    }

    public boolean isGroupMembershipEditable() {
        return false;
    }

    public SimAccountType(Context context) {
        this.titleRes = R.string.account_sim;
        this.iconRes = R.drawable.quantum_ic_sim_card_vd_theme_24;
        try {
            addDataKindStructuredName(context);
            addDataKindName(context);
            DataKind addDataKindPhone = addDataKindPhone(context);
            addDataKindPhone.typeOverallMax = 1;
            addDataKindPhone.typeList = Collections.emptyList();
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindStructuredName(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind("vnd.android.cursor.item/name", R.string.nameLabelsGroup, -1, true));
        addKind.actionHeader = new BaseAccountType.SimpleInflater((int) R.string.nameLabelsGroup);
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeOverallMax = 1;
        addKind.fieldList = Lists.newArrayList();
        addKind.fieldList.add(new AccountType.EditField("data2", R.string.name_given, 8289));
        addKind.fieldList.add(new AccountType.EditField("data3", R.string.name_family, 8289));
        return addKind;
    }

    /* access modifiers changed from: protected */
    public DataKind addDataKindName(Context context) throws AccountType.DefinitionException {
        DataKind addKind = addKind(new DataKind(DataKind.PSEUDO_MIME_TYPE_NAME, R.string.nameLabelsGroup, -1, true));
        addKind.actionHeader = new BaseAccountType.SimpleInflater((int) R.string.nameLabelsGroup);
        addKind.actionBody = new BaseAccountType.SimpleInflater("data1");
        addKind.typeOverallMax = 1;
        boolean z = context.getResources().getBoolean(R.bool.config_editor_field_order_primary);
        addKind.fieldList = Lists.newArrayList();
        if (!z) {
            addKind.fieldList.add(new AccountType.EditField("data3", R.string.name_family, 8289));
            addKind.fieldList.add(new AccountType.EditField("data2", R.string.name_given, 8289));
        } else {
            addKind.fieldList.add(new AccountType.EditField("data2", R.string.name_given, 8289));
            addKind.fieldList.add(new AccountType.EditField("data3", R.string.name_family, 8289));
        }
        return addKind;
    }

    public AccountInfo wrapAccount(Context context, AccountWithDataSet accountWithDataSet) {
        return new AccountInfo(new AccountDisplayInfo(accountWithDataSet, getDisplayLabel(context), getDisplayLabel(context), getDisplayIcon(context), true), this);
    }
}
