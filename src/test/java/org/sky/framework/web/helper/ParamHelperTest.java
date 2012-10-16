package org.sky.framework.web.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.sky.framework.common.enumeration.ClassType;

public class ParamHelperTest extends TestCase{

	public void testConvert2ListString(){
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		String parameterizedType = ClassType.String.value;
		
		List<String> list = ParamHelper.convert2List(paramName, translator, parameterizedType);
		
		assertNotNull(list);
		assertTrue(list.size()==2);
		
		assertEquals("test1", list.get(0));
		assertEquals("test2", list.get(1));		
	}
	
	public void testConvert2ListInteger(){
		String paramName = "names";
		String[] paramValues = {"1","2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);

		String parameterizedType = ClassType.Integer.value;
		
		List<Integer> list = ParamHelper.convert2List(paramName, translator, parameterizedType);
		
		assertNotNull(list);
		assertTrue(list.size()==2);
		
		assertEquals(new Integer(1), list.get(0));
		assertEquals(new Integer(2), list.get(1));
	}
	
	public void testConvert2ListDouble(){
		String paramName = "names";
		String[] paramValues = {"1.1","2.2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);

		String parameterizedType = ClassType.Double.value;
		
		List<Double> list = ParamHelper.convert2List(paramName, translator, parameterizedType);
		
		assertNotNull(list);
		assertTrue(list.size()==2);
		
		assertEquals(new Double(1.1), list.get(0));
		assertEquals(new Double(2.2), list.get(1));
	}
	
	public void testConvert2ListFloat(){
		String paramName = "names";
		String[] paramValues = {"1","2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);

		String parameterizedType = ClassType.Float.value;
		
		List<Float> list = ParamHelper.convert2List(paramName, translator, parameterizedType);
		
		assertNotNull(list);
		assertTrue(list.size()==2);
		
		assertEquals(new Float(1), list.get(0));
		assertEquals(new Float(2), list.get(1));
	}	
	
	public void testConvert2ListShort(){
		String paramName = "names";
		String[] paramValues = {"1","2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);

		String parameterizedType = ClassType.Short.value;
		
		List<Short> list = ParamHelper.convert2List(paramName, translator, parameterizedType);
		
		assertNotNull(list);
		assertTrue(list.size()==2);
		
		assertEquals(new Short("1"), list.get(0));
		assertEquals(new Short("2"), list.get(1));
	}		
	
	public void testConvert2SetString(){
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		String parameterizedType = ClassType.String.value;
		
		Set<String> set = ParamHelper.convert2Set(paramName, translator, parameterizedType);
		
		assertNotNull(set);
		assertTrue(set.size()==2);
		
		assertEquals(true, set.contains("test1"));
		assertEquals(true, set.contains("test2"));
		
	}
	
	public void testConvert2Int(){
		String paramName = "names";
		String[] paramValues = {"1"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		
		int values = ParamHelper.convert2Int(paramName, translator);
		
		assertTrue(values==1);

	}	
	
	public void testConvert2IntArray(){
		String paramName = "names";
		String[] paramValues = {"1","2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		
		int[] values = ParamHelper.convert2IntArray(paramName, translator);
		
		assertNotNull(values);
		assertTrue(values.length==2);
		
		assertEquals(1, values[0]);
		assertEquals(2, values[1]);
		
	}
	
	public void testConvert2Integer(){
		String paramName = "names";
		String[] paramValues = {"1"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		
		Integer value = ParamHelper.convert2Integer(paramName, translator);
		
		assertEquals(new Integer(1), value);

	}	
	
	public void testConvert2IntegerArray(){
		String paramName = "names";
		String[] paramValues = {"1","2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		
		Integer[] values = ParamHelper.convert2IntegerArray(paramName, translator);
		
		assertNotNull(values);
		assertTrue(values.length==2);
		
		assertEquals(new Integer(1), values[0]);
		assertEquals(new Integer(2), values[1]);
		
	}	
	
}
