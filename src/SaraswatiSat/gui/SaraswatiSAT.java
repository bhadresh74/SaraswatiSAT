package SaraswatiSat.gui;

import SaraswatiSat.covergae.CoverageAnalyzer;
import SaraswatiSat.covergae.SaraswatiSATTimeDependent;
import SaraswatiSat.objects.AbstractSatellite;
import SaraswatiSat.objects.CustomSatellite;
import SaraswatiSat.objects.GroundStation;
import SaraswatiSat.objects.SatelliteTleSGP4;
import SaraswatiSat.utilities.TLE;
import name.gano.astro.bodies.Sun;
import name.gano.astro.time.Time;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.Timer;
import javax.swing.event.InternalFrameEvent;
import bsh.Interpreter;
import bsh.util.JConsole;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhadresh
 */
public class SaraswatiSAT extends javax.swing.JFrame  implements InternalFrameListener, WindowListener, Serializable
{
    private String versionString = " Version 1.0";
    private Hashtable<String,AbstractSatellite> satHash = new Hashtable<>();
    private Hashtable<String,GroundStation> gsHash = new Hashtable<String,GroundStation>();
    Time currentJulianDate = new Time();
    private SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS z");
    private SimpleDateFormat dateformatShort1 = new SimpleDateFormat("dd MMM y H:m:s.S z");
    private SimpleDateFormat dateformatShort2 = new SimpleDateFormat("dd MMM y H:m:s z");
    Vector<SatPropertyPanel> satPropWindowVec = new Vector<SatPropertyPanel>();
    Vector<SaraswatiSATTimeDependent> timeDependentObjects = new Vector<SaraswatiSATTimeDependent>();
    Vector<navigate> twoDWindowVec = new Vector<navigate>();
    private Sun sun;
    private name.gano.astro.time.Time scenarioEpochDate = new name.gano.astro.time.Time();
    double[] timeStepSpeeds = new double[] {0.0001,0.001,0.01,0.1,0.25,0.5,1.0,2.0,5.0,10.0,30.0,60.0,300.0,600.0,1800.0,3600.0,43200.0,86400.0,604800.0,2419200.0,31556926.0};
    private int currentTimeStepSpeedIndex = 11;
    TimeZone localTZ = TimeZone.getDefault();
    
    JConsole commandConsole = new JConsole();
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    Timer messageTimer;
    Timer busyIconTimer;
    public Interpreter beanShellInterp = new Interpreter(commandConsole);
    
    private Timer playTimer;
    private boolean stopHit = false;
    private int realTimeAnimationRefreshRateMs = 1000; // refresh rate for real time animation
    private int nonRealTimeAnimationRefreshRateMs = 50; // refresh rate for non-real time animation
    private int animationRefreshRateMs = nonRealTimeAnimationRefreshRateMs; // (current)Milliseconds ** this should be an option somewhere!! - determines CPU used in animation
    private double animationSimStepSeconds = 1.0; // dt in Days per animation step/time update
    int currentPlayDirection = 0;
    private long lastFPSms;
    private double fpsAnimation;
    JObjectListPanel objListPanel = new JObjectListPanel(satHash, gsHash, this);
    private CoverageAnalyzer coverageAnalyzer;
    
