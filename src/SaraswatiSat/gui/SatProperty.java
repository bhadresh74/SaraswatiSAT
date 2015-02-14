/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SaraswatiSat.gui;

import SaraswatiSat.objects.AbstractSatellite;
import SaraswatiSat.gui.SaraswatiSAT;
import javax.swing.JTextField;
import name.gano.astro.AstroConst;
import name.gano.astro.coordinates.CoordinateConversion;
import net.sf.antcontrib.net.Prop;

/**
 *
 * @author Bhadresh
 */
public class SatProperty{
    
    AbstractSatellite satProp;
    
    public SatProperty(AbstractSatellite satProp,SaraswatiSAT prop)
    {
        this.satProp = satProp;
        double[] pos = new double[3], vel = new double[3];
        getSelectedPosVel(pos, vel);
        
        prop.orbit_no.setText(""+satProp.getTleAgeDays());
        prop.lat.setText(""+satProp.getLatitude()*180.0/Math.PI);
        prop.lon.setText(""+satProp.getLongitude()*180.0/Math.PI);
        prop.alt.setText(""+satProp.getAltitude());
        
        prop.x.setText(""+pos[0]);
        prop.y.setText(""+pos[1]);
        prop.z.setText(""+pos[2]);
        
        prop.dx.setText(""+vel[0]);
        prop.dy.setText(""+vel[1]);
        prop.dz.setText(""+vel[2]);
        
        // try for keplarian elements -- might be singular
        try
        {
            double[] kep = satProp.getKeplarianElements();
            
            prop.semimajor.setText(""+kep[0]);
            prop.eccen.setText(""+kep[1]);
            prop.incl.setText(""+kep[2]*180.0/Math.PI);
            prop.raan.setText(""+kep[3]*180.0/Math.PI);
            prop.aop.setText(""+kep[4]*180.0/Math.PI);
            prop.meanano.setText(""+kep[5]*180.0/Math.PI);
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    private void getSelectedPosVel(double[] pos, double[] vel)
    {
        copyArray(satProp.getJ2000Position(), pos);
        copyArray(satProp.getJ2000Velocity(), vel);
        
    } // getSelectedPosVel
    
    private void copyArray(double[] a1, double[] a2)
    {
        for(int i=0; i<a1.length; i++)
        {
            a2[i] = a1[i];
        }
    } // copyArray

    
}
