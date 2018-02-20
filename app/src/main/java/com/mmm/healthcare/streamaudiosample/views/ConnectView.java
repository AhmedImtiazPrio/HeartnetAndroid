/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.views;

import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mmm.healthcare.scope.Stethoscope;
import com.mmm.healthcare.streamaudiosample.R;
import com.mmm.healthcare.streamaudiosample.enums.ConnectionState;
import com.mmm.healthcare.streamaudiosample.enums.StreamingState;
import com.mmm.healthcare.streamaudiosample.interfaces.IConnectView;
import com.mmm.healthcare.streamaudiosample.interfaces.IConnectViewListener;
import com.mmm.healthcare.streamaudiosample.models.ApplicationModel;

/**
 * A view that allows users to connect a sender stethoscope and a receiver
 * stethoscope. The application streams audio from the sender stethoscope to the
 * receiver stethoscope. This view also allows users to start audio streaming
 * once the sender and receiver stethoscopes are connected.
 * 
 * @author 3M Company
 * 
 */
public class ConnectView extends LinearLayout implements IConnectView {

    private Button startStreamingButton;

    private Button senderConnectionButton;
    private Button receiverConnectionButton;

    private Spinner senderSpinner;
    private Spinner receiverSpinner;

    IConnectViewListener listener;

    private ApplicationModel model;
    private Context context;

    /**
     * 
     * @param context
     * @param attrs
     */
    public ConnectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        model = ApplicationModel.getInstance();
    }

    /**
     * Adds a <code>IConnectViewListener</code> to listen to user input in this
     * view.
     */
    public void addConnectionEventListener(IConnectViewListener listener) {
        this.listener = listener;

        // Set default selected stethoscopes
        listener.onReceiverSelected(
                (Stethoscope) receiverSpinner.getSelectedItem(),
                receiverSpinner.getSelectedItemPosition());
        listener.onSenderSelected(
                (Stethoscope) senderSpinner.getSelectedItem(),
                senderSpinner.getSelectedItemPosition());
    }

    /**
     * Occurs after the view is loaded.
     */
    @Override
    protected void onFinishInflate() {
        initComponents();
    }

    /**
     * Sets the sender stethoscope's button state to connect/disconnect.
     */
    @Override
    public void updateSenderConnectionState(ConnectionState state) {

        switch (state) {
        
        case Connected:
            senderConnectionButton.setText("Disconnect");
            senderSpinner.setEnabled(false);
            senderSpinner.setFocusable(false);
            break;
        
        case Disconnected:
            senderConnectionButton.setText("Connect");
            senderSpinner.setEnabled(true);
            senderSpinner.setFocusable(true);
            break;
        
        case Error:
            String message = "Could not connect to the stethoscope. Ensure that the stethoscope "
                    + "you are trying to connect "
                    + "to is in connect mode and is not already connected to the application.";
            showErrorDialog(message);
            break;
        }
    }

    /**
     * Sets the receiver stethoscope's button state to connect/disconnect.
     * Displays an error message if an error occurred while
     * connecting/disconnecting.
     */
    @Override
    public void updateReceiverConnectionState(ConnectionState state) {

        switch (state) {
        
        case Connected:
            receiverConnectionButton.setText("Disconnect");
            receiverSpinner.setEnabled(false);
            receiverSpinner.setFocusable(false);
            break;
        
        case Disconnected:
            receiverConnectionButton.setText("Connect");
            receiverSpinner.setEnabled(true);
            receiverSpinner.setFocusable(true);
            break;
        
        case Error:
            String message = "Could not connect to the stethoscope. Ensure that the stethoscope "
                    + "you are trying to connect "
                    + "to is in connect mode and is not already connected to the application.";
            showErrorDialog(message);
            break;
        }
    }

    /**
     * Enables/Disables the streaming button and sets the button text to
     * start/stop streaming.
     */
    @Override
    public void updateStreamingState(StreamingState state) {

        switch (state) {
        case Streaming:
            startStreamingButton.setEnabled(true);
            startStreamingButton.setText("Stop Streaming");
            senderSpinner.setEnabled(false);
            receiverSpinner.setEnabled(false);
            break;

        case NotStreaming:
            startStreamingButton.setEnabled(true);
            startStreamingButton.setText("Start Streaming");
            senderSpinner.setEnabled(true);
            receiverSpinner.setEnabled(true);
            break;

        case CannotStartStreaming:
            startStreamingButton.setEnabled(false);
            startStreamingButton.setText("Start Streaming");
            senderSpinner.setEnabled(true);
            receiverSpinner.setEnabled(true);
            break;

        case CanStartStreaming:
            startStreamingButton.setEnabled(true);
            startStreamingButton.setText("Start Streaming");
            senderSpinner.setEnabled(true);
            receiverSpinner.setEnabled(true);
            break;
        }
    }

    /**
     * Occurs when a users attempts to connect the same stethoscope as the
     * sender and receiver stethoscope.
     */
    @Override
    public void onSameStethoscopeSelected() {
        showErrorDialog("Sender and Receiver stethoscopes are the same. "
                + "Select a different stethoscope.");
    }

    /**
     * Shows an error message to the user.
     * 
     * @param message
     *            The error message to display.
     */
    private void showErrorDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Error")
                .setMessage(message).setPositiveButton("Ok", null).create();

        dialog.show();
    }

    /**
     * Assigns the view's components to variables and add listeners to any
     * necessary components.
     */
    private void initComponents() {

        senderSpinner = (Spinner) findViewById(R.id.sender_spinner);

        senderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {

                Stethoscope selectedStethoscope = (Stethoscope) senderSpinner
                        .getSelectedItem();
                listener.onSenderSelected(selectedStethoscope,
                        senderSpinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Do nothing.
            }
        });

        receiverSpinner = (Spinner) findViewById(R.id.receiver_spinner);
        receiverSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                Stethoscope selectedStethoscope = (Stethoscope) receiverSpinner
                        .getSelectedItem();
                listener.onReceiverSelected(selectedStethoscope,
                        receiverSpinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Do nothing.
            }
        });

        // Populate the sender and receiver spinners with the list of selectable
        // stethoscopes.
        Vector<Stethoscope> selectableStethoscopes = model
                .getPairedStethoscopes();
        ArrayAdapter<Stethoscope> stethoscopeAdapter = new ArrayAdapter<Stethoscope>(
                context, R.layout.stethoscope_list, R.id.list_view_text,
                selectableStethoscopes);

        senderSpinner.setAdapter(stethoscopeAdapter);
        receiverSpinner.setAdapter(stethoscopeAdapter);

        senderConnectionButton = (Button) findViewById(R.id.sender_connect_button);
        senderConnectionButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onSenderConnectionRequest();
            }
        });

        receiverConnectionButton = (Button) findViewById(R.id.receiver_connect_button);
        receiverConnectionButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onReceiverConnectionRequest();
            }
        });

        startStreamingButton = (Button) findViewById(R.id.stream_button);
        startStreamingButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onStreamingRequest();
            }
        });
        startStreamingButton.setEnabled(false);
    }

    /**
     * Sets the selected index of receiver stethoscope's spinner.
     * 
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    @Override
    public void setSelectedReceiverStethoscope(int index) {
        receiverSpinner.setSelection(index);
    }

    /**
     * Sets the selected index of sender stethoscope's spinner.
     * 
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    @Override
    public void setSelectedSenderStethoscope(int index) {
        senderSpinner.setSelection(index);

    }

}
