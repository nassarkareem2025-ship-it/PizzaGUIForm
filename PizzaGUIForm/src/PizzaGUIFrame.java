import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame implements ActionListener {

    // VARIABLES
    double subtotal;
    double tax = subtotal * .07;
    double total = subtotal + tax;
    double topping = 1;
    String selectedCrust;
    String[] crusts = {"Thin", "Regular", "Deep-Dish"};
    String[] sizes = {"small","regular","large","super"};
    double[] pricing = {8.00,12.00,16.00,20.00};
    double DDP = 2.50;

    // JFRAME ELEMENTS

    // JComboBox for sizes
    JComboBox<String> sizebox = new JComboBox<>(sizes);

    // JRadioButtons for crusts
    JRadioButton thinCrust = new JRadioButton("Thin");
    JRadioButton regularCrust = new JRadioButton("Regular");
    JRadioButton deepDishCrust = new JRadioButton("Deep-Dish");
    ButtonGroup crustGroup = new ButtonGroup();

    // JCheckBoxes for toppings
    JCheckBox worcestershire = new JCheckBox("Worcestershire Sauce");
    JCheckBox rawTuna = new JCheckBox("Raw Tuna");
    JCheckBox pickledGrapes = new JCheckBox("Pickled Grapes");
    JCheckBox friedTurnips = new JCheckBox("Fried Turnips");
    JCheckBox grasshopper = new JCheckBox("Roasted Grasshopper");
    JCheckBox cashews = new JCheckBox("Boiled Cashews");
    JCheckBox[] toppings = {worcestershire,rawTuna,pickledGrapes,friedTurnips,
            grasshopper,cashews};

    // Receipt area
    JTextArea receipt = new JTextArea(50, 40);
    JScrollPane scrollPane = new JScrollPane(receipt);

    // --- Buttons ---
    JButton quitBtn = new JButton("Quit");
    JButton resetBtn = new JButton("Reset");
    JButton orderBtn = new JButton("Order");

    // CONSTRUCTOR
    public PizzaGUIFrame(){
        setLayout(new BorderLayout());

        receipt.setEditable(false);
        // --- Panels ---
        JPanel PizzaPnl = new JPanel();
        JPanel crustPnl = new JPanel();
        JPanel toppingPnl = new JPanel();
        JPanel sizePnl = new JPanel();
        JPanel finalBtnPnl = new JPanel();
        JPanel rcptPnl = new JPanel();
        JPanel lblrcptPnl = new JPanel();
        JPanel labelPnl = new JPanel();


        // --- Labels ---
        JLabel nameLbl = new JLabel("Nassar's Pizza", JLabel.CENTER);
        JLabel size = new JLabel("Size");
        JLabel crust = new JLabel("Crust");
        JLabel toppings = new JLabel("Toppings");
        JLabel receiptLbl = new JLabel("Receipt");

        // --- Label styling ---
        nameLbl.setFont(new Font("Arial", Font.BOLD, 24));

        receiptLbl.setFont(new Font("Arial", Font.BOLD, 16));
        size.setFont(new Font("Arial", Font.BOLD, 16));
        toppings.setFont(new Font("Arial", Font.BOLD, 16));
        crust.setFont(new Font("Arial", Font.BOLD, 16));

        // --- Add labels to panels ---
        PizzaPnl.add(nameLbl);
        rcptPnl.add(receiptLbl);
        sizePnl.add(size);
        toppingPnl.add(toppings);
        crustPnl.add(crust);
        lblrcptPnl.add(receiptLbl);

        // --- Add buttons to button panel ---
        finalBtnPnl.add(quitBtn);
        finalBtnPnl.add(resetBtn);
        finalBtnPnl.add(orderBtn);

        // --- Add crust radio buttons ---
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDishCrust);
        crustPnl.add(thinCrust);
        crustPnl.add(regularCrust);
        crustPnl.add(deepDishCrust);

        // --- Add size combo box ---
        sizePnl.add(sizebox);

        // --- Add topping checkboxes ---
        toppingPnl.setLayout(new GridLayout(3,2));
        toppingPnl.add(worcestershire);
        toppingPnl.add(rawTuna);
        toppingPnl.add(pickledGrapes);
        toppingPnl.add(friedTurnips);
        toppingPnl.add(grasshopper);
        toppingPnl.add(cashews);

        // --- Add receipt scroll pane ---
        rcptPnl.setLayout(new BoxLayout(rcptPnl, BoxLayout.Y_AXIS));
        rcptPnl.add(lblrcptPnl);
        rcptPnl.add(scrollPane);

        // --- Align and stack left panels vertically ---
        crustPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        sizePnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        toppingPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        PizzaPnl.setLayout(new BoxLayout(PizzaPnl, BoxLayout.Y_AXIS));
        PizzaPnl.add(crustPnl);
        PizzaPnl.add(sizePnl);
        PizzaPnl.add(toppingPnl);


        // --- Add panels to frame ---
        add(finalBtnPnl, BorderLayout.SOUTH);
        add(rcptPnl, BorderLayout.EAST);
        add(PizzaPnl, BorderLayout.WEST);

        // --- Add action listeners ---
        quitBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        orderBtn.addActionListener(this);

        // --- Frame settings ---
        setSize(900,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == orderBtn){
            
            subtotal = getFinalPrice();
            if(total == 0){int invalid = JOptionPane.showConfirmDialog(null, "Please select a size, crust, and at least one topping", "Invalid Order", JOptionPane.OK_CANCEL_OPTION);}
            receipt.setText("============================\n" +
                    "Size: " + sizebox.getSelectedItem() + "                    $" + getSizePrice() + "\n" +
                    "Crust: " + getCrust() + "                    $" + getCrustPrice() + "\n" +
                    "\n" +
                    "Sub-total:                       $" + subtotal + "\n" +
                    "Tax:                               $" + tax + "\n" +
                    "-----------------------------------------\n" +
                    "Total:                             $" + total + "\n" +
                    "===========================");

        }
        if(e.getSource() == resetBtn){
            receipt.setText("");
            subtotal = 0;
            tax = 0;
            total = 0;
            for(JCheckBox t : toppings){
                t.setSelected(false);
            }
            sizebox.setSelectedIndex(0);
            getCrust();
        }
        if(e.getSource() == quitBtn){
            System.exit(0);
        }
    }

    // HELPER METHODS
    private String getCrust(){
        if(thinCrust.isSelected()){selectedCrust = crusts[0];}
        else if (regularCrust.isSelected()){selectedCrust = crusts[1];}
        else {selectedCrust = crusts[2];}
        return selectedCrust;
    }

    private double getFinalPrice(){
        int index = sizebox.getSelectedIndex();
        double basePrice = pricing[index];

        if(getCrust().equals(crusts[2])){basePrice = pricing[index] + DDP;}
        subtotal = basePrice;
        for(JCheckBox t : toppings){
            if(t.isSelected()){subtotal += topping;}
        }

        return subtotal;
    }
    private double getCrustPrice(){
        if(getCrust().equals(crusts[2])){ return DDP; }
        else { return 0.00; }
    }
    private double getSizePrice(){
        int index = sizebox.getSelectedIndex();
        return pricing[index];
    }
}
