/*
 * Copyright (c) 2011, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//  - rbarkhouse - 08 September 2011 - 2.4 - Initial implementation
package org.eclipse.persistence.testing.jaxb.annotations.xmllocation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlLocation;
import org.xml.sax.Locator;

@XmlRootElement
public class Data {

    @XmlElement
    public String key;

    @XmlElement
    public String data1;
    @XmlElement
    public String data2;
    @XmlElement
    public String data3;

    @XmlElement
    public List<SubData> subData = new ArrayList<SubData>();

    @XmlLocation
    @XmlTransient
    public Locator locator;

    @Override
    public String toString() {
        String loc = " noLoc";
        if (locator != null) {
            loc = " L" + locator.getLineNumber() + " C" + locator.getColumnNumber() + " " + locator.getSystemId();
        }

        String subDataS = "\n";
        for (Iterator<SubData> iterator = subData.iterator(); iterator.hasNext();) {
            SubData type = iterator.next();
            subDataS += "\t" + type.toString() + "\n";
        }

        return "\nData(" + key + ")" + loc + subDataS;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Data)) {
            return false;
        }

        Data d = (Data) obj;

        if (!(d.key.equals(this.key))) {
            return false;
        }

        if (d.locator == null && this.locator != null) {
            return false;
        }
        if (d.locator != null && this.locator == null) {
            return false;
        }

        if (this.locator != null && d.locator != null) {
            if (!(this.locator.equals(d.locator))) {
                return false;
            }
        }

        if (!(this.subData.equals(d.subData))) {
            return false;
        }

        return true;
    }

}
