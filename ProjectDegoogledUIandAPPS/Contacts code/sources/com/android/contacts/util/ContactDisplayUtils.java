package com.android.contacts.util;

import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Patterns;
import com.android.contacts.R;
import com.android.contacts.compat.PhoneNumberUtilsCompat;
import com.android.contacts.preference.ContactsPreferences;

public class ContactDisplayUtils {
    private static final String TAG = "ContactDisplayUtils";

    public static boolean isCustomPhoneType(Integer num) {
        return num.intValue() == 0 || num.intValue() == 19;
    }

    public static int getPhoneLabelResourceId(Integer num) {
        if (num == null) {
            return R.string.call_other;
        }
        switch (num.intValue()) {
            case 1:
                return R.string.call_home;
            case 2:
                return R.string.call_mobile;
            case 3:
                return R.string.call_work;
            case 4:
                return R.string.call_fax_work;
            case 5:
                return R.string.call_fax_home;
            case 6:
                return R.string.call_pager;
            case 7:
                return R.string.call_other;
            case 8:
                return R.string.call_callback;
            case 9:
                return R.string.call_car;
            case 10:
                return R.string.call_company_main;
            case 11:
                return R.string.call_isdn;
            case 12:
                return R.string.call_main;
            case 13:
                return R.string.call_other_fax;
            case 14:
                return R.string.call_radio;
            case 15:
                return R.string.call_telex;
            case 16:
                return R.string.call_tty_tdd;
            case 17:
                return R.string.call_work_mobile;
            case 18:
                return R.string.call_work_pager;
            case 19:
                return R.string.call_assistant;
            case 20:
                return R.string.call_mms;
            default:
                return R.string.call_custom;
        }
    }

    public static int getSmsLabelResourceId(Integer num) {
        if (num == null) {
            return R.string.sms_other;
        }
        switch (num.intValue()) {
            case 1:
                return R.string.sms_home;
            case 2:
                return R.string.sms_mobile;
            case 3:
                return R.string.sms_work;
            case 4:
                return R.string.sms_fax_work;
            case 5:
                return R.string.sms_fax_home;
            case 6:
                return R.string.sms_pager;
            case 7:
                return R.string.sms_other;
            case 8:
                return R.string.sms_callback;
            case 9:
                return R.string.sms_car;
            case 10:
                return R.string.sms_company_main;
            case 11:
                return R.string.sms_isdn;
            case 12:
                return R.string.sms_main;
            case 13:
                return R.string.sms_other_fax;
            case 14:
                return R.string.sms_radio;
            case 15:
                return R.string.sms_telex;
            case 16:
                return R.string.sms_tty_tdd;
            case 17:
                return R.string.sms_work_mobile;
            case 18:
                return R.string.sms_work_pager;
            case 19:
                return R.string.sms_assistant;
            case 20:
                return R.string.sms_mms;
            default:
                return R.string.sms_custom;
        }
    }

    public static boolean isPossiblePhoneNumber(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        return Patterns.PHONE.matcher(charSequence.toString()).matches();
    }

    public static Spannable getTelephoneTtsSpannable(String str, String str2) {
        int i;
        if (str == null) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        if (str2 == null) {
            i = -1;
        } else {
            i = str.indexOf(str2);
        }
        while (i >= 0) {
            int length = str2.length() + i;
            spannableString.setSpan(PhoneNumberUtilsCompat.createTtsSpan(str2), i, length, 33);
            i = str.indexOf(str2, length);
        }
        return spannableString;
    }

    public static CharSequence getTtsSpannedPhoneNumber(Resources resources, int i, String str) {
        return getTelephoneTtsSpannable(resources.getString(i, new Object[]{str}), str);
    }

    public static String getPreferredDisplayName(String str, String str2, ContactsPreferences contactsPreferences) {
        return contactsPreferences == null ? str != null ? str : str2 : (contactsPreferences.getDisplayOrder() != 1 && contactsPreferences.getDisplayOrder() == 2 && !TextUtils.isEmpty(str2)) ? str2 : str;
    }

    public static String getPreferredSortName(String str, String str2, ContactsPreferences contactsPreferences) {
        return contactsPreferences == null ? str != null ? str : str2 : (contactsPreferences.getSortOrder() != 1 && contactsPreferences.getSortOrder() == 2 && !TextUtils.isEmpty(str2)) ? str2 : str;
    }
}
