/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.interfaces;

import com.mmm.healthcare.scope.Stethoscope;
import com.mmm.healthcare.streamaudiosample.views.ConnectView;

/**
 * An interface that allows classes to listen to user actions in the
 * {@link ConnectView}.
 * 
 * Audio is sent from the sender stethoscope to the receiver stethoscope.
 * 
 * @author 3M Company
 * 
 */
public interface IConnectViewListener {

    /**
     * Occurs when a receiver stethoscope is selected.
     * 
     * @param stethoscope
     *            The stethoscope that was selected.
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    public void onReceiverSelected(Stethoscope stethoscope, int index);

    /**
     * Occurs when a sender stethoscope is selected.
     * 
     * @param stethoscope
     *            The stethoscope that was selected.
     * @param index
     *            The index of the stethoscope in the spinner.
     */
    public void onSenderSelected(Stethoscope stethoscope, int index);

    /**
     * Occurs when a connect/disconnect for the receiver stethoscope is
     * received.
     */
    public void onReceiverConnectionRequest();

    /**
     * Occurs when a connect/disconnect for the sender stethoscope is received.
     */
    public void onSenderConnectionRequest();

    /**
     * Occurs when a start/stop streaming event is received.
     */
    public void onStreamingRequest();
}
