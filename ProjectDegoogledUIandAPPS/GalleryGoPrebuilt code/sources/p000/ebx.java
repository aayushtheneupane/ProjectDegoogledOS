package p000;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.settings.LinkableSwitchPreference;
import com.google.android.libraries.social.licenses.LicenseMenuActivity;
import java.util.Locale;

/* renamed from: ebx */
/* compiled from: PG */
final class ebx implements gvc {

    /* renamed from: a */
    private final /* synthetic */ eby f7872a;

    public ebx(eby eby) {
        this.f7872a = eby;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5511a(th, "SettingsFragmentPeer: Failed to fetch app settings.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        ada ada;
        boolean z;
        ebj ebj = (ebj) obj;
        eby eby = this.f7872a;
        adv adv = eby.f7878d.f218b;
        PreferenceScreen a = adv.mo229a(adv.f235a);
        eby.f7878d.mo206a(a);
        a.mo1198q();
        PreferenceCategory preferenceCategory = new PreferenceCategory(a.f1111j);
        preferenceCategory.mo1198q();
        preferenceCategory.mo1182c("gallery_settings");
        preferenceCategory.mo1176b((int) R.string.gallery_settings_title);
        preferenceCategory.mo1197p();
        a.mo1200a((Preference) preferenceCategory);
        if (eby.f7883i.mo3175a()) {
            ife.m12876b(eby.f7890p.isPresent(), (Object) "Hide folders activity must exist when the feature is enabled");
            Context context = preferenceCategory.f1111j;
            Preference preference = new Preference(context);
            preference.mo1198q();
            preference.mo1182c("hide_folders");
            preference.mo1181c((int) R.drawable.quantum_gm_ic_folder_open_vd_theme_24);
            preference.mo1176b((int) R.string.hide_folders_title);
            preference.mo1186d((int) R.string.hide_folders_subtitle);
            preference.f1120s = new Intent(context, (Class) eby.f7890p.get());
            preferenceCategory.mo1200a(preference);
        }
        if (eby.f7884j.mo3175a()) {
            ife.m12876b(eby.f7891q.isPresent(), (Object) "Compression activity must exist when the compression feature is enabled");
            Context context2 = preferenceCategory.f1111j;
            Preference preference2 = new Preference(context2);
            preference2.mo1198q();
            preference2.mo1182c("compression");
            preference2.mo1181c((int) R.drawable.quantum_gm_ic_compare_vd_theme_24);
            preference2.mo1176b((int) R.string.compression_title);
            preference2.mo1186d((int) R.string.compression_subtitle);
            preference2.f1120s = new Intent(context2, (Class) eby.f7891q.get());
            preferenceCategory.mo1200a(preference2);
        }
        if ((ebj.f7849a & 1) != 0) {
            cjm cjm = cjm.UNKNOWN;
            cjm a2 = cjm.m4399a(ebj.f7850b);
            if (a2 == null) {
                a2 = cjm.UNKNOWN;
            }
            int ordinal = a2.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    z = true;
                } else if (!(ordinal == 3 || ordinal == 4)) {
                    z = false;
                }
                Context context3 = preferenceCategory.f1111j;
                eby.f7897w = new LinkableSwitchPreference(context3);
                eby.f7897w.mo1198q();
                eby.f7897w.mo1182c("group_similar_faces");
                eby.f7897w.mo1181c((int) R.drawable.quantum_gm_ic_people_vd_theme_24);
                eby.f7897w.mo1176b((int) R.string.group_similar_faces_title);
                eby.f7897w.mo1164a((CharSequence) Html.fromHtml(context3.getString(R.string.group_similar_faces_subtitle, new Object[]{eby.f7893s}), 63));
                eby.f7897w.mo1212d(z);
                eby.f7897w.f1115n = eby.f7889o.mo7618a((acz) new ebr(eby), "face grouping availability change");
                preferenceCategory.mo1200a((Preference) eby.f7897w);
            }
        }
        SwitchPreference switchPreference = new SwitchPreference(preferenceCategory.f1111j);
        switchPreference.mo1198q();
        switchPreference.mo1182c("dark_mode");
        switchPreference.mo1176b((int) R.string.dark_mode_title);
        switchPreference.mo1181c((int) R.drawable.quantum_gm_ic_brightness_4_vd_theme_24);
        switchPreference.mo1210c(switchPreference.f1111j.getString(R.string.dark_mode_always));
        switchPreference.mo1211d((CharSequence) switchPreference.f1111j.getString(Build.VERSION.SDK_INT < 29 ? R.string.dark_mode_off : R.string.dark_mode_auto));
        switchPreference.mo1212d(C0397om.f15415a == 2);
        switchPreference.f1115n = eby.f7889o.mo7618a((acz) new ebw(eby), "DarkModeSettingPreferenceChange");
        preferenceCategory.mo1200a((Preference) switchPreference);
        if (preferenceCategory.mo1203g() == 0 && preferenceCategory.f1124w) {
            preferenceCategory.f1124w = false;
            acy acy = preferenceCategory.f1088A;
            if (acy != null) {
                ((adr) acy).mo183b();
            }
        }
        Intent launchIntentForPackage = eby.f7878d.mo2634k().getPackageManager().getLaunchIntentForPackage("com.google.android.apps.photos");
        if (eby.f7881g.mo3175a() && launchIntentForPackage == null) {
            PreferenceCategory preferenceCategory2 = new PreferenceCategory(a.f1111j);
            preferenceCategory2.mo1198q();
            preferenceCategory2.mo1182c("cloud_storage");
            preferenceCategory2.mo1176b((int) R.string.cloud_storage_title);
            preferenceCategory2.mo1197p();
            a.mo1200a((Preference) preferenceCategory2);
            Context context4 = preferenceCategory2.f1111j;
            Preference preference3 = new Preference(context4);
            preference3.mo1198q();
            preference3.mo1182c("promo");
            preference3.mo1176b((int) R.string.promo_title);
            preference3.mo1186d((int) R.string.promo_summary);
            preference3.f1127z = R.layout.exit_to_app;
            preference3.mo1181c((int) R.drawable.product_logo_photos_color_24);
            preference3.f1116o = new ebt(eby, context4);
            preferenceCategory2.mo1200a(preference3);
        }
        PreferenceCategory preferenceCategory3 = new PreferenceCategory(a.f1111j);
        preferenceCategory3.mo1198q();
        preferenceCategory3.mo1182c("other_category");
        preferenceCategory3.mo1176b((int) R.string.other_category_title);
        preferenceCategory3.mo1197p();
        a.mo1200a((Preference) preferenceCategory3);
        Context context5 = a.f1111j;
        Preference preference4 = new Preference(context5);
        preference4.mo1198q();
        preference4.mo1182c("build_number");
        preference4.mo1176b((int) R.string.version_number_title);
        preference4.mo1181c((int) R.drawable.quantum_gm_ic_info_vd_theme_24);
        try {
            PackageInfo packageInfo = context5.getPackageManager().getPackageInfo(context5.getPackageName(), 0);
            preference4.mo1164a((CharSequence) String.format(Locale.US, "%s (%d#%s)", new Object[]{packageInfo.versionName, Integer.valueOf(packageInfo.versionCode), eby.f7873A}));
        } catch (PackageManager.NameNotFoundException e) {
            cwn.m5515b((Throwable) e, "AboutFragment: Failed to get our own package information.", new Object[0]);
        }
        a.mo1200a(preference4);
        Preference preference5 = new Preference(a.f1111j);
        preference5.mo1198q();
        preference5.mo1182c("licenses");
        preference5.mo1176b((int) R.string.licenses_title);
        preference5.f1120s = new Intent(preference5.f1111j, LicenseMenuActivity.class);
        preference5.mo1181c((int) R.drawable.quantum_gm_ic_insert_drive_file_vd_theme_24);
        a.mo1200a(preference5);
        Preference preference6 = new Preference(a.f1111j);
        preference6.mo1198q();
        preference6.mo1182c("privacy_policy");
        preference6.mo1176b((int) R.string.privacy_policy_title);
        preference6.f1120s = new Intent("android.intent.action.VIEW", eby.f7875a);
        preference6.mo1181c((int) R.drawable.quantum_gm_ic_insert_drive_file_vd_theme_24);
        a.mo1200a(preference6);
        Preference preference7 = new Preference(a.f1111j);
        preference7.mo1198q();
        preference7.mo1182c("tos");
        preference7.mo1176b((int) R.string.terms_of_service_title);
        preference7.f1120s = new Intent("android.intent.action.VIEW", eby.f7876b);
        preference7.mo1181c((int) R.drawable.quantum_gm_ic_insert_drive_file_vd_theme_24);
        a.mo1200a(preference7);
        Preference preference8 = new Preference(preferenceCategory3.f1111j);
        preference8.mo1198q();
        preference8.mo1182c("help_and_feedback");
        heu heu = new heu((byte[]) null);
        heu.mo7340a("android_default");
        heu.mo7340a(eby.f7877c);
        Uri parse = Uri.parse("https://support.google.com");
        if (parse != null) {
            heu.f12620b = parse;
            if (heu.f12621c == null) {
                heu.f12621c = hso.m12047f();
            }
            String str = "";
            String str2 = heu.f12619a == null ? " helpCenterContext" : str;
            if (heu.f12620b == null) {
                str2 = str2.concat(" fallbackSupportUri");
            }
            if (!str2.isEmpty()) {
                String valueOf = String.valueOf(str2);
                throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
            }
            heb heb = new heb(heu.f12619a, heu.f12620b, heu.f12621c);
            hec hec = new hec((byte[]) null);
            hec.mo7336a(true);
            hec.f12571a = "com.google.android.apps.photosgo.USER_INITIATED_FEEDBACK_REPORT";
            hec.mo7336a(false);
            if (hec.f12572b == null) {
                hec.f12572b = hvb.f13454a;
            }
            if (hec.f12571a == null) {
                str = " categoryTag";
            }
            if (hec.f12573c == null) {
                str = str.concat(" includeScreenshot");
            }
            if (!str.isEmpty()) {
                String valueOf2 = String.valueOf(str);
                throw new IllegalStateException(valueOf2.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf2));
            }
            hea hea = new hea(hec.f12571a, hec.f12572b, hec.f12573c.booleanValue());
            if (!eby.f7882h.mo3175a()) {
                preference8.mo1176b((int) R.string.feedback_label);
                preference8.mo1181c((int) R.drawable.quantum_gm_ic_feedback_vd_theme_24);
                ada = eby.f7889o.mo7619a((ada) new ebu(eby, hea), "Feedback");
            } else {
                preference8.mo1176b((int) R.string.help_and_feedback_label);
                preference8.mo1181c((int) R.drawable.quantum_gm_ic_help_outline_vd_theme_24);
                ada = eby.f7889o.mo7619a((ada) new ebv(eby, heb, hea), "Help and Feedback");
            }
            preference8.f1116o = ada;
            preferenceCategory3.mo1200a(preference8);
            if (eby.f7880f.isPresent()) {
                PreferenceCategory preferenceCategory4 = new PreferenceCategory(a.f1111j);
                preferenceCategory4.mo1178b((CharSequence) "Developer settings");
                String valueOf3 = String.valueOf(preferenceCategory4.f1118q);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 4);
                sb.append(valueOf3);
                sb.append(" key");
                preferenceCategory4.mo1182c(sb.toString());
                preferenceCategory4.mo1198q();
                preferenceCategory4.mo1197p();
                a.mo1200a((Preference) preferenceCategory4);
                hvs i = ((hso) eby.f7880f.get()).listIterator();
                while (i.hasNext()) {
                    ecn ecn = (ecn) i.next();
                    Preference preference9 = new Preference(a.f1111j);
                    preference9.mo1198q();
                    preference9.mo1182c(ecn.mo4677a());
                    preference9.mo1178b((CharSequence) ecn.mo4677a());
                    preference9.mo1164a((CharSequence) ecn.mo4678b());
                    preference9.f1116o = ecn.mo4679c();
                    preference9.mo1197p();
                    a.mo1200a(preference9);
                }
                return;
            }
            return;
        }
        throw new NullPointerException("Null fallbackSupportUri");
    }
}
