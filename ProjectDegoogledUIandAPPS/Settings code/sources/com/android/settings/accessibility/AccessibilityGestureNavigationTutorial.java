package com.android.settings.accessibility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.havoc.config.center.C1715R;

public class AccessibilityGestureNavigationTutorial {
    private static final DialogInterface.OnClickListener mOnClickListener = C0471x9575e4a0.INSTANCE;

    public static void showGestureNavigationSettingsTutorialDialog(Context context, DialogInterface.OnDismissListener onDismissListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createTutorialDialogContentView(context, 2));
        builder.setNegativeButton((int) C1715R.string.accessibility_tutorial_dialog_button, mOnClickListener);
        builder.setOnDismissListener(onDismissListener);
        AlertDialog create = builder.create();
        create.requestWindowFeature(1);
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    static AlertDialog showAccessibilityButtonTutorialDialog(Context context) {
        AlertDialog createDialog = createDialog(context, 0);
        if (!isGestureNavigateEnabled(context)) {
            updateMessageWithIcon(context, createDialog);
        }
        return createDialog;
    }

    static AlertDialog showGestureNavigationTutorialDialog(Context context) {
        return createDialog(context, 1);
    }

    private static View createTutorialDialogContentView(Context context, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (i == 0) {
            return layoutInflater.inflate(C1715R.layout.tutorial_dialog_launch_service_by_accessibility_button, (ViewGroup) null);
        }
        int i2 = C1715R.raw.illustration_accessibility_gesture_three_finger;
        if (i == 1) {
            View inflate = layoutInflater.inflate(C1715R.layout.tutorial_dialog_launch_service_by_gesture_navigation, (ViewGroup) null);
            TextureView textureView = (TextureView) inflate.findViewById(C1715R.C1718id.gesture_tutorial_video);
            TextView textView = (TextView) inflate.findViewById(C1715R.C1718id.gesture_tutorial_message);
            if (!isTouchExploreOn(context)) {
                i2 = C1715R.raw.illustration_accessibility_gesture_two_finger;
            }
            VideoPlayer.create(context, i2, textureView);
            textView.setText(isTouchExploreOn(context) ? C1715R.string.accessibility_tutorial_dialog_message_gesture_with_talkback : C1715R.string.accessibility_tutorial_dialog_message_gesture_without_talkback);
            return inflate;
        } else if (i != 2) {
            return null;
        } else {
            View inflate2 = layoutInflater.inflate(C1715R.layout.tutorial_dialog_launch_by_gesture_navigation_settings, (ViewGroup) null);
            TextureView textureView2 = (TextureView) inflate2.findViewById(C1715R.C1718id.gesture_tutorial_video);
            TextView textView2 = (TextView) inflate2.findViewById(C1715R.C1718id.gesture_tutorial_message);
            if (!isTouchExploreOn(context)) {
                i2 = C1715R.raw.illustration_accessibility_gesture_two_finger;
            }
            VideoPlayer.create(context, i2, textureView2);
            textView2.setText(isTouchExploreOn(context) ? C1715R.string.f91x63aa3085 : C1715R.string.f92x4954d4f1);
            return inflate2;
        }
    }

    private static AlertDialog createDialog(Context context, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(createTutorialDialogContentView(context, i));
        builder.setNegativeButton((int) C1715R.string.accessibility_tutorial_dialog_button, mOnClickListener);
        AlertDialog create = builder.create();
        create.requestWindowFeature(1);
        create.setCanceledOnTouchOutside(false);
        create.show();
        return create;
    }

    private static void updateMessageWithIcon(Context context, AlertDialog alertDialog) {
        TextView textView = (TextView) alertDialog.findViewById(C1715R.C1718id.button_tutorial_message);
        textView.setText(getMessageStringWithIcon(context, textView.getLineHeight()));
    }

    private static SpannableString getMessageStringWithIcon(Context context, int i) {
        String string = context.getString(C1715R.string.accessibility_tutorial_dialog_message_button);
        SpannableString valueOf = SpannableString.valueOf(string);
        int indexOf = string.indexOf("%s");
        Drawable drawable = context.getDrawable(C1715R.C1717drawable.ic_accessibility_new);
        drawable.setTint(getThemeAttrColor(context, 16842806));
        drawable.setBounds(0, 0, i, i);
        valueOf.setSpan(new ImageSpan(drawable), indexOf, indexOf + 2, 33);
        return valueOf;
    }

    private static int getThemeAttrColor(Context context, int i) {
        return ContextCompat.getColor(context, getAttrResourceId(context, i));
    }

    private static int getAttrResourceId(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static boolean isGestureNavigateEnabled(Context context) {
        return context.getResources().getInteger(17694869) == 2;
    }

    private static boolean isTouchExploreOn(Context context) {
        return ((AccessibilityManager) context.getSystemService("accessibility")).isTouchExplorationEnabled();
    }
}
