package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Developer;
import com.example.demo.models.Organization;
import com.example.demo.models.Position;
import com.example.demo.models.Skill;
import com.example.demo.services.PlatformService;
import com.example.demo.validator.DeveloperValidator;
import com.example.demo.validator.OrgValidator;
import com.example.demo.validator.PositionValidator;

@Controller
public class PlatformController {

	@Autowired
	PlatformService service;

	@Autowired
	DeveloperValidator dvalidate;
	@Autowired
	OrgValidator ovalidate;

	@Autowired
	PositionValidator pvalidate;

	@GetMapping("/devSignup")
	public String devSignUp(@ModelAttribute("developer") Developer dev) {
		return "devSignup.jsp";
	}

	@PostMapping("/newdev")
	public String newdev(@Valid @ModelAttribute("developer") Developer dev, BindingResult result, HttpSession session) {
		dvalidate.validate(dev, result);
		if (result.hasErrors()) {
			return "devSignup.jsp";
		}
		service.createDev(dev);
		session.setAttribute("devid", dev.getId());
		return "redirect:/devlogin";
	}

	@GetMapping("/orgSignup")
	public String orgSignUp(@ModelAttribute("organization") Organization org) {
		return "OrgSignUp.jsp";
	}

	@PostMapping("/newOrg")
	public String newOrg(@Valid @ModelAttribute("organization") Organization org, BindingResult result,
			HttpSession session) {
		ovalidate.validate(org, result);
		if (result.hasErrors()) {
			return "OrgSignUp.jsp";
		}
		service.createOrg(org);
		 session.setAttribute("orgId", org.getId());
		return "redirect:/orgSignup";
	}

	@GetMapping("/devlogin")
	public String devlogin(@ModelAttribute("developer") Developer dev) {
		return "devlogin.jsp";
	}

	@PostMapping("/devlogin")
	public String devSignin(@Valid @ModelAttribute("developer") Developer dev, BindingResult result, Model model,
			HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {

		boolean authenticate = service.authenticateDev(email, password);
		if (!authenticate) {
			model.addAttribute("error", "Email and/or password do not match");
			return "devlogin.jsp";
		}
		
		dev = service.findDeveloperByEmail(email);
		if (session.getAttribute("devid")==null) {
			session.setAttribute("devid", dev.getId());
		}
		session.setAttribute("devid", dev.getId());

		return "redirect:/devskills";
	}

	@GetMapping("/orglogin")
	public String orglogin(@ModelAttribute("organization") Organization org) {
		return "orglogin.jsp";
	}

	@PostMapping("/orglogin")
	public String orgSignin(@ModelAttribute("organization") Organization org, BindingResult result, Model model,
			HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {

		boolean authenticate = service.authenticateOrg(email, password);
		if (!authenticate) {
			model.addAttribute("error", "Hiring Manager email and/or password do not match");
			return "orglogin.jsp";
		}
		org = service.findOrgByEmail(email);
		session.setAttribute("orgId", org.getId());
		return "redirect:/orgdashboard";
	}

	@GetMapping("/devskills")
	public String devskills(@ModelAttribute("developer") Developer dev, HttpSession session,Model model) {
		List<Skill> skills = service.findAllSkills();
		List<Skill> langs = new ArrayList<Skill>();;
		for(int i = 0; i < skills.size();i++) {
			if(skills.get(i).isLanguage()) {
				langs.add(skills.get(i));
			}
		}
		dev = service.findDeveloper((Long) session.getAttribute("devid"));

		model.addAttribute("skills",langs);
		model.addAttribute("dev",dev);
		
		return "devskill.jsp";
	}

	@GetMapping("/orgdashboard")
	public String orgdash(Model model, HttpSession session) {
		if (session.getAttribute("orgId") == null) {
			return "orglogin.jsp";
		}
		Long id = (Long) session.getAttribute("orgId");
		Organization org = service.findOrgById(id);
		
		model.addAttribute("org", org);
		return "orgdashboard.jsp";
	}

	@GetMapping("/newPosition")
	public String newPosition(@ModelAttribute("position") Position pos, HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("orgId");
		Organization org = service.findOrgById(id);
		List<Skill> skills = service.findAllSkills();
		model.addAttribute("skills", skills);

		return "newPosition.jsp";
	}

	@PostMapping("/newPosition")
	public String createPosition(@Valid @ModelAttribute("position") Position position, BindingResult result,
			@RequestParam("posSkills") ArrayList<Long> skillz, HttpSession session, HttpServletRequest request) {
		
		Long id = (Long) session.getAttribute("orgId");
		Organization org = service.findOrgById(id);
		position.setOrganization(org);
		ArrayList<Skill> skills = new ArrayList<Skill>();
			
		for (int i = 0; i < skillz.size(); i++) {	
			Long d = skillz.get(i);
			Skill s = service.findSkill(d);		
			skills.add(s);
		}
		
		System.out.println(skills);
		pvalidate.validate(position, result);
		if (result.hasErrors()) {
			return "newPosition.jsp";
		}
		
		// position.setPosSkills(skillz);
		service.savePosition(position);
		return "redirect:/orgdashboard";
	}
	
	
	@GetMapping("/devskillsss")
	public String devskills( @ModelAttribute("developer") Developer dev,BindingResult result,
			HttpSession session,Model model,@PathVariable("id")Long id) {
		if(session.getAttribute("devid")==null) {
			return"redirect:/devlogin";
		}
		
		Developer dev1 = service.findDeveloper(id);
		dev1.setMyskills(dev.getMyskills());
		dev1.setBiography(dev.getBiography());
		service.saveDev(dev1);
		session.setAttribute("devid", dev1.getId());
					
		
		return "redirect:/devtools/{id}";
	}
	

	
	@PostMapping("/devtools/{id}")
	public String devtools(@Valid @ModelAttribute("developer") Developer dev,BindingResult result,
			HttpSession session,Model model,@PathVariable("id")Long id) {
		dvalidate.validate(dev, result);
		if (result.hasErrors()) {
			return "devskill.jsp";
		}
		service.saveDev(dev);
		dev = service.findDeveloper(id);
		System.out.println(dev.getMyskills());
		List<Skill> skills = service.findAllSkills();
		List<Skill> frameworks = new ArrayList<Skill>();
		for(int i = 0; i < skills.size();i++) {
			if(skills.get(i).isFramework()) {
				frameworks.add(skills.get(i));
			}
		}
		System.out.println(dev.getId());
		model.addAttribute("dev",dev);		
		session.setAttribute("devid", dev.getId());
		model.addAttribute("frameworks",frameworks);
		return"devtool.jsp";
	}
	
	@GetMapping("/devdashboard")
	public String devdash(HttpSession session,Model model) {
		Long id = (Long) session.getAttribute("devid");
		Developer dev = service.findDeveloper(id);
		model.addAttribute("dev",dev);
		List<Position> positions = service.findAllPositions();
		model.addAttribute("positions",positions);
		List<Organization> companies = service.findAllOrgs();
		model.addAttribute("orgs",companies);
		return"devdashboard.jsp";
	}
	
	@GetMapping("/organization/{id}")
	public String orgJobPortal(Model model,@PathVariable("id")Long id,HttpSession session) {
		Organization org = service.findOrgById(id);
		Long devid = (Long) session.getAttribute("devid");
		Developer dev = service.findDeveloper(devid);
		model.addAttribute("org",org);
		model.addAttribute("dev",dev);
		return"orgJobPortal.jsp";
	}
}
