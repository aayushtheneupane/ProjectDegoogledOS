package p000;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.infosheet.InfoView;

/* renamed from: ctb */
/* compiled from: PG */
public final class ctb {

    /* renamed from: a */
    public final ImageView f5617a;

    /* renamed from: b */
    public final InfoView f5618b;

    /* renamed from: c */
    private final hbl f5619c;

    /* renamed from: d */
    private final TextView f5620d;

    /* renamed from: e */
    private final TextView f5621e;

    /* renamed from: f */
    private final ImageView f5622f;

    public ctb(hbl hbl, InfoView infoView) {
        this.f5619c = hbl;
        this.f5618b = infoView;
        LayoutInflater.from(hbl).inflate(R.layout.infosheet_info_view_content, infoView, true);
        this.f5620d = (TextView) infoView.findViewById(R.id.info_title);
        this.f5621e = (TextView) infoView.findViewById(R.id.info_description);
        this.f5622f = (ImageView) infoView.findViewById(R.id.info_image);
        this.f5617a = (ImageView) infoView.findViewById(R.id.info_action_image);
    }

    /* renamed from: a */
    public final void mo3811a(int i) {
        InfoView infoView = this.f5618b;
        StringBuilder sb = new StringBuilder(this.f5619c.getString(i));
        sb.append(this.f5620d.getText());
        sb.append("\n");
        sb.append(this.f5621e.getText());
        infoView.setContentDescription(sb);
    }

    /* renamed from: a */
    public final void mo3812a(String str) {
        this.f5621e.setText(str);
    }

    /* renamed from: b */
    public final void mo3813b(int i) {
        this.f5622f.setImageResource(i);
    }

    /* renamed from: b */
    public final void mo3814b(String str) {
        this.f5620d.setText(str);
    }
}
