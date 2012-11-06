package com.zhuyanbin.animationEditor;

import org.puremvc.java.patterns.facade.Facade;

import com.zhuyanbin.animationEditor.command.MainCommand;

final public class AppFacade extends Facade
{
    protected AppFacade()
    {
    }
    
    /**
     * 获取AppFacade实例化对象
     * 
     * @return 
     *          The Singleton instance of the AppFacade
     */
    public synchronized static AppFacade getInstance()
    {
        if (null == instance)
        {
            instance = new AppFacade();
        }
        
        return (AppFacade) instance;
    }
    
    /**
     * 初始化控制器
     */
    @Override
    protected void initializeController()
    {
        super.initializeController();
        // 注册启动主程序事件
        registerCommand(NotiConst.STARTUP, new MainCommand());
    }
}
