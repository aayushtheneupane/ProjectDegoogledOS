package com.android.settings.wifi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.internal.PreferenceImageView;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.wifi.NetworkRequestErrorDialogFragment;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class NetworkRequestDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, WifiManager.NetworkRequestMatchCallback {
    static final String EXTRA_APP_NAME = "com.android.settings.wifi.extra.APP_NAME";
    private List<AccessPoint> mAccessPointList;
    private AccessPointAdapter mDialogAdapter;
    private FilterWifiTracker mFilterWifiTracker;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                removeMessages(0);
                NetworkRequestDialogFragment.this.stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.TIME_OUT);
            }
        }
    };
    private boolean mIsSpecifiedSsid;
    /* access modifiers changed from: private */
    public boolean mShowLimitedItem = true;
    private WifiManager.NetworkRequestUserSelectionCallback mUserSelectionCallback;
    /* access modifiers changed from: private */
    public boolean mWaitingConnectCallback;

    public int getMetricsCategory() {
        return 1373;
    }

    public static NetworkRequestDialogFragment newInstance() {
        return new NetworkRequestDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        View inflate = LayoutInflater.from(context).inflate(C1715R.layout.network_request_dialog_title, (ViewGroup) null);
        ((TextView) inflate.findViewById(C1715R.C1718id.network_request_title_text)).setText(getTitle());
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            this.mIsSpecifiedSsid = intent.getBooleanExtra("com.android.settings.wifi.extra.REQUEST_IS_FOR_SINGLE_NETWORK", false);
        }
        ((ProgressBar) inflate.findViewById(C1715R.C1718id.network_request_title_progress)).setVisibility(0);
        this.mDialogAdapter = new AccessPointAdapter(context, C1715R.layout.preference_access_point, getAccessPointList());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCustomTitle(inflate);
        builder.setAdapter(this.mDialogAdapter, this);
        builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                NetworkRequestDialogFragment.this.lambda$onCreateDialog$0$NetworkRequestDialogFragment(dialogInterface, i);
            }
        });
        builder.setNeutralButton(C1715R.string.network_connection_request_dialog_showall, (DialogInterface.OnClickListener) null);
        if (this.mIsSpecifiedSsid) {
            builder.setPositiveButton((int) C1715R.string.wifi_connect, (DialogInterface.OnClickListener) null);
        }
        AlertDialog create = builder.create();
        create.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener(create) {
            private final /* synthetic */ AlertDialog f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                NetworkRequestDialogFragment.this.lambda$onCreateDialog$1$NetworkRequestDialogFragment(this.f$1, adapterView, view, i, j);
            }
        });
        setCancelable(false);
        create.setOnShowListener(new DialogInterface.OnShowListener(create) {
            private final /* synthetic */ AlertDialog f$1;

            {
                this.f$1 = r2;
            }

            public final void onShow(DialogInterface dialogInterface) {
                NetworkRequestDialogFragment.this.lambda$onCreateDialog$4$NetworkRequestDialogFragment(this.f$1, dialogInterface);
            }
        });
        return create;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestDialogFragment(DialogInterface dialogInterface, int i) {
        onCancel(dialogInterface);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestDialogFragment(AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        onClick(alertDialog, i);
    }

    public /* synthetic */ void lambda$onCreateDialog$4$NetworkRequestDialogFragment(AlertDialog alertDialog, DialogInterface dialogInterface) {
        Button button = alertDialog.getButton(-3);
        button.setVisibility(8);
        button.setOnClickListener(new View.OnClickListener(button) {
            private final /* synthetic */ Button f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                NetworkRequestDialogFragment.this.lambda$onCreateDialog$2$NetworkRequestDialogFragment(this.f$1, view);
            }
        });
        if (this.mIsSpecifiedSsid) {
            Button button2 = alertDialog.getButton(-1);
            button2.setOnClickListener(new View.OnClickListener(alertDialog) {
                private final /* synthetic */ AlertDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    NetworkRequestDialogFragment.this.lambda$onCreateDialog$3$NetworkRequestDialogFragment(this.f$1, view);
                }
            });
            button2.setEnabled(false);
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestDialogFragment(Button button, View view) {
        this.mShowLimitedItem = false;
        renewAccessPointList((List<ScanResult>) null);
        notifyAdapterRefresh();
        button.setVisibility(8);
    }

    public /* synthetic */ void lambda$onCreateDialog$3$NetworkRequestDialogFragment(AlertDialog alertDialog, View view) {
        onClick(alertDialog, 0);
    }

    private String getTitle() {
        Intent intent = getActivity().getIntent();
        return getString(C1715R.string.network_connection_request_dialog_title, intent != null ? intent.getStringExtra(EXTRA_APP_NAME) : "");
    }

    /* access modifiers changed from: package-private */
    public List<AccessPoint> getAccessPointList() {
        if (this.mAccessPointList == null) {
            this.mAccessPointList = new ArrayList();
        }
        return this.mAccessPointList;
    }

    private BaseAdapter getDialogAdapter() {
        return this.mDialogAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        List<AccessPoint> accessPointList = getAccessPointList();
        if (accessPointList.size() != 0 && this.mUserSelectionCallback != null && i < accessPointList.size()) {
            AccessPoint accessPoint = accessPointList.get(i);
            WifiConfiguration config = accessPoint.getConfig();
            if (config == null) {
                config = WifiUtils.getWifiConfig(accessPoint, (ScanResult) null, (String) null);
            }
            if (config != null) {
                this.mUserSelectionCallback.select(config);
                this.mWaitingConnectCallback = true;
                updateConnectButton(false);
            }
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (getActivity() != null) {
            getActivity().finish();
        }
        WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback = this.mUserSelectionCallback;
        if (networkRequestUserSelectionCallback != null) {
            networkRequestUserSelectionCallback.reject();
        }
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeMessages(0);
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.unregisterNetworkRequestMatchCallback(this);
        }
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker != null) {
            filterWifiTracker.onPause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker != null) {
            filterWifiTracker.onDestroy();
            this.mFilterWifiTracker = null;
        }
    }

    /* access modifiers changed from: private */
    public void showAllButton() {
        Button button;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null && (button = alertDialog.getButton(-3)) != null) {
            button.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateConnectButton(boolean z) {
        AlertDialog alertDialog;
        Button button;
        if (this.mIsSpecifiedSsid && (alertDialog = (AlertDialog) getDialog()) != null && (button = alertDialog.getButton(-1)) != null) {
            button.setEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public void hideProgressIcon() {
        View findViewById;
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null && (findViewById = alertDialog.findViewById(C1715R.C1718id.network_request_title_progress)) != null) {
            findViewById.setVisibility(8);
        }
    }

    public void onResume() {
        super.onResume();
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(WifiManager.class);
        if (wifiManager != null) {
            wifiManager.registerNetworkRequestMatchCallback(this, this.mHandler);
        }
        this.mHandler.sendEmptyMessageDelayed(0, 30000);
        if (this.mFilterWifiTracker == null) {
            this.mFilterWifiTracker = new FilterWifiTracker(getContext(), getSettingsLifecycle());
        }
        this.mFilterWifiTracker.onResume();
    }

    /* access modifiers changed from: protected */
    public void stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE error_dialog_type) {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.isShowing()) {
            dismiss();
        }
        NetworkRequestErrorDialogFragment newInstance = NetworkRequestErrorDialogFragment.newInstance();
        newInstance.setRejectCallback(this.mUserSelectionCallback);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DIALOG_ERROR_TYPE", error_dialog_type);
        newInstance.setArguments(bundle);
        newInstance.show(getActivity().getSupportFragmentManager(), NetworkRequestDialogFragment.class.getSimpleName());
    }

    private class AccessPointAdapter extends ArrayAdapter<AccessPoint> {
        private final LayoutInflater mInflater;
        private final int mResourceId;

        public AccessPointAdapter(Context context, int i, List<AccessPoint> list) {
            super(context, i, list);
            this.mResourceId = i;
            this.mInflater = LayoutInflater.from(context);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(this.mResourceId, viewGroup, false);
                view.findViewById(C1715R.C1718id.two_target_divider).setVisibility(8);
            }
            AccessPoint accessPoint = (AccessPoint) getItem(i);
            TextView textView = (TextView) view.findViewById(16908310);
            if (textView != null) {
                textView.setSingleLine(false);
                textView.setText(accessPoint.getTitle());
            }
            TextView textView2 = (TextView) view.findViewById(16908304);
            if (textView2 != null) {
                String settingsSummary = accessPoint.getSettingsSummary();
                if (TextUtils.isEmpty(settingsSummary)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(settingsSummary);
                }
            }
            PreferenceImageView preferenceImageView = (PreferenceImageView) view.findViewById(16908294);
            int level = accessPoint.getLevel();
            if (preferenceImageView != null) {
                Drawable drawable = getContext().getDrawable(Utils.getWifiIconResource(level));
                drawable.setTintList(Utils.getColorAttr(getContext(), 16843817));
                preferenceImageView.setImageDrawable(drawable);
            }
            return view;
        }
    }

    public void onAbort() {
        stopScanningAndPopErrorDialog(NetworkRequestErrorDialogFragment.ERROR_DIALOG_TYPE.ABORT);
    }

    public void onUserSelectionCallbackRegistration(WifiManager.NetworkRequestUserSelectionCallback networkRequestUserSelectionCallback) {
        this.mUserSelectionCallback = networkRequestUserSelectionCallback;
    }

    public void onMatch(List<ScanResult> list) {
        if (list != null && list.size() > 0) {
            this.mHandler.removeMessages(0);
            renewAccessPointList(list);
            notifyAdapterRefresh();
        }
    }

    private void renewAccessPointList(List<ScanResult> list) {
        FilterWifiTracker filterWifiTracker = this.mFilterWifiTracker;
        if (filterWifiTracker != null) {
            if (list != null) {
                filterWifiTracker.updateKeys(list);
            }
            List<AccessPoint> accessPointList = getAccessPointList();
            accessPointList.clear();
            accessPointList.addAll(this.mFilterWifiTracker.getAccessPoints());
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyAdapterRefresh() {
        if (getDialogAdapter() != null) {
            getDialogAdapter().notifyDataSetChanged();
        }
    }

    public void onUserSelectionConnectSuccess(WifiConfiguration wifiConfiguration) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, C1715R.string.network_connection_connect_successful, 0).show();
            activity.finish();
        }
    }

    public void onUserSelectionConnectFailure(WifiConfiguration wifiConfiguration) {
        this.mWaitingConnectCallback = false;
        updateConnectButton(true);
    }

    private final class FilterWifiTracker {
        private final List<String> mAccessPointKeys;
        private final Context mContext;
        private WifiTracker.WifiListener mWifiListener = new WifiTracker.WifiListener() {
            public void onWifiStateChanged(int i) {
                NetworkRequestDialogFragment.this.notifyAdapterRefresh();
            }

            public void onConnectedChanged() {
                NetworkRequestDialogFragment.this.notifyAdapterRefresh();
            }

            public void onAccessPointsChanged() {
                NetworkRequestDialogFragment.this.notifyAdapterRefresh();
            }
        };
        private final WifiTracker mWifiTracker;

        public FilterWifiTracker(Context context, Lifecycle lifecycle) {
            this.mWifiTracker = WifiTrackerFactory.create(context, this.mWifiListener, lifecycle, true, true);
            this.mAccessPointKeys = new ArrayList();
            this.mContext = context;
        }

        public void updateKeys(List<ScanResult> list) {
            for (ScanResult key : list) {
                String key2 = AccessPoint.getKey(this.mContext, key);
                if (!this.mAccessPointKeys.contains(key2)) {
                    this.mAccessPointKeys.add(key2);
                }
            }
        }

        public List<AccessPoint> getAccessPoints() {
            List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (AccessPoint next : accessPoints) {
                if (this.mAccessPointKeys.contains(next.getKey())) {
                    arrayList.add(next);
                    i++;
                    if (NetworkRequestDialogFragment.this.mShowLimitedItem && i >= 5) {
                        break;
                    }
                }
            }
            if (NetworkRequestDialogFragment.this.mShowLimitedItem && i >= 5) {
                NetworkRequestDialogFragment.this.showAllButton();
            }
            if (i > 0) {
                NetworkRequestDialogFragment.this.hideProgressIcon();
            }
            if (i < 0) {
                NetworkRequestDialogFragment.this.updateConnectButton(false);
            } else if (!NetworkRequestDialogFragment.this.mWaitingConnectCallback) {
                NetworkRequestDialogFragment.this.updateConnectButton(true);
            }
            return arrayList;
        }

        public void onDestroy() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onDestroy();
            }
        }

        public void onResume() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onStart();
            }
        }

        public void onPause() {
            WifiTracker wifiTracker = this.mWifiTracker;
            if (wifiTracker != null) {
                wifiTracker.onStop();
            }
        }
    }
}
