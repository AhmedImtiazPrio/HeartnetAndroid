/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mmm.healthcare.streamaudiosample.R;
import com.mmm.healthcare.streamaudiosample.interfaces.IStreamEventListener;
import com.mmm.healthcare.streamaudiosample.interfaces.IStreamView;
import com.mmm.healthcare.streamaudiosample.models.ApplicationModel;
import com.mmm.healthcare.streamaudiosample.views.StreamView;

/**
 * A controller that manages the {@link IStreamView}.
 * 
 * @author 3M Company
 * 
 */
public class StreamingActivity extends Activity implements IStreamEventListener {
    
    private Thread metricsThread;

    private ApplicationModel model;
    private IStreamView senderView;
    private IStreamView receiverView;

    private CurrentView currentView;

    private boolean keepUpdatingMetrics = true;

    /**
     * Occurs when this activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ApplicationModel.getInstance();

        senderView = (IStreamView) View.inflate(this,
                R.layout.streaming_activity, null);
        senderView.setViewTitle("Sending Stethoscope");
        senderView.setSwitchText("Switch to Receiver");
        senderView.addStreamEventListener(this);

        receiverView = (IStreamView) View.inflate(this,
                R.layout.streaming_activity, null);
        receiverView.setViewTitle("Receiving Stethoscope");
        receiverView.setSwitchText("Switch to Sender");
        receiverView.addStreamEventListener(this);

        setContentView((StreamView) senderView);

        currentView = CurrentView.Sender;

        createAndStartMetricsThread();        
    }

        
       
    /**
     * Stops audio streaming between the sender stethoscope and the receiver
     * stethoscope.
     */
    @Override
    public void stopStreaming() {

   
        // Stop audio streaming.
        model.stopStreaming();

        keepUpdatingMetrics = false;

        // Switch to the connection activity.
        Intent connection = new Intent(this, ConnectionActivity.class);
        startActivity(connection);
    }

    /**
     * Switches the views to display either the sender stethoscope information
     * or the receiver stethoscope information.
     */
    @Override
    public void switchStethoscopeInformation() {

        switch (currentView) {
        
        case Sender:
            
            setContentView((StreamView) receiverView);
            currentView = CurrentView.Receiver;
            break;
            
        case Receiver:
            
            setContentView((StreamView) senderView);
            currentView = CurrentView.Sender;          
            break;
        }
    }

    /**
     * Creates a thread that updates the stethoscopes' information and streaming
     * metrics every 0.25 seconds.
     */
    private void createAndStartMetricsThread() {
        metricsThread = new Thread() {

            @Override
            public void run() {

                while (keepUpdatingMetrics) {
                    
                    if (!model.isReceiverConnected()
                            || !model.isSenderConnected()) {
                        
                        stopStreaming();
                        return;
                    }
                    
                    switch (currentView) {
                    case Sender:
                        senderView.updateStethoscopeInformation(model
                                .getSenderInformation());
                        break;
                    case Receiver:
                        receiverView.updateStethoscopeInformation(model
                                .getReceiverInformation());
                        break;
                    }

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        metricsThread.start();
    }

    /**
     * An enum used to keep track of the current view shown to the user.
     * 
     * @author 3M Company
     * 
     */
    private enum CurrentView {

        /**
         * The sender stethoscope information view is shown.
         */
        Sender,

        /**
         * The receiver stethoscope information view is shown.
         */
        Receiver
    }

}
