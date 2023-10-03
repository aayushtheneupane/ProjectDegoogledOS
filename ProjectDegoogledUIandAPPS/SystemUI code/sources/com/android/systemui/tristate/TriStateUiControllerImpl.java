package com.android.systemui.tristate;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManagerGlobal;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tristate.TriStateUiController;

public class TriStateUiControllerImpl implements ConfigurationController.ConfigurationListener, TriStateUiController {
    private static String TAG = "TriStateUiControllerImpl";
    private int mBackgroundColor = 0;
    private Context mContext;
    private int mDensity;
    private Dialog mDialog;
    private int mDialogPosition;
    private ViewGroup mDialogView;
    private final C1588H mHandler;
    private int mIconColor = 0;
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.SCREEN_OFF")) {
                boolean unused = TriStateUiControllerImpl.this.mScreenOn = false;
            } else if (action.equals("android.intent.action.SCREEN_ON")) {
                boolean unused2 = TriStateUiControllerImpl.this.mScreenOn = true;
            } else if (action.equals("android.media.RINGER_MODE_CHANGED")) {
                TriStateUiControllerImpl.this.updateRingerModeChanged();
            }
        }
    };
    private TriStateUiController.UserActivityListener mListener;
    OrientationEventListener mOrientationListener;
    private int mOrientationType = 0;
    /* access modifiers changed from: private */
    public boolean mScreenOn = true;
    private int mTextColor = 0;
    private int mThemeMode = 0;
    private ImageView mTriStateIcon;
    private int mTriStateMode = -1;
    private TextView mTriStateText;
    private Window mWindow;
    private WindowManager.LayoutParams mWindowLayoutParams;
    private int mWindowType;

    /* renamed from: com.android.systemui.tristate.TriStateUiControllerImpl$H */
    private final class C1588H extends Handler {
        private TriStateUiControllerImpl mUiController;

        public C1588H(TriStateUiControllerImpl triStateUiControllerImpl) {
            super(Looper.getMainLooper());
            this.mUiController = triStateUiControllerImpl;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.mUiController.handleShow();
            } else if (i == 2) {
                this.mUiController.handleDismiss();
            } else if (i == 3) {
                this.mUiController.handleResetTimeout();
            } else if (i == 4) {
                this.mUiController.handleStateChanged();
            }
        }
    }

    public TriStateUiControllerImpl(Context context) {
        this.mContext = context;
        this.mHandler = new C1588H(this);
        this.mOrientationListener = new OrientationEventListener(this.mContext, 3) {
            public void onOrientationChanged(int i) {
                TriStateUiControllerImpl.this.checkOrientationType();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.mContext.registerReceiver(this.mIntentReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    public void checkOrientationType() {
        int rotation;
        Display realDisplay = DisplayManagerGlobal.getInstance().getRealDisplay(0);
        if (realDisplay != null && (rotation = realDisplay.getRotation()) != this.mOrientationType) {
            this.mOrientationType = rotation;
            updateTriStateLayout();
        }
    }

    public void init(int i, TriStateUiController.UserActivityListener userActivityListener) {
        this.mWindowType = i;
        this.mDensity = this.mContext.getResources().getConfiguration().densityDpi;
        this.mListener = userActivityListener;
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        initDialog();
    }

    public void destroy() {
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).removeCallback(this);
        this.mContext.unregisterReceiver(this.mIntentReceiver);
    }

    private void initDialog() {
        this.mDialog = new Dialog(this.mContext);
        this.mWindow = this.mDialog.getWindow();
        this.mWindow.requestFeature(1);
        this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mWindow.clearFlags(2);
        this.mWindow.addFlags(17563944);
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mWindowLayoutParams = this.mWindow.getAttributes();
        WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
        layoutParams.type = this.mWindowType;
        layoutParams.format = -3;
        layoutParams.setTitle(TriStateUiControllerImpl.class.getSimpleName());
        WindowManager.LayoutParams layoutParams2 = this.mWindowLayoutParams;
        layoutParams2.gravity = 53;
        layoutParams2.y = this.mDialogPosition;
        this.mWindow.setAttributes(layoutParams2);
        this.mWindow.setSoftInputMode(48);
        this.mDialog.setContentView(C1779R$layout.tri_state_dialog);
        this.mDialogView = (ViewGroup) this.mDialog.findViewById(C1777R$id.tri_state_layout);
        this.mTriStateIcon = (ImageView) this.mDialog.findViewById(C1777R$id.tri_state_icon);
        this.mTriStateText = (TextView) this.mDialog.findViewById(C1777R$id.tri_state_text);
        updateTheme();
    }

    public void show() {
        this.mHandler.obtainMessage(1, 0, 0).sendToTarget();
    }

    private void registerOrientationListener(boolean z) {
        if (!this.mOrientationListener.canDetectOrientation() || !z) {
            Log.v(TAG, "Cannot detect orientation");
            this.mOrientationListener.disable();
            return;
        }
        Log.v(TAG, "Can detect orientation");
        this.mOrientationListener.enable();
    }

    private void updateTriStateLayout() {
        Resources resources;
        int i;
        int i2;
        int i3;
        int i4;
        int dimensionPixelSize;
        Context context = this.mContext;
        if (context != null && (resources = context.getResources()) != null) {
            WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
            int i5 = layoutParams.y;
            int i6 = layoutParams.x;
            int i7 = layoutParams.gravity;
            int i8 = this.mTriStateMode;
            boolean z = false;
            if (i8 == 0) {
                i2 = C1776R$drawable.ic_volume_ringer_mute;
                i = C1784R$string.volume_ringer_status_silent;
            } else if (i8 == 1) {
                i2 = C1776R$drawable.ic_volume_ringer_vibrate;
                i = C1784R$string.volume_ringer_status_vibrate;
            } else if (i8 != 2) {
                i2 = 0;
                i = 0;
            } else {
                i2 = C1776R$drawable.ic_volume_ringer;
                i = C1784R$string.volume_ringer_status_normal;
            }
            if (resources.getInteger(17694734) != 0) {
                z = true;
            }
            int i9 = this.mOrientationType;
            int i10 = 51;
            if (i9 != 1) {
                if (i9 == 2) {
                    i3 = z ? 83 : 85;
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_deep);
                    int i11 = this.mTriStateMode;
                    if (i11 == 0) {
                        resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position);
                        resources.getDimensionPixelSize(17105434);
                    } else if (i11 != 1) {
                        if (i11 == 2) {
                            resources.getDimensionPixelSize(C1775R$dimen.tri_state_down_dialog_position);
                            resources.getDimensionPixelSize(17105434);
                        }
                        i4 = C1776R$drawable.dialog_tri_state_middle_bg;
                    } else {
                        resources.getDimensionPixelSize(C1775R$dimen.tri_state_middle_dialog_position);
                        resources.getDimensionPixelSize(17105434);
                    }
                } else if (i9 != 3) {
                    if (z) {
                        i10 = 53;
                    }
                    int dimensionPixelSize2 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_deep);
                    int i12 = this.mTriStateMode;
                    if (i12 == 0) {
                        dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position) + resources.getDimensionPixelSize(17105434);
                        if (z) {
                            i4 = C1776R$drawable.right_dialog_tri_state_up_bg;
                        } else {
                            i4 = C1776R$drawable.left_dialog_tri_state_up_bg;
                        }
                    } else if (i12 == 1 || i12 != 2) {
                        dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.tri_state_middle_dialog_position) + resources.getDimensionPixelSize(17105434);
                        i4 = C1776R$drawable.dialog_tri_state_middle_bg;
                    } else {
                        dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.tri_state_down_dialog_position) + resources.getDimensionPixelSize(17105434);
                        i4 = z ? C1776R$drawable.right_dialog_tri_state_down_bg : C1776R$drawable.left_dialog_tri_state_down_bg;
                    }
                    int i13 = dimensionPixelSize;
                    i6 = dimensionPixelSize2;
                    i5 = i13;
                }
                i3 = z ? 85 : 53;
                i5 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_deep_land);
                if (!z) {
                    i5 += resources.getDimensionPixelSize(17105434);
                }
                int i14 = this.mTriStateMode;
                if (i14 == 0) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_l);
                } else if (i14 == 1) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_middle_dialog_position_l);
                } else if (i14 == 2) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_down_dialog_position_l);
                }
                i4 = C1776R$drawable.dialog_tri_state_middle_bg;
            } else {
                if (!z) {
                    i10 = 83;
                }
                int dimensionPixelSize3 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_deep_land);
                if (z) {
                    dimensionPixelSize3 += resources.getDimensionPixelSize(17105434);
                }
                int i15 = this.mTriStateMode;
                if (i15 == 0) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_up_dialog_position_l);
                } else if (i15 == 1) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_middle_dialog_position_l);
                } else if (i15 == 2) {
                    i6 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_down_dialog_position_l);
                }
                i4 = C1776R$drawable.dialog_tri_state_middle_bg;
            }
            if (this.mTriStateMode != -1) {
                ImageView imageView = this.mTriStateIcon;
                if (imageView != null) {
                    imageView.setImageResource(i2);
                }
                if (this.mTriStateText != null) {
                    String string = resources.getString(i);
                    if (string != null && this.mTriStateText.length() == string.length()) {
                        string = string + " ";
                    }
                    this.mTriStateText.setText(string);
                }
                ViewGroup viewGroup = this.mDialogView;
                if (viewGroup != null) {
                    viewGroup.setBackgroundDrawable(resources.getDrawable(i4));
                }
                this.mDialogPosition = i5;
            }
            int dimensionPixelSize4 = resources.getDimensionPixelSize(C1775R$dimen.tri_state_dialog_padding);
            WindowManager.LayoutParams layoutParams2 = this.mWindowLayoutParams;
            layoutParams2.gravity = i3;
            layoutParams2.y = i5 - dimensionPixelSize4;
            layoutParams2.x = i6 - dimensionPixelSize4;
            this.mWindow.setAttributes(layoutParams2);
            handleResetTimeout();
        }
    }

    /* access modifiers changed from: private */
    public void updateRingerModeChanged() {
        this.mHandler.obtainMessage(4, 0, 0).sendToTarget();
        if (this.mTriStateMode != -1) {
            show();
        }
    }

    /* access modifiers changed from: private */
    public void handleShow() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        handleResetTimeout();
        if (!this.mDialog.isShowing() && this.mScreenOn) {
            updateTheme();
            registerOrientationListener(true);
            checkOrientationType();
            this.mDialog.show();
            TriStateUiController.UserActivityListener userActivityListener = this.mListener;
            if (userActivityListener != null) {
                userActivityListener.onTriStateUserActivity();
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleDismiss() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        Dialog dialog = this.mDialog;
        if (dialog != null && dialog.isShowing()) {
            registerOrientationListener(false);
            this.mDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void handleStateChanged() {
        int ringerModeInternal = ((AudioManager) this.mContext.getSystemService("audio")).getRingerModeInternal();
        if (ringerModeInternal != this.mTriStateMode) {
            this.mTriStateMode = ringerModeInternal;
            updateTriStateLayout();
            TriStateUiController.UserActivityListener userActivityListener = this.mListener;
            if (userActivityListener != null) {
                userActivityListener.onTriStateUserActivity();
            }
        }
    }

    public void handleResetTimeout() {
        this.mHandler.removeMessages(2);
        C1588H h = this.mHandler;
        h.sendMessageDelayed(h.obtainMessage(2, 3, 0), 2000);
        TriStateUiController.UserActivityListener userActivityListener = this.mListener;
        if (userActivityListener != null) {
            userActivityListener.onTriStateUserActivity();
        }
    }

    public void onDensityOrFontScaleChanged() {
        handleDismiss();
        initDialog();
        updateTriStateLayout();
    }

    public void onConfigChanged(Configuration configuration) {
        updateTheme();
        updateTriStateLayout();
    }

    private void updateTheme() {
        this.mIconColor = getAttrColor(16843829);
        this.mTextColor = getAttrColor(16842806);
        this.mBackgroundColor = getAttrColor(16843827);
        this.mDialogView.setBackgroundTintList(ColorStateList.valueOf(this.mBackgroundColor));
        this.mTriStateIcon.setColorFilter(this.mIconColor);
        this.mTriStateText.setTextColor(this.mTextColor);
    }

    public int getAttrColor(int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }
}
