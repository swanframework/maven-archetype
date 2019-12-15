package ${package};

import org.junit.BeforeClass;
import org.junit.Test;
import org.zongf.auto.generator.quick.config.GeneratorConfig;
import org.zongf.auto.generator.quick.utils.CodeGenerator;
import org.zongf.db.meta.mysql.config.DbConfig;

import java.io.File;

/** 自动代码生成类
 * @author zongf
 * @date 2019-11-30
 */
public class AutoCodeGeneratorTest {

    private static GeneratorConfig generatorConfig;

    private static CodeGenerator codeCreator;


    @BeforeClass
    public static void setUp(){

        String virtualFilePath = new File("temp").getAbsolutePath();
        String projectName = virtualFilePath.substring(0, virtualFilePath.lastIndexOf(File.separator));
        String packageName = AutoCodeGeneratorTest.class.getPackage().getName();

        // 初始化数据库配置
        DbConfig dbConfig = DbConfig.getInstance();
        dbConfig.setHost("localhost");
        dbConfig.setPort(3306);
        dbConfig.setUsername("root");
        dbConfig.setPassword("root");

        // 初始化项目配置
        generatorConfig = new GeneratorConfig();
        generatorConfig.setProjectPath(projectName);
        generatorConfig.setEntityPackage(packageName + ".persistence.entity");
        generatorConfig.setFieldEnumPackage(packageName + ".persistence.enums");
        generatorConfig.setMapperApiPackage(packageName + ".persistence.mapper");
        generatorConfig.setMapperXmlPath("src/main/resources/mappers");
        generatorConfig.setServiceApiPackage(packageName + ".service.api");
        generatorConfig.setServiceImplPackage(packageName + ".service.impl");
        generatorConfig.setControllerPackage(packageName + ".controller");

        // 创建代码生成器
        codeCreator = new CodeGenerator(generatorConfig);
    }


    @Test
    public void createAll(){
        String schemaName = "study-app";
        String tableName = "user";

        this.codeCreator.generateEntityClass(schemaName, tableName);
        this.codeCreator.generateFieldEnumClass(schemaName, tableName);
        this.codeCreator.generateMapperApiClass(schemaName, tableName);
        this.codeCreator.generateMapperXml(schemaName, tableName);
        this.codeCreator.generateServiceApiClass(schemaName, tableName);
        this.codeCreator.generateServiceImplClass(schemaName, tableName);
        this.codeCreator.generateControllerClass(schemaName, tableName);
    }

    @Test
    public void createEntity(){
        codeCreator.generateEntityClass("codes-generator", "user");
    }

    @Test
    public void createFieldEnum(){
        codeCreator.generateFieldEnumClass("codes-generator", "user");
    }

    @Test
    public void createMapper(){
        codeCreator.generateMapperApiClass("codes-generator", "user");
    }

    @Test
    public void createXml(){
        codeCreator.generateMapperXml("codes-generator", "user");
    }

    @Test
    public void createServiceApi(){
        codeCreator.generateServiceApiClass("codes-generator", "user");
    }

    @Test
    public void createServiceImpl(){
        codeCreator.generateServiceImplClass("codes-generator", "user");
    }

    @Test
    public void createController(){
        codeCreator.generateControllerClass("codes-generator", "user");
    }


}
