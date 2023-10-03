package com.android.contacts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.TimingLogger;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.contacts.activities.PeopleActivity;
import com.android.contacts.database.SimContactDao;
import com.android.contacts.model.SimCard;
import com.android.contacts.model.SimContact;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.ContactsNotificationChannelsUtil;
import com.android.contactsbind.FeedbackHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimImportService extends Service {
    public static final String BROADCAST_SERVICE_STATE_CHANGED = (SimImportService.class.getName() + "#serviceStateChanged");
    public static final String BROADCAST_SIM_IMPORT_COMPLETE = (SimImportService.class.getName() + "#simImportComplete");
    public static final String EXTRA_ACCOUNT = "account";
    public static final String EXTRA_OPERATION_REQUESTED_AT_TIME = "requestedTime";
    public static final String EXTRA_RESULT_CODE = "resultCode";
    public static final String EXTRA_RESULT_COUNT = "count";
    public static final String EXTRA_SIM_CONTACTS = "simContacts";
    public static final String EXTRA_SIM_SUBSCRIPTION_ID = "simSubscriptionId";
    private static final int NOTIFICATION_ID = 100;
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_UNKNOWN = 0;
    private static final String TAG = "SimImportService";
    /* access modifiers changed from: private */
    public static List<ImportTask> sPending = new ArrayList();
    private static StatusProvider sStatusProvider = new StatusProvider() {
        public boolean isRunning() {
            return !SimImportService.sPending.isEmpty();
        }

        public boolean isImporting(SimCard simCard) {
            return SimImportService.isImporting(simCard);
        }
    };
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public interface StatusProvider {
        boolean isImporting(SimCard simCard);

        boolean isRunning();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: private */
    public static boolean isImporting(SimCard simCard) {
        for (ImportTask sim : sPending) {
            if (sim.getSim().equals(simCard)) {
                return true;
            }
        }
        return false;
    }

    public static StatusProvider getStatusProvider() {
        return sStatusProvider;
    }

    public static void startImport(Context context, int i, ArrayList<SimContact> arrayList, AccountWithDataSet accountWithDataSet) {
        context.startService(new Intent(context, SimImportService.class).putExtra(EXTRA_SIM_CONTACTS, arrayList).putExtra(EXTRA_SIM_SUBSCRIPTION_ID, i).putExtra("account", accountWithDataSet));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        ContactsNotificationChannelsUtil.createDefaultChannel(this);
        ImportTask createTaskForIntent = createTaskForIntent(intent, i2);
        if (createTaskForIntent == null) {
            new StopTask(this, i2).executeOnExecutor(this.mExecutor, new Void[0]);
            return 2;
        }
        sPending.add(createTaskForIntent);
        createTaskForIntent.executeOnExecutor(this.mExecutor, new Void[0]);
        notifyStateChanged();
        return 3;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mExecutor.shutdown();
    }

    private ImportTask createTaskForIntent(Intent intent, int i) {
        AccountWithDataSet accountWithDataSet = (AccountWithDataSet) intent.getParcelableExtra("account");
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(EXTRA_SIM_CONTACTS);
        int intExtra = intent.getIntExtra(EXTRA_SIM_SUBSCRIPTION_ID, -1);
        SimContactDao create = SimContactDao.create(this);
        SimCard simBySubscriptionId = create.getSimBySubscriptionId(intExtra);
        if (simBySubscriptionId != null) {
            return new ImportTask(simBySubscriptionId, parcelableArrayListExtra, accountWithDataSet, create, i);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Notification getCompletedNotification() {
        Intent intent = new Intent(this, PeopleActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ContactsNotificationChannelsUtil.DEFAULT_CHANNEL);
        builder.setOngoing(false);
        builder.setAutoCancel(true);
        builder.setContentTitle(getString(R.string.importing_sim_finished_title));
        builder.setColor(getResources().getColor(R.color.dialtacts_theme_color));
        builder.setSmallIcon(R.drawable.quantum_ic_done_vd_theme_24);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0));
        return builder.build();
    }

    /* access modifiers changed from: private */
    public Notification getFailedNotification() {
        Intent intent = new Intent(this, PeopleActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ContactsNotificationChannelsUtil.DEFAULT_CHANNEL);
        builder.setOngoing(false);
        builder.setAutoCancel(true);
        builder.setContentTitle(getString(R.string.importing_sim_failed_title));
        builder.setContentText(getString(R.string.importing_sim_failed_message));
        builder.setColor(getResources().getColor(R.color.dialtacts_theme_color));
        builder.setSmallIcon(R.drawable.quantum_ic_error_vd_theme_24);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0));
        return builder.build();
    }

    /* access modifiers changed from: private */
    public Notification getImportingNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ContactsNotificationChannelsUtil.DEFAULT_CHANNEL);
        String string = getString(R.string.importing_sim_in_progress_title);
        builder.setOngoing(true);
        builder.setProgress(0, 100, true);
        builder.setContentTitle(string);
        builder.setColor(getResources().getColor(R.color.dialtacts_theme_color));
        builder.setSmallIcon(17301633);
        return builder.build();
    }

    /* access modifiers changed from: private */
    public void notifyStateChanged() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BROADCAST_SERVICE_STATE_CHANGED));
    }

    private static class StopTask extends AsyncTask<Void, Void, Void> {
        private Service mHost;
        private final int mStartId;

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            return null;
        }

        private StopTask(Service service, int i) {
            this.mHost = service;
            this.mStartId = i;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            this.mHost.stopSelf(this.mStartId);
        }
    }

    private class ImportTask extends AsyncTask<Void, Void, Boolean> {
        private final List<SimContact> mContacts;
        private final SimContactDao mDao;
        private final NotificationManager mNotificationManager;
        private final SimCard mSim;
        private final int mStartId;
        private final long mStartTime = System.currentTimeMillis();
        private final AccountWithDataSet mTargetAccount;

        public ImportTask(SimCard simCard, List<SimContact> list, AccountWithDataSet accountWithDataSet, SimContactDao simContactDao, int i) {
            this.mSim = simCard;
            this.mContacts = list;
            this.mTargetAccount = accountWithDataSet;
            this.mDao = simContactDao;
            this.mNotificationManager = (NotificationManager) SimImportService.this.getSystemService("notification");
            this.mStartId = i;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            SimImportService simImportService = SimImportService.this;
            simImportService.startForeground(100, simImportService.getImportingNotification());
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            TimingLogger timingLogger = new TimingLogger(SimImportService.TAG, "import");
            try {
                this.mDao.importContacts(this.mContacts, this.mTargetAccount);
                this.mDao.persistSimState(this.mSim.withImportedState(true));
                timingLogger.addSplit("done");
                timingLogger.dumpToLog();
                return true;
            } catch (OperationApplicationException | RemoteException e) {
                FeedbackHelper.sendFeedback(SimImportService.this, SimImportService.TAG, "Failed to import contacts from SIM card", e);
                return false;
            }
        }

        public SimCard getSim() {
            return this.mSim;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            Intent intent;
            Notification notification;
            super.onPostExecute(bool);
            SimImportService.this.stopSelf(this.mStartId);
            if (bool.booleanValue()) {
                intent = new Intent(SimImportService.BROADCAST_SIM_IMPORT_COMPLETE).putExtra("resultCode", 1).putExtra("count", this.mContacts.size()).putExtra(SimImportService.EXTRA_OPERATION_REQUESTED_AT_TIME, this.mStartTime).putExtra(SimImportService.EXTRA_SIM_SUBSCRIPTION_ID, this.mSim.getSubscriptionId());
                notification = SimImportService.this.getCompletedNotification();
            } else {
                intent = new Intent(SimImportService.BROADCAST_SIM_IMPORT_COMPLETE).putExtra("resultCode", 2).putExtra(SimImportService.EXTRA_OPERATION_REQUESTED_AT_TIME, this.mStartTime).putExtra(SimImportService.EXTRA_SIM_SUBSCRIPTION_ID, this.mSim.getSubscriptionId());
                notification = SimImportService.this.getFailedNotification();
            }
            LocalBroadcastManager.getInstance(SimImportService.this).sendBroadcast(intent);
            SimImportService.sPending.remove(this);
            if (SimImportService.sPending.isEmpty()) {
                SimImportService.this.stopForeground(false);
                this.mNotificationManager.notify(100, notification);
            }
            SimImportService.this.notifyStateChanged();
        }
    }
}
