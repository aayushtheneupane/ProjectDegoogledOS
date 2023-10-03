package com.android.settings;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.havoc.config.center.C1715R;

public class AllowBindAppWidgetActivity extends AlertActivity implements DialogInterface.OnClickListener {
    private CheckBox mAlwaysUse;
    private int mAppWidgetId;
    private AppWidgetManager mAppWidgetManager;
    private Bundle mBindOptions;
    private String mCallingPackage;
    private boolean mClicked;
    private ComponentName mComponentName;
    private UserHandle mProfile;

    public void onClick(DialogInterface dialogInterface, int i) {
        int i2;
        ComponentName componentName;
        this.mClicked = true;
        if (!(i != -1 || (i2 = this.mAppWidgetId) == -1 || (componentName = this.mComponentName) == null || this.mCallingPackage == null)) {
            try {
                if (this.mAppWidgetManager.bindAppWidgetIdIfAllowed(i2, this.mProfile, componentName, this.mBindOptions)) {
                    Intent intent = new Intent();
                    intent.putExtra("appWidgetId", this.mAppWidgetId);
                    setResult(-1, intent);
                }
            } catch (Exception unused) {
                Log.v("BIND_APPWIDGET", "Error binding widget with id " + this.mAppWidgetId + " and component " + this.mComponentName);
            }
            boolean isChecked = this.mAlwaysUse.isChecked();
            if (isChecked != this.mAppWidgetManager.hasBindAppWidgetPermission(this.mCallingPackage)) {
                this.mAppWidgetManager.setBindAppWidgetPermission(this.mCallingPackage, isChecked);
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (!this.mClicked) {
            finish();
        }
        AllowBindAppWidgetActivity.super.onPause();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.DialogInterface$OnClickListener, android.content.Context, com.android.internal.app.AlertActivity, com.android.settings.AllowBindAppWidgetActivity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Object obj;
        AllowBindAppWidgetActivity.super.onCreate(bundle);
        setResult(0);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.mAppWidgetId = intent.getIntExtra("appWidgetId", -1);
                this.mProfile = (UserHandle) intent.getParcelableExtra("appWidgetProviderProfile");
                if (this.mProfile == null) {
                    this.mProfile = Process.myUserHandle();
                }
                this.mComponentName = (ComponentName) intent.getParcelableExtra("appWidgetProvider");
                this.mBindOptions = (Bundle) intent.getParcelableExtra("appWidgetOptions");
                this.mCallingPackage = getCallingPackage();
                PackageManager packageManager = getPackageManager();
                obj = packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mCallingPackage, 0));
            } catch (Exception unused) {
                this.mAppWidgetId = -1;
                this.mComponentName = null;
                this.mCallingPackage = null;
                Log.v("BIND_APPWIDGET", "Error getting parameters");
                finish();
                return;
            }
        } else {
            obj = "";
        }
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = getString(C1715R.string.allow_bind_app_widget_activity_allow_bind_title);
        alertParams.mMessage = getString(C1715R.string.allow_bind_app_widget_activity_allow_bind, new Object[]{obj});
        alertParams.mPositiveButtonText = getString(C1715R.string.create);
        alertParams.mNegativeButtonText = getString(17039360);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonListener = this;
        alertParams.mView = ((LayoutInflater) getSystemService("layout_inflater")).inflate(17367090, (ViewGroup) null);
        this.mAlwaysUse = (CheckBox) alertParams.mView.findViewById(16908723);
        this.mAlwaysUse.setText(getString(C1715R.string.allow_bind_app_widget_activity_always_allow_bind, new Object[]{obj}));
        CheckBox checkBox = this.mAlwaysUse;
        checkBox.setPadding(checkBox.getPaddingLeft(), this.mAlwaysUse.getPaddingTop(), this.mAlwaysUse.getPaddingRight(), (int) (((float) this.mAlwaysUse.getPaddingBottom()) + getResources().getDimension(C1715R.dimen.bind_app_widget_dialog_checkbox_bottom_padding)));
        this.mAppWidgetManager = AppWidgetManager.getInstance(this);
        this.mAlwaysUse.setChecked(this.mAppWidgetManager.hasBindAppWidgetPermission(this.mCallingPackage, this.mProfile.getIdentifier()));
        setupAlert();
    }
}
