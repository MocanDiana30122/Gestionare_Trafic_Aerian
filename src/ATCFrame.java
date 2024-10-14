import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ATCFrame extends JFrame {
    private ATC atc;
    private JTextArea logArea; // Text area for logs
    private JTextField idField; // Field for Aircraft ID
    private JTextField typeField; // Field for Aircraft Type
    private JTextField altitudeField; // Field for Altitude input

    public ATCFrame(ATC atc) {
        this.atc = atc;
        setTitle("Air Traffic Control System");
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create a text area for logging
        logArea = new JTextArea();
        logArea.setEditable(false); // Make it read-only
        logArea.setBackground(new Color(230, 230, 230));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Aircraft ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Aircraft ID:"), gbc);
        idField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        // Aircraft Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Aircraft Type:"), gbc);
        typeField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(typeField, gbc);

        // Cruising Altitude
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Cruising Altitude:"), gbc);
        altitudeField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(altitudeField, gbc);

        // Add buttons
        JButton addButton = new JButton("Add Aircraft");
        addButton.addActionListener(this::addAircraft);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(addButton, gbc);

        JButton takeOffButton = new JButton("Take Off");
        takeOffButton.addActionListener(this::sendTakeoffCommand);
        gbc.gridx = 1;
        inputPanel.add(takeOffButton, gbc);

        JButton landButton = new JButton("Land");
        landButton.addActionListener(this::sendLandCommand);
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(landButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Button to show aircrafts
        JButton showButton = new JButton("Show Aircrafts");
        showButton.addActionListener(e -> showAircrafts());
        add(showButton, BorderLayout.SOUTH);
    }

    // Add the rest of the methods here (addAircraft, sendTakeoffCommand, etc.)

    private void addAircraft(ActionEvent event) {
        String id = idField.getText();
        String type = typeField.getText();
        if (!id.isEmpty() && !type.isEmpty()) {
            atc.addAircraft(id, type);
            logArea.append("Added aircraft: " + id + " of type: " + type + "\n");
            idField.setText("");
            typeField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both ID and Type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendTakeoffCommand(ActionEvent event) {
        String id = idField.getText();
        String altitudeStr = altitudeField.getText();
        if (!id.isEmpty() && !altitudeStr.isEmpty()) {
            try {
                int altitude = Integer.parseInt(altitudeStr);
                atc.sendCommand(id, new AtcCommand("takeoff", altitude));
                logArea.append("Sent takeoff command to aircraft: " + id + " at altitude: " + altitude + "\n");
                altitudeField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer for altitude.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Aircraft ID and Cruising Altitude.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendLandCommand(ActionEvent event) {
        String id = idField.getText();
        if (!id.isEmpty()) {
            atc.sendCommand(id, new AtcCommand("land", 0));
            logArea.append("Sent land command to aircraft: " + id + "\n");
            idField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Aircraft ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAircrafts() {
        logArea.append("Current Aircrafts:\n");
        for (String aircraft : atc.getAircrafts()) {
            logArea.append(aircraft + "\n");
        }
    }
}
