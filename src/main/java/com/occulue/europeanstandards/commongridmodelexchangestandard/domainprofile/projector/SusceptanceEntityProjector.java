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
 * Projector for Susceptance as outlined for the CQRS pattern.
 * 
 * Commands are handled by SusceptanceAggregate
 * 
 * @author your_name_here
 *
 */
@Component("susceptance-entity-projector")
public class SusceptanceEntityProjector {
		
	// core constructor
	public SusceptanceEntityProjector(SusceptanceRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Susceptance
	 * 
     * @param	entity Susceptance
     */
    public Susceptance create( Susceptance entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Susceptance
	 * 
     * @param	entity Susceptance
     */
    public Susceptance update( Susceptance entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Susceptance
	 * 
     * @param	id		UUID
     */
    public Susceptance delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Susceptance entity = repository.findById(id).get();
	    
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
     * @return	Susceptance
     */
    public Susceptance assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    Susceptance parentEntity = repository.findById( parentId ).get();
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
	 * @return	Susceptance
	 */
	public Susceptance unAssignValue( UUID parentId ) {
		Susceptance parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the Susceptance via an FindSusceptanceQuery
     * @return 	query	FindSusceptanceQuery
     */
    @SuppressWarnings("unused")
    public Susceptance find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Susceptance - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Susceptances
     *
     * @param	query	FindAllSusceptanceQuery 
     * @return 	List<Susceptance> 
     */
    @SuppressWarnings("unused")
    public List<Susceptance> findAll( FindAllSusceptanceQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Susceptance - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SusceptanceRepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "aCLineSegment-entity-projector")
	ACLineSegmentEntityProjector ACLineSegmentProjector;
    @Autowired
	@Qualifier(value = "linearShuntCompensator-entity-projector")
	LinearShuntCompensatorEntityProjector LinearShuntCompensatorProjector;
    @Autowired
	@Qualifier(value = "nonlinearShuntCompensatorPoint-entity-projector")
	NonlinearShuntCompensatorPointEntityProjector NonlinearShuntCompensatorPointProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SusceptanceEntityProjector.class.getName());

}
