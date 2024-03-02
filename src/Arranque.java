import java.awt.event.*;
import javax.swing.*;

class Ventana extends JFrame implements ActionListener, KeyListener {
	
	int width = 26;
	int height = 26;
	JTextField cajaKm = new JTextField(10);
	boolean decim = false;
	Motor conv = new Motor();
	
	JRadioButton radioMillas = new JRadioButton("Millas");
	JRadioButton radioPies = new JRadioButton("Pies");
	JRadioButton radioPulgadas = new JRadioButton("Pulgadas");
	
	JCheckBox checkTodos = new JCheckBox("<<<TODOS>>>");
	
	JTextField cajaMillas = new JTextField(10);
	JTextField cajaPies = new JTextField(10);
	JTextField cajaPulgadas = new JTextField(10);
	
	ButtonGroup bg = new ButtonGroup();
	
	public Ventana() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("Conversor de KM");
		setVisible(true);
		
		dimensionar(new JLabel("Ingresa Kilometros"), 5, 1, 6, 1);
		dimensionar(cajaKm, 4, 2, 6, 1);
		cajaKm.setText("10");
		cajaKm.addKeyListener(this);
		dimensionar(checkTodos, 5, 3, 6, 1);
		checkTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarCajas();
				conv.km = Double.parseDouble(cajaKm.getText());
				String t = conv.aMillas()+"";
				int p = t.indexOf('.');
				
				if(p!= -1) t = t.substring(0, Math.min(t.length(), p+5));
				establecerCaja(cajaMillas, t);
				
				t = conv.aPies()+"";
				p = t.indexOf('.');
				if(p!= -1) t = t.substring(0, Math.min(t.length(), p+5));
				establecerCaja(cajaPies, t);
				
				t = conv.aPulgadas()+"";
				p = t.indexOf('.');
				if(p!= -1) t = t.substring(0, Math.min(t.length(), p+5));
				establecerCaja(cajaPulgadas, t);
								
			}
		});
		radioMillas.addActionListener(this);
		radioPies.addActionListener(this);
		radioPulgadas.addActionListener(this);
		
		bg.add(radioMillas);
		bg.add(radioPies);
		bg.add(radioPulgadas);
		
		dimensionar(radioMillas, 4, 4, 3, 1);
		dimensionar(radioPies, 4, 5, 3, 1);
		dimensionar(radioPulgadas, 4, 6, 3, 1);
		
		dimensionar(cajaMillas, 7, 4, 3, 1);
		dimensionar(cajaPies, 7, 5, 3, 1);
		dimensionar(cajaPulgadas, 7, 6, 3, 1);
		
		cajaMillas.setEditable(false);
		cajaPies.setEditable(false);
		cajaPulgadas.setEditable(false);
		
		
		
		
	}
	void limpiarCajas() {
		cajaMillas.setText("");
		cajaPies.setText("");
		cajaPulgadas.setText("");
	}
	void establecerCaja(JTextField b, String t) {
		b.setText(t);
	}
	void dimensionar(JComponent c, int x, int y, double w, double h) {
		c.setBounds(x*width,y*height,(int)w*width,(int)h*height);
		add(c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String t = cajaKm.getText();	
		conv.km = Double.parseDouble(t);
		
		JTextField caja = cajaKm;
		
		limpiarCajas();
		if(e.getSource() == radioMillas) {
			
			t = conv.aMillas()+"";
			caja = cajaMillas;
		}else if(e.getSource() == radioPies) {

			t = conv.aPies()+"";
			caja = cajaPies;
		}else if(e.getSource() == radioPulgadas) {

			t = conv.aPulgadas()+"";
			caja = cajaPulgadas;
		}
		//que deje 5 decimales
		int p = t.indexOf('.');
		if(p!= -1) t = t.substring(0, Math.min(t.length(), p+5));
		establecerCaja(caja, t);
		
	}
	boolean impedirLetras(KeyEvent e) {
		char a = e.getKeyChar();
		return !(Character.isDigit(a) || (a == '.' && !decim));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		for(char l : cajaKm.getText().toCharArray()) {
			
			if(l == '.') { decim = true; break;}
			
		}
		
		if(impedirLetras(e)) e.consume();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
public class Arranque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ventana();
	}

}
