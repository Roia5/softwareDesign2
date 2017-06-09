package il.ac.technion.cs.sd.buy.app;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;


/** This class will only be instantiated by Guice after one of the setup methods has been called. */
public interface BuyProductReader {
  /** Returns true iff the given ID is that of a valid (possibly canceled) order. */
  CompletableFuture<Boolean> isValidOrderId(String orderId);
  /** Return true iff the given ID is that of a valid and canceled order. */
  CompletableFuture<Boolean> isCanceledOrder(String orderId);
  /** Return true iff the given ID is that of a valid that was modified. */
  CompletableFuture<Boolean> isModifiedOrder(String orderId);

  /**
   * Returns the number of products that were ordered with the given order ID. If the order was modified, returns the
   * current number. If the order was cancelled, returns the <b>negation</b> of the last number. If the order ID is not
   * found, returns empty.
   */
  CompletableFuture<OptionalInt> getNumberOfProductOrdered(String orderId);
  /**
   * Returns the history of products ordered with the given order ID, from first to last. If the order was cancelled,
   * appends -1 to the list. If the order ID is invalid, returns an empty list.
   */
  CompletableFuture<List<Integer>> getHistoryOfOrder(String orderId);

  /**
   * Returns the order IDs of all orders made by the given user (including cancelled orders), lexicographically ordered.
   * If the user is not found, returns an empty list.
   */
  CompletableFuture<List<String>> getOrderIdsForUser(String userId);
  /**
   * Returns the total amount of money spent by the user, i.e., the cost of each product times were purchased.
   * If the user is not found, return 0. Canceled orders are not included in this sum.
   */
  CompletableFuture<Long> getTotalAmountSpentByUser(String userId);

  /**
   * Returns the list of user IDs that purchased this product. If the product ID isn't found, return an empty list.
   * Users who only made a purchase that was later canceled do not appear in this list.
   */
  CompletableFuture<List<String>> getUsersThatPurchased(String productId);
  /**
   * Returns a list of order IDs that purchased this product, including canceled. If the product is not found, returns
   * an empty list.
   */
  CompletableFuture<List<String>> getOrderIdsThatPurchased(String productId);
  /**
   * Returns the total count of purchased items of the given product ID. Canceled orders do not contribute to this
   * sum. If the product ID is not found, returns empty.
   */
  CompletableFuture<OptionalLong> getTotalNumberOfItemsPurchased(String productId);

  /**
   * Returns the average number of purchased items of the give product ID. Canceled orders do not contribute to this
   * sum. If the product ID is not found, returns empty.
   */
  CompletableFuture<OptionalDouble> getAverageNumberOfItemsPurchased(String productId);

  /**
   * Returns the ratio of canceled orders, e.g., if the user made a total of 10 orders and 6 of them were canceled,
   * return 0.6. If the user ID is not found, returns empty.
   */
  CompletableFuture<OptionalDouble> getCancelRatioForUser(String userId);
  /**
   * Returns the ratio of modified orders, e.g., if the user made a total of 10 orders and 6 of them were modified,
   * return 0.6. Modified orders that were later canceled are included. If the user ID is not found, returns
   * empty.
   */
  CompletableFuture<OptionalDouble> getModifyRatioForUser(String userId);

  /**
   * Returns a map from from product IDs to the total number of items that were purchased, across all orders. Canceled
   * orders are not included in this total. If the user ID is not found, returns an empty map.
   */
  CompletableFuture<Map<String, Long>> getAllItemsPurchased(String userId);
  /**
   * Returns a map from from user IDs to the total number of items that the user purchased. Canceled orders are not
   * included in this total. If the product ID is not found, returns an empty map.
   */
  CompletableFuture<Map<String, Long>> getItemsPurchasedByUsers(String productId);
}
