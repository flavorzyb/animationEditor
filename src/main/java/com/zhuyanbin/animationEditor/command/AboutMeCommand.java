package com.zhuyanbin.animationEditor.command;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.mediator.AboutMeWindowMediator;
import com.zhuyanbin.animationEditor.view.AboutMeWindow;
import com.zhuyanbin.animationEditor.view.MainWindow;

public class AboutMeCommand extends SimpleCommand
{
    @Override
    public void execute( INotification notification )
    {
        String notiName = notification.getName();
        if (notiName.equals(NotiConst.S_COMMAND_ABOUTME_WINDOW_OPEN))
        {
            MainWindow mw = (MainWindow) notification.getBody();
            initMediator(mw);
            sendNotification(NotiConst.S_MEDIATOR_ABOUTME_WINDOW_OPEN);
        }
        else if (notiName.equals(NotiConst.S_COMMAND_ABOUTME_WINDOW_CLOSE))
        {
            sendNotification(NotiConst.S_MEDIATOR_ABOUTME_WINDOW_CLOSE);
            removeMediator();
        }
    }
    
    private void initMediator(MainWindow mw)
    {
        if (!facade.hasMediator(AboutMeWindowMediator.NAME))
        {
            facade.registerMediator(new AboutMeWindowMediator(AboutMeWindowMediator.NAME, new AboutMeWindow(mw)));
        }
    }
    
    private void removeMediator()
    {
        if (facade.hasMediator(AboutMeWindowMediator.NAME))
        {
            facade.removeMediator(AboutMeWindowMediator.NAME);
        }
    }
}
