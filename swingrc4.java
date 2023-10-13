import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SwingRC4 extends JFrame {
    JTextField jt1, jt2, jt3;
    JButton jb1, jb2;
    JLabel jl1, jl2, jl3;

    SwingRC4() {
        super("RC4 Algorithm");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        jl1 = new JLabel("RC4 ENCRYPTION/DECRYPTION ALGORITHM");
        jl1.setBounds(50, 30, 700, 50);
        jl1.setFont(new Font("Arial", Font.BOLD, 26));
        jl1.setForeground(Color.BLUE);
        this.add(jl1);

        jl2 = new JLabel("Enter Plain/Encrypted Text:");
        jl2.setBounds(50, 100, 250, 30);
        jl2.setFont(new Font("Arial", Font.PLAIN, 16));
        jl2.setForeground(Color.DARK_GRAY);
        this.add(jl2);

        jt1 = new JTextField(20);
        jt1.setBounds(300, 100, 400, 30);
        this.add(jt1);

        jl3 = new JLabel("Enter Key:");
        jl3.setBounds(50, 150, 200, 30);
        jl3.setFont(new Font("Arial", Font.PLAIN, 16));
        jl3.setForeground(Color.DARK_GRAY);
        this.add(jl3);

        jt2 = new JTextField(20);
        jt2.setBounds(300, 150, 400, 30);
        this.add(jt2);

        jt3 = new JTextField("");
        jt3.setBounds(50, 250, 650, 100);
        jt3.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(jt3);

        jb1 = new JButton("Encrypt");
        jb1.setBounds(200, 200, 100, 30);
        jb1.setBackground(Color.GREEN);
        jb1.setForeground(Color.WHITE);
        this.add(jb1);

        jb2 = new JButton("Decrypt");
        jb2.setBounds(400, 200, 100, 30);
        jb2.setBackground(Color.RED);
        jb2.setForeground(Color.WHITE);
        this.add(jb2);

        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setSize(800, 400);
        this.setLayout(null);
        this.setVisible(true);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                performAction(true);
            }
        });

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                performAction(false);
            }
        });
    }

    private void performAction(boolean isEncrypt) {
        try {
            String inputText = jt1.getText();
            String key = jt2.getText();
            if (inputText.equals("") || key.equals("")) {
                System.out.println("Enter text and key");
                jl2.setText("Please enter text and key");
            }

            String outputText = "";
            int temp = 0;
            int[] s = new int[256];
            int[] T = new int[256];
            char[] inputTextCh = inputText.toCharArray();
            char[] keyCh = key.toCharArray();
            int[] result = new int[inputText.length()];
            int[] inputTextInt = new int[inputText.length()];
            int[] keyInt = new int[key.length()];
            for (int i = 0; i < inputText.length(); i++) {
                inputTextInt[i] = (int) inputTextCh[i];
            }
            for (int i = 0; i < key.length(); i++) {
                keyInt[i] = (int) keyCh[i];
            }
            for (int i = 0; i < 255; i++) {
                s[i] = i;
                T[i] = keyInt[i % key.length()];
            }
            int j = 0;
            for (int i = 0; i < 255; i++) {
                j = (j + s[i] + T[i]) % 256;
                temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
            int i = 0;
            j = 0;
            int keystream = 0;
            for (int l = 0; l < inputText.length(); l++) {
                i = (l + 1) % 256;
                j = (j + s[i]) % 256;
                temp = s[i];
                s[i] = s[j];
                s[j] = temp;
                keystream = s[(s[i] + s[j]) % 256];
                if (isEncrypt) {
                    result[l] = keystream ^ inputTextInt[l];
                } else {
                    result[l] = keystream ^ inputTextInt[l];
                }
            }
            for (i = 0; i < inputText.length(); i++) {
                outputText = outputText + Character.toString((char) result[i]);
            }

            jt3.setText(outputText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) throws Exception {
        SwingRC4 obj = new SwingRC4();
    }
}
