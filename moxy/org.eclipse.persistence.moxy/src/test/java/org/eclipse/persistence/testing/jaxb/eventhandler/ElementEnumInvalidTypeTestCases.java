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
//    Denise Smith - May 2012
package org.eclipse.persistence.testing.jaxb.eventhandler;

import jakarta.xml.bind.ValidationEvent;

import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class ElementEnumInvalidTypeTestCases extends JAXBWithJSONTestCases{
    MyEventHandler handler;
    public ElementEnumInvalidTypeTestCases(String name) throws Exception {
        super(name);
        setClasses(new Class<?>[] {MyClass.class});
        setControlDocument("org/eclipse/persistence/testing/jaxb/eventhandler/elementEnumWrongType.xml");
        setControlJSON("org/eclipse/persistence/testing/jaxb/eventhandler/elementEnumWrongType.json");
        setWriteControlDocument("org/eclipse/persistence/testing/jaxb/eventhandler/valid.xml");
        setWriteControlJSON("org/eclipse/persistence/testing/jaxb/eventhandler/valid.json");

    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
        handler = new MyEventHandler();
        jaxbUnmarshaller.setEventHandler(handler);
    }

    @Override
    protected Object getControlObject() {
        MyClass myClass = new MyClass();
        myClass.myAttribute =10;
        myClass.myElement = 20;

        return myClass;
    }

    @Override
    public void jsonToObjectTest(Object testObject, Object controlObject) throws Exception {
        assertEquals(ValidationEvent.ERROR, handler.getSeverity());
   }

    @Override
    public void xmlToObjectTest(Object testObject) throws Exception {
        assertEquals(ValidationEvent.ERROR, handler.getSeverity());
    }

    @Override
    public void testRoundTrip(){};

}
