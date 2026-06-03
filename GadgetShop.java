import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class GadgetShop extends JFrame implements ActionListener {
    // ArrayList to store gadgets
    private ArrayList<Gadget> gadgets = new ArrayList<>();

    // GUI Text Fields
    private JTextField tfModel, tfPrice, tfWeight, tfSize;
    private JTextField tfCredit, tfMemory, tfPhoneNumber, tfDuration;
    private JTextField tfDownload, tfDisplayNumber;
    
    // GUI Text Area for Output Redirection
    private JTextArea taOutput;

    // GUI Buttons
    private JButton btnAddMobile, btnAddMP3, btnClear, btnDisplayAll, btnMakeCall, btnDownloadMusic;

    public GadgetShop() {
        // Frame Configuration
        setTitle("Gadget Shop CS4001");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Top Panel for Inputs (Grid Layout matching the GUI mockup)
        JPanel panelInputs = new JPanel(new GridLayout(6, 4, 5, 5));

        // Row 1 Labels
        panelInputs.add(new JLabel("Model:"));
        panelInputs.add(new JLabel("Price:"));
        panelInputs.add(new JLabel("Weight:"));
        panelInputs.add(new JLabel("Size:"));

        // Row 2 Fields
        tfModel = new JTextField();
        tfPrice = new JTextField();
        tfWeight = new JTextField();
        tfSize = new JTextField();
        panelInputs.add(tfModel);
        panelInputs.add(tfPrice);
        panelInputs.add(tfWeight);
        panelInputs.add(tfSize);

        // Row 3 Labels & Buttons
        panelInputs.add(new JLabel("Credit:"));
        panelInputs.add(new JLabel("Memory:"));
        btnAddMobile = new JButton("Add Mobile");
        btnAddMP3 = new JButton("Add MP3");
        panelInputs.add(btnAddMobile);
        panelInputs.add(btnAddMP3);

        // Row 4 Fields & Buttons
        tfCredit = new JTextField();
        tfMemory = new JTextField();
        btnClear = new JButton("Clear");
        btnDisplayAll = new JButton("Display All");
        panelInputs.add(tfCredit);
        panelInputs.add(tfMemory);
        panelInputs.add(btnClear);
        panelInputs.add(btnDisplayAll);

        // Row 5 Labels
        panelInputs.add(new JLabel("Phone No:"));
        panelInputs.add(new JLabel("Duration:"));
        panelInputs.add(new JLabel("Download:"));
        panelInputs.add(new JLabel("Display Number:"));

        // Row 6 Fields
        tfPhoneNumber = new JTextField();
        tfDuration = new JTextField();
        tfDownload = new JTextField();
        tfDisplayNumber = new JTextField();
        panelInputs.add(tfPhoneNumber);
        panelInputs.add(tfDuration);
        panelInputs.add(tfDownload);
        panelInputs.add(tfDisplayNumber);

        // Bottom Panel for Execution Actions
        JPanel panelActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnMakeCall = new JButton("Make A Call");
        btnDownloadMusic = new JButton("Download Music");
        panelActions.add(btnMakeCall);
        panelActions.add(btnDownloadMusic);

        // Center Panel to hold the Output Text Area (with Scrollbar)
        taOutput = new JTextArea(8, 50);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);

        // Combining Layout structures into the Frame
        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(panelInputs, BorderLayout.CENTER);
        panelNorth.add(panelActions, BorderLayout.SOUTH);

        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Register Action Listeners
        btnAddMobile.addActionListener(this);
        btnAddMP3.addActionListener(this);
        btnClear.addActionListener(this);
        btnDisplayAll.addActionListener(this);
        btnMakeCall.addActionListener(this);
        btnDownloadMusic.addActionListener(this);

        setVisible(true);
    }

    // --- Helper Methods to Read Input Fields ---
    private String readModel() { return tfModel.getText().trim(); }
    private String readSize() { return tfSize.getText().trim(); }
    private String readPhoneNumber() { return tfPhoneNumber.getText().trim(); }
    
    private double readPrice() { 
        try { return Double.parseDouble(tfPrice.getText().trim()); } catch(Exception e) { return 0.0; }
    }
    private int readWeight() { 
        try { return Integer.parseInt(tfWeight.getText().trim()); } catch(Exception e) { return 0; }
    }
    private int readCredit() { 
        try { return Integer.parseInt(tfCredit.getText().trim()); } catch(Exception e) { return 0; }
    }
    private int readMemory() { 
        try { return Integer.parseInt(tfMemory.getText().trim()); } catch(Exception e) { return 0; }
    }
    private int readDuration() { 
        try { return Integer.parseInt(tfDuration.getText().trim()); } catch(Exception e) { return 0; }
    }
    private int readDownloadSize() { 
        try { return Integer.parseInt(tfDownload.getText().trim()); } catch(Exception e) { return 0; }
    }

    private void captureDisplay(Gadget g) {
        // Optional helper to display instant feedback in text area when adding gadgets
        taOutput.append("Registered: " + g.getModel() + " (" + g.getClass().getSimpleName() + ")\n");
    }

    // --- 4.1 FUNCTION getDisplayNumber() ---
    private int getDisplayNumber() {
        int displayNumber = -1;
        try {
            int input = Integer.parseInt(tfDisplayNumber.getText().trim());
            if (input >= 0 && input < gadgets.size()) {
                displayNumber = input;
            } else {
                JOptionPane.showMessageDialog(this, "Display number out of range.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Display number must be a whole number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return displayNumber;
    }

    // --- 4.2 PROCEDURE addMobile() ---
    private void addMobile() {
        Mobile m = new Mobile(readModel(), readPrice(), readWeight(), readSize(), readCredit());
        gadgets.add(m);
        taOutput.append("Mobile added at index " + (gadgets.size() - 1) + "\n");
        captureDisplay(m);
    }

    // --- 4.3 PROCEDURE addMP3() ---
    private void addMP3() {
        MP3 mp3 = new MP3(readModel(), readPrice(), readWeight(), readSize(), readMemory());
        gadgets.add(mp3);
        taOutput.append("MP3 added at index " + (gadgets.size() - 1) + "\n");
        captureDisplay(mp3);
    }

    // --- 4.4 PROCEDURE displayAll() ---
    private void displayAll() {
        if (gadgets.isEmpty()) {
            taOutput.append("No gadgets in the shop yet.\n");
            return;
        }
        taOutput.append("=== All Gadgets ===\n");
        for (int i = 0; i < gadgets.size(); i++) {
            taOutput.append("Gadget #" + i + "\n");

            // REDIRECT System.out TO ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream oldOut = System.out;
            System.setOut(ps);

            // CALL display()
            gadgets.get(i).display();

            // RESTORE System.out
            System.out.flush();
            System.setOut(oldOut);

            // APPEND captured output TO taOutput
            taOutput.append(baos.toString());
            taOutput.append("----------------------------\n");
        }
    }

    // --- 4.5 PROCEDURE makeCall() ---
    private void makeCall() {
        int idx = getDisplayNumber();
        if (idx == -1) return;

        Gadget g = gadgets.get(idx);
        if (!(g instanceof Mobile)) {
            taOutput.append("Error: not a Mobile phone.\n");
            return;
        }

        String ph = readPhoneNumber();
        int dur = readDuration();

        // REDIRECT System.out
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // CALL makeCall
        ((Mobile) g).makeCall(ph, dur);

        // RESTORE System.out
        System.out.flush();
        System.setOut(oldOut);

        // APPEND captured output TO taOutput
        taOutput.append(baos.toString() + "\n");
    }

    // --- 4.6 PROCEDURE downloadMusic() ---
    private void downloadMusic() {
        int idx = getDisplayNumber();
        if (idx == -1) return;

        Gadget g = gadgets.get(idx);
        if (!(g instanceof MP3)) {
            taOutput.append("Error: not an MP3 player.\n");
            return;
        }

        int ds = readDownloadSize();

        // REDIRECT System.out
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // CALL downloadMusic
        ((MP3) g).downloadMusic(ds);

        // RESTORE System.out
        System.out.flush();
        System.setOut(oldOut);

        // APPEND captured output TO taOutput
        taOutput.append(baos.toString() + "\n");
    }

    // --- Clear Procedure ---
    private void clearFields() {
        tfModel.setText("");
        tfPrice.setText("");
        tfWeight.setText("");
        tfSize.setText("");
        tfCredit.setText("");
        tfMemory.setText("");
        tfPhoneNumber.setText("");
        tfDuration.setText("");
        tfDownload.setText("");
        tfDisplayNumber.setText("");
        taOutput.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddMobile) {
            addMobile();
        } else if (e.getSource() == btnAddMP3) {
            addMP3();
        } else if (e.getSource() == btnDisplayAll) {
            displayAll();
        } else if (e.getSource() == btnMakeCall) {
            makeCall();
        } else if (e.getSource() == btnDownloadMusic) {
            downloadMusic();
        } else if (e.getSource() == btnClear) {
            clearFields();
        }
    }

    public static void main(String[] args) {
        new GadgetShop();
    }
}