package com.unidev.polyembeddedcms;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unidev.polydata.SQLiteStorage;
import com.unidev.polydata.SQLiteStorageException;
import com.unidev.polydata.domain.BasicPoly;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * SQLite poly records generation tests
 */
public class PolySQLiteRecordsTest {

    private PolyCore polyCore;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() {
        polyCore = new PolyCore();
        polyCore.setStorageRoot(temporaryFolder.getRoot());
    }

    @Test
    public void testContentGeneration() throws SQLiteStorageException, SQLException, JsonProcessingException {
        String tenant = "testTenant";

        polyCore.createTenantStorage(tenant);

        File root = polyCore.fetchStorageRoot(tenant);

        File db = new File(root, "poly.db");

        SQLiteStorage sqLiteStorage = new SQLiteStorage(db.getPath());
        sqLiteStorage.setPolyMigrators(JSONColumnPolyMigrator.sqLitePolyMigratorList());


        HashMap<String, Object> data = new HashMap<>();
        data.put("url", "http://google.com");


        ObjectMapper objectMapper = new ObjectMapper();
        String strData = objectMapper.writeValueAsString(data);


        PolyRecord polyRecord = new PolyRecord()
                ._id("tomato").label("Tomato").data(strData);

        sqLiteStorage.save("tomato", polyRecord);

        Connection connection = sqLiteStorage.openDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tomato;");

        List<BasicPoly> polyList = sqLiteStorage.evaluateStatement(preparedStatement);

        assertThat(polyList, is(notNullValue()));
        assertThat(polyList.size(), is(1));

        BasicPoly basicPoly = polyList.get(0);
        assertThat(basicPoly, is(not(nullValue())));
        assertThat(basicPoly._id(), is("tomato"));
        assertThat(basicPoly.get("label"), is("Tomato"));
    }

}
