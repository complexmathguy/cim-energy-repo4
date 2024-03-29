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
 * Projector for LoadUserDefined as outlined for the CQRS pattern.
 * 
 * Commands are handled by LoadUserDefinedAggregate
 * 
 * @author your_name_here
 *
 */
@Component("loadUserDefined-entity-projector")
public class LoadUserDefinedEntityProjector {
		
	// core constructor
	public LoadUserDefinedEntityProjector(LoadUserDefinedRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a LoadUserDefined
	 * 
     * @param	entity LoadUserDefined
     */
    public LoadUserDefined create( LoadUserDefined entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a LoadUserDefined
	 * 
     * @param	entity LoadUserDefined
     */
    public LoadUserDefined update( LoadUserDefined entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a LoadUserDefined
	 * 
     * @param	id		UUID
     */
    public LoadUserDefined delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    LoadUserDefined entity = repository.findById(id).get();
	    
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
     * @return	LoadUserDefined
     */
    public LoadUserDefined assignProprietary( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning Proprietary as " + assignment.toString() );

	    LoadUserDefined parentEntity = repository.findById( parentId ).get();
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
	 * @return	LoadUserDefined
	 */
	public LoadUserDefined unAssignProprietary( UUID parentId ) {
		LoadUserDefined parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the LoadUserDefined via an FindLoadUserDefinedQuery
     * @return 	query	FindLoadUserDefinedQuery
     */
    @SuppressWarnings("unused")
    public LoadUserDefined find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a LoadUserDefined - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all LoadUserDefineds
     *
     * @param	query	FindAllLoadUserDefinedQuery 
     * @return 	List<LoadUserDefined> 
     */
    @SuppressWarnings("unused")
    public List<LoadUserDefined> findAll( FindAllLoadUserDefinedQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all LoadUserDefined - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final LoadUserDefinedRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;

    private static final Logger LOGGER 	= Logger.getLogger(LoadUserDefinedEntityProjector.class.getName());

}
