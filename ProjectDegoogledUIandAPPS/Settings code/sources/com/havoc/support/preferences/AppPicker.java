package com.havoc.support.preferences;

import android.app.ListActivity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.R$string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppPicker extends ListActivity {
    protected List<ApplicationInfo> applist = null;
    protected Adapter listadapter = null;
    protected List<ActivityInfo> mActivitiesList = null;
    protected boolean mIsActivitiesList = false;
    protected PackageManager packageManager = null;

    /* access modifiers changed from: protected */
    public void onLongClick(int i) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(17367060);
        setTitle(R$string.active_edge_app_select_title);
        this.packageManager = getPackageManager();
        new LoadApplications().execute(new Void[0]);
    }

    public void onBackPressed() {
        if (this.mIsActivitiesList) {
            setListAdapter(this.listadapter);
            setTitle(R$string.active_edge_app_select_title);
            this.mIsActivitiesList = false;
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"com.google.android.as", "com.google.android.GoogleCamera", "com.google.android.imaging.easel.service", "com.android.traceur"};
        for (ApplicationInfo next : list) {
            try {
                if (!Arrays.asList(strArr).contains(next.packageName) && this.packageManager.getLaunchIntentForPackage(next.packageName) != null) {
                    arrayList.add(next);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(arrayList, new ApplicationInfo.DisplayNameComparator(this.packageManager));
        return arrayList;
    }

    class LoadApplications extends AsyncTask<Void, Void, Void> {
        LoadApplications() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            AppPicker appPicker = AppPicker.this;
            appPicker.applist = appPicker.checkForLaunchIntent(appPicker.packageManager.getInstalledApplications(128));
            AppPicker appPicker2 = AppPicker.this;
            appPicker2.listadapter = new Adapter(appPicker2, R$layout.app_list_item, appPicker2.applist, appPicker2.packageManager);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            AppPicker appPicker = AppPicker.this;
            appPicker.setListAdapter(appPicker.listadapter);
            AppPicker.this.getListView().setLongClickable(true);
            AppPicker.this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                    AppPicker.this.onLongClick(i);
                    return true;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }
    }

    /* access modifiers changed from: protected */
    public void showActivitiesDialog(String str) {
        ArrayList arrayList;
        this.mIsActivitiesList = true;
        try {
            arrayList = new ArrayList(Arrays.asList(this.packageManager.getPackageInfo(str, 1).activities));
        } catch (PackageManager.NameNotFoundException unused) {
            arrayList = null;
        }
        ArrayList arrayList2 = arrayList;
        this.mActivitiesList = arrayList2;
        if (arrayList2 == null) {
            this.mIsActivitiesList = false;
            return;
        }
        setTitle(R$string.active_edge_activity_select_title);
        setListAdapter(new ActivitiesAdapter(this, R$layout.app_list_item, arrayList2, this.packageManager));
    }

    class Adapter extends ArrayAdapter<ApplicationInfo> {
        private List<ApplicationInfo> appList;
        private Context context;
        private PackageManager packageManager;

        public long getItemId(int i) {
            return (long) i;
        }

        private Adapter(Context context2, int i, List<ApplicationInfo> list, PackageManager packageManager2) {
            super(context2, i, list);
            this.context = context2;
            this.appList = list;
            this.packageManager = packageManager2;
        }

        public int getCount() {
            List<ApplicationInfo> list = this.appList;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public ApplicationInfo getItem(int i) {
            List<ApplicationInfo> list = this.appList;
            if (list != null) {
                return list.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ApplicationInfo applicationInfo = this.appList.get(i);
            if (view == null) {
                view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R$layout.app_list_item, (ViewGroup) null);
            }
            if (applicationInfo != null) {
                ((TextView) view.findViewById(R$id.app_name)).setText(applicationInfo.loadLabel(this.packageManager));
                ((ImageView) view.findViewById(R$id.app_icon)).setImageDrawable(applicationInfo.loadIcon(this.packageManager));
            }
            return view;
        }
    }

    class ActivitiesAdapter extends ArrayAdapter<ActivityInfo> {
        private List<ActivityInfo> appList;
        private Context context;
        private PackageManager packageManager;

        public long getItemId(int i) {
            return (long) i;
        }

        private ActivitiesAdapter(Context context2, int i, List<ActivityInfo> list, PackageManager packageManager2) {
            super(context2, i, list);
            this.context = context2;
            this.appList = list;
            this.packageManager = packageManager2;
        }

        public int getCount() {
            List<ActivityInfo> list = this.appList;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public ActivityInfo getItem(int i) {
            List<ActivityInfo> list = this.appList;
            if (list != null) {
                return list.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ActivityInfo activityInfo = this.appList.get(i);
            if (view == null) {
                view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(17367043, (ViewGroup) null);
            }
            if (activityInfo != null) {
                ((TextView) view.findViewById(16908308)).setText(activityInfo.name);
            }
            return view;
        }
    }
}
