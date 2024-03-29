/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class SusceptanceValidator {
		
	/**
	 * default constructor
	 */
	protected SusceptanceValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SusceptanceValidator getInstance() {
		return new SusceptanceValidator();
	}
		
	/**
	 * handles creation validation for a Susceptance
	 */
	public void validate( CreateSusceptanceCommand susceptance )throws Exception {
		Assert.notNull( susceptance, "CreateSusceptanceCommand should not be null" );
//		Assert.isNull( susceptance.getSusceptanceId(), "CreateSusceptanceCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Susceptance
	 */
	public void validate( UpdateSusceptanceCommand susceptance ) throws Exception {
		Assert.notNull( susceptance, "UpdateSusceptanceCommand should not be null" );
		Assert.notNull( susceptance.getSusceptanceId(), "UpdateSusceptanceCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Susceptance
	 */
    public void validate( DeleteSusceptanceCommand susceptance ) throws Exception {
		Assert.notNull( susceptance, "{commandAlias} should not be null" );
		Assert.notNull( susceptance.getSusceptanceId(), "DeleteSusceptanceCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Susceptance
	 */
	public void validate( SusceptanceFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SusceptanceFetchOneSummary should not be null" );
		Assert.notNull( summary.getSusceptanceId(), "SusceptanceFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a Susceptance
	 * 
	 * @param	command AssignValueToSusceptanceCommand
	 */	
	public void validate( AssignValueToSusceptanceCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToSusceptanceCommand should not be null" );
		Assert.notNull( command.getSusceptanceId(), "AssignValueToSusceptanceCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToSusceptanceCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a Susceptance
	 * 
	 * @param	command UnAssignValueFromSusceptanceCommand
	 */	
	public void validate( UnAssignValueFromSusceptanceCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromSusceptanceCommand should not be null" );
		Assert.notNull( command.getSusceptanceId(), "UnAssignValueFromSusceptanceCommand identifier should not be null" );
	}


}
