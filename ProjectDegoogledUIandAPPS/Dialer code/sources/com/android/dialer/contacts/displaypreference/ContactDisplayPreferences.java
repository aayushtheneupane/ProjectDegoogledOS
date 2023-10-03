package com.android.dialer.contacts.displaypreference;

import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import java.util.Arrays;
import java.util.function.Predicate;

public interface ContactDisplayPreferences {

    public enum DisplayOrder implements StringResEnum {
        PRIMARY(R.string.display_options_view_given_name_first_value),
        ALTERNATIVE(R.string.display_options_view_family_name_first_value);
        
        private final int value;

        private DisplayOrder(int i) {
            this.value = i;
        }

        public int getStringRes() {
            return this.value;
        }
    }

    public enum SortOrder implements StringResEnum {
        BY_PRIMARY(R.string.display_options_sort_by_given_name_value),
        BY_ALTERNATIVE(R.string.display_options_sort_by_family_name_value);
        
        private final int value;

        private SortOrder(int i) {
            this.value = i;
        }

        public int getStringRes() {
            return this.value;
        }
    }

    public interface StringResEnum {
        static <T extends Enum<T> & StringResEnum> T fromValue(Context context, T[] tArr, String str) {
            return (Enum) Arrays.stream(tArr).filter(new Predicate(context, str) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ String f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final boolean test(Object obj) {
                    return TextUtils.equals(((ContactDisplayPreferences.StringResEnum) ((Enum) obj)).getValue(this.f$0), this.f$1);
                }
            }).reduce(C0464xc78a03ca.INSTANCE).get();
        }

        static /* synthetic */ Enum lambda$fromValue$1(Enum enumR, Enum enumR2) {
            throw new AssertionError("multiple result");
        }

        int getStringRes();

        String getValue(Context context) {
            return context.getString(getStringRes());
        }
    }

    String getDisplayName(String str, String str2) {
        int ordinal;
        if (TextUtils.isEmpty(str2) || (ordinal = getDisplayOrder().ordinal()) == 0) {
            return str;
        }
        if (ordinal == 1) {
            return str2;
        }
        throw new AssertionError("exhaustive switch");
    }

    DisplayOrder getDisplayOrder();

    String getSortName(String str, String str2) {
        int ordinal;
        if (TextUtils.isEmpty(str2) || (ordinal = getSortOrder().ordinal()) == 0) {
            return str;
        }
        if (ordinal == 1) {
            return str2;
        }
        throw new AssertionError("exhaustive switch");
    }

    SortOrder getSortOrder();
}
