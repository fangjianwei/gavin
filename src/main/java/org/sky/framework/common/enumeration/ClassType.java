package org.sky.framework.common.enumeration;

public enum ClassType {
	
	String("java.lang.String"),
	StringArray("[Ljava.lang.String;"),
	
	ShortBase("short"),
	ShortBaseArray("[S"),
	
	Short("java.lang.Short"),
	ShortArray("[Ljava.lang.Short;"),
	
	IntBase("int"),
	IntBaseArray("[I"),
	
	Integer("java.lang.Integer"),
	IntegerArray("[Ljava.lang.Integer;"),
	
	LongBase("long"),
	LongBaseArray("[L"),
	
	Long("java.lang.Long"),
	LongArray("[Ljava.lang.Long;"),
	
	FloatBase("float"),
	FloatBaseArray("[F"),
	
	Float("java.lang.Float"),
	FloatArray("[Ljava.lang.Float;"),
	
	DoubleBase("double"),
	DoubleBaseArray("[D"),
	
	Double("java.lang.Double"),
	DoubleArray("[Ljava.lang.Double;"),
	
	List("java.util.List"),
	
	Map("java.lang.Map"),
	
	Set("java.util.Set"), 
	
	ByteBase("byte"),
	ByteBaseArray("[B"),
	
	Byte("java.lang.Byte"),
	ByteArray("[Ljava.lang.Byte;"),
	
	BooleanBase("boolean"),
	BooleanBaseArray("[Z"),
	
	Boolean("java.lang.Boolean"),
	BooleanArray("[Ljava.lang.Boolean;"),
	
	CharBase("char"),
	CharBaseArray("[C"),
	
	CharacterArray("[Ljava.lang.Character;")
	;
	
	public final String value;
	
	private ClassType( String value ){
		this.value = value;
	}
	
}
