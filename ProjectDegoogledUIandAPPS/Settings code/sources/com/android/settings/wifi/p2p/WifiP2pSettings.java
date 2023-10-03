package com.android.settings.wifi.p2p;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pGroupList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class WifiP2pSettings extends DashboardFragment implements WifiP2pManager.PersistentGroupInfoListener, WifiP2pManager.PeerListListener, WifiP2pManager.DeviceInfoListener {
    private DialogInterface.OnClickListener mCancelConnectListener;
    /* access modifiers changed from: private */
    public WifiP2pManager.Channel mChannel;
    private int mConnectedDevices;
    private DialogInterface.OnClickListener mDeleteGroupListener;
    /* access modifiers changed from: private */
    public EditText mDeviceNameText;
    private DialogInterface.OnClickListener mDisconnectListener;
    private final IntentFilter mIntentFilter = new IntentFilter();
    /* access modifiers changed from: private */
    public boolean mIsIgnoreInitConnectionInfoCallback = false;
    /* access modifiers changed from: private */
    public boolean mLastGroupFormed = false;
    private P2pPeerCategoryPreferenceController mPeerCategoryController;
    /* access modifiers changed from: private */
    public WifiP2pDeviceList mPeers = new WifiP2pDeviceList();
    private P2pPersistentCategoryPreferenceController mPersistentCategoryController;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean z = false;
            if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
                WifiP2pSettings wifiP2pSettings = WifiP2pSettings.this;
                if (intent.getIntExtra("wifi_p2p_state", 1) == 2) {
                    z = true;
                }
                boolean unused = wifiP2pSettings.mWifiP2pEnabled = z;
                WifiP2pSettings.this.handleP2pStateChanged();
            } else if ("android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
                WifiP2pDeviceList unused2 = WifiP2pSettings.this.mPeers = (WifiP2pDeviceList) intent.getParcelableExtra("wifiP2pDeviceList");
                WifiP2pSettings.this.handlePeersChanged();
            } else if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
                if (WifiP2pSettings.this.mWifiP2pManager != null) {
                    WifiP2pInfo wifiP2pInfo = (WifiP2pInfo) intent.getParcelableExtra("wifiP2pInfo");
                    if (!((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected() && !WifiP2pSettings.this.mLastGroupFormed) {
                        WifiP2pSettings.this.startSearch();
                    }
                    boolean unused3 = WifiP2pSettings.this.mLastGroupFormed = wifiP2pInfo.groupFormed;
                    boolean unused4 = WifiP2pSettings.this.mIsIgnoreInitConnectionInfoCallback = true;
                }
            } else if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
                WifiP2pSettings.this.mWifiP2pManager.requestDeviceInfo(WifiP2pSettings.this.mChannel, WifiP2pSettings.this);
            } else if ("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE".equals(action)) {
                if (intent.getIntExtra("discoveryState", 1) == 2) {
                    WifiP2pSettings.this.updateSearchMenu(true);
                } else {
                    WifiP2pSettings.this.updateSearchMenu(false);
                }
            } else if ("android.net.wifi.p2p.PERSISTENT_GROUPS_CHANGED".equals(action) && WifiP2pSettings.this.mWifiP2pManager != null) {
                WifiP2pSettings.this.mWifiP2pManager.requestPersistentGroupInfo(WifiP2pSettings.this.mChannel, WifiP2pSettings.this);
            }
        }
    };
    private DialogInterface.OnClickListener mRenameListener;
    private String mSavedDeviceName;
    /* access modifiers changed from: private */
    public WifiP2pPersistentGroup mSelectedGroup;
    private String mSelectedGroupName;
    private WifiP2pPeer mSelectedWifiPeer;
    private WifiP2pDevice mThisDevice;
    private P2pThisDevicePreferenceController mThisDevicePreferenceController;
    /* access modifiers changed from: private */
    public boolean mWifiP2pEnabled;
    /* access modifiers changed from: private */
    public WifiP2pManager mWifiP2pManager;
    private boolean mWifiP2pSearching;

    public int getDialogMetricsCategory(int i) {
        if (i == 1) {
            return 575;
        }
        if (i == 2) {
            return 576;
        }
        if (i != 3) {
            return i != 4 ? 0 : 578;
        }
        return 577;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_wifi_p2p;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "WifiP2pSettings";
    }

    public int getMetricsCategory() {
        return 109;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.wifi_p2p_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mPersistentCategoryController = new P2pPersistentCategoryPreferenceController(context);
        this.mPeerCategoryController = new P2pPeerCategoryPreferenceController(context);
        this.mThisDevicePreferenceController = new P2pThisDevicePreferenceController(context);
        arrayList.add(this.mPersistentCategoryController);
        arrayList.add(this.mPeerCategoryController);
        arrayList.add(this.mThisDevicePreferenceController);
        return arrayList;
    }

    public void onActivityCreated(Bundle bundle) {
        FragmentActivity activity = getActivity();
        this.mWifiP2pManager = (WifiP2pManager) getSystemService("wifip2p");
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null) {
            this.mChannel = wifiP2pManager.initialize(activity.getApplicationContext(), getActivity().getMainLooper(), (WifiP2pManager.ChannelListener) null);
            if (this.mChannel == null) {
                Log.e("WifiP2pSettings", "Failed to set up connection with wifi p2p service");
                this.mWifiP2pManager = null;
            }
        } else {
            Log.e("WifiP2pSettings", "mWifiP2pManager is null !");
        }
        if (bundle != null && bundle.containsKey("PEER_STATE")) {
            this.mSelectedWifiPeer = new WifiP2pPeer(getPrefContext(), (WifiP2pDevice) bundle.getParcelable("PEER_STATE"));
        }
        if (bundle != null && bundle.containsKey("DEV_NAME")) {
            this.mSavedDeviceName = bundle.getString("DEV_NAME");
        }
        if (bundle != null && bundle.containsKey("GROUP_NAME")) {
            this.mSelectedGroupName = bundle.getString("GROUP_NAME");
        }
        this.mRenameListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1 && WifiP2pSettings.this.mWifiP2pManager != null) {
                    String obj = WifiP2pSettings.this.mDeviceNameText.getText().toString();
                    if (obj != null) {
                        int i2 = 0;
                        while (i2 < obj.length()) {
                            char charAt = obj.charAt(i2);
                            if (Character.isDigit(charAt) || Character.isLetter(charAt) || charAt == '-' || charAt == '_' || charAt == ' ') {
                                i2++;
                            } else {
                                Toast.makeText(WifiP2pSettings.this.getActivity(), C1715R.string.wifi_p2p_failed_rename_message, 1).show();
                                return;
                            }
                        }
                    }
                    WifiP2pSettings.this.mWifiP2pManager.setDeviceName(WifiP2pSettings.this.mChannel, WifiP2pSettings.this.mDeviceNameText.getText().toString(), new WifiP2pManager.ActionListener() {
                        public void onSuccess() {
                        }

                        public void onFailure(int i) {
                            Toast.makeText(WifiP2pSettings.this.getActivity(), C1715R.string.wifi_p2p_failed_rename_message, 1).show();
                        }
                    });
                }
            }
        };
        this.mDisconnectListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1 && WifiP2pSettings.this.mWifiP2pManager != null) {
                    WifiP2pSettings.this.mWifiP2pManager.removeGroup(WifiP2pSettings.this.mChannel, new WifiP2pManager.ActionListener() {
                        public void onFailure(int i) {
                        }

                        public void onSuccess() {
                        }
                    });
                }
            }
        };
        this.mCancelConnectListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1 && WifiP2pSettings.this.mWifiP2pManager != null) {
                    WifiP2pSettings.this.mWifiP2pManager.cancelConnect(WifiP2pSettings.this.mChannel, new WifiP2pManager.ActionListener() {
                        public void onFailure(int i) {
                        }

                        public void onSuccess() {
                        }
                    });
                }
            }
        };
        this.mDeleteGroupListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    if (WifiP2pSettings.this.mWifiP2pManager != null && WifiP2pSettings.this.mSelectedGroup != null) {
                        WifiP2pSettings.this.mWifiP2pManager.deletePersistentGroup(WifiP2pSettings.this.mChannel, WifiP2pSettings.this.mSelectedGroup.getNetworkId(), new WifiP2pManager.ActionListener() {
                            public void onFailure(int i) {
                            }

                            public void onSuccess() {
                            }
                        });
                        WifiP2pPersistentGroup unused = WifiP2pSettings.this.mSelectedGroup = null;
                    }
                } else if (i == -2) {
                    WifiP2pPersistentGroup unused2 = WifiP2pSettings.this.mSelectedGroup = null;
                }
            }
        };
        super.onActivityCreated(bundle);
    }

    public void onResume() {
        super.onResume();
        this.mIntentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.p2p.DISCOVERY_STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.p2p.PERSISTENT_GROUPS_CHANGED");
        getPreferenceScreen();
        getActivity().registerReceiver(this.mReceiver, this.mIntentFilter);
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null) {
            wifiP2pManager.requestPeers(this.mChannel, this);
            this.mWifiP2pManager.requestDeviceInfo(this.mChannel, this);
            this.mIsIgnoreInitConnectionInfoCallback = false;
            this.mWifiP2pManager.requestNetworkInfo(this.mChannel, new WifiP2pManager.NetworkInfoListener() {
                public final void onNetworkInfoAvailable(NetworkInfo networkInfo) {
                    WifiP2pSettings.this.lambda$onResume$1$WifiP2pSettings(networkInfo);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onResume$1$WifiP2pSettings(NetworkInfo networkInfo) {
        this.mWifiP2pManager.requestConnectionInfo(this.mChannel, new WifiP2pManager.ConnectionInfoListener(networkInfo) {
            private final /* synthetic */ NetworkInfo f$1;

            {
                this.f$1 = r2;
            }

            public final void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
                WifiP2pSettings.this.lambda$onResume$0$WifiP2pSettings(this.f$1, wifiP2pInfo);
            }
        });
    }

    public /* synthetic */ void lambda$onResume$0$WifiP2pSettings(NetworkInfo networkInfo, WifiP2pInfo wifiP2pInfo) {
        if (!this.mIsIgnoreInitConnectionInfoCallback) {
            if (!networkInfo.isConnected() && !this.mLastGroupFormed) {
                startSearch();
            }
            this.mLastGroupFormed = wifiP2pInfo.groupFormed;
        }
    }

    public void onPause() {
        super.onPause();
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null) {
            wifiP2pManager.stopPeerDiscovery(this.mChannel, (WifiP2pManager.ActionListener) null);
        }
        getActivity().unregisterReceiver(this.mReceiver);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 1, 0, this.mWifiP2pSearching ? C1715R.string.wifi_p2p_menu_searching : C1715R.string.wifi_p2p_menu_search).setEnabled(this.mWifiP2pEnabled);
        menu.add(0, 2, 0, C1715R.string.wifi_p2p_menu_rename).setEnabled(this.mWifiP2pEnabled);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(1);
        MenuItem findItem2 = menu.findItem(2);
        if (this.mWifiP2pEnabled) {
            findItem.setEnabled(true);
            findItem2.setEnabled(true);
        } else {
            findItem.setEnabled(false);
            findItem2.setEnabled(false);
        }
        if (this.mWifiP2pSearching) {
            findItem.setTitle(C1715R.string.wifi_p2p_menu_searching);
        } else {
            findItem.setTitle(C1715R.string.wifi_p2p_menu_search);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startSearch();
            return true;
        } else if (itemId != 2) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            showDialog(3);
            return true;
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference instanceof WifiP2pPeer) {
            this.mSelectedWifiPeer = (WifiP2pPeer) preference;
            int i = this.mSelectedWifiPeer.device.status;
            if (i == 0) {
                showDialog(1);
            } else if (i == 1) {
                showDialog(2);
            } else {
                WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
                wifiP2pConfig.deviceAddress = this.mSelectedWifiPeer.device.deviceAddress;
                int i2 = SystemProperties.getInt("wifidirect.wps", -1);
                if (i2 != -1) {
                    wifiP2pConfig.wps.setup = i2;
                } else if (this.mSelectedWifiPeer.device.wpsPbcSupported()) {
                    wifiP2pConfig.wps.setup = 0;
                } else if (this.mSelectedWifiPeer.device.wpsKeypadSupported()) {
                    wifiP2pConfig.wps.setup = 2;
                } else {
                    wifiP2pConfig.wps.setup = 1;
                }
                this.mWifiP2pManager.connect(this.mChannel, wifiP2pConfig, new WifiP2pManager.ActionListener() {
                    public void onSuccess() {
                    }

                    public void onFailure(int i) {
                        Log.e("WifiP2pSettings", " connect fail " + i);
                        Toast.makeText(WifiP2pSettings.this.getActivity(), C1715R.string.wifi_p2p_failed_connect_message, 0).show();
                    }
                });
            }
        } else if (preference instanceof WifiP2pPersistentGroup) {
            this.mSelectedGroup = (WifiP2pPersistentGroup) preference;
            showDialog(4);
        }
        return super.onPreferenceTreeClick(preference);
    }

    public Dialog onCreateDialog(int i) {
        String str;
        String str2;
        String str3;
        if (i == 1) {
            if (TextUtils.isEmpty(this.mSelectedWifiPeer.device.deviceName)) {
                str2 = this.mSelectedWifiPeer.device.deviceAddress;
            } else {
                str2 = this.mSelectedWifiPeer.device.deviceName;
            }
            if (this.mConnectedDevices > 1) {
                str3 = getActivity().getString(C1715R.string.wifi_p2p_disconnect_multiple_message, new Object[]{str2, Integer.valueOf(this.mConnectedDevices - 1)});
            } else {
                str3 = getActivity().getString(C1715R.string.wifi_p2p_disconnect_message, new Object[]{str2});
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.wifi_p2p_disconnect_title);
            builder.setMessage((CharSequence) str3);
            builder.setPositiveButton((CharSequence) getActivity().getString(C1715R.string.dlg_ok), this.mDisconnectListener);
            builder.setNegativeButton((CharSequence) getActivity().getString(C1715R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
            return builder.create();
        } else if (i == 2) {
            if (TextUtils.isEmpty(this.mSelectedWifiPeer.device.deviceName)) {
                str = this.mSelectedWifiPeer.device.deviceAddress;
            } else {
                str = this.mSelectedWifiPeer.device.deviceName;
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
            builder2.setTitle((int) C1715R.string.wifi_p2p_cancel_connect_title);
            builder2.setMessage((CharSequence) getActivity().getString(C1715R.string.wifi_p2p_cancel_connect_message, new Object[]{str}));
            builder2.setPositiveButton((CharSequence) getActivity().getString(C1715R.string.dlg_ok), this.mCancelConnectListener);
            builder2.setNegativeButton((CharSequence) getActivity().getString(C1715R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
            return builder2.create();
        } else if (i == 3) {
            this.mDeviceNameText = new EditText(getActivity());
            this.mDeviceNameText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
            String str4 = this.mSavedDeviceName;
            if (str4 != null) {
                this.mDeviceNameText.setText(str4);
                this.mDeviceNameText.setSelection(this.mSavedDeviceName.length());
            } else {
                WifiP2pDevice wifiP2pDevice = this.mThisDevice;
                if (wifiP2pDevice != null && !TextUtils.isEmpty(wifiP2pDevice.deviceName)) {
                    this.mDeviceNameText.setText(this.mThisDevice.deviceName);
                    this.mDeviceNameText.setSelection(0, this.mThisDevice.deviceName.length());
                }
            }
            this.mSavedDeviceName = null;
            AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
            builder3.setTitle((int) C1715R.string.wifi_p2p_menu_rename);
            builder3.setView((View) this.mDeviceNameText);
            builder3.setPositiveButton((CharSequence) getActivity().getString(C1715R.string.dlg_ok), this.mRenameListener);
            builder3.setNegativeButton((CharSequence) getActivity().getString(C1715R.string.dlg_cancel), (DialogInterface.OnClickListener) null);
            return builder3.create();
        } else if (i != 4) {
            return null;
        } else {
            AlertDialog.Builder builder4 = new AlertDialog.Builder(getActivity());
            builder4.setMessage((CharSequence) getActivity().getString(C1715R.string.wifi_p2p_delete_group_message));
            builder4.setPositiveButton((CharSequence) getActivity().getString(C1715R.string.dlg_ok), this.mDeleteGroupListener);
            builder4.setNegativeButton((CharSequence) getActivity().getString(C1715R.string.dlg_cancel), this.mDeleteGroupListener);
            return builder4.create();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        WifiP2pPeer wifiP2pPeer = this.mSelectedWifiPeer;
        if (wifiP2pPeer != null) {
            bundle.putParcelable("PEER_STATE", wifiP2pPeer.device);
        }
        EditText editText = this.mDeviceNameText;
        if (editText != null) {
            bundle.putString("DEV_NAME", editText.getText().toString());
        }
        WifiP2pPersistentGroup wifiP2pPersistentGroup = this.mSelectedGroup;
        if (wifiP2pPersistentGroup != null) {
            bundle.putString("GROUP_NAME", wifiP2pPersistentGroup.getGroupName());
        }
    }

    /* access modifiers changed from: private */
    public void handlePeersChanged() {
        this.mPeerCategoryController.removeAllChildren();
        this.mConnectedDevices = 0;
        for (WifiP2pDevice next : this.mPeers.getDeviceList()) {
            this.mPeerCategoryController.addChild(new WifiP2pPeer(getPrefContext(), next));
            if (next.status == 0) {
                this.mConnectedDevices++;
            }
        }
    }

    public void onPersistentGroupInfoAvailable(WifiP2pGroupList wifiP2pGroupList) {
        this.mPersistentCategoryController.removeAllChildren();
        for (WifiP2pGroup wifiP2pPersistentGroup : wifiP2pGroupList.getGroupList()) {
            WifiP2pPersistentGroup wifiP2pPersistentGroup2 = new WifiP2pPersistentGroup(getPrefContext(), wifiP2pPersistentGroup);
            this.mPersistentCategoryController.addChild(wifiP2pPersistentGroup2);
            if (wifiP2pPersistentGroup2.getGroupName().equals(this.mSelectedGroupName)) {
                this.mSelectedGroup = wifiP2pPersistentGroup2;
                this.mSelectedGroupName = null;
            }
        }
        if (this.mSelectedGroupName != null) {
            Log.w("WifiP2pSettings", " Selected group " + this.mSelectedGroupName + " disappered on next query ");
        }
    }

    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        this.mPeers = wifiP2pDeviceList;
        handlePeersChanged();
    }

    public void onDeviceInfoAvailable(WifiP2pDevice wifiP2pDevice) {
        this.mThisDevice = wifiP2pDevice;
        this.mThisDevicePreferenceController.updateDeviceName(this.mThisDevice);
    }

    /* access modifiers changed from: private */
    public void handleP2pStateChanged() {
        updateSearchMenu(false);
        this.mThisDevicePreferenceController.setEnabled(this.mWifiP2pEnabled);
        this.mPersistentCategoryController.setEnabled(this.mWifiP2pEnabled);
        this.mPeerCategoryController.setEnabled(this.mWifiP2pEnabled);
    }

    /* access modifiers changed from: private */
    public void updateSearchMenu(boolean z) {
        this.mWifiP2pSearching = z;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: private */
    public void startSearch() {
        WifiP2pManager wifiP2pManager = this.mWifiP2pManager;
        if (wifiP2pManager != null && !this.mWifiP2pSearching) {
            wifiP2pManager.discoverPeers(this.mChannel, new WifiP2pManager.ActionListener() {
                public void onFailure(int i) {
                }

                public void onSuccess() {
                }
            });
        }
    }
}
