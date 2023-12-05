package dev.langz.rebirthinvincibility;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("成功启用");
        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("插件已卸载");
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // 获取重生玩家
        Player player = event.getPlayer();

        // 重生时免疫伤害
        player.setInvulnerable(true);

        //BukkitRunnable 5s后取消免疫状态
        getServer().getScheduler().runTaskLater(this, () -> {
            player.setInvulnerable(false);
        }, 100L);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // 检查事件是否是对玩家造成的伤害
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // 检查玩家是否在免疫状态
            if (player.isInvulnerable()) {
                // 取消伤害
                event.setCancelled(true);
            }
        }
    }
}
