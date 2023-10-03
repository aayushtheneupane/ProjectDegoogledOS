package p000;

/* renamed from: ipv */
/* compiled from: PG */
public final class ipv implements ipu {

    /* renamed from: a */
    private static final fqg f14634a;

    static {
        fqf fqf = new fqf(fqb.m9396a("com.google.android.gms.feedback"));
        if (fqf.f10245b.isEmpty()) {
            fqf fqf2 = new fqf(fqf.f10244a, fqf.f10245b, fqf.f10246c, true);
            fqg.m9401a(fqf2, "AndroidFeedback__fix_suggested_article_crash", false);
            fqg.m9401a(fqf2, "AndroidFeedback__select_last_chosen_account_in_email_spinner", false);
            f14634a = fqg.m9401a(fqf2, "AndroidFeedback__throw_on_set_screenshot_but_no_pii_allowed", true);
            fqg.m9401a(fqf2, "AndroidFeedback__trim_feedback_submission", false);
            return;
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    /* renamed from: a */
    public final boolean mo9059a() {
        return ((Boolean) f14634a.mo6028c()).booleanValue();
    }
}
