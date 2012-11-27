package com.zhuyanbin.animationEditor.view.mainwindow;

import org.eclipse.swt.widgets.Composite;

public final class AnimationShowThread extends Thread
{
    private AnimationShowCanvas _canvas;
    
    public AnimationShowThread(Composite parent, int style)
    {
        _canvas = new AnimationShowCanvas(parent, style);
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                sleep(300);
                _canvas.redraw();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
