/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile..controller.command;

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
 * Implements Spring Controller command CQRS processing for entity DateProxy.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DateProxy")
public class DateProxyCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DateProxy.  if not key provided, calls create, otherwise calls save
     * @param		DateProxy	dateProxy
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDateProxyCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DateProxyBusinessDelegate.getDateProxyInstance().createDateProxy( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DateProxy.  if no key provided, calls create, otherwise calls save
     * @param		DateProxy dateProxy
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDateProxyCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDateProxyCommand
			// -----------------------------------------------
			completableFuture = DateProxyBusinessDelegate.getDateProxyInstance().updateDateProxy(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DateProxyController:update() - successfully update DateProxy - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DateProxy entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID dateProxyId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDateProxyCommand command = new DeleteDateProxyCommand( dateProxyId );

    	try {
        	DateProxyBusinessDelegate delegate = DateProxyBusinessDelegate.getDateProxyInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DateProxy with key " + command.getDateProxyId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	



//************************************************************************    
// Attributes
//************************************************************************
    protected DateProxy dateProxy = null;
    private static final Logger LOGGER = Logger.getLogger(DateProxyCommandRestController.class.getName());
    
}
