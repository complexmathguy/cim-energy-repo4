/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class AnalogLimitValidator {
		
	/**
	 * default constructor
	 */
	protected AnalogLimitValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public AnalogLimitValidator getInstance() {
		return new AnalogLimitValidator();
	}
		
	/**
	 * handles creation validation for a AnalogLimit
	 */
	public void validate( CreateAnalogLimitCommand analogLimit )throws Exception {
		Assert.notNull( analogLimit, "CreateAnalogLimitCommand should not be null" );
//		Assert.isNull( analogLimit.getAnalogLimitId(), "CreateAnalogLimitCommand identifier should be null" );
	}

	/**
	 * handles update validation for a AnalogLimit
	 */
	public void validate( UpdateAnalogLimitCommand analogLimit ) throws Exception {
		Assert.notNull( analogLimit, "UpdateAnalogLimitCommand should not be null" );
		Assert.notNull( analogLimit.getAnalogLimitId(), "UpdateAnalogLimitCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a AnalogLimit
	 */
    public void validate( DeleteAnalogLimitCommand analogLimit ) throws Exception {
		Assert.notNull( analogLimit, "{commandAlias} should not be null" );
		Assert.notNull( analogLimit.getAnalogLimitId(), "DeleteAnalogLimitCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a AnalogLimit
	 */
	public void validate( AnalogLimitFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "AnalogLimitFetchOneSummary should not be null" );
		Assert.notNull( summary.getAnalogLimitId(), "AnalogLimitFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a AnalogLimit
	 * 
	 * @param	command AssignValueToAnalogLimitCommand
	 */	
	public void validate( AssignValueToAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "AssignValueToAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToAnalogLimitCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a AnalogLimit
	 * 
	 * @param	command UnAssignValueFromAnalogLimitCommand
	 */	
	public void validate( UnAssignValueFromAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "UnAssignValueFromAnalogLimitCommand identifier should not be null" );
	}

	/**
	 * handles add to Limits validation for a AnalogLimit
	 * 
	 * @param	command AssignLimitsToAnalogLimitCommand
	 */	
	public void validate( AssignLimitsToAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignLimitsToAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "AssignLimitsToAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignLimitsToAnalogLimitCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from Limits validation for a AnalogLimit
	 * 
	 * @param	command RemoveLimitsFromAnalogLimitCommand
	 */	
	public void validate( RemoveLimitsFromAnalogLimitCommand command ) throws Exception {
		Assert.notNull( command, "RemoveLimitsFromAnalogLimitCommand should not be null" );
		Assert.notNull( command.getAnalogLimitId(), "RemoveLimitsFromAnalogLimitCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveLimitsFromAnalogLimitCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getAnalogLimitId(), "RemoveLimitsFromAnalogLimitCommand removeFrom attribubte identifier should not be null" );
	}
	

}
