package com.zhuyanbin.animationEditor;

import org.puremvc.java.patterns.observer.Notification;

public final class App 
{
    public App()
    {
    }
    
    public static void main(String args[])
    {
        AppFacade.getInstance().notifyObservers(new Notification(NotiConst.STARTUP));
    }
}
