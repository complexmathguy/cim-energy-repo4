/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels..projector;

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
 * Projector for PowerSystemStabilizerUserDefined as outlined for the CQRS pattern.
 * 
 * Commands are handled by PowerSystemStabilizerUserDefinedAggregate
 * 
 * @author your_name_here
 *
 */
@Component("powerSystemStabilizerUserDefined-entity-projector")
public class PowerSystemStabilizerUserDefinedEntityProjector {
		
	// core constructor
	public PowerSystemStabilizerUserDefinedEntityProjector(PowerSystemStabilizerUserDefinedRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PowerSystemStabilizerUserDefined
	 * 
     * @param	entity PowerSystemStabilizerUserDefined
     */
    public PowerSystemStabilizerUserDefined create( PowerSystemStabilizerUserDefined entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PowerSystemStabilizerUserDefined
	 * 
     * @param	entity PowerSystemStabilizerUserDefined
     */
    public PowerSystemStabilizerUserDefined update( PowerSystemStabilizerUserDefined entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PowerSystemStabilizerUserDefined
	 * 
     * @param	id		UUID
     */
    public PowerSystemStabilizerUserDefined delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PowerSystemStabilizerUserDefined entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Proprietary
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	PowerSystemStabilizerUserDefined
     */
    public PowerSystemStabilizerUserDefined assignProprietary( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning Proprietary as " + assignment.toString() );

	    PowerSystemStabilizerUserDefined parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the Proprietary to the parent entity
		// ------------------------------------------ 
	    parentEntity.setProprietary( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Proprietary
	 * 
	 * @param	parentId		UUID
	 * @return	PowerSystemStabilizerUserDefined
	 */
	public PowerSystemStabilizerUserDefined unAssignProprietary( UUID parentId ) {
		PowerSystemStabilizerUserDefined parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Proprietary on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Proprietary on the parent entithy
		// ------------------------------------------     
	    parentEntity.setProprietary(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the PowerSystemStabilizerUserDefined via an FindPowerSystemStabilizerUserDefinedQuery
     * @return 	query	FindPowerSystemStabilizerUserDefinedQuery
     */
    @SuppressWarnings("unused")
    public PowerSystemStabilizerUserDefined find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PowerSystemStabilizerUserDefined - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PowerSystemStabilizerUserDefineds
     *
     * @param	query	FindAllPowerSystemStabilizerUserDefinedQuery 
     * @return 	List<PowerSystemStabilizerUserDefined> 
     */
    @SuppressWarnings("unused")
    public List<PowerSystemStabilizerUserDefined> findAll( FindAllPowerSystemStabilizerUserDefinedQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PowerSystemStabilizerUserDefined - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PowerSystemStabilizerUserDefinedRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PowerSystemStabilizerUserDefinedEntityProjector.class.getName());

}
