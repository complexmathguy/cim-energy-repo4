/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.winddynamics..projector;

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
 * Projector for WindAeroConstIEC as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindAeroConstIECAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windAeroConstIEC-entity-projector")
public class WindAeroConstIECEntityProjector {
		
	// core constructor
	public WindAeroConstIECEntityProjector(WindAeroConstIECRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindAeroConstIEC
	 * 
     * @param	entity WindAeroConstIEC
     */
    public WindAeroConstIEC create( WindAeroConstIEC entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindAeroConstIEC
	 * 
     * @param	entity WindAeroConstIEC
     */
    public WindAeroConstIEC update( WindAeroConstIEC entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindAeroConstIEC
	 * 
     * @param	id		UUID
     */
    public WindAeroConstIEC delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindAeroConstIEC entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the WindAeroConstIEC via an FindWindAeroConstIECQuery
     * @return 	query	FindWindAeroConstIECQuery
     */
    @SuppressWarnings("unused")
    public WindAeroConstIEC find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindAeroConstIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindAeroConstIECs
     *
     * @param	query	FindAllWindAeroConstIECQuery 
     * @return 	List<WindAeroConstIEC> 
     */
    @SuppressWarnings("unused")
    public List<WindAeroConstIEC> findAll( FindAllWindAeroConstIECQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindAeroConstIEC - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindAeroConstIECRepository repository;

    private static final Logger LOGGER 	= Logger.getLogger(WindAeroConstIECEntityProjector.class.getName());

}
