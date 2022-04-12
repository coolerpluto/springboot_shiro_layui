1.分页
new一个ipage包装页码，每页的数据数量
IPage<pojo> page = new Page<>(currentPage, limit);
然后把这个Ipage放到service方法调用的page方法，这是mybatisPlus自带的分页方法
xxxService.page(page,wrapper),这个第二个参数是数据筛选wrapper
然后在自定义的mybatisPlusConfig配置文件里，往容器放一个PaginationInterceptor
@ConditionalOnClass(value = {PaginationInterceptor.class})
@Configuration
public class MybatiPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}