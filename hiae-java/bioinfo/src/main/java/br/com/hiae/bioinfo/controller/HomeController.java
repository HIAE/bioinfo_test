package br.com.hiae.bioinfo.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class HomeController {
	
	private final Result result;
	
	protected HomeController() {
		this(null);
	}
	
	@Inject
	public HomeController(Result result) {
		this.result = result;
	}
	
	@Get("/")
	public void home(String geneName) {
		
	}


}
