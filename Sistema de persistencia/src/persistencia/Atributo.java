package persistencia;

public class Atributo {

	private String name;
	private Class<?> type;
	private String value;

	public Atributo(String name, Class<?> type, String value)
	{
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {

		Object respond = null;

		if (this.type == String.class)
		{
			respond = this.value;
		}
		else if (this.type == int.class)
		{
			respond = Integer.parseInt(this.value);
		}

		return respond;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
