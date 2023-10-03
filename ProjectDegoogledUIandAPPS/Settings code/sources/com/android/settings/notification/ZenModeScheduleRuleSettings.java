package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.service.notification.ZenModeConfig;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ZenModeScheduleRuleSettings extends ZenModeRuleSettingsBase {
    private AlertDialog mDayDialog;
    private final SimpleDateFormat mDayFormat = new SimpleDateFormat("EEE");
    private Preference mDays;
    private TimePickerPreference mEnd;
    private SwitchPreference mExitAtAlarm;
    /* access modifiers changed from: private */
    public ZenModeConfig.ScheduleInfo mSchedule;
    private TimePickerPreference mStart;

    public int getMetricsCategory() {
        return 144;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_schedule_rule_settings;
    }

    /* access modifiers changed from: protected */
    public boolean setRule(AutomaticZenRule automaticZenRule) {
        this.mSchedule = automaticZenRule != null ? ZenModeConfig.tryParseScheduleConditionId(automaticZenRule.getConditionId()) : null;
        return this.mSchedule != null;
    }

    /* access modifiers changed from: protected */
    public void onCreateInternal() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mDays = preferenceScreen.findPreference("days");
        this.mDays.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ZenModeScheduleRuleSettings.this.showDaysDialog();
                return true;
            }
        });
        FragmentManager fragmentManager = getFragmentManager();
        this.mStart = new TimePickerPreference(getPrefContext(), fragmentManager);
        this.mStart.setKey("start_time");
        this.mStart.setTitle((int) C1715R.string.zen_mode_start_time);
        this.mStart.setCallback(new TimePickerPreference.Callback() {
            public boolean onSetTime(int i, int i2) {
                if (ZenModeScheduleRuleSettings.this.mDisableListeners) {
                    return true;
                }
                if (!ZenModeConfig.isValidHour(i) || !ZenModeConfig.isValidMinute(i2)) {
                    return false;
                }
                if (i == ZenModeScheduleRuleSettings.this.mSchedule.startHour && i2 == ZenModeScheduleRuleSettings.this.mSchedule.startMinute) {
                    return true;
                }
                if (ZenModeRuleSettingsBase.DEBUG) {
                    Log.d("ZenModeSettings", "onPrefChange start h=" + i + " m=" + i2);
                }
                ZenModeScheduleRuleSettings.this.mSchedule.startHour = i;
                ZenModeScheduleRuleSettings.this.mSchedule.startMinute = i2;
                ZenModeScheduleRuleSettings zenModeScheduleRuleSettings = ZenModeScheduleRuleSettings.this;
                zenModeScheduleRuleSettings.updateRule(ZenModeConfig.toScheduleConditionId(zenModeScheduleRuleSettings.mSchedule));
                return true;
            }
        });
        preferenceScreen.addPreference(this.mStart);
        this.mStart.setDependency(this.mDays.getKey());
        this.mEnd = new TimePickerPreference(getPrefContext(), fragmentManager);
        this.mEnd.setKey("end_time");
        this.mEnd.setTitle((int) C1715R.string.zen_mode_end_time);
        this.mEnd.setCallback(new TimePickerPreference.Callback() {
            public boolean onSetTime(int i, int i2) {
                if (ZenModeScheduleRuleSettings.this.mDisableListeners) {
                    return true;
                }
                if (!ZenModeConfig.isValidHour(i) || !ZenModeConfig.isValidMinute(i2)) {
                    return false;
                }
                if (i == ZenModeScheduleRuleSettings.this.mSchedule.endHour && i2 == ZenModeScheduleRuleSettings.this.mSchedule.endMinute) {
                    return true;
                }
                if (ZenModeRuleSettingsBase.DEBUG) {
                    Log.d("ZenModeSettings", "onPrefChange end h=" + i + " m=" + i2);
                }
                ZenModeScheduleRuleSettings.this.mSchedule.endHour = i;
                ZenModeScheduleRuleSettings.this.mSchedule.endMinute = i2;
                ZenModeScheduleRuleSettings zenModeScheduleRuleSettings = ZenModeScheduleRuleSettings.this;
                zenModeScheduleRuleSettings.updateRule(ZenModeConfig.toScheduleConditionId(zenModeScheduleRuleSettings.mSchedule));
                return true;
            }
        });
        preferenceScreen.addPreference(this.mEnd);
        this.mEnd.setDependency(this.mDays.getKey());
        this.mExitAtAlarm = (SwitchPreference) preferenceScreen.findPreference("exit_at_alarm");
        this.mExitAtAlarm.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                ZenModeScheduleRuleSettings.this.mSchedule.exitAtAlarm = ((Boolean) obj).booleanValue();
                ZenModeScheduleRuleSettings zenModeScheduleRuleSettings = ZenModeScheduleRuleSettings.this;
                zenModeScheduleRuleSettings.updateRule(ZenModeConfig.toScheduleConditionId(zenModeScheduleRuleSettings.mSchedule));
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateDays() {
        int[] iArr = this.mSchedule.days;
        if (iArr != null && iArr.length > 0) {
            StringBuilder sb = new StringBuilder();
            Calendar instance = Calendar.getInstance();
            int[] daysOfWeekForLocale = ZenModeScheduleDaysSelection.getDaysOfWeekForLocale(instance);
            for (int i : daysOfWeekForLocale) {
                int i2 = 0;
                while (true) {
                    if (i2 >= iArr.length) {
                        break;
                    } else if (i == iArr[i2]) {
                        instance.set(7, i);
                        if (sb.length() > 0) {
                            sb.append(this.mContext.getString(C1715R.string.summary_divider_text));
                        }
                        sb.append(this.mDayFormat.format(instance.getTime()));
                    } else {
                        i2++;
                    }
                }
            }
            if (sb.length() > 0) {
                this.mDays.setSummary((CharSequence) sb);
                this.mDays.notifyDependencyChange(false);
                return;
            }
        }
        this.mDays.setSummary((int) C1715R.string.zen_mode_schedule_rule_days_none);
        this.mDays.notifyDependencyChange(true);
    }

    private void updateEndSummary() {
        ZenModeConfig.ScheduleInfo scheduleInfo = this.mSchedule;
        int i = (scheduleInfo.startHour * 60) + scheduleInfo.startMinute;
        int i2 = (scheduleInfo.endHour * 60) + scheduleInfo.endMinute;
        int i3 = 0;
        if (i >= i2) {
            i3 = C1715R.string.zen_mode_end_time_next_day_summary_format;
        }
        this.mEnd.setSummaryFormat(i3);
    }

    /* access modifiers changed from: protected */
    public void updateControlsInternal() {
        updateDays();
        TimePickerPreference timePickerPreference = this.mStart;
        ZenModeConfig.ScheduleInfo scheduleInfo = this.mSchedule;
        timePickerPreference.setTime(scheduleInfo.startHour, scheduleInfo.startMinute);
        TimePickerPreference timePickerPreference2 = this.mEnd;
        ZenModeConfig.ScheduleInfo scheduleInfo2 = this.mSchedule;
        timePickerPreference2.setTime(scheduleInfo2.endHour, scheduleInfo2.endMinute);
        this.mExitAtAlarm.setChecked(this.mSchedule.exitAtAlarm);
        updateEndSummary();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mHeader = new ZenAutomaticRuleHeaderPreferenceController(context, this, getSettingsLifecycle());
        this.mActionButtons = new ZenRuleButtonsPreferenceController(context, this, getSettingsLifecycle());
        this.mSwitch = new ZenAutomaticRuleSwitchPreferenceController(context, this, getSettingsLifecycle());
        arrayList.add(this.mHeader);
        arrayList.add(this.mActionButtons);
        arrayList.add(this.mSwitch);
        return arrayList;
    }

    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mDayDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDayDialog.dismiss();
            this.mDayDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public void showDaysDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle((int) C1715R.string.zen_mode_schedule_rule_days);
        builder.setView((View) new ZenModeScheduleDaysSelection(this.mContext, this.mSchedule.days) {
            /* access modifiers changed from: protected */
            public void onChanged(int[] iArr) {
                ZenModeScheduleRuleSettings zenModeScheduleRuleSettings = ZenModeScheduleRuleSettings.this;
                if (!zenModeScheduleRuleSettings.mDisableListeners && !Arrays.equals(iArr, zenModeScheduleRuleSettings.mSchedule.days)) {
                    if (ZenModeRuleSettingsBase.DEBUG) {
                        Log.d("ZenModeSettings", "days.onChanged days=" + Arrays.asList(new int[][]{iArr}));
                    }
                    ZenModeScheduleRuleSettings.this.mSchedule.days = iArr;
                    ZenModeScheduleRuleSettings zenModeScheduleRuleSettings2 = ZenModeScheduleRuleSettings.this;
                    zenModeScheduleRuleSettings2.updateRule(ZenModeConfig.toScheduleConditionId(zenModeScheduleRuleSettings2.mSchedule));
                }
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                ZenModeScheduleRuleSettings.this.updateDays();
            }
        });
        builder.setPositiveButton((int) C1715R.string.done_button, (DialogInterface.OnClickListener) null);
        this.mDayDialog = builder.show();
    }

    private static class TimePickerPreference extends Preference {
        private Callback mCallback;
        private final Context mContext;
        /* access modifiers changed from: private */
        public int mHourOfDay;
        /* access modifiers changed from: private */
        public int mMinute;
        private int mSummaryFormat;

        public interface Callback {
            boolean onSetTime(int i, int i2);
        }

        public TimePickerPreference(Context context, final FragmentManager fragmentManager) {
            super(context);
            this.mContext = context;
            setPersistent(false);
            setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    TimePickerFragment timePickerFragment = new TimePickerFragment();
                    timePickerFragment.pref = TimePickerPreference.this;
                    timePickerFragment.show(fragmentManager, TimePickerPreference.class.getName());
                    return true;
                }
            });
        }

        public void setCallback(Callback callback) {
            this.mCallback = callback;
        }

        public void setSummaryFormat(int i) {
            this.mSummaryFormat = i;
            updateSummary();
        }

        public void setTime(int i, int i2) {
            Callback callback = this.mCallback;
            if (callback == null || callback.onSetTime(i, i2)) {
                this.mHourOfDay = i;
                this.mMinute = i2;
                updateSummary();
            }
        }

        private void updateSummary() {
            Calendar instance = Calendar.getInstance();
            instance.set(11, this.mHourOfDay);
            instance.set(12, this.mMinute);
            String format = DateFormat.getTimeFormat(this.mContext).format(instance.getTime());
            if (this.mSummaryFormat != 0) {
                format = this.mContext.getResources().getString(this.mSummaryFormat, new Object[]{format});
            }
            setSummary((CharSequence) format);
        }

        public static class TimePickerFragment extends InstrumentedDialogFragment implements TimePickerDialog.OnTimeSetListener {
            public TimePickerPreference pref;

            public int getMetricsCategory() {
                return 556;
            }

            public Dialog onCreateDialog(Bundle bundle) {
                TimePickerPreference timePickerPreference = this.pref;
                boolean z = timePickerPreference != null && timePickerPreference.mHourOfDay >= 0 && this.pref.mMinute >= 0;
                Calendar instance = Calendar.getInstance();
                return new TimePickerDialog(getActivity(), this, z ? this.pref.mHourOfDay : instance.get(11), z ? this.pref.mMinute : instance.get(12), DateFormat.is24HourFormat(getActivity()));
            }

            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                TimePickerPreference timePickerPreference = this.pref;
                if (timePickerPreference != null) {
                    timePickerPreference.setTime(i, i2);
                }
            }
        }
    }
}
