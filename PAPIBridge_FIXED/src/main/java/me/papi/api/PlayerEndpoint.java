package me.papi.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import me.papi.cache.PlaceholderCache;
import org.bukkit.*;

import java.io.OutputStream;
import java.util.UUID;

public class PlayerEndpoint implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) {
        try {
            String name = ex.getRequestURI().getPath().split("/")[3];
            OfflinePlayer p = Bukkit.getOfflinePlayer(name);
            UUID uuid = p.getUniqueId();

            String money = PlaceholderCache.get(uuid + ":money", () ->
                PlaceholderAPI.setPlaceholders(p, "%vault_eco_balance%")
            );

            String json = String.format("{\"name\":\"%s\",\"money\":\"%s\"}", name, money);

            ex.sendResponseHeaders(200, json.getBytes().length);
            OutputStream os = ex.getResponseBody();
            os.write(json.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
