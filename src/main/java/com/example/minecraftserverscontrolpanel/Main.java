package com.example.minecraftserverscontrolpanel;

import com.example.minecraftserverscontrolpanel.file.PathHelper;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        new File(PathHelper.INSTANCE.getBatFolderPath()).mkdir();

        PathHelper.INSTANCE.setProduction(true);
        MainApplication.main(args);
    }
}
