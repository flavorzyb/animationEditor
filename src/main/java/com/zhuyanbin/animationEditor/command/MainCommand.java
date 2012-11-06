package com.zhuyanbin.animationEditor.command;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.mediator.MainWindowMediator;
import com.zhuyanbin.animationEditor.view.MainWindow;

public final class MainCommand extends SimpleCommand
{
    @Override
    public void execute( INotification notification )
    {
        initMediator();
        sendNotification(NotiConst.S_MEDIATOR_MAIN_SHOW);
    }
    
    private void initMediator()
    {
        if (!facade.hasMediator(MainWindowMediator.NAME))
        {
            facade.registerMediator(new MainWindowMediator(MainWindowMediator.NAME, new MainWindow()));
        }
    }
}
