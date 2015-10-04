package programa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;

@SuppressWarnings("serial")
public class Vista extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textName, textLastName, textID, textDogName, textDogID;
	private JLabel labelOperation;
	private JButton buttonSave, buttonLoad, buttonDogSave, buttonDogLoad, buttonDelPerson, buttonDelDog;

	public Vista() {
		setTitle("Sistema de persistencia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 248);
		setResizable(false);
		contentPane = new JPanel();	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPerson = new JLabel("Persona/Due\u00F1o");
		labelPerson.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelPerson.setBounds(214, 8, 125, 14);
		contentPane.add(labelPerson);
		
		JLabel labelName = new JLabel("Nombre:");
		labelName.setBounds(10, 33, 53, 14);
		contentPane.add(labelName);
		
		JLabel labelLastName = new JLabel("Apellido:");
		labelLastName.setBounds(10, 58, 73, 14);
		contentPane.add(labelLastName);
		
		textName = new JTextField();
		textName.setBounds(66, 30, 135, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(66, 55, 135, 20);
		contentPane.add(textLastName);
		textLastName.setColumns(10);
		
		JLabel labelID = new JLabel("ID:");
		labelID.setBounds(320, 33, 26, 14);
		contentPane.add(labelID);
		
		textID = new JTextField();
		textID.setBounds(345, 30, 53, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		buttonSave = new JButton("Guardar Persona");
		buttonSave.setBounds(44, 83, 135, 23);
		buttonSave.addActionListener(this);
		contentPane.add(buttonSave);
		
		buttonLoad = new JButton("Cargar Persona");
		buttonLoad.setBounds(222, 54, 135, 23);
		buttonLoad.addActionListener(this);
		contentPane.add(buttonLoad);
		
		labelOperation = new JLabel();
		labelOperation.setHorizontalAlignment(SwingConstants.CENTER);
		labelOperation.setBounds(155, 205, 218, 14);
		contentPane.add(labelOperation);
		
		JLabel labelDog = new JLabel("Perro");
		labelDog.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDog.setBounds(244, 117, 53, 14);
		contentPane.add(labelDog);
		
		JLabel labelDogName = new JLabel("Nombre:");
		labelDogName.setBounds(10, 145, 53, 14);
		contentPane.add(labelDogName);
		
		textDogName = new JTextField();
		textDogName.setBounds(66, 142, 135, 20);
		contentPane.add(textDogName);
		textDogName.setColumns(10);
		
		textDogID = new JTextField();
		textDogID.setBounds(345, 142, 53, 20);
		contentPane.add(textDogID);
		textDogID.setColumns(10);
		
		JLabel labelDogID = new JLabel("ID:");
		labelDogID.setBounds(320, 145, 19, 14);
		contentPane.add(labelDogID);
		
		buttonDogSave = new JButton("Guardar Perro/Due\u00F1o");
		buttonDogSave.setBounds(32, 170, 157, 23);
		buttonDogSave.addActionListener(this);
		contentPane.add(buttonDogSave);
		
		buttonDogLoad = new JButton("Cargar Perro");
		buttonDogLoad.setBounds(222, 170, 135, 23);
		buttonDogLoad.addActionListener(this);
		contentPane.add(buttonDogLoad);
		
		buttonDelPerson = new JButton("Borrar Persona");
		buttonDelPerson.setBounds(363, 54, 135, 23);
		buttonDelPerson.addActionListener(this);
		contentPane.add(buttonDelPerson);
		
		buttonDelDog = new JButton("Borrar Perro");
		buttonDelDog.setBounds(363, 170, 135, 23);
		buttonDelDog.addActionListener(this);
		contentPane.add(buttonDelDog);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSave) {
			if (textName.getText().equals("") || textLastName.getText().equals("")) {
				labelOperation.setText("Introduzca datos");			
			}
			else {
				int id = this.savePerson (textID.getText().equals("")?0:Integer.parseInt(textID.getText()), textName.getText(), textLastName.getText());			
				textID.setText(Integer.toString(id));
			}
		}
		
		if(e.getSource() == buttonLoad) {		
			if (textID.getText().equals("")) 
				labelOperation.setText("Introduzca ID");
			else {
				try {
					int id = Integer.parseInt(textID.getText());
					loadPerson (id);
				}catch (NumberFormatException c) {
					labelOperation.setText("Introduzca un ID válido");
				}
			}		
		}
		
		if(e.getSource() == buttonDogSave) {
			if ((textName.getText().equals("") || textLastName.getText().equals("") || textDogName.getText().equals(""))) {
				labelOperation.setText("Introduzca datos");			
			}
			else {
				int dogId = saveDog (textDogID.getText().equals("")?0:Integer.parseInt(textDogID.getText()), textID.getText().equals("")?0:Integer.parseInt(textID.getText()), textName.getText(), textLastName.getText(), textDogName.getText());
				textDogID.setText(Integer.toString(dogId));
			}
		}
		
		if(e.getSource() == buttonDogLoad) {
				
			if (textDogID.getText().equals("")) 
				labelOperation.setText("Introduzca ID");
			else {
				try {
					int id = Integer.parseInt(textDogID.getText());
					loadDog (id);
				}catch (NumberFormatException c) {
					labelOperation.setText("Introduzca un ID válido");
				}
			}		
		}
		
		if(e.getSource() == buttonDelPerson) {
			
			if (textID.getText().equals("")) 
				labelOperation.setText("Introduzca ID");
			else {
				try {
					int id = Integer.parseInt(textID.getText());
					deletePerson (id);
				}catch (NumberFormatException c) {
					labelOperation.setText("Introduzca un ID válido");
				}
			}		
		}
		
		if(e.getSource() == buttonDelDog) {
			
			if (textDogID.getText().equals("")) 
				labelOperation.setText("Introduzca ID");
			else {
				try {
					int ID = Integer.parseInt(textDogID.getText());
					deleteDog (ID);
				}catch (NumberFormatException c) {
					labelOperation.setText("Introduzca un ID válido");
				}
			}		
		}
		
	}
	
	private int savePerson(int id, String name, String lastName) {
		
		Persona person = new Persona();
		
		person.setName(name);
		person.setLastname(lastName);
		if(id != 0)
			person.setId(id);
		
		if (person.save()) {
			labelOperation.setText("Objeto persistido");
		}
		else {
			labelOperation.setText("Error al persistir objeto");
		}
		
		return person.getInstanceId();
	}
	
	private void loadPerson (int id) {
		
		Persona person = new Persona();
		
		System.out.println("\nPROGRAMA> levantando al usuario con ID = " + id + " ..." );
		
		person.load(id);
		
		System.out.println("\nPROGRAMA> usuario " + person.getName() + " levantado.");
		System.out.println("\nPROGRAMA> " + person.getName() + " " + person.getLastname());
		
		textName.setText(person.getName());
		textLastName.setText(person.getLastname());
		textDogName.setText("");
		textDogID.setText("");
		labelOperation.setText("Objeto recuperado (Persona ID:" + id + ")");
	}
	
	private int saveDog(int dogId, int ownerId, String ownerName, String ownerLastName, String dogName) {
		
		Persona person = new Persona();
		person.setName(ownerName);
		person.setLastname(ownerLastName);
		if(ownerId != 0)
			person.setId(ownerId);
		
		Perro dog = new Perro();
		dog.setName(dogName);
		dog.setDuenio(person);
		if(dogId != 0)
			dog.setId(dogId);
		
		if (person.save() && dog.save()) {
			labelOperation.setText("Objetos persistidos");
		}
		else {
			labelOperation.setText("Error al persistir objetos");
		}
		
		return dog.getInstanceId();
	}
	
	private void loadDog (int id) {
		
		Perro dog = (Perro) new Perro().load(id);
			
		System.out.println("\nEl dueño de " + dog.getName() + " es " + dog.getDuenio().getName());
		
		textDogName.setText(dog.getName());
		textName.setText(dog.getDuenio().getName());
		textLastName.setText(dog.getDuenio().getLastname());
		textID.setText(Integer.toString(dog.getDuenio().getInstanceId()));
		labelOperation.setText("Objeto recuperado (Perro ID:" + id + ")");
	}
	
	private void deletePerson (int id) {
		
		Persona person = new Persona();

		if (person.delete(id)) {
			textName.setText("");
			textLastName.setText("");
			textID.setText("");
			labelOperation.setText("Objeto borrado");
		}
		else {
			labelOperation.setText("Error al borrar objeto");
		}
		
	}
	
	private void deleteDog (int id) {
		
		Perro dog = new Perro();
		
		if (dog.delete(id)) {
			textName.setText("");
			textLastName.setText("");
			textDogName.setText("");
			textDogID.setText("");
			labelOperation.setText("Objeto borrado");
		}
		else {
			labelOperation.setText("Error al borrar objeto");
		}
		
	}
}
