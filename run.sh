#!/bin/sh
APP_PATH=target/animationEditor-1.0.0.jar
java -Djava.library.path=plugins  \
	-cp plugins/org.eclipse.core.commands-3.6.1.v20120521-2329.jar:plugins/org.eclipse.equinox.common-3.6.100.v20120522-1841.jar:plugins/org.eclipse.jface-3.8.0.v20120521-2329.jar:plugins/org.eclipse.osgi-3.8.0.v20120529-1548.jar:plugins/org.eclipse.swt.cocoa.macosx.x86_64-3.8.jar:plugins/org.eclipse.ui.workbench-3.103.0.v20120530-1824.jar -XstartOnFirstThread -jar $APP_PATH \
	com.zhuyanbin.animationEditor.App -XstartOnFirstThread
