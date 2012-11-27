package com.zhuyanbin.animationEditor.view.mainwindow;

import java.util.Vector;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.zhuyanbin.animationEditor.model.ImageVO;

public class AnimationShowCanvas extends Canvas
{
    private static int DELAY = 200;
    private Vector<Image> _images;
    private int index = 0;
    private Vector<ImageVO> _imageVOs;
    private AnimationPaintListener _animationPaintListener;
    private int _delay = DELAY; 
    
    public AnimationShowCanvas(Composite parent, int style)
    {
        super(parent, style);
        init();
        (new AnimationRunnable()).run();
    }
    /*
    private void setDelay(int delay)
    {
        _delay = delay;
    }
    */
    private int getDelay()
    {
        return _delay;
    }
    
    public void addImageVO(ImageVO ivo)
    {
        _imageVOs.add(ivo);
        _images.add(new Image(getDisplay(), ivo.getFilePath()));
    }
    
    public void clean()
    {
        _imageVOs.clear();
        _images.clear();
    }
    
    private void init()
    {
        if (null != getParent())
        {
            setSize(getParent().getSize());
        }
        
        _imageVOs = new Vector<ImageVO>();
        _images = new Vector<Image>();
        
        addEvents();
    }
    
    private void addEvents()
    {
        _animationPaintListener = new AnimationPaintListener();
        addPaintListener(_animationPaintListener);
    }
    
    private void removeEvents()
    {
        if (null != _animationPaintListener)
        {
            removePaintListener(_animationPaintListener);
            _animationPaintListener = null;
        }
    }

    @Override
    public void dispose ()
    {
        removeEvents();
        super.dispose();
    }
    
    class AnimationPaintListener implements PaintListener
    {
        @Override
        public void paintControl(PaintEvent e)
        {
            if (_images.size() > 0)
            {
                index = index % _images.size();
                e.gc.drawImage(_images.get(index), 10, 10);
                index ++;
            }
        }
    }
    
    class AnimationRunnable implements Runnable
    {
        public void run()
        {
            redraw();
            getDisplay().timerExec(getDelay(), this);
        }
    }
}
