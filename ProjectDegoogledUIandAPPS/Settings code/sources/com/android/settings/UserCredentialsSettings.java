package com.android.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.KeyChain;
import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterBlob;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class UserCredentialsSettings extends SettingsPreferenceFragment implements View.OnClickListener {
    private static final SparseArray<Credential.Type> credentialViewTypes = new SparseArray<>();

    public int getMetricsCategory() {
        return 285;
    }

    public void onResume() {
        super.onResume();
        refreshItems();
    }

    public void onClick(View view) {
        Credential credential = (Credential) view.getTag();
        if (credential != null) {
            CredentialDialogFragment.show(this, credential);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(C1715R.string.user_credentials);
    }

    /* access modifiers changed from: protected */
    public void announceRemoval(String str) {
        if (isAdded()) {
            getListView().announceForAccessibility(getString(C1715R.string.user_credential_removed, str));
        }
    }

    /* access modifiers changed from: protected */
    public void refreshItems() {
        if (isAdded()) {
            new AliasLoader().execute(new Void[0]);
        }
    }

    public static class CredentialDialogFragment extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 533;
        }

        public static void show(Fragment fragment, Credential credential) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("credential", credential);
            if (fragment.getFragmentManager().findFragmentByTag("CredentialDialogFragment") == null) {
                CredentialDialogFragment credentialDialogFragment = new CredentialDialogFragment();
                credentialDialogFragment.setTargetFragment(fragment, -1);
                credentialDialogFragment.setArguments(bundle);
                credentialDialogFragment.show(fragment.getFragmentManager(), "CredentialDialogFragment");
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            final Credential credential = (Credential) getArguments().getParcelable("credential");
            View inflate = getActivity().getLayoutInflater().inflate(C1715R.layout.user_credential_dialog, (ViewGroup) null);
            ViewGroup viewGroup = (ViewGroup) inflate.findViewById(C1715R.C1718id.credential_container);
            viewGroup.addView(UserCredentialsSettings.getCredentialView(credential, C1715R.layout.user_credential, (View) null, viewGroup, true));
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(inflate);
            builder.setTitle((int) C1715R.string.user_credential_title);
            builder.setPositiveButton((int) C1715R.string.done, (DialogInterface.OnClickListener) null);
            final int myUserId = UserHandle.myUserId();
            if (!RestrictedLockUtilsInternal.hasBaseUserRestriction(getContext(), "no_config_credentials", myUserId)) {
                builder.setNegativeButton((int) C1715R.string.trusted_credentials_remove_label, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(CredentialDialogFragment.this.getContext(), "no_config_credentials", myUserId);
                        if (checkIfRestrictionEnforced != null) {
                            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(CredentialDialogFragment.this.getContext(), checkIfRestrictionEnforced);
                        } else {
                            CredentialDialogFragment credentialDialogFragment = CredentialDialogFragment.this;
                            new RemoveCredentialsTask(credentialDialogFragment.getContext(), CredentialDialogFragment.this.getTargetFragment()).execute(new Credential[]{credential});
                        }
                        dialogInterface.dismiss();
                    }
                });
            }
            return builder.create();
        }

        private class RemoveCredentialsTask extends AsyncTask<Credential, Void, Credential[]> {
            private Context context;
            private Fragment targetFragment;

            /* access modifiers changed from: protected */
            public /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
                Credential[] credentialArr = (Credential[]) objArr;
                doInBackground(credentialArr);
                return credentialArr;
            }

            public RemoveCredentialsTask(Context context2, Fragment fragment) {
                this.context = context2;
                this.targetFragment = fragment;
            }

            /* access modifiers changed from: protected */
            public Credential[] doInBackground(Credential... credentialArr) {
                for (Credential credential : credentialArr) {
                    if (credential.isSystem()) {
                        removeGrantsAndDelete(credential);
                    } else {
                        deleteWifiCredential(credential);
                    }
                }
                return credentialArr;
            }

            private void deleteWifiCredential(Credential credential) {
                KeyStore instance = KeyStore.getInstance();
                EnumSet<Credential.Type> storedTypes = credential.getStoredTypes();
                if (storedTypes.contains(Credential.Type.USER_KEY)) {
                    instance.delete("USRPKEY_" + credential.getAlias(), 1010);
                }
                if (storedTypes.contains(Credential.Type.USER_CERTIFICATE)) {
                    instance.delete("USRCERT_" + credential.getAlias(), 1010);
                }
                if (storedTypes.contains(Credential.Type.CA_CERTIFICATE)) {
                    instance.delete("CACERT_" + credential.getAlias(), 1010);
                }
            }

            private void removeGrantsAndDelete(Credential credential) {
                try {
                    KeyChain.KeyChainConnection bind = KeyChain.bind(CredentialDialogFragment.this.getContext());
                    try {
                        bind.getService().removeKeyPair(credential.alias);
                    } catch (RemoteException e) {
                        Log.w("CredentialDialogFragment", "Removing credentials", e);
                    } catch (Throwable th) {
                        bind.close();
                        throw th;
                    }
                    bind.close();
                } catch (InterruptedException e2) {
                    Log.w("CredentialDialogFragment", "Connecting to KeyChain", e2);
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Credential... credentialArr) {
                Fragment fragment = this.targetFragment;
                if ((fragment instanceof UserCredentialsSettings) && fragment.isAdded()) {
                    UserCredentialsSettings userCredentialsSettings = (UserCredentialsSettings) this.targetFragment;
                    for (Credential credential : credentialArr) {
                        userCredentialsSettings.announceRemoval(credential.alias);
                    }
                    userCredentialsSettings.refreshItems();
                }
            }
        }
    }

    private class AliasLoader extends AsyncTask<Void, Void, List<Credential>> {
        private AliasLoader() {
        }

        /* access modifiers changed from: protected */
        public List<Credential> doInBackground(Void... voidArr) {
            KeyStore instance = KeyStore.getInstance();
            int myUserId = UserHandle.myUserId();
            int uid = UserHandle.getUid(myUserId, 1000);
            int uid2 = UserHandle.getUid(myUserId, 1010);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(getCredentialsForUid(instance, uid).values());
            arrayList.addAll(getCredentialsForUid(instance, uid2).values());
            return arrayList;
        }

        private boolean isAsymmetric(KeyStore keyStore, String str, int i) throws UnrecoverableKeyException {
            KeyCharacteristics keyCharacteristics = new KeyCharacteristics();
            int keyCharacteristics2 = keyStore.getKeyCharacteristics(str, (KeymasterBlob) null, (KeymasterBlob) null, i, keyCharacteristics);
            if (keyCharacteristics2 == 1) {
                Integer num = keyCharacteristics.getEnum(268435458);
                if (num == null) {
                    throw new UnrecoverableKeyException("Key algorithm unknown");
                } else if (num.intValue() == 1 || num.intValue() == 3) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw ((UnrecoverableKeyException) new UnrecoverableKeyException("Failed to obtain information about key").initCause(KeyStore.getKeyStoreException(keyCharacteristics2)));
            }
        }

        private SortedMap<String, Credential> getCredentialsForUid(KeyStore keyStore, int i) {
            KeyStore keyStore2 = keyStore;
            int i2 = i;
            TreeMap treeMap = new TreeMap();
            Credential.Type[] values = Credential.Type.values();
            int length = values.length;
            int i3 = 0;
            while (i3 < length) {
                Credential.Type type = values[i3];
                String[] strArr = type.prefix;
                int length2 = strArr.length;
                int i4 = 0;
                while (i4 < length2) {
                    String str = strArr[i4];
                    String[] list = keyStore2.list(str, i2);
                    int length3 = list.length;
                    int i5 = 0;
                    while (i5 < length3) {
                        String str2 = list[i5];
                        Credential.Type[] typeArr = values;
                        if (UserHandle.getAppId(i) != 1000 || (!str2.startsWith("profile_key_name_encrypt_") && !str2.startsWith("profile_key_name_decrypt_") && !str2.startsWith("synthetic_password_"))) {
                            try {
                                if (type == Credential.Type.USER_KEY) {
                                    try {
                                        if (!isAsymmetric(keyStore2, str + str2, i2)) {
                                        }
                                    } catch (UnrecoverableKeyException e) {
                                        e = e;
                                        Log.e("UserCredentialsSettings", "Unable to determine algorithm of key: " + str + str2, e);
                                        i5++;
                                        keyStore2 = keyStore;
                                        i2 = i;
                                        values = typeArr;
                                    }
                                }
                                Credential credential = (Credential) treeMap.get(str2);
                                if (credential == null) {
                                    credential = new Credential(str2, i2);
                                    treeMap.put(str2, credential);
                                }
                                credential.storedTypes.add(type);
                            } catch (UnrecoverableKeyException e2) {
                                e = e2;
                                Log.e("UserCredentialsSettings", "Unable to determine algorithm of key: " + str + str2, e);
                                i5++;
                                keyStore2 = keyStore;
                                i2 = i;
                                values = typeArr;
                            }
                        }
                        i5++;
                        keyStore2 = keyStore;
                        i2 = i;
                        values = typeArr;
                    }
                    Credential.Type[] typeArr2 = values;
                    i4++;
                    keyStore2 = keyStore;
                    i2 = i;
                    values = typeArr2;
                }
                Credential.Type[] typeArr3 = values;
                i3++;
                keyStore2 = keyStore;
                i2 = i;
                values = typeArr3;
            }
            return treeMap;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<Credential> list) {
            if (UserCredentialsSettings.this.isAdded()) {
                if (list == null || list.size() == 0) {
                    TextView textView = (TextView) UserCredentialsSettings.this.getActivity().findViewById(16908292);
                    textView.setText(C1715R.string.user_credential_none_installed);
                    UserCredentialsSettings.this.setEmptyView(textView);
                } else {
                    UserCredentialsSettings.this.setEmptyView((View) null);
                }
                UserCredentialsSettings.this.getListView().setAdapter(new CredentialAdapter(list, UserCredentialsSettings.this));
            }
        }
    }

    private static class CredentialAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final List<Credential> mItems;
        private final View.OnClickListener mListener;

        public CredentialAdapter(List<Credential> list, View.OnClickListener onClickListener) {
            this.mItems = list;
            this.mListener = onClickListener;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1715R.layout.user_credential_preference, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            UserCredentialsSettings.getCredentialView(this.mItems.get(i), C1715R.layout.user_credential_preference, viewHolder.itemView, (ViewGroup) null, false);
            viewHolder.itemView.setTag(this.mItems.get(i));
            viewHolder.itemView.setOnClickListener(this.mListener);
        }

        public int getItemCount() {
            return this.mItems.size();
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    static {
        credentialViewTypes.put(C1715R.C1718id.contents_userkey, Credential.Type.USER_KEY);
        credentialViewTypes.put(C1715R.C1718id.contents_usercrt, Credential.Type.USER_CERTIFICATE);
        credentialViewTypes.put(C1715R.C1718id.contents_cacrt, Credential.Type.CA_CERTIFICATE);
    }

    protected static View getCredentialView(Credential credential, int i, View view, ViewGroup viewGroup, boolean z) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        }
        ((TextView) view.findViewById(C1715R.C1718id.alias)).setText(credential.alias);
        ((TextView) view.findViewById(C1715R.C1718id.purpose)).setText(credential.isSystem() ? C1715R.string.credential_for_vpn_and_apps : C1715R.string.credential_for_wifi);
        view.findViewById(C1715R.C1718id.contents).setVisibility(z ? 0 : 8);
        if (z) {
            for (int i2 = 0; i2 < credentialViewTypes.size(); i2++) {
                view.findViewById(credentialViewTypes.keyAt(i2)).setVisibility(credential.storedTypes.contains(credentialViewTypes.valueAt(i2)) ? 0 : 8);
            }
        }
        return view;
    }

    static class Credential implements Parcelable {
        public static final Parcelable.Creator<Credential> CREATOR = new Parcelable.Creator<Credential>() {
            public Credential createFromParcel(Parcel parcel) {
                return new Credential(parcel);
            }

            public Credential[] newArray(int i) {
                return new Credential[i];
            }
        };
        final String alias;
        final EnumSet<Type> storedTypes;
        final int uid;

        public int describeContents() {
            return 0;
        }

        enum Type {
            CA_CERTIFICATE("CACERT_"),
            USER_CERTIFICATE("USRCERT_"),
            USER_KEY("USRPKEY_", "USRSKEY_");
            
            final String[] prefix;

            private Type(String... strArr) {
                this.prefix = strArr;
            }
        }

        Credential(String str, int i) {
            this.storedTypes = EnumSet.noneOf(Type.class);
            this.alias = str;
            this.uid = i;
        }

        Credential(Parcel parcel) {
            this(parcel.readString(), parcel.readInt());
            long readLong = parcel.readLong();
            for (Type type : Type.values()) {
                if (((1 << type.ordinal()) & readLong) != 0) {
                    this.storedTypes.add(type);
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.alias);
            parcel.writeInt(this.uid);
            Iterator it = this.storedTypes.iterator();
            long j = 0;
            while (it.hasNext()) {
                j |= 1 << ((Type) it.next()).ordinal();
            }
            parcel.writeLong(j);
        }

        public boolean isSystem() {
            return UserHandle.getAppId(this.uid) == 1000;
        }

        public String getAlias() {
            return this.alias;
        }

        public EnumSet<Type> getStoredTypes() {
            return this.storedTypes;
        }
    }
}
