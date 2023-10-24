package org.glebchanskiy.util;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceBuilder {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public DataSourceBuilder setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public DataSourceBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public DataSourceBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public DataSourceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public DataSource build() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
