package example.controller;

import java.util.Scanner;

public abstract class Controller {
	public Scanner sc;
	
	public abstract void doAction(String cmd, String methodName);
}
