package kr.co.bit.service;

import kr.co.bit.dao.FileUploadDAO;
import kr.co.bit.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService {

    @Autowired
    FileUploadDAO fileUploadDAO;

    public FileVO restore(MultipartFile multipartFile,String title,int user_no){
        String saveDir = "D:\\javaStudy\\upload";

        //오리지날 파일명
        String orgName = multipartFile.getOriginalFilename();
        System.out.println("orgName : "+orgName);

        //확장자
        String exName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        System.out.println("exName : "+exName);

        //저장파일명
        String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
        System.out.println("currentTimeMillis : "+System.currentTimeMillis());
        System.out.println("UUID : "+UUID.randomUUID());
        System.out.println("saveName : "+saveName);

        //파일패스
        String filePath = saveDir + "\\" + saveName;
        System.out.println(filePath);

        //파일사이즈
        long fileSize = multipartFile.getSize();
        System.out.println("fileSize : "+fileSize+" Byte");

        FileVO fileVO = new FileVO();
        fileVO.setFilePath(filePath);
        fileVO.setFileSize(fileSize);
        fileVO.setOrgName(orgName);
        fileVO.setSaveName(saveName);
        fileVO.setTitle(title);
        fileVO.setUser_no(user_no);

        System.out.println(fileVO.toString());
        //다오연결해서 DB에 저장
        if(fileVO != null){
            fileUploadDAO.fileinput(fileVO);
        }

        //파일 서버 복사
        try {
            byte[] fileData = multipartFile.getBytes();
            OutputStream outputStream = new FileOutputStream(filePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            bufferedOutputStream.write(fileData);

            if(bufferedOutputStream != null){
                bufferedOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileVO;
    }

    public List<FileVO> imgview() {
        return fileUploadDAO.listAll();
    }

    public String imgdel(String no) {
        int count = fileUploadDAO.imgdel(no);
        System.out.println("몇개 지웠냐 : "+count);
        if(count > 0){
            return no;
        }else{
            return "";
        }
    }

    public boolean delbutton(String no, String user_no) {
        FileVO fileVO = fileUploadDAO.delbutton(no);
        String usernumber = String.valueOf(fileVO.getUser_no());
        boolean flag = false;
        if(fileVO != null && usernumber.equals(user_no)){
            flag = true;
        }
        return flag;
    }
}
