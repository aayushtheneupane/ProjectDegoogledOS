package com.android.settings.password;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.havoc.config.center.C1715R;
import java.util.List;

public class ChooseLockTypeDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    private ScreenLockAdapter mAdapter;
    private ChooseLockGenericController mController;

    public int getMetricsCategory() {
        return 990;
    }

    public static ChooseLockTypeDialogFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", i);
        ChooseLockTypeDialogFragment chooseLockTypeDialogFragment = new ChooseLockTypeDialogFragment();
        chooseLockTypeDialogFragment.setArguments(bundle);
        return chooseLockTypeDialogFragment;
    }

    public interface OnLockTypeSelectedListener {
        void onLockTypeSelected(ScreenLockType screenLockType);

        void startChooseLockActivity(ScreenLockType screenLockType, Activity activity) {
            Intent intent = activity.getIntent();
            Intent intent2 = new Intent(activity, SetupChooseLockGeneric.class);
            intent2.addFlags(33554432);
            ChooseLockTypeDialogFragment.copyBooleanExtra(intent, intent2, "has_challenge", false);
            ChooseLockTypeDialogFragment.copyBooleanExtra(intent, intent2, "show_options_button", false);
            if (intent.hasExtra("choose_lock_generic_extras")) {
                intent2.putExtras(intent.getBundleExtra("choose_lock_generic_extras"));
            }
            intent2.putExtra("lockscreen.password_type", screenLockType.defaultQuality);
            intent2.putExtra("challenge", intent.getLongExtra("challenge", 0));
            WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
            activity.startActivity(intent2);
            activity.finish();
        }
    }

    /* access modifiers changed from: private */
    public static void copyBooleanExtra(Intent intent, Intent intent2, String str, boolean z) {
        intent2.putExtra(str, intent.getBooleanExtra(str, z));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mController = new ChooseLockGenericController(getContext(), getArguments().getInt("userId"));
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        OnLockTypeSelectedListener onLockTypeSelectedListener;
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof OnLockTypeSelectedListener) {
            onLockTypeSelectedListener = (OnLockTypeSelectedListener) parentFragment;
        } else {
            Context context = getContext();
            onLockTypeSelectedListener = context instanceof OnLockTypeSelectedListener ? (OnLockTypeSelectedListener) context : null;
        }
        if (onLockTypeSelectedListener != null) {
            onLockTypeSelectedListener.onLockTypeSelected((ScreenLockType) this.mAdapter.getItem(i));
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        this.mAdapter = new ScreenLockAdapter(context, this.mController.getVisibleScreenLockTypes(65536, false), this.mController);
        builder.setAdapter(this.mAdapter, this);
        builder.setTitle((int) C1715R.string.setup_lock_settings_options_dialog_title);
        return builder.create();
    }

    private static class ScreenLockAdapter extends ArrayAdapter<ScreenLockType> {
        private final ChooseLockGenericController mController;

        ScreenLockAdapter(Context context, List<ScreenLockType> list, ChooseLockGenericController chooseLockGenericController) {
            super(context, C1715R.layout.choose_lock_dialog_item, list);
            this.mController = chooseLockGenericController;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Context context = viewGroup.getContext();
            if (view == null) {
                view = LayoutInflater.from(context).inflate(C1715R.layout.choose_lock_dialog_item, viewGroup, false);
            }
            ScreenLockType screenLockType = (ScreenLockType) getItem(i);
            TextView textView = (TextView) view;
            textView.setText(this.mController.getTitle(screenLockType));
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getIconForScreenLock(context, screenLockType), (Drawable) null, (Drawable) null, (Drawable) null);
            return view;
        }

        private static Drawable getIconForScreenLock(Context context, ScreenLockType screenLockType) {
            int i = C11191.$SwitchMap$com$android$settings$password$ScreenLockType[screenLockType.ordinal()];
            if (i == 1) {
                return context.getDrawable(C1715R.C1717drawable.ic_pattern);
            }
            if (i == 2) {
                return context.getDrawable(C1715R.C1717drawable.ic_pin);
            }
            if (i != 3) {
                return null;
            }
            return context.getDrawable(C1715R.C1717drawable.ic_password);
        }
    }

    /* renamed from: com.android.settings.password.ChooseLockTypeDialogFragment$1 */
    static /* synthetic */ class C11191 {
        static final /* synthetic */ int[] $SwitchMap$com$android$settings$password$ScreenLockType = new int[ScreenLockType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.settings.password.ScreenLockType[] r0 = com.android.settings.password.ScreenLockType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$settings$password$ScreenLockType = r0
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PATTERN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PIN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.PASSWORD     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.NONE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.SWIPE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$android$settings$password$ScreenLockType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.settings.password.ScreenLockType r1 = com.android.settings.password.ScreenLockType.MANAGED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ChooseLockTypeDialogFragment.C11191.<clinit>():void");
        }
    }
}
