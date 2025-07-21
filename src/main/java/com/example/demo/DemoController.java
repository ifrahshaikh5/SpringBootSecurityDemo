package com.example.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class DemoController {
	
	@GetMapping("/public")
	public String first()
	{
		return "hey, thsi os the public page";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String first1()
	{
		return "hey, this is admin page";
	}
	
	@PreAuthorize("hasRole('XYZ')")
	@GetMapping("/xyz")
	public String first3()
	{
		return "hey, this is normal page";
	}
}
