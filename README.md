# Özellikler

Oyuncu Bakiyesi: Oyuncuların mevcut madeni para bakiyelerini kontrol edebilirsiniz.
Madeni Para Ekleme: Oyunculara madeni para ekleyebilirsiniz.
Madeni Para Çıkarma: Oyunculardan madeni para çıkarabilirsiniz.
Bakiye Sıfırlama: Oyuncuların madeni para bakiyelerini sıfırlayabilirsiniz.
En Çok Madeni Paraya Sahip Oyuncular: En çok madeni paraya sahip olan oyuncuları listeleme özelliği bulunmaktadır.
Kullanım
Eklentiyi kullanmak için aşağıdaki adımları takip edin:

Eklentiyi projenize veya sunucunuza yükleyin.

CoinAPI sınıfını kullanmak için gerekli olan sınıfları ve yöntemleri içe aktarın:

**import me.ozaii.coin.CoinAPI;**

**import org.bukkit.entity.Player;**

*CoinAPI coinAPI = new CoinAPI();*

Madeni para ile ilgili çeşitli işlemleri gerçekleştirin:

Oyuncunun madeni para bakiyesini kontrol etme:
**double coinBalance = coinAPI.getPlayerCoins(player.getName());**

Oyuncuya madeni para ekleme:

**coinAPI.addPlayerCoins(player.getName(), 100);**

Oyuncudan madeni para çıkarma:

**coinAPI.removePlayerCoins(player.getName(), 50);**

Oyuncunun madeni para bakiyesini sıfırlama:

**coinAPI.resetPlayerCoins(player.getName());**

En çok madeni paraya sahip olan oyuncuları listeleme:

**Map<String, Double> topPlayers = coinAPI.getTopCoinPlayers(10);**
