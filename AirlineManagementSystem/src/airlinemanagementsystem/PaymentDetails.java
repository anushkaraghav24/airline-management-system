package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.Random;

public class PaymentDetails extends JFrame implements ActionListener {

    JTextField tfpnr, tfcardnumber, tfbankname;
    JLabel tfname, lblpaymentno, tfamount;
    JButton fetchButton, payButton, uploadScreenshotButton, confirmScreenshotButton;
    JComboBox<String> paymentOptions, flightClassOptions;
    JLabel qrImage, uploadedImagePreview;
    JPanel cardDetailsPanel;
    JCheckBox upiConfirmationCheck;
    boolean isScreenshotConfirmed = false;

    public PaymentDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("PAYMENT");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/airlinemanagementsystem/icons/payment-icon-png-18.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 80, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpnr);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Fetch Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 200, 25);
        add(tfname);

        JLabel lblpayment = new JLabel("Payment No");
        lblpayment.setBounds(60, 180, 150, 25);
        lblpayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpayment);

        lblpaymentno = new JLabel("" + Math.abs(random.nextInt(1000000)));
        lblpaymentno.setBounds(220, 180, 150, 25);
        add(lblpaymentno);

        JLabel lblclass = new JLabel("Flight Class");
        lblclass.setBounds(60, 230, 150, 25);
        lblclass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblclass);

        flightClassOptions = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});
        flightClassOptions.setBounds(220, 230, 150, 25);
        add(flightClassOptions);

        JLabel lblamount = new JLabel("Amount (₹)");
        lblamount.setBounds(60, 280, 150, 25);
        lblamount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblamount);

        tfamount = new JLabel();
        tfamount.setBounds(220, 280, 150, 25);
        tfamount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfamount);

        JLabel lblmethod = new JLabel("Payment Method");
        lblmethod.setBounds(60, 330, 150, 25);
        lblmethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblmethod);

        paymentOptions = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "Net Banking", "UPI"});
        paymentOptions.setBounds(220, 330, 150, 25);
        add(paymentOptions);

        cardDetailsPanel = new JPanel();
        cardDetailsPanel.setLayout(null);
        cardDetailsPanel.setBounds(60, 380, 310, 100);
        cardDetailsPanel.setBackground(Color.LIGHT_GRAY);
        cardDetailsPanel.setVisible(false);
        add(cardDetailsPanel);

        JLabel lblcardnumber = new JLabel("Card No / UPI ID");
        lblcardnumber.setBounds(10, 10, 120, 25);
        cardDetailsPanel.add(lblcardnumber);

        tfcardnumber = new JTextField();
        tfcardnumber.setBounds(140, 10, 150, 25);
        cardDetailsPanel.add(tfcardnumber);

        JLabel lblbankname = new JLabel("Bank Name");
        lblbankname.setBounds(10, 50, 120, 25);
        cardDetailsPanel.add(lblbankname);

        tfbankname = new JTextField();
        tfbankname.setBounds(140, 50, 150, 25);
        cardDetailsPanel.add(tfbankname);

        qrImage = new JLabel();
        qrImage.setBounds(470, 400, 250, 250);
        add(qrImage);

        upiConfirmationCheck = new JCheckBox("I have completed the UPI payment");
        upiConfirmationCheck.setBounds(470, 660, 250, 25);
        upiConfirmationCheck.setBackground(Color.WHITE);
        upiConfirmationCheck.setVisible(false);
        add(upiConfirmationCheck);

        uploadScreenshotButton = new JButton("Upload Payment Screenshot");
        uploadScreenshotButton.setBounds(470, 690, 250, 25);
        uploadScreenshotButton.setVisible(false);
        uploadScreenshotButton.addActionListener(this);
        add(uploadScreenshotButton);

        confirmScreenshotButton = new JButton("Confirm Screenshot");
        confirmScreenshotButton.setBounds(470, 720, 250, 25);
        confirmScreenshotButton.setVisible(false);
        confirmScreenshotButton.addActionListener(this);
        add(confirmScreenshotButton);

        uploadedImagePreview = new JLabel();
        uploadedImagePreview.setBounds(470, 750, 250, 250);
        add(uploadedImagePreview);

        payButton = new JButton("Pay Now");
        payButton.setBackground(Color.BLACK);
        payButton.setForeground(Color.WHITE);
        payButton.setBounds(220, 500, 120, 30);
        payButton.addActionListener(this);
        add(payButton);

        flightClassOptions.addActionListener(e -> {
            String flightClass = (String) flightClassOptions.getSelectedItem();
            tfamount.setText(flightClass.equals("Economy") ? "5000" : flightClass.equals("Business") ? "12000" : "20000");
        });

        paymentOptions.addActionListener(e -> {
            String method = (String) paymentOptions.getSelectedItem();
            cardDetailsPanel.setVisible(!method.equals("UPI"));
            upiConfirmationCheck.setVisible(method.equals("UPI"));
            uploadScreenshotButton.setVisible(false);
            confirmScreenshotButton.setVisible(false);
            uploadedImagePreview.setIcon(null);
            isScreenshotConfirmed = false;
            if (method.equals("UPI")) {
                ImageIcon qrIcon = new ImageIcon(getClass().getResource("/airlinemanagementsystem/icons/qr.jpg"));
                Image qrImg = qrIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
                qrImage.setIcon(new ImageIcon(qrImg));
            } else {
                qrImage.setIcon(null);
            }
        });

        upiConfirmationCheck.addActionListener(e -> uploadScreenshotButton.setVisible(upiConfirmationCheck.isSelected()));

        setSize(800, 1050);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();
            try {
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery("SELECT * FROM reservation WHERE PNR = '" + pnr + "'");
                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a correct PNR.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == uploadScreenshotButton) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                uploadedImagePreview.setIcon(new ImageIcon(img));
                confirmScreenshotButton.setVisible(true);
            }
        } else if (ae.getSource() == confirmScreenshotButton) {
            isScreenshotConfirmed = true;
            JOptionPane.showMessageDialog(null, "Screenshot confirmed. You can now proceed.");
        } else if (ae.getSource() == payButton) {
            String pnr = tfpnr.getText();
            String name = tfname.getText();
            String amount = tfamount.getText();
            String method = (String) paymentOptions.getSelectedItem();
            String paymentNo = lblpaymentno.getText();
            String cardno = tfcardnumber.getText();
            String bank = tfbankname.getText();

            if (pnr.isEmpty() || name.isEmpty() || amount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fetch details first.");
                return;
            }

            if (!method.equals("UPI")) {
                if (cardno.isEmpty() || bank.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the card/bank details.");
                    return;
                }

                if (!cardno.matches("\\d{12,19}")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid card number (12-19 digits).");
                    return;
                }

                if (!bank.matches("[a-zA-Z ]{3,}")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid bank name (letters only).");
                    return;
                }
            }

            if (method.equals("UPI")) {
                if (!upiConfirmationCheck.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please check the UPI confirmation box.");
                    return;
                }
                if (!isScreenshotConfirmed) {
                    JOptionPane.showMessageDialog(null, "Please upload and confirm your UPI payment screenshot.");
                    return;
                }
            }

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO payment (PNR, name, payment_no, amount, method, card_number, bank) " +
                        "VALUES ('" + pnr + "', '" + name + "', '" + paymentNo + "', '" + amount + "', '" + method + "', '" + cardno + "', '" + bank + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Payment Successful!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PaymentDetails();
    }
}
