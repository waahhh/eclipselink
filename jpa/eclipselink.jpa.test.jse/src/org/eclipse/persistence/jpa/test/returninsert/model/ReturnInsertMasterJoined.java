/*******************************************************************************
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.persistence.jpa.test.returninsert.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the JPA22_RETURNINSERT_MASTER_JOINED database table.
 * 
 */
@Entity
@Table(name = "JPA22_RETURNINSERT_MASTER_JOINED")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "AbstractEntityObjectSEQ_STORE", sequenceName = "TEST_RETURNINSERT_MASTER_JOINED_ID_SEQ", allocationSize = 1)
public class ReturnInsertMasterJoined implements Serializable {

	@Id
	private Long id;

	private String type;

	public ReturnInsertMasterJoined() {
	}

	public ReturnInsertMasterJoined(Long id, String type) {
		this.id = id;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}