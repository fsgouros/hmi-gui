import com.fazecast.jSerialComm.SerialPort;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.IOException;

public class LightGUI extends JFrame {
    //=================Text Field JFrame  JFrame JSlider Initialization=================//
    static volatile int rval;
    static volatile int gval;
    static volatile int bval;
    static volatile boolean butSt;

    JLabel jl  = new JLabel("R = 0");
    JLabel jl1 = new JLabel("G = 0");
    JLabel jl2 = new JLabel("B = 0");
    JLabel IO  = new JLabel("off");


    JTextField tf = new JTextField("Red= 0");
    JTextField tf1 = new JTextField("Green= 0");
    JTextField tf2 = new JTextField("Blue= 0");
    //JTextField IO = new JTextField();


    JFrame fr = new JFrame("LightGUI");
    JPanel JP = new JPanel();

    ImageIcon icon = new ImageIcon("IO.png");
    Image scaleImage = icon.getImage().getScaledInstance(100, 90,Image.SCALE_DEFAULT);
    JToggleButton tb = new JToggleButton(new ImageIcon(scaleImage));


    JSlider sl = new JSlider(0, 255, 0);
    JSlider sl1 = new JSlider(0, 255, 0);
    JSlider sl2 = new JSlider(0, 255, 0);

    //=================GUI Constructor including all GUI function=================//
    LightGUI() throws IOException, InterruptedException {
        GUI_SET_UP();
        JPanelSetup();
        JLabelSetup();
        buttonProperties();
        SliderProperties();
        SPI_Thread thread = new SPI_Thread();
        thread.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new LightGUI();


    }

    //=================GUI SET UP Function=================//
    //====This function is used to set up the bounds of the (buttons,sliders..etc)
    //====as well as the frame layout and look=================//
    private void GUI_SET_UP() {
        tb.setBounds(50, 100, 90, 30);

        //====Bound set up of sliders====//
        sl.setBounds( 50, 200, 400, 30);
        sl1.setBounds(50, 250, 400, 30);
        sl2.setBounds(50, 300, 400, 30);


        fr.setBackground(Color.black);
        fr.setSize(640, 480);
        fr.setLayout(null);
        fr.setVisible(true);
       // try{
         //   UIManager.setLookAndFeel(new Synthetical);
        //}
        fr.setDefaultLookAndFeelDecorated(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void JLabelSetup(){
        IO.setFont(new Font("Serif",Font.PLAIN,16));


        jl.setFont(new Font("Serif",Font.PLAIN,16));
        jl1.setFont(new Font("Serif",Font.PLAIN,16));
        jl2.setFont(new Font("Serif",Font.PLAIN,16));

        jl.setForeground(Color.RED);
        jl1.setForeground(Color.GREEN);
        jl2.setForeground(Color.BLUE);
    }

    private void buttonProperties()  {
        tb.setBounds(50, 100, 100, 90);
        tb.setLocation(200, 50);
        IO.setHorizontalAlignment(JTextField.CENTER);
        IO.setBounds(225, 150, 50, 30);
        //Define action Listener
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton ab = (AbstractButton) actionEvent.getSource();
                boolean state = ab.getModel().isSelected();
                if (state) {
                    butSt= true;
                    IO.setText("on");
                } else {
                    butSt=false;
                    IO.setText("off");
                }
            }
        };
        tb.addActionListener(AL);

        fr.add(IO);
        fr.add(tb);
    }

    public void SliderProperties() {
        sl.setMajorTickSpacing(25);
        sl.setMinorTickSpacing(5);
        sl.setPaintTicks(true);
        sl.setOrientation(SwingConstants.HORIZONTAL);

        sl1.setMajorTickSpacing(25);
        sl1.setMinorTickSpacing(5);
        sl1.setPaintTicks(true);
        sl1.setOrientation(SwingConstants.HORIZONTAL);

        sl2.setMajorTickSpacing(25);
        sl2.setMinorTickSpacing(5);
        sl2.setPaintTicks(true);
        sl2.setOrientation(SwingConstants.HORIZONTAL);

        jl.setBounds(450, 195, 70, 30);
        sl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                rval = sl.getValue();
                JPanelSetup();
                jl.setText("R = " + rval);
                jl.setFont(new Font("Serif",Font.PLAIN,14));
            }
        });

        jl1.setBounds(450, 245, 70, 30);
        sl1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                gval = sl1.getValue();
                JPanelSetup();
                jl1.setText("G =" + gval);
            }
        });
        jl2.setBounds(450, 295, 70, 30);
        sl2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                bval = sl2.getValue();
                JPanelSetup();
                jl2.setText("B =" + bval);
            }
        });
        fr.add(sl);
        fr.add(sl1);
        fr.add(sl2);

        fr.add(jl);
        fr.add(jl1);
        fr.add(jl2);

    }
    private void JPanelSetup(){

        JP.setBounds(500,0,130,1000);
        JP.setLayout(new FlowLayout());
        JP.setBackground(new Color(rval,gval,bval));

        fr.add(JP);
    }
}