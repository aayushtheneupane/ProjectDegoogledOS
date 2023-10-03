package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.storage.StorageManager;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.havoc.config.center.C1715R;
import java.util.Locale;

public class AccessibilityServiceWarning {
    private static final View.OnTouchListener filterTouchListener = C0473x17fbc732.INSTANCE;

    static /* synthetic */ boolean lambda$static$0(View view, MotionEvent motionEvent) {
        if ((motionEvent.getFlags() & 1) == 0 && (motionEvent.getFlags() & 2) == 0) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            Toast.makeText(view.getContext(), C1715R.string.touch_filtered_warning, 0).show();
        }
        return true;
    }

    public static Dialog createCapabilitiesDialog(Activity activity, AccessibilityServiceInfo accessibilityServiceInfo, View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(createEnableDialogContentView(activity, accessibilityServiceInfo, onClickListener));
        AlertDialog create = builder.create();
        Window window = create.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.privateFlags |= 524288;
        window.setAttributes(attributes);
        create.create();
        create.setCanceledOnTouchOutside(true);
        return create;
    }

    public static Dialog createDisableDialog(Activity activity, AccessibilityServiceInfo accessibilityServiceInfo, View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(createDisableDialogContentView(activity, accessibilityServiceInfo, onClickListener));
        builder.setCancelable(true);
        return builder.create();
    }

    private static boolean isFullDiskEncrypted() {
        return StorageManager.isNonDefaultBlockEncrypted();
    }

    private static View createEnableDialogContentView(Context context, AccessibilityServiceInfo accessibilityServiceInfo, View.OnClickListener onClickListener) {
        Drawable drawable;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C1715R.layout.enable_accessibility_service_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(C1715R.C1718id.encryption_warning);
        if (isFullDiskEncrypted()) {
            textView.setText(context.getString(C1715R.string.enable_service_encryption_warning, new Object[]{getServiceName(context, accessibilityServiceInfo)}));
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        if (accessibilityServiceInfo.getResolveInfo().getIconResource() == 0) {
            drawable = ContextCompat.getDrawable(context, C1715R.C1717drawable.ic_accessibility_generic);
        } else {
            drawable = accessibilityServiceInfo.getResolveInfo().loadIcon(context.getPackageManager());
        }
        ((ImageView) inflate.findViewById(C1715R.C1718id.permissionDialog_icon)).setImageDrawable(drawable);
        ((TextView) inflate.findViewById(C1715R.C1718id.permissionDialog_title)).setText(context.getString(C1715R.string.enable_service_title, new Object[]{getServiceName(context, accessibilityServiceInfo)}));
        Button button = (Button) inflate.findViewById(C1715R.C1718id.permission_enable_allow_button);
        button.setOnClickListener(onClickListener);
        button.setOnTouchListener(filterTouchListener);
        ((Button) inflate.findViewById(C1715R.C1718id.permission_enable_deny_button)).setOnClickListener(onClickListener);
        return inflate;
    }

    private static View createDisableDialogContentView(Context context, AccessibilityServiceInfo accessibilityServiceInfo, View.OnClickListener onClickListener) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C1715R.layout.disable_accessibility_service_dialog_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(C1715R.C1718id.permissionDialog_disable_title)).setText(context.getString(C1715R.string.disable_service_title, new Object[]{getServiceName(context, accessibilityServiceInfo)}));
        ((TextView) inflate.findViewById(C1715R.C1718id.permissionDialog_disable_message)).setText(context.getString(C1715R.string.disable_service_message, new Object[]{context.getString(C1715R.string.accessibility_dialog_button_stop), getServiceName(context, accessibilityServiceInfo)}));
        ((Button) inflate.findViewById(C1715R.C1718id.permission_disable_stop_button)).setOnClickListener(onClickListener);
        ((Button) inflate.findViewById(C1715R.C1718id.permission_disable_cancel_button)).setOnClickListener(onClickListener);
        return inflate;
    }

    private static CharSequence getServiceName(Context context, AccessibilityServiceInfo accessibilityServiceInfo) {
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        return BidiFormatter.getInstance(locale).unicodeWrap(accessibilityServiceInfo.getResolveInfo().loadLabel(context.getPackageManager()));
    }
}
