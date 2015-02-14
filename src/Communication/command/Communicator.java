/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Communication.command;

import gnu.io.*;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import SaraswatiSat.gui.Telemetry;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Bhadresh
 */
public class Communicator implements SerialPortEventListener {
    
    //Main GUI
    Telemetry window = null;
    
    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String logText = "";
    int i = 0, j = 0;
    public int[] k= new int[100];
    public int tx_c = 0, rx_c = 0, temp = 0, fail = 0;

    public Communicator(Telemetry window)
    {
        this.window = window;
        
        window.RSSI_val.setText(Integer.toString(window.RSSI.getValue()));

        if ( window.tx_c.getText() == "" )
            this.tx_c = 0;
                    
        if ( window.rx_c.getText() == "" )
            this.rx_c = 0;
    }

    //search for all the serial ports
    //pre: none
    //post: adds all the found ports to a combo box on the GUI
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                window.Port.addItem(curPort.getName());
                portMap.put(curPort.getName(), curPort);
            }
        }
    }

    //connect to the selected port in the combo box
    //pre: ports are already found by using the searchForPorts method
    //post: the connected comm port is stored in commPort, otherwise,
    //an exception is generated
    public void connect()
    {
        String selectedPort = (String)window.Port.getSelectedItem();
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("TigerControlPanel", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;
           
            //String j = window.Baudrate.getSelectedItem().toString();
            //serialPort.setSerialPortParams(j, i, i, i);

            //for controlling GUI elements
            setConnected(true);

            //logging
            logText = selectedPort + " opened successfully.";
            window.status.setForeground(Color.black);
            window.status.append(logText + "\n");

        }
        catch (PortInUseException e)
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";
            
            window.status.setForeground(Color.RED);
            window.status.append(logText + "\n");
        }
        catch (Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
            window.status.append(logText + "\n");
            window.status.setForeground(Color.RED);
        }
    }
    
    //open the input and output streams
    //pre: an open port
    //post: initialized intput and output streams for use to communicate data
    public boolean initIOStream()
    {
        //return value for whather opening the streams is successful or not
        boolean successful = false;

        try {
            //
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            successful = true;
            return successful;
        }
        catch (IOException e) {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            window.status.setForeground(Color.red);
            window.status.append(logText + "\n");
            return successful;
        }
        
    }

    //starts the event listener that knows whenever data is available to be read
    //pre: an open serial port
    //post: an event listener for the serial port that knows when data is recieved
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
            window.status.setForeground(Color.red);
            window.status.append(logText + "\n");
        }
    }
    
    public void nodata()
    {
        logText = "No data available on serial port.";
        window.status.setForeground(Color.red);
        window.status.append(logText + "\n");
    }

    //disconnect the serial port
    //pre: an open serial port
    //post: clsoed serial port
    public void disconnect()
    {
        //close the serial port
        try
        {
            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            setConnected(false);
            logText = "Disconnected.";
            window.status.setForeground(Color.red);
            window.status.append(logText + "\n");
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getName() + "(" + e.toString() + ")";
            window.status.setForeground(Color.red);
            window.status.append(logText + "\n");
        }
    }
    
    final public boolean getConnected()
    {
        return bConnected;
    }

    public void setConnected(boolean bConnected)
    {
        this.bConnected = bConnected;
    }
    
    //what happens when data is received
    //pre: serial event is triggered
    //post: processing on the data it reads
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                RSSIUpdate();
                byte singleData = (byte)input.read();
                tx_c++;
                window.tx_c.setText(Integer.toString(tx_c));
                logText = new String(new byte[] {singleData});
                window.packet.append(logText);
                i++;
                if( i == 1 )
                {
                    window.payload.setBackground(Color.GREEN);
                    window.payload.setText("On");
                    window.ant.setBackground(Color.GREEN);
                    window.ant.setText("True");
                    window.amp.setBackground(Color.GREEN);
                    window.amp.setText("On");
                    window.ram.setBackground(Color.GREEN);
                    window.ram.setText("Available");
                    window.failp.setText(Integer.toString(fail));
                    
                }
                if ( i > 150 )
                {
                    window.ram.setBackground(Color.RED);
                    window.ram.setForeground(Color.WHITE);
                    window.ram.setText("Full");
                }
                if(i % 8 == 0)
                {
                    window.packet.append("\n");
                }
                //if(i>64)
                //{
                    k[j] = Integer.parseInt(logText);
                    j++;
                    if(i % 8 == 0)
                    {
                        temp = binary2dec(k);
                        window.lastp.setText(Integer.toString(temp));
                        if( temp == 18 )
                            voltage(binary2dec(k));
                        if( temp == 35 )
                            temperature(binary2dec(k));
                        if( temp == 67 )
                            BattryA(binary2dec(k));
                        if( temp == 99 )
                            BattryB(binary2dec(k));
                        if( temp == 131 )
                            X(binary2dec(k));
                        if( temp == 163 )
                            Y(binary2dec(k));
                        if( temp == 195 )
                            Z(binary2dec(k));
                    }
                //}
            }
            catch (Exception e)
            {
                fail++;
                logText = "Failed to read data. (" + e.toString() + ")";
                window.status.setForeground(Color.red);
                window.status.append(logText + "\n");
                window.failp.setText(Integer.toString(fail));
            }
        }
    }
    
    public void writeData(String data)
    {
        try
        {
            rx_c++;
            window.rx_c.setText(Integer.toString(rx_c));
            output.write(data.getBytes());
            output.flush();
        }
        catch (Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";
            window.status.setForeground(Color.red);
            window.status.append(logText + "\n");
        }
    }
    
    public int binary2dec(int[] k)
    {
        int decimal = 0, power = 0, flag = 0, trace = 7;
        while(flag != 8)
        {
            decimal += k[trace]*Math.pow(2, power);
            //System.out.println("Decimal: "+decimal+"k["+trace+"]:"+k[trace]+" Power:"+power);
            trace--;
            power++;
            flag++;
        }
        k=null;
        j=0;
        return decimal;
    }
    
    public void RSSIUpdate()
    {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        window.RSSI.setValue(randomInt);
        window.RSSI_val.setText(Integer.toString(window.RSSI.getValue()));
    }
    public void voltage(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<4;a++)
        {
            int randomInt = randomGenerator.nextInt(5);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(m/randomInt);
            else
                s = Integer.toString(m/(randomInt+2));
            switch(a)
            {
                case 0:
                    window.vp1.setText(s);
                    break;
                case 1:
                    window.vp2.setText(s);
                    break;
                case 2:
                    window.vp3.setText(s);
                    break;
                case 3:
                    window.vp4.setText(s);
                    break;
            }
        }
    }
    
    public void temperature(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<4;a++)
        {
            int randomInt = randomGenerator.nextInt(6);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(((m*randomInt)/(randomInt-1)));
            else
                s = Integer.toString(((m*3)/Math.abs(randomInt-2)));
            switch(a)
            {
                case 0:
                    window.tp1.setText(s);
                    break;
                case 1:
                    window.tp2.setText(s);
                    break;
                case 2:
                    window.tp3.setText(s);
                    break;
                case 3:
                    window.tp4.setText(s);
                    break;
            }
        }
    }
    
    public void BattryA(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<5;a++)
        {
            int randomInt = randomGenerator.nextInt(6);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(((m*randomInt)/(randomInt-1)));
            else
                s = Integer.toString(((m*3)/Math.abs(randomInt-2)));
            switch(a)
            {
                case 0:
                    window.raacA.setText(s);
                    break;
                case 1:
                    window.iavgA.setText(s);
                    break;
                case 2:
                    window.iinstA.setText(s);
                    break;
                case 3:
                    window.voltA.setText(s);
                case 4:
                    window.tempA.setText(s);
                    break;
            }
        }
    }
    
    public void BattryB(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<5;a++)
        {
            int randomInt = randomGenerator.nextInt(6);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(((m*randomInt)/(randomInt-1)));
            else
                s = Integer.toString(((m*3)/Math.abs(randomInt-2)));
            switch(a)
            {
                case 0:
                    window.raacB.setText(s);
                    break;
                case 1:
                    window.iavgB.setText(s);
                    break;
                case 2:
                    window.iinstB.setText(s);
                    break;
                case 3:
                    window.voltB.setText(s);
                case 4:
                    window.tempB.setText(s);
                    break;
            }
        }
    }
    
    public void X(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<4;a++)
        {
            int randomInt = randomGenerator.nextInt(5);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(m/randomInt);
            else
                s = Integer.toString(m/(randomInt+2));
            switch(a)
            {
                case 0:
                    window.sx.setText(s);
                    break;
                case 1:
                    window.mx.setText(s);
                    break;
                case 2:
                    window.gx.setText(s);
                    break;
            }
        }
    }
    
    public void Y(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<4;a++)
        {
            int randomInt = randomGenerator.nextInt(5);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(m/randomInt);
            else
                s = Integer.toString(m/(randomInt+2));
            switch(a)
            {
                case 0:
                    window.sy.setText(s);
                    break;
                case 1:
                    window.my.setText(s);
                    break;
                case 2:
                    window.gy.setText(s);
                    break;
            }
        }
    }
    
    public void Z(int m)
    {
        int a = 0;
        String s = null;
        Random randomGenerator = new Random();
        for(a=0;a<4;a++)
        {
            int randomInt = randomGenerator.nextInt(5);
            if ( randomInt != 0 && randomInt != 1)
                s = Integer.toString(m/randomInt);
            else
                s = Integer.toString(m/(randomInt+2));
            switch(a)
            {
                case 0:
                    window.sz.setText(s);
                    break;
                case 1:
                    window.mz.setText(s);
                    break;
                case 2:
                    window.gz.setText(s);
                    break;
            }
        }
    }
}
