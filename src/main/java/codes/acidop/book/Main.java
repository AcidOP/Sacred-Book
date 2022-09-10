package codes.acidop.book;

import codes.acidop.book.Magic.Book;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Book(), this);

    }
}
