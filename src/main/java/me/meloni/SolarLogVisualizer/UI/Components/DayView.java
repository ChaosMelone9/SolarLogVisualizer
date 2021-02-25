/*
Copyright 2020 - 2021 Christoph Kohnen
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package me.meloni.SolarLogVisualizer.UI.Components;

import me.meloni.SolarLogAPI.BasicGUI.Components.DatePicker;
import me.meloni.SolarLogAPI.DataConversion.GetStartOf;
import me.meloni.SolarLogAPI.SolarMap;
import me.meloni.SolarLogVisualizer.Config.Colors;
import me.meloni.SolarLogVisualizer.UI.Visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DayView extends JPanel {
    private me.meloni.SolarLogAPI.BasicGUI.Components.Graph.DayView cmp;
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
                Date date = GetStartOf.day(event.getNewDate());
                cmp = new me.meloni.SolarLogAPI.BasicGUI.Components.Graph.DayView(solarMap, date);
                paintComponent();
                instance.setDate(date.toString());
            } else {
                if(!(event.getOldDate() == null)){
                    picker.setDate(event.getOldDate());
                }
            }
        });
        picker.setBackground(Colors.optionsColor);

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
        b1.setBackground(Colors.optionsColor);
        b2.setBackground(Colors.optionsColor);
        b3.setBackground(Colors.optionsColor);
        b4.setBackground(Colors.optionsColor);
        b5.setBackground(Colors.optionsColor);
        b1.setForeground(Colors.fontColor);
        b2.setForeground(Colors.fontColor);
        b3.setForeground(Colors.fontColor);
        b4.setForeground(Colors.fontColor);
        b5.setForeground(Colors.fontColor);
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
        p.setBackground(Colors.optionsColor);

        JCheckBox mouseGUI = new JCheckBox();
        mouseGUI.setText("MouseGUI");
        mouseGUI.setSelected(true);
        mouseGUI.setBackground(Colors.optionsColor);
        mouseGUI.setForeground(Colors.fontColor);
        mouseGUI.addActionListener(actionEvent -> {
            cmp.setMouseGUIVisible(mouseGUI.isSelected());
            paintComponent();
        });
        p.add(mouseGUI);

        add(p,BorderLayout.WEST);
        setBackground(Colors.optionsColor);
    }

    private void paintComponent() {
        cmp.setBackgroundColor(Colors.backgroundColor);
        instance.setGraph(cmp);
    }

}
