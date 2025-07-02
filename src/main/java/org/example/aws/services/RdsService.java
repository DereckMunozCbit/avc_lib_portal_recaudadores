package org.example.aws.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface RdsService {
    Connection getConnection() throws SQLException;
}
