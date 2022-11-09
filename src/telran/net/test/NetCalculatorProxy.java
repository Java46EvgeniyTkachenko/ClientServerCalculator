package telran.net.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import telran.net.NetworkHandler;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class NetCalculatorProxy implements Calculator, NetworkHandler {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	public NetCalculatorProxy(String hostname, int port) throws Exception {		
		socket = new Socket(hostname, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());

	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String requestType, Serializable requestData) {
		Request request = new Request(requestType, requestData);
		try {
			output.writeObject(request);
			Response response = (Response) input.readObject();
			if (response.code != ResponseCode.OK) {
				throw new Exception(response.responseData.toString());
			}
			return (T) response.responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void close() throws IOException {
		output.close();
		input.close();
		socket.close();

	}

	@Override
	public  double add(double op1, double op2) {
		return send("add", numberToObject(op1, op2));
	}

	@Override
	public double subtract(double op1, double op2) {
		return send("subtract", numberToObject(op1, op2));
	}

	@Override
	public double divide(double op1, double op2) {
		return send("divide", numberToObject(op1, op2));
	}

	@Override
	public double multiply(double op1, double op2) {
		return send("multiply", numberToObject(op1, op2));
	}

	private Double[] numberToObject(double op1, double op2) {
		Double[] op = { op1, op2 };		
		return op;
	}
	

}
