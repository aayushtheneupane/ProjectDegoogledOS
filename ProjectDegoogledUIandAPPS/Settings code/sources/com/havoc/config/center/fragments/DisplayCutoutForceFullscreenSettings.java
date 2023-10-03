package com.havoc.config.center.fragments;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.Switch;
import android.widget.TextView;
import com.android.internal.util.custom.cutout.CutoutFullscreenController;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;
import com.havoc.config.center.fragments.DisplayCutoutForceFullscreenSettings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayCutoutForceFullscreenSettings extends SettingsPreferenceFragment implements ApplicationsState.Callbacks {
    /* access modifiers changed from: private */
    public ActivityFilter mActivityFilter;
    /* access modifiers changed from: private */
    public ActivityManager mActivityManager;
    /* access modifiers changed from: private */
    public AllPackagesAdapter mAllPackagesAdapter;
    /* access modifiers changed from: private */
    public ApplicationsState mApplicationsState;
    /* access modifiers changed from: private */
    public CutoutFullscreenController mCutoutForceFullscreenSettings;
    private Map<String, ApplicationsState.AppEntry> mEntryMap = new HashMap();
    private ApplicationsState.Session mSession;
    private ListView mUserListView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onAllSizesComputed() {
    }

    public void onLauncherInfoChanged() {
    }

    public void onPackageIconChanged() {
    }

    public void onPackageSizeChanged(String str) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApplicationsState = ApplicationsState.getInstance(getActivity().getApplication());
        this.mSession = this.mApplicationsState.newSession(this);
        this.mSession.onResume();
        this.mActivityManager = (ActivityManager) getActivity().getSystemService("activity");
        this.mActivityFilter = new ActivityFilter(getActivity().getPackageManager());
        this.mAllPackagesAdapter = new AllPackagesAdapter(getActivity());
        this.mCutoutForceFullscreenSettings = new CutoutFullscreenController(getContext());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().getActionBar().setTitle(C1715R.string.display_cutout_force_fullscreen_title);
        return layoutInflater.inflate(C1715R.layout.cutout_force_fullscreen_layout, viewGroup, false);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mUserListView = (ListView) view.findViewById(C1715R.C1718id.user_list_view);
        this.mUserListView.setAdapter(this.mAllPackagesAdapter);
    }

    public void onResume() {
        super.onResume();
        rebuild();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mSession.onPause();
        this.mSession.onDestroy();
    }

    public void onPackageListChanged() {
        this.mActivityFilter.updateLauncherInfoList();
        rebuild();
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
        if (arrayList != null) {
            handleAppEntries(arrayList);
            this.mAllPackagesAdapter.notifyDataSetChanged();
        }
    }

    public void onLoadEntriesCompleted() {
        rebuild();
    }

    private void handleAppEntries(List<ApplicationsState.AppEntry> list) {
        String str;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        PackageManager packageManager = getPackageManager();
        int i = 0;
        String str2 = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ApplicationInfo applicationInfo = list.get(i2).info;
            String str3 = (String) applicationInfo.loadLabel(packageManager);
            if (!applicationInfo.enabled) {
                str = "--";
            } else if (TextUtils.isEmpty(str3)) {
                str = "";
            } else {
                str = str3.substring(0, 1).toUpperCase();
            }
            if (str2 == null || !TextUtils.equals(str, str2)) {
                arrayList.add(str);
                arrayList2.add(Integer.valueOf(i));
                str2 = str;
            }
            i++;
        }
        this.mAllPackagesAdapter.setEntries(list, arrayList, arrayList2);
        this.mEntryMap.clear();
        for (ApplicationsState.AppEntry next : list) {
            this.mEntryMap.put(next.info.packageName, next);
        }
    }

    private void rebuild() {
        this.mSession.rebuild(this.mActivityFilter, ApplicationsState.ALPHA_COMPARATOR);
    }

    private class AllPackagesAdapter extends BaseAdapter implements SectionIndexer {
        /* access modifiers changed from: private */
        public List<ApplicationsState.AppEntry> mEntries = new ArrayList();
        private final LayoutInflater mInflater;
        private int[] mPositions;
        private String[] mSections;

        public boolean hasStableIds() {
            return true;
        }

        public AllPackagesAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
            ActivityFilter unused = DisplayCutoutForceFullscreenSettings.this.mActivityFilter = new ActivityFilter(context.getPackageManager());
        }

        public int getCount() {
            return this.mEntries.size();
        }

        public Object getItem(int i) {
            return this.mEntries.get(i);
        }

        public long getItemId(int i) {
            return this.mEntries.get(i).f61id;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
            if (view == null) {
                viewHolder = new ViewHolder(this.mInflater.inflate(C1715R.layout.cutout_force_fullscreen_list_item, viewGroup, false));
                viewHolder.state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        DisplayCutoutForceFullscreenSettings.AllPackagesAdapter.this.mo17608xd47a5470(compoundButton, z);
                    }
                });
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (appEntry == null) {
                return viewHolder.rootView;
            }
            viewHolder.title.setText(appEntry.label);
            DisplayCutoutForceFullscreenSettings.this.mApplicationsState.ensureIcon(appEntry);
            viewHolder.icon.setImageDrawable(appEntry.icon);
            viewHolder.state.setTag(appEntry);
            viewHolder.state.setChecked(DisplayCutoutForceFullscreenSettings.this.mCutoutForceFullscreenSettings.shouldForceCutoutFullscreen(appEntry.info.packageName));
            return viewHolder.rootView;
        }

        /* renamed from: lambda$getView$0$DisplayCutoutForceFullscreenSettings$AllPackagesAdapter */
        public /* synthetic */ void mo17608xd47a5470(CompoundButton compoundButton, boolean z) {
            ApplicationsState.AppEntry appEntry = (ApplicationsState.AppEntry) compoundButton.getTag();
            if (z) {
                DisplayCutoutForceFullscreenSettings.this.mCutoutForceFullscreenSettings.addApp(appEntry.info.packageName);
            } else {
                DisplayCutoutForceFullscreenSettings.this.mCutoutForceFullscreenSettings.removeApp(appEntry.info.packageName);
            }
            try {
                DisplayCutoutForceFullscreenSettings.this.mActivityManager.forceStopPackage(appEntry.info.packageName);
            } catch (Exception unused) {
            }
        }

        /* access modifiers changed from: private */
        public void setEntries(List<ApplicationsState.AppEntry> list, List<String> list2, List<Integer> list3) {
            this.mEntries = list;
            this.mSections = (String[]) list2.toArray(new String[list2.size()]);
            this.mPositions = new int[list3.size()];
            for (int i = 0; i < list3.size(); i++) {
                this.mPositions[i] = list3.get(i).intValue();
            }
            notifyDataSetChanged();
        }

        public int getPositionForSection(int i) {
            if (i < 0 || i >= this.mSections.length) {
                return -1;
            }
            return this.mPositions[i];
        }

        public int getSectionForPosition(int i) {
            if (i < 0 || i >= getCount()) {
                return -1;
            }
            int binarySearch = Arrays.binarySearch(this.mPositions, i);
            return binarySearch >= 0 ? binarySearch : (-binarySearch) - 2;
        }

        public Object[] getSections() {
            return this.mSections;
        }
    }

    private static class ViewHolder {
        /* access modifiers changed from: private */
        public ImageView icon;
        /* access modifiers changed from: private */
        public View rootView;
        /* access modifiers changed from: private */
        public Switch state;
        /* access modifiers changed from: private */
        public TextView title;

        private ViewHolder(View view) {
            this.title = (TextView) view.findViewById(C1715R.C1718id.app_name);
            this.icon = (ImageView) view.findViewById(C1715R.C1718id.app_icon);
            this.state = (Switch) view.findViewById(C1715R.C1718id.state);
            this.rootView = view;
            view.setTag(this);
        }
    }

    private class ActivityFilter implements ApplicationsState.AppFilter {
        private final String[] hideApps;
        private final List<String> mLauncherResolveInfoList;
        private final PackageManager mPackageManager;
        final /* synthetic */ DisplayCutoutForceFullscreenSettings this$0;

        public void init() {
        }

        private ActivityFilter(DisplayCutoutForceFullscreenSettings displayCutoutForceFullscreenSettings, PackageManager packageManager) {
            this.this$0 = displayCutoutForceFullscreenSettings;
            this.mLauncherResolveInfoList = new ArrayList();
            this.hideApps = new String[]{"com.android.settings", "com.android.documentsui", "com.android.fmradio", "com.caf.fmradio", "com.android.stk", "com.google.android.calculator", "com.google.android.calendar", "com.google.android.deskclock", "com.google.android.contacts", "com.google.android.apps.messaging", "com.google.android.googlequicksearchbox", "com.android.vending", "com.google.android.dialer", "com.google.android.apps.wallpaper", "com.google.android.as"};
            this.mPackageManager = packageManager;
            updateLauncherInfoList();
        }

        public void updateLauncherInfoList() {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> queryIntentActivities = this.mPackageManager.queryIntentActivities(intent, 0);
            synchronized (this.mLauncherResolveInfoList) {
                this.mLauncherResolveInfoList.clear();
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    this.mLauncherResolveInfoList.add(resolveInfo.activityInfo.packageName);
                }
            }
        }

        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            boolean z = true;
            boolean z2 = !this.this$0.mAllPackagesAdapter.mEntries.contains(appEntry.info.packageName);
            if (z2) {
                synchronized (this.mLauncherResolveInfoList) {
                    if (!this.mLauncherResolveInfoList.contains(appEntry.info.packageName) || Arrays.asList(this.hideApps).contains(appEntry.info.packageName)) {
                        z = false;
                    }
                    z2 = z;
                }
            }
            return z2;
        }
    }
}
