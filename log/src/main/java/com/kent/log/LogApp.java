package com.kent.log;

import com.kent.log.form.MainUI;

import javax.swing.*;

public class LogApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
            mainUI.setOnStartListener((filePathList, resolveInfo) -> {
                System.out.println("开始解析日志文件");
                new LogResolver().resolve(filePathList, resolveInfo);
            });
        });
    }
}
