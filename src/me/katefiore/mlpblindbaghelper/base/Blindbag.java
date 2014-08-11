package me.katefiore.mlpblindbaghelper.base;

/**
 * Класс блайнбэга.
 *
 * @author cab404
 */
public class Blindbag implements Comparable<Blindbag> {

	public final int wave;
	public final String name;
	public final String id;
	public final int wave_color;

	public Blindbag(int wave, String id, String name, int wave_color) {
		this.wave = wave;
		this.id = id;
		this.name = name;
		this.wave_color = wave_color;
	}

	@Override public String toString() {
		return "{ 'wave'='" + wave + "', 'id'='" + id + "', 'name'='" + name + "'}";
	}

	public boolean matchesPattern(String pattern) {

		return (pattern.equalsIgnoreCase("wave:" + wave) ||                 /* Выборка по волнам */
				StringUtils.fast_match(pattern, id) ||                      /* Поиск по номеру */
				name.toLowerCase().contains(pattern.toLowerCase()));        /* Поиск по имени */
	}

	/**
	 * Тут у нас настройки сортировки
	 */
	@Override public int compareTo(Blindbag another) {
		int wave_c = wave - another.wave;           /* От большего к меньшему */
		int name_c = -another.name.compareTo(name); /* В алфавитном порядке */
		int id_c = another.id.compareTo(id);

		return wave_c == 0 ? name_c == 0 ? id_c == 0 ? 0 : id_c : name_c : wave_c;
	}
}
