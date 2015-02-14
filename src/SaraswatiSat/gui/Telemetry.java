/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SaraswatiSat.gui;

import Communication.command.Communicator;
import Communication.command.Communicator.*;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Bhadresh
 */
public class Telemetry extends javax.swing.JFrame {

    SaraswatiSAT app;
    Communicator communicator = null;
    /**
     * Creates new form Telemetry
     */
    
    public Telemetry()
    {
        initComponents();
        
    }
    public Telemetry(SaraswatiSAT app) {
//        
//        this.app = app;
        initComponents();
        createObjects();
        communicator.searchForPorts();
        gsc.setText(app.dateTextField.getText());
        
//        final int length = packet.getText().length();
//        packet.setCaretPosition(length);
//        DefaultCaret caret = (DefaultCaret)packet.getCaret();
//        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//        jScrollPane2.setViewportView(packet);
        
        vp1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(vp1.getText()) < Integer.parseInt(thvp1.getText()) )
                {
                    vp1.setBackground(Color.red);
                    vp1.setForeground(Color.WHITE);
                }
                else
                {
                    vp1.setBackground(UIManager.getColor("TextField.background"));
                    vp1.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        vp2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(vp2.getText()) < Integer.parseInt(thvp2.getText()) )
                {
                    vp2.setBackground(Color.red);
                    vp2.setForeground(Color.WHITE);
                }
                else
                {
                    vp2.setBackground(UIManager.getColor("TextField.background"));
                    vp2.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
        
       vp3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(vp3.getText()) < Integer.parseInt(thvp3.getText()) )
                {
                    vp3.setBackground(Color.red);
                    vp3.setForeground(Color.WHITE);
                }
                else
                {
                    vp3.setBackground(UIManager.getColor("TextField.background"));
                    vp3.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       vp4.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(vp4.getText()) < Integer.parseInt(thvp4.getText()) )
                {
                    vp4.setBackground(Color.red);
                    vp4.setForeground(Color.WHITE);
                }
                else
                {
                    vp4.setBackground(UIManager.getColor("TextField.background"));
                    vp4.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       tp1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tp1.getText()) < Integer.parseInt(thtp1.getText()) )
                {
                    tp1.setBackground(Color.red);
                    tp1.setForeground(Color.WHITE);
                }
                else
                {
                    tp1.setBackground(UIManager.getColor("TextField.background"));
                    tp1.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        tp2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tp2.getText()) < Integer.parseInt(thtp2.getText()) )
                {
                    tp2.setBackground(Color.red);
                    tp2.setForeground(Color.WHITE);
                }
                else
                {
                    tp2.setBackground(UIManager.getColor("TextField.background"));
                    tp2.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
        
       tp3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tp3.getText()) < Integer.parseInt(thtp3.getText()) )
                {
                    tp3.setBackground(Color.red);
                    tp3.setForeground(Color.WHITE);
                }
                else
                {
                    tp3.setBackground(UIManager.getColor("TextField.background"));
                    tp3.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       tp4.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tp4.getText()) < Integer.parseInt(thtp4.getText()) )
                {
                    tp4.setBackground(Color.red);
                    tp4.setForeground(Color.WHITE);
                }
                else
                {
                    tp4.setBackground(UIManager.getColor("TextField.background"));
                    tp4.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       raacA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(raacA.getText()) > 200 )
                {
                    raacA.setBackground(Color.red);
                    raacA.setForeground(Color.WHITE);
                }
                else
                {
                    raacA.setBackground(UIManager.getColor("TextField.background"));
                    raacA.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        iavgA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(iavgA.getText()) > 200 )
                {
                    iavgA.setBackground(Color.red);
                    iavgA.setForeground(Color.WHITE);
                }
                else
                {
                    iavgA.setBackground(UIManager.getColor("TextField.background"));
                    iavgA.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
        
       iinstA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(iinstA.getText()) > 200 )
                {
                    iinstA.setBackground(Color.red);
                    iinstA.setForeground(Color.WHITE);
                }
                else
                {
                    iinstA.setBackground(UIManager.getColor("TextField.background"));
                    iinstA.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       voltA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(voltA.getText()) > 200 )
                {
                    voltA.setBackground(Color.red);
                    voltA.setForeground(Color.WHITE);
                }
                else
                {
                    voltA.setBackground(UIManager.getColor("TextField.background"));
                    voltA.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       tempA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tempA.getText()) > 200 )
                {
                    tempA.setBackground(Color.red);
                    tempA.setForeground(Color.WHITE);
                }
                else
                {
                    tempA.setBackground(UIManager.getColor("TextField.background"));
                    tempA.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       raacB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(raacB.getText()) > 190 )
                {
                    raacB.setBackground(Color.red);
                    raacB.setForeground(Color.WHITE);
                }
                else
                {
                    raacB.setBackground(UIManager.getColor("TextField.background"));
                    raacB.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        iavgB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(iavgB.getText()) > 190 )
                {
                    iavgB.setBackground(Color.red);
                    iavgB.setForeground(Color.WHITE);
                }
                else
                {
                    iavgB.setBackground(UIManager.getColor("TextField.background"));
                    iavgB.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
        
       iinstB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(iinstB.getText()) > 190 )
                {
                    iinstB.setBackground(Color.red);
                    iinstB.setForeground(Color.WHITE);
                }
                else
                {
                    iinstB.setBackground(UIManager.getColor("TextField.background"));
                    iinstB.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       voltB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(voltB.getText()) > 190 )
                {
                    voltB.setBackground(Color.red);
                    voltB.setForeground(Color.WHITE);
                }
                else
                {
                    voltB.setBackground(UIManager.getColor("TextField.background"));
                    voltB.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       tempB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(tempB.getText()) > 190 )
                {
                    tempB.setBackground(Color.red);
                    tempB.setForeground(Color.WHITE);
                }
                else
                {
                    tempB.setBackground(UIManager.getColor("TextField.background"));
                    tempB.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       sx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(sx.getText()) <= 32 )
                {
                    sx.setBackground(Color.red);
                    sx.setForeground(Color.WHITE);
                }
                else
                {
                    sx.setBackground(UIManager.getColor("TextField.background"));
                    sx.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       mx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(mx.getText()) <= 32 )
                {
                    mx.setBackground(Color.red);
                    mx.setForeground(Color.WHITE);
                }
                else
                {
                    mx.setBackground(UIManager.getColor("TextField.background"));
                    mx.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       gx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(gx.getText()) <= 32 )
                {
                    gx.setBackground(Color.red);
                    gx.setForeground(Color.WHITE);
                }
                else
                {
                    gx.setBackground(UIManager.getColor("TextField.background"));
                    gx.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       sy.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(sy.getText()) <= 40 )
                {
                    sy.setBackground(Color.red);
                    sy.setForeground(Color.WHITE);
                }
                else
                {
                    sy.setBackground(UIManager.getColor("TextField.background"));
                    sy.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       my.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(my.getText()) <= 40 )
                {
                    my.setBackground(Color.red);
                    my.setForeground(Color.WHITE);
                }
                else
                {
                    my.setBackground(UIManager.getColor("TextField.background"));
                    my.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       gy.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(gy.getText()) <= 40 )
                {
                    gy.setBackground(Color.red);
                    gy.setForeground(Color.WHITE);
                }
                else
                {
                    gy.setBackground(UIManager.getColor("TextField.background"));
                    gy.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       sz.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(sz.getText()) <= 48 )
                {
                    sz.setBackground(Color.red);
                    sz.setForeground(Color.WHITE);
                }
                else
                {
                    sz.setBackground(UIManager.getColor("TextField.background"));
                    sz.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       mz.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(mz.getText()) <= 48 )
                {
                    mz.setBackground(Color.red);
                    mz.setForeground(Color.WHITE);
                }
                else
                {
                    mz.setBackground(UIManager.getColor("TextField.background"));
                    mz.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
       
       gz.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (Integer.parseInt(gz.getText()) <= 48 )
                {
                    gz.setBackground(Color.red);
                    gz.setForeground(Color.WHITE);
                }
                else
                {
                    gz.setBackground(UIManager.getColor("TextField.background"));
                    gz.setForeground(UIManager.getColor("TextField.foreground"));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
       });
    }
    
    private void createObjects()
    {
        communicator = new Communicator(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Port = new javax.swing.JComboBox();
        Stopbits = new javax.swing.JComboBox();
        Baudrate = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Parity = new javax.swing.JComboBox();
        Databits = new javax.swing.JComboBox();
        closePort = new javax.swing.JButton();
        openPort = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        packet = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        raacA = new javax.swing.JTextField();
        iavgA = new javax.swing.JTextField();
        iinstA = new javax.swing.JTextField();
        voltA = new javax.swing.JTextField();
        tempA = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sx = new javax.swing.JTextField();
        mx = new javax.swing.JTextField();
        gx = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        sz = new javax.swing.JTextField();
        mz = new javax.swing.JTextField();
        gz = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sy = new javax.swing.JTextField();
        my = new javax.swing.JTextField();
        gy = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tp4 = new javax.swing.JTextField();
        tp2 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        tp1 = new javax.swing.JTextField();
        tp3 = new javax.swing.JTextField();
        thtp1 = new javax.swing.JTextField();
        thtp2 = new javax.swing.JTextField();
        thtp4 = new javax.swing.JTextField();
        thtp3 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        rx_c = new javax.swing.JTextField();
        tx_c = new javax.swing.JTextField();
        RSSI_val = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lastp = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        failp = new javax.swing.JTextField();
        RSSI = new javax.swing.JProgressBar();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        raacB = new javax.swing.JTextField();
        iavgB = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        iinstB = new javax.swing.JTextField();
        voltB = new javax.swing.JTextField();
        tempB = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        vp4 = new javax.swing.JTextField();
        vp2 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        vp1 = new javax.swing.JTextField();
        vp3 = new javax.swing.JTextField();
        thvp1 = new javax.swing.JTextField();
        thvp2 = new javax.swing.JTextField();
        thvp4 = new javax.swing.JTextField();
        thvp3 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        payload = new javax.swing.JTextField();
        ant = new javax.swing.JTextField();
        amp = new javax.swing.JTextField();
        ram = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        gsc = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        status = new javax.swing.JTextArea();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        telec = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Telemetry");
        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(500, 500));

        jPanel1.setMinimumSize(new java.awt.Dimension(1160, 643));
        jPanel1.setPreferredSize(new java.awt.Dimension(1155, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Connection", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Stop Bits");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, -1));

        jLabel2.setText("Port");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        jLabel3.setText("Baud Rate");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        Port.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COM3", "COM4", "COM8" }));
        Port.setSelectedIndex(2);
        Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PortActionPerformed(evt);
            }
        });
        jPanel4.add(Port, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        Stopbits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        jPanel4.add(Stopbits, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 40, -1));

        Baudrate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4800", "9600" }));
        Baudrate.setSelectedIndex(1);
        jPanel4.add(Baudrate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel4.setText("Parity Bits");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel5.setText("Data Bits");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        Parity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "2", "4" }));
        jPanel4.add(Parity, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        Databits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "16", "32" }));
        jPanel4.add(Databits, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        closePort.setBackground(Color.DARK_GRAY);
        closePort.setForeground(Color.WHITE);
        closePort.setContentAreaFilled(false);
        closePort.setOpaque(true);
        closePort.setText("Close Port");
        closePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closePortActionPerformed(evt);
            }
        });
        jPanel4.add(closePort, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        openPort.setBackground(Color.DARK_GRAY);
        openPort.setForeground(Color.WHITE);
        openPort.setContentAreaFilled(false);
        openPort.setOpaque(true);
        openPort.setText("Open Port");
        openPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPortActionPerformed(evt);
            }
        });
        jPanel4.add(openPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 419, 119));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Packet Log", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        packet.setColumns(20);
        packet.setRows(5);
        jScrollPane2.setViewportView(packet);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 480, 290));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Battry A", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(raacA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 8, 90, -1));

        iavgA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iavgAActionPerformed(evt);
            }
        });
        jPanel21.add(iavgA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 90, -1));
        jPanel21.add(iinstA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 68, 90, -1));
        jPanel21.add(voltA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 98, 90, -1));
        jPanel21.add(tempA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 128, 90, -1));

        jLabel30.setText("RAAC");
        jPanel21.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel31.setText("IAVG");
        jPanel21.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel34.setText("IINST");
        jPanel21.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel35.setText("VOLT");
        jPanel21.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel36.setText("TEMP");
        jPanel21.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel43.setText("mAh");
        jPanel21.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel45.setText("mA");
        jPanel21.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel46.setText("mA");
        jPanel21.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel48.setText("Vdc");
        jPanel21.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jLabel51.setText("C");
        jPanel21.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 10, -1));

        jPanel6.add(jPanel21, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 230, 180));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "X-Axis", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Sun Vector X");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel7.setText("Magnatometer X");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel8.setText("Gyroscope X");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel2.add(sx, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 8, 60, -1));

        mx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mxActionPerformed(evt);
            }
        });
        jPanel2.add(mx, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 38, 60, -1));
        jPanel2.add(gx, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 68, 60, -1));

        jLabel9.setText("nT");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel10.setText("deg/s");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jPanel9.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 230, 123));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Z-Axis", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setText("Sun Vector Z");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel17.setText("Magnatometer Z");
        jPanel14.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel18.setText("Gyroscope Z");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel19.setText("nT");
        jPanel14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel20.setText("deg/s");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));
        jPanel14.add(sz, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 8, 60, -1));

        mz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mzActionPerformed(evt);
            }
        });
        jPanel14.add(mz, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 38, 60, -1));
        jPanel14.add(gz, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 68, 60, -1));

        jPanel11.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 520, 250, 123));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Y-Axis", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("Sun Vector Y");
        jPanel13.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel12.setText("Magnatometer Y");
        jPanel13.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel13.setText("Gyroscope Y");
        jPanel13.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel14.setText("nT");
        jPanel13.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel15.setText("deg/s");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));
        jPanel13.add(sy, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 8, 60, -1));

        my.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myActionPerformed(evt);
            }
        });
        jPanel13.add(my, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 38, 60, -1));
        jPanel13.add(gy, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 68, 60, -1));

        jPanel10.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 520, 230, 123));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Temprature", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText("Degree");
        jPanel15.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel28.setText("Threshold");
        jPanel15.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        tp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tp4ActionPerformed(evt);
            }
        });
        jPanel15.add(tp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        tp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel15.add(tp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 80, -1));

        jLabel56.setText("P1");
        jPanel15.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 34, -1, -1));

        jLabel57.setText("P2");
        jPanel15.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 64, -1, -1));

        jLabel58.setText("P3");
        jPanel15.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 94, -1, -1));

        jLabel59.setText("P4");
        jPanel15.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 126, -1, -1));

        jLabel60.setText("Payload");
        jPanel15.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tp1ActionPerformed(evt);
            }
        });
        jPanel15.add(tp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 80, -1));

        tp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tp3ActionPerformed(evt);
            }
        });
        jPanel15.add(tp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 80, -1));

        thtp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thtp1.setText("50");
        thtp1.setEnabled(false);
        thtp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thtp1ActionPerformed(evt);
            }
        });
        jPanel15.add(thtp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 80, -1));

        thtp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thtp2.setText("40");
        thtp2.setEnabled(false);
        jPanel15.add(thtp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 80, -1));

        thtp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thtp4.setText("55");
        thtp4.setEnabled(false);
        thtp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thtp4ActionPerformed(evt);
            }
        });
        jPanel15.add(thtp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 80, -1));

        thtp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thtp3.setText("30");
        thtp3.setEnabled(false);
        thtp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thtp3ActionPerformed(evt);
            }
        });
        jPanel15.add(thtp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 80, -1));

        jPanel7.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 250, 180));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Communication", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("RSSI");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel22.setText("Comm Tx Byte Count");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel23.setText("Failed Packets");
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel24.setText("Last Packet Received");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel12.add(rx_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 68, 60, -1));

        tx_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_cActionPerformed(evt);
            }
        });
        jPanel12.add(tx_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 38, 60, -1));
        jPanel12.add(RSSI_val, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 8, 60, -1));

        jLabel25.setText("Comm Rx Byte Count");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel12.add(lastp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 98, 60, -1));

        jLabel26.setText("dB");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));
        jPanel12.add(failp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 128, 60, -1));

        RSSI.setValue(60);
        RSSI.setPreferredSize(new java.awt.Dimension(140, 14));
        jPanel12.add(RSSI, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 8, 127, 20));

        jPanel8.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 286, 180));

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Battry B", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setText("RAAC");
        jPanel20.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel20.add(raacB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 8, 90, -1));

        iavgB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iavgBActionPerformed(evt);
            }
        });
        jPanel20.add(iavgB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 90, -1));

        jLabel38.setText("IAVG");
        jPanel20.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel39.setText("IINST");
        jPanel20.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel20.add(iinstB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 68, 90, -1));
        jPanel20.add(voltB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 98, 90, -1));
        jPanel20.add(tempB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 128, 90, -1));

        jLabel40.setText("TEMP");
        jPanel20.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel41.setText("VOLT");
        jPanel20.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel42.setText("mAh");
        jPanel20.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel44.setText("mA");
        jPanel20.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel47.setText("mA");
        jPanel20.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel49.setText("Vdc");
        jPanel20.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jLabel50.setText("C");
        jPanel20.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 10, -1));

        jPanel19.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 230, 180));

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Voltage", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setText("Voltage");
        jPanel24.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel62.setText("Threshold");
        jPanel24.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        vp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel24.add(vp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        vp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel24.add(vp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 80, -1));

        jLabel63.setText("P1");
        jPanel24.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 34, -1, -1));

        jLabel64.setText("P2");
        jPanel24.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 64, -1, -1));

        jLabel65.setText("P3");
        jPanel24.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 94, -1, -1));

        jLabel66.setText("P4");
        jPanel24.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 126, -1, -1));

        jLabel67.setText("Payload");
        jPanel24.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        vp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel24.add(vp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 80, -1));

        vp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel24.add(vp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 80, -1));

        thvp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thvp1.setText("4");
        thvp1.setEnabled(false);
        thvp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thvp1ActionPerformed(evt);
            }
        });
        jPanel24.add(thvp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 80, -1));

        thvp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thvp2.setText("5");
        thvp2.setEnabled(false);
        jPanel24.add(thvp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 80, -1));

        thvp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thvp4.setText("8");
        thvp4.setEnabled(false);
        thvp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thvp4ActionPerformed(evt);
            }
        });
        jPanel24.add(thvp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 80, -1));

        thvp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        thvp3.setText("6");
        thvp3.setEnabled(false);
        thvp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thvp3ActionPerformed(evt);
            }
        });
        jPanel24.add(thvp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 80, -1));

        jPanel23.add(jPanel24, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 250, 180));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "System Status Flags", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        payload.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel22.add(payload, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 8, 90, -1));

        ant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antActionPerformed(evt);
            }
        });
        jPanel22.add(ant, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 90, -1));

        amp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel22.add(amp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 68, 90, -1));

        ram.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel22.add(ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 98, 90, -1));

        jLabel53.setText("Ant. Dep.");
        jPanel22.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel54.setText("AMP");
        jPanel22.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel55.setText("RAM");
        jPanel22.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel52.setText("Payload");
        jPanel22.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel16.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 180, 180));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Clocks and Timers", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setText("Satellite Clock");
        jPanel18.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, -1, -1));

        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 38, 160, -1));

        jLabel33.setText("Ground System Clock");
        jPanel18.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        gsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gscActionPerformed(evt);
            }
        });
        jPanel18.add(gsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 8, 160, -1));

        jPanel17.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 310, 120));

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Status Log", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel25.setLayout(new java.awt.BorderLayout());

        status.setColumns(20);
        status.setRows(5);
        jScrollPane3.setViewportView(status);

        jPanel25.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 480, 200));

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Transmitting Telecommand", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 0, 51))); // NOI18N
        jPanel26.setLayout(new java.awt.BorderLayout());

        telec.setColumns(20);
        telec.setRows(5);
        jScrollPane4.setViewportView(telec);

        jPanel26.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, 480, -1));

        jButton1.setBackground(Color.DARK_GRAY);
        jButton1.setForeground(Color.WHITE);
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.setText("Transmit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 620, 170, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PortActionPerformed

    private void closePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closePortActionPerformed

        closePort.setEnabled(false);
        closePort.setBackground(new Color(138,138,138));
        closePort.setContentAreaFilled(false);
        closePort.setOpaque(true);
        openPort.setEnabled(true);
        openPort.setBackground(Color.DARK_GRAY);
        openPort.setContentAreaFilled(false);
        openPort.setOpaque(true);
        communicator.disconnect();
    }//GEN-LAST:event_closePortActionPerformed

    private void openPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPortActionPerformed

        openPort.setEnabled(false);
        openPort.setBackground(new Color(138,138,138));
        openPort.setContentAreaFilled(false);
        openPort.setOpaque(true);
        closePort.setEnabled(true);
        closePort.setBackground(Color.DARK_GRAY);
        closePort.setContentAreaFilled(false);
        closePort.setOpaque(true);
        communicator.connect();
        if (communicator.getConnected() == true)
        {
            if (communicator.initIOStream() == true)
            {
                communicator.initListener();
            }
            if (communicator.initIOStream() == false)
            {
                //communicator.nodata();
            }
        }
    }//GEN-LAST:event_openPortActionPerformed

    private void mxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mxActionPerformed

    private void myActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myActionPerformed

    private void mzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mzActionPerformed

    private void tx_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_cActionPerformed

    private void tp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tp4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tp4ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void gscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gscActionPerformed

    }//GEN-LAST:event_gscActionPerformed

    private void iavgAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iavgAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iavgAActionPerformed

    private void iavgBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iavgBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iavgBActionPerformed

    private void antActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_antActionPerformed

    private void tp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tp1ActionPerformed

    private void tp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tp3ActionPerformed

    private void thtp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thtp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thtp1ActionPerformed

    private void thtp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thtp4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thtp4ActionPerformed

    private void thtp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thtp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thtp3ActionPerformed

    private void thvp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thvp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thvp1ActionPerformed

    private void thvp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thvp4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thvp4ActionPerformed

    private void thvp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thvp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thvp3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        communicator.writeData(telec.getText());
        telec.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Telemetry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Telemetry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Telemetry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Telemetry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Telemetry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox Baudrate;
    private javax.swing.JComboBox Databits;
    private javax.swing.JComboBox Parity;
    public javax.swing.JComboBox Port;
    public javax.swing.JProgressBar RSSI;
    public javax.swing.JTextField RSSI_val;
    private javax.swing.JComboBox Stopbits;
    public javax.swing.JTextField amp;
    public javax.swing.JTextField ant;
    private javax.swing.JButton closePort;
    public javax.swing.JTextField failp;
    public javax.swing.JTextField gsc;
    public javax.swing.JTextField gx;
    public javax.swing.JTextField gy;
    public javax.swing.JTextField gz;
    public javax.swing.JTextField iavgA;
    public javax.swing.JTextField iavgB;
    public javax.swing.JTextField iinstA;
    public javax.swing.JTextField iinstB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField25;
    public javax.swing.JTextField lastp;
    public javax.swing.JTextField mx;
    public javax.swing.JTextField my;
    public javax.swing.JTextField mz;
    private javax.swing.JButton openPort;
    public javax.swing.JTextArea packet;
    public javax.swing.JTextField payload;
    public javax.swing.JTextField raacA;
    public javax.swing.JTextField raacB;
    public javax.swing.JTextField ram;
    public javax.swing.JTextField rx_c;
    public javax.swing.JTextArea status;
    public javax.swing.JTextField sx;
    public javax.swing.JTextField sy;
    public javax.swing.JTextField sz;
    public javax.swing.JTextArea telec;
    public javax.swing.JTextField tempA;
    public javax.swing.JTextField tempB;
    public javax.swing.JTextField thtp1;
    public javax.swing.JTextField thtp2;
    public javax.swing.JTextField thtp3;
    public javax.swing.JTextField thtp4;
    public javax.swing.JTextField thvp1;
    public javax.swing.JTextField thvp2;
    public javax.swing.JTextField thvp3;
    public javax.swing.JTextField thvp4;
    public javax.swing.JTextField tp1;
    public javax.swing.JTextField tp2;
    public javax.swing.JTextField tp3;
    public javax.swing.JTextField tp4;
    public javax.swing.JTextField tx_c;
    public javax.swing.JTextField voltA;
    public javax.swing.JTextField voltB;
    public javax.swing.JTextField vp1;
    public javax.swing.JTextField vp2;
    public javax.swing.JTextField vp3;
    public javax.swing.JTextField vp4;
    // End of variables declaration//GEN-END:variables
}
