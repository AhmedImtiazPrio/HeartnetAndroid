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

import com.mmm.healthcare.scope.ConfigurationFactory;
import com.mmm.healthcare.scope.Stethoscope;
import com.mmm.healthcare.streamaudiosample.R;
import com.mmm.healthcare.streamaudiosample.enums.ConnectionState;
import com.mmm.healthcare.streamaudiosample.enums.StreamingState;
import com.mmm.healthcare.streamaudiosample.interfaces.IConnectView;
import com.mmm.healthcare.streamaudiosample.interfaces.IConnectViewListener;
import com.mmm.healthcare.streamaudiosample.models.ApplicationModel;
import com.mmm.healthcare.streamaudiosample.views.ConnectView;

/**
 * A controller that manages the {@link IConnectView}.
 * 
 * @author 3M Company
 * 
 */
public class ConnectionActivity extends Activity implements
        IConnectViewListener {
    
    private ApplicationModel model;
    private IConnectView view;
    private boolean isStreaming = false;
    private StreamingState currentStreamingState;

    /**
     * Occurs when this activity is created.
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        model = ApplicationModel.getInstance();
        
        model.stopStreaming();

        ConfigurationFactory.setContext(this);
        view = (IConnectView) View.inflate(this, R.layout.stethoscope_layout,
                null);
        view.addConnectionEventListener(this);

        view.setSelectedReceiverStethoscope(model.getReceiverStethoscopeIndex());
        view.setSelectedSenderStethoscope(model.getSenderIndex());

        setContentView((ConnectView) view);
        updateConnectionComponents();
    }

    /**
     * Sets the receiver stethoscope.
     */
    @Override
    public void onReceiverSelected(Stethoscope stethoscope, int index) {

        Stethoscope receiver = model.getReceiverStethoscope();

        if (receiver == null || !receiver.isConnected()) {
            model.setReceiverStethoscope(stethoscope, index);
        } else {
            // Receiver stethoscope is already connected. Keep it selected.
            view.setSelectedReceiverStethoscope(model.getReceiverStethoscopeIndex());
            updateConnectionComponents();
        }
    }

    /**
     * Sets the sender stethoscope.
     */
    @Override
    public void onSenderSelected(Stethoscope stethoscope, int index) {

        Stethoscope receiver = model.getReceiverStethoscope();

        if (receiver == null || !receiver.isConnected()) {
            model.setSenderStethoscope(stethoscope, index);
        } else {
            // Sender stethoscope is already connected. Keep it selected.
            updateConnectionComponents();
        }
    }

    /**
     * Connects or disconnects the receiver stethoscope from the application.
     */
    @Override
    public void onReceiverConnectionRequest() {

        if (isSameStethoscopeSelected()) {
            view.onSameStethoscopeSelected();
            return;
        }

        if (model.isReceiverConnected()) {
            model.disconnectReceiver();
            view.updateReceiverConnectionState(ConnectionState.Disconnected);
        } else {
            try {

                Stethoscope sender = model.getSenderStethoscope();
                Stethoscope receiver = model.getReceiverStethoscope();

                if (sender != null
                        && sender.getSerialNumber().equals(
                                receiver.getSerialNumber())) {
                    // Prevent the model from referencing the same connected
                    // stethoscope for both the receiver and sender.
                    model.setSenderStethoscope(null, 0);
                }

                model.connectReceiver();
                view.updateReceiverConnectionState(ConnectionState.Connected);
                
            } catch (Exception e) {
                view.updateReceiverConnectionState(ConnectionState.Error);
            }
        }

        setStreamingViewState();
    }

    /**
     * Connects or disconnects the sender stethoscope from the application.
     */
    @Override
    public void onSenderConnectionRequest() {

        if (isSameStethoscopeSelected()) {
            view.onSameStethoscopeSelected();
            return;
        }

        if (model.isSenderConnected()) {
            model.disconnectSender();
            view.updateSenderConnectionState(ConnectionState.Disconnected);
        } else {
            try {

                Stethoscope sender = model.getSenderStethoscope();
                Stethoscope receiver = model.getReceiverStethoscope();

                if (receiver != null
                        && sender.getSerialNumber().equals(
                                receiver.getSerialNumber())) {
                    // Prevent the model from referencing the same connected
                    // stethoscope for both the receiver and sender.

                    model.setReceiverStethoscope(null, 0);
                }

                model.connectSender();
                view.updateSenderConnectionState(ConnectionState.Connected);
                
            } catch (Exception e) {
                view.updateSenderConnectionState(ConnectionState.Error);
            }
        }

        setStreamingViewState();
    }

    /**
     * Starts or stops stethoscope streaming.
     */
    @Override
    public void onStreamingRequest() {
        if (!isStreaming) {

            // Start audio streaming between the sender and receiver
            // stethoscopes.
            model.startStreaming();
            currentStreamingState = StreamingState.Streaming;

            // Start the stream activity showing information from the sender
            // stethoscope
            Intent streaming = new Intent(this, StreamingActivity.class);
            startActivity(streaming);

        } else {
            model.stopStreaming();
            currentStreamingState = StreamingState.NotStreaming;
        }

        view.updateStreamingState(currentStreamingState);
    }

    /**
     * Updates this activity's view based on the connection state of the sender
     * and receiver stethoscopes.
     */
    private void updateConnectionComponents() {
        if (model.isSenderConnected()) {
            view.updateSenderConnectionState(ConnectionState.Connected);
        } else {
            view.updateSenderConnectionState(ConnectionState.Disconnected);
        }

        if (model.isReceiverConnected()) {
            view.updateReceiverConnectionState(ConnectionState.Connected);
        } else {
            view.updateReceiverConnectionState(ConnectionState.Disconnected);
        }

        if (model.isSenderConnected() && model.isReceiverConnected()) {
            view.updateStreamingState(StreamingState.CanStartStreaming);
        } else {
            view.updateStreamingState(StreamingState.CannotStartStreaming);
        }
    }

    /**
     * Determines whether or not the same stethoscope is selected.
     * 
     * @return <code>true</code> if the same stethoscope is selected and one of
     *         the stethoscopes is already selected; <code>false</code>
     *         otherwise.
     */
    private boolean isSameStethoscopeSelected() {
        Stethoscope sender = model.getSenderStethoscope();
        Stethoscope receiver = model.getReceiverStethoscope();

        boolean isStethoscopeAlreadyConnected;

        if (sender == null || receiver == null) {
            return false;
        } else if (sender.isConnected() || receiver.isConnected()) {
            isStethoscopeAlreadyConnected = true;
        } else {
            isStethoscopeAlreadyConnected = false;
        }

        if (sender.equals(receiver) && isStethoscopeAlreadyConnected == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines whether or not the user can start streaming.
     */
    private void setStreamingViewState() {
        Stethoscope sender = model.getSenderStethoscope();
        Stethoscope receiver = model.getReceiverStethoscope();

        if (sender == null || receiver == null) {
            currentStreamingState = StreamingState.CannotStartStreaming;
        } else if (!sender.isConnected() || !receiver.isConnected()
                || isSameStethoscopeSelected()) {
            currentStreamingState = StreamingState.CannotStartStreaming;
        } else if (sender.isConnected() && receiver.isConnected()) {
            currentStreamingState = StreamingState.CanStartStreaming;
        }

        view.updateStreamingState(currentStreamingState);
    }
}
