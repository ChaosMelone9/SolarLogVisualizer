package me.meloni.SolarLogVisualizer.UI;

import Handling.SolarMap;
import Interface.DatePicker;
import TransformUtilities.DataConversion.GetStartOf;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;

public class DayView extends JPanel {
    private Interface.Graph.DayView cmp = null;
    private final Visualizer instance;
    public DayView(Visualizer instance) {
        this.instance = instance;
        SolarMap solarMap = instance.getSolarMap();
        setLayout(new BorderLayout());

        DatePicker picker = new DatePicker();
        picker.addVetoPolicy(solarMap);
        picker.setMaximumSize(new Dimension(200,40));
        picker.addDateChangeListener(event -> {
            if(solarMap.includesDay(event.getNewDate())){
                try {
                    Date date = GetStartOf.day(event.getNewDate());
                    cmp = new Interface.Graph.DayView(solarMap.getDayGraphData(date));
                    paintComponent();
                    instance.setDate(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                if(!(event.getOldDate() == null)){
                    picker.setDate(event.getOldDate());
                }
            }
        });

        add(picker, BorderLayout.PAGE_START);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));

        JCheckBox b1 = new JCheckBox();
        JCheckBox b2 = new JCheckBox();
        JCheckBox b3 = new JCheckBox();
        JCheckBox b4 = new JCheckBox();
        JCheckBox b5 = new JCheckBox();
        b1.setText("Row 1");
        b2.setText("Row 2");
        b3.setText("Row 3");
        b4.setText("Row 4");
        b5.setText("Row 5");
        b1.setSelected(true);
        b2.setSelected(true);
        b3.setSelected(true);
        b4.setSelected(true);
        b5.setSelected(true);
        b1.addActionListener(actionEvent -> {
            cmp.setRow1Visible(b1.isSelected());
            paintComponent();
        });
        b2.addActionListener(actionEvent -> {
            cmp.setRow2Visible(b2.isSelected());
            paintComponent();
        });
        b3.addActionListener(actionEvent -> {
            cmp.setRow3Visible(b3.isSelected());
            paintComponent();
        });
        b4.addActionListener(actionEvent -> {
            cmp.setRow4Visible(b4.isSelected());
            paintComponent();
        });
        b5.addActionListener(actionEvent -> {
            cmp.setRow5Visible(b5.isSelected());
            paintComponent();
        });
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);

        JCheckBox mouseGUI = new JCheckBox();
        mouseGUI.setText("MouseGUI");
        mouseGUI.setSelected(true);
        mouseGUI.addActionListener(actionEvent -> {
            cmp.setMouseGUIVisible(mouseGUI.isSelected());
            paintComponent();
        });
        p.add(mouseGUI);

        add(p,BorderLayout.WEST);
    }

    private void paintComponent() {
        instance.setGraph(cmp);
    }

}