    SaraswatiSAT parentApp;
    String line1;
    String line2;
    String line3;
    public SaraswatiSAT() {

        sun = new Sun(currentJulianDate.getMJD());
        CreateMWindow();
        setContentPane(desktop_pane);
        initComponents();
         
        // first call to update time to current time:
        currentJulianDate.update2CurrentTime(); //update();// = getCurrentJulianDate(); // ini time
        
        // just a little touch up -- remove the milliseconds from the time
        int mil = currentJulianDate.get(name.gano.astro.time.Time.MILLISECOND);
        currentJulianDate.add(name.gano.astro.time.Time.MILLISECOND,1000-mil); // remove the milliseconds (so it shows an even second)
        
        // set time string format
        currentJulianDate.setDateFormat(dateformat);
        scenarioEpochDate.setDateFormat(dateformat);
        
        updateTime(); // update plots
                
        // update gui with timestep
        updateTimeStepsDataGUI();
        
        localTimeZoneCheckBox.doClick();
         
        int messageTimeout = 60000; // 1 minute
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        
        idleIcon = new javax.swing.ImageIcon(getClass().getResource("/icons/busyicons/idle-icon.png"));
        
        //busy-icon0.png
        for(int i=0;i<15;i++)
        {
           busyIcons[i] = new javax.swing.ImageIcon(getClass().getResource("/icons/busyicons/busy-icon"+i+".png")); 
        }
                
        setStatusMessage("Welcome to SaraswatiSAT " + versionString + " developed by bhadresh dhanani");
        
//        try
//        {
//                        
//            // name completion 
//            final NameCompletionTable nct = new NameCompletionTable();
//            nct.add(beanShellInterp.getNameSpace());
//            commandConsole.setNameCompletion(nct);
//        
//            // start the interperator in its own thread
//            final Thread thread = new Thread(beanShellInterp, "BeanShell");
//            thread.setDaemon(true);
//            thread.start();
//            
//            //new Thread( beanShellInterp ).start();
//            beanShellInterp.set("SaraswatiSAT", this);
//            
//            // set the scope so it is in this object (no need to use jsattrak. all the time)
//            // doesn't work this way?
//            //beanShellInterp.eval("setNameSpace(jsattrak.namespace)");
//            
//            
//            // set this so closing the bean shell Desktop doesn't close the whole app
//            beanShellInterp.eval("bsh.system.shutdownOnExit = false;");
//            
//            // so bean shell returns values upon evaluation
//            // doesn't seem to work
//            //beanShellInterp.eval("show();");
//            
//        }
//        catch(Exception e)
//        {
//            System.out.println("Error saving main object to bean shell:" + e.toString());
//        }
        
        try
        {
            
//                // check for a startup script file and run it if it exists
//                if( (new File("startup.bsh")).exists())
//                {
//                    // run startup script
//                    runScript("startup.bsh");
//                }
//                else // default scene - ISS
//                {
                    // default startup scene
                     addSat2ListByName("ISS (ZARYA)             ");
                    satHash.get("ISS (ZARYA)             ").setThreeDModelPath("isscomplete/iss_complete.3ds");
//                  }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        
//        this.setSize(this.getWidth(), 650);
//        
//        // display window in the center of the screen
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        Point p = this.getLocation();
//        int x= (dim.width - this.getWidth())/2;
//        int y = (dim.height- this.getHeight())/2;
//        //System.out.println("h" + this.getHeight());
//        this.setBounds(x, y, this.getWidth(), this.getHeight()+1);      
        
        // Debug for coverage module - for now create it and add it to 2D window
        if (false)
        {
            coverageAnalyzer = new CoverageAnalyzer(currentJulianDate); // start after first step
            //CoverageAnalyzer coverageAnalyzer = new CoverageAnalyzer(currentJulianDate,timeStepSpeeds[currentTimeStepSpeedIndex]/(24.0*60*60),satHash);
            twoDWindowVec.get(0).addRenderableObject(coverageAnalyzer); // add it to the panel
            coverageAnalyzer.addSatToCoverageAnaylsis("ISS (ZARYA)             ");
            //coverageAnalyzer.addSatToCoverageAnaylsis("test");
            timeDependentObjects.add(coverageAnalyzer); // add object to time updates
            twoDWindowVec.get(0).setShowLatLonLines(false);
            twoDWindowVec.get(0).setDrawSun(false);
            twoDWindowVec.get(0).setShowDateTime(false);
        }
        
        // fire resize function - to correct "dissapearing or transparent windows" issues
        int sizeJump = 1;
        this.setSize(this.getSize().width+sizeJump, this.getSize().height+sizeJump);
        this.setSize(this.getSize().width-sizeJump, this.getSize().height-sizeJump);
        LoadInitial();
        // DEBUG - testing earht lights
        //this.twoDWindowVec.get(0).setShowEarthLightsMask(true);
        
        // for some reason nimbus has to be reapplied to work correctly
        
    } //constructor
  
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_window = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        dateTextField = new javax.swing.JTextField();
        realTimeModeCheckBox = new javax.swing.JCheckBox();
        localTimeZoneCheckBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        desktop_pane = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        orbit_no = new javax.swing.JTextField();
        alt = new javax.swing.JTextField();
        lat = new javax.swing.JTextField();
        lon = new javax.swing.JTextField();
        y = new javax.swing.JTextField();
        tle_age = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        x = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        z = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        dx = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        dy = new javax.swing.JTextField();
        dz = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        semimajor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        eccen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        meanano = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        incl = new javax.swing.JTextField();
        raan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        aop = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Menubar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        Windows = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        Navigation = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        receiveTele = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SaraswatiSAT");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        main_window.setForeground(java.awt.Color.darkGray);

        playButton.setBackground(Color.DARK_GRAY);
        playButton.setForeground(Color.WHITE);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);
        playButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        playButton.setText("Start");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        jButton2.setBackground(Color.DARK_GRAY);
        jButton2.setForeground(Color.WHITE);
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton2.setText("Capture Position");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        stopButton.setBackground(Color.DARK_GRAY);
        stopButton.setForeground(Color.WHITE);
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(true);
        stopButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        dateTextField.setText("Date/Time");
        dateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextFieldActionPerformed(evt);
            }
        });

        realTimeModeCheckBox.setText("Real Time");
        realTimeModeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realTimeModeCheckBoxActionPerformed(evt);
            }
        });

        localTimeZoneCheckBox.setText("Local Time Zone");
        localTimeZoneCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localTimeZoneCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playButton)
                .addGap(18, 18, 18)
                .addComponent(stopButton)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(realTimeModeCheckBox)
                .addGap(18, 18, 18)
                .addComponent(localTimeZoneCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(jButton2)
                    .addComponent(stopButton)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(realTimeModeCheckBox)
                    .addComponent(localTimeZoneCheckBox))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setText("Status bar");
        statusLabel.setPreferredSize(new java.awt.Dimension(60, 16));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        desktop_pane.setBackground(new java.awt.Color(102, 102, 102));
        desktop_pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout desktop_paneLayout = new javax.swing.GroupLayout(desktop_pane);
        desktop_pane.setLayout(desktop_paneLayout);
        desktop_paneLayout.setHorizontalGroup(
            desktop_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktop_paneLayout.setVerticalGroup(
            desktop_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Satellite Information");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 2, 143, 20));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 28, 317, -1));

        orbit_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orbit_noActionPerformed(evt);
            }
        });
        jPanel4.add(orbit_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 36, 140, -1));
        jPanel4.add(alt, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 114, 140, -1));
        jPanel4.add(lat, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 62, 140, -1));
        jPanel4.add(lon, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 88, 140, -1));
        jPanel4.add(y, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 201, 140, -1));

        tle_age.setText("TLE Age [Days]");
        jPanel4.add(tle_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 39, -1, -1));

        jLabel6.setText("Altitude [m]");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 117, -1, -1));

        jLabel7.setText("Latitude [Deg]");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 65, -1, -1));

        jLabel8.setText("Longitude [Deg]");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 91, -1, -1));

        jLabel9.setText("Y [m]");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, -1, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Position and Velocity");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 145, -1, -1));

        x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xActionPerformed(evt);
            }
        });
        jPanel4.add(x, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 175, 140, -1));

        jLabel11.setText("Z [m]");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 230, -1, -1));
        jPanel4.add(z, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 227, 140, -1));

        jSeparator2.setBackground(java.awt.Color.darkGray);
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 139, 317, -1));
        jPanel4.add(dx, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 253, 140, -1));

        jLabel15.setText("dy/dt [m/s]");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 282, -1, -1));
        jPanel4.add(dy, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 279, 140, -1));
        jPanel4.add(dz, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 305, 140, -1));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 168, 317, -1));

        jLabel18.setText("X [m]");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 178, -1, -1));

        jLabel20.setText("dz/dt [m/s]");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 307, -1, -1));

        jLabel21.setText("dx/dt [m/s]");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 256, -1, -1));

        jPanel2.setBackground(java.awt.Color.darkGray);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 317, 30));

        jPanel6.setBackground(java.awt.Color.darkGray);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 140, 317, 28));

        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Keplarian Elements");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 2, 121, 17));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 27, 317, -1));

        jLabel12.setText("Semimajor Axis (a) [m]");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, -1, -1));

        semimajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semimajorActionPerformed(evt);
            }
        });
        jPanel5.add(semimajor, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 35, 140, -1));

        jLabel13.setText("Eccentricity (e)");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, 80, -1));

        eccen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eccenActionPerformed(evt);
            }
        });
        jPanel5.add(eccen, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 61, 140, -1));

        jLabel14.setText("Inclination (i) [deg]");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 90, -1, -1));

        meanano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meananoActionPerformed(evt);
            }
        });
        jPanel5.add(meanano, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 166, 140, -1));

        jLabel16.setText("RAAN");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, -1, -1));

        jLabel17.setText("Mean Anomaly [m] [Deg]");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 169, -1, -1));

        incl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclActionPerformed(evt);
            }
        });
        jPanel5.add(incl, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 87, 140, -1));

        raan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raanActionPerformed(evt);
            }
        });
        jPanel5.add(raan, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 113, 140, -1));

        jLabel19.setText("Arg of pericenter [deg]");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 143, -1, -1));

        aop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aopActionPerformed(evt);
            }
        });
        jPanel5.add(aop, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 140, 140, -1));

        jPanel7.setBackground(java.awt.Color.darkGray);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 318, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout main_windowLayout = new javax.swing.GroupLayout(main_window);
        main_window.setLayout(main_windowLayout);
        main_windowLayout.setHorizontalGroup(
            main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_windowLayout.createSequentialGroup()
                .addGroup(main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_windowLayout.createSequentialGroup()
                        .addGroup(main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(main_windowLayout.createSequentialGroup()
                                .addGroup(main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(desktop_pane)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_windowLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        main_windowLayout.setVerticalGroup(
            main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_windowLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(main_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_windowLayout.createSequentialGroup()
                        .addComponent(desktop_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(main_windowLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Menubar.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));

        jMenu2.setText("Prediction");

        jMenu1.setText("Today");

        jMenuItem7.setText("Remaining");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Complete");
        jMenu1.add(jMenuItem8);

        jMenu2.add(jMenu1);

        jMenuItem4.setText("Tomorrow");
        jMenu2.add(jMenuItem4);

        jMenuItem6.setText("Yesterday");
        jMenu2.add(jMenuItem6);

        Menubar.add(jMenu2);

        Windows.setText("Windows");
        Windows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WindowsActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Keplar Elements");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Windows.add(jMenuItem3);

        jMenuItem9.setText("Load TLE");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        Windows.add(jMenuItem9);

        Navigation.setText("Navigation");
        Navigation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NavigationActionPerformed(evt);
            }
        });
        Windows.add(Navigation);

        Menubar.add(Windows);

        jMenu3.setText("Communication");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        receiveTele.setText("Telemetry & Telecommand");
        receiveTele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveTeleActionPerformed(evt);
            }
        });
        jMenu3.add(receiveTele);

        Menubar.add(jMenu3);

        Help.setText("Help");

        jMenuItem1.setText("About");
        Help.add(jMenuItem1);

        jMenuItem2.setText("Check for updates");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Help.add(jMenuItem2);

        Menubar.add(Help);

        setJMenuBar(Menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_window, javax.swing.GroupLayout.PREFERRED_SIZE, 1108, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_window, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orbit_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orbit_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orbit_noActionPerformed

    private void xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xActionPerformed

    private void semimajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semimajorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semimajorActionPerformed

    private void eccenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eccenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eccenActionPerformed

    private void meananoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meananoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meananoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed

        playScenario();
    }//GEN-LAST:event_playButtonActionPerformed

     public void playScenario()
    {
        currentPlayDirection = 1; // forwards
        runAnimation(); // perform animation
    } // playScenario
     
    private void runAnimation()
    {
        // useses globally set animation direction and starts animation
        playButton.setEnabled(false);
        playButton.setBackground(new Color(138,138,138));
        playButton.setForeground(Color.WHITE);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);                
        stopButton.setBackground(Color.DARK_GRAY);
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(true);
        stopButton.setEnabled(true);
        jButton2.setBackground(Color.DARK_GRAY);
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.setEnabled(true);
        jButton2.setEnabled(true);
        setStatusMessage("Real time tracking has been activated");
        //stepForwardTimeButton.setEnabled(false);
        //stepBackButton.setEnabled(false);
        //playBackButton.setEnabled(false);
        //resetTimeButton.setEnabled(false);
        
        stopHit = false;
        //Create a timer.
        lastFPSms = System.currentTimeMillis();
        playTimer = new Timer(animationRefreshRateMs, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                // take one time step in the aimation
                updateTime(); // animate
                long stopTime = System.currentTimeMillis();
                
                fpsAnimation = 1.0 / ((stopTime-lastFPSms)/1000.0); // fps calculation
                lastFPSms = stopTime;
                // goal FPS:
                //fpsAnimation = 1.0 / (animationRefreshRateMs/1000.0);
                
                if (stopHit)
                {
                    playTimer.stop();
                    resetAnimationIcons();                    
                }
                
                // SEG - if update took a long time reduce the timers repeat interval
                                
            }
        });
        playTimer.setRepeats(true);
        playTimer.start();
    } // runAnimation
    
    public void resetAnimationIcons()
    {
        if( realTimeModeCheckBox.isSelected() )
        {
            // real time mode
            playButton.setEnabled(true);
//            stepForwardTimeButton.setEnabled(true);
//            stepBackButton.setEnabled(false);
//            playBackButton.setEnabled(false);
//            resetTimeButton.setEnabled(false);
        }
        else
        {   // non-real time mode
            playButton.setEnabled(true);
//            stepForwardTimeButton.setEnabled(true);
//            stepBackButton.setEnabled(true);
//            playBackButton.setEnabled(true);
//            resetTimeButton.setEnabled(true);
        }
        
        
    } // resetAnimationIcons
    
     
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        TLE_update tle = new TLE_update();
        tle.setAlwaysOnTop(true);
        tle.setLocation( 500, 250);
        tle.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void WindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WindowsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WindowsActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void NavigationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NavigationActionPerformed

        createinitialwindow().setLocation(0,1);
    }//GEN-LAST:event_NavigationActionPerformed

    private void dateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextFieldActionPerformed

        // save old time
        
        double prevJulDate = currentJulianDate.getJulianDate();
        // enter hit in date/time box...
        System.out.println("Date Time Changed");
        
        GregorianCalendar currentTimeDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        //or
        //GregorianCalendar currentTimeDate = new GregorianCalendar();
        
        boolean dateAccepted = true; // assume date valid at first
        try
        {
            currentTimeDate.setTime( dateformatShort1.parse(dateTextField.getText()) );
            dateTextField.setText(dateformat.format(currentTimeDate.getTime()));
        }
        catch(Exception e2)
        {
            try
            {
                // try reading without the milliseconds
                currentTimeDate.setTime( dateformatShort2.parse(dateTextField.getText()) );
                dateTextField.setText(dateformat.format(currentTimeDate.getTime()));
            }
            catch(Exception e3)
            {
                // bad date input put back the old date string
                dateTextField.setText(dateformat.format(currentTimeDate.getTime()));
                dateAccepted = false;
                System.out.println(" -- Rejected");
            } // catch 2
                        
        } // catch 1
        
        if(dateAccepted)
        {
            // date entered was good...
            System.out.println(" -- Accepted");
            
            // save
            currentJulianDate.set( currentTimeDate.getTimeInMillis() );
//            currentJulianDate.set(currentTimeDate.get(Calendar.YEAR),
//                                  currentTimeDate.get(Calendar.MONTH),
//                                  currentTimeDate.get(Calendar.DATE),
//                                  currentTimeDate.get(Calendar.HOUR_OF_DAY),
//                                  currentTimeDate.get(Calendar.MINUTE),
//                                  currentTimeDate.get(Calendar.SECOND));
                    
            // check that time change wasn't too great for resetting ground track
            double timeDiffDays = Math.abs(currentJulianDate.getJulianDate()-prevJulDate); // in days
            checkTimeDiffResetGroundTracks(timeDiffDays);
                        
            // update maps ----------------
            // set animation direction = 0
            currentPlayDirection = 0;
            // update graphics
            updateTime();
        }      
    }//GEN-LAST:event_dateTextFieldActionPerformed

    private void localTimeZoneCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localTimeZoneCheckBoxActionPerformed

        // Set the formatting timezone
        if(localTimeZoneCheckBox.isSelected())
        {
            // local time zone
            currentJulianDate.setTzStringFormat(localTZ);
            // epoch
            scenarioEpochDate.setTzStringFormat(localTZ);
        }
        else
        {
            // UTC
            currentJulianDate.setTzStringFormat( TimeZone.getTimeZone("UTC") );
            // epoch
            scenarioEpochDate.setTzStringFormat( TimeZone.getTimeZone("UTC") );
        }
        
        // update date box:
        dateTextField.setText( currentJulianDate.getDateTimeStr() );
    }//GEN-LAST:event_localTimeZoneCheckBoxActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

        stopAnimation();
        setStatusMessage("Real time tracking has been stopped");
        stopButton.setEnabled(false);
        stopButton.setBackground(new Color(138,138,138));
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(true);
        jButton2.setEnabled(false);
        jButton2.setBackground(Color.DARK_GRAY);
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.setEnabled(true);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setForeground(Color.WHITE);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);
        playButton.setEnabled(true);
    }//GEN-LAST:event_stopButtonActionPerformed

    private void realTimeModeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeModeCheckBoxActionPerformed

    }//GEN-LAST:event_realTimeModeCheckBoxActionPerformed

    private void receiveTeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveTeleActionPerformed

        Telemetry tele = new Telemetry(this);
        tele.setAlwaysOnTop(true);
        tele.setLocation( 10, 10);
        tele.setVisible(true);
        tele.setAlwaysOnTop( false );
        Dimension d = new Dimension(1260,690);
        tele.setPreferredSize(d);
        tele.pack();
    }//GEN-LAST:event_receiveTeleActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jButton2.setEnabled(false);
        jButton2.setBackground(new Color(138,138,138));
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setForeground(Color.WHITE);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);
        playButton.setEnabled(true);
        stopButton.setBackground(Color.DARK_GRAY);
        stopButton.setForeground(Color.WHITE);
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(true);
        stopButton.setEnabled(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclActionPerformed

    private void raanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_raanActionPerformed

    private void aopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aopActionPerformed

     public void stopAnimation()
    {
        stopHit = true; // set flag for next animation step
    }
     
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    

    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SaraswatiSAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaraswatiSAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaraswatiSAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaraswatiSAT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
       /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SaraswatiSAT().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Help;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JMenuItem Navigation;
    private javax.swing.JMenu Windows;
    public javax.swing.JTextField alt;
    public javax.swing.JTextField aop;
    public javax.swing.JTextField dateTextField;
    public javax.swing.JDesktopPane desktop_pane;
    public javax.swing.JTextField dx;
    public javax.swing.JTextField dy;
    public javax.swing.JTextField dz;
    public javax.swing.JTextField eccen;
    public javax.swing.JTextField incl;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField lat;
    private javax.swing.JCheckBox localTimeZoneCheckBox;
    public javax.swing.JTextField lon;
    private javax.swing.JPanel main_window;
    public javax.swing.JTextField meanano;
    public javax.swing.JTextField orbit_no;
    private javax.swing.JButton playButton;
    public javax.swing.JTextField raan;
    private javax.swing.JCheckBox realTimeModeCheckBox;
    private javax.swing.JMenuItem receiveTele;
    public javax.swing.JTextField semimajor;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton stopButton;
    public javax.swing.JLabel tle_age;
    public javax.swing.JTextField x;
    public javax.swing.JTextField y;
    public javax.swing.JTextField z;
    // End of variables declaration//GEN-END:variables

    private JInternalFrame CreateMWindow(){
  
        
        desktop_pane = new JDesktopPane();
        getContentPane().add(desktop_pane);
        desktop_pane.setLayout(null);
        
        return createinitialwindow();
//            navigate newPanel = new navigate();
//            
//            String windowName = "Earth View";
//            newPanel.setName(windowName);
//            
//            JInternalFrame iframe = new JInternalFrame("Earth View",true,true,true,true);
//            
//            iframe.setContentPane(newPanel);
//            iframe.setBounds(0, 0, 985, 550);
//            iframe.setLocation(1, 52);
//            iframe.setVisible(true);           
//            desktop_pane.add(iframe);
//            try
//            {
//                iframe.setSelected(true);
//            }
//            catch (java.beans.PropertyVetoException e)
//            {
//            }
//            return iframe;
    }
    
    private JInternalFrame createinitialwindow() {
    
        navigate newPanel = new navigate(satHash, gsHash, currentJulianDate, sun, this);
            
            String windowName = "Earth View";
            newPanel.setName(windowName);
            twoDWindowVec.add(newPanel);
            
            JInternalFrame iframe = new JInternalFrame("Earth View",true,true,true,true);
            
            iframe.setContentPane(newPanel);
            iframe.setBackground(Color.darkGray);
            //iframe.setBounds(0, 0, 780, 430);
            Dimension d = new Dimension(780, 430);
            iframe.setLocation(1, 52);
            iframe.setVisible(true);
            iframe.setPreferredSize(d);
            desktop_pane.add(iframe);
            iframe.pack();
            try
            {
                iframe.setSelected(true);
            }
            catch (java.beans.PropertyVetoException e)
            {
            }
            return iframe;
    }
    
    public void LoadInitial()
    {
        Object obj = "ISS (ZARYA)             ";
        String nameSelected = obj.toString();
        
        // see if it is a satellite
        if(satHash.containsKey(nameSelected))
        {
             
            AbstractSatellite prop = satHash.get(nameSelected);
            // create property Panel:
            SatProperty newPanel = new SatProperty(prop,this);
        }
    }
    public void checkTimeDiffResetGroundTracks(double timeDiffDays)
    {
        if( timeDiffDays > 91.0/1440.0)
        {
            // big time jump
            for (AbstractSatellite sat : satHash.values() )
            {
                if(sat.getShowGroundTrack() && (sat.getPeriod() <= (timeDiffDays*24.0*60.0) ) )
                {
                    sat.setGroundTrackIni2False();
                    //System.out.println(sat.getName() +" - Groundtrack Iniated");
                }
            }
        }
    } // checkTimeDiffResetGroundTracks
    

   
   public void forceRepainting(boolean updateMapsData)
    {
        if(updateMapsData)
        {
            // update maps ----------------
            // set animation direction = 0
            currentPlayDirection = 0;
            // update graphics
            updateTime();
        }
        else
        {
            // just do a normal update -- don't regenerate data
            forceRepainting();
        }
    } // forceRepainting
   
   public void updateTime()
    {
        // save old time
        double prevJulDate = currentJulianDate.getJulianDate();
        
        // Get current simulation time!             
        if(realTimeModeCheckBox.isSelected())
        {
            // real time mode -- just use real time
            
            // Get current time in GMT
            // calculate current Juilian Date, update to current time
            currentJulianDate.update2CurrentTime(); //update();// = getCurrentJulianDate();

        }
        else
        {
            // non-real time mode add fraction of time to current jul date
            //currentJulianDate += currentPlayDirection*animationSimStepDays;
            currentJulianDate.addSeconds( currentPlayDirection*animationSimStepSeconds );
        }
                // update sun position
        try{
        sun.setCurrentMJD(currentJulianDate.getMJD());
           /// System.out.println(currentJulianDate.getMJD());
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
// DEBUG:
//        double [] sunPos = sun.getCurrentPositionJ2K();
//        System.out.println("Sun Pos(J2K) - Date (MJD): " + currentJulianDate.getMJD() + ", <x,y,z> < " +sunPos[0] +", " +sunPos[1] +", "+sunPos[2] +" >");
//        sunPos = sun.getCurrentPositionMOD();
//        System.out.println("Sun Pos(MOD) - Date (MJD): " + currentJulianDate.getMJD() + ", <x,y,z> < " +sunPos[0] +", " +sunPos[1] +", "+sunPos[2] +" >");
//        double[] llaTemp = GeoFunctions.GeodeticJulDate( sun.getCurrentPositionMOD() ,currentJulianDate.getJulianDate());
//        // sun.getCurrentPositionMOD()
//        //double[] llaTemp = GeoFunctions.GeodeticJulDate( new double[] {-1.400954880970050E+08,  5.168955443226393E+07 , 2.240975286312218E+07} ,currentJulianDate.getJDN());
//        // 01 Sep 2007 00:00:00.000 UTC
//        System.out.println("Sun lat/long :  " + llaTemp[0]*180.0/Math.PI + " , " + llaTemp[1]*180.0/Math.PI);
                
        // if time jumps by more than 91 minutes check period of sat to see if
        // ground tracks need to be updated
        double timeDiffDays = Math.abs(currentJulianDate.getJulianDate()-prevJulDate); // in days
        checkTimeDiffResetGroundTracks(timeDiffDays);        
                
        // update date box:
        dateTextField.setText( currentJulianDate.getDateTimeStr() );//String.format("%tc",cal) );
        
        // now propogate all satellites to the current time  
        for (AbstractSatellite sat : satHash.values() )
        {
            sat.propogate2JulDate( currentJulianDate.getJulianDate() );
        } // propgate each sat
        
        // update ground stations to the current time  
        for (GroundStation gs : gsHash.values() )
        {
            gs.setCurrentJulianDate( currentJulianDate.getJulianDate() );
            
            // test look angles
//            SatelliteProps sp= satHash.get("ISS (ZARYA)             ");
//            double[] aer = gs.calculate_AER(sp.getJ2000Position());
//            
//            System.out.println("AER: " + aer[0] + ", " + aer[1] + ", " + aer[2]);
            
        } // propgate each sat
        
        
        // update any other time dependant objects
        for(SaraswatiSATTimeDependent tdo : timeDependentObjects)
        {
            if(tdo != null)
            {
                tdo.updateTime(currentJulianDate, satHash, gsHash);
            }
        }
        
        forceRepainting(); // repaint 2d/3d earth
        LoadInitial();
        // update any satellite property window that is open
//        for(SatPropertyPanel satP : satPropWindowVec)
//        {
//            satP.updateProperties(); 
//        }
        
        // update Tracking windows
//        for(JTrackingPanel tp : trackingWindowVec)
//        {
//            tp.updateTime( currentJulianDate.getDateTimeStr() );
//        }
        
        
    } // update time
    
    public void forceRepainting()
    {
        // force repainting of all 2D windows
        for(navigate twoDPanel : twoDWindowVec )
        {
            twoDPanel.repaint();
        }
        
    }// forceRepainting
    
    private void updateTimeStepsDataGUI()
    {
        // bounds checking
        if(currentTimeStepSpeedIndex > (timeStepSpeeds.length-1))
        {
            currentTimeStepSpeedIndex = (timeStepSpeeds.length-1);
        }
        else if(currentTimeStepSpeedIndex < 0)
        {
            currentTimeStepSpeedIndex = 0;
        }
        
        double speedInSec = timeStepSpeeds[currentTimeStepSpeedIndex];
                
       // timeStepLabel.setText("" + speedInSec);
        
        animationSimStepSeconds = speedInSec; // seconds to days 
    }
    
    public void setStatusMessage(String msg)
    {
        statusLabel.setText(msg);
        messageTimer.restart();
        
        // so this shows up in console
        System.out.println(msg);
    }
    
    public void runScript(String scriptFilePath)
    {
        System.out.println("Running Plugin: " + scriptFilePath);

        // runnning bash script
        try
        {
            beanShellInterp.source( scriptFilePath );
        }
        catch(Exception ee)
        {
            System.out.println("Error running plugin script (" + scriptFilePath + "): " + ee.toString());
            JOptionPane.showMessageDialog(this, "Error running plugin script (" + scriptFilePath + "): \n" + ee.toString(), "Pluging Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addSat2ListByName(String satName)
    {
        // add sat to list if TLE exsits by name
        
 //       JSatBrowser newBrowswer = new JSatBrowser(this, false, this);
        line1 = satName;
        line2 = "1 25544U 98067A   14058.09600917  .00017829  00000-0  31095-3 0  7682";
        line3 = "2 25544  51.6497 285.3348 0003988 178.0421 328.4955 15.50781313874243";
        TLE data_tle = new TLE(line1, line2, line3);
        
//        Hashtable<String,TLE> tleHash = newBrowswer.getTleHash();
        
//        TLE data_tle = tleHash.get(satName); 
         
         if(data_tle!= null)
         {
            // make sat prop object and add it to the list
             try
             {
                SatelliteTleSGP4 prop = new SatelliteTleSGP4(data_tle.getSatName(), data_tle.getLine1(), data_tle.getLine2());            
                // add sat to list
                objListPanel.addSat2List(prop);
             }
             catch(Exception e)
             {
                System.out.println(e);
             }
         }
         
    } // addSat2ListByName
    
    public double getCurrentJulTime()
    {
        return currentJulianDate.getJulianDate();
    }

    public void addInternalFrame(JInternalFrame iframe)
    {
        addInternalFrame(iframe, 15, 15);
    }
    
    public void addInternalFrame(JInternalFrame iframe, int xloc, int yloc)
    {
        // add action listener
        iframe.addInternalFrameListener(this);
        
        iframe.setLocation(xloc,yloc); // set starting loc

        // add default icon
//        iframe.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo/JSatTrakLogo_16.png")));
        
        iframe.setVisible(true);
        
        // add frame
        desktop_pane.add(iframe);
        
        // select new frame
        try
        {
            iframe.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e)
        {}
        
        // Figure out which kind of internal frame was added, and add it to Vector
        try
        {
            // is it a satellite Propery window?
            SatPropertyPanel closedPropPanel = (SatPropertyPanel)( iframe.getContentPane() );
            satPropWindowVec.add( closedPropPanel ); // add it to the vector
            // init it
            closedPropPanel.updateProperties();
        }
        catch(Exception e)
        {
            // else somthing else
        }
        
    } // add InternalFrame
    
    public void addCustomSat()
    {
        // get a name from the user:
        String name = JOptionPane.showInputDialog(this,"Custom Satellite Name");
         // call overloaded function with name
         addCustomSat(name);
    }
    
    /**
     * Stops Statusbar Animation
     */   
    
    public void addCustomSat(String name)
    {
        // add new custom sat to the list
        // is not already in list
        // add to hashTable
        
        
        
        // if nothing given:
        if(name == null || name.equalsIgnoreCase(""))
        {
            //System.out.println("returned");
            this.setStatusMessage("Custom Satellite Canceled: Either by user or not supplying a name.");
            return;
        }
        
        CustomSatellite prop = new CustomSatellite(name,this.getScenarioEpochDate());
        
        satHash.put(name, prop);

        // set satellite time to current date
        prop.propogate2JulDate(this.getCurrentJulTime());

        // add item to the Object list tree
        objListPanel.addSat2List(prop);
        
//        //topSatTreeNode.add( new IconTreeNode(name) );
//        IconTreeNode newNode = new IconTreeNode(name);
//        // assign icon to node
//        newNode.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/custom/sat_icon_cst.png"))));
//
//        treeModel.insertNodeInto(newNode, topSatTreeNode, topSatTreeNode.getChildCount());
//
//
//        //System.out.println("node added: " + name);                   
//        objectTree.scrollPathToVisible(getPath(newNode));
        
        this.setStatusMessage("Custom Satellite Added: " + name );
        // open properties panel
        objListPanel.openCurrentOptions(prop);
    }
    
    public void showSatBrowserInternalFrame()
    {
//        // show satellite browser window
//        JSatBrowser satBrowser = new JSatBrowser(this, false, this); // non-modal version
//
//        // create new internal frame window
//        String windowName = "Satellite Browser";
//        JInternalFrame iframe = new JInternalFrame(windowName,true,true,true,true);
//
//        iframe.setContentPane( satBrowser.getContentPane() );
//
//        if(satBrowser.getJMenuBar() != null)
//        {
//            iframe.setJMenuBar(satBrowser.getJMenuBar()); // get menu bar too! (if there is one)
//        }

        // NEW WAY
       JInternalFrame iframe = new JSatBrowserInternalFrame(this, this);

        iframe.setSize(261,450); // w,h
        iframe.setLocation(5,5);
        
        // add close action listener -- to remove window from hash
        iframe.addInternalFrameListener(this);
                
        iframe.setVisible(true);
        desktop_pane.add(iframe);
        try
        {
            iframe.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e)
        {}
        
        
    } // showsatBrowserInternalFrame
    
    public void open2dWindowOptions()
    {
        if( desktop_pane.getSelectedFrame() == null || !(desktop_pane.getSelectedFrame().getContentPane() instanceof navigate))
        {
            JOptionPane.showInternalMessageDialog(desktop_pane,"A 2D window must be selected.","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // first check to make sure a 2D window is selected
        // if a 2d window was selected do a little more math to just get to map
        
        
        navigate panel =  (navigate)desktop_pane.getSelectedFrame().getContentPane();
        
        Point pt = desktop_pane.getSelectedFrame().getLocation();
        
        TwoDViewPropertiesPanel propPanel = new TwoDViewPropertiesPanel(panel, this);
        
        // create new internal frame window
        JInternalFrame iframe = new JInternalFrame("2D Earth Window Prop.",true,true,true,true);
        
        iframe.setContentPane( propPanel );
        iframe.setSize(605,377+30); // w, h
        iframe.setLocation(pt.x + 15, pt.y + 20);

        iframe.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo/JSatTrakLogo_16.png")));
        
        // save frame
        propPanel.setInternalFrame(iframe);
        
        // add close action listener -- to remove window from hash
        iframe.addInternalFrameListener(this);
        
        iframe.setVisible(true);
        desktop_pane.add(iframe);
        try
        {
            iframe.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e)
        {}
        
        
        
    } // open2dWindowOptions

    public Vector<navigate> getTwoDWindowVec()
    {
        return twoDWindowVec;
        
    }
    
    public double getFpsAnimation()
    {
        return fpsAnimation;
    }

    public name.gano.astro.time.Time getScenarioEpochDate()
    {
        return scenarioEpochDate;
    }
    
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // System.out.println("Showed");
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}