package spring.common.datasource;

public enum MultiDataSourceTypeEnum {

    BUSINESS("business"),
    BASE("base") ;

    private String name;

    MultiDataSourceTypeEnum(String name) {
        this.name = name;
    }

        public String getName() {
            return this.name;
        }

    public static String  getValue(MultiDataSourceTypeEnum multiDataSourceTypeEnum) {
        return  multiDataSourceTypeEnum.getName();
    }


}
//
//class a {
//
//    public static void main(String[] args) {
//        System.out.println( MultiDataSourceTypeEnum.valueOf("BASE"));
//        System.out.println(MultiDataSourceTypeEnum.BUSINESS.ordinal());
//        System.out.println( MultiDataSourceTypeEnum.values());
//        for (MultiDataSourceTypeEnum type : MultiDataSourceTypeEnum.values()) {
//            if (type.name().equals("BASE")) {
//                System.out.println("type:"+type);
//            }
//        }
//    }
//}
