package telran.net.test;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorApp {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 3000;

	public static void main(String[] args) throws Exception {
		InputOutput io = new ConsoleInputOutput();

		NetCalculatorProxy client = new NetCalculatorProxy(HOST_NAME, PORT);

		Item[] items = { CalculatorMenu.getCalculatorItems("Calculator", client),

				Item.of("Exit", iop -> {
					try {
						client.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}, true) };
		Menu menu = new Menu("Calculator", items);
		menu.perform(io);

	}

}
