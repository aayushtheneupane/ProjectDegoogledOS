package com.android.systemui.p006qs.tiles;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.DetailAdapter;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.ScreenStabilizationTile */
public class ScreenStabilizationTile extends QSTileImpl<QSTile.BooleanState> {
    private final ScreenStabilizationDetailAdapter mDetailAdapter = ((ScreenStabilizationDetailAdapter) createDetailAdapter());
    private boolean mListening;
    /* access modifiers changed from: private */
    public ContentResolver mResolver = this.mContext.getContentResolver();
    /* access modifiers changed from: private */
    public final SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int id = seekBar.getId();
            if (id == C1777R$id.stabilization_velocity_friction_seekbar) {
                ScreenStabilizationTile.this.updateValuesFloat(seekBar.getProgress(), false);
            } else if (id == C1777R$id.stabilization_velocity_amplitude_seekbar) {
                ScreenStabilizationTile.this.updateValuesInt(seekBar.getProgress());
            } else if (id == C1777R$id.stabilization_position_friction_seekbar) {
                ScreenStabilizationTile.this.updateValuesFloat(seekBar.getProgress(), true);
            }
        }
    };

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    /* access modifiers changed from: protected */
    public void handleUserSwitch(int i) {
    }

    public boolean isAvailable() {
        return true;
    }

    public ScreenStabilizationTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public DetailAdapter getDetailAdapter() {
        return this.mDetailAdapter;
    }

    /* access modifiers changed from: protected */
    public DetailAdapter createDetailAdapter() {
        return new ScreenStabilizationDetailAdapter();
    }

    /* access modifiers changed from: protected */
    public void handleLongClick() {
        showDetail(true);
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        ContentResolver contentResolver = this.mResolver;
        int i = 0;
        if (Settings.System.getInt(contentResolver, "stabilization_enable", 0) != 1) {
            i = 1;
        }
        Settings.System.putInt(contentResolver, "stabilization_enable", i);
        refreshState();
    }

    /* access modifiers changed from: protected */
    public void handleSecondaryClick() {
        handleLongClick();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_stabilization_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z = false;
        if (Settings.System.getInt(this.mContext.getContentResolver(), "stabilization_enable", 0) == 1) {
            z = true;
        }
        booleanState.value = z;
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_stabilization_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_screen_stabilization);
        if (booleanState.value) {
            booleanState.state = 2;
        } else {
            booleanState.state = 1;
        }
        booleanState.contentDescription = booleanState.label;
    }

    public void handleSetListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
        }
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.quick_settings_stabilization_on);
        }
        return this.mContext.getString(C1784R$string.quick_settings_stabilization_off);
    }

    /* renamed from: com.android.systemui.qs.tiles.ScreenStabilizationTile$ScreenStabilizationDetailAdapter */
    private class ScreenStabilizationDetailAdapter implements DetailAdapter {
        private SeekBar mPositionFriction;
        private SeekBar mVelocityAmplitude;
        private SeekBar mVelocityFriction;

        public int getMetricsCategory() {
            return 268;
        }

        public Intent getSettingsIntent() {
            return null;
        }

        private ScreenStabilizationDetailAdapter() {
        }

        public CharSequence getTitle() {
            return ScreenStabilizationTile.this.mContext.getString(C1784R$string.quick_settings_stabilization_label);
        }

        public Boolean getToggleState() {
            return Boolean.valueOf(((QSTile.BooleanState) ScreenStabilizationTile.this.mState).value);
        }

        public void setToggleState(boolean z) {
            Settings.System.putIntForUser(ScreenStabilizationTile.this.mResolver, "stabilization_enable", z ? 1 : 0, -2);
            ScreenStabilizationTile.this.refreshState();
            if (!z) {
                ScreenStabilizationTile.this.showDetail(false);
            }
        }

        public View createDetailView(Context context, View view, ViewGroup viewGroup) {
            View inflate = view != null ? view : LayoutInflater.from(context).inflate(C1779R$layout.screen_stabilization_panel, viewGroup, false);
            if (view == null) {
                this.mVelocityFriction = (SeekBar) inflate.findViewById(C1777R$id.stabilization_velocity_friction_seekbar);
                this.mVelocityFriction.setOnSeekBarChangeListener(ScreenStabilizationTile.this.mSeekBarListener);
                this.mVelocityAmplitude = (SeekBar) inflate.findViewById(C1777R$id.stabilization_velocity_amplitude_seekbar);
                this.mVelocityAmplitude.setOnSeekBarChangeListener(ScreenStabilizationTile.this.mSeekBarListener);
                this.mPositionFriction = (SeekBar) inflate.findViewById(C1777R$id.stabilization_position_friction_seekbar);
                this.mPositionFriction.setOnSeekBarChangeListener(ScreenStabilizationTile.this.mSeekBarListener);
            }
            refreshFloat(Settings.System.getFloatForUser(ScreenStabilizationTile.this.mResolver, "stabilization_velocity_friction", 0.1f, -2), this.mVelocityFriction);
            refreshFloat(Settings.System.getFloatForUser(ScreenStabilizationTile.this.mResolver, "stabilization_position_friction", 0.1f, -2), this.mPositionFriction);
            refreshInt(Settings.System.getIntForUser(ScreenStabilizationTile.this.mResolver, "stabilization_velocity_amplitude", 8000, -2), this.mVelocityAmplitude);
            return inflate;
        }

        private void refreshFloat(float f, SeekBar seekBar) {
            if (f < 0.02f) {
                seekBar.setProgress(1);
            } else if (f < 0.06f) {
                seekBar.setProgress(2);
            } else if (f < 0.11f) {
                seekBar.setProgress(3);
            } else if (f < 0.21f) {
                seekBar.setProgress(4);
            } else if (f < 0.31f) {
                seekBar.setProgress(5);
            }
        }

        private void refreshInt(int i, SeekBar seekBar) {
            if (i == 4000) {
                seekBar.setProgress(1);
            } else if (i == 6000) {
                seekBar.setProgress(2);
            } else if (i == 8000) {
                seekBar.setProgress(3);
            } else if (i == 10000) {
                seekBar.setProgress(4);
            } else if (i == 12000) {
                seekBar.setProgress(5);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateValuesFloat(int i, boolean z) {
        String str = z ? "stabilization_position_friction" : "stabilization_velocity_friction";
        if (i == 1) {
            Settings.System.putFloatForUser(this.mResolver, str, 0.01f, -2);
        } else if (i == 2) {
            Settings.System.putFloatForUser(this.mResolver, str, 0.05f, -2);
        } else if (i == 3) {
            Settings.System.putFloatForUser(this.mResolver, str, 0.1f, -2);
        } else if (i == 4) {
            Settings.System.putFloatForUser(this.mResolver, str, 0.2f, -2);
        } else if (i == 5) {
            Settings.System.putFloatForUser(this.mResolver, str, 0.3f, -2);
        }
    }

    /* access modifiers changed from: private */
    public void updateValuesInt(int i) {
        if (i == 1) {
            Settings.System.putIntForUser(this.mResolver, "stabilization_velocity_amplitude", 4000, -2);
        } else if (i == 2) {
            Settings.System.putIntForUser(this.mResolver, "stabilization_velocity_amplitude", 6000, -2);
        } else if (i == 3) {
            Settings.System.putIntForUser(this.mResolver, "stabilization_velocity_amplitude", 8000, -2);
        } else if (i == 4) {
            Settings.System.putIntForUser(this.mResolver, "stabilization_velocity_amplitude", 10000, -2);
        } else if (i == 5) {
            Settings.System.putIntForUser(this.mResolver, "stabilization_velocity_amplitude", 12000, -2);
        }
    }
}
