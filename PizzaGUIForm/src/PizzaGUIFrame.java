import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame implements ActionListener {
    //Variables
    double subtotal;
    double tax = subtotal * .07;
    double total = subtotal + tax;
    double topping = 1;
    String selectedCrust;
    String[] crusts = {"Thin", "Regular", "Deep-Dish"};
    String[] sizes = {"small","regular","large","super"};
    double[] pricing = {8.00,12.00,16.00,20.00};
    double DDP = 2.50;


    //JFrame elements

    //JComboBox for sizes
    JComboBox<String> sizebox = new JComboBox<>(sizes);

    //JRadio for crusts
    JRadioButton thinCrust = new JRadioButton("Thin");
    JRadioButton regularCrust = new JRadioButton("Regular");
    JRadioButton deepDishCrust = new JRadioButton("Deep-Dish");
    ButtonGroup crustGroup = new ButtonGroup();

    //JCheckbox for toppings
    JCheckBox worcestershire = new JCheckBox("Worcestershire Sauce");
    JCheckBox rawTuna = new JCheckBox("Raw Tuna");
    JCheckBox pickledGrapes = new JCheckBox("Pickled Grapes");
    JCheckBox friedTurnips = new JCheckBox("Fried Turnips");
    JCheckBox grasshopper = new JCheckBox("Roasted Grasshopper");
    JCheckBox cashews = new JCheckBox("Boiled Cashews");
    JCheckBox[] toppings = {worcestershire,rawTuna,pickledGrapes,friedTurnips,
    grasshopper,cashews};
    public PizzaGUIFrame(){
        setLayout(new BorderLayout());

        JPanel PizzaPnl = new JPanel();
        JPanel crustPnl = new JPanel();
        JPanel toppingPnl = new JPanel();
        JPanel sizePnl = new JPanel();
        JPanel finalBtnPnl = new JPanel();
        JPanel rcptPnl = new JPanel();

        JButton quitBtn = new JButton("Quit");
        JButton resetBtn = new JButton("Reset");
        JButton orderBtn = new JButton("Order");

        finalBtnPnl.add(quitBtn);
        finalBtnPnl.add(resetBtn);
        finalBtnPnl.add(orderBtn);

        PizzaPnl.setLayout(new BoxLayout(PizzaPnl, BoxLayout.Y_AXIS));
        crustPnl.add(thinCrust);
        crustPnl.add(regularCrust);
        crustPnl.add(deepDishCrust);
        toppingPnl.add(worcestershire);
        toppingPnl.add(rawTuna);
        toppingPnl.add(pickledGrapes);
        toppingPnl.add(friedTurnips);
        toppingPnl.add(grasshopper);
        toppingPnl.add(cashews);

        sizePnl.add(sizebox);
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDishCrust);

        crustPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        sizePnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        toppingPnl.setAlignmentX(Component.LEFT_ALIGNMENT);

        toppingPnl.setLayout(new GridLayout(3,2));
        PizzaPnl.add(crustPnl);
        PizzaPnl.add(sizePnl);
        PizzaPnl.add(toppingPnl);

        add(finalBtnPnl, BorderLayout.SOUTH);
        add(rcptPnl, BorderLayout.EAST);
        add(PizzaPnl, BorderLayout.WEST);

        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e){


    }

    private String getCrust(){
        if(thinCrust.isSelected()){selectedCrust = crusts[0];}
        else if (regularCrust.isSelected()){selectedCrust = crusts[1];}
        else {selectedCrust = crusts[2];}
        return selectedCrust;
    }

    private double getPrice(){
        int index = sizebox.getSelectedIndex();
        double basePrice = pricing[index];


        if(getCrust().equals(crusts[2])){basePrice = pricing[index] + DDP;}
        subtotal = basePrice;
        for(JCheckBox t : toppings){
            if(t.isSelected()){subtotal += topping;}
        }

        return subtotal;

    }
}