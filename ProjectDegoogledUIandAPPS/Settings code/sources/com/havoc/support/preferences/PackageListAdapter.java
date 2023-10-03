package com.havoc.support.preferences;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PackageListAdapter extends BaseAdapter implements Runnable {
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            PackageItem packageItem = (PackageItem) message.obj;
            int binarySearch = Collections.binarySearch(PackageListAdapter.this.mInstalledPackages, packageItem);
            if (binarySearch < 0) {
                PackageListAdapter.this.mInstalledPackages.add((-binarySearch) - 1, packageItem);
            }
            PackageListAdapter.this.notifyDataSetChanged();
        }
    };
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public final List<PackageItem> mInstalledPackages = new LinkedList();
    private PackageManager mPm;

    public static class PackageItem implements Comparable<PackageItem> {
        public final Drawable icon;
        public final String packageName;
        public final CharSequence title;

        PackageItem(String str, CharSequence charSequence, Drawable drawable) {
            this.packageName = str;
            this.title = charSequence;
            this.icon = drawable;
        }

        public int compareTo(PackageItem packageItem) {
            int compareToIgnoreCase = this.title.toString().compareToIgnoreCase(packageItem.title.toString());
            return compareToIgnoreCase != 0 ? compareToIgnoreCase : this.packageName.compareTo(packageItem.packageName);
        }
    }

    public PackageListAdapter(Context context) {
        this.mPm = context.getPackageManager();
        this.mInflater = LayoutInflater.from(context);
        reloadList();
    }

    public int getCount() {
        int size;
        synchronized (this.mInstalledPackages) {
            size = this.mInstalledPackages.size();
        }
        return size;
    }

    public PackageItem getItem(int i) {
        PackageItem packageItem;
        synchronized (this.mInstalledPackages) {
            packageItem = this.mInstalledPackages.get(i);
        }
        return packageItem;
    }

    public long getItemId(int i) {
        long hashCode;
        synchronized (this.mInstalledPackages) {
            hashCode = (long) this.mInstalledPackages.get(i).packageName.hashCode();
        }
        return hashCode;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = this.mInflater.inflate(R$layout.applist_preference_icon, (ViewGroup) null, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
            viewHolder.title = (TextView) view.findViewById(16908310);
            viewHolder.icon = (ImageView) view.findViewById(R$id.icon);
        }
        PackageItem item = getItem(i);
        viewHolder.title.setText(item.title);
        viewHolder.icon.setImageDrawable(item.icon);
        return view;
    }

    private void reloadList() {
        this.mInstalledPackages.clear();
        new Thread(this).start();
    }

    public void run() {
        for (ApplicationInfo next : this.mPm.getInstalledApplications(128)) {
            if (next.icon != 0) {
                this.mHandler.obtainMessage(0, new PackageItem(next.packageName, next.loadLabel(this.mPm), next.loadIcon(this.mPm))).sendToTarget();
            }
        }
    }

    private static class ViewHolder {
        ImageView icon;
        TextView title;

        private ViewHolder() {
        }
    }
}
