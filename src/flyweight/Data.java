package flyweight;
import java.util.WeakHashMap;

public class Data {
  private Data() {}
  private static WeakHashMap<Integer,Video> _hashmap = new WeakHashMap<Integer,Video>();

  private static int hashcoshing (Object key1, Object key2, Object key3) {
    return key1.hashCode () + 5 * key2.hashCode () + 13 * key3.hashCode ();	
  }

  /**
   * Creates and manages flyweight objects. Ensures that flyweights
   * are shared properly.  When a client requests a flyweight, the
   * Flyweight Factory object supplies an existing instance or
   * creates one, if none exists.
   */
  static public Video getVideo(String title, int year, String director) {
    if (  (title == null)
       || (director == null)
       || (year <= 1800)
       || (year >= 5000)) {
      throw new IllegalArgumentException();
    }
    title = title.trim();
    director = director.trim();
    if (  ("".equals(title))
       || ("".equals(director))) {
      throw new IllegalArgumentException();
    }
    Integer key = hashcoshing(title, year, director);
    Video v = (Video) _hashmap.get(key);
    if (   (v == null)
        || !(v.title().equals(title))
        || (v.year() != year)
        || !(v.title().equals(title))) {
      v = new VideoObj(title, year, director);
      _hashmap.put(key, v);
    }
    return v;
  }
}  

