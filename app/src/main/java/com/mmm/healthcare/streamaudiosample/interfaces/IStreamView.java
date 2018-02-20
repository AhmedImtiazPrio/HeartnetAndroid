/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.interfaces;

import com.mmm.healthcare.streamaudiosample.models.StethoscopeInformation;

/**
 * An interface for a streaming view GUI.
 * 
 * @author 3M Company
 * 
 */
public interface IStreamView {

    /**
     * Updates the stethoscope's information and streaming metrics.
     * 
     * @param info
     *            The stethoscope's information.
     */
    public void updateStethoscopeInformation(StethoscopeInformation info);

    /**
     * Adds a listener to this view to respond to user events.
     * 
     * @param eventListener
     *            The event listener.
     */
    public void addStreamEventListener(IStreamEventListener eventListener);

    /**
     * Sets the streaming view's title.
     * 
     * @param title
     *            The view's title.
     */
    public void setViewTitle(String title);

    /**
     * Set's the text of the switch view component.
     * 
     * @param text
     *            The switch view component's text.
     */
    public void setSwitchText(String text);

}
