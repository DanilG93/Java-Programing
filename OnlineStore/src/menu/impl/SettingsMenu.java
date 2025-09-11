package menu.impl;


import configs.ApplicationContext;
import menu.Menu;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
			+ "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		// <write your code here>
	}

	@Override
	public void printMenuHeader() {
		// <write your code here>	
	}

}
