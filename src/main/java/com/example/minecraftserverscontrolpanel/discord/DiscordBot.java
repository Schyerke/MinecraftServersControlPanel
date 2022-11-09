package com.example.minecraftserverscontrolpanel.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public enum DiscordBot {
    INSTANCE;

    private boolean isReady = false;

    public void initDiscordBot(){
        if(isReady){
            return;
        }
        JDA jda = JDABuilder.createDefault("MTAzNjkwMjY5Mjc5MjA2MjA0Mg.G391qA.4nSkP7Hbin68yRKuL8WrwAevkv2ZXmqwWLvNHI")
                .setActivity(Activity.playing("Krisztina Tóth"))
                .build();


        


        this.isReady = true;
    }


    public boolean isReady() {
        return isReady;
    }
}
