package com.zhuyanbin.animationEditor.model;

import org.eclipse.swt.graphics.Point;

public final class ImageVO
{
    private FrameVO _frameVO;
    private Point _offset;
    private boolean _rotated;
    private Point _sourceSize;
    
    public ImageVO()
    {
    }

    public FrameVO getFrameVO()
    {
        return _frameVO;
    }

    public void setFrameVO(FrameVO frameVO)
    {
        _frameVO = frameVO;
    }

    public Point getOffset()
    {
        return _offset;
    }

    public void setOffset(Point offset)
    {
        _offset = offset;
    }

    public boolean isRotated()
    {
        return _rotated;
    }

    public void setRotated(boolean rotated)
    {
        _rotated = rotated;
    }

    public Point getSourceSize()
    {
        return _sourceSize;
    }

    public void setSourceSize(Point sourceSize)
    {
        _sourceSize = sourceSize;
    }
}
