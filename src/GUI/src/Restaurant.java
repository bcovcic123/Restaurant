package GUI.src;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Restaurant {
	
	public ArrayList<MenuItem> menu;
	public ArrayList<String> l;
	public JList<String> list;
	public JList<String> list_1 = new JList<String>();
	public ArrayList<OrderItem> ord = new ArrayList<OrderItem>();
	public JLabel lblPrice = new JLabel("Price: 0.0");
	public JButton btnPay = new JButton("Pay");
	public JRadioButton rdbtnCash = new JRadioButton("Cash");
	public JRadioButton rdbtnCreditCard = new JRadioButton("Credit card");
	public JButton button = new JButton("Pay");

	private JFrame frame;
	public JTextField txtCardNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Restaurant window = new Restaurant();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Restaurant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		l=new ArrayList<String>();
		menu=new ArrayList<MenuItem>();
		
		l.add("Sugar");
		l.add("Chocolate");
		l.add("Flour");
		
		menu.add(new MenuItem(new Meal("Salad","Appetizer",l),10.0));
		menu.add(new MenuItem(new Meal("Chicken","Main course",l),12.0));
		menu.add(new MenuItem(new Meal("Cake","Dessert",l),8.0));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 526, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.addItem("Appetizer");
		comboBox.addItem("Main course");
		comboBox.addItem("Dessert");
		
		JLabel lblCategory = new JLabel("Category");
		
		list = new JList();
		
		JButton btnNewButton = new JButton("Add item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean b=false;
				if(list.getSelectedValue()!=null)
				{
					for(int i=0;i<ord.size();i++)
					{
						if(ord.get(i).getMi().toString().equals(list.getSelectedValue().toString()))
						{
							ord.get(i).setAmount(ord.get(i).getAmount()+1);
							b=true;
							break;
						}
					
					}
					if(!b)
					{
						MenuItem m;
						for(int i=0;i<menu.size();i++)
						{
							if(menu.get(i).toString().equals(list.getSelectedValue().toString()))
							{
								m=menu.get(i);
								ord.add(new OrderItem(m,1));
							}
						}
						
						
					}
					double price=0;
					DefaultListModel orders= new DefaultListModel();
					for(int i=0;i<ord.size();i++)
					{
						orders.addElement(ord.get(i).toString());
						price+=ord.get(i).getMi().getPrice()*ord.get(i).getAmount();
					}
					lblPrice.setText("Price: "+price);
					list_1.setModel(orders);
					
				}
			}
		});
		
		JLabel lblOrder = new JLabel("Order");
		
		JButton btnNewButton_1 = new JButton("Remove item");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list_1.getSelectedValue()!=null)
				{
				for(int i=0;i<ord.size();i++)
				{
					if(ord.get(i).toString().equals(list_1.getSelectedValue().toString()))
					{
						if(ord.get(i).getAmount()!=1) ord.get(i).setAmount(ord.get(i).getAmount()-1);
						else ord.remove(i);
						break;
					}
				}
				
				double price=0;
				DefaultListModel orders= new DefaultListModel();
				for(int i=0;i<ord.size();i++)
				{
					orders.addElement(ord.get(i).toString());
					price+=ord.get(i).getMi().getPrice()*ord.get(i).getAmount();
				}
				lblPrice.setText("Price: "+price);
				list_1.setModel(orders);
			}
		}
			
		});
		
		JLabel lblTypeOfPayment = new JLabel("Type of payment");
		buttonGroup.add(rdbtnCash);
		rdbtnCash.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnCash.isSelected()==true) btnPay.setEnabled(true);
				else btnPay.setEnabled(false);
			}
		});
		
		
		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		rdbtnCash.setSelected(true);
		
		
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ord.size()!=0)
				{	
				ord.clear();
				DefaultListModel listModel = (DefaultListModel) list_1.getModel();
		        listModel.removeAllElements();
		        lblPrice.setText("Price: 0.0");
		        JOptionPane.showMessageDialog(frame,"Payed");
				}else JOptionPane.showMessageDialog(frame,"Nothing to pay");
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		txtCardNo = new JTextField();
		txtCardNo.setEnabled(false);
		txtCardNo.setToolTipText("");
		txtCardNo.setBounds(10, 32, 135, 20);
		panel.add(txtCardNo);
		txtCardNo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Card number");
		lblNewLabel.setBounds(10, 11, 98, 14);
		panel.add(lblNewLabel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ord.size()!=0 && txtCardNo.getText().length()!=0)
				{	
				ord.clear();
				txtCardNo.setText("");
				DefaultListModel listModel = (DefaultListModel) list_1.getModel();
		        listModel.removeAllElements();
		        lblPrice.setText("Price: 0.0");
		        JOptionPane.showMessageDialog(frame,"Payed");
				}else JOptionPane.showMessageDialog(frame,"Nothing to pay");
			}
			
		});
		
		
		button.setEnabled(false);
		button.setBounds(20, 63, 112, 23);
		panel.add(button);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOrder, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(153)
									.addComponent(rdbtnCash, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(180)
									.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(163)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(175)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(175)
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(336)
							.addComponent(rdbtnCreditCard, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(130)
							.addComponent(lblTypeOfPayment, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblOrder, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(29)
											.addComponent(rdbtnCash, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
									.addGap(4)
									.addComponent(btnPay)
									.addGap(123)
									.addComponent(btnNewButton_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(149)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(71)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(195)
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(254)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(138)
							.addComponent(rdbtnCreditCard, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(lblTypeOfPayment, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addGap(8))
		);
		buttonGroup.add(rdbtnCreditCard);
		rdbtnCreditCard.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnCreditCard.isSelected()==true)
				{
					button.setEnabled(true);
					txtCardNo.setEnabled(true);
				}
				else 
				{
					button.setEnabled(false);
					txtCardNo.setEnabled(false);
				}
				
			}
		});
		frame.getContentPane().setLayout(groupLayout);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String selected = comboBox.getSelectedItem().toString();
				DefaultListModel<String> a= new DefaultListModel<String>();	
				for(int i=0;i<menu.size();i++)
				{
					if(menu.get(i).getM().getType().equals(selected)) a.addElement(menu.get(i).toString());
				}
				list.setModel(a);
			}
		});
		
		DefaultListModel<String> a= new DefaultListModel<String>();	
		for(int i=0;i<menu.size();i++)
		{
			if(menu.get(i).getM().getType().equals("Appetizer")) a.addElement(menu.get(i).toString());
		}
		list.setModel(a);
		
	
		
		
	}
}
