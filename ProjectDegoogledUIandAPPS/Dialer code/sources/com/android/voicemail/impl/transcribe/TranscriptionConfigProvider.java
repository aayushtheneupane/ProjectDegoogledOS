package com.android.voicemail.impl.transcribe;

import android.content.Context;
import android.os.Build;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import java.util.concurrent.TimeUnit;

public class TranscriptionConfigProvider {
    private final Context context;

    public TranscriptionConfigProvider(Context context2) {
        this.context = context2;
    }

    public String getApiKey() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getString("voicemail_transcription_client_api_key", "AIzaSyAXdDnif6B7sBYxU8hzw9qAp3pRPVHs060");
    }

    public String getAuthToken() {
        return null;
    }

    public long getInitialGetTranscriptPollDelayMillis() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong("voicemail_transcription_get_initial_transcript_poll_delay_millis", TimeUnit.SECONDS.toMillis(1));
    }

    public long getMaxGetTranscriptPollTimeMillis() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong("voicemail_transcription_get_max_transcript_poll_time_millis", TimeUnit.MINUTES.toMillis(20));
    }

    public int getMaxGetTranscriptPolls() {
        return (int) ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong("voicemail_transcription_max_get_transcript_polls", 20);
    }

    public long getMaxTranscriptionRetries() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong("voicemail_transcription_max_transcription_retries", 2);
    }

    public String getServerAddress() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getString("voicemail_transcription_server_address", "voicemailtranscription-pa.googleapis.com");
    }

    public boolean isVoicemailDonationAvailable() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("voicemail_transcription_donation_available", false);
    }

    public boolean isVoicemailTranscriptionAvailable() {
        int i = Build.VERSION.SDK_INT;
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("voicemail_transcription_available", false);
    }

    public boolean shouldUsePlaintext() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("voicemail_transcription_server_use_plaintext", false);
    }

    public boolean shouldUseSyncApi() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("voicemail_transcription_server_use_sync_api", false);
    }

    public String toString() {
        getAuthToken();
        return String.format("{ address: %s, api key: %s, auth token: %s, plaintext: %b, sync: %b, retries: %d, polls: %d, poll ms: %d }", new Object[]{getServerAddress(), getApiKey(), null, Boolean.valueOf(shouldUsePlaintext()), Boolean.valueOf(shouldUseSyncApi()), Long.valueOf(getMaxTranscriptionRetries()), Integer.valueOf(getMaxGetTranscriptPolls()), Long.valueOf(getMaxGetTranscriptPollTimeMillis())});
    }

    public boolean useClientGeneratedVoicemailIds() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("voicemail_transcription_client_generated_voicemail_ids", false);
    }
}
