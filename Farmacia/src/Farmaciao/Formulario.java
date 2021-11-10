package Farmaciao;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Formulario extends JFrame implements ActionListener {

	private ResultSet resultado = null;
	private JPanel contentPane;
	private JTable table = null;
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private Tabla tablaInventario;
	public static DefaultComboBoxModel lista;
	JCheckBox checkPrincipal;
	JCheckBox checkSecundaria;
	JRadioButton rbtnCofarma;
	JRadioButton rbtnEmpsephar;
	JRadioButton rbtnCemefar;
	
	String datos[][]= {};			
	String cabecera[]= {"Clave", "Tipo", "Nombre", "Cantidad", "Precio"};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {					
			try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public void llenarTabla ()
	{
		Tabla tablechida = new Tabla(table,datos,cabecera);
		tablechida.llenarTabla();
	}
	public boolean comprobarDatos() {
		if(txtNombre.getText().length() < 1) {	
			return false;	
		}
		try {
			int a = Integer.parseInt(txtCantidad.getText());
			if(a<1) {
				return false;
			}
			
		}catch(Exception ex) {
			return false;
		}
		
		
		return true;
		
	}
	
	public String getFarmacia() {
		if (checkPrincipal.isSelected()) {
			return "Principal";
		} 
		
		if (checkSecundaria.isSelected()) {
			return "Secundaria";
		}
		
		return "hola";
	}
	
	public String getDistribuidor() {
		
		if(rbtnCofarma.isSelected()) {
			return "Cofarma";
		}
		
		if(rbtnEmpsephar.isSelected()) {
			return "Empsephar";
		}
		
		if(rbtnCemefar.isSelected()) {
			return "Cemefar";
		}
		
		
		return "Metodo erroneo";
	}
	
	public Formulario() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/Imagenes/ojito.png")));
		setTitle("Farmacia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JComboBox jComboFrame1 = new JComboBox();
		jComboFrame1.setModel(new DefaultComboBoxModel(new String[] {"Analg\u00E9sico", "Anal\u00E9ptico", "Anest\u00E9sico", "Anti\u00E1cido", "Antidepresivo", "Antibi\u00F3ticos"}));
		
		JLabel labelTipo = new JLabel("Tipo:");
		
		JLabel labelCantidad = new JLabel("Cantidad");
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		
		JButton btnRealizar = new JButton("Realizar Pedido");
		btnRealizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!comprobarDatos()) {
					JOptionPane.showMessageDialog(null, "Revisar datos.", "Atencion", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					JOptionPane.showInternalMessageDialog(null, "Correcto","Todo Correcto!", JOptionPane.INFORMATION_MESSAGE);				}
				Resumen res = new Resumen();
				
				String nombre = txtNombre.getText();
				String cantidad = txtCantidad.getText();
				String tipo = (String) jComboFrame1.getSelectedItem();
				String farmacia = getFarmacia();
				String distribuidor = getDistribuidor();
				
				Resumen resumen = new Resumen ();
				resumen.setDatos(nombre, cantidad, tipo, farmacia, distribuidor);
				resumen.setDatosVisibles();
				resumen.setVisible(true);
			}
		});
		
		
		JLabel lblDireccion = new JLabel("Elige a que farmacia:");
		 checkPrincipal = new JCheckBox("Principal");
		 checkSecundaria = new JCheckBox("Secundario");
		checkPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSecundaria.setSelected(false);
			}
		});
		checkPrincipal.setSelected(true);
		checkSecundaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPrincipal.setSelected(false);
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar pedido");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtCantidad.setText("");
			}
		});
		
		rbtnCofarma = new JRadioButton("Cofarma");
		rbtnEmpsephar = new JRadioButton("Empsephar");
		rbtnCemefar = new JRadioButton("Cemefar");
		
		
		rbtnCofarma.setSelected(true);
		
		
		rbtnCofarma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnEmpsephar.setSelected(false);
				rbtnCemefar.setSelected(false);
			}
		});
		rbtnEmpsephar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnCofarma.setSelected(false);
				rbtnCemefar.setSelected(false);
			}
		});
		rbtnCemefar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnEmpsephar.setSelected(false);
				rbtnCofarma.setSelected(false);
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		JLabel lblEligeUnDistribuidor = new JLabel("Elige un distribuidor:");
		JLabel lblRealizarPedido = new JLabel("REALIZAR PEDIDO");
		lblRealizarPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizarPedido.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblInventarioActual = new JLabel("INVENTARIO ACTUAL");
		lblInventarioActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventarioActual.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDireccion)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(checkPrincipal)
													.addGap(18)
													.addComponent(checkSecundaria)
													.addGap(157))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(btnLimpiar)
													.addGap(31)))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(rbtnCofarma)
													.addGap(18)
													.addComponent(rbtnEmpsephar)
													.addGap(18)
													.addComponent(rbtnCemefar))
												.addComponent(lblEligeUnDistribuidor, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRealizar)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelCantidad))
									.addGap(53)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelTipo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboFrame1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(52)
									.addComponent(lblRealizarPedido, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addComponent(lblInventarioActual, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRealizarPedido)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNombre)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(labelCantidad)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(labelTipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jComboFrame1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEligeUnDistribuidor)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rbtnCofarma)
								.addComponent(rbtnEmpsephar)
								.addComponent(rbtnCemefar)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDireccion)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkPrincipal)
								.addComponent(checkSecundaria))))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRealizar)
						.addComponent(btnLimpiar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInventarioActual, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Tipo", "Nombre", "Cantidad", "Precio"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		llenarTabla();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
