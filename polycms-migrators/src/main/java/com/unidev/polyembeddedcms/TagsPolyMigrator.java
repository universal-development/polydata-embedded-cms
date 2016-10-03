package com.unidev.polyembeddedcms;

import com.unidev.polydata.SQLitePolyMigrator;
import com.unidev.polydata.SQLiteStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TagsPolyMigrator implements SQLitePolyMigrator {
    private static Logger LOG = LoggerFactory.getLogger(TagsPolyMigrator.class);
    @Override
    public boolean canHandle(String poly) {
        return PolyConstants.TAGS_POLY.equalsIgnoreCase(poly);
    }

    @Override
    public void handle(String poly, Connection connection) throws SQLiteStorageException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+poly+" (_id VARCHAR(255) PRIMARY KEY, label VARCHAR(255), count INTEGER,  data JSON)");
        } catch (SQLException e) {
            LOG.warn("Failed to run tags migration ", e);
            throw new SQLiteStorageException(e);
        }
    }
}
