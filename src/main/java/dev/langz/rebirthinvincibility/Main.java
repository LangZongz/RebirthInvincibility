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
        getLogger().info("�ɹ�����");
        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("�����ж��");
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // ��ȡ�������
        Player player = event.getPlayer();

        // ����ʱ�����˺�
        player.setInvulnerable(true);

        //BukkitRunnable 5s��ȡ������״̬
        getServer().getScheduler().runTaskLater(this, () -> {
            player.setInvulnerable(false);
        }, 100L);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // ����¼��Ƿ��Ƕ������ɵ��˺�
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // �������Ƿ�������״̬
            if (player.isInvulnerable()) {
                // ȡ���˺�
                event.setCancelled(true);
            }
        }
    }
}
