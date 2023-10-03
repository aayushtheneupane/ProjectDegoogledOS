package com.android.contacts.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;

public class ContactListFilterView extends LinearLayout {
    private static final String TAG = "ContactListFilterView";
    private TextView mAccountType;
    private TextView mAccountUserName;
    private ContactListFilter mFilter;
    private ImageView mIcon;
    private RadioButton mRadioButton;
    private boolean mSingleAccount;

    public ContactListFilterView(Context context) {
        super(context);
    }

    public ContactListFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setContactListFilter(ContactListFilter contactListFilter) {
        this.mFilter = contactListFilter;
    }

    public void setSingleAccount(boolean z) {
        this.mSingleAccount = z;
    }

    public void setActivated(boolean z) {
        super.setActivated(z);
        RadioButton radioButton = this.mRadioButton;
        if (radioButton != null) {
            radioButton.setChecked(z);
        } else {
            Log.wtf(TAG, "radio-button cannot be activated because it is null");
        }
        setContentDescription(generateContentDescription());
    }

    public boolean isChecked() {
        return this.mRadioButton.isChecked();
    }

    public void bindView(AccountTypeManager accountTypeManager) {
        if (this.mAccountType == null) {
            this.mIcon = (ImageView) findViewById(R.id.icon);
            this.mAccountType = (TextView) findViewById(R.id.accountType);
            this.mAccountUserName = (TextView) findViewById(R.id.accountUserName);
            this.mRadioButton = (RadioButton) findViewById(R.id.radioButton);
            this.mRadioButton.setChecked(isActivated());
        }
        if (this.mFilter == null) {
            this.mAccountType.setText(R.string.contactsList);
            return;
        }
        this.mAccountUserName.setVisibility(8);
        int i = this.mFilter.filterType;
        if (i == -6) {
            bindView(0, R.string.list_filter_single);
        } else if (i == -5) {
            bindView(0, R.string.list_filter_phones);
        } else if (i == -4) {
            bindView(R.drawable.quantum_ic_star_vd_theme_24, R.string.list_filter_all_starred);
        } else if (i == -3) {
            bindView(0, R.string.list_filter_customize);
        } else if (i == -2) {
            bindView(0, R.string.list_filter_all_accounts);
        } else if (i == 0) {
            this.mAccountUserName.setVisibility(0);
            this.mIcon.setVisibility(0);
            Drawable drawable = this.mFilter.icon;
            if (drawable != null) {
                this.mIcon.setImageDrawable(drawable);
            } else {
                this.mIcon.setImageResource(R.drawable.unknown_source);
            }
            ContactListFilter contactListFilter = this.mFilter;
            AccountType accountType = accountTypeManager.getAccountType(contactListFilter.accountType, contactListFilter.dataSet);
            this.mAccountUserName.setText(this.mFilter.accountName);
            this.mAccountType.setText(accountType.getDisplayLabel(getContext()));
        }
        setContentDescription(generateContentDescription());
    }

    private void bindView(int i, int i2) {
        if (i != 0) {
            this.mIcon.setVisibility(0);
            this.mIcon.setImageResource(i);
        } else {
            this.mIcon.setVisibility(8);
        }
        this.mAccountType.setText(i2);
    }

    /* access modifiers changed from: package-private */
    public String generateContentDescription() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.mAccountType.getText())) {
            sb.append(this.mAccountType.getText());
        }
        if (!TextUtils.isEmpty(this.mAccountUserName.getText())) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(this.mAccountUserName.getText());
        }
        return getContext().getString(isActivated() ? R.string.account_filter_view_checked : R.string.account_filter_view_not_checked, new Object[]{sb.toString()});
    }
}
