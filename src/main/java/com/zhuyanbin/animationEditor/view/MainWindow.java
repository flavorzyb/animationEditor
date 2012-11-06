package com.zhuyanbin.animationEditor.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.puremvc.java.patterns.facade.Facade;

import com.zhuyanbin.animationEditor.NotiConst;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;

public final class MainWindow extends Shell
{
    private ResizeListener _resizeListener;
    private Text tSpeed;
    private Text tWidth;
    private Text tRepeat;
    private Text tHeight;
    
    public MainWindow()
    {
        this(Display.getDefault());
    }
    
    /**
     * Create the shell.
     * @param display
     */
    public MainWindow(Display display)
    {
        super(display, SWT.SHELL_TRIM);
        createContents();
        addEvents();
    }
    
    /**
     * Create contents of the shell.
     */
    protected void createContents()
    {
        setText("动作编辑器");
        setSize(1000, 600);
        
        Label lbAction = new Label(this, SWT.NONE);
        lbAction.setBounds(10, 10, 36, 14);
        lbAction.setText("动作:");
        
        CCombo ccAction = new CCombo(this, SWT.BORDER);
        ccAction.setEditable(false);
        ccAction.setBounds(52, 9, 62, 14);
        
        Label lbDirection = new Label(this, SWT.NONE);
        lbDirection.setBounds(10, 63, 36, 14);
        lbDirection.setText("方向:");
        
        CCombo ccDirection = new CCombo(this, SWT.BORDER);
        ccDirection.setEditable(false);
        ccDirection.setBounds(52, 62, 62, 14);
        
        Label lbSpeed = new Label(this, SWT.NONE);
        lbSpeed.setBounds(128, 10, 36, 14);
        lbSpeed.setText("速度:");
        
        tSpeed = new Text(this, SWT.BORDER);
        tSpeed.setBounds(164, 5, 40, 20);
        
        Label lbWidth = new Label(this, SWT.NONE);
        lbWidth.setBounds(132, 36, 26, 14);
        lbWidth.setText("宽:");
        
        tWidth = new Text(this, SWT.BORDER);
        tWidth.setBounds(164, 30, 40, 20);
        
        Label lbRepeat = new Label(this, SWT.NONE);
        lbRepeat.setBounds(211, 10, 36, 14);
        lbRepeat.setText("重复:");
        
        tRepeat = new Text(this, SWT.BORDER);
        tRepeat.setBounds(253, 7, 40, 20);
        
        Label lbHeight = new Label(this, SWT.NONE);
        lbHeight.setBounds(221, 36, 26, 14);
        lbHeight.setText("高:");
        
        tHeight = new Text(this, SWT.BORDER);
        tHeight.setBounds(253, 30, 40, 20);
    }
    
    @Override
    protected void checkSubclass()
    {
    }
    
    private void addEvents()
    {
        _resizeListener = new ResizeListener();
        addListener(SWT.Resize, _resizeListener);
    }
    
    private void removeEvents()
    {
        if (null != _resizeListener)
        {
            removeListener(SWT.Resize, _resizeListener);
            _resizeListener = null;
        }
    }
    
    @Override
    public void dispose()
    {
        removeEvents();
        super.dispose();
    }
    
    class ResizeListener implements Listener
    {
        @Override
        public void handleEvent (Event event)
        {
            Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_MAIN_RESIZE);
        }
    }
}
