
package SaraswatiSat.utilities;

/**
 *
 * @author Bhadresh
 */
public class TLE implements java.io.Serializable
{
    String line0 = ""; // name line
    String line1 = ""; // first line
    String line2 = ""; // second line
    
    /** Creates a new instance of TLE */
    public TLE()
    {
        
    }
    
    public TLE(String name, String l1, String l2)
    {
        line0 = name;
        line1 = l1;
        line2 = l2;
    }
    
    public String getSatName()
    {
        return line0;
    }
    
    public String getLine1()
    {
        return line1;
    }
    
    public String getLine2()
    {
        return line2;
    }
             
    
} // TLE
