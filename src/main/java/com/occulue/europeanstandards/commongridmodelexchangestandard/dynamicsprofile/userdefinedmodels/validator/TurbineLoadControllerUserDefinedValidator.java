/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class TurbineLoadControllerUserDefinedValidator {
		
	/**
	 * default constructor
	 */
	protected TurbineLoadControllerUserDefinedValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TurbineLoadControllerUserDefinedValidator getInstance() {
		return new TurbineLoadControllerUserDefinedValidator();
	}
		
	/**
	 * handles creation validation for a TurbineLoadControllerUserDefined
	 */
	public void validate( CreateTurbineLoadControllerUserDefinedCommand turbineLoadControllerUserDefined )throws Exception {
		Assert.notNull( turbineLoadControllerUserDefined, "CreateTurbineLoadControllerUserDefinedCommand should not be null" );
//		Assert.isNull( turbineLoadControllerUserDefined.getTurbineLoadControllerUserDefinedId(), "CreateTurbineLoadControllerUserDefinedCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TurbineLoadControllerUserDefined
	 */
	public void validate( UpdateTurbineLoadControllerUserDefinedCommand turbineLoadControllerUserDefined ) throws Exception {
		Assert.notNull( turbineLoadControllerUserDefined, "UpdateTurbineLoadControllerUserDefinedCommand should not be null" );
		Assert.notNull( turbineLoadControllerUserDefined.getTurbineLoadControllerUserDefinedId(), "UpdateTurbineLoadControllerUserDefinedCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TurbineLoadControllerUserDefined
	 */
    public void validate( DeleteTurbineLoadControllerUserDefinedCommand turbineLoadControllerUserDefined ) throws Exception {
		Assert.notNull( turbineLoadControllerUserDefined, "{commandAlias} should not be null" );
		Assert.notNull( turbineLoadControllerUserDefined.getTurbineLoadControllerUserDefinedId(), "DeleteTurbineLoadControllerUserDefinedCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TurbineLoadControllerUserDefined
	 */
	public void validate( TurbineLoadControllerUserDefinedFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TurbineLoadControllerUserDefinedFetchOneSummary should not be null" );
		Assert.notNull( summary.getTurbineLoadControllerUserDefinedId(), "TurbineLoadControllerUserDefinedFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Proprietary validation for a TurbineLoadControllerUserDefined
	 * 
	 * @param	command AssignProprietaryToTurbineLoadControllerUserDefinedCommand
	 */	
	public void validate( AssignProprietaryToTurbineLoadControllerUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "AssignProprietaryToTurbineLoadControllerUserDefinedCommand should not be null" );
		Assert.notNull( command.getTurbineLoadControllerUserDefinedId(), "AssignProprietaryToTurbineLoadControllerUserDefinedCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignProprietaryToTurbineLoadControllerUserDefinedCommand assignment should not be null" );
	}

	/**
	 * handles unassign Proprietary validation for a TurbineLoadControllerUserDefined
	 * 
	 * @param	command UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand
	 */	
	public void validate( UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand should not be null" );
		Assert.notNull( command.getTurbineLoadControllerUserDefinedId(), "UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand identifier should not be null" );
	}


}
