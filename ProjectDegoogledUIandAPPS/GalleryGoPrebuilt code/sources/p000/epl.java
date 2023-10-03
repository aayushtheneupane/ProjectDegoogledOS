package p000;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.photosgo.R;

/* renamed from: epl */
/* compiled from: PG */
public final class epl {

    /* renamed from: a */
    private static final C0309lf f8783a = new C0309lf();

    /* renamed from: b */
    private static String m8002b(Context context) {
        String packageName = context.getPackageName();
        try {
            erb a = erc.m8049a(context);
            return a.f8865a.getPackageManager().getApplicationLabel(a.f8865a.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    /* renamed from: a */
    public static String m7998a(Context context) {
        return context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
    }

    /* renamed from: e */
    public static String m8006e(Context context, int i) {
        Resources resources = context.getResources();
        if (i == 1) {
            return resources.getString(R.string.common_google_play_services_install_button);
        }
        if (i == 2) {
            return resources.getString(R.string.common_google_play_services_update_button);
        }
        if (i != 3) {
            return resources.getString(17039370);
        }
        return resources.getString(R.string.common_google_play_services_enable_button);
    }

    /* renamed from: c */
    public static String m8004c(Context context, int i) {
        Resources resources = context.getResources();
        String b = m8002b(context);
        if (i == 1) {
            return resources.getString(R.string.common_google_play_services_install_text, new Object[]{b});
        } else if (i == 2) {
            esv.m8128b(context);
            return resources.getString(R.string.common_google_play_services_update_text, new Object[]{b});
        } else if (i == 3) {
            return resources.getString(R.string.common_google_play_services_enable_text, new Object[]{b});
        } else if (i == 5) {
            return m8001a(context, "common_google_play_services_invalid_account_text", b);
        } else {
            if (i == 7) {
                return m8001a(context, "common_google_play_services_network_error_text", b);
            }
            if (i == 9) {
                return resources.getString(R.string.common_google_play_services_unsupported_text, new Object[]{b});
            } else if (i == 20) {
                return m8001a(context, "common_google_play_services_restricted_profile_text", b);
            } else {
                switch (i) {
                    case 16:
                        return m8001a(context, "common_google_play_services_api_unavailable_text", b);
                    case 17:
                        return m8001a(context, "common_google_play_services_sign_in_failed_text", b);
                    case 18:
                        return resources.getString(R.string.common_google_play_services_updating_text, new Object[]{b});
                    default:
                        return resources.getString(R.string.common_google_play_services_unknown_issue, new Object[]{b});
                }
            }
        }
    }

    /* renamed from: d */
    public static String m8005d(Context context, int i) {
        if (i == 6 || i == 19) {
            return m8001a(context, "common_google_play_services_resolution_required_text", m8002b(context));
        }
        return m8004c(context, i);
    }

    /* renamed from: b */
    public static String m8003b(Context context, int i) {
        String str;
        if (i == 6) {
            str = m8000a(context, "common_google_play_services_resolution_required_title");
        } else {
            str = m7999a(context, i);
        }
        return str == null ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : str;
    }

    /* renamed from: a */
    public static String m7999a(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case RecyclerView.SCROLL_STATE_SETTLING:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return m8000a(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return m8000a(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return m8000a(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return m8000a(context, "common_google_play_services_restricted_profile_title");
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
        }
    }

    /* renamed from: a */
    private static String m8001a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String a = m8000a(context, str);
        if (a == null) {
            a = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, a, new Object[]{str2});
    }

    /* renamed from: a */
    private static String m8000a(Context context, String str) {
        Resources resources;
        synchronized (f8783a) {
            String str2 = (String) f8783a.get(str);
            if (str2 != null) {
                return str2;
            }
            int i = ekh.f8469a;
            try {
                resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
            } catch (PackageManager.NameNotFoundException e) {
                resources = null;
            }
            if (resources == null) {
                return null;
            }
            int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier != 0) {
                String string = resources.getString(identifier);
                if (!TextUtils.isEmpty(string)) {
                    f8783a.put(str, string);
                    return string;
                }
                Log.w("GoogleApiAvailability", str.length() == 0 ? new String("Got empty resource: ") : "Got empty resource: ".concat(str));
                return null;
            }
            Log.w("GoogleApiAvailability", str.length() == 0 ? new String("Missing resource: ") : "Missing resource: ".concat(str));
            return null;
        }
    }
}
