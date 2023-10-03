package com.android.messaging.p041ui.appsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.messaging.R;
import com.android.messaging.sms.C1006b;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.appsettings.d */
public class C1088d extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: ia */
    private static String f1724ia;
    /* access modifiers changed from: private */

    /* renamed from: ja */
    public static final String[] f1725ja = {"_id", "name", "mmsc", "mcc", "mnc", "numeric", "mmsproxy", "mmsport", "type"};
    /* access modifiers changed from: private */

    /* renamed from: aa */
    public EditTextPreference f1726aa;

    /* renamed from: ba */
    private EditTextPreference f1727ba;

    /* renamed from: ca */
    private EditTextPreference f1728ca;
    /* access modifiers changed from: private */

    /* renamed from: da */
    public String f1729da;
    /* access modifiers changed from: private */

    /* renamed from: ea */
    public String f1730ea;
    /* access modifiers changed from: private */

    /* renamed from: fa */
    public boolean f1731fa;
    /* access modifiers changed from: private */

    /* renamed from: ga */
    public String f1732ga;
    /* access modifiers changed from: private */

    /* renamed from: ha */
    public SQLiteDatabase f1733ha;
    /* access modifiers changed from: private */
    public Cursor mCursor;
    private boolean mFirstTime;
    /* access modifiers changed from: private */
    public EditTextPreference mMmsProxy;
    /* access modifiers changed from: private */
    public EditTextPreference mMmsc;
    private EditTextPreference mName;
    private int mSubId;

    /* access modifiers changed from: private */
    /* renamed from: Ya */
    public String m2690Ya(String str) {
        return (str == null || str.equals(f1724ia)) ? "" : str;
    }

    /* renamed from: Za */
    private String m2691Za(String str) {
        if (str == null || str.length() == 0) {
            return f1724ia;
        }
        return str;
    }

    /* access modifiers changed from: private */
    /* renamed from: qa */
    public boolean m2706qa(boolean z) {
        String Ya = m2690Ya(this.mName.getText());
        String Ya2 = m2690Ya(this.f1727ba.getText());
        String Ya3 = m2690Ya(this.f1728ca.getText());
        if (m2708vm() == null || z) {
            new C1086b(this, Ya, Ya2, Ya3).execute(new Void[]{null});
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_msg", m2708vm());
        getActivity().showDialog(0, bundle);
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: um */
    public void m2707um() {
        if (this.f1731fa) {
            this.f1727ba.setText((String) null);
            this.f1728ca.setText((String) null);
            String simOperatorNumeric = C1474sa.get(this.mSubId).getSimOperatorNumeric();
            if (simOperatorNumeric != null && simOperatorNumeric.length() > 4) {
                String substring = simOperatorNumeric.substring(0, 3);
                String substring2 = simOperatorNumeric.substring(3);
                this.f1727ba.setText(substring);
                this.f1728ca.setText(substring2);
                this.f1729da = substring2;
                this.f1730ea = substring;
            }
            this.mName.setText((String) null);
            this.mMmsProxy.setText((String) null);
            this.f1726aa.setText((String) null);
            this.mMmsc.setText((String) null);
        } else if (this.mFirstTime) {
            this.mFirstTime = false;
            this.mName.setText(this.mCursor.getString(1));
            this.mMmsProxy.setText(this.mCursor.getString(6));
            this.f1726aa.setText(this.mCursor.getString(7));
            this.mMmsc.setText(this.mCursor.getString(2));
            this.f1727ba.setText(this.mCursor.getString(3));
            this.f1728ca.setText(this.mCursor.getString(4));
        }
        EditTextPreference editTextPreference = this.mName;
        editTextPreference.setSummary(m2691Za(editTextPreference.getText()));
        EditTextPreference editTextPreference2 = this.mMmsProxy;
        editTextPreference2.setSummary(m2691Za(editTextPreference2.getText()));
        EditTextPreference editTextPreference3 = this.f1726aa;
        editTextPreference3.setSummary(m2691Za(editTextPreference3.getText()));
        EditTextPreference editTextPreference4 = this.mMmsc;
        editTextPreference4.setSummary(m2691Za(editTextPreference4.getText()));
        EditTextPreference editTextPreference5 = this.f1727ba;
        editTextPreference5.setSummary(m2691Za(editTextPreference5.getText()));
        EditTextPreference editTextPreference6 = this.f1728ca;
        editTextPreference6.setSummary(m2691Za(editTextPreference6.getText()));
    }

    /* renamed from: vm */
    private String m2708vm() {
        String Ya = m2690Ya(this.mName.getText());
        String Ya2 = m2690Ya(this.f1727ba.getText());
        String Ya3 = m2690Ya(this.f1728ca.getText());
        if (Ya.length() < 1) {
            return getString(R.string.error_apn_name_empty);
        }
        if (Ya2.length() != 3) {
            return getString(R.string.error_mcc_not3);
        }
        if ((Ya3.length() & 65534) != 2) {
            return getString(R.string.error_mnc_not23);
        }
        return null;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        addPreferencesFromResource(R.xml.apn_editor);
        setHasOptionsMenu(true);
        f1724ia = getResources().getString(R.string.apn_not_set);
        this.mName = (EditTextPreference) findPreference("apn_name");
        this.mMmsProxy = (EditTextPreference) findPreference("apn_mms_proxy");
        this.f1726aa = (EditTextPreference) findPreference("apn_mms_port");
        this.mMmsc = (EditTextPreference) findPreference("apn_mmsc");
        this.f1727ba = (EditTextPreference) findPreference("apn_mcc");
        this.f1728ca = (EditTextPreference) findPreference("apn_mnc");
        Intent intent = getActivity().getIntent();
        this.mFirstTime = bundle == null;
        this.f1732ga = intent.getStringExtra("apn_row_id");
        this.f1731fa = this.f1732ga == null;
        this.f1733ha = C1006b.m2350pb().getWritableDatabase();
        if (this.f1731fa) {
            m2707um();
        } else {
            new C1085a(this).execute(new Void[]{null});
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (!this.f1731fa) {
            menu.add(0, 1, 0, R.string.menu_delete_apn).setIcon(R.drawable.ic_delete_small);
        }
        menu.add(0, 2, 0, R.string.menu_save_apn).setIcon(17301582);
        menu.add(0, 3, 0, R.string.menu_discard_apn_change).setIcon(17301560);
    }

    public void onDestroy() {
        super.onDestroy();
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            cursor.close();
            this.mCursor = null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            new C1087c(this).execute(new Void[]{null});
            getActivity().finish();
            return true;
        } else if (itemId == 2) {
            if (m2706qa(false)) {
                getActivity().finish();
            }
            return true;
        } else if (itemId == 3) {
            getActivity().finish();
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            getActivity().onBackPressed();
            return true;
        }
    }

    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Cursor cursor;
        super.onSaveInstanceState(bundle);
        if (m2706qa(true) && (cursor = this.mCursor) != null) {
            bundle.putInt("pos", cursor.getInt(0));
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Preference findPreference = findPreference(str);
        if (findPreference != null) {
            findPreference.setSummary(m2691Za(sharedPreferences.getString(str, "")));
        }
    }

    public void setSubId(int i) {
        this.mSubId = i;
    }
}
