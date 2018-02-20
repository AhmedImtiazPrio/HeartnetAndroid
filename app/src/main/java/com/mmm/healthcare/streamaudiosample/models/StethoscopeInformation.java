/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.models;

/**
 * An object used to hold stethoscope information and metrics and display them
 * to users when audio is being streamed.
 * 
 * @author 3M Company
 * 
 */
public class StethoscopeInformation {

    private String modelNumber;
    private double firmwareVersion;
    private int batteryLevel;
    private boolean isAutoBluetoothOn;
    private long totalBytesRead;
    private int bytesReadPerSecond;
    private long totalBytesWritten;
    private long bytesWrittenPerSecond;

    /**
     * Sets the stethoscope's model number.
     * 
     * @param model
     *            The stethoscope's model number.
     */
    public void setModelNumber(String model) {
        modelNumber = model;
    }

    /**
     * Gets the stethoscope's model number.
     * 
     * @return The stethoscope's model number
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * Sets the stethoscope's firmware version.
     * 
     * @param version
     *            The stethoscope's firmware version.
     */
    public void setFirmwareVersion(double version) {
        firmwareVersion = version;
    }

    /**
     * Gets the stethoscope's firmware version.
     * 
     * @return The stethoscope's firmware version.
     */
    public double getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * Sets the stethoscope's battery level.
     * 
     * @param level
     *            The stethoscope's battery level.
     */
    public void setBatteryLevel(int level) {
        batteryLevel = level;
    }

    /**
     * Gets the stethoscope's battery level.
     * 
     * @return The stethoscope's battery level.
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Sets whether or not the stethoscope's auto bluetooth-on is enabled or
     * disabled.
     * 
     * @param isOn
     *            Whether or not the stethoscope's auto bluetooth-on is enabled
     *            or disabled.
     */
    public void setIsAutoBluetoothOn(boolean isOn) {
        isAutoBluetoothOn = isOn;
    }

    /**
     * Gets whether or not the stethoscope's auto bluetooth-on is enabled or
     * disabled.
     * 
     * @return <code>true</code> for enabled; <code>false</code> for disabled.
     */
    public boolean getIsAutoBluetoothOn() {
        return isAutoBluetoothOn;
    }

    /**
     * Sets the total bytes read by the stethoscope.
     * 
     * @param totalRead
     *            The total bytes read.
     */
    public void setTotalBytesRead(long totalRead) {
        totalBytesRead = totalRead;
    }

    /**
     * Gets the total bytes read by the stethoscope.
     * 
     * @return The total bytes read.
     */
    public long getTotalBytesRead() {
        return totalBytesRead;
    }

    /**
     * Sets the total bytes read per second by the stethoscope.
     * 
     * @param readPerSecond
     *            The total bytes read per second.
     */
    public void setBytesReadPerSecond(int readPerSecond) {
        bytesReadPerSecond = readPerSecond;
    }

    /**
     * Gets the bytes read per second by the stethoscope.
     * 
     * @return The bytes read per second.
     */
    public int getBytesReadPerSecond() {
        return bytesReadPerSecond;
    }

    /**
     * Sets the total bytes written by the stethoscope.
     * 
     * @param totalWritten
     *            The total bytes written.
     */
    public void setTotalBytesWritten(long totalWritten) {
        totalBytesWritten = totalWritten;
    }

    /**
     * Gets the total bytes written by the stethoscope.
     * 
     * @return The total bytes written.
     */
    public long getTotalBytesWritten() {
        return totalBytesWritten;
    }

    /**
     * Gets the bytes written per second by the stethoscope.
     * 
     * @param writtenPerSecond
     *            The bytes written per second.
     */
    public void setBytesWrittenPerSecond(long writtenPerSecond) {
        bytesWrittenPerSecond = writtenPerSecond;
    }

    /**
     * Gets the bytes written per second by the stethoscope.
     * 
     * @return The bytes written per second.
     */
    public long getBytesWrittenPerSecond() {
        return bytesWrittenPerSecond;
    }

}
