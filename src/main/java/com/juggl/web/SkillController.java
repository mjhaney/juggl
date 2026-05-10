package com.juggl.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juggl.domain.Skill;
import com.juggl.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillRepository skillRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills";
    }

    @GetMapping("/new")
    public String newSkillForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skill-form";
    }

    @GetMapping("/{id}/edit")
    public String editSkillForm(@PathVariable Long id, Model model) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        model.addAttribute("skill", skill);
        return "skill-form";
    }

    @PostMapping
    public String save(@ModelAttribute Skill skill) {
        skillRepository.save(skill);
        return "redirect:/skills";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        skillRepository.deleteById(id);
        return "redirect:/skills";
    }
}