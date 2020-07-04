package spring.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author : JieWang
 * @Date : Created in 2020年06月04日14:39
 * @Email : wjahstu@163.com
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceTypeManager.get();
    }
}
