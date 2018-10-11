import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.xlily6x.compiler.read.U1;
import com.xlily6x.core.util.CodeGeneratorUtil;
import com.xlily6x.core.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Admin on 21/7/2017.
 */
public class Test1 {

    public static long readU4(InputStream inputStream) {
        byte[] bytes = new byte[4];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long num = 0;
        for (int i= 0; i < bytes.length; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }
    public static int readU2(InputStream inputStream) {
        byte[] bytes = new byte[2];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int num = 0;
        for (int i= 0; i < bytes.length; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }
    public static short readU1(InputStream inputStream) {
        byte[] bytes = new byte[1];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        short value = (short) (bytes[0] & 0xFF);
        return value;
    }

    @Test
    public void te(){
        int i = 100;
        System.out.print(Integer.toBinaryString(i));
        System.out.print("\n");
        int [] codePoints = new int[]{116, 101, 115, 116};
        String s = new String(codePoints,0,codePoints.length);
        System.out.print(s);

        System.out.print("\n");
        String t = "RuntimeVisibleAnnotations";
        byte [] bytes = t.getBytes();
        for (int k =0;k<bytes.length;k++){
            int nu = bytes[k];
            System.out.print(nu);
            System.out.print(" ");
        }




    }
    @Test
    public void testP (){


        long n = -52;
        System.out.println(Long.toHexString(n).toUpperCase());

        String filepath = "D:\\Leo-Xiao\\MyWork\\project\\Pre-Order\\AF-PreOrder\\branch\\newPreorder-0821-0901\\PreOrder-0828-0901\\PreOrder-Core\\target\\test-classes\\ClassTest.class";
        File file = new File(filepath);
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            int b = 0;
            byte [] bytes = new byte[1024];
            while (b<file.length()){
                if(b%16==0){
                    System.out.print("\n");
                }
                System.out.print(" ");

                System.out.print(StringUtils.leftPad(Integer.toHexString(U1.read(inputStream)).toUpperCase(),2,"0") );


                b++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    @Test
    public void testClass(){
        String filepath = "D:\\Leo-Xiao\\MyWork\\project\\Pre-Order\\AF-PreOrder\\branch\\newPreorder-0821-0901\\PreOrder-0828-0901\\PreOrder-Core\\target\\test-classes\\ClassTest.class";
        File file = new File(filepath);
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            long res = Test1.readU4(inputStream);
            int r = Test1.readU2(inputStream);
            int s = Test1.readU2(inputStream);
            long ress = Test1.readU4(inputStream);
            System.out.println(res);
            System.out.println(r);
            System.out.println(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    Logger logger = LogManager.getLogger();
//
//
//
//    @Test
//    public void pdfExportTest() throws Exception{
//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("D:/Leo-Xiao/test2.pdf"));
//
//        document.open();
//
//        //写PDF 内容
//        //创建表格
//        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
//                "UniGB-UCS2-H", false);
//        Font font = new Font(bfChinese, 10, Font.NORMAL);
//
//
//        document.add(new Paragraph("Order info:"));
//
//        document.add(new Paragraph("finish"));
//
//        document.add(new Paragraph("你好 ， 中国人 ！",font));
//
//        Chunk underline = new Chunk("hello,AJava.org ");
//        underline.setUnderline(0.1f, -1f);
//        document.add(underline);
//
//        Paragraph p1 = new Paragraph("LEFT");
//        p1.add(new Chunk(new LineSeparator()));
//        p1.add("R");
//        document.add(p1);
//
//        //点线
//        Paragraph p2 = new Paragraph("LEFT");
//        p2.add(new Chunk(new DottedLineSeparator()));
//        p2.add("R");
//        document.add(p2);
//
//        document.close();
//    }
//
//    @Test
//    public void testPDF(){
//
//            try
//            {
//                Document document = new Document(PageSize.A4.rotate());
//                PdfWriter.getInstance(document, new FileOutputStream("D:/Leo-Xiao/test.pdf"));
//
//                //设置字体
//                BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//                com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
//                com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD);
//                com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
//                com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
//                com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
//                com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
//                com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);
//
//                document.open();
//                //table1
//                PdfPTable table1 = new PdfPTable(3);
//                PdfPCell cell11 = new PdfPCell(new Paragraph("费用报销",FontChinese24));
//                cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell11.setBorder(0);
////                String imagePath = "D:/alibaba.jpg";
////                Image image1 = Image.getInstance(imagePath);
////
////                Image image2 = Image.getInstance(imagePath);
//                //设置每列宽度比例
//                int width11[] = {35,40,25};
//                table1.setWidths(width11);
//                table1.getDefaultCell().setBorder(0);
//                table1.addCell(new Paragraph("图片",FontChinese12));
//                table1.addCell(cell11);
//                table1.addCell(new Paragraph("图片",FontChinese12));
//                document.add(table1);
//                //加入空行
//                Paragraph blankRow1 = new Paragraph(18f, " ", FontChinese18);
//                document.add(blankRow1);
//
//                //table2
//                PdfPTable table2 = new PdfPTable(2);
//                //设置每列宽度比例
//                int width21[] = {2,98};
//                table2.setWidths(width21);
//                table2.getDefaultCell().setBorder(0);
//                PdfPCell cell21 = new PdfPCell(new Paragraph("报销概要",FontChinese16));
////                String imagePath2 = "D:/boder.jpg";
////                Image image21 = Image.getInstance(imagePath2);
//                cell21.setBorder(0);
//                table2.addCell(new Paragraph("图片",FontChinese12));
//                table2.addCell(cell21);
//                document.add(table2);
//                //加入空行
//                Paragraph blankRow2 = new Paragraph(18f, " ", FontChinese18);
//                document.add(blankRow2);
//
//                //table3
//                PdfPTable table3 = new PdfPTable(3);
//                int width3[] = {40,35,25};
//                table3.setWidths(width3);
//                PdfPCell cell31 = new PdfPCell(new Paragraph("申请人："+"XXX",FontChinese11Normal));
//                PdfPCell cell32 = new PdfPCell(new Paragraph("日期："+"2011-11-11",FontChinese11Normal));
//                PdfPCell cell33 = new PdfPCell(new Paragraph("报销单号："+"123456789",FontChinese11Normal));
//                cell31.setBorder(0);
//                cell32.setBorder(0);
//                cell33.setBorder(0);
//                table3.addCell(cell31);
//                table3.addCell(cell32);
//                table3.addCell(cell33);
//                document.add(table3);
//                //加入空行
//                Paragraph blankRow31 = new Paragraph(18f, " ", FontChinese11);
//                document.add(blankRow31);
//
//                //table4
//                PdfPTable table4 = new PdfPTable(2);
//                int width4[] = {40,60};
//                table4.setWidths(width4);
//                PdfPCell cell41 = new PdfPCell(new Paragraph("公司："+"XXX",FontChinese11Normal));
//                PdfPCell cell42 = new PdfPCell(new Paragraph("部门："+"XXX",FontChinese11Normal));
//                cell41.setBorder(0);
//                cell42.setBorder(0);
//                table4.addCell(cell41);
//                table4.addCell(cell42);
//                document.add(table4);
//                //加入空行
//                Paragraph blankRow41 = new Paragraph(18f, " ", FontChinese11);
//                document.add(blankRow41);
//
//                //table5
//                PdfPTable table5 = new PdfPTable(1);
//                PdfPCell cell51 = new PdfPCell(new Paragraph("报销说明："+"XXX",FontChinese11));
//                cell51.setBorder(0);
//                table5.addCell(cell51);
//                document.add(table5);
//                //加入空行
//                Paragraph blankRow51 = new Paragraph(18f, " ", FontChinese18);
//                document.add(blankRow51);
//
//                //table6
//                PdfPTable table6 = new PdfPTable(2);
//                table6.getDefaultCell().setBorder(0);
//                table6.setWidths(width21);
//                PdfPCell cell61 = new PdfPCell(new Paragraph("报销明细",FontChinese16));
//                cell61.setBorder(0);
//                table6.addCell(new Paragraph("图片",FontChinese12));
//                table6.addCell(cell61);
//                document.add(table6);
//                //加入空行
//                Paragraph blankRow4 = new Paragraph(18f, " ", FontChinese16);
//                document.add(blankRow4);
//
//                //table7
//                PdfPTable table7 = new PdfPTable(6);
//                BaseColor lightGrey = new BaseColor(0xCC,0xCC,0xCC);
//                int width7[] = {20,18,13,20,14,15};
//                table7.setWidths(width7);
//                PdfPCell cell71 = new PdfPCell(new Paragraph("费用类型",FontChinese11Bold));
//                PdfPCell cell72 = new PdfPCell(new Paragraph("费用发生时间",FontChinese11Bold));
//                PdfPCell cell73 = new PdfPCell(new Paragraph("详细信息",FontChinese11Bold));
//                PdfPCell cell74 = new PdfPCell(new Paragraph("消费金币/币种",FontChinese11Bold));
//                PdfPCell cell75 = new PdfPCell(new Paragraph("报销汇率",FontChinese11Bold));
//                PdfPCell cell76 = new PdfPCell(new Paragraph("报销金额",FontChinese11Bold));
//                //表格高度
//                cell71.setFixedHeight(25);
//                cell72.setFixedHeight(25);
//                cell73.setFixedHeight(25);
//                cell74.setFixedHeight(25);
//                cell75.setFixedHeight(25);
//                cell76.setFixedHeight(25);
//                //水平居中
//                cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell76.setHorizontalAlignment(Element.ALIGN_CENTER);
//                //垂直居中
//                cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell73.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell74.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell75.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell76.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                //边框颜色
//                cell71.setBorderColor(lightGrey);
//                cell72.setBorderColor(lightGrey);
//                cell73.setBorderColor(lightGrey);
//                cell74.setBorderColor(lightGrey);
//                cell75.setBorderColor(lightGrey);
//                cell76.setBorderColor(lightGrey);
//                //去掉左右边框
//                cell71.disableBorderSide(8);
//                cell72.disableBorderSide(4);
//                cell72.disableBorderSide(8);
//                cell73.disableBorderSide(4);
//                cell73.disableBorderSide(8);
//                cell74.disableBorderSide(4);
//                cell74.disableBorderSide(8);
//                cell75.disableBorderSide(4);
//                cell75.disableBorderSide(8);
//                cell76.disableBorderSide(4);
//                table7.addCell(cell71);
//                table7.addCell(cell72);
//                table7.addCell(cell73);
//                table7.addCell(cell74);
//                table7.addCell(cell75);
//                table7.addCell(cell76);
//                document.add(table7);
//
//                //table8
//                PdfPTable table8 = new PdfPTable(6);
//                int width8[] = {20,18,13,20,14,15};
//                table8.setWidths(width8);
//                PdfPCell cell81 = new PdfPCell(new Paragraph("差旅报销",FontChinese12));
//                PdfPCell cell82 = new PdfPCell(new Paragraph("2011-11-11",FontChinese12));
//                PdfPCell cell83 = new PdfPCell(new Paragraph("XXX",FontChinese12));
//                PdfPCell cell84 = new PdfPCell(new Paragraph("XXX",FontChinese12));
//                PdfPCell cell85 = new PdfPCell(new Paragraph("XXX",FontChinese12));
//                PdfPCell cell86 = new PdfPCell(new Paragraph("XXX",FontChinese12));
//                //表格高度
//                cell81.setFixedHeight(25);
//                cell82.setFixedHeight(25);
//                cell83.setFixedHeight(25);
//                cell84.setFixedHeight(25);
//                cell85.setFixedHeight(25);
//                cell86.setFixedHeight(25);
//                //水平居中
//                cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
//                //垂直居中
//                cell81.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell82.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell83.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell84.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell85.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell86.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                //边框颜色
//                cell81.setBorderColor(lightGrey);
//                cell82.setBorderColor(lightGrey);
//                cell83.setBorderColor(lightGrey);
//                cell84.setBorderColor(lightGrey);
//                cell85.setBorderColor(lightGrey);
//                cell86.setBorderColor(lightGrey);
//                //去掉左右边框
//                cell81.disableBorderSide(8);
//                cell82.disableBorderSide(4);
//                cell82.disableBorderSide(8);
//                cell83.disableBorderSide(4);
//                cell83.disableBorderSide(8);
//                cell84.disableBorderSide(4);
//                cell84.disableBorderSide(8);
//                cell85.disableBorderSide(4);
//                cell85.disableBorderSide(8);
//                cell86.disableBorderSide(4);
//                table8.addCell(cell81);
//                table8.addCell(cell82);
//                table8.addCell(cell83);
//                table8.addCell(cell84);
//                table8.addCell(cell85);
//                table8.addCell(cell86);
//                document.add(table8);
//                //加入空行
//                Paragraph blankRow5 = new Paragraph(18f, " ", FontChinese18);
//                document.add(blankRow5);
//
//                //table9
//                PdfPTable table9 = new PdfPTable(3);
//                int width9[] = {30,50,20};
//                table9.setWidths(width9);
//                PdfPCell cell91 = new PdfPCell(new Paragraph("",FontChinese12));
//                PdfPCell cell92 = new PdfPCell(new Paragraph("收到的报销金额",FontChinese12));
//                PdfPCell cell93 = new PdfPCell(new Paragraph("1000",FontChinese24));
//                cell92.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell92.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell93.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell93.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell91.setBorder(0);
//                cell92.setBorder(0);
//                cell93.setBorder(0);
//                table9.addCell(cell91);
//                table9.addCell(cell92);
//                table9.addCell(cell93);
//                document.add(table9);
//
//                document.close();
//
//            } catch (Exception ex)
//            {
//                ex.printStackTrace();
//            }
//    }
//
//
//    @Test
//    public void testcopy001(){
//        String basePath = "D:\\temp";
//        String rpath = "D:\\temp\\rpath";
//        String tpath = "D:\\temp\\tpath";
//        String tempPath = FileUtils.getTempDirectoryPath();
//
//
//
//    }
//
//    List<String> classPaths = new ArrayList<String>();
//    @Test
//    public void searchClass() throws ClassNotFoundException {
//        //包名
//        String basePack = "com.xlily6x";
//        //先把包名转换为路径,首先得到项目的classpath
//        String classpath = FileUtil.class.getResource("/").getPath();
//        //然后把我们的包名basPach转换为路径名
//        basePack = basePack.replace(".", File.separator);
//        //然后把classpath和basePack合并
//        String searchPath = classpath + basePack;
//        doPath(new File(searchPath));
//        //这个时候我们已经得到了指定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
//        for (String s : classPaths) {
//            //把 D:\work\code\20170401\search-class\target\classes\com\baibin\search\a\A.class 这样的绝对路径转换为全类名com.baibin.search.a.A
//            s = s.replace(classpath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
//            Class cls = Class.forName(s);
//            System.out.println(cls);
//        }
//    }
//
//    /**
//     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
//     * @param file
//     */
//    private void doPath(File file) {
//        if (file.isDirectory()) {//文件夹
//            //文件夹我们就递归
//            File[] files = file.listFiles();
//            for (File f1 : files) {
//                doPath(f1);
//            }
//        } else {//标准文件
//            //标准文件我们就判断是否是class文件
//            if (file.getName().endsWith(".class")) {
//                //如果是class文件我们就放入我们的集合中。
//                classPaths.add(file.getPath());
//            }
//        }
//    }
//
//    @Test
//    public void testFileUtil(){
//        // //获取临时目录
//        String path = FileUtils.getTempDirectoryPath();
//        System.out.println(path);
//        File path2 = FileUtils.getTempDirectory();
//        System.out.println(path2);
//    }
//
//    public List<String> fileList = new ArrayList<>();
//    public void iteration(String path){
//        File file = new File(path);
//        String p = file.getName();
//        logger.info(p);
//        if(file.isDirectory()){
//            File[] filelists = file.listFiles();
//            for(File f : filelists){
//                iteration(f.getPath());
//            }
//        }else{
//            fileList.add(file.getPath());
//        }
//    }
//
//    @Test
//    public void testFile(){
//        String pkg = "com.xlily6x";
//        String s = this.getClass().getResource("").getPath();
//        String path = s+FileUtil.packageToPath(pkg);
//        path = FileUtil.windowsPathToJava(path);
//        logger.info(path);
//        iteration(path);
//
//        for(String j : fileList){
//            logger.info(j);
//        }
////        String s = this.getClass().getResource("").getPath();
////        logger.info(s);
////
////        logger.info(System.getProperty("user.dir"));
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testCode(){
//        logger.info("lily test");
//        CodeGeneratorUtil.GeneratorHelper g =  CodeGeneratorUtil.getGeneratorHelper();
//        g.setTableNames(new String[]{"test_sys_user"});
//        g.setPassword("123456");
//        g.setUserName("system");
//        String userDir = System.getProperty("user.dir");
//        g.setHomePath(userDir);
//        g.setBasePackage("com.xlily6x");
//        g.setUrl("jdbc:mysql://10.205.138.135:8066/system?useUnicode=true&amp;characterEncoding=UTF-8");
//        logger.info(System.getProperty("user.dir"));
//        Map<String,String> modals = new HashMap<>();
//        modals.put("api","xLily6X-Api");
//        modals.put("domain","xLily6X-Facade");
//        try {
//            CodeGeneratorUtil.generator(g);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String homePath = g.getHomePath();
//        String apiPath = userDir.substring(0,userDir.lastIndexOf("\\"));
//        logger.info(apiPath+"\\"+modals.get("api"));
//
//        String domainPath = userDir.substring(0,userDir.lastIndexOf("\\"));
//        String domainPro =domainPath+"\\"+modals.get("domain");
//        logger.info(domainPro);
//
//
//        //复制文件到不同项目
//        Map<String,File[]> code = new HashMap<>();
//        File tempFiles = new File(homePath+"\\com\\xlily6x\\temp");
//        if(!tempFiles.exists()){
//            return;
//        }
//        String n = tempFiles.getName();
//        logger.info("Project path : ");
//        logger.info(n);
//        File[] list = tempFiles.listFiles();
//
//        int i = list.length;
//        logger.info(i);
//        String domainProPath = domainPro+"\\src\\main\\java\\com\\xlily6x\\doman";
//        File file = new File(domainProPath);
//        if(!file.exists()){
//            logger.info("domain path not exists");
//            file.mkdir();
//        }
//
//        if(tempFiles.isDirectory()){
//            logger.info("isDirectory");
//            for(File f :list){
//                logger.info("GF:");
//                logger.info(f.getName());
//                File[] subfiles = f.listFiles();
//                code.put(f.getName(),subfiles);
//                if(f.getName().equals("domain")){
//                    try {
//                        FileUtil.copyDirectory(f,domainProPath);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        logger.info(code);
//
//    }
//
//    @Test
//    public void test1410(){
//        logger.info("lily test");
//        String cmd = "cmd /c start D:\\Leo-Xiao\\MyWork\\project\\project\\xLily6X\\xLily6X-Common";
//        try {
//            final Process process = Runtime.getRuntime().exec(cmd);
//            printMessage(process.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.info("lily test end");
//
//    }
//
//    private static void printMessage(final InputStream input){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Reader reader = new InputStreamReader(input);
//                BufferedReader bf = new BufferedReader(reader);
//                String line = null;
//                try {
//                    while ((line=bf.readLine())!=null){
//                        System.out.println(line);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//
////
//    @Test
//    public void test002(){
//        /***
//         * %s
//         字符串类型
//         "mingrisoft"
//         %c
//         字符类型
//         'm'
//         %b
//         布尔类型
//         true
//         %d
//         整数类型（十进制）
//         99
//         %x
//         整数类型（十六进制）
//         FF
//         %o
//         整数类型（八进制）
//         77
//         %f
//         浮点类型
//         99.99
//         %a
//         十六进制浮点类型
//         FF.35AE
//         %e
//         指数类型
//         9.38e+5
//         %g
//         通用浮点类型（f和e类型中较短的）
//
//         %h
//         散列码
//
//         %%
//         百分比类型
//         ％
//         %n
//         换行符
//
//         %tx
//         日期与时间类型（x代表不同的日期与时间转换符
//         */
//        String str121=null;
//        str121=String.format("Hi,%s", "王力");
//        System.out.println(str121);
//        str121=String.format("Hi,%s:%s.%s", "王南","王力","王张");
//        System.out.println(str121);
//        System.out.printf("字母a的大写是：%c %n", 'A');
//        System.out.printf("3>7的结果是：%b %n", 3>7);
//        System.out.printf("100的一半是：%d %n", 100/2);
//        System.out.printf("100的16进制数是：%x %n", 100);
//        System.out.printf("100的8进制数是：%o %n", 100);
//        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
//        System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
//        System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
//        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
//        System.out.printf("上面的折扣是%d%% %n", 85);
//        System.out.printf("字母A的散列码是：%h %n", 'A');
//        System.out.printf("%n****************************************************%n");
//
//        Date date=new Date();
//        //c的使用
//        System.out.printf("全部日期和时间信息：%tc%n",date);
//        //f的使用
//        System.out.printf("年-月-日格式：%tF%n",date);
//        //d的使用
//        System.out.printf("月/日/年格式：%tD%n",date);
//        //r的使用
//        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
//        //t的使用
//        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
//        //R的使用
//        System.out.printf("HH:MM格式（24时制）：%tR",date);
//
//        System.out.printf("%n****************************************************%n");
//        //b的使用，月份简称
//        String str=String.format(Locale.US,"英文月份简称：%tb",date);
//        System.out.println(str);
//        System.out.printf("本地月份简称：%tb%n",date);
//        //B的使用，月份全称
//        str=String.format(Locale.US,"英文月份全称：%tB",date);
//        System.out.println(str);
//        System.out.printf("本地月份全称：%tB%n",date);
//        //a的使用，星期简称
//        str=String.format(Locale.US,"英文星期的简称：%ta",date);
//        System.out.println(str);
//        //A的使用，星期全称
//        System.out.printf("本地星期的简称：%tA%n",date);
//        //C的使用，年前两位
//        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);
//        //y的使用，年后两位
//        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);
//        //j的使用，一年的天数
//        System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);
//        //m的使用，月份
//        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);
//        //d的使用，日（二位，不够补零）
//        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);
//        //e的使用，日（一位不补零）
//        System.out.printf("月份的日（前面不补0）：%te",date);
//
//        System.out.printf("%n****************************************************%n");
//        //H的使用
//        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);
//        //I的使用
//        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);
//        //k的使用
//        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);
//        //l的使用
//        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);
//        //M的使用
//        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);
//        //S的使用
//        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);
//        //L的使用
//        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);
//        //N的使用
//        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);
//        //p的使用
//        String str0 = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);
//        System.out.println(str0);
//        System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);
//        //z的使用
//        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);
//        //Z的使用
//        System.out.printf("时区缩写字符串:%tZ%n", date);
//        //s的使用
//        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);
//        //Q的使用
//        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n", date);
////        String s = "";
////        String i = "com.xlily6x.lily";
////        int res = i.indexOf(".");
////        if(res>=0){
////             s =i.replace(".","\\");
////        }
////        logger.info(s);
////        String s = i.replaceAll(".","\\");
////        logger.info(s);
////        String zipPath = "D:\\com";
////        String zipFilePath = CodeGeneratorUtil.zipped(zipPath);
////        System.out.println(zipFilePath);
//    }
////
////    @Test
////    public void test001(){
////        File zipFile = new File("D:\\com.zip");
////        File srcdir = new File("D:\\com");
////
////        if (!srcdir.exists()){
////            throw new RuntimeException("com 不存在！");
////        }
////
////        Project prj = new Project();
////
////
////        FileSet fileSet = new FileSet();
////        fileSet.setProject(prj);
////        fileSet.setDir(srcdir);
////        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
////        //fileSet.setExcludes(...); //排除哪些文件或文件夹
////
////        Zip zip = new Zip();
////        zip.setProject(prj);
////        zip.setDestFile(zipFile);
////        zip.addFileset(fileSet);
////        zip.execute();
////
////    }
////
////    @Test
////    public void testDir(){
////        ZipOutputStream zos = null;
////        try {
////            // the file path need to compress
////            File file = new File ( "D:\\ziptest" ) ;
////            zos = new ZipOutputStream(
////                    new FileOutputStream(
////                            "D:\\ziptest.zip" ) );
////
////            // judge the file is the directory
////            if ( file.isDirectory ( ) )
////            {
////                // get the every file in the directory
////                File [ ] files = file.listFiles ( ) ;
////
////                for ( int i = 0 ; i < files.length ; i ++ )
////                {
////                    // new the BuuferedInputStream
////                    BufferedInputStream bis = new BufferedInputStream(
////                            new FileInputStream(
////                                    files [ i ] ) ) ;
////                    // the file entry ,set the file name in the zip
////                    // file
////                    zos.putNextEntry ( new ZipEntry( file
////                            .getName ( )
////                            + file.separator
////                            + files [ i ].getName ( ) ) ) ;
////                    while ( true )
////                    {
////                        byte [ ] b = new byte [ 100 ] ;
////                        int len = bis.read ( b ) ;
////                        if ( len == - 1 )
////                            break ;
////                        zos.write ( b , 0 , len ) ;
////                    }
////
////                    // close the input stream
////                    bis.close ( ) ;
////                }
////
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                zos.close ( ) ;
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        // close the zip output stream
////
////    }
//
//    @Test
//    public void testCopyFile(){
//        String opath = "D:\\testCopy\\d1";
//        String npath = "D:\\testCopy\\d2";
//        try {
////            FileUtils.copyDirectory(new File(opath),new File(npath),true);
//
//            List<String> ofilelist = new ArrayList<>();
//            List<String> nfilelist = new ArrayList<>();
//
//            File nfiles = new File(npath);
//
//            File [] files2 = null;
//
//            if(nfiles.exists()){
//                files2 = nfiles.listFiles();
//                System.out.print(files2.length);
//                for(File f : files2){
//                    nfilelist.add(f.getName());
//                }
//            }
//
//            File ofiles = new File(opath);
//            File [] files =null;
//            if(ofiles.exists()){
//                files = ofiles.listFiles();
//
//                System.out.print("\n"+files.length );
//
//                for(File f : files){
//                    ofilelist.add(f.getName());
//                    if(nfilelist.contains(f.getName())){
//                        System.out.println(f.getName()+ " 文件已经存在");
//                    }else{
//                        System.out.println(f.getName()+ " 不存在");
//                        FileUtils.copyFileToDirectory(f,nfiles);
//                    }
//
//                }
//
//            }
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
