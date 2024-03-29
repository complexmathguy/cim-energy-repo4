/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas..projector;

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
 * Projector for AccumulatorLimit as outlined for the CQRS pattern.
 * 
 * Commands are handled by AccumulatorLimitAggregate
 * 
 * @author your_name_here
 *
 */
@Component("accumulatorLimit-entity-projector")
public class AccumulatorLimitEntityProjector {
		
	// core constructor
	public AccumulatorLimitEntityProjector(AccumulatorLimitRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a AccumulatorLimit
	 * 
     * @param	entity AccumulatorLimit
     */
    public AccumulatorLimit create( AccumulatorLimit entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a AccumulatorLimit
	 * 
     * @param	entity AccumulatorLimit
     */
    public AccumulatorLimit update( AccumulatorLimit entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a AccumulatorLimit
	 * 
     * @param	id		UUID
     */
    public AccumulatorLimit delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    AccumulatorLimit entity = repository.findById(id).get();
	    
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
     * @param	assignment 	IntegerProxy 
     * @return	AccumulatorLimit
     */
    public AccumulatorLimit assignValue( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    AccumulatorLimit parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
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
	 * @return	AccumulatorLimit
	 */
	public AccumulatorLimit unAssignValue( UUID parentId ) {
		AccumulatorLimit parentEntity = repository.findById(parentId).get();

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


    /*
     * Add to the Limits
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	AccumulatorLimit
     */
    public AccumulatorLimit addToLimits( UUID parentId, AccumulatorLimit addTo ) {
	    LOGGER.info("handling AssignLimitsToAccumulatorLimitEvent - " );
	    
	    AccumulatorLimit parentEntity = repository.findById(parentId).get();
	    AccumulatorLimit child = AccumulatorLimitProjector.find(addTo.getAccumulatorLimitId());
	    
	    parentEntity.getLimits().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the Limits
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	AccumulatorLimit
     */
	public AccumulatorLimit removeFromLimits( UUID parentId, AccumulatorLimit removeFrom ) {
	    LOGGER.info("handling RemoveLimitsFromAccumulatorLimitEvent " );
	
	    AccumulatorLimit parentEntity = repository.findById(parentId).get();
	    AccumulatorLimit child = AccumulatorLimitProjector.find(removeFrom.getAccumulatorLimitId());
	    
	    parentEntity.getLimits().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}



    /**
     * Method to retrieve the AccumulatorLimit via an FindAccumulatorLimitQuery
     * @return 	query	FindAccumulatorLimitQuery
     */
    @SuppressWarnings("unused")
    public AccumulatorLimit find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a AccumulatorLimit - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all AccumulatorLimits
     *
     * @param	query	FindAllAccumulatorLimitQuery 
     * @return 	List<AccumulatorLimit> 
     */
    @SuppressWarnings("unused")
    public List<AccumulatorLimit> findAll( FindAllAccumulatorLimitQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all AccumulatorLimit - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final AccumulatorLimitRepository repository;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "accumulatorLimit-entity-projector")
	AccumulatorLimitEntityProjector AccumulatorLimitProjector;

    private static final Logger LOGGER 	= Logger.getLogger(AccumulatorLimitEntityProjector.class.getName());

}
