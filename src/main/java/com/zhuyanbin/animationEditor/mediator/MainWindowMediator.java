package com.zhuyanbin.animationEditor.mediator;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.model.ImageVO;
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
                NotiConst.S_MEDIATOR_MAIN_RESIZE,
                NotiConst.S_MEDIATOR_MAIN_DRAW_COORDINATE,
                NotiConst.S_MEDIATOR_MAIN_OPEN_IMAGE_FILES
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
        else if (notiName.equals(NotiConst.S_MEDIATOR_MAIN_DRAW_COORDINATE))
        {
            GC gc = (GC) notification.getBody();
            getViewComponent().drawCoordinate(gc);
        }
        else if (notiName.equals(NotiConst.S_MEDIATOR_MAIN_OPEN_IMAGE_FILES))
        {
            String fileNames[] = (String [])notification.getBody();
            if (null != fileNames)
            {
                int len = fileNames.length;
                if (len > 0)
                {
                    getViewComponent().getAnimationShowCanvas().clean();
                }
                
                for (int i = 0; i< len; i++)
                {
                    getViewComponent().getAnimationShowCanvas().addImageVO(new ImageVO(fileNames[i]));
                }
            }
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
