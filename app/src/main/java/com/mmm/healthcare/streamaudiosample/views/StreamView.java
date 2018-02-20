/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mmm.healthcare.streamaudiosample.R;
import com.mmm.healthcare.streamaudiosample.interfaces.IStreamEventListener;
import com.mmm.healthcare.streamaudiosample.interfaces.IStreamView;
import com.mmm.healthcare.streamaudiosample.models.StethoscopeInformation;

/**
 * A view that is shown while the application is streaming audio. This view
 * shows stethoscope information and streaming metrics.
 * 
 * @author 3M Company
 * 
 */
public class StreamView extends TableLayout implements IStreamView {

    TextView stethoscopeLabel;
    TextView modelNumber;
    TextView firmwareVersion;
    TextView batteryLevel;
    TextView autoBluetooth;

    TextView bytesRead;
    TextView bytesReadSecond;
    TextView bytesWritten;
    TextView bytesWrittenSecond;

    Button switchButton;
    Button stopStreamingButton;

    IStreamEventListener listener;

    Handler handler = new Handler();

    /**
     * 
     * @param context
     * @param attrs
     */
    public StreamView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * Occurs after this view is loaded.
     */
    @Override
    protected void onFinishInflate() {
        initComponents();
    }

    /**
     * Adds a IStreamEventListener to this view.
     * 
     * @param eventListener The IStreamEventListener to add.
     */
    @Override
    public void addStreamEventListener(IStreamEventListener eventListener) {
        listener = eventListener;
    }

    /**
     * Updates the view with the stethoscope's metrics.
     */
    @Override
    public void updateStethoscopeInformation(StethoscopeInformation info) {

        update(info.getModelNumber(), modelNumber);
        update(info.getFirmwareVersion() + "", firmwareVersion);
        update(info.getBatteryLevel() + "", batteryLevel);
        update(info.getIsAutoBluetoothOn() + "", autoBluetooth);

        update(info.getTotalBytesRead() + "", bytesRead);
        update(info.getBytesReadPerSecond() + "", bytesReadSecond);

        update(info.getTotalBytesWritten() + "", bytesWritten);
        update(info.getBytesWrittenPerSecond() + "", bytesWrittenSecond);
    }

    /**
     * Sets the title for this view.
     */
    @Override
    public void setViewTitle(String title) {
        stethoscopeLabel.setText(title);
    }

    /**
     * Sets the text for this view's "switch metrics" button.
     */
    @Override
    public void setSwitchText(String text) {
        switchButton.setText(text);
    }

    /**
     * Helper method to update the information on the screen.
     * 
     * @param text
     *            The text to set.
     * @param view
     *            The view to set the text with.
     */
    private void update(String text, TextView view) {
        handler.post(new TextUpdater(text, view));
    }

    /**
     * Gets the components of this view and adds any necessary listeners.
     */
    private void initComponents() {
        stethoscopeLabel = (TextView) findViewById(R.id.stethoscope_label);
        modelNumber = (TextView) findViewById(R.id.model);
        firmwareVersion = (TextView) findViewById(R.id.firmware_version);
        batteryLevel = (TextView) findViewById(R.id.battery_level);
        autoBluetooth = (TextView) findViewById(R.id.auto_bluetooth);

        bytesRead = (TextView) findViewById(R.id.bytes_read);
        bytesReadSecond = (TextView) findViewById(R.id.bytes_read_sec);
        bytesWritten = (TextView) findViewById(R.id.bytes_written);
        bytesWrittenSecond = (TextView) findViewById(R.id.bytes_written_sec);

        switchButton = (Button) findViewById(R.id.stethoscope_switch_button);
        switchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.switchStethoscopeInformation();
            }
        });

        stopStreamingButton = (Button) findViewById(R.id.stop_streaming_button);
        stopStreamingButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.stopStreaming();
            }
        });
    }

    /**
     * A class used to send messages to the UI thread.
     * 
     * @author 3M Company
     * 
     */
    private class TextUpdater implements Runnable {

        String text;
        TextView textView;

        public TextUpdater(String text, TextView view) {
            this.text = text;
            textView = view;
        }

        @Override
        public void run() {
            textView.setText(text);
        }
    }

}
