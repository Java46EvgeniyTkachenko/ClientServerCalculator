package telran.net.test;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorMenu {
public static Calculator Calculator;

private static double	res;

	public static Item getCalculatorItems(String name, Calculator client) throws Exception {
		CalculatorMenu.Calculator  = client;
		return Item.of(name, io -> {
			Item [] items = {
			Item.of("Add two numbers", CalculatorMenu::	addTwoNumbers),				
			Item.of("Subtract two numbers", CalculatorMenu::subtractTwoNumbers),
			Item.of("Divide two numbers", CalculatorMenu::divideTwoNumbers),
			Item.of("Multiply two numbers", CalculatorMenu::multiplyTwoNumbers),
			Item.exit()
		};
			Menu menu = new Menu(name, items);
			menu.perform(io);
		});	
		
	}
	
	
		static double[] getTwoNumbers(InputOutput io) {
		double firstNumber = io.readInt("Enter first number", "no number");
		double secondNumber = io.readInt("Enter second number","no number");
		return new double[] {firstNumber, secondNumber};
	}
	static void addTwoNumbers(InputOutput io)  {
		double [] numbers = getTwoNumbers(io);
			res =  Calculator.add(numbers[0],numbers[1]);
		io.writeLine(res);
	}
	static void subtractTwoNumbers(InputOutput io) {
		double [] numbers = getTwoNumbers(io);

		res = Calculator.subtract(numbers[0],numbers[1]);
		io.writeLine(res);
	}
	static void divideTwoNumbers(InputOutput io) {
		double [] numbers = getTwoNumbers(io);

		res = Calculator.divide(numbers[0],numbers[1]);
		io.writeLine(res);
	}
	static void multiplyTwoNumbers(InputOutput io) {
		double [] numbers = getTwoNumbers(io);
	
		res = Calculator.multiply(numbers[0],numbers[1]);
		io.writeLine(res);
	}

}
