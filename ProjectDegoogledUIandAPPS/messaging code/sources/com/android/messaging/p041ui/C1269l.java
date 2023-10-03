package com.android.messaging.p041ui;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.datamodel.p038b.C0849L;
import com.android.messaging.datamodel.p038b.C0877q;
import com.android.messaging.datamodel.p038b.C0880t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.ui.l */
public class C1269l {
    /* renamed from: a */
    public static View m3184a(LayoutInflater layoutInflater, MessagePartData messagePartData, ViewGroup viewGroup, int i, boolean z, C1063Q q) {
        VideoThumbnailView videoThumbnailView;
        int i2;
        int i3;
        String contentType = messagePartData.getContentType();
        int i4 = -1;
        int i5 = 0;
        if (messagePartData instanceof PendingAttachmentData) {
            PendingAttachmentData pendingAttachmentData = (PendingAttachmentData) messagePartData;
            View inflate = layoutInflater.inflate(R.layout.attachment_pending_item, viewGroup, false);
            ViewGroup.LayoutParams layoutParams = ((ImageView) inflate.findViewById(R.id.pending_item_view)).getLayoutParams();
            int dimensionPixelSize = layoutInflater.getContext().getResources().getDimensionPixelSize(R.dimen.pending_attachment_size);
            if (pendingAttachmentData.getWidth() == -1) {
                i3 = dimensionPixelSize;
            } else {
                i3 = pendingAttachmentData.getWidth();
            }
            layoutParams.width = i3;
            if (pendingAttachmentData.getHeight() != -1) {
                dimensionPixelSize = pendingAttachmentData.getHeight();
            }
            layoutParams.height = dimensionPixelSize;
            videoThumbnailView = inflate;
        } else {
            boolean z2 = true;
            if (C1481w.isImageType(contentType)) {
                int i6 = R.layout.attachment_single_image;
                if (i != 1) {
                    if (i == 2) {
                        i6 = R.layout.attachment_multiple_image;
                    } else if (i != 3) {
                        C1424b.fail("unsupported attachment view type!");
                    } else {
                        i6 = R.layout.attachment_chooser_image;
                    }
                }
                View inflate2 = layoutInflater.inflate(i6, viewGroup, false);
                AsyncImageView asyncImageView = (AsyncImageView) inflate2.findViewById(R.id.attachment_image_view);
                int maxWidth = asyncImageView.getMaxWidth();
                int maxHeight = asyncImageView.getMaxHeight();
                if (i == 3) {
                    i2 = layoutInflater.getContext().getResources().getDimensionPixelSize(R.dimen.attachment_grid_image_cell_size);
                    maxHeight = i2;
                } else {
                    i2 = maxWidth;
                }
                if (i2 <= 0 || i2 == Integer.MAX_VALUE) {
                    i2 = -1;
                }
                if (maxHeight > 0 && maxHeight != Integer.MAX_VALUE) {
                    i4 = maxHeight;
                }
                if (z) {
                    asyncImageView.mo6858a(m3185a(messagePartData, i2, i4));
                }
                asyncImageView.setContentDescription(viewGroup.getResources().getString(R.string.message_image_content_description));
                videoThumbnailView = inflate2;
            } else if (C1481w.m3831za(contentType)) {
                int i7 = R.layout.attachment_single_audio;
                if (i != 1) {
                    if (i == 2) {
                        i7 = R.layout.attachment_multiple_audio;
                    } else if (i != 3) {
                        C1424b.fail("unsupported attachment view type!");
                    } else {
                        i7 = R.layout.attachment_chooser_audio;
                    }
                }
                View inflate3 = layoutInflater.inflate(i7, viewGroup, false);
                ((AudioAttachmentView) inflate3.findViewById(R.id.audio_attachment_view)).mo6878a(messagePartData, false, false);
                videoThumbnailView = inflate3;
            } else if (C1481w.m3830Ea(contentType)) {
                int i8 = R.layout.attachment_single_video;
                if (i != 1) {
                    if (i == 2) {
                        i8 = R.layout.attachment_multiple_video;
                    } else if (i != 3) {
                        C1424b.fail("unsupported attachment view type!");
                    } else {
                        i8 = R.layout.attachment_chooser_video;
                    }
                }
                VideoThumbnailView videoThumbnailView2 = (VideoThumbnailView) layoutInflater.inflate(i8, viewGroup, false);
                videoThumbnailView2.mo7096b(messagePartData, false);
                videoThumbnailView = videoThumbnailView2;
            } else if (C1481w.m3829Da(contentType)) {
                int i9 = R.layout.attachment_single_vcard;
                if (i != 1) {
                    if (i == 2) {
                        i9 = R.layout.attachment_multiple_vcard;
                    } else if (i != 3) {
                        C1424b.fail("unsupported attachment view type!");
                    } else {
                        i9 = R.layout.attachment_chooser_vcard;
                    }
                }
                View inflate4 = layoutInflater.inflate(i9, viewGroup, false);
                PersonItemView personItemView = (PersonItemView) inflate4.findViewById(R.id.vcard_attachment_view);
                if (i == 1) {
                    z2 = false;
                }
                personItemView.mo7071q(z2);
                personItemView.mo7066d(C0947h.get().mo6600a(layoutInflater.getContext(), messagePartData));
                personItemView.mo7065a((C1117ca) new C1267k(personItemView));
                videoThumbnailView = inflate4;
            } else {
                C1424b.fail("unsupported attachment type: " + contentType);
                return null;
            }
        }
        TextView textView = (TextView) videoThumbnailView.findViewById(R.id.caption);
        if (textView != null) {
            String text = messagePartData.getText();
            if (TextUtils.isEmpty(text)) {
                i5 = 8;
            }
            textView.setVisibility(i5);
            textView.setText(text);
        }
        if (q != null) {
            videoThumbnailView.setOnClickListener(new C1263i(q, messagePartData));
            videoThumbnailView.setOnLongClickListener(new C1265j(q, messagePartData));
        }
        return videoThumbnailView;
    }

    /* renamed from: a */
    public static C0880t m3185a(MessagePartData messagePartData, int i, int i2) {
        Uri contentUri = messagePartData.getContentUri();
        String str = null;
        if (!C1481w.isImageType(messagePartData.getContentType())) {
            return null;
        }
        if (C1488za.m3876y(contentUri)) {
            str = contentUri.getPath();
        }
        String str2 = str;
        if (str2 != null) {
            return new C0877q(str2, i, i2, messagePartData.getWidth(), messagePartData.getHeight(), false, true, false);
        }
        return new C0849L(contentUri, i, i2, messagePartData.getWidth(), messagePartData.getHeight(), true, false, false, 0, 0);
    }
}
