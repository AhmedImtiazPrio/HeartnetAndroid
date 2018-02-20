/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 3M and its licensors 2013. All Rights Reserved. This software and         //
// associated files are licensed under the terms of the signed license agreement.  All     //
// sample code & sample applications are provided for demonstration purposes only and      //
// should not be used for commercial or diagnostic purposes.                               //
/////////////////////////////////////////////////////////////////////////////////////////////

package com.mmm.healthcare.streamaudiosample.controllers;

import java.io.InputStream;
import java.io.OutputStream;
import com.mmm.healthcare.scope.Stethoscope;

/**
 * Stream audio from one stethoscope to another stethoscope.
 * 
 * @author 3M Company
 * 
 */
public class StreamThread implements Runnable {

    private Stethoscope senderStethoscope;
    private Stethoscope receiverStethoscope;

    private Thread thread;

    /**
     * The thread entry point.
     */
    public void run() {

        try {

            InputStream senderInputStream = senderStethoscope
                    .getAudioInputStream();
            OutputStream recieverOutputStream = receiverStethoscope
                    .getAudioOutputStream();

            byte[] bytesToSend = new byte[128];

            while (true) {

                // read from sender stethoscope.
                int bytesReadCount = senderInputStream.read(bytesToSend);

                // wait for more audio from sender stethoscope.
                // this wait also prevent this thread from using all the CPU.
                if (bytesReadCount <= 0) {
                    Thread.sleep(100);

                    continue;
                }

                // send to receiver stethoscope.
                recieverOutputStream.write(bytesToSend);
            }

        } catch (InterruptedException expection) {
            
            // do nothing if the thread is interrupted.
            
        } catch (Exception exception) {
            
            exception.printStackTrace();
        }
    }

    /**
     * Starts the thread.
     */
    public void start(Stethoscope senderStethoscope,
            Stethoscope receiverStethoscope) {

        thread = new Thread(this);

        this.senderStethoscope = senderStethoscope;
        this.receiverStethoscope = receiverStethoscope;

        thread.start();
    }

    /**
     * Stops the thread.
     */
    public void stop() {

        // stop thread by interrupting.
        if (thread != null) {

            thread.interrupt();
            thread = null;

        }
    }
}
