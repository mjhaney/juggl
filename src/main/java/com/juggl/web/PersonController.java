package com.juggl.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juggl.domain.ExpertiseLevel;
import com.juggl.domain.Person;
import com.juggl.domain.PersonSkill;
import com.juggl.domain.Skill;
import com.juggl.repository.PersonRepository;
import com.juggl.repository.PersonSkillRepository;
import com.juggl.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final SkillRepository skillRepository;
    private final PersonSkillRepository personSkillRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "persons";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @GetMapping("/{id}/edit")
    public String editPersonForm(@PathVariable Long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow();
        model.addAttribute("person", person);
        model.addAttribute("allSkills", skillRepository.findAll());
        model.addAttribute("expertiseLevels", ExpertiseLevel.values());
        return "person-detail";
    }

    @PostMapping
    public String save(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/persons";
    }

    @PostMapping("/{personId}/skills")
    public String addSkill(@PathVariable Long personId, @RequestParam Long skillId, @RequestParam ExpertiseLevel level) {
        Person person = personRepository.findById(personId).orElseThrow();
        Skill skill = skillRepository.findById(skillId).orElseThrow();

        // Check if person already has this skill
        boolean alreadyHasSkill = person.getSkills().stream()
            .anyMatch(ps -> ps.getSkill().getId().equals(skillId));

        if (!alreadyHasSkill) {
            person.addSkill(skill, level);
            personRepository.save(person);
        }

        return "redirect:/persons/" + personId + "/edit";
    }

    @PostMapping("/{personId}/skills/{skillId}/update")
    public String updateSkillLevel(@PathVariable Long personId, @PathVariable Long skillId, @RequestParam ExpertiseLevel level) {
        Person person = personRepository.findById(personId).orElseThrow();
        PersonSkill personSkill = person.getSkills().stream()
            .filter(ps -> ps.getSkill().getId().equals(skillId))
            .findFirst().orElseThrow();

        personSkill.setLevel(level);
        personSkillRepository.save(personSkill);

        return "redirect:/persons/" + personId + "/edit";
    }

    @PostMapping("/{personId}/skills/{skillId}/delete")
    public String removeSkill(@PathVariable Long personId, @PathVariable Long skillId) {
        Person person = personRepository.findById(personId).orElseThrow();
        person.getSkills().removeIf(ps -> ps.getSkill().getId().equals(skillId));
        personRepository.save(person);

        return "redirect:/persons/" + personId + "/edit";
    }
}