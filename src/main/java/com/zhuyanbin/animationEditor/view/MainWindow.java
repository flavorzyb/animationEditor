package com.zhuyanbin.animationEditor.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.puremvc.java.patterns.facade.Facade;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.view.mainwindow.PreviewShowCanvas;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;

public final class MainWindow extends Shell
{
    private ResizeListener _resizeListener;
    private PrewviewShowCanvasPaintListener _pscPaintListener;
    private AboutMeMouseListener _aboutMeMouseListener;
    private PreviewShowCanvas _psc;
    private Button _btnAboutMe;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Text text_3;
    
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
        setText("动画编辑器");
        setSize(1200, 640);
        
        Composite cPreviewShowComposite = new Composite(this, SWT.BORDER);
        cPreviewShowComposite.setBounds(10, 140, 450, 450);
        
        _psc = new PreviewShowCanvas(cPreviewShowComposite, SWT.NONE);
        
        Button btnNewButton = new Button(this, SWT.NONE);
        btnNewButton.setBounds(640, 0, 94, 28);
        btnNewButton.setText("导入图片");
        
        Composite composite_1 = new Composite(this, SWT.BORDER);
        composite_1.setBackground(SWTResourceManager.getColor(0, 0, 0));
        composite_1.setBounds(630, 140, 450, 450);
        
        Button btnjta = new Button(this, SWT.NONE);
        btnjta.setBounds(763, 0, 94, 28);
        btnjta.setText("导入jta文件");
        
        _btnAboutMe = new Button(this, SWT.NONE);
        _btnAboutMe.setBounds(984, 3, 94, 28);
        _btnAboutMe.setText("关于");
        
        Group group_1 = new Group(this, SWT.NONE);
        group_1.setText("帧设置");
        group_1.setBounds(10, 7, 248, 95);
        
        Label label_1 = new Label(group_1, SWT.NONE);
        label_1.setText("速度:");
        label_1.setBounds(10, 10, 36, 14);
        
        text_1 = new Text(group_1, SWT.BORDER);
        text_1.setBounds(50, 8, 40, 20);
        
        Label label_2 = new Label(group_1, SWT.NONE);
        label_2.setText("宽:");
        label_2.setBounds(10, 38, 26, 14);
        
        text_2 = new Text(group_1, SWT.BORDER);
        text_2.setBounds(50, 36, 40, 20);
        
        text_3 = new Text(group_1, SWT.BORDER);
        text_3.setBounds(118, 35, 40, 20);
        
        Label label_3 = new Label(group_1, SWT.NONE);
        label_3.setText("高:");
        label_3.setBounds(96, 38, 26, 14);
        
        Button btnNewButton_1 = new Button(group_1, SWT.NONE);
        btnNewButton_1.setBounds(130, 3, 94, 28);
        btnNewButton_1.setText("保存设置");
        
        Group group = new Group(this, SWT.NONE);
        group.setBounds(264, 7, 220, 74);
        group.setText("系统设置");
        
        Label label = new Label(group, SWT.NONE);
        label.setText("重复:");
        label.setBounds(10, 13, 36, 14);
        
        text = new Text(group, SWT.BORDER);
        text.setBounds(52, 10, 40, 20);
        
        Button btnNewButton_2 = new Button(group, SWT.NONE);
        btnNewButton_2.setBounds(98, 6, 94, 28);
        btnNewButton_2.setText("保存设置");
    }
    
    public void drawCoordinate(GC gc)
    {
        if (null != _psc)
        {
            _psc.drawCoordinate(gc);
        }
    }
    
    @Override
    protected void checkSubclass()
    {
    }
    
    private void addEvents()
    {
        _resizeListener = new ResizeListener();
        addListener(SWT.Resize, _resizeListener);
        
        _pscPaintListener = new PrewviewShowCanvasPaintListener();
        _psc.addPaintListener(_pscPaintListener);
        
        _aboutMeMouseListener = new AboutMeMouseListener();
        _btnAboutMe.addMouseListener(_aboutMeMouseListener);
    }
    
    private void removeEvents()
    {
        if (null != _resizeListener)
        {
            removeListener(SWT.Resize, _resizeListener);
            _resizeListener = null;
        }
        
        if (null != _pscPaintListener)
        {
            _psc.removePaintListener(_pscPaintListener);
            _pscPaintListener = null;
        }
        
        if (null != _aboutMeMouseListener)
        {
            _btnAboutMe.removeMouseListener(_aboutMeMouseListener);
            _aboutMeMouseListener = null;
        }
    }
    
    @Override
    public void dispose()
    {
        removeEvents();
        super.dispose();
    }
    
    private MainWindow getMainWindow()
    {
        return this;
    }
    
    class ResizeListener implements Listener
    {
        @Override
        public void handleEvent (Event event)
        {
            Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_MAIN_RESIZE);
        }
    }
    
    class PrewviewShowCanvasPaintListener implements PaintListener
    {
        @Override
        public void paintControl(PaintEvent e)
        {
            Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_MAIN_DRAW_COORDINATE, e.gc);
        }
    }
    
    class AboutMeMouseListener implements MouseListener
    {
        @Override
        public void mouseDoubleClick(MouseEvent e)
        {
            // do nothing
        }
        
        @Override
        public void mouseDown(MouseEvent e)
        {
            Facade.getInstance().sendNotification(NotiConst.S_COMMAND_ABOUTME_WINDOW_OPEN, getMainWindow());
        }
        
        @Override
        public void mouseUp(MouseEvent e)
        {
            // do nothing
        }
    }
}
