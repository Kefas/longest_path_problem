package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GenFrame extends JFrame implements ActionListener {
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 50;
	static final int FPS_INIT = 25; 
	
	private int h,w;
	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
	private JButton generate;  
	
	
		GenFrame(){
			System.out.println("GenFrame");
			w=500;
			h=300;
			sliderPanel = new JPanel();
			sliderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			
			
			// common listener for all sliders
		      listener = new ChangeListener()
		         {
		            public void stateChanged(ChangeEvent event)
		            {
		               // update text field when the slider value changes
		               JSlider source = (JSlider) event.getSource();
		               textField.setText("" + source.getValue());
		            }
		         };
		         
		         JSlider slider = new JSlider(FPS_MIN, FPS_MAX, FPS_INIT);
		        
		         slider.setPaintTicks(true);
		         slider.setPaintLabels(true);
		         slider.setMajorTickSpacing(10);
		         slider.setMinorTickSpacing(1);
		         slider.setPreferredSize(new Dimension(300, 60));
		         textField = new JTextField("25", 3);
		        
		         addSlider("Liczba Wierzcho�k�w:  ",slider, textField);
		         add(sliderPanel, BorderLayout.CENTER);
		        
		         generate = new JButton("Generuj");
		        
		         add(generate, BorderLayout.SOUTH);
		         
		         generate.addActionListener(this);
		         
		         
		         
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setTitle("Generuj graf");
				setResizable(false);
				setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(w/2),Toolkit.getDefaultToolkit().getScreenSize().height/2-(h/2));
				setSize(w,h);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
		}


	public void addSlider(String description,JSlider s,  JTextField t)
	{
	   s.addChangeListener(listener);
	   JPanel panel = new JPanel();
	   panel.add(new JLabel(description));
	   panel.add(s);
	   panel.add(t);
	  
	   sliderPanel.add(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == generate){
			MyFrame.getGraph(Integer.parseInt(textField.getText()));
			dispose();
		}
		
	}
	
	
}