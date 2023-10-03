package com.android.settings.vpn2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.net.IConnectivityManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.vpn2.AppDialog;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AppDialogFragment extends InstrumentedDialogFragment implements AppDialog.Listener {
    private Listener mListener;
    private PackageInfo mPackageInfo;
    private final IConnectivityManager mService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
    private UserManager mUserManager;

    public interface Listener {
        void onCancel();

        void onForget();
    }

    public int getMetricsCategory() {
        return 546;
    }

    public static void show(Fragment fragment, PackageInfo packageInfo, String str, boolean z, boolean z2) {
        if (z || z2) {
            show(fragment, (Listener) null, packageInfo, str, z, z2);
        }
    }

    public static void show(Fragment fragment, Listener listener, PackageInfo packageInfo, String str, boolean z, boolean z2) {
        if (fragment.isAdded()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("package", packageInfo);
            bundle.putString("label", str);
            bundle.putBoolean("managing", z);
            bundle.putBoolean("connected", z2);
            AppDialogFragment appDialogFragment = new AppDialogFragment();
            appDialogFragment.mListener = listener;
            appDialogFragment.setArguments(bundle);
            appDialogFragment.setTargetFragment(fragment, 0);
            appDialogFragment.show(fragment.getFragmentManager(), "vpnappdialog");
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserManager = UserManager.get(getContext());
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        String string = arguments.getString("label");
        boolean z = arguments.getBoolean("managing");
        boolean z2 = arguments.getBoolean("connected");
        this.mPackageInfo = (PackageInfo) arguments.getParcelable("package");
        if (z) {
            return new AppDialog(getActivity(), this, this.mPackageInfo, string);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle((CharSequence) string);
        builder.setMessage((CharSequence) getActivity().getString(C1715R.string.vpn_disconnect_confirm));
        builder.setNegativeButton((CharSequence) getActivity().getString(C1715R.string.vpn_cancel), (DialogInterface.OnClickListener) null);
        if (z2 && !isUiRestricted()) {
            builder.setPositiveButton((CharSequence) getActivity().getString(C1715R.string.vpn_disconnect), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppDialogFragment.this.onDisconnect(dialogInterface);
                }
            });
        }
        return builder.create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        dismiss();
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onCancel();
        }
        super.onCancel(dialogInterface);
    }

    public void onForget(DialogInterface dialogInterface) {
        if (!isUiRestricted()) {
            int userId = getUserId();
            try {
                this.mService.setVpnPackageAuthorization(this.mPackageInfo.packageName, userId, false);
                onDisconnect(dialogInterface);
            } catch (RemoteException e) {
                Log.e("AppDialogFragment", "Failed to forget authorization of " + this.mPackageInfo.packageName + " for user " + userId, e);
            }
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onForget();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onDisconnect(DialogInterface dialogInterface) {
        if (!isUiRestricted()) {
            int userId = getUserId();
            try {
                if (this.mPackageInfo.packageName.equals(VpnUtils.getConnectedPackage(this.mService, userId))) {
                    this.mService.setAlwaysOnVpnPackage(userId, (String) null, false, (List) null);
                    this.mService.prepareVpn(this.mPackageInfo.packageName, "[Legacy VPN]", userId);
                }
            } catch (RemoteException e) {
                Log.e("AppDialogFragment", "Failed to disconnect package " + this.mPackageInfo.packageName + " for user " + userId, e);
            }
        }
    }

    private boolean isUiRestricted() {
        return this.mUserManager.hasUserRestriction("no_config_vpn", UserHandle.of(getUserId()));
    }

    private int getUserId() {
        return UserHandle.getUserId(this.mPackageInfo.applicationInfo.uid);
    }
}
