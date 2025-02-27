/*
 * Copyright (c) 1998, 2023 Oracle and/or its affiliates. All rights reserved.
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
package org.eclipse.persistence.testing.oxm.events;

import org.eclipse.persistence.oxm.XMLMarshaller;
import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class RootWithAnyObjectTestCases extends XMLMappingTestCases {
    public MarshalListenerImpl listener;
    public UnmarshalListenerImpl unmarshalListener;
    public ArrayList expectedMarshalEvents;
    public ArrayList expectedUnmarshalEvents;

    public RootWithAnyObjectTestCases(String name) throws Exception {
        super(name);
        if (!System.getProperties().contains("platformType")) {
            System.setProperty("platformType", "DOM");
        }
        setProject(new EmployeeProject());
        setControlDocument("org/eclipse/persistence/testing/oxm/events/any_object.xml");

        expectedMarshalEvents = new ArrayList();
        expectedMarshalEvents.add(MarshalListenerImpl.EMPLOYEE_BEFORE_MARSHAL);
        expectedMarshalEvents.add(MarshalListenerImpl.ADDRESS_BEFORE_MARSHAL);
        expectedMarshalEvents.add(MarshalListenerImpl.ADDRESS_AFTER_MARSHAL);
        expectedMarshalEvents.add(MarshalListenerImpl.EMPLOYEE_AFTER_MARSHAL);

        expectedUnmarshalEvents = new ArrayList();
        expectedUnmarshalEvents.add(UnmarshalListenerImpl.EMPLOYEE_BEFORE_UNMARSHAL);
        expectedUnmarshalEvents.add(UnmarshalListenerImpl.ADDRESS_BEFORE_UNMARSHAL);
        expectedUnmarshalEvents.add(UnmarshalListenerImpl.ADDRESS_AFTER_UNMARSHAL);
        expectedUnmarshalEvents.add(UnmarshalListenerImpl.EMPLOYEE_AFTER_UNMARSHAL);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        unmarshalListener = new UnmarshalListenerImpl();
        this.xmlUnmarshaller.setUnmarshalListener(unmarshalListener);
    }

    @Override
    protected XMLMarshaller createMarshaller() {
        XMLMarshaller marshaller = super.createMarshaller();
        listener = new MarshalListenerImpl();
        marshaller.setMarshalListener(listener);
        return marshaller;
    }

    @Override
    public void xmlToObjectTest(Object testObject) throws Exception {
        super.xmlToObjectTest(testObject);
        assertTrue("Expected sequence of Unmarshal events not found", expectedUnmarshalEvents.equals(unmarshalListener.events));
    }

    @Override
    public void objectToXMLDocumentTest(Document testDocument) throws Exception {
        super.objectToXMLDocumentTest(testDocument);
        assertTrue("Expected sequence of Marshal events not found", expectedMarshalEvents.equals(listener.events));
    }

    @Override
    public Object getControlObject() {
        Employee employee = new Employee();
        Address address = new Address();
        address.street = "2201 Riverside Drive";
        employee.anyObject = address;
        return employee;
    }
}
