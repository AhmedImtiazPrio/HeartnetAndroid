/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.enums;

/**
 * An enum representing the possible streaming states for the application.
 * 
 * @author 3M Company
 * 
 */
public enum StreamingState {

    /**
     * The application is currently streaming.
     */
    Streaming,

    /**
     * The application is currently not streaming.
     */
    NotStreaming,

    /**
     * The application is able to start streaming.
     */
    CanStartStreaming,

    /**
     * The application is not able to start streaming.
     */
    CannotStartStreaming
}
