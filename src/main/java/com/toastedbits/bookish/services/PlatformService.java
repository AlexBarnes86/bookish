package com.toastedbits.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toastedbits.bookish.domain.PlatformDetails;
import com.toastedbits.bookish.repositories.PlatformRepository;

//TODO: this should probably be rewritten a bit to directly manage and ensure only one PlatformDetails instance rather than rely on a repository
@Service
public class PlatformService {
	@Autowired
	private PlatformRepository platformRepo;
	
	private PlatformDetails details = null;
	
	public PlatformDetails getPlatformDetails() {
		if(details == null) {
			details = platformRepo.findByPropertyValue(PlatformDetails.PLATFORM_DETAILS_SINGLETON_ID, PlatformDetails.PLATFORM_DETAILS_SINGLETON_ID);
			if(details == null) {
				details = new PlatformDetails();
				platformRepo.save(details);
			}
		}
		
		return details;
	}
}
