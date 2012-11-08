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
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public final class MainWindow extends Shell
{
    private ResizeListener _resizeListener;
    private PrewviewShowCanvasPaintListener _pscPaintListener;
    private Text tSpeed;
    private Text tActWidth;
    private Text tRepeat;
    private Text tActHeight;
    private Text tDirectWidth;
    private Text tDirectHeight;
    private PreviewShowCanvas _psc;
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
        setSize(1200, 640);
        
        Label lbAction = new Label(this, SWT.NONE);
        lbAction.setBounds(10, 10, 36, 14);
        lbAction.setText("动作:");
        
        CCombo ccAction = new CCombo(this, SWT.BORDER);
        ccAction.setEditable(false);
        ccAction.setBounds(52, 9, 62, 14);
        
        Label lbSpeed = new Label(this, SWT.NONE);
        lbSpeed.setBounds(128, 10, 36, 14);
        lbSpeed.setText("速度:");
        
        tSpeed = new Text(this, SWT.BORDER);
        tSpeed.setBounds(164, 5, 40, 20);
        
        Label lbActWidth = new Label(this, SWT.NONE);
        lbActWidth.setBounds(132, 36, 26, 14);
        lbActWidth.setText("宽:");
        
        tActWidth = new Text(this, SWT.BORDER);
        tActWidth.setBounds(164, 30, 40, 20);
        
        Label lbRepeat = new Label(this, SWT.NONE);
        lbRepeat.setBounds(211, 10, 36, 14);
        lbRepeat.setText("重复:");
        
        tRepeat = new Text(this, SWT.BORDER);
        tRepeat.setBounds(253, 7, 40, 20);
        
        Label lbActHeight = new Label(this, SWT.NONE);
        lbActHeight.setBounds(221, 36, 26, 14);
        lbActHeight.setText("高:");
        
        tActHeight = new Text(this, SWT.BORDER);
        tActHeight.setBounds(253, 30, 40, 20);
        
        Label lbDirection = new Label(this, SWT.NONE);
        lbDirection.setBounds(10, 63, 36, 14);
        lbDirection.setText("方向:");
        
        CCombo ccDirection = new CCombo(this, SWT.BORDER);
        ccDirection.setEditable(false);
        ccDirection.setBounds(52, 62, 62, 14);
        
        Label lbDirectWidth = new Label(this, SWT.NONE);
        lbDirectWidth.setText("宽:");
        lbDirectWidth.setBounds(132, 62, 26, 14);
        
        tDirectWidth = new Text(this, SWT.BORDER);
        tDirectWidth.setBounds(164, 62, 40, 20);
        
        Label lbDirectHeight = new Label(this, SWT.NONE);
        lbDirectHeight.setText("高:");
        lbDirectHeight.setBounds(221, 62, 26, 14);
        
        tDirectHeight = new Text(this, SWT.BORDER);
        tDirectHeight.setBounds(253, 62, 40, 20);
        
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
        
        Button btnAboutMe = new Button(this, SWT.NONE);
        btnAboutMe.setBounds(984, 3, 94, 28);
        btnAboutMe.setText("关于");
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
            removePaintListener(_pscPaintListener);
            _pscPaintListener = null;
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
    
    class PrewviewShowCanvasPaintListener implements PaintListener
    {
        @Override
        public void paintControl(PaintEvent e)
        {
            Facade.getInstance().sendNotification(NotiConst.S_MEDIATOR_MAIN_DRAW_COORDINATE, e.gc);
        }
    }
}
