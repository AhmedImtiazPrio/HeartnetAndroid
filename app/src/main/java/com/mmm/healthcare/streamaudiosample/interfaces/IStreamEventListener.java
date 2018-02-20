/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.interfaces;

/**
 * An interface used to respond to a stop streaming request or to change
 * streaming metrics between the sender stethoscope and receiver stethoscope.
 * 
 * @author 3M Company
 * 
 */
public interface IStreamEventListener {

    /**
     * A request to stop streaming occurred.
     */
    public void stopStreaming();

    /**
     * Switches the streaming information to display between the sender
     * stethoscope and the receiver stethoscope.
     */
    public void switchStethoscopeInformation();

}
