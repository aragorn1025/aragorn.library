package aragorn.util;

import java.util.ArrayList;

public class Utilities {

	public static <E> E getLast(ArrayList<E> list) {
		return getLast(list, 0);
	}

	public static <E> E getLast(ArrayList<E> list, int index_count_from_last) {
		return list.get(list.size() - 1 - index_count_from_last);
	}

	public static <E> E getLast(E[] array) {
		return getLast(array, 0);
	}

	public static <E> E getLast(E[] array, int index_count_from_last) {
		return array[array.length - 1 - index_count_from_last];
	}
}