package Farmaciao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Resumen extends JFrame {

	private JPanel contentPane;
	public static JComboBox jComboResumen;
	String nombre; 
	String cantidad; 
	String tipo; 
	String farmacia; 
	String distribuidor;
	JLabel lblCantidad;
	JLabel lblTipo;
	JLabel lblFarmacia;
	JLabel lblNombre;
	JLabel lblDistribuidor;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resumen frame = new Resumen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void setDatos(String nombre, String cantidad, String tipo, String farmacia, String distribuidor) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tipo = tipo;
		this.farmacia = farmacia;
		this.distribuidor = distribuidor;
		
		System.out.println("ESTO ES DE LA CLASE DE RESUMEN: "+nombre+cantidad+tipo);
	}
	
	public void setDatosVisibles() {
		
		System.out.println("DATOS VISIBLES: "+cantidad+" "+tipo);
		lblCantidad.setText(cantidad);
		lblTipo.setText(tipo);
		lblFarmacia.setText(farmacia);
		lblNombre.setText(nombre);
		lblDistribuidor.setText(distribuidor);
	}

	/**
	 * Create the frame.
	 */
	public Resumen() {
		setTitle("Resumen del pedido.");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblCantidad = new JLabel("");
		 lblCantidad.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblCantidad.setBounds(10, 44, 40, 30);
		contentPane.add(lblCantidad);
		
		 lblTipo = new JLabel("TIPO");
		 lblTipo.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblTipo.setBounds(145, 44, 80, 30);
		contentPane.add(lblTipo);
		
		 lblFarmacia = new JLabel("FARMACIA");
		 lblFarmacia.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblFarmacia.setBounds(127, 131, 80, 30);
		contentPane.add(lblFarmacia);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNombre.setBounds(217, 44, 80, 30);
		contentPane.add(lblNombre);
		
		 lblDistribuidor = new JLabel("DISTRIBUIDOR");
		 lblDistribuidor.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblDistribuidor.setBounds(330, 131, 94, 30);
		contentPane.add(lblDistribuidor);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "El pedido ya ha sido realizado");
				dispose();
			}
		});
		btnEnviar.setBounds(10, 211, 90, 25);
		contentPane.add(btnEnviar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(277, 211, 90, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("unidades de:");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel.setBounds(60, 44, 94, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Para la farmacia");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 131, 107, 30);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Del distribuidor:");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(217, 131, 113, 30);
		contentPane.add(lblNewLabel_2);
		
		 
		
		
	}
}
