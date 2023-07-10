package me.ozaii.coin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinAPI extends JavaPlugin implements CommandExecutor {
  private Connection connection;
  
  public void onEnable() {
    saveDefaultConfig();
    setupDatabase();
    getCommand("coin").setExecutor(this);
    getCommand("TopCoin").setExecutor(new TopCoin(this));
    getLogger().info("CoinAPI enabled.");
    if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
      (new CoinPlaceholderExpansion(this)).register(); 
  }
  
  private class CoinPlaceholderExpansion extends PlaceholderExpansion {
    private final CoinAPI plugin;
    
    public CoinPlaceholderExpansion(CoinAPI plugin) {
      this.plugin = plugin;
    }
    
    public String getIdentifier() {
      return "coinapi";
    }
    
    public String getAuthor() {
      return "ozaii";
    }
    
    public String getVersion() {
      return this.plugin.getDescription().getVersion();
    }
    
    public String onPlaceholderRequest(Player player, String identifier) {
      if (player == null)
        return ""; 
      if (identifier.equals("coin_amount")) {
        double balance = CoinAPI.this.getPlayerCoins(player.getName());
        return String.valueOf(balance);
      } 
      return null;
    }
  }
  
  public void onDisable() {
    closeDatabase();
    getLogger().info("CoinAPI disabled.");
  }
  
  private void setupDatabase() {
    String host = getConfig().getString("mysql.host");
    int port = getConfig().getInt("mysql.port");
    String database = getConfig().getString("mysql.database");
    String username = getConfig().getString("mysql.username");
    String password = getConfig().getString("mysql.password");
    String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    try {
      this.connection = DriverManager.getConnection(url, username, password);
      createTable();
    } catch (SQLException e) {
      getLogger().severe("Failed to connect to MySQL database: " + e.getMessage());
      getServer().getPluginManager().disablePlugin((Plugin)this);
    } 
  }
  
  private void closeDatabase() {
    try {
      if (this.connection != null && !this.connection.isClosed())
        this.connection.close(); 
    } catch (SQLException e) {
      getLogger().severe("Failed to close MySQL database connection: " + e.getMessage());
    } 
  }
  
  private void createTable() {
    String query = "CREATE TABLE IF NOT EXISTS player_coins (player_name VARCHAR(255), coin_amount DOUBLE, PRIMARY KEY (player_name))";
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to create player_coins table: " + e.getMessage());
      getServer().getPluginManager().disablePlugin((Plugin)this);
    } 
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be used by players!");
      return true;
    } 
    Player player = (Player)sender;
    String playerName = player.getName();
    if (command.getName().equalsIgnoreCase("coin")) {
      double balance;
      String targetPlayer;
      double addAmount;
      String targetPlayer2;
      double removeAmount;
      String targetPlayer3;
      double setAmount;
      if (args.length < 1) {
        player.sendMessage("Invalid command! Correct usage: /coin <action> [player] [amount]");
        return true;
      } 
      String action = args[0];
      String str1;
      switch ((str1 = action.toLowerCase()).hashCode()) {
        case -934610812:
          if (!str1.equals("remove"))
            break; 
          if (args.length < 3) {
            player.sendMessage(
                ChatColor.RED + "HatalKullanDoKullan/coin remove <player> <amount>");
            return true;
          } 
          targetPlayer2 = args[1];
          try {
            removeAmount = Double.parseDouble(args[2]);
          } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "GeCoin De);
            return true;
          } 
          removePlayerCoins(targetPlayer2, removeAmount);
          player.sendMessage(
              ChatColor.RED + removeAmount + " coin kald" + targetPlayer2 + "'nin Hesab);
          return true;
        case 96417:
          if (!str1.equals("add"))
            break; 
          if (args.length < 3) {
            player.sendMessage(ChatColor.RED + "HatalKullanDoKullan/coin add <player> <amount>");
            return true;
          } 
          targetPlayer = args[1];
          try {
            addAmount = Double.parseDouble(args[2]);
          } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "GeBir Coin De);
            return true;
          } 
          addPlayerCoins(targetPlayer, addAmount);
          player.sendMessage(ChatColor.GREEN + addAmount + " coin eklendi " + targetPlayer + "'nin Hesab);
          return true;
        case 113762:
          if (!str1.equals("set"))
            break; 
          if (args.length < 3) {
            player.sendMessage(ChatColor.RED + "HatalKullanDoKullan/coin set <player> <amount>");
            return true;
          } 
          targetPlayer3 = args[1];
          try {
            setAmount = Double.parseDouble(args[2]);
          } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "GeBir Coin De);
            return true;
          } 
          setPlayerCoins(targetPlayer3, setAmount);
          player.sendMessage(ChatColor.GREEN + targetPlayer3 + "'nin coin DeAyarland" + setAmount);
          return true;
        case 94627080:
          if (!str1.equals("check"))
            break; 
          balance = getPlayerCoins(playerName);
          player.sendMessage(ChatColor.GOLD + "Coin Miktar" + balance);
          return true;
        case 108404047:
          if (!str1.equals("reset"))
            break; 
          resetPlayerCoins(playerName);
          player.sendMessage(ChatColor.RED + "Bakiyen Resetlendi.");
          return true;
      } 
      player.sendMessage(ChatColor.RED + "HatalKullanSunlar mevcut: check, reset, add, remove, set");
    } 
    return true;
  }
  
  public double getPlayerCoins(String playerName) {
    String query = "SELECT coin_amount FROM player_coins WHERE player_name=?";
    try {
      Exception exception1 = null, exception2 = null;
      try {
      
      } finally {
        exception2 = null;
        if (exception1 == null) {
          exception1 = exception2;
        } else if (exception1 != exception2) {
          exception1.addSuppressed(exception2);
        } 
      } 
    } catch (SQLException e) {
      getLogger().severe("Failed to retrieve coin amount: " + e.getMessage());
    } 
    return 0.0D;
  }
  
  public void addPlayerCoins(String playerName, double amount) {
    double currentBalance = getPlayerCoins(playerName);
    double newBalance = currentBalance + amount;
    String query = "INSERT INTO player_coins (player_name, coin_amount) VALUES (?, ?) ON DUPLICATE KEY UPDATE coin_amount=coin_amount+?";
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to add coins: " + e.getMessage());
    } 
    Player player = getServer().getPlayer(playerName);
    if (player != null)
      player.sendMessage(ChatColor.GREEN + amount + " coins eklendi Yeni Bakiyen: " + newBalance); 
  }
  
  public void removePlayerCoins(String playerName, double amount) {
    double currentBalance = getPlayerCoins(playerName);
    if (currentBalance < amount)
      amount = currentBalance; 
    double newBalance = currentBalance - amount;
    String query = "UPDATE player_coins SET coin_amount=? WHERE player_name=?";
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to remove coins: " + e.getMessage());
    } 
    Player player = getServer().getPlayer(playerName);
    if (player != null)
      player.sendMessage(ChatColor.RED + amount + " coins alYeni Bakiyen: " + newBalance); 
  }
  
  public void setPlayerCoins(String playerName, double amount) {
    String query = "INSERT INTO player_coins (player_name, coin_amount) VALUES (?, ?) ON DUPLICATE KEY UPDATE coin_amount=?";
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to set coins: " + e.getMessage());
    } 
    Player player = getServer().getPlayer(playerName);
    if (player != null)
      player.sendMessage("Your coin balance has been set to " + amount); 
  }
  
  Map<String, Double> getTopCoinPlayers(int limit) {
    String query = "SELECT player_name, coin_amount FROM player_coins ORDER BY coin_amount DESC LIMIT ?";
    Map<String, Double> topPlayers = new LinkedHashMap<>();
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to retrieve top coin players: " + e.getMessage());
    } 
    return topPlayers;
  }
  
  public void resetPlayerCoins(String playerName) {
    String query = "DELETE FROM player_coins WHERE player_name=?";
    try {
      Exception exception2, exception1 = null;
    } catch (SQLException e) {
      getLogger().severe("Failed to reset coin balance: " + e.getMessage());
    } 
  }
}
