package com.zhuyanbin.animationEditor.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.puremvc.java.patterns.facade.Facade;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.Version;

public class AboutMeWindow extends Shell
{
    private Button _btnClose;
    private BtnCloseMouseListener _btnCloseMouseListener;
    public AboutMeWindow()
    {
        this(Display.getCurrent());
    }
    /**
     * Create the shell.
     * @param display
     */
    public AboutMeWindow(Display display)
    {
        super(display, SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
        createContents();
        addEvents();
    }
    
    public AboutMeWindow(Composite parent)
    {
        this();
        setParent(parent);
    }
    
    /**
     * Create contents of the shell.
     */
    protected void createContents()
    {
        setText("关于AnimationEditor");
        setSize(300, 200);
        
        Label lblNewLabel = new Label(this, SWT.NONE);
        lblNewLabel.setBounds(82, 25, 129, 14);
        lblNewLabel.setText("作者:"+Version.Author);
        
        Label lblNewLabel_1 = new Label(this, SWT.NONE);
        lblNewLabel_1.setBounds(82, 56, 97, 14);
        lblNewLabel_1.setText("版本:"+Version.Version);
        
        Label label = new Label(this, SWT.NONE);
        label.setBounds(82, 91, 129, 14);
        label.setText("更新时间:"+Version.Date);
        
        _btnClose = new Button(this, SWT.NONE);
        _btnClose.setBounds(115, 125, 84, 28);
        _btnClose.setText("关闭");
    }
    
    @Override
    protected void checkSubclass()
    {
    }
    
    private void addEvents()
    {
        _btnCloseMouseListener = new BtnCloseMouseListener();
        _btnClose.addMouseListener(_btnCloseMouseListener);
    }
    
    private void removeEvents()
    {
        if (null != _btnCloseMouseListener)
        {
            _btnClose.removeMouseListener(_btnCloseMouseListener);
            _btnCloseMouseListener = null;
        }
    }
    
    protected void sendCloseEvent()
    {
        Facade.getInstance().sendNotification(NotiConst.S_COMMAND_ABOUTME_WINDOW_CLOSE);
    }
    
    @Override
    public void dispose()
    {
        removeEvents();
        super.dispose();
        sendCloseEvent();
    }
    
    class BtnCloseMouseListener implements MouseListener
    {
        @Override
        public void mouseDoubleClick(MouseEvent e)
        {
            //do nothing
        }
        @Override
        public void mouseDown(MouseEvent e)
        {
            Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_ABOUTME_WINDOW_CLOSE);
        }
        @Override
        public void mouseUp(MouseEvent e)
        {
          //do nothing
        }
    }
}
