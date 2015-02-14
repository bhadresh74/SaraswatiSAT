/*
 * SaraswatiSATRenderable.java
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
 * 
 * 
 */

package SaraswatiSat.covergae;

import java.awt.Graphics2D;
import SaraswatiSat.gui.navigate;
import SaraswatiSat.gui.navigatepanel;

/**
 *
 * @author Shawn
 */
public interface SaraswatiSATRenderable 
{
    public void draw2d(Graphics2D g2, navigatepanel earthLabel, int totWidth, int totHeight, int imgWidth, int imgHeight, double zoomFac, double cLat, double cLong);
    public void draw3d();
}
