/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package task7.ui.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task7.ui.entities.Group;
import task7.ui.entities.Student;
import task7.ui.repositories.GroupRepository;
import task7.ui.repositories.StudentRepository;

/**
 * @author Rob Winch
 */
@Controller
public class MainController {
	private GroupRepository groupRepository;
	private StudentRepository studentRepository;

	@Autowired
	public void setGroupRepository(GroupRepository groupRepository){
		this.groupRepository = groupRepository;
	}

	@Autowired
	public void setStudentRepository(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

	@GetMapping("/")
	public ModelAndView listGroups() {
		return new ModelAndView("list", "groups", groupRepository.findAll());
	}

	@GetMapping("/add")
	public String getAdd(@ModelAttribute Group group, @ModelAttribute Student student) {
		return "add";
	}

	@PostMapping("/add")
	public ModelAndView postAdd(@ModelAttribute(value="group") @Valid Group group,
								BindingResult resultGroup,
								@ModelAttribute(value="student") @Valid Student student,
								BindingResult resultStudent,
								RedirectAttributes redirect) {
		System.out.println(student);
		System.out.println(group);
		System.out.println(group.getName());
		if (resultGroup.hasErrors() || resultStudent.hasErrors()) {
			return new ModelAndView("add");
//			return new ModelAndView("add", "groupErrors", resultGroup.getAllErrors());
		}
		if (resultStudent.hasErrors()) {
			return new ModelAndView("add", "studentErrors", resultStudent.getAllErrors());
		}

		Group dbGroup = groupRepository.findByName(group.getName()).orElse(null);
		if (dbGroup == null){
			groupRepository.save(group);
			dbGroup = group;
		}
		student.setGroup(dbGroup);
		studentRepository.save(student);

//		System.out.println("post add 2");
		redirect.addFlashAttribute("successMessage", "Successfully created a new message");
//		System.out.println("post add 3");
		return new ModelAndView("redirect:/");
	}
}
