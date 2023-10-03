package com.android.settings.applications;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.util.MemInfoReader;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.RunningState;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class RunningProcessesView extends FrameLayout implements AdapterView.OnItemClickListener, AbsListView.RecyclerListener, RunningState.OnRefreshUiListener {
    long SECONDARY_SERVER_MEM;
    final HashMap<View, ActiveItem> mActiveItems = new HashMap<>();
    ServiceListAdapter mAdapter;
    ActivityManager mAm;
    TextView mAppsProcess;
    CharSequence mAppsProcessText;
    TextView mBackgroundProcess;
    CharSequence mBackgroundProcessText;
    StringBuilder mBuilder = new StringBuilder(128);
    ProgressBar mColorBar;
    long mCurHighRam = -1;
    long mCurLowRam = -1;
    long mCurMedRam = -1;
    RunningState.BaseItem mCurSelected;
    boolean mCurShowCached = false;
    long mCurTotalRam = -1;
    Runnable mDataAvail;
    TextView mForegroundProcess;
    CharSequence mForegroundProcessText;
    View mHeader;
    ListView mListView;
    MemInfoReader mMemInfoReader = new MemInfoReader();
    final int mMyUserId = UserHandle.myUserId();
    SettingsPreferenceFragment mOwner;
    RunningState mState;

    public static class ActiveItem {
        long mFirstRunTime;
        ViewHolder mHolder;
        RunningState.BaseItem mItem;
        View mRootView;
        boolean mSetBackground;

        /* access modifiers changed from: package-private */
        public void updateTime(Context context, StringBuilder sb) {
            TextView textView;
            RunningState.BaseItem baseItem = this.mItem;
            if (baseItem instanceof RunningState.ServiceItem) {
                textView = this.mHolder.size;
            } else {
                String str = baseItem.mSizeStr;
                if (str == null) {
                    str = "";
                }
                if (!str.equals(this.mItem.mCurSizeStr)) {
                    this.mItem.mCurSizeStr = str;
                    this.mHolder.size.setText(str);
                }
                RunningState.BaseItem baseItem2 = this.mItem;
                if (baseItem2.mBackground) {
                    if (!this.mSetBackground) {
                        this.mSetBackground = true;
                        this.mHolder.uptime.setText("");
                    }
                } else if (baseItem2 instanceof RunningState.MergedItem) {
                    textView = this.mHolder.uptime;
                }
                textView = null;
            }
            if (textView != null) {
                boolean z = false;
                this.mSetBackground = false;
                if (this.mFirstRunTime >= 0) {
                    textView.setText(DateUtils.formatElapsedTime(sb, (SystemClock.elapsedRealtime() - this.mFirstRunTime) / 1000));
                    return;
                }
                RunningState.BaseItem baseItem3 = this.mItem;
                if ((baseItem3 instanceof RunningState.MergedItem) && ((RunningState.MergedItem) baseItem3).mServices.size() > 0) {
                    z = true;
                }
                if (z) {
                    textView.setText(context.getResources().getText(C1715R.string.service_restarting));
                } else {
                    textView.setText("");
                }
            }
        }
    }

    public static class ViewHolder {
        public TextView description;
        public ImageView icon;
        public TextView name;
        public View rootView;
        public TextView size;
        public TextView uptime;

        public ViewHolder(View view) {
            this.rootView = view;
            this.icon = (ImageView) view.findViewById(C1715R.C1718id.icon);
            this.name = (TextView) view.findViewById(C1715R.C1718id.name);
            this.description = (TextView) view.findViewById(C1715R.C1718id.description);
            this.size = (TextView) view.findViewById(C1715R.C1718id.size);
            this.uptime = (TextView) view.findViewById(C1715R.C1718id.uptime);
            view.setTag(this);
        }

        public ActiveItem bind(RunningState runningState, RunningState.BaseItem baseItem, StringBuilder sb) {
            ActiveItem activeItem;
            synchronized (runningState.mLock) {
                PackageManager packageManager = this.rootView.getContext().getPackageManager();
                if (baseItem.mPackageInfo == null && (baseItem instanceof RunningState.MergedItem) && ((RunningState.MergedItem) baseItem).mProcess != null) {
                    ((RunningState.MergedItem) baseItem).mProcess.ensureLabel(packageManager);
                    baseItem.mPackageInfo = ((RunningState.MergedItem) baseItem).mProcess.mPackageInfo;
                    baseItem.mDisplayLabel = ((RunningState.MergedItem) baseItem).mProcess.mDisplayLabel;
                }
                this.name.setText(baseItem.mDisplayLabel);
                activeItem = new ActiveItem();
                activeItem.mRootView = this.rootView;
                activeItem.mItem = baseItem;
                activeItem.mHolder = this;
                activeItem.mFirstRunTime = baseItem.mActiveSince;
                if (baseItem.mBackground) {
                    this.description.setText(this.rootView.getContext().getText(C1715R.string.cached));
                } else {
                    this.description.setText(baseItem.mDescription);
                }
                baseItem.mCurSizeStr = null;
                this.icon.setImageDrawable(baseItem.loadIcon(this.rootView.getContext(), runningState));
                this.icon.setVisibility(0);
                activeItem.updateTime(this.rootView.getContext(), sb);
            }
            return activeItem;
        }
    }

    class ServiceListAdapter extends BaseAdapter {
        final LayoutInflater mInflater;
        final ArrayList<RunningState.MergedItem> mItems = new ArrayList<>();
        ArrayList<RunningState.MergedItem> mOrigItems;
        boolean mShowBackground;
        final RunningState mState;

        public boolean areAllItemsEnabled() {
            return false;
        }

        public boolean hasStableIds() {
            return true;
        }

        ServiceListAdapter(RunningState runningState) {
            this.mState = runningState;
            this.mInflater = (LayoutInflater) RunningProcessesView.this.getContext().getSystemService("layout_inflater");
            refreshItems();
        }

        /* access modifiers changed from: package-private */
        public void setShowBackground(boolean z) {
            if (this.mShowBackground != z) {
                this.mShowBackground = z;
                this.mState.setWatchingBackgroundItems(z);
                refreshItems();
                RunningProcessesView.this.refreshUi(true);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean getShowBackground() {
            return this.mShowBackground;
        }

        /* access modifiers changed from: package-private */
        public void refreshItems() {
            ArrayList<RunningState.MergedItem> arrayList;
            if (this.mShowBackground) {
                arrayList = this.mState.getCurrentBackgroundItems();
            } else {
                arrayList = this.mState.getCurrentMergedItems();
            }
            if (this.mOrigItems != arrayList) {
                this.mOrigItems = arrayList;
                if (arrayList == null) {
                    this.mItems.clear();
                    return;
                }
                this.mItems.clear();
                this.mItems.addAll(arrayList);
                if (this.mShowBackground) {
                    Collections.sort(this.mItems, this.mState.mBackgroundComparator);
                }
            }
        }

        public int getCount() {
            return this.mItems.size();
        }

        public boolean isEmpty() {
            return this.mState.hasData() && this.mItems.size() == 0;
        }

        public Object getItem(int i) {
            return this.mItems.get(i);
        }

        public long getItemId(int i) {
            return (long) this.mItems.get(i).hashCode();
        }

        public boolean isEnabled(int i) {
            return !this.mItems.get(i).mIsProcess;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = newView(viewGroup);
            }
            bindView(view, i);
            return view;
        }

        public View newView(ViewGroup viewGroup) {
            View inflate = this.mInflater.inflate(C1715R.layout.running_processes_item, viewGroup, false);
            new ViewHolder(inflate);
            return inflate;
        }

        public void bindView(View view, int i) {
            synchronized (this.mState.mLock) {
                if (i < this.mItems.size()) {
                    RunningProcessesView.this.mActiveItems.put(view, ((ViewHolder) view.getTag()).bind(this.mState, this.mItems.get(i), RunningProcessesView.this.mBuilder));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshUi(boolean z) {
        long j;
        long j2;
        if (z) {
            ServiceListAdapter serviceListAdapter = this.mAdapter;
            serviceListAdapter.refreshItems();
            serviceListAdapter.notifyDataSetChanged();
        }
        Runnable runnable = this.mDataAvail;
        if (runnable != null) {
            runnable.run();
            this.mDataAvail = null;
        }
        this.mMemInfoReader.readMemInfo();
        synchronized (this.mState.mLock) {
            if (this.mCurShowCached != this.mAdapter.mShowBackground) {
                this.mCurShowCached = this.mAdapter.mShowBackground;
                if (this.mCurShowCached) {
                    this.mForegroundProcessText = getResources().getText(C1715R.string.running_processes_header_used_prefix);
                    this.mAppsProcessText = getResources().getText(C1715R.string.running_processes_header_cached_prefix);
                } else {
                    this.mForegroundProcessText = getResources().getText(C1715R.string.running_processes_header_system_prefix);
                    this.mAppsProcessText = getResources().getText(C1715R.string.running_processes_header_apps_prefix);
                }
            }
            long totalSize = this.mMemInfoReader.getTotalSize();
            if (this.mCurShowCached) {
                j2 = this.mMemInfoReader.getFreeSize() + this.mMemInfoReader.getCachedSize();
                j = this.mState.mBackgroundProcessMemory;
            } else {
                j2 = this.mMemInfoReader.getFreeSize() + this.mMemInfoReader.getCachedSize() + this.mState.mBackgroundProcessMemory;
                j = this.mState.mServiceProcessMemory;
            }
            long j3 = (totalSize - j) - j2;
            if (!(this.mCurTotalRam == totalSize && this.mCurHighRam == j3 && this.mCurMedRam == j && this.mCurLowRam == j2)) {
                this.mCurTotalRam = totalSize;
                this.mCurHighRam = j3;
                this.mCurMedRam = j;
                this.mCurLowRam = j2;
                BidiFormatter instance = BidiFormatter.getInstance();
                String unicodeWrap = instance.unicodeWrap(Formatter.formatShortFileSize(getContext(), j2));
                TextView textView = this.mBackgroundProcess;
                textView.setText(this.mBackgroundProcessText + ": " + getResources().getString(C1715R.string.running_processes_header_ram, new Object[]{unicodeWrap}));
                String unicodeWrap2 = instance.unicodeWrap(Formatter.formatShortFileSize(getContext(), j));
                TextView textView2 = this.mAppsProcess;
                textView2.setText(this.mAppsProcessText + ": " + getResources().getString(C1715R.string.running_processes_header_ram, new Object[]{unicodeWrap2}));
                String unicodeWrap3 = instance.unicodeWrap(Formatter.formatShortFileSize(getContext(), j3));
                TextView textView3 = this.mForegroundProcess;
                textView3.setText(this.mForegroundProcessText + ": " + getResources().getString(C1715R.string.running_processes_header_ram, new Object[]{unicodeWrap3}));
                float f = (float) totalSize;
                int i = (int) ((((float) j3) / f) * 100.0f);
                this.mColorBar.setProgress(i);
                this.mColorBar.setSecondaryProgress(i + ((int) ((((float) j) / f) * 100.0f)));
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RunningState.MergedItem mergedItem = (RunningState.MergedItem) ((ListView) adapterView).getAdapter().getItem(i);
        this.mCurSelected = mergedItem;
        startServiceDetailsActivity(mergedItem);
    }

    private void startServiceDetailsActivity(RunningState.MergedItem mergedItem) {
        if (this.mOwner != null && mergedItem != null) {
            Bundle bundle = new Bundle();
            RunningState.ProcessItem processItem = mergedItem.mProcess;
            if (processItem != null) {
                bundle.putInt("uid", processItem.mUid);
                bundle.putString("process", mergedItem.mProcess.mProcessName);
            }
            bundle.putInt("user_id", mergedItem.mUserId);
            bundle.putBoolean("background", this.mAdapter.mShowBackground);
            new SubSettingLauncher(getContext()).setDestination(RunningServiceDetails.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.runningservicedetails_settings_title).setSourceMetricsCategory(this.mOwner.getMetricsCategory()).launch();
        }
    }

    public void onMovedToScrapHeap(View view) {
        this.mActiveItems.remove(view);
    }

    public RunningProcessesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void doCreate() {
        this.mAm = (ActivityManager) getContext().getSystemService("activity");
        this.mState = RunningState.getInstance(getContext());
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        layoutInflater.inflate(C1715R.layout.running_processes_view, this);
        this.mListView = (ListView) findViewById(16908298);
        View findViewById = findViewById(16908292);
        if (findViewById != null) {
            this.mListView.setEmptyView(findViewById);
        }
        this.mListView.setOnItemClickListener(this);
        this.mListView.setRecyclerListener(this);
        this.mAdapter = new ServiceListAdapter(this.mState);
        this.mListView.setAdapter(this.mAdapter);
        this.mHeader = layoutInflater.inflate(C1715R.layout.running_processes_header, (ViewGroup) null);
        this.mListView.addHeaderView(this.mHeader, (Object) null, false);
        this.mColorBar = (ProgressBar) this.mHeader.findViewById(C1715R.C1718id.color_bar);
        Context context = getContext();
        this.mColorBar.setProgressTintList(ColorStateList.valueOf(context.getColor(C1715R.C1716color.running_processes_system_ram)));
        this.mColorBar.setSecondaryProgressTintList(Utils.getColorAccent(context));
        this.mColorBar.setSecondaryProgressTintMode(PorterDuff.Mode.SRC);
        this.mColorBar.setProgressBackgroundTintList(ColorStateList.valueOf(context.getColor(C1715R.C1716color.running_processes_free_ram)));
        this.mColorBar.setProgressBackgroundTintMode(PorterDuff.Mode.SRC);
        this.mBackgroundProcess = (TextView) this.mHeader.findViewById(C1715R.C1718id.freeSize);
        this.mAppsProcess = (TextView) this.mHeader.findViewById(C1715R.C1718id.appsSize);
        this.mForegroundProcess = (TextView) this.mHeader.findViewById(C1715R.C1718id.systemSize);
        this.mForegroundProcessText = getResources().getText(C1715R.string.running_processes_header_system_prefix);
        this.mAppsProcessText = getResources().getText(C1715R.string.running_processes_header_apps_prefix);
        this.mBackgroundProcessText = getResources().getText(C1715R.string.running_processes_header_free_prefix);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.mAm.getMemoryInfo(memoryInfo);
        this.SECONDARY_SERVER_MEM = memoryInfo.secondaryServerThreshold;
    }

    public void doPause() {
        this.mState.pause();
        this.mDataAvail = null;
        this.mOwner = null;
    }

    public boolean doResume(SettingsPreferenceFragment settingsPreferenceFragment, Runnable runnable) {
        this.mOwner = settingsPreferenceFragment;
        this.mState.resume(this);
        if (this.mState.hasData()) {
            refreshUi(true);
            return true;
        }
        this.mDataAvail = runnable;
        return false;
    }

    /* access modifiers changed from: package-private */
    public void updateTimes() {
        Iterator<ActiveItem> it = this.mActiveItems.values().iterator();
        while (it.hasNext()) {
            ActiveItem next = it.next();
            if (next.mRootView.getWindowToken() == null) {
                it.remove();
            } else {
                next.updateTime(getContext(), this.mBuilder);
            }
        }
    }

    public void onRefreshUi(int i) {
        if (i == 0) {
            updateTimes();
        } else if (i == 1) {
            refreshUi(false);
            updateTimes();
        } else if (i == 2) {
            refreshUi(true);
            updateTimes();
        }
    }
}
