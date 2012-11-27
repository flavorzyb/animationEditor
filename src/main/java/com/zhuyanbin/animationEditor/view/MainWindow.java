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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public final class MainWindow extends Shell
{
    private ResizeListener _resizeListener;
    private PrewviewShowCanvasPaintListener _pscPaintListener;
    private AboutMeSelectionListener _aboutMeSelectionListener;
    private PreviewShowCanvas _psc;
    private MenuItem _miAboutMe;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Text text_3;
    private Text text_4;
    private Text text_5;
    
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
        
        Composite composite_1 = new Composite(this, SWT.BORDER);
        composite_1.setBackground(SWTResourceManager.getColor(0, 0, 0));
        composite_1.setBounds(630, 140, 450, 450);
        
        Group group_1 = new Group(this, SWT.NONE);
        group_1.setText("帧设置");
        group_1.setBounds(10, 7, 248, 122);
        
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
        
        Label label_4 = new Label(group_1, SWT.NONE);
        label_4.setBounds(10, 58, 59, 14);
        label_4.setText("锚点坐标:");
        
        text_4 = new Text(group_1, SWT.BORDER);
        text_4.setBounds(118, 74, 40, 20);
        
        Label lblY = new Label(group_1, SWT.NONE);
        lblY.setText("Y:");
        lblY.setBounds(96, 77, 26, 14);
        
        text_5 = new Text(group_1, SWT.BORDER);
        text_5.setBounds(50, 75, 40, 20);
        
        Label lblX = new Label(group_1, SWT.NONE);
        lblX.setText("X:");
        lblX.setBounds(10, 77, 26, 14);
        
        Group gAnimationSettings = new Group(this, SWT.NONE);
        gAnimationSettings.setBounds(264, 7, 220, 75);
        gAnimationSettings.setText("动画设置");
        
        Label label = new Label(gAnimationSettings, SWT.NONE);
        label.setText("播放次数:");
        label.setBounds(10, 13, 58, 14);
        
        text = new Text(gAnimationSettings, SWT.BORDER);
        text.setText("1");
        text.setBounds(68, 10, 40, 20);
        
        Button btnNewButton_2 = new Button(gAnimationSettings, SWT.NONE);
        btnNewButton_2.setBounds(118, 6, 94, 28);
        btnNewButton_2.setText("保存设置");
        
        Menu menu = new Menu(this, SWT.BAR);
        setMenuBar(menu);
        
        MenuItem menuItem_2 = new MenuItem(menu, SWT.CASCADE);
        menuItem_2.setText("文件");
        
        Menu menu_1 = new Menu(menuItem_2);
        menuItem_2.setMenu(menu_1);
        
        MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
        menuItem_3.setText("打开图片");
        
        MenuItem menuItem = new MenuItem(menu_1, SWT.NONE);
        menuItem.setText("导出动画");
        
        MenuItem micHelp = new MenuItem(menu, SWT.CASCADE);
        micHelp.setText("帮助");
        
        Menu menu_2 = new Menu(micHelp);
        micHelp.setMenu(menu_2);
        
        _miAboutMe = new MenuItem(menu_2, SWT.NONE);
        _miAboutMe.setText("关于");
        
        Group group = new Group(this, SWT.NONE);
        group.setText("动画演示");
        group.setBounds(645, 19, 283, 101);
        
        Button btnNewButton = new Button(group, SWT.NONE);
        btnNewButton.setBounds(10, 20, 94, 28);
        btnNewButton.setText("播放动画");
        
        Button button = new Button(group, SWT.NONE);
        button.setBounds(135, 20, 94, 28);
        button.setText("刷新动画");
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
        
        _aboutMeSelectionListener = new AboutMeSelectionListener();
        _miAboutMe.addSelectionListener(_aboutMeSelectionListener);
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
        
        if (null != _aboutMeSelectionListener)
        {
            _miAboutMe.removeSelectionListener(_aboutMeSelectionListener);
            _aboutMeSelectionListener = null;
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
    
    class AboutMeSelectionListener implements SelectionListener
    {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
            Facade.getInstance().sendNotification(NotiConst.S_COMMAND_ABOUTME_WINDOW_OPEN, getMainWindow());
        }
        
        @Override
        public void widgetDefaultSelected(SelectionEvent e)
        {
            // do nothing
        }
    }
}
