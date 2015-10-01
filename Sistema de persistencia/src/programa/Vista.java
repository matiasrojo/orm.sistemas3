package programa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Vista extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textLastName;
	private JTextField textID;
	private JLabel labelOperation;
	private JButton buttonSave, buttonLoad, buttonUpdate;

	public Vista() {
		setTitle("Sistema de persistencia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 151);
		setResizable(false);
		contentPane = new JPanel();	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(10, 11, 46, 14);
		contentPane.add(labelName);
		
		JLabel labelLastName = new JLabel("Last Name:");
		labelLastName.setBounds(10, 36, 73, 14);
		contentPane.add(labelLastName);
		
		textName = new JTextField();
		textName.setBounds(81, 8, 135, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(81, 33, 135, 20);
		contentPane.add(textLastName);
		textLastName.setColumns(10);
		
		JLabel labelID = new JLabel("ID:");
		labelID.setBounds(250, 11, 26, 14);
		contentPane.add(labelID);
		
		textID = new JTextField();
		textID.setBounds(274, 8, 53, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		buttonSave = new JButton("Save");
		buttonSave.setBounds(32, 61, 89, 23);
		buttonSave.addActionListener(this);
		contentPane.add(buttonSave);	
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setEnabled(false);
		buttonUpdate.setBounds(131, 61, 89, 23);
		buttonUpdate.addActionListener(this);
		contentPane.add(buttonUpdate);
		
		buttonLoad = new JButton("Load");
		buttonLoad.setBounds(250, 32, 89, 23);
		buttonLoad.addActionListener(this);
		contentPane.add(buttonLoad);
		
		labelOperation = new JLabel();
		labelOperation.setHorizontalAlignment(SwingConstants.CENTER);
		labelOperation.setBounds(63, 95, 218, 14);
		contentPane.add(labelOperation);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSave) {
			//save
		}
		
		if(e.getSource() == buttonUpdate) {
			//update
		}
		
		if(e.getSource() == buttonLoad) {
				
			if (textID.getText().equals("")) 
				labelOperation.setText("Introduzca ID a cargar");
			else {
				try {
					int ID = Integer.parseInt(textID.getText());
					load(ID);
				}catch (NumberFormatException c) {
					labelOperation.setText("Introduzca un ID válido");
				}
			}		
		}
		
	}
	
	private void load(int ID) {
		
		Persona personita = new Persona();
		
		System.out.println("\nPROGRAMA> levantando al usuario con ID = " + ID + " ..." );
		
		personita.load(ID);
		
		System.out.println("\nPROGRAMA> usuario " + personita.getName() + " levantado.");
		System.out.println("\nPROGRAMA> " + personita.getName() + " " + personita.getLastname());
		
		buttonUpdate.setEnabled(true);
		textName.setText(personita.getName());
		textLastName.setText(personita.getLastname());
		labelOperation.setText("Objeto recuperado (ID:" + ID + ")");
	}
	
	
	
}
