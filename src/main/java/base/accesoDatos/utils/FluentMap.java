package base.accesoDatos.utils;

import java.util.Map;
import java.util.HashMap;

public class FluentMap {

    @SafeVarargs
	public static Map<String, Object> Map( Tuple<String, Object>... entries) {
        Map<String, Object> map = new HashMap<String, Object>();

        for (Tuple<String, Object> entry : entries) {
            map.put(entry.t1, entry.t2);
        }
        return map;
    }


	public static Tuple<String, Object> entry(String o1, Object o2) {
        return new Tuple<String, Object>(o1, o2);
    }

    @SuppressWarnings("hiding")
	public static class Tuple<String, Object> {
        private String t1;
        private Object t2;

        public Tuple(String t1, Object t2) {
            this.t1 = t1;
            this.t2 = t2;
        }
    }
}