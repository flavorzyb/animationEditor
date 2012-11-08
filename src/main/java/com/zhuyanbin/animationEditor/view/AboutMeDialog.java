package com.zhuyanbin.animationEditor.view;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import com.zhuyanbin.animationEditor.Version;

public class AboutMeDialog extends Dialog
{
    
    protected Object result;
    protected Shell  shell;
    
    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public AboutMeDialog(Shell parent, int style)
    {
        super(parent, style);
        setText("关于AnimationEditor");
    }
    
    /**
     * Open the dialog.
     * @return the result
     */
    public Object open()
    {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
        return result;
    }
    
    /**
     * Create contents of the dialog.
     */
    private void createContents()
    {
        shell = new Shell(getParent(), getStyle());
        shell.setSize(300, 200);
        shell.setText(getText());
        
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setBounds(82, 25, 129, 14);
        lblNewLabel.setText("作者:"+Version.Author);
        
        Label lblNewLabel_1 = new Label(shell, SWT.NONE);
        lblNewLabel_1.setBounds(82, 56, 97, 14);
        lblNewLabel_1.setText("版本:"+Version.Version);
        
        Label label = new Label(shell, SWT.NONE);
        label.setBounds(82, 91, 129, 14);
        label.setText("更新时间:"+Version.Date);
        
        Button button = new Button(shell, SWT.NONE);
        button.setBounds(115, 125, 84, 28);
        button.setText("关闭");
    }
}
