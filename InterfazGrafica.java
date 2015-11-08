/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructura de Datos
 * Sección: 10
 * 30/07/2015
 * Hoja de Trabajo 2
 *
 */

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


/**
 * Esta es la clase <InterfazGrafica> y muestra una Interfaz
 * mas amigable con el usuario al tener un botón para buscar el
 * archivo deseado para realizar las operacinoes. Busca el archivo
 * y tienen otro botón que permite realizar el cálculo de la operacion.
 * Por útltimo, tiene un área de texto en el cual muestra el resultado
 * de la operación.
 * 
 * @author Delbert Custodio
 * @author Rudy Garrido
 *
 */
public class InterfazGrafica {
		
		/*
		 * Instanciacion de la interfaz
		 * */
		
		private JFrame frame;
		private JSlider slider;
		private JLabel label;
		private JTextField textField;
		private JFileChooser fc;
		private File file;
		private Manejador manejador = new Manejador();
		//private Calculadora<Integer> calculadora = new Calculadora<Integer>();
		private JButton btnSeleccionarArchivo;
		private JButton buttonCalcular;
		private JTextArea textArea;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
		private JTextField textField_5;
		private JButton btnCalcularDistancia;
		private JButton btnNuevaConexion;
		private JTextArea textArea_1;
		private JButton btnSinConexion;
		private JButton btnCentroDelGrafo;
		private JLabel lblLaCiudadCentral;
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InterfazGrafica window = new InterfazGrafica();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		public InterfazGrafica(){
			initialize();
		}
		/**
		 * Crea los elementos de la interfaz gráfica.
		 *El frame principal, etiquetas para nombre y 
		 *resultado, botones buscar archivo y calcular 
		 *y un área de texto para mostrar el resultado.
		 */
		public void initialize(){
			frame = new JFrame();
			frame.setBounds(100, 100, 682, 541);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblCalculadoraPostfix = new JLabel("Ciudades");
			lblCalculadoraPostfix.setFont(new Font("Tahoma", Font.BOLD, 50));
			lblCalculadoraPostfix.setBounds(10, 0, 586, 73);
			frame.getContentPane().add(lblCalculadoraPostfix);
			
			btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
			btnSeleccionarArchivo.setBounds(422, 128, 174, 23);
			btnSeleccionarArchivo.addActionListener(new Evento());
			frame.getContentPane().add(btnSeleccionarArchivo);
			
			textField = new JTextField();
			textField.setBounds(20, 128, 378, 23);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			buttonCalcular = new JButton("Calcular");
			buttonCalcular.setBounds(422, 162, 174, 23);
			buttonCalcular.addActionListener(new Evento());
			frame.getContentPane().add(buttonCalcular);
			
			JLabel lblResultados = new JLabel("Resultado");
			lblResultados.setBounds(20, 258, 93, 21);
			frame.getContentPane().add(lblResultados);
			
			textArea = new JTextArea();
			textArea.setBounds(115, 256, 358, 46);
			JScrollPane sp_1 = new JScrollPane(textArea);
			sp_1.setBounds(115, 256, 358, 46);
			frame.getContentPane().add(sp_1);
			
			JLabel lblCuidadOrigen = new JLabel("Cuidad Origen");
			lblCuidadOrigen.setBounds(20, 196, 93, 23);
			frame.getContentPane().add(lblCuidadOrigen);
			
			textField_1 = new JTextField();
			textField_1.setBounds(115, 196, 134, 23);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(115, 225, 134, 23);
			frame.getContentPane().add(textField_2);
			
			JLabel lblCuidadDestino = new JLabel("Cuidad Destino");
			lblCuidadDestino.setBounds(20, 225, 93, 23);
			frame.getContentPane().add(lblCuidadDestino);
			
			JLabel lblDistancia = new JLabel("Distancia");
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblDistancia.setBounds(10, 162, 205, 23);
			frame.getContentPane().add(lblDistancia);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(115, 347, 106, 23);
			frame.getContentPane().add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(115, 376, 106, 23);
			frame.getContentPane().add(textField_4);
			
			JLabel label_1 = new JLabel("Cuidad Origen");
			label_1.setBounds(20, 347, 93, 23);
			frame.getContentPane().add(label_1);
			
			JLabel label_2 = new JLabel("Cuidad Destino");
			label_2.setBounds(20, 376, 93, 23);
			frame.getContentPane().add(label_2);
			
			JLabel lblModificacion = new JLabel("Modificacion");
			lblModificacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblModificacion.setBounds(10, 313, 205, 23);
			frame.getContentPane().add(lblModificacion);
			
			btnSinConexion = new JButton("Sin conexion");
			btnSinConexion.setBounds(10, 438, 124, 23);
			btnSinConexion.addActionListener(new Evento());
			frame.getContentPane().add(btnSinConexion);
			
			JLabel lblDistancia_1 = new JLabel("Distancia");
			lblDistancia_1.setBounds(20, 408, 76, 14);
			frame.getContentPane().add(lblDistancia_1);
			
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(115, 404, 106, 23);
			frame.getContentPane().add(textField_5);
			
			btnNuevaConexion = new JButton("Nueva Conexion");
			btnNuevaConexion.setBounds(140, 438, 134, 23);
			btnNuevaConexion.addActionListener(new Evento());
			frame.getContentPane().add(btnNuevaConexion);
			
			lblLaCiudadCentral = new JLabel("La ciudad central es _______");
			lblLaCiudadCentral.setBounds(20, 103, 229, 14);
			frame.getContentPane().add(lblLaCiudadCentral);
			
			btnCalcularDistancia = new JButton("Calcular Distancia");
			btnCalcularDistancia.setBounds(259, 225, 139, 23);
			btnCalcularDistancia.addActionListener(new Evento());
			frame.getContentPane().add(btnCalcularDistancia);
			
			textArea_1 = new JTextArea();
			textArea_1.setBounds(301, 345, 355, 146);
			JScrollPane sp = new JScrollPane(textArea_1);
			sp.setBounds(301, 345, 355, 146);
			frame.getContentPane().add(sp);
			
			JLabel lblMatrizDeAdyacencia = new JLabel("Matriz de Adyacencia");
			lblMatrizDeAdyacencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMatrizDeAdyacencia.setBounds(299, 313, 205, 23);
			frame.getContentPane().add(lblMatrizDeAdyacencia);
			
			btnCentroDelGrafo = new JButton("Centro del Grafo");
			btnCentroDelGrafo.addActionListener(new Evento());
			btnCentroDelGrafo.setBounds(69, 468, 134, 23);
			frame.getContentPane().add(btnCentroDelGrafo);
			
			fc = new JFileChooser();
			
			
		}
		/**
		 * Clase interna <Evento> para poder obtener el botón al cual se le realiza 
		 * la acción y asi realizar la operación deseada por el usuario.
		 * Un evento en <btnSeleccionarArchivo> permite abrir el buscador de archivos
		 * Un envento en <btnCalcular> permite realizar el cálculo de la operacion
		 *
		 *Este actionListener se basa en la clase manejador.
		 *
		 */
		private class Evento implements ActionListener{


