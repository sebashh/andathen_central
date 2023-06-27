package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.LanguageDao;
import nl.andathen.central.domain.Language;

@Named
@SessionScoped
public class LanguageBean implements Serializable, IImageUploader {
	private static final long serialVersionUID = 1068390508730479865L;
	@EJB
	private LanguageDao languageDao;
	private SortedSet<Language> languages;
	private HashMap<Long, Language> languageMap;
	private Language language;
	private Part uploadedFile;

	@PostConstruct
	public void init() {
		languages = new TreeSet<>();
		languageMap = new HashMap<>();
		languages.addAll(languageDao.get());
		for (Language r: languages) {
			languageMap.put(r.getId(), r);
		}
		this.language = new Language();
	}
	
	public SortedSet<Language> getLanguages() {
		return languages;
	}
	
	@Transactional
	public String submit() {
		Language del = languageMap.get(language.getId());
		languages.remove(del);
		languageMap.remove(del.getId());
		Language sk = languageDao.merge(language);
		languages.add(sk);
		languageMap.put(sk.getId(), sk);
		this.language = new Language();
		return "managelanguages?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		languageDao.create(language);
		language = languageDao.get(language.getId());
		languages.add(language);
		languageMap.put(language.getId(), language);
		this.language = new Language();
		return "manage-languages?faces-redirect=true";
	}
	
	public String create() {
	    return "add-language";
	}
	
	public String cancel() {
		return "managelanguages?faces-redirect=true";
	}
	
	public String edit(Language r) throws IOException {
		this.language.setId(r.getId());
		this.language.setName(r.getName());
		this.language.setIso(r.getIso());
		this.language.setType(r.getType());
		this.language.setDescription(r.getDescription());
		this.language.setImage(r.getImage());
		return "language-details";
	}
	
	@Transactional
	public void delete(Language r) {
		Language res = languageDao.get(r.getIso());
		if (res != null) {
			languageDao.remove(res);
			init();
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}

	@Override
	public void setImage(BufferedImage img) {
		language.setImage(img);
	}
}
