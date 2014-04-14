import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;

//HI
@SuppressWarnings("serial")
public class chatGUI extends JFrame implements ActionListener{
	
	private JButton serverButton, clientButton;
	private JLabel welcomeLabel;
	private JPanel firstPanel, clientPanel;
	private JLabel connectLabel,chatLabel;
	private JButton clientConnect, clientDisconnect, clientExit;
	private JPanel clientButtonPanel;
	private JPanel clientConnects;
	private JTextArea clientChatWindow;
	private JPanel clientContainer;
	private JTextField clientTextArea;
	private JButton clientMultipleMessages;
	private JButton serverConnect;
	private JPanel serverPanel;
	private JTextField portTextField;
	private int serverPort;
	private JPanel serverInfoPanel;
	private JScrollPane chatScrollPane;
	
	
	
	
	
	public chatGUI(){
		
		super("Chat Client");
		setSize(200,100);
		
		firstPanel = new JPanel();
		
		firstPanel.setLayout(new FlowLayout());
		welcomeLabel = new JLabel("Are you a server or client? ");
		serverButton = new JButton("Server");
		serverButton.addActionListener(this);
		clientButton = new JButton("Client");
		clientButton.addActionListener(this);
		
		
		firstPanel.add(welcomeLabel);
		firstPanel.add(serverButton);
		firstPanel.add(clientButton);
		
		add(firstPanel);
		
		
		
		
		
		
		setVisible(true);
	}
	
	private void setServerGUI(){
		
		remove(firstPanel);
		
		setSize(300,200);
		
		serverPanel = new JPanel(new GridLayout(3,1));
		add(serverPanel);
		
		JLabel serverWelcome = new JLabel("Enter the port you will connect to");
		serverPanel.add(serverWelcome,SwingConstants.CENTER);
		
		portTextField = new JTextField();
		portTextField.setEditable(false);
		portTextField.setText("0");
		serverPanel.add(portTextField);
		
		
		serverConnect = new JButton("Connect");
		serverConnect.addActionListener(this);
		serverPanel.add(serverConnect);
		
		
		
		revalidate();
		
		
		
		
	}
	
	private void setClientGUI(){
		
		
		
		remove(firstPanel);
		
		setSize(1000,750);
		
		clientPanel=new JPanel(new BorderLayout());
		add(clientPanel);
		
		clientConnect = new JButton("Connect");
		clientConnect.addActionListener(this);
		clientDisconnect = new JButton("Disconnect");
		clientDisconnect.addActionListener(this);
		clientExit = new JButton("Exit");
		clientExit.addActionListener(this);
		clientMultipleMessages = new JButton("Private Message");
		clientMultipleMessages.addActionListener(this);
		
		clientButtonPanel = new JPanel();
		clientButtonPanel.add(clientConnect);
		clientButtonPanel.add(clientDisconnect);
		clientButtonPanel.add(clientExit);
		clientButtonPanel.add(clientMultipleMessages);
		
		clientPanel.add(clientButtonPanel, BorderLayout.NORTH);
		
		clientContainer = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		clientConnects = new JPanel();
		Border connectBorder = BorderFactory.createLineBorder(Color.black);
		clientConnects.setBorder(connectBorder);
		clientConnects.setBackground(Color.WHITE);
		c.gridx=0;
		c.gridy=1;
		c.weightx=0.1;
		c.ipady=600;
		c.ipadx=200;
		
		
		
		clientContainer.add(clientConnects,c);
		
		connectLabel = new JLabel("Connections");
		c.gridx=0;
		c.gridy=0;
		c.ipady=0;
		c.ipadx=0;
		clientContainer.add(connectLabel,c);
		
		chatScrollPane = new JScrollPane();
		clientChatWindow = new JTextArea();
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		clientChatWindow.setBorder(chatBorder);
		clientChatWindow.setBackground(Color.WHITE);
		c.gridx=1;
		c.gridy=1;
		c.ipadx=700;
		c.ipady=600;
		chatScrollPane.add(clientChatWindow);
		clientContainer.add(chatScrollPane,c);
		
		chatLabel = new JLabel("Chat");
		c.gridx=1;
		c.gridy=0;
		c.ipadx=0;
		c.ipady=0;
		clientContainer.add(chatLabel,c);
		
		clientPanel.add(clientContainer);
		
		clientTextArea = new JTextField();
		clientTextArea.addActionListener(this);
		clientPanel.add(clientTextArea,BorderLayout.SOUTH);
		
		
		
		
		revalidate();
		
	}
	
	public int getServerPort(){
		return serverPort;
	}
	
	public JPanel getConnectWindow(){
		return clientConnects;
	}
	
	public JTextArea getChatWindow(){
		return clientChatWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == serverButton){
			
			setServerGUI();
			
		}
		
		else if(e.getSource() == clientButton){
			
			setClientGUI();
			
		}
		
		else if(e.getSource() == serverConnect){
			String tempPort = portTextField.getText();
			serverPort = Integer.parseInt(tempPort);
			remove(serverPanel);
			serverInfoPanel = new JPanel();
			add(serverInfoPanel);
			JLabel info = new JLabel("You are connected to port: "+tempPort);
			serverInfoPanel.add(info);
			System.out.println(serverPort);
			revalidate();
		}
		
		else if(e.getSource() == clientExit){
			System.exit(0);
		}
		else if(e.getSource() == clientConnect){
			String port=(String)
					JOptionPane.showInputDialog("What port do you want to use?");
			System.out.println(port);
		}
		else if(e.getSource() == clientDisconnect){
			
		}
		
		else if(e.getSource() == clientTextArea){
			String line = clientTextArea.getText();
			System.out.println(line);
			clientTextArea.setText(null);
			
		}
		else if(e.getSource() == clientMultipleMessages){
			JTextField people = new JTextField(25);
			JTextField message = new JTextField(50);
			JLabel peopleLabel = new JLabel("Enter the people you wish to \n"
					+ "chat with separated with a space\n.");
			JLabel messageLabel = new JLabel("Enter your message \n");
			
			JPanel privateMessagePanel = new JPanel(new GridLayout(4,1));
			privateMessagePanel.add(peopleLabel);
			privateMessagePanel.add(people);
			
			privateMessagePanel.add(messageLabel);
			privateMessagePanel.add(message);
			
			int result =
					JOptionPane.showConfirmDialog(
							null,
							privateMessagePanel,
							"Enter your message and recipients",
							JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION){
				System.out.println(people.getText());
				System.out.println(message.getText());
			}
					
			
			
					
		}
		else{
			System.out.println("Problem with button press");
		}
		
	}

	
	
	
	
	
	
}