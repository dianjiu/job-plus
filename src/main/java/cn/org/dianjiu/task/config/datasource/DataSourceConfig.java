package cn.org.dianjiu.task.config.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * druid数据源配置类
 *
 * @author DianJiu
 * @date 2019/4/9
 */
@Configuration
public class DataSourceConfig {

    //地址
    @Value("${spring.datasource.url}")
    private String dbUrl;

    //用户名
    @Value("${spring.datasource.username}")
    private String userName;

    ///密码
    @Value("${spring.datasource.password}")
    private String password;

    //驱动名称
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    //初始化大小
    @Value("${spring.datasource.initial-size}")
    private int initialSize;

    //最小值
    @Value("${spring.datasource.min-idle}")
    private int minIdle;

    //最大值
    @Value("${spring.datasource.max-active}")
    private int maxActive;

    //配置获取连接等待超时的时间
    @Value("${spring.datasource.max-wait}")
    private int maxWait;

    //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    //配置一个连接在池中最小生存的时间，单位是毫秒
    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.test-on-return}")
    private boolean testOnReturn;

    //打开PSCache，并且指定每个连接上PSCache的大小
    @Value("${spring.datasource.pool-prepared-statements}")
    private boolean poolPreparedStatements;

    //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    @Value("${spring.datasource.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.use-global-data-source-stat}")
    private boolean useGlobalDataSourceStat;

    //通过connectProperties属性来打开mergeSql功能；慢SQL记录
    @Value("{spring.datasource.connect-properties}")
    private String connectionProperties;


    @Primary
    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);

        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        WallFilter wallFilter= new WallFilter();
        wallFilter.setConfig(wallConfig);
        List<Filter> filterList = new ArrayList<>();
        filterList.add(wallFilter);
        datasource.setProxyFilters(filterList);

        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "*");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }
}  