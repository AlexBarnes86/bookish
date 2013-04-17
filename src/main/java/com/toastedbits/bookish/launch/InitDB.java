package com.toastedbits.bookish.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.toastedbits.bookish.domain.Authority;
import com.toastedbits.bookish.domain.BookishUser;
import com.toastedbits.bookish.domain.Category;
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
	
	@Autowired
	private ResourceLoader loader;
	
	private String defaultCategoriesLocation;
	private String adminUser;
	private String adminPassword;
	
	public void start() {
		LOG.info("Loading Bookish Platform");
		if(platform.isFirstTimeSetup()) {
			LOG.info("First Time Setup Enabled");
			createDefaultCategories();
			forceUserIndexCreation();
			platform.setFirstTimeSetup(false);
		}
		createAdminAuthority();
		createAdminUser();
	}
	
	private void forceUserIndexCreation() {
		try {
			String forceIndexCreationAccount = "bookishtempphonyuser" + Math.random();
			userService.delete(userService.addUser(forceIndexCreationAccount, forceIndexCreationAccount));
		}
		catch(UsernameAlreadyExistsException e) {
			LOG.error("Error forcing user index creation");
		}
	}

	private void createDefaultCategories() {
		LOG.info("Creating Default Categories");
		Stack<Category> categories = new Stack<Category>();
		categories.add(catService.createCategoryRoot());
		Resource resource = loader.getResource(defaultCategoriesLocation);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			String line = "";
			while((line = br.readLine()) != null) {
				if(line.trim().isEmpty()) {
					continue;
				}
				
				int curIndent = line.lastIndexOf("\t")+1;
				
				if(curIndent < categories.size()-1) {
					for(int i = categories.size()-1; i > curIndent; --i) {
						//LOG.debug("Creating category: " + categories.peek().getName() + " with parent " + categories.peek().getParent().getName());
						catService.createCategory(categories.pop());
					}
				}
				
				Category nCat = new Category();
				nCat.setName(line.trim());
				nCat.setParent(categories.peek());
				categories.push(nCat);
			}
			
			while(!categories.isEmpty()) {
				//LOG.debug("Creating category: " + categories.peek().getName());
				catService.createCategory(categories.pop());
			}
		}
		catch(IOException e) {
			LOG.error("Error loading default categories", e);
		}
	}

	private void createAdminUser() {
		try {
			List<BookishUser> admins = userService.getAdminUsers();
			if(admins.size() == 0) {
				LOG.info("No admin user found, creating default admin user");
				userService.createAdminUser(adminUser, adminPassword);
			}
		}
		catch(UsernameAlreadyExistsException e) {
			LOG.error("Attempt to create admin user, but user already exists", e);
		}
	}

	private void createAdminAuthority() {
		if(authService.getAuthority(Authority.ROLE_ADMIN) == null) {
			authService.addAuthority(Authority.ROLE_ADMIN);
		}
	}

	public String getDefaultCategoriesLocation() {
		return defaultCategoriesLocation;
	}

	public void setDefaultCategoriesLocation(String defaultCategoriesLocation) {
		this.defaultCategoriesLocation = defaultCategoriesLocation;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}