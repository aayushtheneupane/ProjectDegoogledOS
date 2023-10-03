package com.android.settings.security;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.security.KeyStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settings.vpn2.VpnUtils;
import com.havoc.config.center.C1715R;

public final class CredentialStorage extends FragmentActivity {
    private Bundle mInstallBundle;
    private final KeyStore mKeyStore = KeyStore.getInstance();
    /* access modifiers changed from: private */
    public LockPatternUtils mUtils;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUtils = new LockPatternUtils(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if (((UserManager) getSystemService("user")).hasUserRestriction("no_config_credentials")) {
            finish();
        } else if ("com.android.credentials.RESET".equals(action)) {
            new ResetDialog();
        } else {
            if ("com.android.credentials.INSTALL".equals(action) && checkCallerIsCertInstallerOrSelfInProfile()) {
                this.mInstallBundle = intent.getExtras();
            }
            handleInstall();
        }
    }

    private void handleInstall() {
        if (!isFinishing() && installIfAvailable()) {
            finish();
        }
    }

    private boolean installIfAvailable() {
        Bundle bundle = this.mInstallBundle;
        boolean z = true;
        if (bundle != null && !bundle.isEmpty()) {
            Bundle bundle2 = this.mInstallBundle;
            this.mInstallBundle = null;
            int i = bundle2.getInt("install_as_uid", -1);
            if (i == -1 || UserHandle.isSameUser(i, Process.myUid())) {
                if (bundle2.containsKey("user_private_key_name")) {
                    String string = bundle2.getString("user_private_key_name");
                    if (!this.mKeyStore.importKey(string, bundle2.getByteArray("user_private_key_data"), i, 0)) {
                        Log.e("CredentialStorage", "Failed to install " + string + " as uid " + i);
                        return true;
                    } else if (i == 1000 || i == -1) {
                        new MarkKeyAsUserSelectable(string.replaceFirst("^USRPKEY_", "")).execute(new Void[0]);
                        z = false;
                    }
                }
                if (bundle2.containsKey("user_certificate_name")) {
                    String string2 = bundle2.getString("user_certificate_name");
                    if (!this.mKeyStore.put(string2, bundle2.getByteArray("user_certificate_data"), i, 0)) {
                        Log.e("CredentialStorage", "Failed to install " + string2 + " as uid " + i);
                        return z;
                    }
                }
                if (bundle2.containsKey("ca_certificates_name")) {
                    String string3 = bundle2.getString("ca_certificates_name");
                    if (!this.mKeyStore.put(string3, bundle2.getByteArray("ca_certificates_data"), i, 0)) {
                        Log.e("CredentialStorage", "Failed to install " + string3 + " as uid " + i);
                        return z;
                    }
                }
                sendBroadcast(new Intent("android.security.action.KEYCHAIN_CHANGED"));
                setResult(-1);
            } else {
                int userId = UserHandle.getUserId(i);
                if (i != 1010) {
                    Log.e("CredentialStorage", "Failed to install credentials as uid " + i + ": cross-user installs may only target wifi uids");
                    return true;
                }
                startActivityAsUser(new Intent("com.android.credentials.INSTALL").setFlags(33554432).putExtras(bundle2), new UserHandle(userId));
                return true;
            }
        }
        return z;
    }

    private class ResetDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private boolean mResetConfirmed;

