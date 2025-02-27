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
package org.eclipse.persistence.internal.queries;

import org.eclipse.persistence.indirection.IndirectCollectionsFactory;
import org.eclipse.persistence.internal.sessions.AbstractSession;

import java.util.Collection;
import java.util.Vector;

/**
 * PERF: Avoids reflection usage for IndirectList.
 *
 * @see ContainerPolicy
 * @author Big Country
 * @since TOPLink/Java 2.5
 */
public class IndirectListContainerPolicy extends ListContainerPolicy {

    /**
     * INTERNAL:
     * Construct a new policy.
     */
    public IndirectListContainerPolicy() {
        super();
    }

    /**
     * INTERNAL:
     * Construct a new policy for the specified class.
     */
    public IndirectListContainerPolicy(Class<?> containerClass) {
        super(containerClass);
    }


    /**
     * INTERNAL:
     * Return a clone of the specified container.
     */
    @Override
    public Object cloneFor(Object container) {
        if (container == null) {
            return null;
        }
        // Use Vector as new objects can have a Vector.
        if (container.getClass() == Vector.class) {
            return ((Vector)container).clone();
        }

        // Could potentially be another Collection type as well.
        return IndirectCollectionsFactory.createIndirectList((Collection)container);
    }

    /**
     * INTERNAL:
     * Just return the Vector.
     */
    @Override
    public Object buildContainerFromVector(Vector vector, AbstractSession session) {
        return IndirectCollectionsFactory.createIndirectList(vector);
    }

    /**
     * INTERNAL:
     * Return a new Vector.
     */
    @Override
    public Object containerInstance() {
        return IndirectCollectionsFactory.createIndirectList();
    }

    /**
     * INTERNAL:
     * Return a new Vector.
     */
    @Override
    public Object containerInstance(int initialCapacity) {
        return IndirectCollectionsFactory.createIndirectList(initialCapacity);
    }
}
