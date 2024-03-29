/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.repository.*;

/**
 * Projector for VoltagePerReactivePower as outlined for the CQRS pattern.
 * 
 * Commands are handled by VoltagePerReactivePowerAggregate
 * 
 * @author your_name_here
 *
 */
@Component("voltagePerReactivePower-entity-projector")
public class VoltagePerReactivePowerEntityProjector {
		
	// core constructor
	public VoltagePerReactivePowerEntityProjector(VoltagePerReactivePowerRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a VoltagePerReactivePower
	 * 
     * @param	entity VoltagePerReactivePower
     */
    public VoltagePerReactivePower create( VoltagePerReactivePower entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a VoltagePerReactivePower
	 * 
     * @param	entity VoltagePerReactivePower
     */
    public VoltagePerReactivePower update( VoltagePerReactivePower entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a VoltagePerReactivePower
	 * 
     * @param	id		UUID
     */
    public VoltagePerReactivePower delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    VoltagePerReactivePower entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Value
     * 
     * @param	parentId	UUID
     * @param	assignment 	FloatProxy 
     * @return	VoltagePerReactivePower
     */
    public VoltagePerReactivePower assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    VoltagePerReactivePower parentEntity = repository.findById( parentId ).get();
	    assignment = FloatProxyProjector.find(assignment.getFloatProxyId());
	    
	    // ------------------------------------------
		// assign the Value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Value
	 * 
	 * @param	parentId		UUID
	 * @return	VoltagePerReactivePower
	 */
	public VoltagePerReactivePower unAssignValue( UUID parentId ) {
		VoltagePerReactivePower parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the VoltagePerReactivePower via an FindVoltagePerReactivePowerQuery
     * @return 	query	FindVoltagePerReactivePowerQuery
     */
    @SuppressWarnings("unused")
    public VoltagePerReactivePower find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a VoltagePerReactivePower - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all VoltagePerReactivePowers
     *
     * @param	query	FindAllVoltagePerReactivePowerQuery 
     * @return 	List<VoltagePerReactivePower> 
     */
    @SuppressWarnings("unused")
    public List<VoltagePerReactivePower> findAll( FindAllVoltagePerReactivePowerQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all VoltagePerReactivePower - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final VoltagePerReactivePowerRepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "shuntCompensator-entity-projector")
	ShuntCompensatorEntityProjector ShuntCompensatorProjector;
    @Autowired
	@Qualifier(value = "staticVarCompensator-entity-projector")
	StaticVarCompensatorEntityProjector StaticVarCompensatorProjector;

    private static final Logger LOGGER 	= Logger.getLogger(VoltagePerReactivePowerEntityProjector.class.getName());

}
