
package com.panel.swing;

import com.panel.model.MenuModel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class MenuList<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int hoverIndex = -1;
    
    public MenuList() 
    {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent evt) 
                {
                if (SwingUtilities.isLeftMouseButton(evt)) 
                {
                    int index = locationToIndex(evt.getPoint());
                    Object o = model.getElementAt(index);
                        if(o instanceof MenuModel) 
                        {
                            MenuModel menu = (MenuModel)o;
                            if(menu.getType() == MenuModel.MenuType.MENU)
                            {
                            selectedIndex = index;
                            }
                         } else 
                            
                        {
                    selectedIndex = index;
                    }
                repaint();
              }
          }
            @Override
                public void mouseExited(MouseEvent evt)
                {
                    hoverIndex = -1;
                    repaint();
                }
     });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) 
                {
                    int index = locationToIndex(evt.getPoint());
                    if(index != hoverIndex)
                    {
                        Object o = model.getElementAt(index);
                            if(o instanceof MenuModel) 
                                {
                                    MenuModel menu = (MenuModel) o;
                                      if(menu.getType() == MenuModel.MenuType.MENU)
                                        {
                                            hoverIndex = index;
                                        } else 
                                        {
                                            hoverIndex = -1;
                                        }
                                      repaint();
                                }
                    }
                }
        });
        
    }
        
        @Override
        public ListCellRenderer<? super E> getCellRenderer()
        {
            return new DefaultListCellRenderer(){
                @Override
                public Component getListCellRendererComponent(JList<?> jlist , Object o, int index, boolean selected, boolean focus)
                {
                    MenuModel data;
                    if(o instanceof MenuModel) 
                    {
                        data = (MenuModel) o;
                    } else
                        {
                            data = new MenuModel("", 0 + "", MenuModel.MenuType.EMPTY);
                        }
                    MenuItem set = new MenuItem(data);
                    set.setSelected(selectedIndex == index);
                    set.setHover(hoverIndex == index);
                    return set;
                }
        };
    }
        
        public void addItem(MenuModel data)
        {
            model.addElement(data);
        }
}