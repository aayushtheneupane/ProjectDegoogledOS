package com.android.messaging.p041ui.appsettings;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.UserManager;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.sms.C1006b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.appsettings.j */
public class C1094j extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    /* access modifiers changed from: private */
    public static final String[] APN_PROJECTION = {"_id", "name", "apn", "type"};
    /* access modifiers changed from: private */

    /* renamed from: pa */
    public static boolean f1736pa;
    /* access modifiers changed from: private */

    /* renamed from: qa */
    public static final ContentValues f1737qa = new ContentValues(1);
    /* access modifiers changed from: private */

    /* renamed from: ra */
    public static final ContentValues f1738ra = new ContentValues(1);
    /* access modifiers changed from: private */

    /* renamed from: ta */
    public static final String[] f1739ta = {"2"};
    /* access modifiers changed from: private */

    /* renamed from: ha */
    public SQLiteDatabase f1740ha;

    /* renamed from: ka */
    private C1093i f1741ka;

    /* renamed from: la */
    private C1092h f1742la;
    private int mSubId;
    private UserManager mUm;

    /* renamed from: ma */
    private HandlerThread f1743ma;
    /* access modifiers changed from: private */

    /* renamed from: na */
    public String f1744na;

    /* renamed from: oa */
    private boolean f1745oa;

    static {
        f1737qa.putNull("current");
        f1738ra.put("current", "2");
    }

    /* access modifiers changed from: private */
    /* renamed from: wm */
    public void m2717wm() {
        new C1090f(this, C1474sa.m3795b(C1474sa.get(this.mSubId).mo8207fk())).execute(new Void[]{null});
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ListView listView = (ListView) getView().findViewById(16908298);
        TextView textView = (TextView) getView().findViewById(16908292);
        if (textView != null) {
            textView.setText(R.string.apn_settings_not_available);
            listView.setEmptyView(textView);
        }
        if (!C1464na.m3758Yj() || !this.mUm.hasUserRestriction("no_config_mobile_networks")) {
            addPreferencesFromResource(R.xml.apn_settings);
            listView.setItemsCanFocus(true);
            return;
        }
        this.f1745oa = true;
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1740ha = C1006b.m2350pb().getWritableDatabase();
        if (C1464na.m3758Yj()) {
            this.mUm = (UserManager) getActivity().getSystemService("user");
            if (!this.mUm.hasUserRestriction("no_config_mobile_networks")) {
                setHasOptionsMenu(true);
                return;
            }
            return;
        }
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!this.f1745oa) {
            menu.add(0, 1, 0, getResources().getString(R.string.menu_new_apn)).setIcon(R.drawable.ic_add_white).setShowAsAction(1);
            menu.add(0, 2, 0, getResources().getString(R.string.menu_restore_default_apn)).setIcon(17301589);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.f1743ma;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startActivity(C1040Ea.get().mo6964b((Context) getActivity(), (String) null, this.mSubId));
            return true;
        } else if (itemId != 2) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            getActivity().showDialog(PointerIconCompat.TYPE_CONTEXT_MENU);
            f1736pa = true;
            if (this.f1741ka == null) {
                this.f1741ka = new C1093i(this, (C1089e) null);
            }
            if (this.f1742la == null || this.f1743ma == null) {
                this.f1743ma = new HandlerThread("Restore default APN Handler: Process Thread");
                this.f1743ma.start();
                this.f1742la = new C1092h(this, this.f1743ma.getLooper(), this.f1741ka);
            }
            this.f1742la.sendEmptyMessage(1);
            return true;
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f1745oa) {
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            this.f1744na = str;
            new C1091g(this, str).execute(new Void[]{null});
        }
        return true;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        startActivity(C1040Ea.get().mo6964b((Context) getActivity(), preference.getKey(), this.mSubId));
        return true;
    }

    public void onResume() {
        super.onResume();
        if (!this.f1745oa && !f1736pa) {
            m2717wm();
        }
    }

    public void setSubId(int i) {
        this.mSubId = i;
    }
}
