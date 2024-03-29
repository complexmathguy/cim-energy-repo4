/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.mechanicalloaddynamics..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class MechanicalLoadDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected MechanicalLoadDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public MechanicalLoadDynamicsValidator getInstance() {
		return new MechanicalLoadDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a MechanicalLoadDynamics
	 */
	public void validate( CreateMechanicalLoadDynamicsCommand mechanicalLoadDynamics )throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "CreateMechanicalLoadDynamicsCommand should not be null" );
//		Assert.isNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "CreateMechanicalLoadDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a MechanicalLoadDynamics
	 */
	public void validate( UpdateMechanicalLoadDynamicsCommand mechanicalLoadDynamics ) throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "UpdateMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "UpdateMechanicalLoadDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a MechanicalLoadDynamics
	 */
    public void validate( DeleteMechanicalLoadDynamicsCommand mechanicalLoadDynamics ) throws Exception {
		Assert.notNull( mechanicalLoadDynamics, "{commandAlias} should not be null" );
		Assert.notNull( mechanicalLoadDynamics.getMechanicalLoadDynamicsId(), "DeleteMechanicalLoadDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a MechanicalLoadDynamics
	 */
	public void validate( MechanicalLoadDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "MechanicalLoadDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getMechanicalLoadDynamicsId(), "MechanicalLoadDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign MechanicalLoadDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command AssignMechanicalLoadDynamicsToMechanicalLoadDynamicsCommand
	 */	
	public void validate( AssignMechanicalLoadDynamicsToMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignMechanicalLoadDynamicsToMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "AssignMechanicalLoadDynamicsToMechanicalLoadDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignMechanicalLoadDynamicsToMechanicalLoadDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign MechanicalLoadDynamics validation for a MechanicalLoadDynamics
	 * 
	 * @param	command UnAssignMechanicalLoadDynamicsFromMechanicalLoadDynamicsCommand
	 */	
	public void validate( UnAssignMechanicalLoadDynamicsFromMechanicalLoadDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignMechanicalLoadDynamicsFromMechanicalLoadDynamicsCommand should not be null" );
		Assert.notNull( command.getMechanicalLoadDynamicsId(), "UnAssignMechanicalLoadDynamicsFromMechanicalLoadDynamicsCommand identifier should not be null" );
	}


}