			public void actionPerformed(ActionEvent a) {
				
				
				/*
				 * Se utiliza un OpenDialog para poder buscar mas facil
				 * el archivo .txt que se utilizara
				 * */
				if(a.getSource()==btnSeleccionarArchivo){
					int returnVal = fc.showOpenDialog(frame);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	System.out.println("Seleccion");
			            file = fc.getSelectedFile();
			            textField.setText(file.getAbsolutePath());
			        } 
				}else if(a.getSource()==buttonCalcular){
					try {
						manejador.readFile(file.getAbsolutePath());
						textArea_1.setText(manejador.obtenerMatriz());
						lblLaCiudadCentral.setText(manejador.calcularNodoCentral());
						//calculadora.readFile(file.getAbsolutePath());
						//textArea.setText(String.valueOf("El resultado final de su operacion es: " +calculadora.calcular()));
					} catch ( Exception e) {
						e.printStackTrace();
					}
				}else if(a.getSource()==btnCalcularDistancia){
					String resultado = manejador.obtenerDistancia(textField_1.getText(), textField_2.getText());
					if(resultado!=null){
						textArea.setText(resultado);
					}else{
						textArea.setText("Ciudades no encontradas");
					}
					
				}else if(a.getSource()==btnSinConexion){
					manejador.eliminarConexion(textField_3.getText(), textField_4.getText());
					textArea_1.setText(manejador.obtenerMatriz());
				}else if(a.getSource()== btnNuevaConexion){
					manejador.crearConexion(textField_3.getText(), textField_4.getText(), Integer.parseInt(textField_5.getText()));
					textArea_1.setText(manejador.obtenerMatriz());
				}
				else if (a.getSource() ==btnCentroDelGrafo){
					JOptionPane.showMessageDialog(frame,manejador.calcularNodoCentral());
				}
			}
		}
}
