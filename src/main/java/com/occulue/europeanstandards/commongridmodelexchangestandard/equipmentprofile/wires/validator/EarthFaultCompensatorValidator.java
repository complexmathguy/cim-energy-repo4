/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class EarthFaultCompensatorValidator {
		
	/**
	 * default constructor
	 */
	protected EarthFaultCompensatorValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public EarthFaultCompensatorValidator getInstance() {
		return new EarthFaultCompensatorValidator();
	}
		
	/**
	 * handles creation validation for a EarthFaultCompensator
	 */
	public void validate( CreateEarthFaultCompensatorCommand earthFaultCompensator )throws Exception {
		Assert.notNull( earthFaultCompensator, "CreateEarthFaultCompensatorCommand should not be null" );
//		Assert.isNull( earthFaultCompensator.getEarthFaultCompensatorId(), "CreateEarthFaultCompensatorCommand identifier should be null" );
	}

	/**
	 * handles update validation for a EarthFaultCompensator
	 */
	public void validate( UpdateEarthFaultCompensatorCommand earthFaultCompensator ) throws Exception {
		Assert.notNull( earthFaultCompensator, "UpdateEarthFaultCompensatorCommand should not be null" );
		Assert.notNull( earthFaultCompensator.getEarthFaultCompensatorId(), "UpdateEarthFaultCompensatorCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a EarthFaultCompensator
	 */
    public void validate( DeleteEarthFaultCompensatorCommand earthFaultCompensator ) throws Exception {
		Assert.notNull( earthFaultCompensator, "{commandAlias} should not be null" );
		Assert.notNull( earthFaultCompensator.getEarthFaultCompensatorId(), "DeleteEarthFaultCompensatorCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a EarthFaultCompensator
	 */
	public void validate( EarthFaultCompensatorFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "EarthFaultCompensatorFetchOneSummary should not be null" );
		Assert.notNull( summary.getEarthFaultCompensatorId(), "EarthFaultCompensatorFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign R validation for a EarthFaultCompensator
	 * 
	 * @param	command AssignRToEarthFaultCompensatorCommand
	 */	
	public void validate( AssignRToEarthFaultCompensatorCommand command ) throws Exception {
		Assert.notNull( command, "AssignRToEarthFaultCompensatorCommand should not be null" );
		Assert.notNull( command.getEarthFaultCompensatorId(), "AssignRToEarthFaultCompensatorCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRToEarthFaultCompensatorCommand assignment should not be null" );
	}

	/**
	 * handles unassign R validation for a EarthFaultCompensator
	 * 
	 * @param	command UnAssignRFromEarthFaultCompensatorCommand
	 */	
	public void validate( UnAssignRFromEarthFaultCompensatorCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRFromEarthFaultCompensatorCommand should not be null" );
		Assert.notNull( command.getEarthFaultCompensatorId(), "UnAssignRFromEarthFaultCompensatorCommand identifier should not be null" );
	}


}
