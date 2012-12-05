package org.sky.framework.dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.sky.framework.dao.data.PersonTest;
import org.sky.framework.dao.dto.Col;
import org.sky.framework.dao.dto.DBMapping;
import org.sky.framework.dao.dto.PrimaryKey;
import org.sky.framework.dao.dto.RefDB;
import org.sky.framework.dao.utils.DBHelper;

public class DBHelperTest {

	@Test
	public void testCombinationDBMapping() throws Exception{
		DBMapping mapping = DBHelper.combinationDBMapping(PersonTest.class);
		
		Assert.assertEquals("person", mapping.getTableName());
		
		Map<String,PrimaryKey> primaryKeys = mapping.getPrimaryKeys();
		Assert.assertEquals(1, primaryKeys.size());
		Assert.assertEquals("id", ((PrimaryKey) primaryKeys.get("id")).getName());
		
		Map<String,Col> columns = mapping.getColumns();
		Assert.assertEquals("name", columns.get("name").getName());
		Assert.assertEquals("id", columns.get("id").getName());
		Assert.assertEquals("add", columns.get("address").getName());
		
		RefDB refdb = columns.get("edu").getRefDB();
		Assert.assertEquals("edu", columns.get("edu").getName());
		Assert.assertEquals("oneToMany", refdb.getRefName());
		Assert.assertEquals("cur.status!=0 and ref.status!=0", refdb.getOtherConditions());
		Assert.assertEquals("id", refdb.getRefKey()[0]);
		Assert.assertEquals("name", refdb.getRefKey()[1]);
		Assert.assertEquals("personId", refdb.getTargetKey()[0]);
		Assert.assertEquals("personName", refdb.getTargetKey()[1]);
		
	}
}
