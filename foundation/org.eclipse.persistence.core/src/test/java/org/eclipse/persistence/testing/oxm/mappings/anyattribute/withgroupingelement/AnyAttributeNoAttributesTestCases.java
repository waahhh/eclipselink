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
package org.eclipse.persistence.testing.oxm.mappings.anyattribute.withgroupingelement;

/**
 *  @version $Header: AnyAttributeNoAttributesTestCases.java 24-apr-2006.15:09:14 mmacivor Exp $
 *  @author  mmacivor
 *  @since   release specific (what release of product did this appear in)
 */

import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;
import org.eclipse.persistence.testing.oxm.mappings.anyattribute.withoutgroupingelement.Root;

import java.util.HashMap;

public class AnyAttributeNoAttributesTestCases extends XMLMappingTestCases {
    public AnyAttributeNoAttributesTestCases(String name) throws Exception {
        super(name);
        setProject(new AnyAttributeWithGroupingElementProject());
        setControlDocument("org/eclipse/persistence/testing/oxm/mappings/anyattribute/withoutgroupingelement/no_attributes.xml");
    }

    @Override
    public Object getControlObject() {
        Root root = new Root();
        HashMap any = new HashMap();
        root.setAny(any);
        return root;
    }

}
