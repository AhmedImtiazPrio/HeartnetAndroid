/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.models;

import java.io.IOException;
import java.util.Vector;

import com.mmm.healthcare.scope.ConfigurationFactory;
import com.mmm.healthcare.scope.IBluetoothManager;
import com.mmm.healthcare.scope.Stethoscope;
import com.mmm.healthcare.streamaudiosample.controllers.StreamThread;

/**
 * The application model. Contains the sender stethoscope and receiver
 * stethoscope.
 * 
 * @author 3M Company.
 * 
 */
public class ApplicationModel {

    private Stethoscope senderStethoscope;
    private int receiverStethoscopeIndex = 0;

    private Stethoscope receiverStethoscope;
    private int senderStethoscopeIndex = 0;

    private StreamThread streamThread;

    private static ApplicationModel model;

    /**
     * Gets the current instance of the <code>ApplicationModel</code>.
     * 
     * @return
     */
    public static ApplicationModel getInstance() {
        if (model == null) {
            model = new ApplicationModel();
        }

        return model;
    }

    /**
     * The default constructor.
     */
    private ApplicationModel() {
        // Do nothing.
    }

    /**
     * Gets the stethoscopes that are paired to this device.
     * 
     * @return The stethoscopes paired to the device.
     */
    public Vector<Stethoscope> getPairedStethoscopes() {

        try {
            IBluetoothManager bluetoothManager = ConfigurationFactory
                    .getBluetoothManager();
            return bluetoothManager.getPairedDevices();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    /**
     * Sets the sender stethoscope. This stethoscope sends its audio to the
     * receiver stethoscope.
     * 
     * @param sender
     *            The sender stethoscope.
     * @param index
     *            The index of the sender stethoscope in the spinner.
     */
    public void setSenderStethoscope(Stethoscope sender, int index) {

        senderStethoscope = sender;
        senderStethoscopeIndex = index;

    }

    /**
     * Sets the receiver stethoscope. This stethoscope receives audio from the
     * sender stethoscope.
     * 
     * @param receiver
     *            The receiver stethoscope.
     * @param index
     *            The index of the receiver stethoscope in the spinner.
     */
    public void setReceiverStethoscope(Stethoscope receiver, int index) {

        receiverStethoscope = receiver;
        receiverStethoscopeIndex = index;
    }

    /**
     * Gets the index of the receiver stethoscope in the spinner.
     * 
     * @return The stethoscope's index.
     */
    public int getReceiverStethoscopeIndex() {
        return receiverStethoscopeIndex;
    }

    /**
     * Gets the index of the sender stethoscope in the spinner.
     * 
     * @return The stethoscope's index.
     */
    public int getSenderIndex() {
        return senderStethoscopeIndex;
    }

    /**
     * Gets the sender stethoscope.
     * 
     * @return The sender stethoscope.
     */
    public Stethoscope getSenderStethoscope() {
        return senderStethoscope;
    }

    /**
     * Gets the receiver stethoscope.
     * 
     * @return The receiver stethoscope.
     */
    public Stethoscope getReceiverStethoscope() {
        return receiverStethoscope;
    }

    /**
     * Gets whether or not the receiver stethoscope is connected.
     * 
     * @return <code>true</code> if the receiver stethoscope is connected;
     *         <code>false</code> otherwise.
     */
    public boolean isReceiverConnected() {
        if (receiverStethoscope != null) {
            return receiverStethoscope.isConnected();
        } else {
            return false;
        }
    }

    /**
     * Starts streaming audio from the sender stethoscope to the receiver
     * stethoscope.
     */
    public void startStreaming() {
        receiverStethoscope.startAudioOutput();
        senderStethoscope.startAudioInput();

        streamThread = new StreamThread();
        streamThread.start(senderStethoscope, receiverStethoscope);
    }

    /**
     * Stops audio streaming.
     */
    public void stopStreaming() {
        if (receiverStethoscope != null && receiverStethoscope.isConnected()) {
            receiverStethoscope.stopAudioOutput();
        }

        if (senderStethoscope != null && senderStethoscope.isConnected()) {
            senderStethoscope.stopAudioInput();
        }

        if (streamThread != null) {
            streamThread.stop();
        }
    }

    /**
     * Gets whether or not the sender stethoscope is connected.
     * 
     * @return <code>true</code> if the sender stethoscope is connected;
     *         <code>false</code> otherwise.
     */
    public boolean isSenderConnected() {
        if (senderStethoscope != null) {
            return senderStethoscope.isConnected();
        } else {
            return false;
        }
    }

    /**
     * Gets the sender stethosocpe's information and metrics.
     * 
     * @return The sender stethoscope's information and metrics.
     */
    public StethoscopeInformation getSenderInformation() {

        StethoscopeInformation info = new StethoscopeInformation();

        if (senderStethoscope != null && senderStethoscope.isConnected()) {

            info.setModelNumber(senderStethoscope.getModelNumber());
            info.setFirmwareVersion(senderStethoscope.getFirmwareVersion());
            info.setBatteryLevel(senderStethoscope.getBatteryLevel());
            info.setIsAutoBluetoothOn(senderStethoscope.getIsAutoBluetoothOn());

            info.setTotalBytesRead(senderStethoscope.getTotalBytesRead());
            info.setBytesReadPerSecond(senderStethoscope
                    .getBytesReadPerSecond());

            info.setTotalBytesWritten(senderStethoscope.getTotalBytesWritten());
            info.setBytesWrittenPerSecond(senderStethoscope
                    .getBytesWrittenPerSecond());

        } else {

            info.setModelNumber("N/A");
            info.setFirmwareVersion(0);
            info.setBatteryLevel(0);
            info.setIsAutoBluetoothOn(false);

            info.setTotalBytesRead(0);
            info.setBytesReadPerSecond(0);

            info.setTotalBytesWritten(0);
            info.setBytesWrittenPerSecond(0);

        }

        return info;
    }

    /**
     * Gets the receiver stethoscope's information and metrics.
     * 
     * @return The receiver stethoscope's information and metrics.
     */
    public StethoscopeInformation getReceiverInformation() {
        StethoscopeInformation info = new StethoscopeInformation();

        if (receiverStethoscope != null && receiverStethoscope.isConnected()) {

            info.setModelNumber(receiverStethoscope.getModelNumber());
            info.setFirmwareVersion(receiverStethoscope.getFirmwareVersion());
            info.setBatteryLevel(receiverStethoscope.getBatteryLevel());
            info.setIsAutoBluetoothOn(receiverStethoscope
                    .getIsAutoBluetoothOn());

            info.setTotalBytesRead(receiverStethoscope.getTotalBytesRead());
            info.setBytesReadPerSecond(receiverStethoscope
                    .getBytesReadPerSecond());

            info.setTotalBytesWritten(receiverStethoscope
                    .getTotalBytesWritten());
            info.setBytesWrittenPerSecond(receiverStethoscope
                    .getBytesWrittenPerSecond());
        } else {

            info.setModelNumber("N/A");
            info.setFirmwareVersion(0);
            info.setBatteryLevel(0);
            info.setIsAutoBluetoothOn(false);

            info.setTotalBytesRead(0);
            info.setBytesReadPerSecond(0);

            info.setTotalBytesWritten(0);
            info.setBytesWrittenPerSecond(0);

        }

        return info;
    }

    /**
     * Disconnects the sender stethoscope.
     */
    public void disconnectSender() {
        senderStethoscope.disconnect();
    }

    /**
     * Connects the sender stethoscope.
     * 
     * @throws IOException
     *             An error occurred while attempting to connect to the sender
     *             stethoscope.
     */
    public void connectSender() throws IOException {
        senderStethoscope.connect();
    }

    /**
     * Disconnects the receiver stethoscope.
     */
    public void disconnectReceiver() {
        receiverStethoscope.disconnect();
    }

    /**
     * Connects the receiver stethoscope.
     * 
     * @throws IOException
     *             An error occurred while attempting to connect the receiver
     *             stethoscope.
     */
    public void connectReceiver() throws IOException {
        receiverStethoscope.connect();
    }
    

}
