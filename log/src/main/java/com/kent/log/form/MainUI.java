package com.kent.log.form;

import com.kent.log.OnStartListener;
import com.kent.log.ResolveInfo;
import lombok.Setter;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainUI extends JFrame implements DropTargetListener {
    private JPanel contentPanel;
    private JPanel checkBoxPanel1;
    private JPanel checkBoxPanel2;
    private JCheckBox cbSubInParam;
    private JCheckBox cbPickInParam;
    private JCheckBox cbPickOutParam;
    private JCheckBox cbBsInParam;
    private JCheckBox cbBsOutParam;
    private JButton startBtn;
    private JPanel buttonPanel;
    private JButton fileBtn;
    private JLabel fileLabel;

    private String curFilePath;

    @Setter
    private OnStartListener onStartListener;

    public MainUI() {
        setTitle("捞日志");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(contentPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startBtn.addActionListener(e -> {
            if (curFilePath == null || curFilePath.isEmpty()) {
                return;
            }
            startResolve(Collections.singletonList(curFilePath));
        });
        fileBtn.addActionListener(e -> chooseFile());

        new DropTarget(this, this);

        contentPanel.requestFocusInWindow();
    }

    public void resolveEnd() {
        startBtn.setEnabled(true);
        startBtn.setText("开始");
    }

    private void startResolve(List<String> filePathList) {
        contentPanel.requestFocusInWindow();
        if (onStartListener != null) {
            startBtn.setEnabled(false);
            startBtn.setText("正在捞...");
            ResolveInfo resolveInfo = ResolveInfo.builder()
                    .subInParam(cbSubInParam.isSelected())
                    .pickInParam(cbPickInParam.isSelected())
                    .pickOutParam(cbPickOutParam.isSelected())
                    .bsInParam(cbBsInParam.isSelected())
                    .bsOutParam(cbBsOutParam.isSelected())
                    .build();
            onStartListener.onStart(filePathList, resolveInfo);
        }
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int retValue = fileChooser.showOpenDialog(null);
        if (retValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            curFilePath = selectedFile.getAbsolutePath();
            setFilePath(Collections.singletonList(selectedFile.getAbsolutePath()));
        }
    }

    private void setFilePath(List<String> filePathList) {
        fileLabel.setText(String.join("\r\n", filePathList));
    }

    @Override
    public void dragEnter(DropTargetDragEvent dragEvent) {
        // 拖拽进入
    }

    @Override
    public void dragOver(DropTargetDragEvent dragEvent) {
        // 处理拖拽过程，例如更改光标类型等
    }

    @SuppressWarnings("unchecked")
    @Override
    public void drop(DropTargetDropEvent dragEvent) {
        try {
            Transferable tr = dragEvent.getTransferable();
            if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dragEvent.acceptDrop(DnDConstants.ACTION_COPY); // 接受拖放动作
                List<File> fileList = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);
                dragEvent.dropComplete(true); // 完成拖放操作
                List<String> filePathList = fileList.stream().map(File::getAbsolutePath).collect(Collectors.toList());
                setFilePath(filePathList);
                startResolve(filePathList);
            } else {
                dragEvent.rejectDrop(); // 拒绝拖放操作
            }
        } catch (Exception e) {
            dragEvent.rejectDrop(); // 异常处理，拒绝拖放操作
        }
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        // 拖拽离开
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dragEvent) {
        // 处理拖放动作改变的情况，例如更改光标类型等
    }
}
