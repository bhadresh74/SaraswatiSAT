/*
 * JSatTrakTimeDependent.java
 * 
 * =====================================================================
 *   This file is part of JSatTrak.
 *
 *   Copyright 2007-2013 Shawn E. Gano
 *   
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *   
 *       http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * =====================================================================
 * 
 * abstract class for objects that depend on time and will be updated when time changes
 * 
 */

package SaraswatiSat.covergae;

import java.util.Hashtable;
import SaraswatiSat.objects.AbstractSatellite;
import SaraswatiSat.objects.GroundStation;
import name.gano.astro.time.Time;

/**
 *
 * @author Shawn
 */
public interface SaraswatiSATTimeDependent 
{
 
    public void updateTime(final Time currentJulianDate, Hashtable<String, AbstractSatellite> satHash, Hashtable<String, GroundStation> gsHash);

}
