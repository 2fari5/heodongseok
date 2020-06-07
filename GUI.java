import java.awt.EventQueue;    
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Color;

public class GUI {      

       private JFrame frame;
       public void gui() {
           EventQueue.invokeLater(new Runnable() {
               public void run() {
                   try {
                       GUI window = new GUI();
                       window.frame.setVisible(true);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           });
       }
       public GUI() {
           initialize();           
               JFrame jframe = new JFrame();
               jframe.setTitle("정지 메뉴");
               jframe.setResizable(false);
       }

       private void initialize() {
           frame = new JFrame();
           frame.getContentPane().setBackground(Color.PINK);
           frame.setBounds(100, 100, 400,150 );
           frame.setLocationRelativeTo(null);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.getContentPane().setLayout(null);
           frame.setResizable(false);
           
           JButton btnContinue = new JButton("계속 하시겠습니까?");
           btnContinue.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                   frame.dispose();
               }

           });
           btnContinue.setBounds(0, 0, 200, 115);
           frame.getContentPane().add(btnContinue);

           JButton btnExit = new JButton("게임종료");
           btnExit.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (JOptionPane.showConfirmDialog(null, "진짜 종료하시겠습니까?", "Exit",
                           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                     System.exit(0);
                   }
               }
           });
           btnExit.setBounds(200,0 , 200, 115);
           frame.getContentPane().add(btnExit);  
       
       }
     


       public void setVisible(boolean b){

           gui();
       }


}