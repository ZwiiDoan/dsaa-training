import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SAPCache {
  private final Map<String, Integer> lengthCache;
  private final Map<String, Integer> ancestorCache;

  public SAPCache() {
    lengthCache = new HashMap<>();
    ancestorCache = new HashMap<>();
  }

  private String getCacheKeyPart(Iterable<Integer> v) {
    return StreamSupport.stream(v.spliterator(), false)
        .map(String::valueOf)
        .collect(Collectors.joining("-")); // Joining with no delimiter
  }

  public String getCacheKey(Iterable<Integer> v, Iterable<Integer> w) {
    return getCacheKeyPart(v) + "|" + getCacheKeyPart(w);
  }

  public Integer getAncestor(Iterable<Integer> v, Iterable<Integer> w) {
    String cacheKey = getCacheKey(v, w);
    return ancestorCache.get(cacheKey);
  }

  public void putAncestor(Iterable<Integer> v, Iterable<Integer> w, Integer ancestor) {
    String cacheKey1 = getCacheKey(v, w);
    String cacheKey2 = getCacheKey(w, v);
    ancestorCache.put(cacheKey1, ancestor);
    ancestorCache.put(cacheKey2, ancestor);
  }

  public Integer getAncestor(int v, int w) {
    return getAncestor(Collections.singletonList(v), Collections.singletonList(w));
  }

  public void putAncestor(int v, int w, int ancestor) {
    String cacheKey1 = getCacheKey(Collections.singletonList(v), Collections.singletonList(w));
    String cacheKey2 = getCacheKey(Collections.singletonList(w), Collections.singletonList(v));
    ancestorCache.put(cacheKey1, ancestor);
    ancestorCache.put(cacheKey2, ancestor);
  }

  public Integer getLength(Iterable<Integer> v, Iterable<Integer> w) {
    String cacheKey = getCacheKey(v, w);
    return lengthCache.get(cacheKey);
  }

  public void putLength(Iterable<Integer> v, Iterable<Integer> w, Integer length) {
    String cacheKey1 = getCacheKey(v, w);
    String cacheKey2 = getCacheKey(w, v);
    lengthCache.put(cacheKey1, length);
    lengthCache.put(cacheKey2, length);
  }

  public Integer getLength(int v, int w) {
    return getLength(Collections.singletonList(v), Collections.singletonList(w));
  }

  public void putLength(int v, int w, int length) {
    String cacheKey1 = getCacheKey(Collections.singletonList(v), Collections.singletonList(w));
    String cacheKey2 = getCacheKey(Collections.singletonList(w), Collections.singletonList(v));
    lengthCache.put(cacheKey1, length);
    lengthCache.put(cacheKey2, length);
  }
}
