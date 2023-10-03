package com.android.contacts.model.account;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public class AccountDisplayInfo {
    private final Drawable mIcon;
    private final boolean mIsDeviceAccount;
    private final CharSequence mName;
    private final AccountWithDataSet mSource;
    private final CharSequence mType;

    public AccountDisplayInfo(AccountWithDataSet accountWithDataSet, CharSequence charSequence, CharSequence charSequence2, Drawable drawable, boolean z) {
        this.mSource = accountWithDataSet;
        this.mName = charSequence;
        this.mType = charSequence2;
        this.mIcon = drawable;
        this.mIsDeviceAccount = z;
    }

    public AccountWithDataSet getSource() {
        return this.mSource;
    }

    public CharSequence getNameLabel() {
        return this.mName;
    }

    public CharSequence getTypeLabel() {
        return this.mType;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public boolean hasGoogleAccountType() {
        return GoogleAccountType.ACCOUNT_TYPE.equals(this.mSource.type);
    }

    public boolean isGoogleAccount() {
        return GoogleAccountType.ACCOUNT_TYPE.equals(this.mSource.type) && this.mSource.dataSet == null;
    }

    public boolean isDeviceAccount() {
        return this.mIsDeviceAccount;
    }

    public boolean hasDistinctName() {
        return !TextUtils.equals(this.mName, this.mType);
    }

    public AccountDisplayInfo withName(CharSequence charSequence) {
        return withNameAndType(charSequence, this.mType);
    }

    public AccountDisplayInfo withType(CharSequence charSequence) {
        return withNameAndType(this.mName, charSequence);
    }

    public AccountDisplayInfo withNameAndType(CharSequence charSequence, CharSequence charSequence2) {
        return new AccountDisplayInfo(this.mSource, charSequence, charSequence2, this.mIcon, this.mIsDeviceAccount);
    }

    public AccountDisplayInfo formatted(Context context, int i, int i2) {
        return new AccountDisplayInfo(this.mSource, context.getString(i, new Object[]{this.mName}), context.getString(i2, new Object[]{this.mType}), this.mIcon, this.mIsDeviceAccount);
    }

    public AccountDisplayInfo withFormattedName(Context context, int i) {
        return withName(context.getString(i, new Object[]{this.mName}));
    }
}
