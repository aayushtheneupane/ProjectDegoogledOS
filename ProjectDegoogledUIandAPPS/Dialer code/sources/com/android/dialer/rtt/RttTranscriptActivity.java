package com.android.dialer.rtt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.MenuItem;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.protos.ProtoParsers;
import com.android.dialer.widget.DialerToolbar;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Callable;

public class RttTranscriptActivity extends AppCompatActivity {
    private RttTranscriptAdapter adapter;
    private UiListener<RttTranscript> rttTranscriptUiListener;
    private DialerToolbar toolbar;

    public static Intent getIntent(Context context, String str, String str2, PhotoInfo photoInfo) {
        Intent intent = new Intent(context, RttTranscriptActivity.class);
        intent.putExtra("extra_transcript_id", str);
        intent.putExtra("extra_primary_text", str2);
        Assert.isNotNull(photoInfo);
        ProtoParsers.put(intent, "extra_photo_info", photoInfo);
        return intent;
    }

    private void handleIntent(Intent intent) {
        Assert.checkArgument(intent.hasExtra("extra_transcript_id"));
        Assert.checkArgument(intent.hasExtra("extra_primary_text"));
        Assert.checkArgument(intent.hasExtra("extra_photo_info"));
        String stringExtra = intent.getStringExtra("extra_transcript_id");
        UiListener<RttTranscript> uiListener = this.rttTranscriptUiListener;
        ListenableFuture submit = DialerExecutorComponent.get(this).lightweightExecutor().submit(new Callable(this, stringExtra) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object call() {
                return RttTranscriptUtil.lambda$loadRttTranscript$1(this.f$0, this.f$1);
            }
        });
        RttTranscriptAdapter rttTranscriptAdapter = this.adapter;
        Objects.requireNonNull(rttTranscriptAdapter);
        uiListener.listen(this, submit, new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                RttTranscriptAdapter.this.setRttTranscript((RttTranscript) obj);
            }
        }, $$Lambda$RttTranscriptActivity$AyNB8U5mPMcdSZHtZqDmjjg6sEw.INSTANCE);
        this.toolbar.setTitle((CharSequence) intent.getStringExtra("extra_primary_text"));
        PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
        newBuilder.mergeFrom((PhotoInfo) ProtoParsers.getTrusted(intent, "extra_photo_info", PhotoInfo.getDefaultInstance()));
        PhotoInfo.Builder builder = newBuilder;
        builder.setIsRtt(false);
        builder.setIsVideo(false);
        this.adapter.setPhotoInfo((PhotoInfo) builder.build());
    }

    static /* synthetic */ void lambda$handleIntent$0(Throwable th) {
        throw new RuntimeException(th);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_rtt_transcript);
        this.toolbar = (DialerToolbar) findViewById(R.id.toolbar);
        this.toolbar.setBackgroundColor(getColor(R.color.rtt_transcript_primary_color));
        getWindow().setStatusBarColor(getColor(R.color.rtt_transcript_primary_color_dark));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rtt_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        recyclerView.setHasFixedSize(true);
        this.adapter = new RttTranscriptAdapter(this);
        recyclerView.setAdapter(this.adapter);
        this.rttTranscriptUiListener = DialerExecutorComponent.get(this).createUiListener(getFragmentManager(), "Load RTT transcript");
        handleIntent(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
