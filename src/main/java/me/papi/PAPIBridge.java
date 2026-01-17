package me.papi;

import me.papi.api.ApiServer;
import org.bukkit.plugin.java.JavaPlugin;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;

public class PAPIBridge extends JavaPlugin {

    @Override
    public void onEnable() {
        GlobalRegionScheduler.get().execute(this, () -> {
            ApiServer.start(8080);
            getLogger().info("PAPI Bridge API started on 8080");
        });
    }
} 
