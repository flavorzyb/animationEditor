package com.zhuyanbin.animationEditor.view.mainwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class PreviewShowCanvas extends Canvas
{
    public PreviewShowCanvas(Composite parent, int style)
    {
        super(parent, style);
        init();
    }
    
    private void init()
    {
        setSize(getParent().getSize());
        setBackground(SWTResourceManager.getColor(238, 222, 2));
    }
    
    public void drawCoordinate(GC gc)
    {
        if (null == gc) return ;
        
        int w = getSize().x;
        int h = getSize().y;
        int x = getSize().x / 2;
        int y = getSize().y / 2;
        
        gc.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
        gc.drawLine(x,0, x,h);
        gc.drawLine(0, y, w, y);
    }
}
