package il.ac.technion.cs.sd.buy.app;

import java.util.concurrent.CompletableFuture;

public interface BuyProductInitializer {
  /** Saves the XML data persistently, so that it could be run using BuyProductReader. */
  CompletableFuture<Void> setupXml(String xmlData);
  /** Saves the JSON data persistently, so that it could be run using BuyProductReader. */
  CompletableFuture<Void> setupJson(String jsonData);
}