        private ResetDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(CredentialStorage.this);
            builder.setTitle(17039380);
            builder.setMessage((int) C1715R.string.credentials_reset_hint);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) this);
            AlertDialog create = builder.create();
            create.setOnDismissListener(this);
            create.show();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.mResetConfirmed = i == -1;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.mResetConfirmed) {
                CredentialStorage.this.finish();
                return;
            }
            this.mResetConfirmed = false;
            if (!CredentialStorage.this.mUtils.isSecure(UserHandle.myUserId())) {
                new ResetKeyStoreAndKeyChain().execute(new Void[0]);
            } else if (!CredentialStorage.this.confirmKeyGuard(1)) {
                Log.w("CredentialStorage", "Failed to launch credential confirmation for a secure user.");
                CredentialStorage.this.finish();
            }
        }
    }

    private class ResetKeyStoreAndKeyChain extends AsyncTask<Void, Void, Boolean> {
        private ResetKeyStoreAndKeyChain() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:9|10|11|12|13) */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
            return false;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0026 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(java.lang.Void... r2) {
            /*
                r1 = this;
                com.android.settings.security.CredentialStorage r2 = com.android.settings.security.CredentialStorage.this
                com.android.internal.widget.LockPatternUtils r2 = r2.mUtils
                int r0 = android.os.UserHandle.myUserId()
                r2.resetKeyStore(r0)
                r2 = 0
                com.android.settings.security.CredentialStorage r1 = com.android.settings.security.CredentialStorage.this     // Catch:{ InterruptedException -> 0x0032 }
                android.security.KeyChain$KeyChainConnection r1 = android.security.KeyChain.bind(r1)     // Catch:{ InterruptedException -> 0x0032 }
                android.security.IKeyChainService r0 = r1.getService()     // Catch:{ RemoteException -> 0x0026 }
                boolean r0 = r0.reset()     // Catch:{ RemoteException -> 0x0026 }
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ RemoteException -> 0x0026 }
                r1.close()     // Catch:{ InterruptedException -> 0x0032 }
                return r0
            L_0x0024:
                r0 = move-exception
                goto L_0x002e
            L_0x0026:
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0024 }
                r1.close()     // Catch:{ InterruptedException -> 0x0032 }
                return r0
            L_0x002e:
                r1.close()     // Catch:{ InterruptedException -> 0x0032 }
                throw r0     // Catch:{ InterruptedException -> 0x0032 }
            L_0x0032:
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.security.CredentialStorage.ResetKeyStoreAndKeyChain.doInBackground(java.lang.Void[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                Toast.makeText(CredentialStorage.this, C1715R.string.credentials_erased, 0).show();
                CredentialStorage.this.clearLegacyVpnIfEstablished();
            } else {
                Toast.makeText(CredentialStorage.this, C1715R.string.credentials_not_erased, 0).show();
            }
            CredentialStorage.this.finish();
        }
    }

    /* access modifiers changed from: private */
    public void clearLegacyVpnIfEstablished() {
        if (VpnUtils.disconnectLegacyVpn(getApplicationContext())) {
            Toast.makeText(this, C1715R.string.vpn_disconnected, 0).show();
        }
    }

    private class MarkKeyAsUserSelectable extends AsyncTask<Void, Void, Boolean> {
        final String mAlias;

        MarkKeyAsUserSelectable(String str) {
            this.mAlias = str;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            if (r3 != null) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
            throw r5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(java.lang.Void... r8) {
            /*
                r7 = this;
                java.lang.String r8 = " as user-selectable."
                java.lang.String r0 = "Failed to mark key "
                java.lang.String r1 = "CredentialStorage"
                r2 = 0
                com.android.settings.security.CredentialStorage r3 = com.android.settings.security.CredentialStorage.this     // Catch:{ RemoteException -> 0x0052, InterruptedException -> 0x002f }
                android.security.KeyChain$KeyChainConnection r3 = android.security.KeyChain.bind(r3)     // Catch:{ RemoteException -> 0x0052, InterruptedException -> 0x002f }
                android.security.IKeyChainService r4 = r3.getService()     // Catch:{ all -> 0x0021 }
                java.lang.String r5 = r7.mAlias     // Catch:{ all -> 0x0021 }
                r6 = 1
                r4.setUserSelectable(r5, r6)     // Catch:{ all -> 0x0021 }
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0021 }
                if (r3 == 0) goto L_0x0020
                r3.close()     // Catch:{ RemoteException -> 0x0052, InterruptedException -> 0x002f }
            L_0x0020:
                return r4
            L_0x0021:
                r4 = move-exception
                throw r4     // Catch:{ all -> 0x0023 }
            L_0x0023:
                r5 = move-exception
                if (r3 == 0) goto L_0x002e
                r3.close()     // Catch:{ all -> 0x002a }
                goto L_0x002e
            L_0x002a:
                r3 = move-exception
                r4.addSuppressed(r3)     // Catch:{ RemoteException -> 0x0052, InterruptedException -> 0x002f }
            L_0x002e:
                throw r5     // Catch:{ RemoteException -> 0x0052, InterruptedException -> 0x002f }
            L_0x002f:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r0)
                java.lang.String r7 = r7.mAlias
                r3.append(r7)
                r3.append(r8)
                java.lang.String r7 = r3.toString()
                android.util.Log.w(r1, r7)
                java.lang.Thread r7 = java.lang.Thread.currentThread()
                r7.interrupt()
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)
                return r7
            L_0x0052:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r0)
                java.lang.String r7 = r7.mAlias
                r3.append(r7)
                r3.append(r8)
                java.lang.String r7 = r3.toString()
                android.util.Log.w(r1, r7)
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.security.CredentialStorage.MarkKeyAsUserSelectable.doInBackground(java.lang.Void[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            Log.i("CredentialStorage", String.format("Marked alias %s as selectable, success? %s", new Object[]{this.mAlias, bool}));
            CredentialStorage.this.finish();
        }
    }

    private boolean checkCallerIsCertInstallerOrSelfInProfile() {
        if (!TextUtils.equals("com.android.certinstaller", getCallingPackage())) {
            try {
                int launchedFromUid = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
                if (launchedFromUid == -1) {
                    Log.e("CredentialStorage", "com.android.credentials.INSTALL must be started with startActivityForResult");
                    return false;
                } else if (!UserHandle.isSameApp(launchedFromUid, Process.myUid())) {
                    return false;
                } else {
                    UserInfo profileParent = ((UserManager) getSystemService("user")).getProfileParent(UserHandle.getUserId(launchedFromUid));
                    if (profileParent == null || profileParent.id != UserHandle.myUserId()) {
                        return false;
                    }
                    return true;
                }
            } catch (RemoteException unused) {
                return false;
            }
        } else if (getPackageManager().checkSignatures(getCallingPackage(), getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean confirmKeyGuard(int i) {
        return new ChooseLockSettingsHelper(this).launchConfirmationActivity(i, getResources().getText(C1715R.string.credentials_title), true);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            return;
        }
        if (i2 == -1) {
            new ResetKeyStoreAndKeyChain().execute(new Void[0]);
        } else {
            finish();
        }
    }
}
