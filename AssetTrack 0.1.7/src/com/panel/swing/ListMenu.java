package com.panel.swing;

import com.panel.model.MenuModel;
import com.panel.model.MenuSelection;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;


public class ListMenu<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int overIndex = -1;
    
    private MenuSelection event;
    
    public void addEventMenuSelected(MenuSelection event){
        this.event = event;
    }
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if(SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if(o instanceof MenuModel) {
                        MenuModel menu = (MenuModel) o;
                        if(menu.getType() == MenuModel.MenuType.MENU) {
                            selectedIndex = index;
                            if(event!=null) {
                                event.Selected(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                        repaint();
                }
            }
            public void mouseExited(MouseEvent me) {
                    overIndex  = -1;
                    repaint();
                }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent me) {
            int index = locationToIndex(me.getPoint());
            if (index != overIndex) {
                Object o = model.getElementAt(index);
                if(o instanceof MenuModel) {
                    MenuModel menu = (MenuModel) o;
                    if(menu.getType() == MenuModel.MenuType.MENU) {
                        overIndex = index;
                    }else {
                        
                    }
                    repaint();
                }
            }
        }
     });
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                MenuModel data;
                if(o instanceof MenuModel) {
                    data = (MenuModel)o;
                }else {
                    data = new MenuModel("", o + "", MenuModel.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex == index);
                item.setHover(overIndex == index);
                return item; 
            }
        };
    }
    public void addItem(MenuModel data){
        model.addElement(data);
    }
}
