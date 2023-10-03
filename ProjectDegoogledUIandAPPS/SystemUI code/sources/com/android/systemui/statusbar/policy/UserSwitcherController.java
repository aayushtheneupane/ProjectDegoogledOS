package com.android.systemui.statusbar.policy;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.UserIcons;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.Utils;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dumpable;
import com.android.systemui.GuestResumeSessionReceiver;
import com.android.systemui.SystemUISecondaryUserService;
import com.android.systemui.p006qs.tiles.UserDetailView;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.DetailAdapter;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class UserSwitcherController implements Dumpable {
    private final ActivityStarter mActivityStarter;
    private final ArrayList<WeakReference<BaseUserAdapter>> mAdapters = new ArrayList<>();
    private Dialog mAddUserDialog;
    /* access modifiers changed from: private */
    public boolean mAddUsersWhenLocked;
    private final KeyguardMonitor.Callback mCallback = new KeyguardMonitor.Callback() {
        public void onKeyguardShowingChanged() {
            if (!UserSwitcherController.this.mKeyguardMonitor.isShowing()) {
                UserSwitcherController userSwitcherController = UserSwitcherController.this;
                userSwitcherController.mHandler.post(new Runnable() {
                    public final void run() {
                        UserSwitcherController.this.notifyAdapters();
                    }
                });
                return;
            }
            UserSwitcherController.this.notifyAdapters();
        }
    };
    protected final Context mContext;
    /* access modifiers changed from: private */
    public Dialog mExitGuestDialog;
    private SparseBooleanArray mForcePictureLoadForUserId = new SparseBooleanArray(2);
    private final GuestResumeSessionReceiver mGuestResumeSessionReceiver = new GuestResumeSessionReceiver();
    protected final Handler mHandler;
    /* access modifiers changed from: private */
    public final KeyguardMonitor mKeyguardMonitor;
    /* access modifiers changed from: private */
    public int mLastNonGuestUser = 0;
    /* access modifiers changed from: private */
    public boolean mPauseRefreshUsers;
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        private int mCallState;

        public void onCallStateChanged(int i, String str) {
            if (this.mCallState != i) {
                this.mCallState = i;
                UserSwitcherController.this.refreshUsers(-10000);
            }
        }
    };
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            int i = -10000;
            if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                if (UserSwitcherController.this.mExitGuestDialog != null && UserSwitcherController.this.mExitGuestDialog.isShowing()) {
                    UserSwitcherController.this.mExitGuestDialog.cancel();
                    Dialog unused = UserSwitcherController.this.mExitGuestDialog = null;
                }
                int intExtra = intent.getIntExtra("android.intent.extra.user_handle", -1);
                UserInfo userInfo = UserSwitcherController.this.mUserManager.getUserInfo(intExtra);
                int size = UserSwitcherController.this.mUsers.size();
                int i2 = 0;
                while (i2 < size) {
                    UserRecord userRecord = (UserRecord) UserSwitcherController.this.mUsers.get(i2);
                    UserInfo userInfo2 = userRecord.info;
                    if (userInfo2 != null) {
                        boolean z2 = userInfo2.id == intExtra;
                        if (userRecord.isCurrent != z2) {
                            UserSwitcherController.this.mUsers.set(i2, userRecord.copyWithIsCurrent(z2));
                        }
                        if (z2 && !userRecord.isGuest) {
                            int unused2 = UserSwitcherController.this.mLastNonGuestUser = userRecord.info.id;
                        }
                        if ((userInfo == null || !userInfo.isAdmin()) && userRecord.isRestricted) {
                            UserSwitcherController.this.mUsers.remove(i2);
                            i2--;
                        }
                    }
                    i2++;
                }
                UserSwitcherController.this.notifyAdapters();
                if (UserSwitcherController.this.mSecondaryUser != -10000) {
                    context.stopServiceAsUser(UserSwitcherController.this.mSecondaryUserServiceIntent, UserHandle.of(UserSwitcherController.this.mSecondaryUser));
                    int unused3 = UserSwitcherController.this.mSecondaryUser = -10000;
                }
                if (!(userInfo == null || userInfo.id == 0)) {
                    context.startServiceAsUser(UserSwitcherController.this.mSecondaryUserServiceIntent, UserHandle.of(userInfo.id));
                    int unused4 = UserSwitcherController.this.mSecondaryUser = userInfo.id;
                }
                z = true;
            } else if ("android.intent.action.USER_INFO_CHANGED".equals(intent.getAction())) {
                i = intent.getIntExtra("android.intent.extra.user_handle", -10000);
            } else if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction()) && intent.getIntExtra("android.intent.extra.user_handle", -10000) != 0) {
                return;
            }
            UserSwitcherController.this.refreshUsers(i);
            if (z) {
                UserSwitcherController.this.mUnpauseRefreshUsers.run();
            }
        }
    };
    private boolean mResumeUserOnGuestLogout = true;
    /* access modifiers changed from: private */
    public int mSecondaryUser = -10000;
    /* access modifiers changed from: private */
    public Intent mSecondaryUserServiceIntent;
    private final ContentObserver mSettingsObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            UserSwitcherController userSwitcherController = UserSwitcherController.this;
            boolean z2 = false;
            boolean unused = userSwitcherController.mSimpleUserSwitcher = Settings.Global.getInt(userSwitcherController.mContext.getContentResolver(), "lockscreenSimpleUserSwitcher", 0) != 0;
            UserSwitcherController userSwitcherController2 = UserSwitcherController.this;
            if (Settings.Global.getInt(userSwitcherController2.mContext.getContentResolver(), "add_users_when_locked", 0) != 0) {
                z2 = true;
            }
            boolean unused2 = userSwitcherController2.mAddUsersWhenLocked = z2;
            UserSwitcherController.this.refreshUsers(-10000);
        }
    };
    /* access modifiers changed from: private */
    public boolean mSimpleUserSwitcher;
    /* access modifiers changed from: private */
    public final Runnable mUnpauseRefreshUsers = new Runnable() {
        public void run() {
            UserSwitcherController.this.mHandler.removeCallbacks(this);
            boolean unused = UserSwitcherController.this.mPauseRefreshUsers = false;
            UserSwitcherController.this.refreshUsers(-10000);
        }
    };
    protected final UserManager mUserManager;
    /* access modifiers changed from: private */
    public ArrayList<UserRecord> mUsers = new ArrayList<>();
    public final DetailAdapter userDetailAdapter = new DetailAdapter() {
        private final Intent USER_SETTINGS_INTENT = new Intent("android.settings.USER_SETTINGS");

        public int getMetricsCategory() {
            return 125;
        }

        public Boolean getToggleState() {
            return null;
        }

        public void setToggleState(boolean z) {
        }

        public CharSequence getTitle() {
            return UserSwitcherController.this.mContext.getString(C1784R$string.quick_settings_user_title);
        }

        public View createDetailView(Context context, View view, ViewGroup viewGroup) {
            UserDetailView userDetailView;
            if (!(view instanceof UserDetailView)) {
                userDetailView = UserDetailView.inflate(context, viewGroup, false);
                userDetailView.createAndSetAdapter(UserSwitcherController.this);
            } else {
                userDetailView = (UserDetailView) view;
            }
            userDetailView.refreshAdapter();
            return userDetailView;
        }

        public Intent getSettingsIntent() {
            return this.USER_SETTINGS_INTENT;
        }
    };

    public UserSwitcherController(Context context, KeyguardMonitor keyguardMonitor, Handler handler, ActivityStarter activityStarter) {
        this.mContext = context;
        if (!UserManager.isGuestUserEphemeral()) {
            this.mGuestResumeSessionReceiver.register(context);
        }
        this.mKeyguardMonitor = keyguardMonitor;
        this.mHandler = handler;
        this.mActivityStarter = activityStarter;
        this.mUserManager = UserManager.get(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_ADDED");
        intentFilter.addAction("android.intent.action.USER_REMOVED");
        intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.intent.action.USER_STOPPED");
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        this.mContext.registerReceiverAsUser(this.mReceiver, UserHandle.SYSTEM, intentFilter, (String) null, (Handler) null);
        this.mSecondaryUserServiceIntent = new Intent(context, SystemUISecondaryUserService.class);
        this.mContext.registerReceiverAsUser(this.mReceiver, UserHandle.SYSTEM, new IntentFilter(), "com.android.systemui.permission.SELF", (Handler) null);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("lockscreenSimpleUserSwitcher"), true, this.mSettingsObserver);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("add_users_when_locked"), true, this.mSettingsObserver);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("allow_user_switching_when_system_user_locked"), true, this.mSettingsObserver);
        this.mSettingsObserver.onChange(false);
        keyguardMonitor.addCallback(this.mCallback);
        listenForCallState();
        refreshUsers(-10000);
    }

    /* access modifiers changed from: private */
    public void refreshUsers(int i) {
        UserInfo userInfo;
        if (i != -10000) {
            this.mForcePictureLoadForUserId.put(i, true);
        }
        if (!this.mPauseRefreshUsers) {
            boolean z = this.mForcePictureLoadForUserId.get(-1);
            SparseArray sparseArray = new SparseArray(this.mUsers.size());
            int size = this.mUsers.size();
            for (int i2 = 0; i2 < size; i2++) {
                UserRecord userRecord = this.mUsers.get(i2);
                if (!(userRecord == null || userRecord.picture == null || (userInfo = userRecord.info) == null || z || this.mForcePictureLoadForUserId.get(userInfo.id))) {
                    sparseArray.put(userRecord.info.id, userRecord.picture);
                }
            }
            this.mForcePictureLoadForUserId.clear();
            final boolean z2 = this.mAddUsersWhenLocked;
            new AsyncTask<SparseArray<Bitmap>, Void, ArrayList<UserRecord>>() {
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: android.content.pm.UserInfo} */
                /* access modifiers changed from: protected */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.util.ArrayList<com.android.systemui.statusbar.policy.UserSwitcherController.UserRecord> doInBackground(android.util.SparseArray<android.graphics.Bitmap>... r21) {
                    /*
                        r20 = this;
                        r0 = r20
                        r1 = 0
                        r2 = r21[r1]
                        com.android.systemui.statusbar.policy.UserSwitcherController r3 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.os.UserManager r3 = r3.mUserManager
                        r4 = 1
                        java.util.List r3 = r3.getUsers(r4)
                        r5 = 0
                        if (r3 != 0) goto L_0x0012
                        return r5
                    L_0x0012:
                        java.util.ArrayList r6 = new java.util.ArrayList
                        int r7 = r3.size()
                        r6.<init>(r7)
                        int r7 = android.app.ActivityManager.getCurrentUser()
                        com.android.systemui.statusbar.policy.UserSwitcherController r8 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.os.UserManager r8 = r8.mUserManager
                        boolean r8 = r8.canSwitchUsers()
                        java.util.Iterator r3 = r3.iterator()
                        r9 = r5
                    L_0x002c:
                        boolean r10 = r3.hasNext()
                        if (r10 == 0) goto L_0x00b9
                        java.lang.Object r10 = r3.next()
                        r12 = r10
                        android.content.pm.UserInfo r12 = (android.content.pm.UserInfo) r12
                        int r10 = r12.id
                        if (r7 != r10) goto L_0x003f
                        r15 = r4
                        goto L_0x0040
                    L_0x003f:
                        r15 = r1
                    L_0x0040:
                        if (r15 == 0) goto L_0x0045
                        r19 = r12
                        goto L_0x0047
                    L_0x0045:
                        r19 = r9
                    L_0x0047:
                        if (r8 != 0) goto L_0x004f
                        if (r15 == 0) goto L_0x004c
                        goto L_0x004f
                    L_0x004c:
                        r18 = r1
                        goto L_0x0051
                    L_0x004f:
                        r18 = r4
                    L_0x0051:
                        boolean r9 = r12.isEnabled()
                        if (r9 == 0) goto L_0x00b5
                        boolean r9 = r12.isGuest()
                        if (r9 == 0) goto L_0x0070
                        com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord r5 = new com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord
                        r11 = 0
                        r13 = 1
                        r14 = 0
                        r16 = 0
                        r9 = r5
                        r10 = r12
                        r12 = r13
                        r13 = r15
                        r15 = r16
                        r16 = r8
                        r9.<init>(r10, r11, r12, r13, r14, r15, r16)
                        goto L_0x00b5
                    L_0x0070:
                        boolean r9 = r12.supportsSwitchToByUser()
                        if (r9 == 0) goto L_0x00b5
                        int r9 = r12.id
                        java.lang.Object r9 = r2.get(r9)
                        android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
                        if (r9 != 0) goto L_0x009e
                        com.android.systemui.statusbar.policy.UserSwitcherController r9 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.os.UserManager r9 = r9.mUserManager
                        int r10 = r12.id
                        android.graphics.Bitmap r9 = r9.getUserIcon(r10)
                        if (r9 == 0) goto L_0x009e
                        com.android.systemui.statusbar.policy.UserSwitcherController r10 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.content.Context r10 = r10.mContext
                        android.content.res.Resources r10 = r10.getResources()
                        int r11 = com.android.systemui.C1775R$dimen.max_avatar_size
                        int r10 = r10.getDimensionPixelSize(r11)
                        android.graphics.Bitmap r9 = android.graphics.Bitmap.createScaledBitmap(r9, r10, r10, r4)
                    L_0x009e:
                        r13 = r9
                        if (r15 == 0) goto L_0x00a3
                        r9 = r1
                        goto L_0x00a7
                    L_0x00a3:
                        int r9 = r6.size()
                    L_0x00a7:
                        com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord r10 = new com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord
                        r14 = 0
                        r16 = 0
                        r17 = 0
                        r11 = r10
                        r11.<init>(r12, r13, r14, r15, r16, r17, r18)
                        r6.add(r9, r10)
                    L_0x00b5:
                        r9 = r19
                        goto L_0x002c
                    L_0x00b9:
                        int r2 = r6.size()
                        if (r2 > r4) goto L_0x00c1
                        if (r5 == 0) goto L_0x00ca
                    L_0x00c1:
                        com.android.systemui.statusbar.policy.UserSwitcherController r2 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.content.Context r2 = r2.mContext
                        java.lang.String r3 = "HasSeenMultiUser"
                        com.android.systemui.Prefs.putBoolean(r2, r3, r4)
                    L_0x00ca:
                        com.android.systemui.statusbar.policy.UserSwitcherController r2 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.os.UserManager r2 = r2.mUserManager
                        android.os.UserHandle r3 = android.os.UserHandle.SYSTEM
                        java.lang.String r7 = "no_add_user"
                        boolean r2 = r2.hasBaseUserRestriction(r7, r3)
                        r2 = r2 ^ r4
                        if (r9 == 0) goto L_0x00e7
                        boolean r3 = r9.isAdmin()
                        if (r3 != 0) goto L_0x00e3
                        int r3 = r9.id
                        if (r3 != 0) goto L_0x00e7
                    L_0x00e3:
                        if (r2 == 0) goto L_0x00e7
                        r3 = r4
                        goto L_0x00e8
                    L_0x00e7:
                        r3 = r1
                    L_0x00e8:
                        if (r2 == 0) goto L_0x00f0
                        boolean r2 = r9
                        if (r2 == 0) goto L_0x00f0
                        r2 = r4
                        goto L_0x00f1
                    L_0x00f0:
                        r2 = r1
                    L_0x00f1:
                        if (r3 != 0) goto L_0x00f5
                        if (r2 == 0) goto L_0x00f9
                    L_0x00f5:
                        if (r5 != 0) goto L_0x00f9
                        r7 = r4
                        goto L_0x00fa
                    L_0x00f9:
                        r7 = r1
                    L_0x00fa:
                        if (r3 != 0) goto L_0x00fe
                        if (r2 == 0) goto L_0x010a
                    L_0x00fe:
                        com.android.systemui.statusbar.policy.UserSwitcherController r2 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        android.os.UserManager r2 = r2.mUserManager
                        boolean r2 = r2.canAddMoreUsers()
                        if (r2 == 0) goto L_0x010a
                        r2 = r4
                        goto L_0x010b
                    L_0x010a:
                        r2 = r1
                    L_0x010b:
                        boolean r3 = r9
                        r3 = r3 ^ r4
                        com.android.systemui.statusbar.policy.UserSwitcherController r4 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        boolean r4 = r4.mSimpleUserSwitcher
                        if (r4 != 0) goto L_0x013d
                        if (r5 != 0) goto L_0x0131
                        if (r7 == 0) goto L_0x013d
                        com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord r1 = new com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord
                        r10 = 0
                        r11 = 0
                        r12 = 1
                        r13 = 0
                        r14 = 0
                        r9 = r1
                        r15 = r3
                        r16 = r8
                        r9.<init>(r10, r11, r12, r13, r14, r15, r16)
                        com.android.systemui.statusbar.policy.UserSwitcherController r4 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        r4.checkIfAddUserDisallowedByAdminOnly(r1)
                        r6.add(r1)
                        goto L_0x013d
                    L_0x0131:
                        boolean r4 = r5.isCurrent
                        if (r4 == 0) goto L_0x0136
                        goto L_0x013a
                    L_0x0136:
                        int r1 = r6.size()
                    L_0x013a:
                        r6.add(r1, r5)
                    L_0x013d:
                        com.android.systemui.statusbar.policy.UserSwitcherController r1 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        boolean r1 = r1.mSimpleUserSwitcher
                        if (r1 != 0) goto L_0x015d
                        if (r2 == 0) goto L_0x015d
                        com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord r1 = new com.android.systemui.statusbar.policy.UserSwitcherController$UserRecord
                        r10 = 0
                        r11 = 0
                        r12 = 0
                        r13 = 0
                        r14 = 1
                        r9 = r1
                        r15 = r3
                        r16 = r8
                        r9.<init>(r10, r11, r12, r13, r14, r15, r16)
                        com.android.systemui.statusbar.policy.UserSwitcherController r0 = com.android.systemui.statusbar.policy.UserSwitcherController.this
                        r0.checkIfAddUserDisallowedByAdminOnly(r1)
                        r6.add(r1)
                    L_0x015d:
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.UserSwitcherController.C15731.doInBackground(android.util.SparseArray[]):java.util.ArrayList");
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(ArrayList<UserRecord> arrayList) {
                    if (arrayList != null) {
                        ArrayList unused = UserSwitcherController.this.mUsers = arrayList;
                        UserSwitcherController.this.notifyAdapters();
                    }
                }
            }.execute(new SparseArray[]{sparseArray});
        }
    }

    private void pauseRefreshUsers() {
        if (!this.mPauseRefreshUsers) {
            this.mHandler.postDelayed(this.mUnpauseRefreshUsers, 3000);
            this.mPauseRefreshUsers = true;
        }
    }

    /* access modifiers changed from: private */
    public void notifyAdapters() {
        for (int size = this.mAdapters.size() - 1; size >= 0; size--) {
            BaseUserAdapter baseUserAdapter = (BaseUserAdapter) this.mAdapters.get(size).get();
            if (baseUserAdapter != null) {
                baseUserAdapter.notifyDataSetChanged();
            } else {
                this.mAdapters.remove(size);
            }
        }
    }

    public boolean isSimpleUserSwitcher() {
        return this.mSimpleUserSwitcher;
    }

    public boolean useFullscreenUserSwitcher() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "enable_fullscreen_user_switcher", -1);
        if (i != -1) {
            return i != 0;
        }
        return this.mContext.getResources().getBoolean(C1773R$bool.config_enableFullscreenUserSwitcher);
    }

    public void switchTo(UserRecord userRecord) {
        int i;
        UserInfo userInfo;
        if (userRecord.isGuest && userRecord.info == null) {
            UserManager userManager = this.mUserManager;
            Context context = this.mContext;
            UserInfo createGuest = userManager.createGuest(context, context.getString(C1784R$string.guest_nickname));
            if (createGuest != null) {
                i = createGuest.id;
            } else {
                return;
            }
        } else if (userRecord.isAddUser) {
            showAddUserDialog();
            return;
        } else {
            i = userRecord.info.id;
        }
        int currentUser = ActivityManager.getCurrentUser();
        if (currentUser == i) {
            if (userRecord.isGuest) {
                showExitGuestDialog(i);
            }
        } else if (!UserManager.isGuestUserEphemeral() || (userInfo = this.mUserManager.getUserInfo(currentUser)) == null || !userInfo.isGuest()) {
            switchToUserId(i);
        } else {
            showExitGuestDialog(currentUser, userRecord.resolveId());
        }
    }

    public int getSwitchableUserCount() {
        int size = this.mUsers.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            UserInfo userInfo = this.mUsers.get(i2).info;
            if (userInfo != null && userInfo.supportsSwitchToByUser()) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void switchToUserId(int i) {
        try {
            pauseRefreshUsers();
            ActivityManager.getService().switchUser(i);
        } catch (RemoteException e) {
            Log.e("UserSwitcherController", "Couldn't switch user.", e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r0 = r2.mUserManager.getUserInfo((r0 = r2.mLastNonGuestUser));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showExitGuestDialog(int r3) {
        /*
            r2 = this;
            boolean r0 = r2.mResumeUserOnGuestLogout
            if (r0 == 0) goto L_0x001f
            int r0 = r2.mLastNonGuestUser
            if (r0 == 0) goto L_0x001f
            android.os.UserManager r1 = r2.mUserManager
            android.content.pm.UserInfo r0 = r1.getUserInfo(r0)
            if (r0 == 0) goto L_0x001f
            boolean r1 = r0.isEnabled()
            if (r1 == 0) goto L_0x001f
            boolean r1 = r0.supportsSwitchToByUser()
            if (r1 == 0) goto L_0x001f
            int r0 = r0.id
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            r2.showExitGuestDialog(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.UserSwitcherController.showExitGuestDialog(int):void");
    }

    /* access modifiers changed from: protected */
    public void showExitGuestDialog(int i, int i2) {
        Dialog dialog = this.mExitGuestDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mExitGuestDialog.cancel();
        }
        this.mExitGuestDialog = new ExitGuestDialog(this.mContext, i, i2);
        this.mExitGuestDialog.show();
    }

    public void showAddUserDialog() {
        Dialog dialog = this.mAddUserDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mAddUserDialog.cancel();
        }
        this.mAddUserDialog = new AddUserDialog(this.mContext);
        this.mAddUserDialog.show();
    }

    /* access modifiers changed from: protected */
    public void exitGuest(int i, int i2) {
        switchToUserId(i2);
        this.mUserManager.removeUser(i);
    }

    private void listenForCallState() {
        TelephonyManager.from(this.mContext).listen(this.mPhoneStateListener, 32);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("UserSwitcherController state:");
        printWriter.println("  mLastNonGuestUser=" + this.mLastNonGuestUser);
        printWriter.print("  mUsers.size=");
        printWriter.println(this.mUsers.size());
        for (int i = 0; i < this.mUsers.size(); i++) {
            printWriter.print("    ");
            printWriter.println(this.mUsers.get(i).toString());
        }
    }

    public String getCurrentUserName(Context context) {
        UserRecord userRecord;
        UserInfo userInfo;
        if (this.mUsers.isEmpty() || (userRecord = this.mUsers.get(0)) == null || (userInfo = userRecord.info) == null) {
            return null;
        }
        if (userRecord.isGuest) {
            return context.getString(C1784R$string.guest_nickname);
        }
        return userInfo.name;
    }

    public void onDensityOrFontScaleChanged() {
        refreshUsers(-1);
    }

    @VisibleForTesting
    public void addAdapter(WeakReference<BaseUserAdapter> weakReference) {
        this.mAdapters.add(weakReference);
    }

    @VisibleForTesting
    public ArrayList<UserRecord> getUsers() {
        return this.mUsers;
    }

    public static abstract class BaseUserAdapter extends BaseAdapter {
        final UserSwitcherController mController;
        private final KeyguardMonitor mKeyguardMonitor;
        private final UnlockMethodCache mUnlockMethodCache;

        public long getItemId(int i) {
            return (long) i;
        }

        protected BaseUserAdapter(UserSwitcherController userSwitcherController) {
            this.mController = userSwitcherController;
            this.mKeyguardMonitor = userSwitcherController.mKeyguardMonitor;
            this.mUnlockMethodCache = UnlockMethodCache.getInstance(userSwitcherController.mContext);
            userSwitcherController.addAdapter(new WeakReference(this));
        }

        public int getCount() {
            int i = 0;
            if (!(this.mKeyguardMonitor.isShowing() && this.mKeyguardMonitor.isSecure() && !this.mUnlockMethodCache.canSkipBouncer())) {
                return this.mController.getUsers().size();
            }
            int size = this.mController.getUsers().size();
            int i2 = 0;
            while (i < size && !this.mController.getUsers().get(i).isRestricted) {
                i2++;
                i++;
            }
            return i2;
        }

        public UserRecord getItem(int i) {
            return this.mController.getUsers().get(i);
        }

        public void switchTo(UserRecord userRecord) {
            this.mController.switchTo(userRecord);
        }

        public String getName(Context context, UserRecord userRecord) {
            if (userRecord.isGuest) {
                if (userRecord.isCurrent) {
                    return context.getString(C1784R$string.guest_exit_guest);
                }
                return context.getString(userRecord.info == null ? C1784R$string.guest_new_guest : C1784R$string.guest_nickname);
            } else if (userRecord.isAddUser) {
                return context.getString(C1784R$string.user_add_user);
            } else {
                return userRecord.info.name;
            }
        }

        public Drawable getDrawable(Context context, UserRecord userRecord) {
            if (userRecord.isAddUser) {
                return context.getDrawable(C1776R$drawable.ic_add_circle_qs);
            }
            Drawable defaultUserIcon = UserIcons.getDefaultUserIcon(context.getResources(), userRecord.resolveId(), false);
            if (userRecord.isGuest) {
                defaultUserIcon.setColorFilter(Utils.getColorAttrDefaultColor(context, 16842800), PorterDuff.Mode.SRC_IN);
            }
            return defaultUserIcon;
        }

        public void refresh() {
            this.mController.refreshUsers(-10000);
        }
    }

    /* access modifiers changed from: private */
    public void checkIfAddUserDisallowedByAdminOnly(UserRecord userRecord) {
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_add_user", ActivityManager.getCurrentUser());
        if (checkIfRestrictionEnforced == null || RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, "no_add_user", ActivityManager.getCurrentUser())) {
            userRecord.isDisabledByAdmin = false;
            userRecord.enforcedAdmin = null;
            return;
        }
        userRecord.isDisabledByAdmin = true;
        userRecord.enforcedAdmin = checkIfRestrictionEnforced;
    }

    public void startActivity(Intent intent) {
        this.mActivityStarter.startActivity(intent, true);
    }

    public static final class UserRecord {
        public RestrictedLockUtils.EnforcedAdmin enforcedAdmin;
        public final UserInfo info;
        public final boolean isAddUser;
        public final boolean isCurrent;
        public boolean isDisabledByAdmin;
        public final boolean isGuest;
        public final boolean isRestricted;
        public boolean isSwitchToEnabled;
        public final Bitmap picture;

        public UserRecord(UserInfo userInfo, Bitmap bitmap, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.info = userInfo;
            this.picture = bitmap;
            this.isGuest = z;
            this.isCurrent = z2;
            this.isAddUser = z3;
            this.isRestricted = z4;
            this.isSwitchToEnabled = z5;
        }

        public UserRecord copyWithIsCurrent(boolean z) {
            return new UserRecord(this.info, this.picture, this.isGuest, z, this.isAddUser, this.isRestricted, this.isSwitchToEnabled);
        }

        public int resolveId() {
            UserInfo userInfo;
            if (this.isGuest || (userInfo = this.info) == null) {
                return -10000;
            }
            return userInfo.id;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("UserRecord(");
            if (this.info != null) {
                sb.append("name=\"");
                sb.append(this.info.name);
                sb.append("\" id=");
                sb.append(this.info.id);
            } else if (this.isGuest) {
                sb.append("<add guest placeholder>");
            } else if (this.isAddUser) {
                sb.append("<add user placeholder>");
            }
            if (this.isGuest) {
                sb.append(" <isGuest>");
            }
            if (this.isAddUser) {
                sb.append(" <isAddUser>");
            }
            if (this.isCurrent) {
                sb.append(" <isCurrent>");
            }
            if (this.picture != null) {
                sb.append(" <hasPicture>");
            }
            if (this.isRestricted) {
                sb.append(" <isRestricted>");
            }
            if (this.isDisabledByAdmin) {
                sb.append(" <isDisabledByAdmin>");
                sb.append(" enforcedAdmin=");
                sb.append(this.enforcedAdmin);
            }
            if (this.isSwitchToEnabled) {
                sb.append(" <isSwitchToEnabled>");
            }
            sb.append(')');
            return sb.toString();
        }
    }

    private final class ExitGuestDialog extends SystemUIDialog implements DialogInterface.OnClickListener {
        private final int mGuestId;
        private final int mTargetId;

        public ExitGuestDialog(Context context, int i, int i2) {
            super(context);
            setTitle(C1784R$string.guest_exit_guest_dialog_title);
            setMessage(context.getString(C1784R$string.guest_exit_guest_dialog_message));
            setButton(-2, context.getString(17039360), this);
            setButton(-1, context.getString(C1784R$string.guest_exit_guest_dialog_remove), this);
            SystemUIDialog.setWindowOnTop(this);
            setCanceledOnTouchOutside(false);
            this.mGuestId = i;
            this.mTargetId = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                cancel();
                return;
            }
            dismiss();
            UserSwitcherController.this.exitGuest(this.mGuestId, this.mTargetId);
        }
    }

    private final class AddUserDialog extends SystemUIDialog implements DialogInterface.OnClickListener {
        public AddUserDialog(Context context) {
            super(context);
            setTitle(C1784R$string.user_add_user_title);
            setMessage(context.getString(C1784R$string.user_add_user_message_short));
            setButton(-2, context.getString(17039360), this);
            setButton(-1, context.getString(17039370), this);
            SystemUIDialog.setWindowOnTop(this);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                cancel();
                return;
            }
            dismiss();
            if (!ActivityManager.isUserAMonkey()) {
                UserSwitcherController userSwitcherController = UserSwitcherController.this;
                UserInfo createUser = userSwitcherController.mUserManager.createUser(userSwitcherController.mContext.getString(C1784R$string.user_new_user_name), 0);
                if (createUser != null) {
                    int i2 = createUser.id;
                    UserSwitcherController.this.mUserManager.setUserIcon(i2, UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(UserSwitcherController.this.mContext.getResources(), i2, false)));
                    UserSwitcherController.this.switchToUserId(i2);
                }
            }
        }
    }
}
