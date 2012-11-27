package com.zhuyanbin.animationEditor.view.mainwindow;

import java.util.Vector;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class AnimationShowCanvas extends Canvas
{
    private Vector<Image> _images;
    private int index = 0;
    
    public AnimationShowCanvas(Composite parent, int style)
    {
        super(parent, style);
        init();
    }
    
    private void init()
    {
        if (null != getParent())
        {
            setSize(getParent().getSize());
        }
        
        String imageFileNames[] = {
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_01.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_02.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_03.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_04.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_05.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_06.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_07.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_08.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_09.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_10.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_11.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_12.png",
                "/Users/flavor/program/animationEditor/src/test/resources/grossini_dance_13.png",
                };
        
        _images = new Vector<Image>();
        int len = imageFileNames.length;
        for (int i = 0; i < len; i++)
        {
            _images.add(new Image(Display.getCurrent(), imageFileNames[i]));
        }
        
        addPaintListener(new PaintListener()
        {
            
            @Override
            public void paintControl(PaintEvent e)
            {
//                System.out.println("paintControl Thread id:"+Thread.currentThread().getId());
                index = index % _images.size();
                e.gc.drawImage(_images.get(index), 10, 10);
                index ++;
                //System.out.println(index);
            }
        });
    }
}
