package com.android.systemui.tuner;

import android.content.DialogInterface;
import android.view.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class KeycodeSelectionHelper {
    private static final ArrayList<String> mKeycodeStrings = new ArrayList<>();
    /* access modifiers changed from: private */
    public static final ArrayList<Integer> mKeycodes = new ArrayList<>();

    public interface OnSelectionComplete {
        void onSelectionComplete(int i);
    }

    static {
        for (Field field : KeyEvent.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getName().startsWith("KEYCODE_") && field.getType().equals(Integer.TYPE)) {
                try {
                    mKeycodeStrings.add(formatString(field.getName()));
                    mKeycodes.add((Integer) field.get((Object) null));
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    private static String formatString(String str) {
        StringBuilder sb = new StringBuilder(str.replace("KEYCODE_", "").replace("_", " ").toLowerCase());
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i - 1) == ' ') {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }
        }
        return sb.toString();
    }

    /* renamed from: com.android.systemui.tuner.KeycodeSelectionHelper$1 */
    class C15921 implements DialogInterface.OnClickListener {
        final /* synthetic */ OnSelectionComplete val$listener;

        public void onClick(DialogInterface dialogInterface, int i) {
            this.val$listener.onSelectionComplete(((Integer) KeycodeSelectionHelper.mKeycodes.get(i)).intValue());
        }
    }
}
