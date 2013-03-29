package com.toastedbits.bookish.launch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toastedbits.bookish.domain.PlatformDetails;
import com.toastedbits.bookish.exceptions.UsernameAlreadyExistsException;
import com.toastedbits.bookish.services.AuthorityService;
import com.toastedbits.bookish.services.CategoryService;
import com.toastedbits.bookish.services.PlatformService;
import com.toastedbits.bookish.services.UserService;

public class InitDB {
	private static final Logger LOG = LoggerFactory.getLogger(InitDB.class);
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private PlatformService platform;
	
	public void start() throws UsernameAlreadyExistsException {
		LOG.debug("Loading bookish platform");
		PlatformDetails details = platform.getPlatformDetails();
		if(details.isFirstTimeSetup()) {
			LOG.debug("First Time Setup enabled");
			catService.createCategoryRoot();
			authService.addAuthority("ROLE_ADMIN");
			userService.createAdminUser("admin", "admin");
		}
	}
}