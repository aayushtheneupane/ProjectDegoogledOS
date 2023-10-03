package com.android.contacts.common.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.protos.ProtoParsers;
import com.android.dialer.telecom.TelecomUtil;
import com.google.common.base.Optional;

public class SelectPhoneAccountDialogFragment extends DialogFragment {
    public static final String ARG_OPTIONS = "options";
    /* access modifiers changed from: private */
    public boolean isDefaultChecked;
    /* access modifiers changed from: private */
    public boolean isSelected;
    /* access modifiers changed from: private */
    public SelectPhoneAccountListener listener;
    /* access modifiers changed from: private */
    public SelectPhoneAccountDialogOptions options = SelectPhoneAccountDialogOptions.getDefaultInstance();

    static class SelectAccountListAdapter extends ArrayAdapter<SelectPhoneAccountDialogOptions.Entry> {
        private int mResId;
        private final SelectPhoneAccountDialogOptions options;

        static final class ViewHolder {
            TextView hintTextView;
            ImageView imageView;
            TextView labelTextView;
            TextView numberTextView;

            ViewHolder() {
            }
        }

        SelectAccountListAdapter(Context context, int i, SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions) {
            super(context, i, selectPhoneAccountDialogOptions.getEntriesList());
            this.options = selectPhoneAccountDialogOptions;
            this.mResId = i;
        }

