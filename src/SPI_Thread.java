import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.lang.*;
public class SPI_Thread extends Thread {

    SerialPort sp;


    SPI_Thread() {
        sp = SerialPort.getCommPort("COM3");
        sp.setComPortParameters(19200, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (sp.openPort())
            System.out.println("Port is open");
        else
            System.out.println("Failed to open Port");

    }


    @Override
    public void run() {
        byte redVal;
        byte greenVal;
        byte blueVal;

        int tempRed = LightGUI.rval;
        int tempGreen = LightGUI.gval;
        int tempBlue = LightGUI.bval;

        while (true) {
            if (sp.openPort()) {
                if (LightGUI.butSt) {

                    redVal = (byte) (LightGUI.rval - 128);
                    greenVal = (byte) (LightGUI.gval - 128);
                    blueVal = (byte) (LightGUI.bval - 128);

                    SendAndSleep(1, redVal, 100);
                    SendAndSleep(2, greenVal, 100);
                    SendAndSleep(3, blueVal, 100);

                }


                System.out.println("rval: " + LightGUI.rval + "\tbval: " + LightGUI.bval + "\tgval: " + LightGUI.gval);

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }

        }
    }


    private void SendAndSleep(int sel,byte sendVal,long sleepTime){
        try{
            sp.getOutputStream().write(sel);
            sp.getOutputStream().flush();
            Thread.sleep(200);
            sp.getOutputStream().write(sendVal);
            sp.getOutputStream().flush();
            Thread.sleep(sleepTime);
        }
        catch (Exception EX){EX.printStackTrace();}

    }
}



