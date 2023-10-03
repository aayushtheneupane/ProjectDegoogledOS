package com.android.contacts.util;

import android.content.res.Resources;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;

public class ViewUtil {
    private static final ViewOutlineProvider OVAL_OUTLINE_PROVIDER;
    private static final ViewOutlineProvider RECT_OUTLINE_PROVIDER;

    public static boolean isViewLayoutRtl(View view) {
        return view.getLayoutDirection() == 1;
    }

    static {
        if (CompatUtils.isLollipopCompatible()) {
            OVAL_OUTLINE_PROVIDER = new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setOval(0, 0, view.getWidth(), view.getHeight());
                }
            };
        } else {
            OVAL_OUTLINE_PROVIDER = null;
        }
        if (CompatUtils.isLollipopCompatible()) {
            RECT_OUTLINE_PROVIDER = new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setRect(0, 0, view.getWidth(), view.getHeight());
                }
            };
        } else {
            RECT_OUTLINE_PROVIDER = null;
        }
    }

    public static void addRectangularOutlineProvider(View view, Resources resources) {
        if (CompatUtils.isLollipopCompatible()) {
            view.setOutlineProvider(RECT_OUTLINE_PROVIDER);
        }
    }

    public static void setupFloatingActionButton(View view, Resources resources) {
        if (CompatUtils.isLollipopCompatible()) {
            view.setOutlineProvider(OVAL_OUTLINE_PROVIDER);
            view.setTranslationZ((float) resources.getDimensionPixelSize(R.dimen.floating_action_button_translation_z));
        }
    }
}
