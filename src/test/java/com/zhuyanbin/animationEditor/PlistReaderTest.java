package com.zhuyanbin.animationEditor;

import java.io.File;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;

public class PlistReaderTest
{
    
    public PlistReaderTest()
    {
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            File file = new File("src/test/resources/grossini_dance.plist");
            NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(file);
            String keys[] = rootDict.allKeys();
            
            for (String str : keys)
            {
                System.out.println("key:"+str);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
