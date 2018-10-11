package com.xlily6x.core.util;

import com.xlily6x.core.exception.BaseException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiaowenlong on 22/11/2017.
 */
public class FileUtil {

    Logger logger = LogManager.getLogger();

    public static void copyFile(String filePath,String newPath) throws Exception{
        File f = new File(filePath);
        copyFile(f,newPath);
    }
    public static void copyFile(File file,String newPath) throws Exception{
        File n = new File(newPath);
        if(!file.exists()){
            throw new BaseException("File not find ");
        }
        FileUtils.copyFile(file,n);
    }
    public static void copyDirectory(File file,String decDirPath) throws Exception{
        File n = new File(decDirPath);
        if(!n.exists()){
            n.mkdir();
        }
        String tempPath = FileUtils.getTempDirectoryPath();
        tempPath = getTargetPath(tempPath,"xlily6x", UUID.randomUUID().toString());
        FileUtils.copyDirectory(n,new File(tempPath));
        FileUtils.copyDirectory(file,n);
        FileUtils.copyDirectory(new File(tempPath),n);
        FileUtils.deleteDirectory(new File(tempPath));

    }
    public static void copyDirectory(String srcDirPath,String decDirPath) throws Exception{
        File f = new File(srcDirPath);
        copyDirectory(f,decDirPath);
    }
    public static void copyDirectory(String srcDirPath,String decDirPath,boolean overwrite) throws Exception{
        if(overwrite){
            copyDirectory(srcDirPath,decDirPath);
            return;
        }
        try {
            List<String> ofilelist = new ArrayList<>();
            List<String> nfilelist = new ArrayList<>();

            File nfiles = new File(decDirPath);

            File [] files2 = null;

            if(nfiles.exists()){
                files2 = nfiles.listFiles();
                System.out.print(files2.length);
                for(File f : files2){
                    nfilelist.add(f.getName());
                }
            }

            File ofiles = new File(srcDirPath);
            File [] files =null;
            if(ofiles.exists()){
                files = ofiles.listFiles();

                System.out.print("\n"+files.length );

                for(File f : files){
                    ofilelist.add(f.getName());
                    //如果是目录，复制目录里面的文件到新目录
                    if(f.isDirectory()){
//                        FileUtils.copyDirectory(new File(getTargetPath(srcDirPath,f.getName())),nfiles);
                        copyDirectory(getTargetPath(srcDirPath,f.getName()),getTargetPath(decDirPath,f.getName()),overwrite);

                    }

                    if(nfilelist.contains(f.getName())){
                        System.out.println(f.getName()+ " 文件已经存在");
                    }else{
                        System.out.println(f.getName()+ " 不存在");
                        FileUtils.copyFileToDirectory(f,nfiles);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void copyFileNotoverwrite(String srcDirPath,String decDirPath,boolean overwrite) throws Exception{
        if(overwrite){
            copyDirectory(srcDirPath,decDirPath);
            return;
        }
        try {
            List<String> ofilelist = new ArrayList<>();
            List<String> nfilelist = new ArrayList<>();

            File nfiles = new File(decDirPath);

            File [] files2 = null;

            if(nfiles.exists()){
                files2 = nfiles.listFiles();
                System.out.print(files2.length);
                for(File f : files2){
                    nfilelist.add(f.getName());
                }
            }

            File ofiles = new File(srcDirPath);
            File [] files =null;
            if(ofiles.exists()){
                files = ofiles.listFiles();

                System.out.print("\n"+files.length );

                for(File f : files){
                    ofilelist.add(f.getName());
                    //如果是目录，复制目录里面的文件到新目录
                    if(f.isDirectory()){
//                        FileUtils.copyDirectory();
                    }

                    if(nfilelist.contains(f.getName())){
                        System.out.println(f.getName()+ " 文件已经存在");
                    }else{
                        System.out.println(f.getName()+ " 不存在");
                        FileUtils.copyFileToDirectory(f,nfiles);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File file(String filePath){
        File f = new File(filePath);
        if(!f.exists()){
            return null;
        }
        return f;
    }

    public static File[] fileLists(File file) throws Exception{
        if(!file.isDirectory()){
            throw new BaseException("File is not a Directory");
        }
        return file.listFiles();
    }

    public static File[] fileLists(String filePath) throws Exception{
        File f = new File(filePath);
        return fileLists(f);
    }

    public static boolean checkFileExists(String path){
        File f = new File(path);
        return f.exists();
    }

    public static void deleteDirectory(String path) throws Exception{
        File file = new File(path);
        if(!file.exists()){
            throw new BaseException("No file find");
        }
        FileUtils.deleteQuietly(file);
    }


    public static String getTargetPath(String ... paths){
        String path = "";
        if(paths.length>0){
            for(String s:paths){
                path = path+"\\"+s;
            }
        }
        return path;
    }

    public static String packageToPath(String pkg){
        return pkg.replace(".","\\");
    }
    public static String windowsPathToJava(String path){
        return path.replace("\\","/");
    }

    public static void main(String args[]){
        try {
//            FileUtil.copyFile("D:\\AFL_Product.xlsx","D:\\home\\AFL_Product.xlsx");
            FileUtils.copyDirectory(new File("D:\\usr"),new File("D:\\home"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
