package spring.common.datasource;

public class MultiDataSourceTypeManager {

    private static final ThreadLocal<MultiDataSourceTypeEnum> dataSourceTypeEnum = new ThreadLocal<MultiDataSourceTypeEnum>(){
        @Override
        protected MultiDataSourceTypeEnum initialValue(){
            return MultiDataSourceTypeEnum.BUSINESS;
        }
    };

    public static String get(){
        return  MultiDataSourceTypeEnum.getValue(dataSourceTypeEnum.get());
    }

    public static void set(MultiDataSourceTypeEnum dataSourceType){
        dataSourceTypeEnum.set(dataSourceType);
    }

}