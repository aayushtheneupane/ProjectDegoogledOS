package com.android.contacts.editor;

import android.text.TextUtils;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.dataitem.DataKind;
import java.util.ArrayList;
import java.util.List;

public final class KindSectionData {
    private final AccountType mAccountType;
    private final DataKind mDataKind;
    private final RawContactDelta mRawContactDelta;

    public KindSectionData(AccountType accountType, DataKind dataKind, RawContactDelta rawContactDelta) {
        this.mAccountType = accountType;
        this.mDataKind = dataKind;
        this.mRawContactDelta = rawContactDelta;
    }

    public AccountType getAccountType() {
        return this.mAccountType;
    }

    public List<ValuesDelta> getValuesDeltas() {
        ArrayList<ValuesDelta> mimeEntries = this.mRawContactDelta.getMimeEntries(this.mDataKind.mimeType);
        return mimeEntries == null ? new ArrayList() : mimeEntries;
    }

    public List<ValuesDelta> getVisibleValuesDeltas() {
        ArrayList arrayList = new ArrayList();
        for (ValuesDelta next : getValuesDeltas()) {
            if (next.isVisible() && !next.isDelete()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<ValuesDelta> getNonEmptyValuesDeltas() {
        ArrayList arrayList = new ArrayList();
        for (ValuesDelta next : getValuesDeltas()) {
            if (!isEmpty(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean isEmpty(ValuesDelta valuesDelta) {
        List<AccountType.EditField> list = this.mDataKind.fieldList;
        if (list == null) {
            return true;
        }
        for (AccountType.EditField editField : list) {
            if (!TextUtils.isEmpty(valuesDelta.getAsString(editField.column))) {
                return false;
            }
        }
        return true;
    }

    public DataKind getDataKind() {
        return this.mDataKind;
    }

    public RawContactDelta getRawContactDelta() {
        return this.mRawContactDelta;
    }

    public String getMimeType() {
        return this.mDataKind.mimeType;
    }
}
