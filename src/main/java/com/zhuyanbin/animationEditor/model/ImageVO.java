package com.zhuyanbin.animationEditor.model;

import org.eclipse.swt.graphics.Point;

public final class ImageVO
{
    private String _filePath;
    private FrameVO _frameVO;
    private Point _offset;
    private boolean _rotated;
    private Point _sourceSize;
    
    public ImageVO(String path)
    {
        setFilePath(path);
    }

    private void setFilePath(String path)
    {
        _filePath = path;
    }
    
    public String getFilePath()
    {
        return _filePath;
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
