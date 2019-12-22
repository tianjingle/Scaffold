package com.inclination.scaffold.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMDExecuteUtil {

    public static String executeCommand(String cmd, File file) {
        // TODO Auto-generated method stub
        //根据运行环境判断运行命令
        String osName=System.getProperty("os.name");
        System.out.println(osName);
        String []command={"","",cmd};
        if(osName.contains("Windows")){
            command[0]="cmd.exe";
            command[1]="/c";
        }else{
            command[0]="/bin/sh";
            command[1]="-c";
        }

        StringBuffer output=new StringBuffer();
        Process p;
        InputStreamReader inputStreamReader=null;
        BufferedReader reader=null;
        try{
            p=Runtime.getRuntime().exec(command,null,file);
            Thread t=new Thread(new InputStreamRunnable(p.getErrorStream()));
            t.start();
            inputStreamReader=new InputStreamReader(p.getInputStream(),"utf-8");
            reader=new BufferedReader(inputStreamReader);
            String line="";
            while((line=reader.readLine())!=null){
                output.append(line+"\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                inputStreamReader.close();
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return output.toString();
    }
}
