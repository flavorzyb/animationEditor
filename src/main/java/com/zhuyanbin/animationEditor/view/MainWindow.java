package com.zhuyanbin.animationEditor.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.puremvc.java.patterns.facade.Facade;

import com.zhuyanbin.animationEditor.NotiConst;
import com.zhuyanbin.animationEditor.view.mainwindow.AnimationShowCanvas;
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
    private OpenImageSelectionListener _openImageSelectionListener;
    private PreviewShowCanvas _previewShowCanvas;
    private AnimationShowCanvas _anAnimationShowCanvas;
    private MenuItem _miAboutMe;
    private MenuItem _miOpenImage;
    
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
        
        _previewShowCanvas = new PreviewShowCanvas(cPreviewShowComposite, SWT.NONE);
        
        Composite cAnimationShowComposite = new Composite(this, SWT.BORDER);
        cAnimationShowComposite.setBackground(SWTResourceManager.getColor(0, 0, 0));
        cAnimationShowComposite.setBounds(630, 140, 450, 450);
        
        Group gFrameSettings = new Group(this, SWT.NONE);
        gFrameSettings.setText("帧设置");
        gFrameSettings.setBounds(10, 7, 248, 122);
        
        Label lbFrameSpeed = new Label(gFrameSettings, SWT.NONE);
        lbFrameSpeed.setText("速度:");
        lbFrameSpeed.setBounds(10, 10, 36, 14);
        lbFrameSpeed.setToolTipText("播放延迟时间,单位:毫秒,1秒=1000毫秒");
        
        Text tFrameSpeed = new Text(gFrameSettings, SWT.BORDER);
        tFrameSpeed.setText("0.3");
        tFrameSpeed.setBounds(50, 8, 40, 20);
        tFrameSpeed.setToolTipText("播放延迟时间,单位:毫秒,1秒=1000毫秒");
        
        Label lbFrameWidth = new Label(gFrameSettings, SWT.NONE);
        lbFrameWidth.setText("宽:");
        lbFrameWidth.setBounds(10, 38, 26, 14);
        
        Text tFrameWidth = new Text(gFrameSettings, SWT.BORDER);
        tFrameWidth.setBounds(50, 36, 40, 20);
        
        Label lbFrameHeight = new Label(gFrameSettings, SWT.NONE);
        lbFrameHeight.setText("高:");
        lbFrameHeight.setBounds(96, 38, 26, 14);
        
        Text tFrameHeight = new Text(gFrameSettings, SWT.BORDER);
        tFrameHeight.setBounds(118, 35, 40, 20);
        
        Button btnSaveFrameSettings = new Button(gFrameSettings, SWT.NONE);
        btnSaveFrameSettings.setBounds(130, 3, 94, 28);
        btnSaveFrameSettings.setText("保存设置");
        
        Label lbAnchor = new Label(gFrameSettings, SWT.NONE);
        lbAnchor.setBounds(10, 58, 59, 14);
        lbAnchor.setText("锚点坐标:");
        
        Label lbAnchorX = new Label(gFrameSettings, SWT.NONE);
        lbAnchorX.setText("X:");
        lbAnchorX.setBounds(10, 77, 26, 14);
        
        Text tFrameX = new Text(gFrameSettings, SWT.BORDER);
        tFrameX.setBounds(50, 75, 40, 20);

        Label lbAnchorY = new Label(gFrameSettings, SWT.NONE);
        lbAnchorY.setText("Y:");
        lbAnchorY.setBounds(96, 77, 26, 14);
        
        Text tFrameY = new Text(gFrameSettings, SWT.BORDER);
        tFrameY.setBounds(118, 74, 40, 20);
        
        Group gAnimationSettings = new Group(this, SWT.NONE);
        gAnimationSettings.setBounds(264, 7, 220, 113);
        gAnimationSettings.setText("动画设置");
        
        Label lbPlayCount = new Label(gAnimationSettings, SWT.NONE);
        lbPlayCount.setText("播放次数:");
        lbPlayCount.setBounds(10, 13, 58, 14);
        
        Text tPlayCount = new Text(gAnimationSettings, SWT.BORDER);
        tPlayCount.setText("1");
        tPlayCount.setBounds(68, 10, 40, 20);
        
        Button btnSaveAnimationSettings = new Button(gAnimationSettings, SWT.NONE);
        btnSaveAnimationSettings.setBounds(118, 6, 94, 28);
        btnSaveAnimationSettings.setText("保存设置");
        
        Label lbPlayCountTips = new Label(gAnimationSettings, SWT.NONE);
        lbPlayCountTips.setBounds(10, 36, 202, 50);
        lbPlayCountTips.setText("提示:\n  播放次数要大于等于0，等于0表示\n  永久循环播放，否则只播放指定次数");
        
        Menu menu = new Menu(this, SWT.BAR);
        setMenuBar(menu);
        
        MenuItem miFile = new MenuItem(menu, SWT.CASCADE);
        miFile.setText("文件");
        
        Menu mFile = new Menu(miFile);
        miFile.setMenu(mFile);
        
        _miOpenImage = new MenuItem(mFile, SWT.NONE);
        _miOpenImage.setText("打开图片");
        
        MenuItem menuItem = new MenuItem(mFile, SWT.NONE);
        menuItem.setText("导出动画");
        
        MenuItem micHelp = new MenuItem(menu, SWT.CASCADE);
        micHelp.setText("帮助");
        
        Menu mHelp = new Menu(micHelp);
        micHelp.setMenu(mHelp);
        
        _miAboutMe = new MenuItem(mHelp, SWT.NONE);
        _miAboutMe.setText("关于");
        
        Group gAnimationShow = new Group(this, SWT.NONE);
        gAnimationShow.setText("动画演示");
        gAnimationShow.setBounds(645, 19, 283, 101);
        
        Button btnPlayAnimation = new Button(gAnimationShow, SWT.NONE);
        btnPlayAnimation.setBounds(10, 20, 94, 28);
        btnPlayAnimation.setText("播放动画");
        
        Button btnFreshAnimation = new Button(gAnimationShow, SWT.NONE);
        btnFreshAnimation.setBounds(135, 20, 94, 28);
        btnFreshAnimation.setText("刷新动画");
        
        _anAnimationShowCanvas = new AnimationShowCanvas(cAnimationShowComposite, SWT.NONE);
    }
    
    public void drawCoordinate(GC gc)
    {
        if (null != _previewShowCanvas)
        {
            _previewShowCanvas.drawCoordinate(gc);
        }
    }
    
    public void redrawAnimation()
    {
        if (null != _anAnimationShowCanvas)
        {
            _anAnimationShowCanvas.redraw();
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
        _previewShowCanvas.addPaintListener(_pscPaintListener);
        
        _aboutMeSelectionListener = new AboutMeSelectionListener();
        _miAboutMe.addSelectionListener(_aboutMeSelectionListener);
        
        _openImageSelectionListener = new OpenImageSelectionListener();
        _miOpenImage.addSelectionListener(_openImageSelectionListener);
        _anAnimationShowCanvas.addPaintListener(new PaintListener()
        {
            @Override
            public void paintControl(PaintEvent e)
            {
                _anAnimationShowCanvas.show();
            }
        });
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
            _previewShowCanvas.removePaintListener(_pscPaintListener);
            _pscPaintListener = null;
        }
        
        if (null != _aboutMeSelectionListener)
        {
            _miAboutMe.removeSelectionListener(_aboutMeSelectionListener);
            _aboutMeSelectionListener = null;
        }
        
        if (null != _openImageSelectionListener)
        {
            _miOpenImage.removeSelectionListener(_openImageSelectionListener);
            _openImageSelectionListener = null;
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
    
    class OpenImageSelectionListener implements SelectionListener
    {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
            FileDialog fd = new FileDialog(getShell(), SWT.APPLICATION_MODAL | SWT.MULTI);
            String []filter = {"*.png","*.jpg","*.gif"};
            fd.setText("打开图片");
            fd.setFilterExtensions(filter);
            if (null != fd.open())
            {
                String [] fileNames = fd.getFileNames();
                for (int i = 0; i < fileNames.length; i++)
                {
                    fileNames[i] = fd.getFilterPath() + "/" + fileNames[i];
                }
                
                Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_MAIN_OPEN_IMAGE_FILES, fileNames);
            }
        }
        
        @Override
        public void widgetDefaultSelected(SelectionEvent e)
        {
            // do nothing
        }
    }
}
