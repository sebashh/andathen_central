package nl.andathen.central.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import nl.andathen.central.dao.SkillDao;
import nl.andathen.central.domain.character.Skill;

@Named
@SessionScoped
public class SkillBean implements Serializable {
	private static final long serialVersionUID = 3348704296656910472L;
	@EJB
	private SkillDao skillDao;
	private SortedSet<Skill> skills;
	private HashMap<Long, Skill> skillMap;
	private Skill skill;

	@PostConstruct
	public void init() {
		skills = new TreeSet<>();
		skillMap = new HashMap<>();
		skills.addAll(skillDao.get());
		for (Skill r: skills) {
			skillMap.put(r.getId(), r);
		}
		this.skill = new Skill();
	}
	
	public SortedSet<Skill> getSkills() {
		return skills;
	}
	
	public List<Skill> getPrerequisites() {
		List<Skill> result = new ArrayList<>();
		for (Skill s: skills) {
			if (checkValidPrerequisite(this.getSkill(), s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	// Check whether the potential is valid to be a prerequisite of target
	private boolean checkValidPrerequisite(Skill target, Skill potential) {
		if (potential.equals(target)) {
			return false;
		}
		else if (potential.getPrerequisite() == null) {
			return true;
		}
		else if (potential.getPrerequisite().equals(target)) {
			return false;
		}
		else {
			return checkValidPrerequisite(potential, potential.getPrerequisite());
		}
	}
	
	@Transactional
	public String submit() {
		skillDao.merge(skill);
		init();
		return "manage-skills?faces-redirect=true";
	}
	
	@Transactional
	public String add() {
		skillDao.create(skill);
		init();
		return "manage-skills?faces-redirect=true";
	}
	
	public String create() {
	    return "add-skill";
	}
	
	public String cancel() {
		return "manage-skills?faces-redirect=true";
	}
	
	public String edit(Skill r) throws IOException {
		this.skill.setId(r.getId());
		this.skill.setName(r.getName());
		this.skill.setDescription(r.getDescription());
		this.skill.setPlayerNotes(r.getPlayerNotes());
		this.skill.setCategory(r.getCategory());
		this.skill.setAgeExperience(r.isAgeExperience());
		this.skill.setPrerequisite(r.getPrerequisite());
		return "skill-details";
	}
	
	@Transactional
	public void delete(Skill r) {
		Skill res = skillDao.get(r.getName());
		if (res != null) {
			skillDao.remove(res);
			init();
		}
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public String goToMainPage() {
	    return "index?faces-redirect=true";
	}
}
