package com.toastedbits.bookish.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toastedbits.bookish.domain.PlatformDetails;
import com.toastedbits.bookish.repositories.PlatformRepository;

//TODO: this should probably be rewritten a bit to directly manage and ensure only one PlatformDetails instance rather than rely on a repository
//TODO: Make this a singleton bean
@Service
public class PlatformService {
	private static final Logger LOG = LoggerFactory.getLogger(PlatformService.class);
	
	@Autowired
	private PlatformRepository platformRepo;
	
	private PlatformDetails details = null;
	
	private PlatformDetails getPlatformDetails() {
		if(details == null) {
			details = platformRepo.findByPropertyValue(PlatformDetails.SINGLETON_NAME, PlatformDetails.SINGLETON_NAME);
			if(details == null) {
				LOG.debug("Creating new platform details singleton");
				details = new PlatformDetails();
				details.setFirstTimeSetup(true);
				platformRepo.save(details);
				LOG.debug("Platform Singleton Name: " + platformRepo.findByPropertyValue(PlatformDetails.SINGLETON_NAME, PlatformDetails.SINGLETON_NAME).getPlatformSingletonName());
			}
		}
		
		return details;
	}
	
	public boolean isFirstTimeSetup() {
		return getPlatformDetails().isFirstTimeSetup();
	}
	
	public void setFirstTimeSetup(boolean setup) {
		if(setup != getPlatformDetails().isFirstTimeSetup()) {
			details.setFirstTimeSetup(setup);
			saveDetails();
		}
	}
	
	public void saveDetails() {
		if(details == null) {
			return;
		}
		
		platformRepo.save(details);
	}
}
