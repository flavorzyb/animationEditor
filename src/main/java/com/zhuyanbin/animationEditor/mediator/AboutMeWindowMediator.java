package com.zhuyanbin.animationEditor.mediator;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.view.AboutMeWindow;

public class AboutMeWindowMediator extends Mediator
{
    public final static String NAME = "AboutMeDialogMediator";
    
    public AboutMeWindowMediator(String mediatorName, AboutMeWindow viewComponent)
    {
        super(mediatorName, viewComponent);
    }
    
    @Override
    public AboutMeWindow getViewComponent()
    {
        return (AboutMeWindow) viewComponent;
    }
    
    @Override
    public String[] listNotificationInterests( )
    {
        return new String[] {
                                NotiConst.S_MEDIATOR_ABOUTME_WINDOW_OPEN,
                                NotiConst.S_MEDIATOR_ABOUTME_WINDOW_CLOSE
                            };
    }
    
    @Override
    public void handleNotification( INotification notification )
    {
        String notiName = notification.getName();
        if (notiName.equals(NotiConst.S_MEDIATOR_ABOUTME_WINDOW_OPEN))
        {
            openWindow();
        }
        else if (notiName.equals(NotiConst.S_MEDIATOR_ABOUTME_WINDOW_CLOSE))
        {
            if (!getViewComponent().isDisposed())
            {
                getViewComponent().dispose();
            }
        }
    }
    
    @Override
    public void onRemove()
    {
        setViewComponent(null);
    }
    
    private void openWindow()
    {
        try
        {
            AboutMeWindow shell = getViewComponent();
            shell.open();
            shell.layout();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
