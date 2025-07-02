package org.example.aws.services.impl;

import org.example.aws.services.RdsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Service
public class PostgresqlRdsServiceImpl implements RdsService {

    private final DataSource dataSource;

    public PostgresqlRdsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}


