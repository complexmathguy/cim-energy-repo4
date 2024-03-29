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
 * Projector for ReactivePower as outlined for the CQRS pattern.
 * 
 * Commands are handled by ReactivePowerAggregate
 * 
 * @author your_name_here
 *
 */
@Component("reactivePower-entity-projector")
public class ReactivePowerEntityProjector {
		
	// core constructor
	public ReactivePowerEntityProjector(ReactivePowerRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ReactivePower
	 * 
     * @param	entity ReactivePower
     */
    public ReactivePower create( ReactivePower entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ReactivePower
	 * 
     * @param	entity ReactivePower
     */
    public ReactivePower update( ReactivePower entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ReactivePower
	 * 
     * @param	id		UUID
     */
    public ReactivePower delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ReactivePower entity = repository.findById(id).get();
	    
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
     * @return	ReactivePower
     */
    public ReactivePower assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    ReactivePower parentEntity = repository.findById( parentId ).get();
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
	 * @return	ReactivePower
	 */
	public ReactivePower unAssignValue( UUID parentId ) {
		ReactivePower parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the ReactivePower via an FindReactivePowerQuery
     * @return 	query	FindReactivePowerQuery
     */
    @SuppressWarnings("unused")
    public ReactivePower find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ReactivePower - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ReactivePowers
     *
     * @param	query	FindAllReactivePowerQuery 
     * @return 	List<ReactivePower> 
     */
    @SuppressWarnings("unused")
    public List<ReactivePower> findAll( FindAllReactivePowerQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ReactivePower - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ReactivePowerRepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "energyConsumer-entity-projector")
	EnergyConsumerEntityProjector EnergyConsumerProjector;
    @Autowired
	@Qualifier(value = "externalNetworkInjection-entity-projector")
	ExternalNetworkInjectionEntityProjector ExternalNetworkInjectionProjector;
    @Autowired
	@Qualifier(value = "svInjection-entity-projector")
	SvInjectionEntityProjector SvInjectionProjector;
    @Autowired
	@Qualifier(value = "svPowerFlow-entity-projector")
	SvPowerFlowEntityProjector SvPowerFlowProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ReactivePowerEntityProjector.class.getName());

}
