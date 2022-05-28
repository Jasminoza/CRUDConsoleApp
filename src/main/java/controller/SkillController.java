package controller;

import model.Skill;
import repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final GsonSkillRepositoryImpl skillRepository = new GsonSkillRepositoryImpl();

    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.create(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public void deleteSkill(Long id) {
        skillRepository.delete(id);
    }
}
