/*
 * Copyright (c) 1998, 2021 Oracle and/or its affiliates. All rights reserved.
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
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.testing.jaxb.xmlattribute;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.testing.jaxb.JAXBTestCases;
import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class XmlAttributeNoNamespaceTestCases extends JAXBWithJSONTestCases {

    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlattribute/employee_nonamespace.xml";
    private final static String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlattribute/employee_nonamespace.json";
    private final static int CONTROL_ID = 10;

    public XmlAttributeNoNamespaceTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setControlJSON(JSON_RESOURCE);
        Class<?>[] classes = new Class<?>[1];
        classes[0] = EmployeeNoNamespace.class;
        setClasses(classes);
    }

    @Override
    protected Object getControlObject() {
        EmployeeNoNamespace employee = new EmployeeNoNamespace();
        employee.id = CONTROL_ID;
        return employee;
    }

    public void testSchemaGen() throws Exception{
        List<InputStream> controlSchemas = new ArrayList<InputStream>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("org/eclipse/persistence/testing/jaxb/xmlattribute/employee_nonamespace.xsd");
        controlSchemas.add(is);
        super.testSchemaGen(controlSchemas);
    }
}
