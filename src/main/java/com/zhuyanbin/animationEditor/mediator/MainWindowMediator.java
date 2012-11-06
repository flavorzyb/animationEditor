package com.zhuyanbin.animationEditor.mediator;

import org.eclipse.swt.widgets.Display;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.view.MainWindow;

public final class MainWindowMediator extends Mediator
{
    final public static String NAME = "MainWindowMediator";
    
    public MainWindowMediator(String mediatorName, MainWindow viewComponent)
    {
        super(mediatorName, viewComponent);
    }
    @Override
    public MainWindow getViewComponent()
    {
        return (MainWindow) viewComponent;
    }
    
    @Override
    public String[] listNotificationInterests( )
    {
        return new String[] {
                NotiConst.S_MEDIATOR_MAIN_SHOW,
                NotiConst.S_MEDIATOR_MAIN_RESIZE
        };
    }
    
    @Override
    public void handleNotification( INotification notification )
    {
        String notiName = notification.getName();
        if (notiName.equals(NotiConst.S_MEDIATOR_MAIN_SHOW))
        {
            openWindow();
        }
        else if (notiName.equals(NotiConst.S_MEDIATOR_MAIN_RESIZE))
        {
        }
    } 
    
    private void openWindow()
    {
        try 
        {
            Display display = getViewComponent().getDisplay();
            MainWindow shell = getViewComponent();
            shell.open();
            shell.layout();
            while (!shell.isDisposed()) 
            {
                if (!display.readAndDispatch()) 
                {
                    display.sleep();
                }
            }
            
            display.dispose();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
