package com.android.dialer.simulator.impl;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telecom.Connection;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TargetApi(28)
class RttChatBot {
    /* access modifiers changed from: private */
    public static final String[] CANDIDATE_MESSAGES = {"To RTT or not to RTT, that is the question...", "Making TTY great again!", "I would be more comfortable with real \"Thyme\" chatting. I don't know how to end this pun", "お疲れ様でした", "The FCC has mandated that I respond... I will do so begrudgingly", "😂😂😂💯"};
    private final HandlerThread handlerThread = new HandlerThread("RttChatBot");
    private final MessageHandler messageHandler;

    private static class MessageHandler extends Handler {
        private String currentTypingMessage = null;
        private int currentTypingPosition = -1;
        private final List<String> messageQueue = new ArrayList();
        private final Random random = new Random();
        private final Connection.RttTextStream rttTextStream;

        MessageHandler(Looper looper, Connection.RttTextStream rttTextStream2) {
            super(looper);
            this.rttTextStream = rttTextStream2;
        }

        private String nextTyping() {
            if (this.currentTypingPosition < 0 || this.currentTypingMessage == null) {
                if (this.messageQueue.isEmpty()) {
                    this.messageQueue.add(RttChatBot.CANDIDATE_MESSAGES[this.random.nextInt(RttChatBot.CANDIDATE_MESSAGES.length)]);
                }
                this.currentTypingMessage = this.messageQueue.remove(0);
                this.currentTypingPosition = 0;
            }
            if (this.currentTypingPosition < this.currentTypingMessage.length()) {
                int nextInt = this.random.nextInt((this.currentTypingMessage.length() - this.currentTypingPosition) + 1);
                String str = this.currentTypingMessage;
                int i = this.currentTypingPosition;
                String substring = str.substring(i, i + nextInt);
                this.currentTypingPosition += nextInt;
                return substring;
            }
            this.currentTypingPosition = -1;
            this.currentTypingMessage = null;
            return "\n";
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                sendMessage(obtainMessage(2, nextTyping()));
            } else if (i == 2) {
                String str = (String) message.obj;
                try {
                    this.rttTextStream.write(str);
                } catch (IOException e) {
                    LogUtil.m7e("RttChatBot.MessageHandler", "write message", (Throwable) e);
                }
                if ("\n".equals(str)) {
                    sendMessageDelayed(obtainMessage(2, nextTyping()), (long) ((this.random.nextInt(10) + 1) * 1000));
                } else {
                    sendMessageDelayed(obtainMessage(2, nextTyping()), (long) (this.random.nextInt(10) * 200));
                }
            }
        }
    }

    RttChatBot(Connection.RttTextStream rttTextStream) {
        this.handlerThread.start();
        this.messageHandler = new MessageHandler(this.handlerThread.getLooper(), rttTextStream);
    }

    /* access modifiers changed from: package-private */
    public void start() {
        Assert.isMainThread();
        LogUtil.enterBlock("RttChatBot.start");
        this.messageHandler.sendEmptyMessage(1);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        Assert.isMainThread();
        LogUtil.enterBlock("RttChatBot.stop");
        HandlerThread handlerThread2 = this.handlerThread;
        if (handlerThread2 != null && handlerThread2.isAlive()) {
            this.handlerThread.quit();
        }
    }
}
