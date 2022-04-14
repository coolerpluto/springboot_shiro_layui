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
数据返回前端，记得在controller方法上面加@Responsebody

2.springboot扫描到mapper文件的方式
第一种是在mapper接口上加@mapepr注解
第二种是在主程序类上加mapper扫描器注解：@MapperScan(basePackages = {"com.example.sys.mapper"})，basePackages写mapper接口的路径

3.通过request请求获取请求ip值
request.getRemoteAddr()
本地请求的话如果是url开头是localhost，那获取的就是0:0:0:0:0:0:0:1
所以要改成127.0.0.1

4.@JsonFormat和@DateTimeFormat
    @JsonFormat是处理从数据查出来的时间赋给pojo实体对象时间属性的正确性，一般设置两个参数
        pattern:是时间格式，一般为"yyyy-mm-dd hh:mm:ss"
        timezone:是时区，一般为"GTM+8"
    @DateTimeFormat主要是处理从前台传来的时间的格式化，然后赋给对象的时间属性，也是用pattern和timezone两个参数
    
   那么springboot可以用yml配置文件来直接规定时间前后台的转换
        spring:
            jackson:
                date-format: yyyy-MM-dd HH:mm:ss
                time-zone: GMT+8  
                
5.QueryWrapper和LambdaQueryWrapper

   QueryWrapper<BannerItem> wrapper = new QueryWrapper<>();
   wrapper.eq("banner_id", id);
   // 查询操作
   List<BannerItem> bannerItems = bannerItemMapper.selectList(wrapper);

   QueryWrapper<BannerItem> wrapper = new QueryWrapper<>();
   wrapper.lambda().eq(BannerItem::getBannerId, id);
   List<BannerItem> bannerItems = bannerItemMapper.selectList(wrapper);

   LambdaQueryWrapper<BannerItem> wrapper = new QueryWrapper<BannerItem>().lambda();
   wrapper.eq(BannerItem::getBannerId, id);
   List<BannerItem> bannerItems = bannerItemMapper.selectList(wrapper);
               