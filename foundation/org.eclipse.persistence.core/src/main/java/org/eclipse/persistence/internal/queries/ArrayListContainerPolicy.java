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
//     10/28/2008-1.1 James Sutherland - initial implementation
package org.eclipse.persistence.internal.queries;

import org.eclipse.persistence.internal.sessions.AbstractSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

/**
 * PERF: Avoids reflection usage for ArrayList.
 */
public class ArrayListContainerPolicy extends ListContainerPolicy {
    /**
     * INTERNAL:
     * Construct a new policy.
     */
    public ArrayListContainerPolicy() {
        super();
    }

    /**
     * INTERNAL:
     * Construct a new policy for the specified class.
     */
    public ArrayListContainerPolicy(Class<?> containerClass) {
        super(containerClass);
    }

    /**
     * INTERNAL:
     * Construct a new policy for the specified class name.
     */
    public ArrayListContainerPolicy(String containerClassName) {
        super(containerClassName);
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
        if (container.getClass() == ArrayList.class) {
            return ((ArrayList)container).clone();
        }

        // Could potentially be another Collection type as well.
        return new ArrayList<>((Collection) container);
    }

    /**
     * INTERNAL:
     * Return an ArrayList from the Vector.
     */
    @Override
    public Object buildContainerFromVector(Vector vector, AbstractSession session) {
        return new ArrayList<>(vector);
    }

    /**
     * INTERNAL:
     * Return a new ArrayList.
     */
    @Override
    public Object containerInstance() {
        return new ArrayList<>();
    }

    /**
     * INTERNAL:
     * Return a new ArrayList.
     */
    @Override
    public Object containerInstance(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }
}
