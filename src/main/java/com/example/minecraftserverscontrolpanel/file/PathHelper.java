package com.example.minecraftserverscontrolpanel.file;

public enum PathHelper {
    INSTANCE;


    private boolean production = false;





    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public String getBatFilePath(String serverName) {
        return isProduction() ? System.getProperty("user.dir") + "\\" + "\\bat\\" + serverName + ".bat" : ".\\bat\\" + serverName + ".bat";
    }

    public String getBatFolderPath() {
        return isProduction() ? System.getProperty("user.dir") + "\\" + "\\bat" : ".\\bat";
    }
}
