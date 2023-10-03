package com.android.contacts.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.vcard.ImportVCardActivity;
import java.util.List;

public class AccountSelectionUtil {
    public static Uri mPath = null;
    public static boolean mVCardShare = false;

    public static class AccountSelectedListener implements DialogInterface.OnClickListener {
        protected final List<AccountWithDataSet> mAccountList;
        private final Activity mActivity;
        private final int mResId;
        private final int mSubscriptionId;

        public AccountSelectedListener(Activity activity, List<AccountWithDataSet> list, int i, int i2) {
            if (list == null || list.size() == 0) {
                Log.e("AccountSelectionUtil", "The size of Account list is 0.");
            }
            this.mActivity = activity;
            this.mAccountList = list;
            this.mResId = i;
            this.mSubscriptionId = i2;
        }

        public AccountSelectedListener(Activity activity, List<AccountWithDataSet> list, int i) {
            this(activity, list, i, -1);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            AccountSelectionUtil.doImport(this.mActivity, this.mResId, this.mAccountList.get(i), this.mSubscriptionId);
        }
    }

    public static Dialog getSelectAccountDialog(Activity activity, int i, DialogInterface.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
        final AccountTypeManager instance = AccountTypeManager.getInstance(activity);
        List<AccountWithDataSet> blockForWritableAccounts = instance.blockForWritableAccounts();
        Log.i("AccountSelectionUtil", "The number of available accounts: " + blockForWritableAccounts.size());
        final LayoutInflater layoutInflater = (LayoutInflater) new ContextThemeWrapper(activity, 16973836).getSystemService("layout_inflater");
        C04531 r0 = new ArrayAdapter<AccountWithDataSet>(activity, R.layout.account_selector_list_item_condensed, blockForWritableAccounts) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                int i2 = 0;
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.account_selector_list_item_condensed, viewGroup, false);
                }
                TextView textView = (TextView) view.findViewById(16908309);
                ImageView imageView = (ImageView) view.findViewById(16908294);
                AccountWithDataSet accountWithDataSet = (AccountWithDataSet) getItem(i);
                AccountType accountType = instance.getAccountType(accountWithDataSet.type, accountWithDataSet.dataSet);
                Context context = getContext();
                ((TextView) view.findViewById(16908308)).setText(accountType.getDisplayLabel(context));
                textView.setText(accountWithDataSet.name);
                if (context.getPackageName().equals(accountWithDataSet.type)) {
                    i2 = 8;
                }
                textView.setVisibility(i2);
                imageView.setImageDrawable(accountType.getDisplayIcon(getContext()));
                return view;
            }
        };
        if (onClickListener == null) {
            onClickListener = new AccountSelectedListener(activity, blockForWritableAccounts, i);
        }
        if (onCancelListener == null) {
            onCancelListener = new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            };
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        TextView textView = (TextView) View.inflate(activity, R.layout.dialog_title, (ViewGroup) null);
        textView.setText(R.string.dialog_new_contact_account);
        builder.setCustomTitle(textView);
        builder.setSingleChoiceItems(r0, 0, onClickListener);
        builder.setOnCancelListener(onCancelListener);
        return builder.create();
    }

    public static void doImport(Activity activity, int i, AccountWithDataSet accountWithDataSet, int i2) {
        if (i == R.string.import_from_sim) {
            doImportFromSim(activity, accountWithDataSet, i2);
        } else if (i == R.string.import_from_vcf_file) {
            doImportFromVcfFile(activity, accountWithDataSet);
        }
    }

    public static void doImportFromSim(Context context, AccountWithDataSet accountWithDataSet, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("vnd.android.cursor.item/sim-contact");
        if (accountWithDataSet != null) {
            intent.putExtra("account_name", accountWithDataSet.name);
            intent.putExtra("account_type", accountWithDataSet.type);
            intent.putExtra("data_set", accountWithDataSet.dataSet);
        }
        intent.putExtra("subscription_id", Integer.valueOf(i));
        intent.setClassName("com.android.phone", "com.android.phone.SimContacts");
        context.startActivity(intent);
    }

    public static void doImportFromVcfFile(Activity activity, AccountWithDataSet accountWithDataSet) {
        Intent intent = new Intent(activity, ImportVCardActivity.class);
        if (accountWithDataSet != null) {
            intent.putExtra("account_name", accountWithDataSet.name);
            intent.putExtra("account_type", accountWithDataSet.type);
            intent.putExtra("data_set", accountWithDataSet.dataSet);
        }
        if (mVCardShare) {
            intent.setAction("android.intent.action.VIEW");
            intent.setData(mPath);
        }
        mVCardShare = false;
        mPath = null;
        activity.startActivityForResult(intent, 0);
    }
}
