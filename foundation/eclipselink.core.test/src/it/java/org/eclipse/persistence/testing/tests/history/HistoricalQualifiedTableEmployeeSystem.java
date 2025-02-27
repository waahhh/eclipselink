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
package org.eclipse.persistence.testing.tests.history;

import java.util.List;
import java.util.Vector;
import org.eclipse.persistence.testing.models.employee.domain.Employee;
import org.eclipse.persistence.testing.models.employee.relational.*;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DirectCollectionMapping;
import org.eclipse.persistence.mappings.ManyToManyMapping;
import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.tools.history.*;

/**
 * <b>Purpose:</b>A specialized Employee System for testing generic historical schema support with qualified table names.
 * <p>
 * This EmployeeSystem creates descriptors with history policies configured, and
 * generates a historical schema on the database when tables are created.
 * <p>
 * The specialized descriptors are effected using the HistoryFacade.
 */
public class HistoricalQualifiedTableEmployeeSystem extends EmployeeSystem {
    public HistoricalQualifiedTableEmployeeSystem(String user) {
        project = getInitialProject(user);
    }

    public org.eclipse.persistence.sessions.Project getInitialProject(String user) {
        org.eclipse.persistence.sessions.Project initialProject = new EmployeeProject();
        HistoryFacade.generateHistoryPolicies(initialProject);

        ClassDescriptor empDescriptor = initialProject.getDescriptor(Employee.class);

        ManyToManyMapping mapping = (ManyToManyMapping)empDescriptor.getMappingForAttributeName("projects");
        String oldRelationTableName = mapping.getRelationTableName();
        List<String> oldHistoricalTableNames = mapping.getHistoryPolicy().getHistoryTableNames();
        mapping.getHistoryPolicy().setHistoricalTables(new Vector(1));
        for (int i = 0; i < oldHistoricalTableNames.size(); i++) {
            mapping.getHistoryPolicy().addHistoryTableName(user + "." + oldHistoricalTableNames.get(i));
        }
        mapping.setRelationTableName(user + "." + oldRelationTableName);

        Vector<DatabaseField> targetRelationKeyFields = mapping.getTargetKeyFields();
        for (int i = 0; i < targetRelationKeyFields.size(); i++) {
            String oldName = targetRelationKeyFields.get(i).getTable().getName();
            targetRelationKeyFields.get(i).setTableName(user + "." + oldName);
        }

        Vector<DatabaseField> sourceRelationKeyFields = mapping.getSourceRelationKeyFields();
        for (int i = 0; i < sourceRelationKeyFields.size(); i++) {
            String oldName = sourceRelationKeyFields.get(i).getTable().getName();
            sourceRelationKeyFields.get(i).setTableName(user + "." + oldName);
        }

        DirectCollectionMapping dcmapping = (DirectCollectionMapping)empDescriptor.getMappingForAttributeName("responsibilitiesList");
        String oldReferenceTableName = dcmapping.getReferenceTableName();
        List<String> oldDCHistoricalTableNames = dcmapping.getHistoryPolicy().getHistoryTableNames();
        dcmapping.getHistoryPolicy().setHistoricalTables(new Vector(1));
        for (int i = 0; i < oldDCHistoricalTableNames.size(); i++) {
            dcmapping.getHistoryPolicy().addHistoryTableName(user + "." + oldDCHistoricalTableNames.get(i));
        }
        dcmapping.setReferenceTableName(user + "." + oldReferenceTableName);

        return initialProject;
    }

    @Override
    public void createTables(DatabaseSession session) {
        super.createTables(session);
        EmployeeTableCreator creator = new EmployeeTableCreator();
        HistoryFacade.generateHistoricalTableDefinitions(creator, session);
        creator.replaceTables(session);
    }
}
