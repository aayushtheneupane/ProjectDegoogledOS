package com.android.dialer.main.impl.bottomnav;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public final class BottomNavBar extends LinearLayout {
    private BottomNavItem callLog;
    private BottomNavItem contacts;
    private final List<OnBottomNavTabSelectedListener> listeners = new ArrayList();
    private int selectedTab;
    private BottomNavItem speedDial;
    private BottomNavItem voicemail;

    public interface OnBottomNavTabSelectedListener {
        void onCallLogSelected();

        void onContactsSelected();

        void onSpeedDialSelected();

        void onVoicemailSelected();
    }

    public BottomNavBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setSelected(View view) {
        BottomNavItem bottomNavItem = this.speedDial;
        boolean z = true;
        bottomNavItem.setSelected(view == bottomNavItem);
        BottomNavItem bottomNavItem2 = this.callLog;
        bottomNavItem2.setSelected(view == bottomNavItem2);
        BottomNavItem bottomNavItem3 = this.contacts;
        bottomNavItem3.setSelected(view == bottomNavItem3);
        BottomNavItem bottomNavItem4 = this.voicemail;
        if (view != bottomNavItem4) {
            z = false;
        }
        bottomNavItem4.setSelected(z);
    }

    public void addOnTabSelectedListener(OnBottomNavTabSelectedListener onBottomNavTabSelectedListener) {
        this.listeners.add(onBottomNavTabSelectedListener);
    }

    public int getSelectedTab() {
        return this.selectedTab;
    }

    public /* synthetic */ void lambda$onFinishInflate$0$BottomNavBar(View view) {
        if (this.selectedTab != 0) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.MAIN_SWITCH_TAB_TO_FAVORITE);
        }
        selectTab(0);
    }

    public /* synthetic */ void lambda$onFinishInflate$1$BottomNavBar(View view) {
        if (this.selectedTab != 1) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.MAIN_SWITCH_TAB_TO_CALL_LOG);
        }
        selectTab(1);
    }

    public /* synthetic */ void lambda$onFinishInflate$2$BottomNavBar(View view) {
        if (this.selectedTab != 2) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.MAIN_SWITCH_TAB_TO_CONTACTS);
        }
        selectTab(2);
    }

    public /* synthetic */ void lambda$onFinishInflate$3$BottomNavBar(View view) {
        if (this.selectedTab != 3) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.MAIN_SWITCH_TAB_TO_VOICEMAIL);
        }
        selectTab(3);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.speedDial = (BottomNavItem) findViewById(R.id.speed_dial_tab);
        this.callLog = (BottomNavItem) findViewById(R.id.call_log_tab);
        this.contacts = (BottomNavItem) findViewById(R.id.contacts_tab);
        this.voicemail = (BottomNavItem) findViewById(R.id.voicemail_tab);
        this.speedDial.setup(R.string.tab_title_speed_dial, R.drawable.quantum_ic_star_vd_theme_24);
        this.callLog.setup(R.string.tab_title_call_history, R.drawable.quantum_ic_access_time_vd_theme_24);
        this.contacts.setup(R.string.tab_title_contacts, R.drawable.quantum_ic_people_vd_theme_24);
        this.voicemail.setup(R.string.tab_title_voicemail, R.drawable.quantum_ic_voicemail_vd_theme_24);
        this.speedDial.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BottomNavBar.this.lambda$onFinishInflate$0$BottomNavBar(view);
            }
        });
        this.callLog.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BottomNavBar.this.lambda$onFinishInflate$1$BottomNavBar(view);
            }
        });
        this.contacts.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BottomNavBar.this.lambda$onFinishInflate$2$BottomNavBar(view);
            }
        });
        this.voicemail.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BottomNavBar.this.lambda$onFinishInflate$3$BottomNavBar(view);
            }
        });
    }

    public void selectTab(int i) {
        if (i == 0) {
            this.selectedTab = 0;
            setSelected(this.speedDial);
        } else if (i == 1) {
            this.selectedTab = 1;
            setSelected(this.callLog);
        } else if (i == 2) {
            this.selectedTab = 2;
            setSelected(this.contacts);
        } else if (i == 3) {
            this.selectedTab = 3;
            setSelected(this.voicemail);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid tab: ", i));
        }
        int i2 = this.selectedTab;
        for (OnBottomNavTabSelectedListener next : this.listeners) {
            if (i2 == 0) {
                next.onSpeedDialSelected();
            } else if (i2 == 1) {
                next.onCallLogSelected();
            } else if (i2 == 2) {
                next.onContactsSelected();
            } else if (i2 == 3) {
                next.onVoicemailSelected();
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid tab: ", i2));
            }
        }
    }

    public void setNotificationCount(int i, int i2) {
        if (i == 0) {
            this.speedDial.setNotificationCount(i2);
        } else if (i == 1) {
            this.callLog.setNotificationCount(i2);
        } else if (i == 2) {
            this.contacts.setNotificationCount(i2);
        } else if (i == 3) {
            this.voicemail.setNotificationCount(i2);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid tab: ", i));
        }
    }

    public void showVoicemail(boolean z) {
        LogUtil.m9i("OldMainActivityPeer.showVoicemail", "showing Tab:%b", Boolean.valueOf(z));
        int visibility = this.voicemail.getVisibility();
        this.voicemail.setVisibility(z ? 0 : 8);
        if (visibility != this.voicemail.getVisibility() && visibility == 0 && getSelectedTab() == 3) {
            LogUtil.m9i("OldMainActivityPeer.showVoicemail", "hid VM tab and moved to speed dial tab", new Object[0]);
            selectTab(0);
        }
    }
}
