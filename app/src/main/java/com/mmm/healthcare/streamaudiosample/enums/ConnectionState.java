/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement. All      //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.enums;

/**
 * An enum representing a stethoscope's current connection state.
 * 
 * @author 3M Company
 * 
 */
public enum ConnectionState {

    /**
     * The stethoscope is connected.
     */
    Connected,

    /**
     * The stethoscope is connected.
     */
    Disconnected,

    /**
     * An error occurred while trying to connect or disconnect the stethoscope.
     */
    Error
}
