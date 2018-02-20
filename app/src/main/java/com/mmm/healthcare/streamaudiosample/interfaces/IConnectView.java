/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.interfaces;

import com.mmm.healthcare.streamaudiosample.enums.ConnectionState;
import com.mmm.healthcare.streamaudiosample.enums.StreamingState;

/**
 * An interface for the connect view GUI.
 * 
 * @author 3M Company
 * 
 */
public interface IConnectView {

    /**
     * Updates the sender stethoscope's connection state.
     * 
     * @param state
     *            The sender stethoscope's current connection state.
     */
    public void updateSenderConnectionState(ConnectionState state);

    /**
     * Updates the receiver stethoscope's connection state.
     * 
     * @param state
     *            The receiver stethoscope's current connection state.
     */
    public void updateReceiverConnectionState(ConnectionState state);

    /**
     * Updates the view's components based on the streaming state.
     * 
     * @param state
     *            The current streaming state.
     */
    public void updateStreamingState(StreamingState state);

    /**
     * Adds a listener to respond to user input from this view.
     * 
     * @param listener
     *            The listener to add.
     */
    public void addConnectionEventListener(IConnectViewListener listener);

    /**
     * Occurs when a user selects the same stethoscope for the sender and
     * receiver stethoscope.
     */
    public void onSameStethoscopeSelected();

    /**
     * Sets the selected index of receiver stethoscope's spinner.
     * 
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    public void setSelectedReceiverStethoscope(int index);

    /**
     * Sets the selected index of sender stethoscope's spinner.
     * 
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    public void setSelectedSenderStethoscope(int index);
}
