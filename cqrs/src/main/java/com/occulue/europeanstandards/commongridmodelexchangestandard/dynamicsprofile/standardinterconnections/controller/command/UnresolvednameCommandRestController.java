/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardinterconnections..controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import com.occulue.controller.command.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity Unresolvedname.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Unresolvedname")
public class UnresolvednameCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a Unresolvedname.  if not key provided, calls create, otherwise calls save
     * @param		Unresolvedname	unresolvedname
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateUnresolvednameCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = UnresolvednameBusinessDelegate.getUnresolvednameInstance().createUnresolvedname( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Unresolvedname.  if no key provided, calls create, otherwise calls save
     * @param		Unresolvedname unresolvedname
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateUnresolvednameCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateUnresolvednameCommand
			// -----------------------------------------------
			completableFuture = UnresolvednameBusinessDelegate.getUnresolvednameInstance().updateUnresolvedname(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "UnresolvednameController:update() - successfully update Unresolvedname - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Unresolvedname entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID unresolvednameId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteUnresolvednameCommand command = new DeleteUnresolvednameCommand( unresolvednameId );

    	try {
        	UnresolvednameBusinessDelegate delegate = UnresolvednameBusinessDelegate.getUnresolvednameInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Unresolvedname with key " + command.getUnresolvednameId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	



//************************************************************************    
// Attributes
//************************************************************************
    protected Unresolvedname unresolvedname = null;
    private static final Logger LOGGER = Logger.getLogger(UnresolvednameCommandRestController.class.getName());
    
}
