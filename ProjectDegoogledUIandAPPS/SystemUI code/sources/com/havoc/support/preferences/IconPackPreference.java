package com.havoc.support.preferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.preference.Preference;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.R$string;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IconPackPreference extends Preference {

    /* renamed from: pm */
    private final PackageManager f84pm;

    public IconPackPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconPackPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(R$layout.iconpack_preference);
        this.f84pm = context.getPackageManager();
        init();
    }

    public void init() {
        String persistedString = getPersistedString("");
        if (persistedString.isEmpty()) {
            setNone();
            return;
        }
        try {
            ApplicationInfo applicationInfo = this.f84pm.getApplicationInfo(persistedString, 0);
            setIcon(applicationInfo.loadIcon(this.f84pm));
            setSummary(applicationInfo.loadLabel(this.f84pm));
        } catch (PackageManager.NameNotFoundException unused) {
            setNone();
        }
    }

    /* access modifiers changed from: private */
    public void setNone() {
        setIcon(getContext().getResources().getDrawable(17301651));
        setSummary((CharSequence) getContext().getResources().getString(R$string.color_default));
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        super.onClick();
        showDialog();
    }

    /* access modifiers changed from: protected */
    public void showDialog() {
        final Map<String, IconPackInfo> loadAvailableIconPacks = loadAvailableIconPacks();
        final IconAdapter iconAdapter = new IconAdapter(getContext(), loadAvailableIconPacks, getPersistedString(""));
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setAdapter(iconAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String item = iconAdapter.getItem(i);
                if (!item.isEmpty()) {
                    IconPackInfo iconPackInfo = (IconPackInfo) loadAvailableIconPacks.get(item);
                    if (iconPackInfo != null) {
                        IconPackPreference.this.setIcon(iconPackInfo.icon);
                        IconPackPreference.this.setSummary(iconPackInfo.label);
                        boolean unused = IconPackPreference.this.persistString(iconPackInfo.packageName);
                        return;
                    }
                    return;
                }
                IconPackPreference.this.setNone();
                boolean unused2 = IconPackPreference.this.persistString("");
            }
        });
        builder.show();
    }

    private Map<String, IconPackInfo> loadAvailableIconPacks() {
        HashMap hashMap = new HashMap();
        List<ResolveInfo> queryIntentActivities = this.f84pm.queryIntentActivities(new Intent("com.novalauncher.THEME"), 0);
        queryIntentActivities.addAll(this.f84pm.queryIntentActivities(new Intent("org.adw.launcher.icons.ACTION_PICK_ICON"), 0));
        queryIntentActivities.addAll(this.f84pm.queryIntentActivities(new Intent("com.dlto.atom.launcher.THEME"), 0));
        queryIntentActivities.addAll(this.f84pm.queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("com.anddoes.launcher.THEME"), 0));
        for (ResolveInfo next : queryIntentActivities) {
            hashMap.put(next.activityInfo.packageName, new IconPackInfo(next, this.f84pm));
        }
        return hashMap;
    }

    private static class IconPackInfo {
        Drawable icon;
        CharSequence label;
        String packageName;

        IconPackInfo(ResolveInfo resolveInfo, PackageManager packageManager) {
            this.packageName = resolveInfo.activityInfo.packageName;
            this.icon = resolveInfo.loadIcon(packageManager);
            this.label = resolveInfo.loadLabel(packageManager);
        }

        public IconPackInfo(String str, Drawable drawable, String str2) {
            this.label = str;
            this.icon = drawable;
            this.packageName = str2;
        }
    }

    private static class IconAdapter extends BaseAdapter {
        String mCurrentIconPack;
        LayoutInflater mLayoutInflater;
        ArrayList<IconPackInfo> mSupportedPackages;

        public long getItemId(int i) {
            return 0;
        }

        IconAdapter(Context context, Map<String, IconPackInfo> map, String str) {
            this.mLayoutInflater = LayoutInflater.from(context);
            this.mSupportedPackages = new ArrayList<>(map.values());
            Collections.sort(this.mSupportedPackages, new Comparator<IconPackInfo>() {
                public int compare(IconPackInfo iconPackInfo, IconPackInfo iconPackInfo2) {
                    return iconPackInfo.label.toString().compareToIgnoreCase(iconPackInfo2.label.toString());
                }
            });
            Resources resources = context.getResources();
            this.mSupportedPackages.add(0, new IconPackInfo(context.getResources().getString(R$string.color_default), resources.getDrawable(17301651), ""));
            this.mCurrentIconPack = str;
        }

        public int getCount() {
            return this.mSupportedPackages.size();
        }

        public String getItem(int i) {
            return this.mSupportedPackages.get(i).packageName;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mLayoutInflater.inflate(R$layout.iconpack_dialog, (ViewGroup) null);
            }
            IconPackInfo iconPackInfo = this.mSupportedPackages.get(i);
            ((TextView) view.findViewById(R$id.title)).setText(iconPackInfo.label);
            ((ImageView) view.findViewById(R$id.icon)).setImageDrawable(iconPackInfo.icon);
            ((RadioButton) view.findViewById(R$id.radio)).setChecked(iconPackInfo.packageName.equals(this.mCurrentIconPack));
            return view;
        }
    }
}
