package com.ovopark.tao.java.tool.demo.easyexcle.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.ovopark.tao.java.tool.easyexcle.read.entity.*;
import com.ovopark.tao.java.tool.easyexcle.read.listener.*;
import com.ovopark.tao.java.tool.easyexcle.read.entity.ExceptionDemoData;
import com.ovopark.tao.java.tool.easyexcle.read.listener.DemoExceptionListener;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 读xsl
 */
public class ReadTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);

  public static String xsl = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Java/Java-Tool/src/main/java/com/ovopark/tao/java/tool/easyexcle/read/xslFile/demo.xlsx";

  @Test
  public void simpleRead(){
    //写法 1

    EasyExcel.read(xsl, DemoData.class,new DemoDataListener()).sheet(1).doRead();

    //写法 2
    ExcelReader excelReader = null;
    try {
      excelReader = EasyExcel.read(xsl, DemoData.class, new DemoDataListener()).build();
      ReadSheet readSheet = EasyExcel.readSheet(0).build();
      excelReader.read(readSheet);
    } finally {
      if (excelReader != null) {
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
      }
    }
  }


  /**
   * 指定列的下标或者列名
   *
   * <p>
   * 1. 创建excel对应的实体对象,并使用{@link ExcelProperty}注解. 参照{@link IndexOrNameData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link IndexOrNameDataListener}
   * <p>
   * 3. 直接读即可
   */
  @Test
  public void indexOrNameRead() {
    String fileName = xsl;
    // 这里默认读取第一个sheet
    EasyExcel.read(fileName, IndexOrNameData.class, new IndexOrNameDataListener()).sheet(0).doRead();
  }

  /**
   * 读多个或者全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
   * <p>
   * 3. 直接读即可
   */
  @Test
  public void repeatedRead() {
    /**
     * 读取全部
     */
    EasyExcel.read(xsl, DemoData.class, new DemoDataListener()).doReadAll();

    /**
     * 读取部分sheet
     */
    ExcelReader excelReader = null;
    try {
      excelReader = EasyExcel.read(xsl).build();
      // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
      //读取sheet1
      ReadSheet readSheet1 =
        EasyExcel.readSheet(0).head(DemoData.class).registerReadListener(new DemoDataListener()).build();
      //读取sheet2
      ReadSheet readSheet2 =
        EasyExcel.readSheet(1).head(DemoData.class).registerReadListener(new DemoDataListener()).build();
      // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
      excelReader.read(readSheet1, readSheet2);
    } finally {
      if (excelReader != null) {
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
      }
    }
  }


  /**
   * 日期、数字或者自定义格式转换
   * <p>
   * 默认读的转换器{@link DefaultConverterLoader#loadDefaultReadConverter()}
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link ConverterData}.里面可以使用注解{@link DateTimeFormat}、{@link NumberFormat}或者自定义注解
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ConverterDataListener}
   * <p>
   * 3. 直接读即可
   */
  @Test
  public void converterRead() {
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(xsl, ConverterData.class, new ConverterDataListener())
      // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
      // 如果就想单个字段使用请使用@ExcelProperty 指定converter
      // .registerConverter(new CustomStringStringConverter())
      // 读取sheet
      .sheet().doRead();
  }


  /**
   * 多行 头
   *
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
   * <p>
   * 3. 设置headRowNumber参数，然后读。 这里要注意headRowNumber如果不指定， 会根据你传入的class的{@link ExcelProperty#value()}里面的表头的数量来决定行数，
   * 如果不传入class则默认为1.当然你指定了headRowNumber不管是否传入class都是以你传入的为准。
   */
  @Test
  public void complexHeaderRead() {
    String fileName = xsl;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet()
      // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
      .headRowNumber(1).doRead();
  }


  /**
   * 读取表头数据
   *
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoHeadDataListener}
   * <p>
   * 3. 直接读即可
   */
  @Test
  public void headerRead() {
    String fileName = xsl;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(fileName, DemoData.class, new DemoHeadDataListener()).sheet().doRead();
  }

  public static String extra = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Java/Java-Tool/src/main/java/com/ovopark/tao/java/tool/easyexcle/read/xslFile/extra.xlsx";

  /**
   * 额外信息（批注、超链接、合并单元格信息读取）
   * <p>
   * 由于是流式读取，没法在读取到单元格数据的时候直接读取到额外信息，所以只能最后通知哪些单元格有哪些额外信息
   *
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoExtraData}
   * <p>
   * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoExtraListener}
   * <p>
   * 3. 直接读即可
   *
   * @since 2.2.0-beat1
   */
  @Test
  public void extraRead() {
    String fileName = extra;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(fileName, DemoExtraData.class, new DemoExtraListener())
      // 需要读取批注 默认不读取
      .extraRead(CellExtraTypeEnum.COMMENT)
      // 需要读取超链接 默认不读取
      .extraRead(CellExtraTypeEnum.HYPERLINK)
      // 需要读取合并单元格信息 默认不读取
      .extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();
  }

  private static String cellData = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Java/Java-Tool/src/main/java/com/ovopark/tao/java/tool/easyexcle/read/xslFile/cellDataDemo.xlsx";

  /**
   * 读取公式和单元格类型
   *
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link CellDataReadDemoData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoHeadDataListener}
   * <p>
   * 3. 直接读即可
   *
   * @since 2.2.0-beat1
   */
  @Test
  public void cellDataRead() {
    String fileName = cellData;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(fileName, CellDataReadDemoData.class, new CellDataDemoHeadDataListener()).sheet().doRead();
  }

  /**
   * 数据转换等异常处理
   *
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link ExceptionDemoData}
   * <p>
   * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoExceptionListener}
   * <p>
   * 3. 直接读即可
   */
  @Test
  public void exceptionRead() {
    String fileName = xsl;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet
    EasyExcel.read(fileName, ExceptionDemoData.class, new DemoExceptionListener()).sheet().doRead();
  }

  /**
   * 不创建对象的读
   */
  @Test
  public void noModelRead() {
    String fileName = xsl;
    // 这里 只要，然后读取第一个sheet 同步读取会自动finish
    EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
  }


  /**
   * 同步的返回，不推荐使用，如果数据量大会把数据放到内存里面
   */
  @Test
  public void synchronousRead() {
    String fileName = xsl;
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
    List<DemoData> list = EasyExcel.read(fileName).head(DemoData.class).sheet().doReadSync();
    for (DemoData data : list) {
      LOGGER.info("读取到数据:{}", JSON.toJSONString(data));
    }

    // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
    List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
    for (Map<Integer, String> data : listMap) {
      // 返回每条数据的键值对 表示所在的列 和所在列的值
      LOGGER.info("读取到数据:{}", JSON.toJSONString(data));
    }
  }
}