        public boolean areAllItemsEnabled() {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            Icon icon;
            String str;
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
            Drawable drawable = null;
            if (view == null) {
                view = layoutInflater.inflate(this.mResId, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.labelTextView = (TextView) view.findViewById(R.id.label);
                viewHolder.numberTextView = (TextView) view.findViewById(R.id.number);
                viewHolder.hintTextView = (TextView) view.findViewById(R.id.hint);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            SelectPhoneAccountDialogOptions.Entry entry = (SelectPhoneAccountDialogOptions.Entry) getItem(i);
            PhoneAccountHandle composePhoneAccountHandle = TelecomUtil.composePhoneAccountHandle(entry.getPhoneAccountHandleComponentName(), entry.getPhoneAccountHandleId());
            Assert.isNotNull(composePhoneAccountHandle);
            PhoneAccount phoneAccount = ((TelecomManager) getContext().getSystemService(TelecomManager.class)).getPhoneAccount(composePhoneAccountHandle);
            if (phoneAccount == null) {
                return view;
            }
            viewHolder.labelTextView.setText(phoneAccount.getLabel());
            if (phoneAccount.getAddress() == null || TextUtils.isEmpty(phoneAccount.getAddress().getSchemeSpecificPart())) {
                viewHolder.numberTextView.setVisibility(8);
            } else {
                viewHolder.numberTextView.setVisibility(0);
                TextView textView = viewHolder.numberTextView;
                Context context = getContext();
                String schemeSpecificPart = phoneAccount.getAddress().getSchemeSpecificPart();
                Context context2 = getContext();
                Optional<SubscriptionInfo> subscriptionInfo = TelecomUtil.getSubscriptionInfo(context2, composePhoneAccountHandle);
                if (!subscriptionInfo.isPresent()) {
                    str = R$style.getCurrentCountryIso(context2);
                } else {
                    str = subscriptionInfo.get().getCountryIso().toUpperCase();
                }
                textView.setText(PhoneNumberHelper.formatNumberForDisplay(context, schemeSpecificPart, str));
            }
            ImageView imageView = viewHolder.imageView;
            Context context3 = getContext();
            if (!(context3 == null || (icon = phoneAccount.getIcon()) == null)) {
                drawable = icon.loadDrawable(context3);
            }
            imageView.setImageDrawable(drawable);
            if (TextUtils.isEmpty(entry.getHint())) {
                viewHolder.hintTextView.setVisibility(8);
            } else {
                viewHolder.hintTextView.setVisibility(0);
                viewHolder.hintTextView.setText(entry.getHint());
            }
            viewHolder.labelTextView.setEnabled(entry.getEnabled());
            viewHolder.numberTextView.setEnabled(entry.getEnabled());
            viewHolder.hintTextView.setEnabled(entry.getEnabled());
            viewHolder.imageView.setImageAlpha(entry.getEnabled() ? 255 : 97);
            return view;
        }

        public boolean isEnabled(int i) {
            return this.options.getEntries(i).getEnabled();
        }
    }

    public static class SelectPhoneAccountListener extends ResultReceiver {
        protected SelectPhoneAccountListener() {
            super(new Handler());
        }

        public void onDialogDismissed(String str) {
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            if (i == 1) {
                onPhoneAccountSelected((PhoneAccountHandle) bundle.getParcelable("extra_selected_account_handle"), bundle.getBoolean("extra_set_default"), bundle.getString("extra_call_id"));
            } else if (i == 2) {
                onDialogDismissed(bundle.getString("extra_call_id"));
            }
        }
    }

    public static SelectPhoneAccountDialogFragment newInstance(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, SelectPhoneAccountListener selectPhoneAccountListener) {
        SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment = new SelectPhoneAccountDialogFragment();
        selectPhoneAccountDialogFragment.setListener(selectPhoneAccountListener);
        Bundle bundle = new Bundle();
        Assert.isNotNull(selectPhoneAccountDialogOptions);
        Assert.isNotNull(bundle);
        Assert.isNotNull(ARG_OPTIONS);
        bundle.putByteArray(ARG_OPTIONS, selectPhoneAccountDialogOptions.toByteArray());
        selectPhoneAccountDialogFragment.setArguments(bundle);
        return selectPhoneAccountDialogFragment;
    }

    public boolean canSetDefault() {
        return this.options.getCanSetDefault();
    }

    public SelectPhoneAccountListener getListener() {
        return this.listener;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (!this.isSelected && this.listener != null) {
            Bundle bundle = new Bundle();
            bundle.putString("extra_call_id", this.options.getCallId());
            this.listener.onReceiveResult(2, bundle);
        }
        super.onCancel(dialogInterface);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.options = (SelectPhoneAccountDialogOptions) ProtoParsers.getTrusted(getArguments(), ARG_OPTIONS, SelectPhoneAccountDialogOptions.getDefaultInstance());
        if (bundle != null) {
            this.isDefaultChecked = bundle.getBoolean("is_default_checked");
        }
        this.isSelected = false;
        C02721 r7 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean unused = SelectPhoneAccountDialogFragment.this.isSelected = true;
                SelectPhoneAccountDialogOptions.Entry entry = SelectPhoneAccountDialogFragment.this.options.getEntriesList().get(i);
                PhoneAccountHandle composePhoneAccountHandle = TelecomUtil.composePhoneAccountHandle(entry.getPhoneAccountHandleComponentName(), entry.getPhoneAccountHandleId());
                Assert.isNotNull(composePhoneAccountHandle);
                Bundle bundle = new Bundle();
                bundle.putParcelable("extra_selected_account_handle", composePhoneAccountHandle);
                bundle.putBoolean("extra_set_default", SelectPhoneAccountDialogFragment.this.isDefaultChecked);
                bundle.putString("extra_call_id", SelectPhoneAccountDialogFragment.this.options.getCallId());
                if (SelectPhoneAccountDialogFragment.this.listener != null) {
                    SelectPhoneAccountDialogFragment.this.listener.onReceiveResult(1, bundle);
                }
            }
        };
        C02732 r0 = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = SelectPhoneAccountDialogFragment.this.isDefaultChecked = z;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog create = builder.setTitle(this.options.hasTitle() ? this.options.getTitle() : R.string.select_account_dialog_title).setAdapter(new SelectAccountListAdapter(builder.getContext(), R.layout.select_account_list_item, this.options), r7).create();
        if (this.options.getCanSetDefault()) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(builder.getContext()).inflate(R.layout.default_account_checkbox, (ViewGroup) null);
            CheckBox checkBox = (CheckBox) linearLayout.findViewById(R.id.default_account_checkbox_view);
            checkBox.setOnCheckedChangeListener(r0);
            checkBox.setChecked(this.isDefaultChecked);
            TextView textView = (TextView) linearLayout.findViewById(R.id.default_account_checkbox_text);
            int setDefaultLabel = this.options.hasSetDefaultLabel() ? this.options.getSetDefaultLabel() : R.string.set_default_account;
            textView.setText(setDefaultLabel);
            textView.setOnClickListener(new View.OnClickListener(checkBox) {
                private final /* synthetic */ CheckBox f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(View view) {
                    this.f$0.performClick();
                }
            });
            linearLayout.setOnClickListener(new View.OnClickListener(checkBox) {
                private final /* synthetic */ CheckBox f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(View view) {
                    this.f$0.performClick();
                }
            });
            linearLayout.setContentDescription(getString(setDefaultLabel));
            create.getListView().addFooterView(linearLayout);
        }
        return create;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("is_default_checked", this.isDefaultChecked);
    }

    public void setListener(SelectPhoneAccountListener selectPhoneAccountListener) {
        this.listener = selectPhoneAccountListener;
    }
}
