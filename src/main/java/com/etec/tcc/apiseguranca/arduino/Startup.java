package com.etec.tcc.apiseguranca.arduino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.etec.tcc.apiseguranca.jdbc.ConexaoUtil;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class Startup {

	@Autowired
	private ConexaoUtil conexao;
	
	
	SerialPort activePort;
	SerialPort[] ports = SerialPort.getCommPorts();
	
	public void showAllPort() {
		int i = 0;
		for(SerialPort port : ports) {
			System.out.print(i + ". " + port.getDescriptivePortName() + " ");
			System.out.println(port.getPortDescription());
			i++;
			}
		
		}
	
	public void setPort(int portIndex) {
		activePort = ports[portIndex];
		
		if (activePort.openPort())
			System.out.println(activePort.getPortDescription() + " port opened. ");
		
		
		
		    		
		activePort.addDataListener(new SerialPortDataListener() {
			
			@Override
			public void serialEvent(SerialPortEvent event) {
				int size = event.getSerialPort().bytesAvailable();
				byte[] buffer = new byte[size];
				event.getSerialPort().readBytes(buffer, size);
				for(byte b:buffer)
					System.out.print((char)b);
			
				try {
					Connection connection = ConexaoUtil.getInstance().getConnection();
					String sql = "INSERT INTO Alerta(id_equipamento) VALUES (1)";
					
					PreparedStatement statement = connection.prepareStatement(sql);
					
					statement.execute();
					statement.close();
				   	} catch (Exception e) {
				   		e.printStackTrace();
				   	}
			}
				
			
			@Override
			public int getListeningEvents() { 
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
								}
			});
		}
	
	public void start() {
		showAllPort();
		Scanner reader = new Scanner(System.in);
		System.out.print("Port? ");
		int p = reader.nextInt();
		setPort(p);
		reader.close();
		}
	
	public static void main(String[] args) {
		Startup startup = new Startup();
		startup.start();
		}
	
	}




