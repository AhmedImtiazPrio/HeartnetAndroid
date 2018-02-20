/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.interfaces;

/**
 * An interface that responds to the connection states of the sender stethoscope
 * and receiver stethoscope.
 * 
 * @author 3M Company.
 * 
 */
public interface IConnectionEventListener {

    /**
     * The receiver stethoscope is connected.
     */
    public void receiverStethoscopeConnected();

    /**
     * The receiver stethoscope is disconnected.
     */
    public void receiverStethoscopeDisconnected();

    /**
     * The sender stethoscope is connected.
     */
    public void senderStethoscopeConnected();

    /**
     * The receiver stethoscope is disconnected.
     */
    public void senderStethoscopeDisconnected();

}
