package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import nl.andathen.central.dao.OrganizationDao;
import nl.andathen.central.dao.SourceDao;
import nl.andathen.central.domain.AccessLevel;
import nl.andathen.central.domain.Organization;
import nl.andathen.central.domain.Source;

@Named
@SessionScoped
public class SourceBean implements Serializable, IImageUploader {
	private static final long serialVersionUID = -8138655797854512684L;
	@EJB
	private SourceDao sourceDao;
	@EJB
	private OrganizationDao organizationDao;
	private List<Source> sources;
	private HashMap<Long, Source> sourceMap;
	private List<Organization> organizationOptions;
 	private Source source;
 	private AccessLevel filterAccessLevelSources = null;
 	private AccessLevel filterAccessLevelOrganization = AccessLevel.L0;
	private Part uploadedFile;
 	
	@PostConstruct
	public void init()
	{
		sources = new ArrayList<>();
		sources.addAll(sourceDao.get());
		sources.sort(null);
		sourceMap = new HashMap<>();
		for (Source src: sources) {
			sourceMap.put(src.getId(), src);
		}
		organizationOptions = new ArrayList<>();
		organizationOptions.addAll(organizationDao.get());
		organizationOptions.sort(null);
		
		this.source = new Source();
	}
	
	public SourceBean() {

	}

	@Transactional
	public String submit() {
		Source del = sourceMap.get(source.getId());
		sources.remove(del);
		sourceMap.remove(del.getId());
		Source sk = sourceDao.merge(source);
		sources.add(sk);
		sourceMap.put(sk.getId(), sk);
		sources.sort(null);
		this.source = new Source();
		return "manage-sources?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		if ((source.getName() != null) && (source.getName().length() > 0)) {
			source.setRegistrationNumber(Math.abs((long) source.getName().hashCode() * (long) source.getDescription().hashCode()) / 2l); // Change to a more meaningful number later
			sourceDao.create(source);
			sources.add(source);
			sources.sort(null);
			sourceMap.put(source.getId(), source);
			this.source = new Source();
			return "manage-sources?faces-redirect=true";
		}
		else {
			return "";
		}
	}
	
	public String edit(Source src) throws IOException {
		this.source.setId(src.getId());
		this.source.setRegistrationNumber(src.getRegistrationNumber());;
		this.source.setName(src.getName());
		this.source.setDescription(src.getDescription());
		this.source.setAccessLevel(src.getAccessLevel());
		this.source.setOwner(src.getOwner());
		setFilterAccessLevelOrganization(src.getOwner().getAccessLevel());
		updateOrganizations(null);
		return "source-details";
	}

	@Transactional
	public void delete(Source src) {
		Source res = sourceDao.get(src.getId());
		if (res != null) {
			sourceDao.remove(res);
			init();
		}
	}

	public void updateOrganizations(AjaxBehaviorEvent event) {
		organizationOptions.clear();
		if (filterAccessLevelOrganization != null) {
			organizationOptions.addAll(organizationDao.getOnAccessLevel(filterAccessLevelOrganization));
		}
		else {
			organizationOptions.addAll(organizationDao.get());
		}
		organizationOptions.sort(null);
	}

	public void updateSources(AjaxBehaviorEvent event) {
		sources.clear();
		if (filterAccessLevelSources != null) {
			sources.addAll(sourceDao.getOnAccessLevel(filterAccessLevelSources));
		}
		else {
			sources.addAll(sourceDao.get());
		}
		sources.sort(null);
	}
	
	public Source getSource() {
		return this.source;
	}
	
	public AccessLevel getFilterAccessLevelSources() {
		return this.filterAccessLevelSources;
	}
	
	public void setFilterAccessLevelSources(AccessLevel lvl) {
		this.filterAccessLevelSources = lvl;
	}
	
	public AccessLevel getFilterAccessLevelOrganization() {
		return this.filterAccessLevelOrganization;
	}
	
	public void setFilterAccessLevelOrganization(AccessLevel lvl) {
		this.filterAccessLevelOrganization = lvl;
	}
	
	public List<Source> getSources(){
		return this.sources;
	}
	
	public List<Organization> getOrganizationOptions() {
		return this.organizationOptions;
	}
	
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String create() {
	    return "add-source";
	}
	
	public String cancel() {
		return "manage-sources?faces-redirect=true";
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}

	@Override
	public void setImage(BufferedImage img) {
		source.setImage(img);
	}
}
