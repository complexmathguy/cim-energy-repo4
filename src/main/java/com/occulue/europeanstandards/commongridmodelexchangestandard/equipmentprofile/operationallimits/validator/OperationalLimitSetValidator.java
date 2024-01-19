/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits..validator;

import org.springframework.util.Assert;

import com.occulue.api.*;
import com.occulue.validator.*;

public class OperationalLimitSetValidator {
		
	/**
	 * default constructor
	 */
	protected OperationalLimitSetValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public OperationalLimitSetValidator getInstance() {
		return new OperationalLimitSetValidator();
	}
		
	/**
	 * handles creation validation for a OperationalLimitSet
	 */
	public void validate( CreateOperationalLimitSetCommand operationalLimitSet )throws Exception {
		Assert.notNull( operationalLimitSet, "CreateOperationalLimitSetCommand should not be null" );
//		Assert.isNull( operationalLimitSet.getOperationalLimitSetId(), "CreateOperationalLimitSetCommand identifier should be null" );
	}

	/**
	 * handles update validation for a OperationalLimitSet
	 */
	public void validate( UpdateOperationalLimitSetCommand operationalLimitSet ) throws Exception {
		Assert.notNull( operationalLimitSet, "UpdateOperationalLimitSetCommand should not be null" );
		Assert.notNull( operationalLimitSet.getOperationalLimitSetId(), "UpdateOperationalLimitSetCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a OperationalLimitSet
	 */
    public void validate( DeleteOperationalLimitSetCommand operationalLimitSet ) throws Exception {
		Assert.notNull( operationalLimitSet, "{commandAlias} should not be null" );
		Assert.notNull( operationalLimitSet.getOperationalLimitSetId(), "DeleteOperationalLimitSetCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a OperationalLimitSet
	 */
	public void validate( OperationalLimitSetFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "OperationalLimitSetFetchOneSummary should not be null" );
		Assert.notNull( summary.getOperationalLimitSetId(), "OperationalLimitSetFetchOneSummary identifier should not be null" );
	}


	/**
	 * handles add to OperationalLimitSet validation for a OperationalLimitSet
	 * 
	 * @param	command AssignOperationalLimitSetToOperationalLimitSetCommand
	 */	
	public void validate( AssignOperationalLimitSetToOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "AssignOperationalLimitSetToOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "AssignOperationalLimitSetToOperationalLimitSetCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignOperationalLimitSetToOperationalLimitSetCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from OperationalLimitSet validation for a OperationalLimitSet
	 * 
	 * @param	command RemoveOperationalLimitSetFromOperationalLimitSetCommand
	 */	
	public void validate( RemoveOperationalLimitSetFromOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "RemoveOperationalLimitSetFromOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "RemoveOperationalLimitSetFromOperationalLimitSetCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveOperationalLimitSetFromOperationalLimitSetCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getOperationalLimitSetId(), "RemoveOperationalLimitSetFromOperationalLimitSetCommand removeFrom attribubte identifier should not be null" );
	}
	

}
