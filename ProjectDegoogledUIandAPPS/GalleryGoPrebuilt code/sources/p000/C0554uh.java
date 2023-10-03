package p000;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* renamed from: uh */
/* compiled from: PG */
final class C0554uh {

    /* renamed from: a */
    public TextClassifier f15990a;

    /* renamed from: b */
    private final TextView f15991b;

    public C0554uh(TextView textView) {
        this.f15991b = (TextView) C0321lr.m14624a((Object) textView);
    }

    /* renamed from: a */
    public final TextClassifier mo10239a() {
        TextClassifier textClassifier = this.f15990a;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f15991b.getContext().getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            return textClassificationManager.getTextClassifier();
        }
        return TextClassifier.NO_OP;
    }
}
