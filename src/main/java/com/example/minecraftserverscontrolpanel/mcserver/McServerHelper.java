package com.example.minecraftserverscontrolpanel.mcserver;

import com.example.minecraftserverscontrolpanel.file.CsvServerRecord;
import com.example.minecraftserverscontrolpanel.file.FileHelper;

import java.io.IOException;

public enum McServerHelper {
    INSTANCE;

    public void startServer(CsvServerRecord serverRecord) {
        if (serverRecord == null) {
            return;
        }
        FileHelper.INSTANCE.createBatFile(serverRecord);
        try {
            String path = System.getProperty("user.dir") + "\\" + "\\bat\\" + serverRecord.serverName() + ".bat";
            Runtime.getRuntime().exec("cmd /c start \"\" " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
