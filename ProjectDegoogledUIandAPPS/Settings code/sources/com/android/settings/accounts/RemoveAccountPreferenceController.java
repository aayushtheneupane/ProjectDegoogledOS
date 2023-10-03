package com.android.settings.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceScreen;
import com.android.settings.accounts.RemoveAccountPreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.io.IOException;

public class RemoveAccountPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, View.OnClickListener {
    private Account mAccount;
    private Fragment mParentFragment;
    private UserHandle mUserHandle;

    public String getPreferenceKey() {
        return "remove_account";
    }

    public boolean isAvailable() {
        return true;
    }

    public RemoveAccountPreferenceController(Context context, Fragment fragment) {
        super(context);
        this.mParentFragment = fragment;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((Button) ((LayoutPreference) preferenceScreen.findPreference("remove_account")).findViewById(C1715R.C1718id.button)).setOnClickListener(this);
    }

    public void onClick(View view) {
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced;
        UserHandle userHandle = this.mUserHandle;
        if (userHandle == null || (checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_modify_accounts", userHandle.getIdentifier())) == null) {
            ConfirmRemoveAccountDialog.show(this.mParentFragment, this.mAccount, this.mUserHandle);
        } else {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mContext, checkIfRestrictionEnforced);
        }
    }

    public void init(Account account, UserHandle userHandle) {
        this.mAccount = account;
        this.mUserHandle = userHandle;
    }

    public static class ConfirmRemoveAccountDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private Account mAccount;
        private UserHandle mUserHandle;

        public int getMetricsCategory() {
            return 585;
        }

        public static ConfirmRemoveAccountDialog show(Fragment fragment, Account account, UserHandle userHandle) {
            if (!fragment.isAdded()) {
                return null;
            }
            ConfirmRemoveAccountDialog confirmRemoveAccountDialog = new ConfirmRemoveAccountDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable("account", account);
            bundle.putParcelable("android.intent.extra.USER", userHandle);
            confirmRemoveAccountDialog.setArguments(bundle);
            confirmRemoveAccountDialog.setTargetFragment(fragment, 0);
            confirmRemoveAccountDialog.show(fragment.getFragmentManager(), "confirmRemoveAccount");
            return confirmRemoveAccountDialog;
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            this.mAccount = (Account) arguments.getParcelable("account");
            this.mUserHandle = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.really_remove_account_title);
            builder.setMessage((int) C1715R.string.really_remove_account_message);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            builder.setPositiveButton((int) C1715R.string.remove_account_label, (DialogInterface.OnClickListener) this);
            return builder.create();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            FragmentActivity activity = getTargetFragment().getActivity();
            AccountManager.get(activity).removeAccountAsUser(this.mAccount, activity, new AccountManagerCallback() {
                public final void run(AccountManagerFuture accountManagerFuture) {
                    RemoveAccountPreferenceController.ConfirmRemoveAccountDialog.this.mo7870xb3366741(accountManagerFuture);
                }
            }, (Handler) null, this.mUserHandle);
        }

        /* renamed from: lambda$onClick$0$RemoveAccountPreferenceController$ConfirmRemoveAccountDialog */
        public /* synthetic */ void mo7870xb3366741(AccountManagerFuture accountManagerFuture) {
            FragmentActivity activity = getTargetFragment().getActivity();
            if (activity == null || activity.isFinishing()) {
                Log.w("PrefControllerMixin", "Activity is no longer alive, skipping results");
                return;
            }
            boolean z = true;
            try {
                z = true ^ ((Bundle) accountManagerFuture.getResult()).getBoolean("booleanResult");
            } catch (AuthenticatorException | OperationCanceledException | IOException unused) {
            }
            if (z) {
                RemoveAccountFailureDialog.show(getTargetFragment());
            } else {
                activity.finish();
            }
        }
    }

    public static class RemoveAccountFailureDialog extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 586;
        }

        public static void show(Fragment fragment) {
            if (fragment.isAdded()) {
                RemoveAccountFailureDialog removeAccountFailureDialog = new RemoveAccountFailureDialog();
                removeAccountFailureDialog.setTargetFragment(fragment, 0);
                try {
                    removeAccountFailureDialog.show(fragment.getFragmentManager(), "removeAccountFailed");
                } catch (IllegalStateException e) {
                    Log.w("PrefControllerMixin", "Can't show RemoveAccountFailureDialog. " + e.getMessage());
                }
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.really_remove_account_title);
            builder.setMessage((int) C1715R.string.remove_account_failed);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
