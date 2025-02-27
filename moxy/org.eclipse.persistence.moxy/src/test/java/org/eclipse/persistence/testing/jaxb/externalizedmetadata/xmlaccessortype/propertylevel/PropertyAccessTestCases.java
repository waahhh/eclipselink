/*
 * Copyright (c) 2012, 2021 Oracle and/or its affiliates. All rights reserved.
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
//     Matt MacIvor - 2.4 - Initial Implementation
package org.eclipse.persistence.testing.jaxb.externalizedmetadata.xmlaccessortype.propertylevel;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class PropertyAccessTestCases extends JAXBWithJSONTestCases {
    private static final String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/xmlaccessortype/propertylevel/field.xml";
    private static final String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/xmlaccessortype/propertylevel/field.json";

    public PropertyAccessTestCases(String name) throws Exception {
        super(name);
        setClasses(new Class<?>[]{Customer.class});
        setControlDocument(XML_RESOURCE);
        setControlJSON(JSON_RESOURCE);
    }

    @Override
    protected Object getControlObject() {
        Customer cust = new Customer();
        cust.property = "control value";
        return cust;
    }


    @Override
    public Map getProperties(){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("org/eclipse/persistence/testing/jaxb/externalizedmetadata/xmlaccessortype/propertylevel/property-access-oxm.xml");

        HashMap<String, Source> metadataSourceMap = new HashMap<String, Source>();
        metadataSourceMap.put("org.eclipse.persistence.testing.jaxb.externalizedmetadata.xmlaccessortype.propertylevel", new StreamSource(inputStream));
        Map<String, Map<String, Source>> properties = new HashMap<String, Map<String, Source>>();
        properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, metadataSourceMap);

        return properties;
    }


}
